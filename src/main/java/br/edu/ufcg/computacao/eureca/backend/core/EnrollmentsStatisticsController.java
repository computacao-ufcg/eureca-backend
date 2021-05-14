package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;
import org.apache.log4j.Logger;

public class EnrollmentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    public EnrollmentsStatisticsController() {}

    public EnrollmentsSummaryResponse getEnrollmentsStatistics() {
        TermCount max = new TermCount(80,"x");
        TermCount min = new TermCount(60,"y");
        EnrollmentsSummaryResponse summary = new EnrollmentsSummaryResponse(120, max, min,
                2, 2, 90, 80);
        return summary;
    }
}
