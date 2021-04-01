package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.as.core.AuthenticationUtil;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.*;
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
import java.util.Map;

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

    public ActiveSummaryResponse getActiveSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES);
        return this.studentsStatisticsController.getActiveSummaryResponse(from, to);
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

    public DropoutSummaryResponse getDropoutsSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS);
        return this.studentsStatisticsController.getDropoutsSummaryResponse(from, to);
    }

    public Collection<StudentDataResponse> getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        return this.studentsDataFetcher.getDropoutsCSV(from, to);
    }

    public Collection<AlumniPerStudentSummary> getAlumniBasicData(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        return this.studentsDataFetcher.getAlumniPerStudentSummary(from, to);
    }

    public Collection<DelayedDataResponse> getDelayedCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED_CSV);
        return this.studentsDataFetcher.getDelayedCSV(from, to);
    }

    public StudentsSummaryResponse getStudentsStatistics(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_STATISTICS);
        return this.studentsStatisticsController.getStudentsSummaryResume(from, to);
    }

    public Map<String, Collection<SubjectSummaryResponse>> getSubjectsStatistics(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_STATISTICS);
        return this.subjectsStatisticsController.getSubjectsStatistics(from, to);
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
