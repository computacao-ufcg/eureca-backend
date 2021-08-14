package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
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

    public StudentsRetentionStatisticsResponse getStudentsRetentionStatistics(String courseCode, String from, String to) {
        Collection<StudentsRetentionPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> retentionPerAdmissionTerm = getStudentsRetentionPerAdmissionTerm(courseCode, from, to);

        for (String term: retentionPerAdmissionTerm.keySet()) {
            StudentMetricsSummary metricsSummary = StudentMetricsCalculator.computeMetricsSummary(retentionPerAdmissionTerm.get(term));
            StudentsRetentionPerTermSummary termData = new StudentsRetentionPerTermSummary(term, metricsSummary);
            terms.add(termData);
        }
        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new StudentsRetentionStatisticsResponse(terms, firstTerm, lastTerm);
    }

    public StudentResponse getStudentsRetentionCSV(String courseCode, String from, String to) {
        Collection<StudentCSV> studentsRetentionData = new TreeSet<>();
        Collection<Student> studentsRetention = getStudentsRetention(courseCode, from, to);
        studentsRetention.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            studentsRetentionData.add(studentDataResponse);
        });
        return new StudentResponse(studentsRetentionData);
    }

    public SubjectRetentionStatisticsResponse getSubjectsRetentionStatistics(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionDigest> digest = this.dataAccessFacade.getSubjectsRetentionSummary(courseCode, curriculumCode);
        return new SubjectRetentionStatisticsResponse(digest);
    }

    public SubjectRetentionResponse getSubjectsRetentionCSV(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionCSV> retention = this.dataAccessFacade.getSubjectsRetention(courseCode, curriculumCode);
        return new SubjectRetentionResponse(retention);
    }

    public RetentionStatisticsSummaryResponse getRetentionStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<Student> studentsRetention = getStudentsRetention(courseCode, from, to);
        StudentMetricsSummary summary = StudentMetricsCalculator.computeMetricsSummary(studentsRetention);
        StudentsRetentionSummary studentsRetentionSummary = new StudentsRetentionSummary(from, to, studentsRetention.size(), summary);

        Collection<SubjectRetentionDigest> subjectsRetentionList = this.dataAccessFacade.getSubjectsRetentionSummary(courseCode, curriculumCode);
        MetricStatistics retentionStatistics = new MetricStatistics(getRetentionSample(subjectsRetentionList));
        SubjectsRetentionSummary subjectRetentionSummary = new SubjectsRetentionSummary(retentionStatistics);

        return new RetentionStatisticsSummaryResponse(studentsRetentionSummary, subjectRetentionSummary);
    }

    private List<Double> getRetentionSample(Collection<SubjectRetentionDigest> subjectsRetentionList) {
        List<Double> retentionSampleList = new ArrayList<>();
        subjectsRetentionList.forEach(item -> {
            retentionSampleList.add((double) item.getRetention());
        });
        return retentionSampleList;
    }

    private Collection<Student> getStudentsRetention(String courseCode, String from, String to) {
         return this.dataAccessFacade.getActives(courseCode, from, to).stream()
                .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                        item.computeRiskClass().equals(RiskClass.HIGH) ||
                        item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                .collect(Collectors.toSet());
    }

    private Map<String, Collection<Student>> getStudentsRetentionPerAdmissionTerm(String courseCode, String from, String to) {
        Map<String, Collection<Student>> studentsRetentionPerAdmissionTerm = new HashMap<>();
        Map<String, Collection<Student>> activesPerAdmissionTerm = this.dataAccessFacade.getActivesPerAdmissionTerm(courseCode, from, to);
        for (String term: activesPerAdmissionTerm.keySet()) {
            Collection<Student> studentsRetention = activesPerAdmissionTerm.get(term).stream()
                    .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                    item.computeRiskClass().equals(RiskClass.HIGH) ||
                    item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                    .collect(Collectors.toSet());
            studentsRetentionPerAdmissionTerm.put(term, studentsRetention);
        }
        return studentsRetentionPerAdmissionTerm;
    }
}