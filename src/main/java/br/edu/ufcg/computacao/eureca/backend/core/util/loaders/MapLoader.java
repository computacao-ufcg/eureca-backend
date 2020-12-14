package br.edu.ufcg.computacao.eureca.backend.core.util.loaders;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EurecaMapKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EurecaMapValue;

import java.util.Map;

public interface MapLoader<T extends EurecaMapKey, V extends EurecaMapValue> {
    public Map<T, V> loadMap(String mapName, Class<T> tClass, Class<V> vClass, int keySize);
}
