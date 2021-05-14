package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;
import org.apache.log4j.Logger;

public class TeacherStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    public TeacherStatisticsController() {}

    public TeachersSummaryResponse getTeachersStatistics() {
        MetricSummary failedDueToAbsences = new MetricSummary(3, 5,4);
        MetricSummary failedDueToGrade = new MetricSummary(3,5,4);
        MetricSummary failedDueToCanceling = new MetricSummary(3,5,4);
        MetricSummary success = new MetricSummary(3,5,4);
        TermCount min = new TermCount(10,"x");
        TermCount max = new TermCount(10, "y");
        TeachersSummaryResponse summary = new TeachersSummaryResponse(failedDueToAbsences, failedDueToGrade,
                failedDueToCanceling, success, min, max, 20);
        return summary;
    }
}
