package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.refactor.CourseData;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.refator.CounterData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.ClassEnrollments;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
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
                                                                         String from, String to, SubjectType subjectType) throws EurecaException {
        Collection<EnrollmentsMetricsPerTermSummary> enrollmentsPerTerm =
                this.dataAccessFacade.getEnrollmentsPerTermSummary(courseCode, curriculumCode, from, to, subjectType);
        return new EnrollmentsStatisticsResponse(enrollmentsPerTerm, courseCode, curriculumCode);
    }

    public EnrollmentsResponse getSubjectEnrollmentsCSV(String courseCode, String curriculumCode, String from, String to,
                                                        SubjectType subjectType) throws EurecaException {
        Collection<EnrollmentsCSV> enrollments = new TreeSet<>();
        Collection<EnrollmentsPerSubjectData> enrollmentsPerSubject =
                this.dataAccessFacade.getEnrollmentsPerSubjectPerTerm(courseCode, curriculumCode, from, to, subjectType);
        enrollmentsPerSubject.forEach(subject -> {
            Map<String, Map<String, ClassEnrollments>> enrollmentsPerTerm = subject.getEnrollmentsPerTerm();
            enrollmentsPerTerm.keySet().forEach(term -> {
                Map<String, ClassEnrollments> classes = enrollmentsPerTerm.get(term);
                classes.keySet().forEach(classId -> {
                    ClassEnrollments classEnrollment = classes.get(classId);
                    CourseData courseData = new CourseData(courseCode, curriculumCode, subject.getSubjectCode(), subject.getSubjectName(), term, classId);
                    CounterData counterData = new CounterData(classEnrollment.getNumberOfEnrolleds(), classEnrollment.getNumberSucceeded(),
                            classEnrollment.getNumberCancelled(), classEnrollment.getNumberExempted(),
                            classEnrollment.getNumberOngoing(), classEnrollment.getNumberFailedDueToGrade(),
                            classEnrollment.getNumberFailedDueToAbsence(), classEnrollment.getNumberSuspended());
                    EnrollmentsCSV enrollmentsData = new EnrollmentsCSV(courseData, counterData);
                    enrollments.add(enrollmentsData);
                });
            });
        });
        return new EnrollmentsResponse(enrollments);
    }

    public EnrollmentsStatisticsSummaryResponse getSubjectEnrollmentsStatisticsSummary(String courseCode, String curriculumCode,
                                                                                       String from, String to) throws EurecaException {
        return this.dataAccessFacade.getEnrollmentsStatisticsSummary(courseCode, curriculumCode, from, to);
    }
}
