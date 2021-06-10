package br.edu.ufcg.computacao.eureca.backend.core.dao;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

import java.util.Collection;
import java.util.TreeSet;

public interface DataAccessFacade {
    Collection<Student> getActives(String from, String to);

    Collection<Student> getAlumni(String from, String to);

    Collection<Student> getDropouts(String from, String to);

    Collection<Student> getDelayed(String from, String to);

    Collection<ActivesPerTermSummary> getActivesPerTermSummary(String from, String to);

    Collection<AlumniPerTermSummary> getAlumniPerTermSummary(String from, String to);

    Collection<DropoutPerTermSummary> getDropoutsPerTermSummary(String from, String to);

    Collection<DelayedPerTermSummary> getDelayedPerTermSummary(String from, String to);

    Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to);

    Student getStudent(String registration);

    Curriculum getCurriculum(String courseCode, String curriculumCode);

    Subject getSubject(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getSucceededStatistics(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getExemptedStatistics(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getOngoingStatistics(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getFailedDueToGradeStatistics(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getFailedDueToAbsencesStatistics(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getSuspendedStatistics(String courseCode, String curriculumCode, String subjectCode);

    MetricStatistics getCancelledStatistics(String courseCode, String curriculumCode, String subjectCode);

    int getRetentionCount(String courseCode, String curriculumCode, String subjectCode);

    Collection<SubjectsRetentionResponse> getRetention(String courseCode, String curriculumCode, String subjectCode);

    TreeSet<String> getTermsForCurriculum(String courseCode, String curriculumCode);

    int getNumberOfClassesPerSubject(String courseCode, String curriculumCode, String subjectCode);
}
