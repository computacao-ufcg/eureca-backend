package br.edu.ufcg.computacao.eureca.backend.core.dao;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.PreEnrollmentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniDigest;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsMetricsPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsPerSubjectData;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.StudentPreEnrollmentResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.profile.ProfileResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;

import java.util.Collection;
import java.util.Map;

public interface DataAccessFacade {
    Collection<Student> getActives(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Collection<Student> getAllActives(String courseCode, String curriculumCode) throws InvalidParameterException;

    Collection<Student> getAlumni(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Collection<Student> getDropouts(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Map<String, Collection<Student>> getActivesPerAdmissionTerm(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Map<String, Collection<Student>> getAlumniPerGraduationTerm(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Map<String, Collection<Student>> getDropoutsPerDropoutTerm(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Collection<AlumniDigest> getAlumniPerStudentSummary(String courseCode, String from, String to) throws InvalidParameterException;

    Collection<Subject> getAllSubjects(String courseCode, String curriculumCode) throws InvalidParameterException;

    Curriculum getCurriculum(String courseCode, String curriculumCode) throws InvalidParameterException;

    Collection<String> getCurriculumCodes(String courseCode) throws InvalidParameterException;

    Subject getSubject(String courseCode, String curriculumCode, String subjectCode) throws InvalidParameterException;

    SubjectsStatisticsSummaryResponse getSubjectStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Collection<SubjectMetricsPerTermSummary> getSubjectMetricsPerTermSummary(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException;

    Collection<SubjectRetentionCSV> getSubjectsRetention(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Collection<SubjectRetentionPerAdmissionTermSummary> getSubjectsRetentionSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Collection<EnrollmentsPerSubjectData> getEnrollmentsPerSubjectPerTerm(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException;

    EnrollmentsStatisticsSummaryResponse getEnrollmentsStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    Collection<EnrollmentsMetricsPerTermSummary> getEnrollmentsPerTermSummary(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException;

    ProfileResponse getProfile(String userId) throws InvalidParameterException;

    TeachersStatisticsResponse getTeachersPerTermSummary(String courseCode, String curriculumCode, String from, String to, String academicUnitId) throws InvalidParameterException;

    Map<String, TeachersStatisticsSummary> getTeachersPerAcademicUnit(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException;

    StudentCurriculumProgress getStudentCurriculumProgress(String studentRegistration) throws InvalidParameterException;

    StudentPreEnrollmentResponse getStudentPreEnrollment(PreEnrollmentData preEnrollmentData) throws InvalidParameterException;

    PreEnrollmentsResponse getActivesPreEnrollment(Collection<PreEnrollmentData> activesPreEnrollmentData) throws InvalidParameterException;
}
