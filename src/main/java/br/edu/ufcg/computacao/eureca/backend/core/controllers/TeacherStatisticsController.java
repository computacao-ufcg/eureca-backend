package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import org.apache.log4j.Logger;

public class TeacherStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public TeacherStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public TeachersResponse getTeachersCSV(String courseCode, String curriculumCode, String from, String to) {
        return null;
    }

    public TeachersStatisticsResponse getTeachersStatistics(String courseCode, String curriculumCode, String from, String to) {
        return null;
    }

    public TeachersStatisticsSummaryResponse getTeachersStatisticsSummary(String courseCode, String curriculumCode, String from, String to) {
        return null;
    }
}
