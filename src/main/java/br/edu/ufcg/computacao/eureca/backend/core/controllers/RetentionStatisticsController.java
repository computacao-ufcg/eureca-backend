package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.CollectionUtil;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class RetentionStatisticsController {
    private Logger LOGGER = Logger.getLogger(RetentionStatisticsController.class);

    private final DataAccessFacade dataAccessFacade;

    public RetentionStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public DelayedStatisticsResponse getDelayedSummary(String from, String to) {
        Collection<DelayedPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> delayedPerAdmissionTerm = getDelayedPerAdmissionTerm(from, to);

        for (String term: delayedPerAdmissionTerm.keySet()) {
            StudentMetricsSummary metricsSummary = StudentMetricsCalculator.computeMetricsSummary(delayedPerAdmissionTerm.get(term));
            DelayedPerTermSummary termData = new DelayedPerTermSummary(term, metricsSummary);
            terms.add(termData);
        }
        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new DelayedStatisticsResponse(terms, firstTerm, lastTerm);
    }

    public StudentResponse getDelayedCSV(String from, String to) {
        Collection<StudentCSV> delayedData = new TreeSet<>();
        Collection<Student> delayed = getDelayed(from, to);
        delayed.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            delayedData.add(studentDataResponse);
        });
        return new StudentResponse(delayedData);
    }

    public SubjectRetentionStatisticsResponse getSubjectRetentionSummary() throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Collection<SubjectRetentionDigest> digest = this.dataAccessFacade.getSubjectsRetentionSummary(courseCode, curriculumCode);
        return new SubjectRetentionStatisticsResponse(digest);
    }

    public SubjectRetentionResponse getSubjectsRetentionCSV() throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Collection<SubjectRetentionCSV> retention = this.dataAccessFacade.getSubjectsRetention(courseCode, curriculumCode);
        return new SubjectRetentionResponse(retention);
    }

    public RetentionStatisticsSummaryResponse getRetentionStatistics(String from, String to) throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();

        Collection<Student> delayed = getDelayed(from, to);
        StudentMetricsSummary summary = StudentMetricsCalculator.computeMetricsSummary(delayed);
        String firstTerm = CollectionUtil.getFirstTermFromStudents(delayed);
        String lastTerm = CollectionUtil.getLastTermFromStudents(delayed);
        DelayedSummary delayedSummary = new DelayedSummary(delayed.size(), summary, firstTerm, lastTerm);

        Collection<SubjectRetentionDigest> subjectsRetentionList = this.dataAccessFacade.getSubjectsRetentionSummary(courseCode, curriculumCode);
        MetricStatistics retentionStatistics = new MetricStatistics(getRetentionSample(subjectsRetentionList));
        SubjectRetentionSummary subjectRetentionSummary = new SubjectRetentionSummary(retentionStatistics);

        return new RetentionStatisticsSummaryResponse(delayedSummary, subjectRetentionSummary);
    }

    private List<Double> getRetentionSample(Collection<SubjectRetentionDigest> subjectsRetentionList) {
        List<Double> retentionSampleList = new ArrayList<>();
        subjectsRetentionList.forEach(item -> {
            retentionSampleList.add((double) item.getRetention());
        });
        return retentionSampleList;
    }

    private Collection<Student> getDelayed(String from, String to) {
         return this.dataAccessFacade.getActives(from, to).stream()
                .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                        item.computeRiskClass().equals(RiskClass.HIGH) ||
                        item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                .collect(Collectors.toSet());
    }

    private Map<String, Collection<Student>> getDelayedPerAdmissionTerm(String from, String to) {
        Map<String, Collection<Student>> delayedPerAdmissionTerm = new HashMap<>();
        Map<String, Collection<Student>> activesPerAdmissionTerm = this.dataAccessFacade.getActivesPerAdmissionTerm(from, to);
        for (String term: activesPerAdmissionTerm.keySet()) {
            Collection<Student> delayed = activesPerAdmissionTerm.get(term).stream()
                    .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                    item.computeRiskClass().equals(RiskClass.HIGH) ||
                    item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                    .collect(Collectors.toSet());
            delayedPerAdmissionTerm.put(term, delayed);
        }
        return delayedPerAdmissionTerm;
    }
}