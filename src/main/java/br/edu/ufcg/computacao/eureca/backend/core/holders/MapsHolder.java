package br.edu.ufcg.computacao.eureca.backend.core.holders;

import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyDefaults;
import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyKeys;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.loaders.GenericLoadMapFromScsvFile;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated.Description;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated.IdCode;
import br.edu.ufcg.computacao.eureca.backend.core.util.ClassFactory;
import br.edu.ufcg.computacao.eureca.common.exceptions.FatalErrorException;
import br.edu.ufcg.computacao.eureca.common.util.HomeDir;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MapsHolder<T extends EurecaMapKey, V extends EurecaMapValue, U extends EurecaMultivaluedMapValue> {
    private Logger LOGGER = Logger.getLogger(MapsHolder.class);

    private static MapsHolder instance;
    private Map<String, Map<T, V>> maps;
    private Map<String, Map<T, U>> multivaluedMaps;

    private MapsHolder() {

        try {
            this.maps = loadAllMaps();
            this.multivaluedMaps = loadAllMultivaluedMaps();
        } catch (IOException e) {
            throw new FatalErrorException(e.getMessage());
        }
    }

    private String getIdCodeStr(String mapName, String description) {
        Map<IdCode, Description> map = MapsHolder.getInstance().getMap(mapName);
        IdCode match = null;
        for (Map.Entry<IdCode, Description> entry : map.entrySet()) {
            IdCode k = entry.getKey();
            Description v = entry.getValue();
            if (v.getDescription().equals(description)) {
                match = k;
                break;
            }
        }
        if (match == null) match = new IdCode(-1);
        return match.toString();
    }

    private Map<String, Map<T,V>> loadAllMaps() throws IOException {
        String mapsListFile = PropertiesHolder.getInstance().getProperty(ConfigurationPropertyKeys.MAPS_FILE,
                ConfigurationPropertyDefaults.DEFAULT_MAPS_FILE);
        String filePath = HomeDir.getPath() + mapsListFile;
        Map<String, Map<T, V>> maps = new HashMap<>();

        ClassFactory factory = new ClassFactory();
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        while ((row = csvReader.readLine()) != null) {
            try {
                String[] data = row.split(",");
                String tableName = data[0];
                T tClass = (T) factory.createClass(data[1]);
                V vClass = (V) factory.createClass(data[2]);
                int keySize = Integer.parseInt(data[3]);
                GenericLoadMapFromScsvFile<T, V, U> loader = new GenericLoadMapFromScsvFile<>();
                Map<T, V> tvMap = loader.loadMap(tableName, tClass.getClass(), vClass.getClass(), keySize);
                LOGGER.info(String.format(Messages.ADD_TABLE_S, tableName));
                maps.put(tableName, tvMap);
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        csvReader.close();
        return maps;
    }

    private Map<String, Map<T,U>> loadAllMultivaluedMaps() throws IOException {
        String mapsListFile = PropertiesHolder.getInstance().getProperty(ConfigurationPropertyKeys.MULTIVALUED_MAPS_FILE,
                ConfigurationPropertyDefaults.DEFAULT_MULTIVALUED_MAPS_FILE);
        String filePath = HomeDir.getPath() + mapsListFile;
        Map<String, Map<T, U>> maps = new HashMap<>();

        ClassFactory factory = new ClassFactory();
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        while ((row = csvReader.readLine()) != null) {
            try {
                String[] data = row.split(",");
                String tableName = data[0];
                T tClass = (T) factory.createClass(data[1]);
                V vClass = (V) factory.createClass(data[2]);
                U uClass = (U) factory.createClass(data[3]);
                int keySize = Integer.parseInt(data[4]);
                GenericLoadMapFromScsvFile<T, V, U> loader = new GenericLoadMapFromScsvFile<>();
                Map<T, U> tuMap = loader.loadMultivaluedMap(tableName, tClass.getClass(), vClass.getClass(),
                        uClass.getClass(), keySize);
                maps.put(tableName, tuMap);
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        csvReader.close();
        return maps;
    }

    public static MapsHolder getInstance() {
        synchronized (MapsHolder.class) {
            if (instance == null) {
                instance = new MapsHolder();
            }
            return instance;
        }
    }

    public Map<T, V> getMap(String name) {
        return maps.get(name);
    }

    public Map<T, U> getMultivaluedMap(String name) {
        return multivaluedMaps.get(name);
    }

    public EurecaMultivaluedMapValue getCollection(String mapName, EurecaMapKey key) {
        Map<T,U> map = this.multivaluedMaps.get(mapName);
        return map.get(key);
    }

    public EurecaMapValue getValue(String mapName, EurecaMapKey key) {
        Map<T,V> map = this.maps.get(mapName);
        return map.get(key);
    }

    private void printMaps() {
        this.maps.forEach((name, map) -> {
            printMap(name);
        });
    }

    private void printMap(String name) {
        Map<T,V> map = getMap(name);
        System.out.println("Map: " + name);
        map.forEach((k, v) -> {
            System.out.println("key: [" + k.toString() + "]->[" + v.toString() + "]");
        });
    }

    private void printMultivaluedMaps() {
        this.multivaluedMaps.forEach((name, map) -> {
            printMultivaluedMap(name);
        });
    }

    private void printMultivaluedMap(String name) {
        Map<T,U> map = getMultivaluedMap(name);
        System.out.println("Map: " + name);
        map.forEach((k, v) -> {
            System.out.println("key: [" + k.toString() + "]->{" + v.toString() + "}");
            v.forEach(item -> System.out.println("[" + item.toString() + "]"));
        });
    }
}
