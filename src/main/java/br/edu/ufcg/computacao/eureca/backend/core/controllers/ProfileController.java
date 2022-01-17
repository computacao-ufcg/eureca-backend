package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.profile.ProfileResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;

public class ProfileController {
    private DataAccessFacade dataAccessFacade;

    public ProfileController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public ProfileResponse getProfile(String userId) throws EurecaException {
        ProfileResponse response = this.dataAccessFacade.getProfile(userId);
        return response;
    }
}
