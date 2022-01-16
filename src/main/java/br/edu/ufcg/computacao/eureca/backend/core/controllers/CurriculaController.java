package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.curriculum.CurriculumCodesResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;

import java.util.Collection;

public class CurriculaController {
    private DataAccessFacade dataAccessFacade;

    public CurriculaController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public CurriculumCodesResponse getCurriculumCodes(String courseCode) throws EurecaException {
        Collection<String> response = this.dataAccessFacade.getCurriculumCodes(courseCode);
        return new CurriculumCodesResponse(response);
    }
}
