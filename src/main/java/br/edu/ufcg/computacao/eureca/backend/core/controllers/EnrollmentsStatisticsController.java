package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsSummaryItemResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.PortugueseStudentsGlossary;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.ClassEnrollments;
import br.edu.ufcg.computacao.eureca.backend.core.models.Enrollment;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;
import org.apache.log4j.Logger;

import java.util.*;

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

        int totalClasses = 0;
        int totalEnrollments = 0;
        TermCount max = new TermCount(-1, null);
        TermCount min = new TermCount(Integer.MAX_VALUE, null);

        Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerTermPerSubject = this.dataAccessFacade.getEnrollmentsPerTermPerSubject(from, to, course, curriculum);
        TreeSet<String> terms = new TreeSet<>();

        int subjects = enrollmentsPerTermPerSubject.size();

        for (Map<String, Map<String, ClassEnrollments>> enrollmentsPerTerm : enrollmentsPerTermPerSubject.values()) {
            for (Map.Entry<String, Map<String, ClassEnrollments>> entry : enrollmentsPerTerm.entrySet()) {
                String term = entry.getKey();
                terms.add(term);
                // key: classId
                Map<String, ClassEnrollments> enrollmentsPerClass = entry.getValue();
                totalClasses += enrollmentsPerClass.keySet().size();

                int totalEnrollmentsPerClass = 0;
                for (ClassEnrollments classEnrollments : enrollmentsPerClass.values()) {
                    totalEnrollmentsPerClass += classEnrollments.getNumberOfEnrolleds();
                }

                if (totalEnrollmentsPerClass > max.getCount()) {
                    max.setCount(totalEnrollmentsPerClass);
                    max.setTerm(term);
                }
                if (totalEnrollmentsPerClass < min.getCount()) {
                    min.setCount(totalEnrollmentsPerClass);
                    min.setTerm(term);
                }

                totalEnrollments += totalEnrollmentsPerClass;
            }
        }

        int totalTerms = terms.size();

        double avgEnrollmentsPerClass = (double) totalEnrollments / totalClasses;
        double avgEnrollmentsPerPeriod = (double) totalEnrollments / totalTerms;
        double avgClassesPerPeriod = (double) totalClasses / totalTerms;
        double avgClassesPerSubject = (double) totalClasses / subjects;
        from = terms.first();
        to = terms.last();

        return new EnrollmentsSummaryResponse(curriculum, from, to, subjects, max, min, avgClassesPerSubject, avgClassesPerPeriod, avgEnrollmentsPerClass, avgEnrollmentsPerPeriod);
    }

    public Collection<EnrollmentsSummaryItemResponse> getEnrollmentsStatisticsCSV(String from, String to) {
        String course = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculum = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();

        Collection<Enrollment> enrollments = this.dataAccessFacade.getEnrollments(from, to, course, curriculum);

        List<EnrollmentsSummaryItemResponse> response = new ArrayList<>();
        response.add(new EnrollmentsSummaryItemResponse("eda", 120, 120, 1, "2018.1", "2020.1", new PortugueseStudentsGlossary()));
        response.add(new EnrollmentsSummaryItemResponse("leda", 120, 60, 2, "2018.1", "2020.1", new PortugueseStudentsGlossary()));
        response.add(new EnrollmentsSummaryItemResponse("sistemas operacionais", 100, 33.3, 3, "2013.1", "2019.2", new PortugueseStudentsGlossary()));
        response.add(new EnrollmentsSummaryItemResponse("redes", 80, 40, 2, "2018.1", "2020.1", new PortugueseStudentsGlossary()));
        response.add(new EnrollmentsSummaryItemResponse("logica", 110, 65, 2, "2018.1", "2020.1", new PortugueseStudentsGlossary()));
        return response;
    }
}
