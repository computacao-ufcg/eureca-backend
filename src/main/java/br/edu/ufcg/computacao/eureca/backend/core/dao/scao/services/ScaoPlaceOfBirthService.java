package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.services;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models.ScaoPlaceOfBirth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Service
public class ScaoPlaceOfBirthService {

    @Autowired
    private ScaoPlaceOfBirthRepository<ScaoPlaceOfBirth, String> scaoPlaceOfBirthDAO;

    private static ScaoPlaceOfBirthService instance;

    public static ScaoPlaceOfBirthService getInstance() {
        return instance;
    }

    @PostConstruct
    private void registerInstance() {
        instance = this;
    }

    public Collection<ScaoPlaceOfBirth> getScaoPlacesOfBirth() {
        return scaoPlaceOfBirthDAO.findAll();
    }
}

