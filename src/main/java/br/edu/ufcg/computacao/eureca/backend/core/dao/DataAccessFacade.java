package br.edu.ufcg.computacao.eureca.backend.core.dao;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;

import java.util.Collection;
import java.util.Map;

public interface DataAccessFacade {
    Collection<Student> getActives(String from, String to);

    Collection<Student> getAlumni(String from, String to);

    Collection<Student> getDropouts(String from, String to);

    Map<String, Collection<Student>> getActivesPerAdmissionTerm(String from, String to);

    Map<String, Collection<Student>> getAlumniPerGraduationTerm(String from, String to);

    Map<String, Collection<Student>> getDropoutsPerDropoutTerm(String from, String to);

    Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to);

    Curriculum getCurriculum(String courseCode, String curriculumCode);

    Collection<String> getCurriculumCodes(String courseCode);

    Subject getSubject(String courseCode, String curriculumCode, String subjectCode);

    SubjectsSummaryResponse getSubjectStatisticsSummary(String from, String to, String course, String code) throws InvalidParameterException;

    Collection<SubjectRetentionResponse> getSubjectsRetention(String courseCode, String curriculumCode) throws InvalidParameterException;

    Collection<SubjectRetentionSummaryResponse> getSubjectsRetentionSummary(String courseCode, String curriculumCode) throws InvalidParameterException;

    Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerTermPerSubject(String from, String to, String courseCode, String curriculumCode);

    Collection<SubjectMetricsSummary> getSubjectMetricsPerTermSummary(String from, String to, String courseCode, String curriculumCode, SubjectType subjectType) throws InvalidParameterException;
}
