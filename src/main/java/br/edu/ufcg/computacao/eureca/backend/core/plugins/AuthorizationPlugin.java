package br.edu.ufcg.computacao.eureca.backend.core.plugins;

import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EurecaOperation;
import br.edu.ufcg.computacao.eureca.common.exceptions.UnauthorizedRequestException;

public interface AuthorizationPlugin {
    public boolean isAuthorized(SystemUser requester, EurecaOperation operation) throws UnauthorizedRequestException;
}
