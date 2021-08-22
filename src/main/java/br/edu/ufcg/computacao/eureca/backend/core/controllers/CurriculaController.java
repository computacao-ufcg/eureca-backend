package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.CurriculumCodesResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;

import java.util.Collection;

public class CurriculaController {
    private DataAccessFacade dataAccessFacade;

    public CurriculaController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public CurriculumCodesResponse getCurriculumCodes(String courseCode) throws InvalidParameterException {
        Collection<String> response = this.dataAccessFacade.getCurriculumCodes(courseCode);
        return new CurriculumCodesResponse(response);
    }
}
