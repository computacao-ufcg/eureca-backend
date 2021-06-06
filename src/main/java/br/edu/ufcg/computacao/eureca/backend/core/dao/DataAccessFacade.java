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

    Curriculum getCurriculum(String course, String curriculumCode);

    Subject getSubject(String subjectCode);

    EnrollmentStatistics getStatisticsSucceeded(String curriculumCode, String subjectCode);

    EnrollmentStatistics getStatisticsExempted(String curriculumCode, String subjectCode);

    EnrollmentStatistics getStatisticsOngoing(String curriculumCode, String subjectCode);

    EnrollmentStatistics getStatisticsFailedDueToGrade(String curriculumCode, String subjectCode);

    EnrollmentStatistics getStatisticsFailedDueToAbsences(String curriculumCode, String subjectCode);

    EnrollmentStatistics getStatisticsSuspended(String curriculumCode, String subjectCode);

    EnrollmentStatistics getStatisticsCancelled(String curriculumCode, String subjectCode);

    TreeSet<String> getTermsForCurriculum(String curriculumCode);

    int getNumberOfClassesPerSubject(String curriculumCode, String subjectCode);
}
