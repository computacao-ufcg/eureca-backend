package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsSummaryItemResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.PortugueseStudentsGlossary;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.Enrollment;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EnrollmentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public EnrollmentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public EnrollmentsSummaryResponse getEnrollmentsStatisticsMock() {
        TermCount max = new TermCount(80,"x");
        TermCount min = new TermCount(60,"y");
        EnrollmentsSummaryResponse summary = new EnrollmentsSummaryResponse("2017", "1980.1", "2020.1",120, max, min,
                2, 2, 90, 80);
        return summary;
    }

    public EnrollmentsSummaryResponse getEnrollmentsStatistics(String from, String to) {
        String curriculum = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();

        int subjects;
        TermCount max;
        TermCount min ;
        double averageClassesPerDiscipline;
        double averageClassesPerPeriod;
        double averageEnrollmentsPerClass;
        double averageEnrollmentsPerPeriod;

        Collection<Enrollment> enrollments = this.dataAccessFacade.getEnrollments(from, to, course, curriculum);

        for (Enrollment enrollment : enrollments) {

        }

        return null;
    }

    public Collection<EnrollmentsSummaryItemResponse> getEnrollmentsStatisticsCSV() {
        List<EnrollmentsSummaryItemResponse> response = new ArrayList<>();
        response.add(new EnrollmentsSummaryItemResponse("eda", 120, 120, 1, "2018.1", "2020.1", new PortugueseStudentsGlossary()));
        response.add(new EnrollmentsSummaryItemResponse("leda", 120, 60, 2, "2018.1", "2020.1", new PortugueseStudentsGlossary()));
        response.add(new EnrollmentsSummaryItemResponse("sistemas operacionais", 100, 33.3, 3, "2013.1", "2019.2", new PortugueseStudentsGlossary()));
        response.add(new EnrollmentsSummaryItemResponse("redes", 80, 40, 2, "2018.1", "2020.1", new PortugueseStudentsGlossary()));
        response.add(new EnrollmentsSummaryItemResponse("logica", 110, 65, 2, "2018.1", "2020.1", new PortugueseStudentsGlossary()));
        return response;
    }
}
