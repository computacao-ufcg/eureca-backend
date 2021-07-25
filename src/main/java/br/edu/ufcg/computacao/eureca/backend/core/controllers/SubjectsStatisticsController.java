package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.CollectionUtil;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;

public class SubjectsStatisticsController {
    private Logger LOGGER = Logger.getLogger(SubjectsStatisticsController.class);

    private final DataAccessFacade dataAccessFacade;

    public SubjectsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public SubjectSummaryResponse getSubjectsSummary(String from, String to, SubjectType subjectType) throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Collection<SubjectMetricsSummary> metricsPerTerm =
                this.dataAccessFacade.getSubjectMetricsPerTermSummary(from, to, courseCode, curriculumCode, subjectType);
        String firstTerm = "0000.0";
        String lastTerm = "9999.9";
        for (SubjectMetricsSummary metricsSummary : metricsPerTerm) {
            String first = CollectionUtil.getFirstTermFromSummaries(metricsSummary.getTerms());
            if (first.compareTo(firstTerm) < 0) firstTerm = first;
            String last = CollectionUtil.getLastTermFromSummaries(metricsSummary.getTerms());
            if (last.compareTo(lastTerm) > 0) lastTerm = last;
        }
        SubjectSummaryResponse response = new SubjectSummaryResponse(metricsPerTerm, firstTerm, lastTerm);
        return response;
    }

    public Collection<SubjectDataResponse> getSubjectsCSV(String from, String to, SubjectType subjectType) throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Collection<SubjectDataResponse> subjectDataResponses = new TreeSet<>();
        Collection<SubjectMetricsSummary> metricsPerTerm =
                this.dataAccessFacade.getSubjectMetricsPerTermSummary(from, to, courseCode, curriculumCode, subjectType);
        metricsPerTerm.forEach(subject -> {
            subject.getTerms().forEach(termSummary -> {
                SubjectDataResponse subjectData = new SubjectDataResponse(courseCode, curriculumCode, subject.getCode(),
                        termSummary.getTerm(), termSummary.getMetrics());
                subjectDataResponses.add(subjectData);
            });
        });
        return subjectDataResponses;
    }

    public SubjectsSummaryResponse getSubjectStatistics(String from, String to) throws InvalidParameterException {
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String code = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        SubjectsSummaryResponse summary = this.dataAccessFacade.getSubjectStatisticsSummary(from, to, course, code);
        return summary;
    }
}
