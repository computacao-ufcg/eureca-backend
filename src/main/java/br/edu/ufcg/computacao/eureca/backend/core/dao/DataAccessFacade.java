package br.edu.ufcg.computacao.eureca.backend.core.dao;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniDigest;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsMetricsPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsPerSubjectData;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.profile.ProfileResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.StudentClassification;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;

import java.util.Collection;
import java.util.Map;

public interface DataAccessFacade {
    Collection<Student> getAllStudentsPerStatus (StudentClassification status, String courseCode, String curriculumCode) throws EurecaException;

    Collection<Student> getActives(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Collection<Student> getAllActives(String courseCode, String curriculumCode) throws EurecaException;

    Collection<Student> getAlumni(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Collection<Student> getDropouts(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Map<String, Collection<Student>> getActivesPerAdmissionTerm(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Map<String, Collection<Student>> getAlumniPerGraduationTerm(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Map<String, Collection<Student>> getDropoutsPerDropoutTerm(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Collection<AlumniDigest> getAlumniPerStudentSummary(String courseCode, String from, String to) throws EurecaException;

    Collection<Subject> getAllSubjects(String courseCode, String curriculumCode) throws EurecaException;

    Curriculum getCurriculum(String courseCode, String curriculumCode) throws EurecaException;

    Collection<String> getCurriculumCodes(String courseCode) throws EurecaException;

    Subject getSubject(String courseCode, String curriculumCode, String subjectCode) throws EurecaException;

    SubjectsStatisticsSummaryResponse getSubjectStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Collection<SubjectMetricsPerTermSummary> getSubjectMetricsPerTermSummary(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws EurecaException;

    Collection<SubjectRetentionCSV> getSubjectsRetention(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Collection<SubjectRetentionPerAdmissionTermSummary> getSubjectsRetentionSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Collection<EnrollmentsPerSubjectData> getEnrollmentsPerSubjectPerTerm(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws EurecaException;

    EnrollmentsStatisticsSummaryResponse getEnrollmentsStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    Collection<EnrollmentsMetricsPerTermSummary> getEnrollmentsPerTermSummary(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws EurecaException;

    ProfileResponse getProfile(String userId) throws EurecaException;

    TeachersStatisticsResponse getTeachersPerTermSummary(String courseCode, String curriculumCode, String from, String to, String academicUnitId) throws EurecaException;

    Map<String, TeachersStatisticsSummary> getTeachersPerAcademicUnit(String courseCode, String curriculumCode, String from, String to) throws EurecaException;

    StudentCurriculumProgress getStudentCurriculumProgress(String studentRegistration) throws EurecaException;

    Map<SubjectScheduleKey, SubjectSchedule> getAllSchedules(String courseCode, String curriculumCode, String term);

    Map<String, Map<String, Collection<Enrollment>>> getEnrollmentsPerStudentPerTerm(String courseCode, String curriculumCode, String from, String to) throws EurecaException;
}
