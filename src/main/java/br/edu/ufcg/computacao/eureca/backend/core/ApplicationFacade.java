package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.as.core.AuthenticationUtil;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.*;
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
    private StudentsStatisticsController studentsStatisticsController;
    private StudentsDataFetcher studentsDataFetcher;
    private SubjectsStatisticsController subjectsStatisticsController;
    private static ApplicationFacade instance;

    private ApplicationFacade() {
        this.studentsStatisticsController = new StudentsStatisticsController();
        this.studentsDataFetcher = new StudentsDataFetcher();
        this.subjectsStatisticsController = new SubjectsStatisticsController();
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

    public ActivesSummaryResponse getActiveSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES);
        return this.studentsStatisticsController.getActivesSummaryResponse(from, to);
    }

    public Collection<StudentDataResponse> getActiveCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_CSV);
        return this.studentsDataFetcher.getActiveCSV(from, to);
    }

    public AlumniSummaryResponse getAlumniSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI);
        return this.studentsStatisticsController.getAlumniSummaryResponse(from, to);
    }

    public Collection<StudentDataResponse> getAlumniCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        return this.studentsDataFetcher.getAlumniCSV(from, to);
    }

    public DropoutsSummaryResponse getDropoutsSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS);
        return this.studentsStatisticsController.getDropoutsSummaryResponse(from, to);
    }

    public Collection<StudentDataResponse> getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        return this.studentsDataFetcher.getDropoutsCSV(from, to);
    }

    public DelayedSummaryResponse getDelayedSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED);
        return this.studentsStatisticsController.getDelayedSummaryResponse(from, to);
    }

    public Collection<StudentDataResponse> getDelayedCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED_CSV);
        return this.studentsDataFetcher.getDelayedCSV(from, to);
    }

    public Collection<AlumniDigestResponse> getAlumniBasicData(String token, String from, String to)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        return this.studentsDataFetcher.getAlumniPerStudentSummary(from, to);
    }

    public StudentsSummaryResponse getStudentsStatistics(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_STATISTICS);
        StudentsSummaryResponse response = this.studentsStatisticsController.getStudentsSummaryResponse(from, to);
        GlossaryFields glossaryFields = null;
        switch(language) {
            case SystemConstants.PORTUGUESE:
            default:
                glossaryFields = new PortugueseGlossary().getGlossary();
        }
        response.setGlossary(glossaryFields);
        return response;
    }

    public SubjectsSummaryResponse getSubjectsStatistics(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_STATISTICS);
        //return this.subjectsStatisticsController.getSubjectsStatistics(from, to, lang);
        return null;
    }

    public TeachersSummaryResponse getTeachersStatistics(String token, String from, String to, String lang)
            throws EurecaException {
        return null;
    }

    public EnrollmentsSummaryResponse getEnrollmentsStatistics(String token, String from, String to, String lang)
            throws EurecaException {
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
