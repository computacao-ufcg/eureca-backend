package br.edu.ufcg.computacao.eureca.backend.core.plugins;

import br.edu.ufcg.computacao.eureca.common.exceptions.UnauthorizedRequestException;
import br.edu.ufcg.computacao.eureca.backend.core.models.EurecaOperation;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;

public class DefaultAuthorizationPlugin implements AuthorizationPlugin {

    @Override
    public boolean isAuthorized(SystemUser requester, EurecaOperation operation) throws UnauthorizedRequestException {
        return true;
    }
}
