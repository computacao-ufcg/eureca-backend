package br.edu.ufcg.computacao.eureca.backend.core.dao;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

public interface DataAccessFacade {
    Collection<Student> getActives(String from, String to);

    Collection<Student> getAlumni(String from, String to);

    Collection<Student> getDropouts(String from, String to);

    Map<String, Collection<Student>> getActivesPerAdmissionTerm(String from, String to);

    Map<String, Collection<Student>> getAlumniPerGraduationTerm(String from, String to);

    Map<String, Collection<Student>> getDropoutsPerDropoutTerm(String from, String to);

    Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to);

    Curriculum getCurriculum(String courseCode, String curriculumCode);

    TreeSet<String> getTermsForCurriculum(String courseCode, String curriculumCode);

    Collection<String> getCurriculumCodes(String courseCode);

    Subject getSubject(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getSucceededStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getExemptedStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getOngoingStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getFailedDueToGradeStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getFailedDueToAbsencesStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getSuspendedStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getCancelledStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    Collection<SubjectRetentionResponse> getSubjectsRetention(String courseCode, String curriculumCode) throws InvalidParameterException;

    Collection<SubjectRetentionSummaryResponse> getSubjectsRetentionSummary(String courseCode, String curriculumCode) throws InvalidParameterException;

    int getNumberOfClassesPerSubject(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerTermPerSubject(String from, String to, String courseCode, String curriculumCode);

    Collection<SubjectMetricsPerTerm> getSubjectMetricsPerTerm(String from, String to, String courseCode, String curriculumCode, String subjectCode);
}
