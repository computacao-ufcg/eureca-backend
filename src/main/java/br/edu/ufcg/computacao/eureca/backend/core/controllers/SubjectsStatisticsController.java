package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;

public class SubjectsStatisticsController {
    private Logger LOGGER = Logger.getLogger(SubjectsStatisticsController.class);

    private final DataAccessFacade dataAccessFacade;

    public SubjectsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public SubjectsStatisticsResponse getSubjectsStatistics(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException {
        Collection<SubjectMetricsPerTermSummary> metricsPerTerm =
                this.dataAccessFacade.getSubjectMetricsPerTermSummary(courseCode, curriculumCode, from, to, subjectType);
        SubjectsStatisticsResponse response = new SubjectsStatisticsResponse(metricsPerTerm, courseCode, curriculumCode);
        return response;
    }

    public SubjectsResponse getSubjectsCSV(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException {
        Collection<SubjectCSV> subjectDataResponses = new TreeSet<>();
        Collection<SubjectMetricsPerTermSummary> metricsPerTerm =
                this.dataAccessFacade.getSubjectMetricsPerTermSummary(courseCode, curriculumCode, from, to, subjectType);
        metricsPerTerm.forEach(subject -> {
            subject.getTerms().forEach(termSummary -> {
                SubjectCSV subjectData = new SubjectCSV(courseCode, curriculumCode, subject.getSubjectCode(),
                        subject.getSubjectName(), termSummary.getTerm(), termSummary.getMetrics());
                subjectDataResponses.add(subjectData);
            });
        });
        return new SubjectsResponse(subjectDataResponses);
    }

    public SubjectsStatisticsSummaryResponse getSubjectsStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        SubjectsStatisticsSummaryResponse summary = this.dataAccessFacade.getSubjectStatisticsSummary(courseCode, curriculumCode, from, to);
        return summary;
    }
}
