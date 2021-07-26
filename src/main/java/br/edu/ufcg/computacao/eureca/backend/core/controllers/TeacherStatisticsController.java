package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.PortugueseTeachersGlossary;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeacherStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public TeacherStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public TeachersSummaryResponse getTeachersStatisticsMock() {
        MetricStatistics failedDueToAbsences = new MetricStatistics(3, 5, 9, 11, 15, 10, 25);
        MetricStatistics failedDueToGrade = new MetricStatistics(3, 5, 9, 11, 15, 10, 25);
        MetricStatistics failedDueToCanceling = new MetricStatistics(3, 5, 9, 11, 15, 10, 25);
        MetricStatistics success = new MetricStatistics(3, 5, 9, 11, 15, 10, 25);
        TermCount min = new TermCount(10,"x");
        TermCount max = new TermCount(10, "y");
        TeachersSummaryResponse summary = new TeachersSummaryResponse("2017","1980.1","2020.1",failedDueToAbsences, failedDueToGrade,
                failedDueToCanceling, success, min, max, 20);
        return summary;
    }

    public Collection<TeachersSummaryItemResponse> getTeacherCSV() {
        List<TeachersSummaryItemResponse> response = new ArrayList<>();
        response.add(new TeachersSummaryItemResponse("fubica", 0.12, 0.03, 0.01, 0.84, 75, "2010.1", "2012.2", new PortugueseTeachersGlossary()));
        response.add(new TeachersSummaryItemResponse("joao arthur", 0.11, 0.08, 0.01, 0.80, 120, "2010.1", "2012.2", new PortugueseTeachersGlossary()));
        response.add(new TeachersSummaryItemResponse("massoni", 0.09, 0.01, 0.01, 0.89, 80, "2010.1", "2012.2", new PortugueseTeachersGlossary()));
        return response;
    }
}
