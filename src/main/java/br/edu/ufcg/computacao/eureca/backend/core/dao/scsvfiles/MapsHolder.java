package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.util.loaders.GenericLoadMapFromScsvFile;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.ClassFactory;
import br.edu.ufcg.computacao.eureca.common.exceptions.FatalErrorException;
import br.edu.ufcg.computacao.eureca.common.util.HomeDir;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MapsHolder<T extends EurecaMapKey, V extends EurecaMapValue> {
    private Logger LOGGER = Logger.getLogger(MapsHolder.class);

    private Map<String, Map<T, V>> maps;

    public MapsHolder(String mapsListFile) {
        try {
            this.maps = loadAllMaps(mapsListFile);
        } catch (IOException e) {
            throw new FatalErrorException(e.getMessage());
        }
    }

    private Map<String, Map<T,V>> loadAllMaps(String mapsListFile) throws IOException {
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
                GenericLoadMapFromScsvFile<T, V> loader = new GenericLoadMapFromScsvFile<>();
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

    public Map<T, V> getMap(String name) {
        return maps.get(name);
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
}
