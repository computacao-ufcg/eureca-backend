package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.as.core.AuthenticationUtil;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.*;
import br.edu.ufcg.computacao.eureca.backend.core.controllers.*;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EurecaAsPublicKeyHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.EurecaOperation;
import br.edu.ufcg.computacao.eureca.backend.core.models.GlossaryType;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
import br.edu.ufcg.computacao.eureca.backend.core.plugins.AuthorizationPlugin;
import br.edu.ufcg.computacao.eureca.backend.core.util.GlossaryFactory;
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
    private AlumniController alumniController;
    private CurriculaController curriculaController;
    private StudentsStatisticsController studentsStatisticsController;
    private SubjectsStatisticsController subjectsStatisticsController;
    private EnrollmentsStatisticsController enrollmentsStatisticsController;
    private TeacherStatisticsController teacherStatisticsController;
    private RetentionStatisticsController retentionStatisticsController;
    private static ApplicationFacade instance;

    private ApplicationFacade() {
        this.alumniController = new AlumniController();
        this.curriculaController = new CurriculaController();
        this.studentsStatisticsController = new StudentsStatisticsController();
        this.subjectsStatisticsController = new SubjectsStatisticsController();
        this.enrollmentsStatisticsController = new EnrollmentsStatisticsController();
        this.teacherStatisticsController = new TeacherStatisticsController();
        this.retentionStatisticsController = new RetentionStatisticsController();
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

    public Collection<AlumniDigestResponse> getAlumniBasicData(String token, String from, String to)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        return this.alumniController.getAlumniPerStudentSummary(from, to);
    }

    public Collection<String> getCurriculumCodes() throws EurecaException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        return this.curriculaController.getCurriculumCodes(courseCode);
    }

    public ActivesSummaryResponse getActivesSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_SUMMARY);
        return this.studentsStatisticsController.getActivesSummaryResponse(from, to);
    }

    public Collection<StudentDataResponse> getActivesCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_CSV);
        return this.studentsStatisticsController.getActiveCSV(from, to);
    }

    public AlumniSummaryResponse getAlumniSummary(String token, String from, String to, String language) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_SUMMARY);
        AlumniSummaryResponse response = this.studentsStatisticsController.getAlumniSummaryResponse(from, to);
        AlumniGlossaryFields glossaryFields = null;
        switch(language) {
            case SystemConstants.PORTUGUESE:
            default:
                glossaryFields = new PortugueseAlumniGlossary().getGlossary();
        }
        response.setGlossary(glossaryFields);
        return response;
    }

    public Collection<StudentDataResponse> getAlumniCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        return this.studentsStatisticsController.getAlumniCSV(from, to);
    }

    public DropoutsSummaryResponse getDropoutsSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_SUMMARY);
        return this.studentsStatisticsController.getDropoutsSummaryResponse(from, to);
    }

    public Collection<StudentDataResponse> getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        return this.studentsStatisticsController.getDropoutsCSV(from, to);
    }

    public StudentsSummaryResponse getStudentsStatistics(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_STATISTICS);
        StudentsSummaryResponse response = this.studentsStatisticsController.getStudentsStatistics(from, to);
        StudentsGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.STUDENT);
        response.setGlossary(glossaryFields);
        return response;
    }

    public DelayedSummaryResponse getDelayedSummary(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED_SUMMARY);
        return this.retentionStatisticsController.getDelayedSummary(from, to);
    }

    public Collection<StudentDataResponse> getDelayedCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED_CSV);
        return this.retentionStatisticsController.getDelayedCSV(from, to);
    }

    public Collection<SubjectRetentionSummaryResponse> getSubjectsRetentionSummary(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_RETENTION_SUMMARY);
        Collection<SubjectRetentionSummaryResponse> response = this.retentionStatisticsController.getSubjectsRetentionSummary();
        return response;
    }

    public Collection<SubjectRetentionResponse> getSubjectsRetentionCSV(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_RETENTION_CSV);
        Collection<SubjectRetentionResponse> response = this.retentionStatisticsController.getSubjectsRetentionCSV();
        return response;
    }

    public RetentionSummaryResponse getRetentionStatistics(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_RETENTION_STATISTICS);
        RetentionSummaryResponse response = this.retentionStatisticsController.getRetentionStatistics(from, to);
        RetentionGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.RETENTION);
        response.setGlossary(glossaryFields);
        return response;
    }

    public SubjectSummaryResponse getSubjectsSummary(String token, String from, String to, SubjectType type) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_SUMMARY);
        SubjectSummaryResponse response = this.subjectsStatisticsController.getSubjectsSummary(from, to, type);
        return response;
    }

    public Collection<SubjectDataResponse> getSubjectsCSV(String token, String from, String to, SubjectType subjectType) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_CSV);
        Collection<SubjectDataResponse> response = this.subjectsStatisticsController.getSubjectsCSV(from, to, subjectType);
        return response;
    }

    public SubjectsSummaryResponse getSubjectsStatistics(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_STATISTICS);
        SubjectsSummaryResponse response = this.subjectsStatisticsController.getSubjectStatistics(from, to);
        SubjectsGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.SUBJECT);
        response.setGlossary(glossaryFields);
        return response;
    }

    public Collection<TeachersSummaryItemResponse> getTeachersCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_TEACHERS_CSV);
        return this.teacherStatisticsController.getTeacherCSV();
    }

    public TeachersSummaryResponse getTeachersStatistics(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_TEACHERS_STATISTICS);
        TeachersSummaryResponse response = this.teacherStatisticsController.getTeachersStatisticsMock();
        TeachersGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.TEACHER);
        response.setGlossary(glossaryFields);
        return response;
    }

    public EnrollmentsCSVResponse getEnrollmentsCSV(String token, String from, String to, String lang) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ENROLLMENTS_CSV);
        return this.enrollmentsStatisticsController.getEnrollmentsCSV(from, to);
    }

    public EnrollmentsSummaryResponse getEnrollmentsStatistics(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ENROLLMENTS_STATISTICS);
        EnrollmentsSummaryResponse response = this.enrollmentsStatisticsController.getEnrollmentsStatistics(from, to);
        EnrollmentsGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.ENROLLMENT);
        response.setGlossary(glossaryFields);
        return response;
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
            try {
                this.asPublicKey = EurecaAsPublicKeyHolder.getInstance().getAsPublicKey();
            } catch (EurecaException e) {
                LOGGER.info(Messages.COULD_NOT_FETCH_AS_PUBLIC_KEY);
                this.asPublicKey = null;
                throw e;
            }
        }
        return this.asPublicKey;
    }

    private SystemUser authenticateAndAuthorize(String token, EurecaOperation operation) throws EurecaException {
        RSAPublicKey keyRSA = getAsPublicKey();
        SystemUser requester = AuthenticationUtil.authenticate(keyRSA, token);
        this.authorizationPlugin.isAuthorized(requester, operation);
        return requester;
    }
}
