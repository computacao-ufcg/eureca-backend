package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.caches;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models.ScaoPlaceOfBirth;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.services.ScaoPlaceOfBirthService;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ScaoPlacesOfBirthCache {
    private Logger LOGGER = Logger.getLogger(ScaoPlacesOfBirthCache.class);

    private Map<String, String> placesOfBirthMap;
    private static ScaoPlacesOfBirthCache instance;

    private ScaoPlacesOfBirthCache() {
        this.placesOfBirthMap = new HashMap<>();
        Collection<ScaoPlaceOfBirth> placesOfBirthSet = ScaoPlaceOfBirthService.getInstance().getScaoPlacesOfBirth();
        placesOfBirthSet.forEach(item -> {
            this.placesOfBirthMap.put(item.getCode(), item.getDescription() + " - " + item.getCounty());
        });
    }

    public static synchronized ScaoPlacesOfBirthCache getInstance() {
        if (instance == null) {
            instance = new ScaoPlacesOfBirthCache();
        }
        return instance;
    }

    public String getPlaceOfBirth(String code) {
        return this.placesOfBirthMap.get(code);
    }
}
