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
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
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

    public ActiveSummaryResponse getActiveSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES);
        Collection<ActiveStatusSummary> activeStatusSummaries = new TreeSet<>();
        Collection<String> sliderLabel = new TreeSet<>();
        Collection<ActiveSummary> summary = this.dataAccessFacade.getActiveSummary(from, to);
        int activesCount = 0;
        int unfeasible = 0, critical = 0, late = 0, normal = 0, advanced = 0, notApplicable = 0;
        for (ActiveSummary item : summary) {
            sliderLabel.add(item.getAdmissionTerm());
            activesCount++;
            Student active = this.dataAccessFacade.getStudent(item.getRegistration());
            RiskClass riskClass = active.getRiskClass();
            switch(riskClass) {
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
            ActiveStatusSummary statusSummary = new ActiveStatusSummary(item, riskClass);
            activeStatusSummaries.add(statusSummary);
        }
        RiskClassCountSummary countSummary = new RiskClassCountSummary(unfeasible, critical, late, normal, advanced,
                notApplicable);
        RiskClassPercentageSummary percentageSummary =
                new RiskClassPercentageSummary((1.0 * unfeasible)/activesCount,
                        (1.0 * critical)/activesCount, (1.0 * late)/activesCount,
                        (1.0 * normal)/activesCount, (1.0 * advanced)/activesCount,
                        (1.0 * notApplicable)/activesCount);
        RiskSummary riskSummary = new RiskSummary(activesCount, countSummary, percentageSummary);
        ActiveSummaryResponse ret = new ActiveSummaryResponse(sliderLabel, activeStatusSummaries, riskSummary);
        return ret;
    }

    public Collection<StudentDataResponse> getActiveCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_CSV);
        return StudentDataFetcher.getInstance().getActiveCSV(from, to);
    }

    public AlumniSummaryResponse getAlumniSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI);
        Collection<String> sliderLabel = new TreeSet<>();
        double accumulatedGPA = 0;
        int maxAlumniCount = 0;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        int totalAlumniCount = 0;

        Collection<AlumniPerTermSummary> terms = this.dataAccessFacade.getAlumniPerTermSummary(from, to);
        for (AlumniPerTermSummary item : terms) {
            sliderLabel.add(item.getGraduationTerm());
            double averageGPA = item.getAverageGpa();
            int termAlumniCount = item.getAlumniCount();
            String term = item.getGraduationTerm();
            totalAlumniCount += termAlumniCount;
            if (termAlumniCount >= maxAlumniCount) {
                maxAlumniCount = termAlumniCount;
                maxAlumniCountTerm = term;
            }
            if (termAlumniCount <= minAlumniCount) {
                minAlumniCount = termAlumniCount;
                minAlumniCountTerm = term;
            }
            accumulatedGPA += averageGPA * termAlumniCount;
        }

        AlumniSummary alumniSummary = new AlumniSummary((totalAlumniCount == 0 ? -1.0 : accumulatedGPA/totalAlumniCount),
                maxAlumniCount, (terms.size() == 0 ? -1.0 : (1.0*totalAlumniCount)/terms.size()), minAlumniCount,
                maxAlumniCountTerm, minAlumniCountTerm, totalAlumniCount);
        AlumniSummaryResponse alumniSummaryResponse = new AlumniSummaryResponse(sliderLabel, alumniSummary, terms);
        return alumniSummaryResponse;
    }

    public Collection<StudentDataResponse> getAlumniCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        return StudentDataFetcher.getInstance().getAlumniCSV(from, to);
    }

    public DropoutSummaryResponse getDropoutsSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS);
        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsSummary(from, to);
        Collection<String> sliderLabel = new TreeSet<>();
        int grossDropoutCount = 0;
        int dropoutReenterSameCourse = 0;
        for (DropoutPerTermSummary item : dropouts) {
            sliderLabel.add(item.getTerm());
            dropoutReenterSameCourse += item.getReasons().getReenterSameCourse();
            grossDropoutCount += item.getReasons().getTotalDropouts();
        }
        int activeCount = this.dataAccessFacade.getActives(from, to).size();
        int alumniCount = this.dataAccessFacade.getAlumni(from, to).size();
        int enrolled = grossDropoutCount + activeCount + alumniCount;
        int netDropoutCount = grossDropoutCount - dropoutReenterSameCourse;
        DropoutSummary summary = new DropoutSummary((1.0 * grossDropoutCount / alumniCount),
                (1.0 * grossDropoutCount / enrolled), (1.0 * netDropoutCount / alumniCount),
                (1.0 * netDropoutCount / enrolled), grossDropoutCount, netDropoutCount);
        return new DropoutSummaryResponse(sliderLabel, dropouts, summary);
    }

    public Collection<StudentDataResponse> getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        return StudentDataFetcher.getInstance().getDropoutsCSV(from, to);
    }

    public Collection<AlumniPerStudentSummary> getAlumniBasicData(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        return this.dataAccessFacade.getAlumniPerStudentSummary(from, to);
    }

    public Collection<DelayedDataResponse> getDelayedCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED_CSV);
        return StudentDataFetcher.getInstance().getDelayedCSV(from, to);
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
