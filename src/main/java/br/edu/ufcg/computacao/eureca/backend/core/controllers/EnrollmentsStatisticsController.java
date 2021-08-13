package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
import org.apache.log4j.Logger;

public class EnrollmentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public EnrollmentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public EnrollmentsStatisticsResponse getEnrollments(String courseCode, String curriculumCode, String from, String to, SubjectType type) {
        return null;
    }

    public EnrollmentsResponse getEnrollmentsCSV(String courseCode, String curriculumCode, String from, String to, SubjectType type) {
//        List<EnrollmentsCSV> response = new ArrayList<>();
//        Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerTermPerSubject = this.dataAccessFacade.getEnrollmentsPerTermPerSubject(courseCode, curriculumCode, from, to);
//
//        for (Map.Entry<SubjectKey, Map<String, Map<String, ClassEnrollments>>> entry : enrollmentsPerTermPerSubject.entrySet()) {
//            SubjectKey subjectKey = entry.getKey();
//            // TODO: 28/06/2021  arranjar alguma forma de mapear o codigo da disciplina ao seu nome
//            String discipline = subjectKey.getSubjectCode();
//            int totalClasses = 0;
//            int totalEnrollments = 0;
//            TreeSet<String> terms = new TreeSet<>();
//            Map<String, Map<String, ClassEnrollments>> enrollmentsPerTermPerClass = entry.getValue();
//
//            for (Map.Entry<String, Map<String, ClassEnrollments>> entry2 : enrollmentsPerTermPerClass.entrySet()) {
//                String term = entry2.getKey();
//                terms.add(term);
//
//                Map<String, ClassEnrollments> enrollmentsPerClass = entry2.getValue();
//
//                totalClasses += enrollmentsPerTermPerClass.values().size();
//                for (ClassEnrollments enrollments : enrollmentsPerClass.values()) {
//                    totalEnrollments += enrollments.getNumberOfEnrolleds();
//                }
//            }
//
//            double averageEnrollmentsPerClass = (double) totalEnrollments / totalClasses;
//            from = terms.first();
//            to = terms.last();
//
//            response.add(new EnrollmentsCSV(discipline, totalEnrollments, averageEnrollmentsPerClass, totalClasses, from, to));
//        }
//
//        response.sort(compareTotalEnrollments());
//
//        return new EnrollmentsResponse(response);
        return null;
    }

    public EnrollmentsStatisticsSummaryResponse getEnrollmentsStatistics(String courseCode, String curriculumCode, String from, String to) {
//        int totalClasses = 0;
//        int totalEnrollments = 0;
//        TermCount max = new TermCount(-1, null);
//        TermCount min = new TermCount(Integer.MAX_VALUE, null);
//
//        Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerTermPerSubject = this.dataAccessFacade.getEnrollmentsPerTermPerSubject(courseCode, curriculumCode, from, to);
//        TreeSet<String> terms = new TreeSet<>();
//
//        int subjects = enrollmentsPerTermPerSubject.size();
//
//        for (Map<String, Map<String, ClassEnrollments>> enrollmentsPerTerm : enrollmentsPerTermPerSubject.values()) {
//            for (Map.Entry<String, Map<String, ClassEnrollments>> entry : enrollmentsPerTerm.entrySet()) {
//                String term = entry.getKey();
//                terms.add(term);
//                // key: classId
//                Map<String, ClassEnrollments> enrollmentsPerClass = entry.getValue();
//                totalClasses += enrollmentsPerClass.keySet().size();
//
//                int totalEnrollmentsPerClass = 0;
//                for (ClassEnrollments classEnrollments : enrollmentsPerClass.values()) {
//                    totalEnrollmentsPerClass += classEnrollments.getNumberOfEnrolleds();
//                }
//
//                if (totalEnrollmentsPerClass > max.getCount()) {
//                    max.setCount(totalEnrollmentsPerClass);
//                    max.setTerm(term);
//                }
//                if (totalEnrollmentsPerClass < min.getCount()) {
//                    min.setCount(totalEnrollmentsPerClass);
//                    min.setTerm(term);
//                }
//
//                totalEnrollments += totalEnrollmentsPerClass;
//            }
//        }
//
//        int totalTerms = terms.size();
//
//        double avgEnrollmentsPerClass = (double) totalEnrollments / totalClasses;
//        double avgEnrollmentsPerPeriod = (double) totalEnrollments / totalTerms;
//        double avgClassesPerPeriod = (double) totalClasses / totalTerms;
//        double avgClassesPerSubject = (double) totalClasses / subjects;
//        from = terms.first();
//        to = terms.last();
//
//        return new EnrollmentsStatisticsSummaryResponse(curriculumCode, from, to, subjects, max, min, avgClassesPerSubject, avgClassesPerPeriod, avgEnrollmentsPerClass, avgEnrollmentsPerPeriod);
        return null;
    }
}
