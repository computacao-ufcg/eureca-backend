package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.RetentionStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.student.StudentsRetentionPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.student.StudentsRetentionStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.student.StudentsRetentionSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentMetricsSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;
import br.edu.ufcg.computacao.eureca.backend.core.models.Range;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
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

    public StudentsRetentionStatisticsResponse getStudentsRetentionStatistics(String courseCode, String curriculumCode,
                                                                              String from, String to) throws InvalidParameterException {
        Collection<StudentsRetentionPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> retentionPerAdmissionTerm =
                getStudentsRetentionPerAdmissionTerm(courseCode, curriculumCode, from, to);

        ArrayList<String> termsList = new ArrayList<>();
        for (String term: retentionPerAdmissionTerm.keySet()) {
            StudentMetricsSummary metricsSummary =
                    StudentMetricsCalculator.computeMetricsSummary(retentionPerAdmissionTerm.get(term));
            StudentsRetentionPerTermSummary termData = new StudentsRetentionPerTermSummary(term, metricsSummary);
            terms.add(termData);
            termsList.add(term);
        }
        Collections.sort(termsList);
        String firstTerm = termsList.get(0);
        String lastTerm = termsList.get(termsList.size() - 1);
        return new StudentsRetentionStatisticsResponse(terms, courseCode, curriculumCode, firstTerm, lastTerm);
    }

    public StudentsResponse getStudentsRetentionCSV(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
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
        return new SubjectsRetentionStatisticsResponse(subjectRetention, courseCode, curriculumCode);
    }

    public SubjectsRetentionResponse getSubjectsRetentionCSV(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<SubjectRetentionCSV> retention = this.dataAccessFacade.getSubjectsRetention(courseCode, curriculumCode, from, to);
        return new SubjectsRetentionResponse(retention);
    }

    public RetentionStatisticsSummaryResponse getRetentionStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<Student> studentsRetention = getStudentsRetention(courseCode, curriculumCode, from, to);
        StudentMetricsSummary summary = StudentMetricsCalculator.computeMetricsSummary(studentsRetention);
        Range limits = getRange(studentsRetention);
        StudentsRetentionSummary studentsRetentionSummary = new StudentsRetentionSummary(limits.getFrom(), limits.getTo(),
                studentsRetention.size(), summary);

        Collection<SubjectRetentionPerAdmissionTermSummary> subjectsRetentionList = this.dataAccessFacade.getSubjectsRetentionSummary(courseCode, curriculumCode, from, to);
        RetentionSampleList retentionSampleList = getRetentionSample(subjectsRetentionList);
        MetricStatistics adequate = new MetricStatistics(retentionSampleList.getAdequateList());
        MetricStatistics possible = new MetricStatistics(retentionSampleList.getPossibleList());
        SubjectsRetentionSummary subjectRetentionSummary = new SubjectsRetentionSummary(retentionSampleList.getFrom(),
                retentionSampleList.getTo(), adequate, possible);

        return new RetentionStatisticsSummaryResponse(courseCode, curriculumCode, studentsRetentionSummary, subjectRetentionSummary);
    }

    private Range getRange(Collection<Student> collection) {
        String first = "9999.9";
        String last = "0000.0";
        for (Student item : collection) {
            String term = item.getAdmissionTerm();
            if (term.compareTo(first) < 0) first = term;
            if (term.compareTo(last) > 0) last = term;
        }
        return new Range(first, last);
    }

    private RetentionSampleList getRetentionSample(Collection<SubjectRetentionPerAdmissionTermSummary> subjectsRetentionList) {
        String from = "9999.9";
        String to = "0000.0";
        List<Double> adequateSampleList = new ArrayList<>();
        List<Double> possibleSampleList = new ArrayList<>();
        for (SubjectRetentionPerAdmissionTermSummary subjectSummary : subjectsRetentionList) {
            if (subjectSummary.getFrom().compareTo(from) < 0) from = subjectSummary.getFrom();
            if (subjectSummary.getTo().compareTo(to) > 0) to = subjectSummary.getTo();
            subjectSummary.getRetention().forEach(termSummary -> {
                adequateSampleList.add((double) termSummary.getAdequate());
                possibleSampleList.add((double) termSummary.getPossible());
            });
        }
        return new RetentionSampleList(from, to, adequateSampleList, possibleSampleList);
    }

    private Collection<Student> getStudentsRetention(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
         return this.dataAccessFacade.getActives(courseCode, curriculumCode, from, to).stream()
                .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                        item.computeRiskClass().equals(RiskClass.HIGH) ||
                        item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                .collect(Collectors.toSet());
    }

    private Map<String, Collection<Student>> getStudentsRetentionPerAdmissionTerm(String courseCode,
                                     String curriculumCode, String from, String to) throws InvalidParameterException {
        Map<String, Collection<Student>> studentsRetentionPerAdmissionTerm = new HashMap<>();
        Map<String, Collection<Student>> activesPerAdmissionTerm = this.dataAccessFacade.getActivesPerAdmissionTerm(courseCode, curriculumCode, from, to);
        for (String term: activesPerAdmissionTerm.keySet()) {
            Collection<Student> studentsRetention = activesPerAdmissionTerm.get(term).stream()
                    .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                    item.computeRiskClass().equals(RiskClass.HIGH) ||
                    item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                    .collect(Collectors.toSet());
            if (studentsRetention.size() > 0) studentsRetentionPerAdmissionTerm.put(term, studentsRetention);
        }
        return studentsRetentionPerAdmissionTerm;
    }

    private class RetentionSampleList {
        private String from;
        private String to;
        private List<Double> adequateList;
        private List<Double> possibleList;

        public RetentionSampleList(String from, String to, List<Double> adequateList, List<Double> possibleList) {
            this.from = from;
            this.to = to;
            this.adequateList = adequateList;
            this.possibleList = possibleList;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public List<Double> getAdequateList() {
            return adequateList;
        }

        public List<Double> getPossibleList() {
            return possibleList;
        }
    }
}