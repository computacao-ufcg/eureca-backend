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

    public StudentsRetentionStatisticsResponse getStudentsRetentionStatistics(String courseCode, String curriculumCode, String from, String to) {
        Collection<StudentsRetentionPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> retentionPerAdmissionTerm = getStudentsRetentionPerAdmissionTerm(courseCode, curriculumCode, from, to);

        for (String term: retentionPerAdmissionTerm.keySet()) {
            StudentMetricsSummary metricsSummary = StudentMetricsCalculator.computeMetricsSummary(retentionPerAdmissionTerm.get(term));
            StudentsRetentionPerTermSummary termData = new StudentsRetentionPerTermSummary(term, metricsSummary);
            terms.add(termData);
        }
        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new StudentsRetentionStatisticsResponse(terms, courseCode, curriculumCode, firstTerm, lastTerm);
    }

    public StudentsResponse getStudentsRetentionCSV(String courseCode, String curriculumCode, String from, String to) {
        Collection<StudentCSV> studentsRetentionData = new TreeSet<>();
        Collection<Student> studentsRetention = getStudentsRetention(courseCode, curriculumCode, from, to);
        studentsRetention.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            studentsRetentionData.add(studentDataResponse);
        });
        return new StudentsResponse(studentsRetentionData);
    }

    public SubjectsRetentionStatisticsResponse getSubjectsRetentionStatistics(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<SubjectRetentionPerAdmissionTermSummary> subjectRetention = this.dataAccessFacade.getSubjectsRetentionSummary(courseCode, curriculumCode, from, to);
        return new SubjectsRetentionStatisticsResponse(subjectRetention, courseCode, curriculumCode, from, to);
    }

    public SubjectsRetentionResponse getSubjectsRetentionCSV(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<SubjectRetentionCSV> retention = this.dataAccessFacade.getSubjectsRetention(courseCode, curriculumCode, from, to);
        return new SubjectsRetentionResponse(retention);
    }

    public RetentionStatisticsSummaryResponse getRetentionStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<Student> studentsRetention = getStudentsRetention(courseCode, curriculumCode, from, to);
        StudentMetricsSummary summary = StudentMetricsCalculator.computeMetricsSummary(studentsRetention);
        StudentsRetentionSummary studentsRetentionSummary = new StudentsRetentionSummary(studentsRetention.size(), summary);

        Collection<SubjectRetentionPerAdmissionTermSummary> subjectsRetentionList = this.dataAccessFacade.getSubjectsRetentionSummary(courseCode, curriculumCode, from, to);
        RetentionSampleList retentionSampleList = getRetentionSample(subjectsRetentionList);
        MetricStatistics adequate = new MetricStatistics(retentionSampleList.getAdequateList());
        MetricStatistics possible = new MetricStatistics(retentionSampleList.getPossibleList());
        SubjectsRetentionSummary subjectRetentionSummary = new SubjectsRetentionSummary(adequate, possible);

        return new RetentionStatisticsSummaryResponse(courseCode, curriculumCode, from, to, studentsRetentionSummary, subjectRetentionSummary);
    }

    private RetentionSampleList getRetentionSample(Collection<SubjectRetentionPerAdmissionTermSummary> subjectsRetentionList) {
        List<Double> adequateSampleList = new ArrayList<>();
        List<Double> possibleSampleList = new ArrayList<>();
        subjectsRetentionList.forEach(subjectSummary -> {
            subjectSummary.getRetention().forEach(termSummary -> {
                adequateSampleList.add((double) termSummary.getAdequate());
                possibleSampleList.add((double) termSummary.getPossible());
            });
        });
        return new RetentionSampleList(adequateSampleList, possibleSampleList);
    }

    private Collection<Student> getStudentsRetention(String courseCode, String curriculumCode, String from, String to) {
         return this.dataAccessFacade.getActives(courseCode, curriculumCode, from, to).stream()
                .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                        item.computeRiskClass().equals(RiskClass.HIGH) ||
                        item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                .collect(Collectors.toSet());
    }

    private Map<String, Collection<Student>> getStudentsRetentionPerAdmissionTerm(String courseCode, String curriculumCode, String from, String to) {
        Map<String, Collection<Student>> studentsRetentionPerAdmissionTerm = new HashMap<>();
        Map<String, Collection<Student>> activesPerAdmissionTerm = this.dataAccessFacade.getActivesPerAdmissionTerm(courseCode, curriculumCode, from, to);
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

    private class RetentionSampleList {
        private List<Double> adequateList;
        private List<Double> possibleList;

        public RetentionSampleList(List<Double> adequateList, List<Double> possibleList) {
            this.adequateList = adequateList;
            this.possibleList = possibleList;
        }

        public List<Double> getAdequateList() {
            return adequateList;
        }

        public List<Double> getPossibleList() {
            return possibleList;
        }
    }
}