package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsRetentionResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsRetentionSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.PortugueseStudentsGlossary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsSummaryItemResponse;
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

    private DataAccessFacade dataAccessFacade;

    public SubjectsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    private <T> Collection<String> getSliderLabel(Collection<T> terms, Function<T, String> function) {
        return terms
                .stream()
                .map(function)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public SubjectSummaryResponse getSubjectStatistics() throws InvalidParameterException {
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String code = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Curriculum curriculum = this.dataAccessFacade.getCurriculum(course, code);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, code, course));
        }
        SubjectSummaryResponse summary = getSubjectStatisticsSummary(course, code, curriculum);
        return summary;
    }

    private SubjectSummaryResponse getSubjectStatisticsSummary(String courseCode, String curriculumCode, Curriculum curriculum) {
        SubjectStatisticsItem mandatory = buildSummary(courseCode, curriculumCode, curriculum.getMandatorySubjectsList());
        SubjectStatisticsItem optional = buildSummary(courseCode, curriculumCode, curriculum.getOptionalSubjectsList());
        SubjectStatisticsItem elective = buildSummary(courseCode, curriculumCode, curriculum.getElectiveSubjectsList());
        SubjectStatisticsItem complementary = buildSummary(courseCode, curriculumCode, curriculum.getComplementarySubjectsList());
        TreeSet<String> terms = this.dataAccessFacade.getTermsForCurriculum(courseCode, curriculumCode);
        String from = terms.first();
        String to = terms.last();
        SubjectSummaryResponse ret = new SubjectSummaryResponse(curriculumCode, from, to, mandatory, optional,
                elective, complementary);
        return ret;
    }

    private SubjectStatisticsItem buildSummary(String courseCode, String curriculumCode, Collection<String> subjects) {
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
        int minRetention = Integer.MAX_VALUE;
        int maxRetention = 0;
        int totalRetention = 0;
        int totalNumberOfClasses = 0;

        for(String subjectCode : subjects) {
            totalNumberOfClasses += this.dataAccessFacade.getNumberOfClassesPerSubject(courseCode, curriculumCode, subjectCode);
            Subject subject = this.dataAccessFacade.getSubject(courseCode, curriculumCode, subjectCode);
            MetricStatistics failedDueToAbsences = this.dataAccessFacade.getFailedDueToAbsencesStatistics(courseCode, curriculumCode, subjectCode);
            if (minFailedDueToAbsences > failedDueToAbsences.getMin()) minFailedDueToAbsences = failedDueToAbsences.getMin();
            if (maxFailedDueToAbsences < failedDueToAbsences.getMax()) maxFailedDueToAbsences = failedDueToAbsences.getMax();
            totalFailedDueToAbsences += failedDueToAbsences.getTotal();
            MetricStatistics failedDueToGrade = this.dataAccessFacade.getFailedDueToGradeStatistics(courseCode, curriculumCode, subjectCode);
            if (minFailedDueToGrade > failedDueToGrade.getMin()) minFailedDueToGrade = failedDueToGrade.getMin();
            if (maxFailedDueToGrade < failedDueToGrade.getMax()) maxFailedDueToGrade = failedDueToGrade.getMax();
            totalFailedDueToGrade += failedDueToGrade.getTotal();
            MetricStatistics suspended = this.dataAccessFacade.getSuspendedStatistics(courseCode, curriculumCode, subjectCode);
            if (minSuspended > suspended.getMin()) minSuspended = suspended.getMin();
            if (maxSuspended < suspended.getMax()) maxSuspended = suspended.getMax();
            totalSuspended += suspended.getTotal();
            MetricStatistics succeeded = this.dataAccessFacade.getSucceededStatistics(courseCode, curriculumCode, subjectCode);
            if (minSucceeded > succeeded.getMin()) minSucceeded = succeeded.getMin();
            if (maxSucceeded < succeeded.getMax()) maxSucceeded = succeeded.getMax();
            totalSucceeded += succeeded.getTotal();
            MetricStatistics exempted = this.dataAccessFacade.getExemptedStatistics(courseCode, curriculumCode, subjectCode);
            if (minExempted > exempted.getMin()) minExempted = exempted.getMin();
            if (maxExempted < exempted.getMax()) maxExempted = exempted.getMax();
            totalExempted += exempted.getTotal();
            int retention = this.dataAccessFacade.getRetentionCount(courseCode, curriculumCode, subjectCode);
            if (minRetention > retention) minRetention = retention;
            if (maxRetention < retention) maxRetention = retention;
            totalRetention += retention;
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
        MetricSummary retentionSummary = new MetricSummary(minRetention, maxRetention,
                (1.0*totalRetention)/totalNumberOfClasses);
        SubjectStatisticsItem ret = new SubjectStatisticsItem(subjects.size(),
                failedDueToAbsencesSummary, failedDueToGradeSummary, suspendedSummary, succeededSummary,
                exemptedSummary, retentionSummary);
        return ret;
    }

    public Collection<SubjectsSummaryItemResponse> getSubjectsStatisticsCSV() {
        List<SubjectsSummaryItemResponse> response = new ArrayList<>();
        response.add(new SubjectsSummaryItemResponse("eda", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseStudentsGlossary()));
        response.add(new SubjectsSummaryItemResponse("leda", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseStudentsGlossary()));
        response.add(new SubjectsSummaryItemResponse("sistemas operacionais", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseStudentsGlossary()));
        response.add(new SubjectsSummaryItemResponse("redes", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseStudentsGlossary()));
        response.add(new SubjectsSummaryItemResponse("logica", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseStudentsGlossary()));
        return response;
    }

    public Collection<SubjectsRetentionSummaryResponse> getSubjectsRetention(String lang) throws InvalidParameterException {
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String code = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Curriculum curriculum = this.dataAccessFacade.getCurriculum(course, code);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, code, course));
        }
        Collection<SubjectsRetentionSummaryResponse> subjectsRetention = new TreeSet<>();
        for (String subjectCode : curriculum.getMandatorySubjectsList()) {
            int retention = this.dataAccessFacade.getRetentionCount(curriculum.getCourse(), curriculum.getCode(), subjectCode);
            Subject subject = this.dataAccessFacade.getSubject(curriculum.getCourse(), curriculum.getCode(), subjectCode);
            SubjectsRetentionSummaryResponse retentionResponse = new
                    SubjectsRetentionSummaryResponse(curriculum.getCourse(), curriculum.getCode(),
                    subjectCode, subject.getName(), retention);
            subjectsRetention.add(retentionResponse);
        }
        return subjectsRetention;
    }

    public Collection<SubjectsRetentionResponse> getSubjectsRetentionCSV(String lang) throws InvalidParameterException {
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String code = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Curriculum curriculum = this.dataAccessFacade.getCurriculum(course, code);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, code, course));
        }
        Collection<SubjectsRetentionResponse> subjectsRetention = new TreeSet<>();
        for (String subjectCode : curriculum.getMandatorySubjectsList()) {
            Collection<SubjectsRetentionResponse> retentionResponses =
                    this.dataAccessFacade.getRetention(curriculum.getCourse(), curriculum.getCode(), subjectCode);
            retentionResponses.forEach(response -> {
                subjectsRetention.add(response);
            });
        }
        return subjectsRetention;
    }
}
