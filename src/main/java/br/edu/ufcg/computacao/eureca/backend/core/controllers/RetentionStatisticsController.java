package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.TreeSet;

public class RetentionStatisticsController {
    private Logger LOGGER = Logger.getLogger(RetentionStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public RetentionStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public DelayedSummaryResponse getDelayedSummary(String from, String to) {
        return this.dataAccessFacade.getDelayedSummaryResponse(from, to);
    }

    public Collection<StudentDataResponse> getDelayedCSV(String from, String to) {
        Collection<StudentDataResponse> delayedData = new TreeSet<>();
        Collection<Student> delayed = this.dataAccessFacade.getDelayed(from, to);
        delayed.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            delayedData.add(studentDataResponse);
        });
        return delayedData;
    }

    public Collection<SubjectRetentionSummaryResponse> getSubjectsRetentionSummary() throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        return this.dataAccessFacade.getSubjectsRetentionSummary(courseCode, curriculumCode);
    }

    public Collection<SubjectRetentionResponse> getSubjectsRetentionCSV() throws InvalidParameterException {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        return this.dataAccessFacade.getSubjectsRetention(courseCode, curriculumCode);
    }

    public RetentionSummaryResponse getRetentionStatistics(String from, String to) {
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        DelayedSummary delayedSummary = this.dataAccessFacade.getDelayedSummary(from, to);
        SubjectRetentionSummary subjectRetentionSummary = this.dataAccessFacade.getSubjectRetentionSummary(courseCode, curriculumCode);
        return new RetentionSummaryResponse(delayedSummary, subjectRetentionSummary);
    }
}