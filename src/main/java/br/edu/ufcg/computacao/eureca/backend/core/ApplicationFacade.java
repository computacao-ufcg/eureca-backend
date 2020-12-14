package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.as.core.AuthenticationUtil;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EurecaAsPublicKeyHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.plugins.AuthorizationPlugin;
import br.edu.ufcg.computacao.eureca.common.exceptions.ConfigurationErrorException;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.util.CryptoUtil;
import br.edu.ufcg.computacao.eureca.common.util.ServiceAsymmetricKeysHolder;
import org.apache.log4j.Logger;

import java.security.GeneralSecurityException;

import java.security.interfaces.RSAPublicKey;
import java.util.Collection;
import java.util.TreeSet;

public class ApplicationFacade {
    private static final Logger LOGGER = Logger.getLogger(ApplicationFacade.class);
    private RSAPublicKey asPublicKey;
    private AuthorizationPlugin authorizationPlugin;
    private DataAccessFacade dataAccessFacade;
    private static ApplicationFacade instance;

    private ApplicationFacade() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
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

    public ActiveDataResponse getActiveStudentsSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES);
        Collection<String> sliderLabel = new TreeSet<>();
        Collection<ActiveData> summary = this.dataAccessFacade.getActiveStudentsSummary(from, to);
        int activesCount = 0;
        int unfeasible = 0, critical = 0, late = 0, normal = 0, advanced = 0, notApplicable = 0;
        for (ActiveData item : summary) {
            sliderLabel.add(item.getAdmissionTerm());
            activesCount++;
            switch(item.getRiskClass()) {
                case UNFEASIBLE:
                    unfeasible++;
                    break;
                case CRITICAL:
                    critical++;
                    break;
                case LATE:
                    late++;
                    break;
                case NORMAL:
                    normal++;
                    break;
                case ADVANCED:
                    advanced++;
                    break;
                default:
                    notApplicable++;
                    break;
            }
        }
        RiskClassCountSummary countSummary = new RiskClassCountSummary(unfeasible, critical, late, normal, advanced,
                notApplicable);
        RiskClassPercentageSummary percentageSummary =
                new RiskClassPercentageSummary((1.0 * unfeasible)/activesCount,
                        (1.0 * critical)/activesCount, (1.0 * late)/activesCount,
                        (1.0 * normal)/activesCount, (1.0 * advanced)/activesCount,
                        (1.0 * notApplicable)/activesCount);
        RiskSummary riskSummary = new RiskSummary(activesCount, countSummary, percentageSummary);
        ActiveDataResponse ret = new ActiveDataResponse(sliderLabel, summary, riskSummary);
        return ret;
    }

    public Collection<StudentDataResponse> getActiveStudentsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_CSV);
        return this.dataAccessFacade.getActiveStudents(from, to);
    }

    public AlumniDataResponse getAlumniSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI);
        return this.dataAccessFacade.getAlumniSummary(from, to);
    }

    public Collection<AlumniData> getAlumniCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        return this.dataAccessFacade.getAllAlumni(from, to);
    }

    public Collection<DropoutSummaryResponse> getDropoutsSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS);
        return this.dataAccessFacade.getDropoutsSummary(from, to);
    }

    public Collection<DropoutData> getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        return this.dataAccessFacade.getAllDropouts(from, to);
    }

    public Collection<AlumnusBasicData> getAlumniBasicData(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        return this.dataAccessFacade.getAlumniBasicData(from, to);
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
