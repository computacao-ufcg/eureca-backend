package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SubjectsStatisticsController {
    private Logger LOGGER = Logger.getLogger(SubjectsStatisticsController.class);

    private final DataAccessFacade dataAccessFacade;

    public SubjectsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public SubjectsSummaryResponse getSubjectStatistics(String from, String to) throws InvalidParameterException {
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String code = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Curriculum curriculum = this.dataAccessFacade.getCurriculum(course, code);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, code, course));
        }
        SubjectsSummaryResponse summary = getSubjectStatisticsSummary(from, to, course, code, curriculum);
        return summary;
    }

    public SubjectMetricsSummaryResponse getSubjectsSummary(String from, String to, SubjectType subjectType) throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Collection<String> subjects = getSubjectCodes(courseCode, curriculumCode, subjectType);
        Collection<SubjectMetricsSummary> subjectMetricsPerTerms = new TreeSet<>();
        for (String subjectCode : subjects) {
            Collection<SubjectMetricsPerTerm> metricsPerTerm = this.dataAccessFacade.getSubjectMetricsPerTerm(from, to,
                    courseCode, curriculumCode, subjectCode);
            Subject subjectData = this.dataAccessFacade.getSubject(courseCode, curriculumCode, subjectCode);
            SubjectMetricsSummary subjectMetricsPerTerm = new SubjectMetricsSummary(subjectCode, subjectData.getName(),
                    metricsPerTerm);
            subjectMetricsPerTerms.add(subjectMetricsPerTerm);
        }
        SubjectMetricsSummaryResponse response = new SubjectMetricsSummaryResponse(subjectMetricsPerTerms, from, to);
        return response;
    }

    private <T> Collection<String> getSliderLabel(Collection<T> terms, Function<T, String> function) {
        return terms
                .stream()
                .map(function)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private SubjectsSummaryResponse getSubjectStatisticsSummary(String from, String to, String courseCode,
                                                                String curriculumCode, Curriculum curriculum) {
        SubjectsStatisticsSummary mandatory = buildSummary(from, to, courseCode, curriculumCode,
                curriculum.getMandatorySubjectsList());
        SubjectsStatisticsSummary optional = buildSummary(from, to, courseCode, curriculumCode,
                curriculum.getOptionalSubjectsList());
        SubjectsStatisticsSummary elective = buildSummary(from, to, courseCode, curriculumCode,
                curriculum.getElectiveSubjectsList());
        SubjectsStatisticsSummary complementary = buildSummary(from, to, courseCode, curriculumCode,
                curriculum.getComplementarySubjectsList());
        TreeSet<String> terms = this.dataAccessFacade.getTermsForCurriculum(courseCode, curriculumCode);
        String first = terms.first();
        String last = terms.last();
        from = (from.compareTo(first) < 0 ? first : from);
        to = (to.compareTo(last) < 0 ? to : last);
        SubjectsSummaryResponse ret = new SubjectsSummaryResponse(courseCode, curriculumCode, from, to, mandatory,
                optional, elective, complementary);
        return ret;
    }

    private SubjectsStatisticsSummary buildSummary(String from, String to, String courseCode, String curriculumCode,
                                                   Collection<String> subjects) {
        int minFailedDueToAbsences = Integer.MAX_VALUE;
        int maxFailedDueToAbsences = 0;
        int totalFailedDueToAbsences = 0;
        int minFailedDueToGrade = Integer.MAX_VALUE;
        int maxFailedDueToGrade = 0;
        int totalFailedDueToGrade = 0;
        int minSuspended = Integer.MAX_VALUE;
        int maxSuspended = 0;
        int totalSuspended = 0;
        int minSucceeded = Integer.MAX_VALUE;
        int maxSucceeded = 0;
        int totalSucceeded = 0;
        int minExempted = Integer.MAX_VALUE;
        int maxExempted = 0;
        int totalExempted = 0;
        int totalNumberOfClasses = 0;
        int totalEnrollments = 0;

        for(String subjectCode : subjects) {
            totalNumberOfClasses += this.dataAccessFacade.getNumberOfClassesPerSubject(from, to, courseCode, curriculumCode, subjectCode);
            Subject subject = this.dataAccessFacade.getSubject(courseCode, curriculumCode, subjectCode);
            MetricStatistics failedDueToAbsences = this.dataAccessFacade.getFailedDueToAbsencesStatistics(from, to, courseCode, curriculumCode, subjectCode);
            if (minFailedDueToAbsences > failedDueToAbsences.getMin()) minFailedDueToAbsences = failedDueToAbsences.getMin();
            if (maxFailedDueToAbsences < failedDueToAbsences.getMax()) maxFailedDueToAbsences = failedDueToAbsences.getMax();
            totalFailedDueToAbsences += failedDueToAbsences.getTotal();
            MetricStatistics failedDueToGrade = this.dataAccessFacade.getFailedDueToGradeStatistics(from, to, courseCode, curriculumCode, subjectCode);
            if (minFailedDueToGrade > failedDueToGrade.getMin()) minFailedDueToGrade = failedDueToGrade.getMin();
            if (maxFailedDueToGrade < failedDueToGrade.getMax()) maxFailedDueToGrade = failedDueToGrade.getMax();
            totalFailedDueToGrade += failedDueToGrade.getTotal();
            MetricStatistics suspended = this.dataAccessFacade.getSuspendedStatistics(from, to, courseCode, curriculumCode, subjectCode);
            if (minSuspended > suspended.getMin()) minSuspended = suspended.getMin();
            if (maxSuspended < suspended.getMax()) maxSuspended = suspended.getMax();
            totalSuspended += suspended.getTotal();
            MetricStatistics succeeded = this.dataAccessFacade.getSucceededStatistics(from, to, courseCode, curriculumCode, subjectCode);
            if (minSucceeded > succeeded.getMin()) minSucceeded = succeeded.getMin();
            if (maxSucceeded < succeeded.getMax()) maxSucceeded = succeeded.getMax();
            totalSucceeded += succeeded.getTotal();
            totalEnrollments += (totalFailedDueToAbsences + totalFailedDueToGrade + totalSuspended + totalSucceeded);
            MetricStatistics exempted = this.dataAccessFacade.getExemptedStatistics(from, to, courseCode, curriculumCode, subjectCode);
            if (minExempted > exempted.getMin()) minExempted = exempted.getMin();
            if (maxExempted < exempted.getMax()) maxExempted = exempted.getMax();
            totalExempted += exempted.getTotal();
        }
        MetricSummary failedDueToAbsencesSummary = new MetricSummary(minFailedDueToAbsences, maxFailedDueToAbsences,
                (1.0*totalFailedDueToAbsences)/totalNumberOfClasses);
        MetricSummary failedDueToGradeSummary = new MetricSummary(minFailedDueToGrade, maxFailedDueToGrade,
                (1.0*totalFailedDueToGrade)/totalNumberOfClasses);
        MetricSummary suspendedSummary = new MetricSummary(minSuspended, maxSuspended,
                (1.0*totalSuspended)/totalNumberOfClasses);
        MetricSummary succeededSummary = new MetricSummary(minSucceeded, maxSucceeded,
                (1.0*totalSucceeded)/totalNumberOfClasses);
        MetricSummary exemptedSummary = new MetricSummary(minExempted, maxExempted,
                (1.0*totalExempted)/totalNumberOfClasses);
        SubjectsStatisticsSummary ret = new SubjectsStatisticsSummary(subjects.size(), totalEnrollments,
                failedDueToAbsencesSummary, failedDueToGradeSummary, suspendedSummary, succeededSummary,
                exemptedSummary);
        return ret;
    }

    public Collection<SubjectDataResponse> getSubjectsCSV(String from, String to, SubjectType subjectType) throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Collection<String> subjects = getSubjectCodes(courseCode, curriculumCode, subjectType);
        Collection<SubjectDataResponse> subjectDataResponses = new TreeSet<>();
        for (String subjectCode : subjects) {
            Collection<SubjectMetricsPerTerm> metricsPerTerm = this.dataAccessFacade.getSubjectMetricsPerTerm(from, to,
                    courseCode, curriculumCode, subjectCode);
            metricsPerTerm.forEach(item -> {
                SubjectDataResponse subjectData = new SubjectDataResponse(courseCode, curriculumCode, subjectCode,
                        item.getTerm(), item.getMetrics());
                subjectDataResponses.add(subjectData);
            });
        }
        return subjectDataResponses;
    }

    private Collection<String> getSubjectCodes(String courseCode, String curriculumCode, SubjectType subjectType) throws InvalidParameterException {
        Curriculum curriculum = this.dataAccessFacade.getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, curriculumCode, courseCode));
        }        Collection<String> subjects = null;
        switch (subjectType) {
            case MANDATORY:
                subjects = curriculum.getMandatorySubjectsList();
                break;
            case OPTIONAL:
                subjects = curriculum.getOptionalSubjectsList();
                break;
            case ELECTIVE:
                subjects = curriculum.getElectiveSubjectsList();
                break;
            case COMPLEMENTARY:
                subjects = curriculum.getComplementarySubjectsList();
                break;
        }
        return subjects;
    }
}
