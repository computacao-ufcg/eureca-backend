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

    public AlumniResponse getAlumni(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        Collection<AlumniDigestResponse> digest = this.alumniController.getAlumniPerStudentSummary(from, to);
        AlumniGlossaryFields glossaryFields = null;
        switch(language) {
            case SystemConstants.PORTUGUESE:
            default:
                glossaryFields = new PortugueseAlumniGlossary().getGlossary();
        }
        AlumniResponse response = new AlumniResponse(digest);
        response.setGlossary(glossaryFields);
        return response;
    }

    public CurriculumCodesResponse getCurriculumCodes(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_CURRICULUM_CODES);
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        return this.curriculaController.getCurriculumCodes(courseCode);
    }

    public ActivesStatisticsResponse getActivesStatistics(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_SUMMARY);
        return this.studentsStatisticsController.getActivesSummaryResponse(from, to);
    }

    public StudentResponse getActivesCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_CSV);
        return this.studentsStatisticsController.getActiveCSV(from, to);
    }

    public AlumniStatisticsResponse getAlumniStatistics(String token, String from, String to, String language) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_SUMMARY);
        return this.studentsStatisticsController.getAlumniSummaryResponse(from, to);
    }

    public StudentResponse getAlumniCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        return this.studentsStatisticsController.getAlumniCSV(from, to);
    }

    public DropoutsStatisticsResponse getDropoutsStatistics(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_SUMMARY);
        return this.studentsStatisticsController.getDropoutsSummaryResponse(from, to);
    }

    public StudentResponse getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        return this.studentsStatisticsController.getDropoutsCSV(from, to);
    }

    public StudentsStatisticsSummaryResponse getStudentsStatisticsSummary(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_STATISTICS);
        StudentsStatisticsSummaryResponse response = this.studentsStatisticsController.getStudentsStatistics(from, to);
        StudentsGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.STUDENT);
        response.setGlossary(glossaryFields);
        return response;
    }

    public DelayedStatisticsResponse getDelayedStatistics(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED_SUMMARY);
        return this.retentionStatisticsController.getDelayedSummary(from, to);
    }

    public StudentResponse getDelayedCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DELAYED_CSV);
        return this.retentionStatisticsController.getDelayedCSV(from, to);
    }

    public SubjectRetentionStatisticsResponse getSubjectsRetentionStatistics(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_RETENTION_SUMMARY);
        return this.retentionStatisticsController.getSubjectRetentionSummary();
    }

    public SubjectRetentionResponse getSubjectsRetentionCSV(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_RETENTION_CSV);
        return this.retentionStatisticsController.getSubjectsRetentionCSV();
    }

    public RetentionStatisticsSummaryResponse getRetentionStatisticsSummary(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_RETENTION_STATISTICS);
        RetentionStatisticsSummaryResponse response = this.retentionStatisticsController.getRetentionStatistics(from, to);
        RetentionGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.RETENTION);
        response.setGlossary(glossaryFields);
        return response;
    }

    public SubjectsStatisticsResponse getSubjectsStatistics(String token, String from, String to, SubjectType type) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_SUMMARY);
        SubjectsStatisticsResponse response = this.subjectsStatisticsController.getSubjectsSummary(from, to, type);
        return response;
    }

    public SubjectResponse getSubjectsCSV(String token, String from, String to, SubjectType subjectType) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_CSV);
        return this.subjectsStatisticsController.getSubjectsCSV(from, to, subjectType);
    }

    public SubjectsStatisticsSummaryResponse getSubjectsStatisticsSummary(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_STATISTICS);
        SubjectsStatisticsSummaryResponse response = this.subjectsStatisticsController.getSubjectStatistics(from, to);
        SubjectsGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.SUBJECT);
        response.setGlossary(glossaryFields);
        return response;
    }

    public TeachersResponse getTeachersCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_TEACHERS_CSV);
        return this.teacherStatisticsController.getTeacherCSV();
    }

    public TeachersStatisticsSummaryResponse getTeachersStatisticsSummary(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_TEACHERS_STATISTICS);
        TeachersStatisticsSummaryResponse response = this.teacherStatisticsController.getTeachersStatisticsMock();
        TeachersGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.TEACHER);
        response.setGlossary(glossaryFields);
        return response;
    }

    public EnrollmentsResponse getEnrollmentsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ENROLLMENTS_CSV);
        return this.enrollmentsStatisticsController.getEnrollmentsCSV(from, to);
    }

    public EnrollmentsStatisticsSummaryResponse getEnrollmentsStatisticsSummary(String token, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ENROLLMENTS_STATISTICS);
        EnrollmentsStatisticsSummaryResponse response = this.enrollmentsStatisticsController.getEnrollmentsStatistics(from, to);
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
