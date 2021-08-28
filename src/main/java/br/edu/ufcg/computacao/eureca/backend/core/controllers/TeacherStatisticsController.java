package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;

public class TeacherStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public TeacherStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public TeachersStatisticsResponse getTeachersStatistics(String courseCode, String curriculumCode, String from, String to, String academicUnitId) throws InvalidParameterException {
        return this.dataAccessFacade.getTeachersPerTermSummary(courseCode, curriculumCode, from, to, academicUnitId);
    }

    public TeachersResponse getTeachersCSV(String courseCode, String curriculumCode, String from, String to, String academicUnitId) throws InvalidParameterException {
        TeachersStatisticsResponse teachersSummary = this.dataAccessFacade.getTeachersPerTermSummary(courseCode, curriculumCode, from, to, academicUnitId);
        Collection<TeacherCSV> response = new TreeSet<>();
        teachersSummary.getTeachers().forEach(teacher -> {
            teacher.getTerms().forEach(term -> {
                TeacherStatisticsSummary metrics = term.getTermSummary();
                TeacherCSV teacherCSV = new TeacherCSV(teacher.getTeacherId(), teacher.getTeacherName(), courseCode,
                        curriculumCode, term.getTerm(), metrics);
                response.add(teacherCSV);
            });
        });
        return new TeachersResponse(response);
    }

    public TeachersStatisticsSummaryResponse getTeachersStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Map<String, TeachersStatisticsSummary> teachersSummaryMap = this.dataAccessFacade.getTeachersPerAcademicUnit(courseCode, curriculumCode, from, to);
        TeachersStatisticsSummaryResponse response = new TeachersStatisticsSummaryResponse(courseCode, curriculumCode, teachersSummaryMap);
        return response;
    }
}
