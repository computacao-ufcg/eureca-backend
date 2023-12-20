package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.as.core.AuthenticationUtil;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.VersionResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.active.ActivesStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniDigest;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.curriculum.CurriculumCodesResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.profile.ProfileResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.RetentionStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectsRetentionResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectsRetentionStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.student.StudentsRetentionStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsTimeseriesResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.*;
import br.edu.ufcg.computacao.eureca.backend.core.controllers.*;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EurecaAsPublicKeyHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.plugins.AuthorizationPlugin;
import br.edu.ufcg.computacao.eureca.backend.core.util.factory.GlossaryFactory;
import br.edu.ufcg.computacao.eureca.common.exceptions.ConfigurationErrorException;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.util.BuildNumberHolder;
import br.edu.ufcg.computacao.eureca.common.util.CryptoUtil;
import br.edu.ufcg.computacao.eureca.common.util.ServiceAsymmetricKeysHolder;
import org.apache.log4j.Logger;

import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

public class ApplicationFacade {
    private static final Logger LOGGER = Logger.getLogger(ApplicationFacade.class);
    private RSAPublicKey asPublicKey;
    private AuthorizationPlugin authorizationPlugin;
    private AlumniController alumniController;
    private CommunicationController communicationController;
    private ProfileController profileController;
    private CurriculaController curriculaController;
    private PreEnrollmentController preEnrollmentController;
    private StudentsStatisticsController studentsStatisticsController;
    private SubjectsStatisticsController subjectsStatisticsController;
    private EnrollmentsStatisticsController enrollmentsStatisticsController;
    private TeacherStatisticsController teacherStatisticsController;
    private RetentionStatisticsController retentionStatisticsController;
    private static ApplicationFacade instance;

    private ApplicationFacade() {
        this.alumniController = new AlumniController();
        this.profileController = new ProfileController();
        this.curriculaController = new CurriculaController();
        this.communicationController = new CommunicationController();
        this.preEnrollmentController = new PreEnrollmentController();
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

    public AlumniResponse getAlumniDigest(String token, String courseCode, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_DIGEST);
        Collection<AlumniDigest> digest = this.alumniController.getAlumniDigest(courseCode, from, to);
        AlumniResponse response = new AlumniResponse(digest);
        AlumniGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.ALUMNI);
        response.setGlossary(glossaryFields);
        return response;
    }

    public ProfileResponse getProfile(String token) throws EurecaException {
        SystemUser systemUser = authenticateAndAuthorize(token, EurecaOperation.GET_PROFILE);
        return this.profileController.getProfile(systemUser.getId());
    }

    public CurriculumCodesResponse getCurriculumCodes(String token, String courseCode) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_CURRICULUM_CODES);
        return this.curriculaController.getCurriculumCodes(courseCode);
    }

    public EnrollmentsStatisticsResponse getSubjectEnrollmentsStatistics(String token, String courseCode, String curriculumCode, String from, String to, SubjectType type) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECT_ENROLLMENTS_STATISTICS);
        return this.enrollmentsStatisticsController.getSubjectEnrollmentsStatistics(courseCode, curriculumCode, from, to, type);
    }

    public EnrollmentsResponse getSubjectEnrollmentsCSV(String token, String courseCode, String curriculumCode, String from, String to, SubjectType type) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECT_ENROLLMENTS_CSV);
        return this.enrollmentsStatisticsController.getSubjectEnrollmentsCSV(courseCode, curriculumCode, from, to, type);
    }

    public EnrollmentsStatisticsSummaryResponse getSubjectEnrollmentsStatisticsSummary(String token, String courseCode, String curriculumCode, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECT_ENROLLMENTS_STATISTICS_SUMMARY);
        EnrollmentsStatisticsSummaryResponse response = this.enrollmentsStatisticsController.getSubjectEnrollmentsStatisticsSummary(courseCode, curriculumCode, from, to);
        EnrollmentsGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.ENROLLMENT);
        response.setGlossary(glossaryFields);
        return response;
    }

    public ActivesStatisticsResponse getActivesStatistics(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_STATISTICS);
        return this.studentsStatisticsController.getActivesStatistics(courseCode, curriculumCode, from, to);
    }

    public StudentsResponse getActivesCSV(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_CSV);
        return this.studentsStatisticsController.getActiveCSV(courseCode, curriculumCode, from, to);
    }
    public StudentsTimeseriesResponse getActivesTimeseries(String token, String courseCode, String curriculumCode,
                                                           String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_TIMESERIES);
        return this.studentsStatisticsController.getActiveTimeseries(courseCode, curriculumCode, from, to);
    }
    public AlumniStatisticsResponse getAlumniStatistics(String token, String courseCode, String curriculumCode, String from, String to, String language) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_STATISTICS);
        return this.studentsStatisticsController.getAlumniStatistics(courseCode, curriculumCode, from, to);
    }

    public StudentsResponse getAlumniCSV(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        return this.studentsStatisticsController.getAlumniCSV(courseCode, curriculumCode, from, to);
    }

    public DropoutsStatisticsResponse getDropoutsStatistics(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_STATISTICS);
        return this.studentsStatisticsController.getDropoutsStatistics(courseCode, curriculumCode, from, to);
    }

    public StudentsResponse getDropoutsCSV(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        return this.studentsStatisticsController.getDropoutsCSV(courseCode, curriculumCode, from, to);
    }

    public StudentsStatisticsSummaryResponse getStudentsStatisticsSummary(String token, String courseCode, String curriculumCode, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_STATISTICS_SUMMARY);
        StudentsStatisticsSummaryResponse response = this.studentsStatisticsController.getStudentsStatisticsSummary(courseCode, curriculumCode, from, to);
        StudentsGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.STUDENT);
        response.setGlossary(glossaryFields);
        return response;
    }

    public Map<String, EmailSearchResponse> getStudentsEmailsSearch(String token, String courseCode, String curriculumCode,
                                                       String admissionTerm, String studentName, String gender,
                                                       String status, String craOperation, String cra,
                                                                    String enrolledCreditsOperation, String enrolledCredits,
                                                                    String affirmativePolicy)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_EMAILS);
        Map<String, EmailSearchResponse> response = this.communicationController.getStudentsEmailsSearch(courseCode, curriculumCode,
                admissionTerm, studentName, gender, status, craOperation, cra, enrolledCreditsOperation, enrolledCredits, affirmativePolicy);
        return response;
    }

    public Map<String, EmailSearchResponse> getSubjectEmailsSearch(String token, String courseCode, String curriculumCode,
                                                                        String subjectName, String subjectType,
                                                                        String academicUnit, String term) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_EMAILS);
        Map<String, EmailSearchResponse> response = this.communicationController.getSubjectEmailsSearch(courseCode, curriculumCode, subjectName,
                subjectType, academicUnit, term);
        return response;
    }

    public Map<String, EmailSearchResponse> getTeacherEmailsSearch(String token, String courseCode, String curriculumCode,
                                                                   String teacherName, String teacherId, String academicUnit,
                                                                   String term) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_EMAILS);
        Map<String, EmailSearchResponse> response = this.communicationController.getTeacherEmailsSearch(courseCode, curriculumCode, teacherName,
                teacherId, academicUnit, term);
        return response;
    }

    public StudentPreEnrollmentResponse getPreEnrollment(String token, String courseCode,
                                                         String registration, String term, Integer numCredits,
                                                         String optionalPriorityList, String electivePriorityList,
                                                         String complementaryPriorityList, String mandatoryPriorityList)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_PRE_ENROLLMENT);
        StudentPreEnrollment studentPreEnrollment = this.preEnrollmentController.getStudentPreEnrollment(courseCode,
                registration, term, numCredits, optionalPriorityList, electivePriorityList,
                complementaryPriorityList, mandatoryPriorityList);
        return new StudentPreEnrollmentResponse(studentPreEnrollment);
    }

    public PreEnrollmentsResponse getPreEnrollments(String token, String courseCode, String term) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_PRE_ENROLLMENTS);
        PreEnrollments preEnrollments = this.preEnrollmentController.getActivesPreEnrollments(courseCode, term);
        return new PreEnrollmentsResponse(preEnrollments.getActivesPreEnrollment(), preEnrollments.getSubjectDemandSummary());
    }

    public PreEnrollmentsCSVResponse getPreEnrollmentsCSV(String token, String courseCode, String term) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_PRE_ENROLLMENTS_CSV);
        PreEnrollments preEnrollments = this.preEnrollmentController.getActivesPreEnrollments(courseCode, term);
        return new PreEnrollmentsCSVResponse(preEnrollments.getActivesPreEnrollment());
    }

    public DemandResponse getDemand(String token, String courseCode, String term) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_PRE_ENROLLMENTS);
        PreEnrollments preEnrollments = this.preEnrollmentController.getActivesPreEnrollments(courseCode, term);
        return new DemandResponse(preEnrollments.getSubjectDemandSummary());
    }

    public DemandCSVResponse getDemandCSV(String token, String courseCode, String term)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DEMAND_CSV);
        PreEnrollments preEnrollments = this.preEnrollmentController.getActivesPreEnrollments(courseCode, term);
        return new DemandCSVResponse(preEnrollments);
    }

    public PossibleGraduateResponse getPossibleGraduate(String token, String courseCode, String term)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DEMAND_CSV);
        PreEnrollments preEnrollments = this.preEnrollmentController.getActivesPreEnrollments(courseCode, term);
        return new PossibleGraduateResponse(preEnrollments);
    }

    public MigrationStatusResponse getMigrationStatus(String token, String courseCode, String term) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_MIGRATION_STATUS);
        Collection<MigrationStatus> migrationStatus = this.preEnrollmentController.getMigrationStatus(courseCode, term);
        return new MigrationStatusResponse(migrationStatus);
    }

    public SubjectsDemandResponse getSubjectsDemand(String token, String courseCode, String term) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DEMAND);
        return this.preEnrollmentController.getDemand(courseCode, term);
    }

    public StudentsRetentionStatisticsResponse getStudentsRetentionStatistics(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_RETENTION_STATISTICS);
        return this.retentionStatisticsController.getStudentsRetentionStatistics(courseCode, curriculumCode, from, to);
    }

    public StudentsResponse getStudentsRetentionCSV(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_STUDENTS_RETENTION_CSV);
        return this.retentionStatisticsController.getStudentsRetentionCSV(courseCode, curriculumCode, from, to);
    }

    public SubjectsRetentionStatisticsResponse getSubjectsRetentionStatistics(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_RETENTION_STATISTICS);
        return this.retentionStatisticsController.getSubjectsRetentionStatistics(courseCode, curriculumCode, from, to);
    }

    public SubjectsRetentionResponse getSubjectsRetentionCSV(String token, String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_RETENTION_CSV);
        return this.retentionStatisticsController.getSubjectsRetentionCSV(courseCode, curriculumCode, from, to);
    }

    public RetentionStatisticsSummaryResponse getRetentionStatisticsSummary(String token, String courseCode, String curriculumCode, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_RETENTION_STATISTICS_SUMMARY);
        RetentionStatisticsSummaryResponse response = this.retentionStatisticsController.getRetentionStatisticsSummary(courseCode, curriculumCode, from, to);
        RetentionGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.RETENTION);
        response.setGlossary(glossaryFields);
        return response;
    }

    public SubjectsStatisticsResponse getSubjectsStatistics(String token, String courseCode, String curriculumCode, String from, String to, SubjectType type) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_STATISTICS);
        return this.subjectsStatisticsController.getSubjectsStatistics(courseCode, curriculumCode, from, to, type);
    }

    public SubjectsResponse getSubjectsCSV(String token, String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_CSV);
        return this.subjectsStatisticsController.getSubjectsCSV(courseCode, curriculumCode, from, to, subjectType);
    }

    public SubjectsStatisticsSummaryResponse getSubjectsStatisticsSummary(String token, String courseCode, String curriculumCode, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_SUBJECTS_STATISTICS_SUMMARY);
        SubjectsStatisticsSummaryResponse response = this.subjectsStatisticsController.getSubjectsStatisticsSummary(courseCode, curriculumCode, from, to);
        SubjectsGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.SUBJECT);
        response.setGlossary(glossaryFields);
        return response;
    }

    public TeachersStatisticsResponse getTeachersStatistics(String token, String courseCode, String curriculumCode, String from, String to, String academicUnitId) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_TEACHERS_STATISTICS);
        return this.teacherStatisticsController.getTeachersStatistics(courseCode, curriculumCode, from, to, academicUnitId);
    }

    public TeachersResponse getTeachersCSV(String token, String courseCode, String curriculumCode, String from, String to, String academicUnitId) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_TEACHERS_CSV);
        return this.teacherStatisticsController.getTeachersCSV(courseCode, curriculumCode, from, to, academicUnitId);
    }

    public TeachersStatisticsSummaryResponse getTeachersStatisticsSummary(String token, String courseCode, String curriculumCode, String from, String to, String language)
            throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_TEACHERS_STATISTICS_SUMMARY);
        TeachersStatisticsSummaryResponse response = this.teacherStatisticsController.getTeachersStatisticsSummary(courseCode, curriculumCode, from, to);
        TeachersGlossaryFields glossaryFields = GlossaryFactory.createGlossary(language, GlossaryType.TEACHER);
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

    public VersionResponse getVersion() {
        String buildNumber = BuildNumberHolder.getInstance().getBuildNumber();
        String versionNumber = SystemConstants.API_VERSION_NUMBER + "-" + buildNumber;
        return new VersionResponse(versionNumber);
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
