package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.ClassEnrollments;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
import br.edu.ufcg.computacao.eureca.backend.core.util.CollectionUtil;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

public class EnrollmentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public EnrollmentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public EnrollmentsStatisticsResponse getSubjectEnrollmentsStatistics(String courseCode, String curriculumCode,
                                String from, String to, SubjectType subjectType) throws InvalidParameterException {
        Collection<EnrollmentsMetricsPerTermSummary> enrollmentsPerTerm =
                this.dataAccessFacade.getEnrollmentsPerTermSummary(courseCode, curriculumCode, from, to, subjectType);
        String firstTerm = "9999.9";
        String lastTerm = "0000.0";
        for (EnrollmentsMetricsPerTermSummary metricsSummary : enrollmentsPerTerm) {
            if (metricsSummary != null && metricsSummary.getTerms() != null) {
                String first = CollectionUtil.getFirstTermFromSummaries(metricsSummary.getTerms());
                if (first.compareTo(firstTerm) < 0) firstTerm = first;
                String last = CollectionUtil.getLastTermFromSummaries(metricsSummary.getTerms());
                if (last.compareTo(lastTerm) > 0) lastTerm = last;
            }
        }
        EnrollmentsStatisticsResponse response = new EnrollmentsStatisticsResponse(enrollmentsPerTerm, courseCode, curriculumCode, firstTerm, lastTerm);
        return response;
    }

    public EnrollmentsResponse getSubjectEnrollmentsCSV(String courseCode, String curriculumCode, String from, String to,
                                                        SubjectType subjectType) throws InvalidParameterException {
        Collection<EnrollmentsCSV> enrollments = new TreeSet<>();
        Collection<EnrollmentsPerSubjectData> enrollmentsPerSubject =
                this.dataAccessFacade.getEnrollmentsPerSubjectPerTerm(courseCode, curriculumCode, from, to, subjectType);
        enrollmentsPerSubject.forEach(subject -> {
            Map<String, Map<String, ClassEnrollments>> enrollmentsPerTerm = subject.getEnrollmentsPerTerm();
            enrollmentsPerTerm.keySet().forEach(term -> {
                Map<String, ClassEnrollments> classes = enrollmentsPerTerm.get(term);
                classes.forEach((classId, classEnrollment) -> {
                    EnrollmentsCSV enrollmentsData = new EnrollmentsCSV(courseCode, curriculumCode,
                            subject.getSubjectCode(), subject.getSubjectName(), term, classId,
                            classEnrollment.getNumberOfEnrolleds(), classEnrollment.getNumberSucceeded(),
                            classEnrollment.getNumberCancelled(), classEnrollment.getNumberExempted(),
                            classEnrollment.getNumberOngoing(), classEnrollment.getNumberFailedDueToGrade(),
                            classEnrollment.getNumberFailedDueToAbsence(), classEnrollment.getNumberSuspended());
                    enrollments.add(enrollmentsData);
                });
            });
        });
        return new EnrollmentsResponse(enrollments);
    }

    public EnrollmentsStatisticsSummaryResponse getSubjectEnrollmentsStatisticsSummary(String courseCode, String curriculumCode,
                                                                                       String from, String to) throws InvalidParameterException {
        EnrollmentsStatisticsSummaryResponse summary =
                this.dataAccessFacade.getEnrollmentsStatisticsSummary(courseCode, curriculumCode, from, to);
        return summary;
    }
}
