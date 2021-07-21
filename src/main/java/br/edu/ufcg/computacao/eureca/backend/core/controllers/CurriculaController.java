package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;

import java.util.Collection;

public class CurriculaController {
    private DataAccessFacade dataAccessFacade;

    public CurriculaController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public Collection<String> getCurriculumCodes(String courseCode) {
        return this.dataAccessFacade.getCurriculumCodes(courseCode);
    }
}
