package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.as.core.AuthenticationUtil;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyKeys;
import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyDefaults;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EurecaAsPublicKeyHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.EurecaOperation;
import br.edu.ufcg.computacao.eureca.backend.core.plugins.AuthorizationPlugin;
import br.edu.ufcg.computacao.eureca.common.exceptions.ConfigurationErrorException;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.util.CryptoUtil;
import br.edu.ufcg.computacao.eureca.common.util.ServiceAsymmetricKeysHolder;
import org.apache.log4j.Logger;

import java.security.GeneralSecurityException;

import java.security.interfaces.RSAPublicKey;
import java.util.Collection;

public class ApplicationFacade {
    private static final Logger LOGGER = Logger.getLogger(ApplicationFacade.class);
    private RSAPublicKey asPublicKey;
    private AuthorizationPlugin authorizationPlugin;
    private static ApplicationFacade instance;

    private ApplicationFacade() {
    }

    public static ApplicationFacade getInstance() {
        synchronized (ApplicationFacade.class) {
            if (instance == null) {
                instance = new ApplicationFacade();
            }
            return instance;
        }
    }

    public void setAuthorizationPlugin(AuthorizationPlugin authorizationPlugin) {
        this.authorizationPlugin = authorizationPlugin;
    }

    public Collection<ActiveSummaryResponse> getActives(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES);
        return null;
    }

    public Collection<ActiveDataResponse> getActivesCSV(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_CSV);
        return null;
    }

    public AlumnusSummaryResponse getAlumni(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI);
        return null;
    }

    public Collection<AlumnusDataResponse> getAlumniCSV(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        return null;
    }

    public Collection<DropoutSummaryResponse> getDropouts(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS);
        return null;
    }

    public Collection<DropoutDataResponse> getDropoutsCSV(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        return null;
    }

    public String getPublicKey() throws EurecaException {
        try {
            return CryptoUtil.toBase64(ServiceAsymmetricKeysHolder.getInstance().getPublicKey());
        } catch (GeneralSecurityException e) {
            throw new ConfigurationErrorException(e.getMessage());
        }
    }

    private RSAPublicKey getAsPublicKey() throws EurecaException {
        if (this.asPublicKey == null) {
            this.asPublicKey = EurecaAsPublicKeyHolder.getInstance().getAsPublicKey();
        }
        return this.asPublicKey;
    }

    private SystemUser authenticateAndAuthorize(String token, EurecaOperation operation) throws EurecaException {
        // ToDo: remove this test when the frontend is updated to send the token in the request
        if (token == null || token.equals("")) return null;
        RSAPublicKey keyRSA = getAsPublicKey();
        SystemUser requester = AuthenticationUtil.authenticate(keyRSA, token);
        this.authorizationPlugin.isAuthorized(requester, operation);
        return requester;
    }

    public String getVersionNumber() {
        String buildNumber = null;
        buildNumber = PropertiesHolder.getInstance().getProperty(ConfigurationPropertyKeys.BUILD_NUMBER_KEY,
                    ConfigurationPropertyDefaults.BUILD_NUMBER);
        return SystemConstants.API_VERSION_NUMBER + "-" + buildNumber;
    }
}
