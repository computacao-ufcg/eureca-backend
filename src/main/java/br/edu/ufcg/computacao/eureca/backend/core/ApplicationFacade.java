package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.as.core.AuthenticationUtil;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EurecaAsPublicKeyHolder;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
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
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Collection<StudentDataResponse> activeStudentsData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        actives.forEach(item -> {
            Metrics metrics = MetricsCalculator.getInstance().computeMetrics(item);
            StudentDataResponse studentDataResponse = new StudentDataResponse(item.getId().getRegistration(),
                    item.getStudentData(), metrics);
            activeStudentsData.add(studentDataResponse);
        });
        return activeStudentsData;
    }

    public AlumniSummaryResponse getAlumniSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI);

        Collection<AlumniPerTermSummary> terms = this.dataAccessFacade.getAlumniPerTermSummary(from, to);
        AlumniSummary alumniSummary = new AlumniSummary(terms);
        Collection<String> sliderLabel = getSliderLabel(terms, AlumniPerTermSummary::getGraduationTerm);

        return new AlumniSummaryResponse(sliderLabel, alumniSummary, terms);
    }

    private <T> Collection<String> getSliderLabel(Collection<T> terms, Function<T, String> function) {
        return terms
                .stream()
                .map(function)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Collection<StudentDataResponse> getAlumniCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        Collection<StudentDataResponse> alumniData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getAlumni(from, to);
        actives.forEach(item -> {
            Metrics metrics = MetricsCalculator.getInstance().computeMetrics(item);
            StudentDataResponse studentDataResponse = new StudentDataResponse(item.getId().getRegistration(),
                    item.getStudentData(), metrics);
            alumniData.add(studentDataResponse);
        });
        return alumniData;
    }

    public DropoutSummaryResponse getDropoutsSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS);

        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsSummary(from, to);
        Collection<String> sliderLabel = this.getSliderLabel(dropouts, DropoutPerTermSummary::getTerm);

        int activeCount = this.dataAccessFacade.getActives(from, to).size();
        int alumniCount = this.dataAccessFacade.getAlumni(from, to).size();
        DropoutSummary summary = new DropoutSummary(dropouts, activeCount, alumniCount);

        return new DropoutSummaryResponse(sliderLabel, dropouts, summary);
    }

    public Collection<StudentDataResponse> getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        Collection<StudentDataResponse> dropoutsData = new TreeSet<>();
        Collection<Student> dropouts = this.dataAccessFacade.getDropouts(from, to);
        dropouts.forEach(item -> {
            Metrics metrics = MetricsCalculator.getInstance().computeMetrics(item);
            StudentDataResponse studentDataResponse = new StudentDataResponse(item.getId().getRegistration(),
                    item.getStudentData(), metrics);
            dropoutsData.add(studentDataResponse);
        });
        return dropoutsData;
    }

    public Collection<AlumniPerStudentSummary> getAlumniBasicData(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        return this.dataAccessFacade.getAlumniPerStudentSummary(from, to);
    }

    public Collection<DelayedDataResponse> getDelayedCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED_CSV);
        return this.dataAccessFacade.getDelayed(from, to)
                .stream()
                .map(DelayedDataResponse::new)
                .collect(Collectors.toSet());
    }

    public StudentsSummaryResume getStudentsStatistics(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_STATISTICS);
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        Collection<AlumniPerTermSummary> alumni = this.dataAccessFacade.getAlumniPerTermSummary(from, to);
        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsSummary(from, to);
        Collection<Student> delayed = this.dataAccessFacade.getDelayed(from, to);

        Collection<DelayedDataResponse> delayedData = delayed
                .stream()
                .map(DelayedDataResponse::new)
                .collect(Collectors.toList());

        int alumniCount = this.dataAccessFacade.getAlumni(from, to).size();
        int activesCount = actives.size();

        AlumniSummary alumniSummary = new AlumniSummary(alumni);
        ActiveSummaryResume activeSummary = new ActiveSummaryResume(actives);
        DelayedSummary delayedSummary = new DelayedSummary(delayedData);
        DropoutSummaryResume dropoutSummary = new DropoutSummaryResume(dropouts, activesCount, alumniCount);

        return new StudentsSummaryResume(activeSummary, alumniSummary, delayedSummary, dropoutSummary);
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
