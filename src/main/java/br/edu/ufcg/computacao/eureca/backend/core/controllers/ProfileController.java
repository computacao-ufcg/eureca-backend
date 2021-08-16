package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.ProfileResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;

public class ProfileController {
    private DataAccessFacade dataAccessFacade;

    public ProfileController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public ProfileResponse getProfile(String userId) {
        ProfileResponse response = this.dataAccessFacade.getProfile(userId);
        return response;
    }
}
