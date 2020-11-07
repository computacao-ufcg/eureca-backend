package br.edu.ufcg.computacao.eureca.backend.core.loaders;

import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyDefaults;
import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyKeys;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.EurecaMapKey;
import br.edu.ufcg.computacao.eureca.backend.core.models.EurecaMapValue;
import br.edu.ufcg.computacao.eureca.backend.core.models.EurecaMultivaluedMapValue;
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

public class GenericLoadMapFromScsvFile<T extends EurecaMapKey, V extends EurecaMapValue,
        U extends EurecaMultivaluedMapValue> implements MapLoader {
    private final Logger LOGGER = Logger.getLogger(GenericLoadMapFromScsvFile.class);

    @Override
    public Map<T, V> loadMap(String mapName, Class tClass, Class vClass, int keySize) {
        String[] headers;
        try {
            headers = loadHeader(mapName);
            return loadData(mapName, headers, tClass, vClass, keySize);
        } catch (IOException e) {
            throw new FatalErrorException(e.getMessage());
        }
    }

    @Override
    public Map loadMultivaluedMap(String mapName, Class tClass, Class vClass, Class uClass, int keySize) {
        String[] headers;
        try {
            headers = loadHeader(mapName);
            return loadData(mapName, headers, tClass, vClass, uClass, keySize);
        } catch (IOException | IllegalAccessException | InstantiationException e) {
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

    private Map<T, V> loadData(String mapName, String[] header, Class<T> tClass, Class<V> vClass, int keySize)
            throws IOException {

        Map<T, V> loadedMap = new HashMap<>();
        String row;
        int line = 0;
        BufferedReader csvReader = getReader(mapName);

        while ((row = csvReader.readLine()) != null) {
            KeyValuePair pair = extractKeyValuePair(header, row, tClass, vClass, keySize, line++);
            LOGGER.debug(String.format(Messages.INSERTING_S_S, pair.getKey().toString(), pair.getValue().toString()));
            loadedMap.put(pair.getKey(), pair.getValue());
        }

        csvReader.close();
        return loadedMap;
    }

    private Map<T, U> loadData(String mapName, String[] header, Class<T> tClass, Class<V> vClass, Class<U> uClass,
                               int keySize) throws IOException, IllegalAccessException, InstantiationException {

        Map<T, U> loadedMap = new HashMap<>();
        String row;
        int line = 0;
        BufferedReader csvReader = getReader(mapName);

        while ((row = csvReader.readLine()) != null) {
            KeyValuePair pair = extractKeyValuePair(header, row, tClass, vClass, keySize, line++);
            U value = loadedMap.get(pair.getKey());
            if (value == null) {
                value = uClass.newInstance();
            }
            value.add(pair.getValue());
            LOGGER.debug(String.format(Messages.INSERTING_S_S, pair.getKey().toString(), value.toString()));
            loadedMap.put(pair.getKey(), value);
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

    private KeyValuePair extractKeyValuePair(String[] header, String row, Class<T> tClass, Class<V> vClass,
                                             int keySize, int index) {
        Gson gson = new Gson();
        T key = null;
        V value = null;

        try {
            String[] data = row.split(";");
            String jsonKey = generateJsonKey(header, data, keySize, index);
            key = gson.fromJson(jsonKey, tClass);
            String jsonData = generateJsonData(header, data, keySize, false);
            try {
                value = gson.fromJson(jsonData, vClass);
            } catch (NumberFormatException e) {
                String fixedJsonData = generateJsonData(header, data, keySize, true);
                value = gson.fromJson(fixedJsonData, vClass);
            }
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
            json = generateJsonFields(header, data, 0, keySize, false);
        }
        LOGGER.debug(String.format(Messages.KEY_S, json));
        return json;
    }

    private String generateJsonData(String[] header, String[] data, int keySize, boolean fix) {
        String json = generateJsonFields(header, data, keySize, (header.length - keySize), fix);
        LOGGER.debug(String.format(Messages.DATA_S, json));
        return json;
    }

    private String generateJsonFields(String[] header, String[] data, int firstIndex, int size, boolean fix) {
        String json = "";
        if (header.length == data.length) {
            for (int i = firstIndex; i < (firstIndex + size); i++) {
                String field = data[i];
                if (fix) field = fixNumberFields(data[i]);
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
