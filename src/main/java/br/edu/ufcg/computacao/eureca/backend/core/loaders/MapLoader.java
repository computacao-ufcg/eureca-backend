package br.edu.ufcg.computacao.eureca.backend.core.loaders;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapKey;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMultivaluedMapValue;

import java.util.Map;

public interface MapLoader<T extends EurecaMapKey, V extends EurecaMapValue, U extends EurecaMultivaluedMapValue> {
    public Map<T, V> loadMap(String mapName, Class<T> tClass, Class<V> vClass, int keySize);

    public Map<T, U> loadMultivaluedMap(String mapName, Class<T> tClass, Class<V> vClass, Class<U> uClass, int keySize);
}
