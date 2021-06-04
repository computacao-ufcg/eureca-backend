package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.constants.PortugueseStudentsGlossary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsSummaryItemResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
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

    public SubjectSummaryResponse getSubjectStatistics() {
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String code = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        CurriculumParsedData curriculum = new CurriculumParsedData(this.dataAccessFacade.getCurriculum(course, code));
        SubjectSummaryResponse summary = getSubjectStatisticsSummary(code, curriculum);
        return summary;
    }

    private SubjectSummaryResponse getSubjectStatisticsSummary(String curriculumCode, CurriculumParsedData curriculum) {
        SubjectStatisticsItem mandatory = buildSummary(curriculumCode, curriculum.getMandatorySubjects());
        SubjectStatisticsItem optional = buildSummary(curriculumCode, curriculum.getOptionalSubjects());
        SubjectStatisticsItem elective = buildSummary(curriculumCode, curriculum.getElectiveSubjects());
        SubjectStatisticsItem complementary = buildSummary(curriculumCode, curriculum.getComplementarySubjects());
        TreeSet<String> terms = this.dataAccessFacade.getTerms(curriculumCode);
        String from = terms.first();
        String to = terms.last();
        SubjectSummaryResponse ret = new SubjectSummaryResponse(curriculumCode, from, to, mandatory, optional, elective, complementary);
        return ret;
    }

    private SubjectStatisticsItem buildSummary(String curriculumCode, String[] subjects) {
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

        for (int i = 0; i < subjects.length; i++) {
            SubjectParsedData subject = new SubjectParsedData(subjects[i], this.dataAccessFacade.getSubject(subjects[i]));
            EnrollmentStatistics failedDueToAbsences = this.dataAccessFacade.getStatisticsFailedDueToAbsences(curriculumCode, subject.getCode());
            if (minFailedDueToAbsences > failedDueToAbsences.getMin()) minFailedDueToAbsences = failedDueToAbsences.getMin();
            if (maxFailedDueToAbsences < failedDueToAbsences.getMax()) maxFailedDueToAbsences = failedDueToAbsences.getMax();
            totalFailedDueToAbsences += failedDueToAbsences.getTotal();
            EnrollmentStatistics failedDueToGrade = this.dataAccessFacade.getStatisticsFailedDueToGrade(curriculumCode, subject.getCode());
            if (minFailedDueToGrade > failedDueToGrade.getMin()) minFailedDueToGrade = failedDueToGrade.getMin();
            if (maxFailedDueToGrade < failedDueToGrade.getMax()) maxFailedDueToGrade = failedDueToGrade.getMax();
            totalFailedDueToGrade += failedDueToGrade.getTotal();
            EnrollmentStatistics suspended = this.dataAccessFacade.getStatisticsSuspended(curriculumCode, subject.getCode());
            if (minSuspended > suspended.getMin()) minSuspended = suspended.getMin();
            if (maxSuspended < suspended.getMax()) maxSuspended = suspended.getMax();
            totalSuspended += suspended.getTotal();
            EnrollmentStatistics succeeded = this.dataAccessFacade.getStatisticsSucceeded(curriculumCode, subject.getCode());
            if (minSucceeded > succeeded.getMin()) minSucceeded = succeeded.getMin();
            if (maxSucceeded < succeeded.getMax()) maxSucceeded = succeeded.getMax();
            totalSucceeded += succeeded.getTotal();
        }
        MetricSummary failedDueToAbsencesSummary = new MetricSummary(minFailedDueToAbsences, maxFailedDueToAbsences,
                (1.0*totalFailedDueToAbsences)/subjects.length);
        MetricSummary failedDueToGradeSummary = new MetricSummary(minFailedDueToGrade, maxFailedDueToGrade,
                (1.0*totalFailedDueToGrade)/subjects.length);
        MetricSummary suspendedSummary = new MetricSummary(minSuspended, maxSuspended,
                (1.0*totalSuspended)/subjects.length);
        MetricSummary succeededSummary = new MetricSummary(minSucceeded, maxSucceeded,
                (1.0*totalSucceeded)/subjects.length);
        MetricSummary relativeRetentionSummary = new MetricSummary(0, 0, 0.0);
        MetricSummary absoluteRetentionSummary = new MetricSummary(0, 0, 0.0);
        SubjectStatisticsItem ret = new SubjectStatisticsItem(subjects.length, 0, 0,
                failedDueToAbsencesSummary, failedDueToGradeSummary, suspendedSummary, succeededSummary,
                relativeRetentionSummary, absoluteRetentionSummary);
        return ret;
    }

    public SubjectSummaryResponse getSubjectStatisticsMock() {
        MetricSummary metrics = new MetricSummary(20, 30, 25);
        SubjectStatisticsItem mandatory = new SubjectStatisticsItem(1980, 196, 30,metrics,metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem optional = new SubjectStatisticsItem(400, 50, 10, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem elective = new SubjectStatisticsItem(840, 56,15,metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem complementary = new SubjectStatisticsItem(450, 30, 10, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectSummaryResponse summary = new SubjectSummaryResponse("2017", "1980.1", "2020.1", mandatory, optional, elective, complementary);
        return summary;
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
}
