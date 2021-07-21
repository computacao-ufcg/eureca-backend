package br.edu.ufcg.computacao.eureca.backend.core.dao;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EnrollmentData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

public interface DataAccessFacade {
    Collection<Student> getActives(String from, String to);

    Collection<Student> getAlumni(String from, String to);

    Collection<Student> getDropouts(String from, String to);

    Collection<Student> getDelayed(String from, String to);

    Collection<Enrollment> getEnrollments(String from, String to, String courseCode, String curriculumCode);

    Collection<ActivesPerTermSummary> getActivesPerTermSummary(String from, String to);

    Collection<AlumniPerTermSummary> getAlumniPerTermSummary(String from, String to);

    Collection<DropoutPerTermSummary> getDropoutsPerTermSummary(String from, String to);

    Collection<DelayedPerTermSummary> getDelayedPerTermSummary(String from, String to);

    Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to);

    ActivesSummary getActivesSummary(String from, String to);

    ActivesSummaryResponse getActivesSummaryResponse(String from, String to);

    AlumniSummary getAlumniSummary(String from, String to);

    AlumniSummaryResponse getAlumniSummaryResponse(String from, String to);

    DelayedSummary getDelayedSummary(String from, String to);

    DelayedSummaryResponse getDelayedSummaryResponse(String from, String to);

    DropoutsSummary getDropoutsSummary(String from, String to);

    DropoutsSummaryResponse getDropoutsSummaryResponse(String from, String to);

    Student getStudent(String registration);

    Curriculum getCurriculum(String courseCode, String curriculumCode);

    Subject getSubject(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getSucceededStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getExemptedStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getOngoingStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getFailedDueToGradeStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getFailedDueToAbsencesStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getSuspendedStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getCancelledStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    int getRetentionCount(String courseCode, String curriculumCode, String subjectCode);

    Collection<SubjectsRetentionResponse> getRetention(String courseCode, String curriculumCode, String subjectCode);

    TreeSet<String> getTermsForCurriculum(String courseCode, String curriculumCode);

    int getNumberOfClassesPerSubject(String from, String to, String courseCode, String curriculumCode, String subjectCode);

    Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerTermPerSubject(String from, String to, String courseCode, String curriculumCode);

    Collection<String> getCurricula(String courseCode);
}
