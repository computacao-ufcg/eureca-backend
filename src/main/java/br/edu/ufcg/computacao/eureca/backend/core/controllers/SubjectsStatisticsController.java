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

    public SubjectsStatisticsResponse getSubjectsSummary(String from, String to, SubjectType subjectType) throws InvalidParameterException {
        try {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Collection<SubjectMetricsPerTermSummary> metricsPerTerm =
                this.dataAccessFacade.getSubjectMetricsPerTermSummary(from, to, courseCode, curriculumCode, subjectType);
        String firstTerm = "9999.9";
        String lastTerm = "0000.0";
        for (SubjectMetricsPerTermSummary metricsSummary : metricsPerTerm) {
            if (metricsSummary != null && metricsSummary.getTerms() != null) {
                String first = CollectionUtil.getFirstTermFromSummaries(metricsSummary.getTerms());
                if (first.compareTo(firstTerm) < 0) firstTerm = first;
                String last = CollectionUtil.getLastTermFromSummaries(metricsSummary.getTerms());
                if (last.compareTo(lastTerm) > 0) lastTerm = last;
            }
        }
        SubjectsStatisticsResponse response = new SubjectsStatisticsResponse(metricsPerTerm, firstTerm, lastTerm);
        return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public SubjectResponse getSubjectsCSV(String from, String to, SubjectType subjectType) throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        Collection<SubjectCSV> subjectDataResponses = new TreeSet<>();
        Collection<SubjectMetricsPerTermSummary> metricsPerTerm =
                this.dataAccessFacade.getSubjectMetricsPerTermSummary(from, to, courseCode, curriculumCode, subjectType);
        metricsPerTerm.forEach(subject -> {
            subject.getTerms().forEach(termSummary -> {
                SubjectCSV subjectData = new SubjectCSV(courseCode, curriculumCode, subject.getCode(),
                        termSummary.getTerm(), termSummary.getMetrics());
                subjectDataResponses.add(subjectData);
            });
        });
        return new SubjectResponse(subjectDataResponses);
    }

    public SubjectsStatisticsSummaryResponse getSubjectStatistics(String from, String to) throws InvalidParameterException {
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String code = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        SubjectsStatisticsSummaryResponse summary = this.dataAccessFacade.getSubjectStatisticsSummary(from, to, course, code);
        return summary;
    }
}
