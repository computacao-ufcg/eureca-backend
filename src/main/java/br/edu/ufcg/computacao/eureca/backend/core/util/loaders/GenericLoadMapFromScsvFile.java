package br.edu.ufcg.computacao.eureca.backend.core.util.loaders;

import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyDefaults;
import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyKeys;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EurecaMapKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EurecaMapValue;
import br.edu.ufcg.computacao.eureca.common.exceptions.FatalErrorException;
import br.edu.ufcg.computacao.eureca.common.util.HomeDir;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GenericLoadMapFromScsvFile<T extends EurecaMapKey, V extends EurecaMapValue> implements MapLoader {
    private final Logger LOGGER = Logger.getLogger(GenericLoadMapFromScsvFile.class);

    @Override
    public Map<T, V> loadMap(String mapName, Class tClass, Class vClass, int keySize) {
        String[] headers;
        Set<String> numberFields = null;
        try {
            headers = loadHeader(mapName);
            numberFields = loadNumberFields(mapName);
            return loadData(mapName, headers, numberFields, tClass, vClass, keySize);
        } catch (IOException e) {
            throw new FatalErrorException(e.getMessage());
        }
    }

    private String[] loadHeader(String mapName) throws IOException {
        String tablesDir = PropertiesHolder.getInstance().getProperty(ConfigurationPropertyKeys.TABLES_DIR_KEY,
                ConfigurationPropertyDefaults.TABLES_DIR);
        String headerFilePath = HomeDir.getPath() + tablesDir + "/" + mapName + ".header";
        BufferedReader csvReader = new BufferedReader(new FileReader(headerFilePath));
        String row = csvReader.readLine();
        String[] header = row.split(";");
        csvReader.close();
        return header;
    }

    private Set<String> loadNumberFields(String mapName) throws IOException {
        String tablesDir = PropertiesHolder.getInstance().getProperty(ConfigurationPropertyKeys.TABLES_DIR_KEY,
                ConfigurationPropertyDefaults.TABLES_DIR);
        String numbersFilePath = HomeDir.getPath() + tablesDir + "/" + mapName + ".numbers";
        BufferedReader csvReader = new BufferedReader(new FileReader(numbersFilePath));
        String row = csvReader.readLine();
        csvReader.close();
        String[] numberFields = row.split(";");
        Set<String> numberFieldsSet = new TreeSet<>();
        for (int i = 0; i < numberFields.length; i++) {
            numberFieldsSet.add(numberFields[i]);
        }
        return numberFieldsSet;
    }

    private Map<T, V> loadData(String mapName, String[] header, Set<String> numberFields, Class<T> tClass, Class<V> vClass, int keySize)
            throws IOException {

        Map<T, V> loadedMap = new HashMap<>();
        String row;
        int line = 1;
        BufferedReader csvReader = getReader(mapName);

        while ((row = csvReader.readLine()) != null) {
            KeyValuePair pair = extractKeyValuePair(header, numberFields, row, tClass, vClass, keySize, line++);
            LOGGER.debug(String.format(Messages.INSERTING_S_S, pair.getKey().toString(), pair.getValue().toString()));
            loadedMap.put(pair.getKey(), pair.getValue());
        }

        csvReader.close();
        return loadedMap;
    }

    private BufferedReader getReader(String mapName) throws FileNotFoundException {
        String tablesDir = PropertiesHolder.getInstance().getProperty(ConfigurationPropertyKeys.TABLES_DIR_KEY,
                ConfigurationPropertyDefaults.TABLES_DIR);
        String dataFilePath = HomeDir.getPath() + tablesDir + "/" + mapName + ".data";
        LOGGER.info(String.format(Messages.LOADING_TABLE_S_FROM_S, mapName, dataFilePath));
        BufferedReader csvReader = new BufferedReader(new FileReader(dataFilePath));
        return csvReader;
    }

    private KeyValuePair extractKeyValuePair(String[] header, Set<String> numberFields, String row, Class<T> tClass, Class<V> vClass,
                                             int keySize, int index) {
        Gson gson = new Gson();
        T key = null;
        V value = null;

        try {
            String[] data = row.split(";");
            String jsonKey = generateJsonKey(header, data, keySize, index);
            key = gson.fromJson(jsonKey, tClass);
            String jsonData = generateJsonData(header, numberFields, data, keySize);
            LOGGER.debug(jsonData);
            value = gson.fromJson(jsonData, vClass);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return new KeyValuePair(key, value);
    }

    private String generateJsonKey(String[] header, String[] data, int keySize, int line) {
        String json = "";
        if (keySize == 0) {
            json = String.format("{ \"id\": %d }", line);
        } else {
            json = generateJsonFields(header, new TreeSet<>(), data, 0, keySize);
        }
        LOGGER.debug(String.format(Messages.KEY_S, json));
        return json;
    }

    private String generateJsonData(String[] header, Set<String> numberFields, String[] data, int keySize) {
        String json = generateJsonFields(header, numberFields, data, keySize, (header.length - keySize));
        LOGGER.debug(String.format(Messages.DATA_S, json));
        return json;
    }

    private String generateJsonFields(String[] header, Set<String> numberFields, String[] data, int firstIndex, int size) {
        String json = "";
        if (header.length == data.length) {
            for (int i = firstIndex; i < (firstIndex + size); i++) {
                String field = null;
                if (numberFields.contains(header[i])) {
                    field = fixNumberFields(data[i]);
                } else {
                    field = data[i];
                }
                json = String.format("%s, \"%s\": \"%s\"", json, header[i], field);
            }
            if (json.charAt(0) == ',') {
                json = json.substring(1, json.length());
            }
        }
        json = String.format("{%s }", json);
        return json;
    }

    private String fixNumberFields(String field) {
        if (field.equals("") || field.equals("-")) return "-1";
        return field.replace(",", ".");
    }

    private class KeyValuePair {
        T key;
        V value;

        public KeyValuePair(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
