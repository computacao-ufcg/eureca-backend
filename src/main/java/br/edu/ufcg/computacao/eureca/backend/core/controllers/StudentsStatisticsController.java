package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.TreeSet;

public class StudentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public StudentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public Collection<StudentDataResponse> getActiveCSV(String from, String to) {
        Collection<StudentDataResponse> activeStudentsData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        actives.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            activeStudentsData.add(studentDataResponse);
        });
        return activeStudentsData;
    }

    public Collection<StudentDataResponse> getAlumniCSV(String from, String to) {
        Collection<StudentDataResponse> alumniData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getAlumni(from, to);
        actives.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            alumniData.add(studentDataResponse);
        });
        return alumniData;
    }

    public Collection<StudentDataResponse> getDropoutsCSV(String from, String to) {
        Collection<StudentDataResponse> dropoutsData = new TreeSet<>();
        Collection<Student> dropouts = this.dataAccessFacade.getDropouts(from, to);
        dropouts.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            dropoutsData.add(studentDataResponse);
        });
        return dropoutsData;
    }

    public Collection<StudentDataResponse> getDelayedCSV(String from, String to) {
        Collection<StudentDataResponse> delayedData = new TreeSet<>();
        Collection<Student> delayed = this.dataAccessFacade.getDelayed(from, to);
        delayed.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            delayedData.add(studentDataResponse);
        });
        return delayedData;
    }

    public ActivesSummaryResponse getActivesSummaryResponse(String from, String to) {
        return this.dataAccessFacade.getActivesSummaryResponse(from, to);
    }

    public AlumniSummaryResponse getAlumniSummaryResponse(String from, String to) {
        return this.dataAccessFacade.getAlumniSummaryResponse(from, to);
    }

    public DropoutsSummaryResponse getDropoutsSummaryResponse(String from, String to) {
        return this.dataAccessFacade.getDropoutsSummaryResponse(from, to);
    }

    public DelayedSummaryResponse getDelayedSummaryResponse(String from, String to) {
        return this.dataAccessFacade.getDelayedSummaryResponse(from, to);
    }

    public StudentsSummaryResponse getStudentsSummaryResponse(String from, String to) {
        ActivesSummary activesSummary = this.dataAccessFacade.getActivesSummary(from, to);
        AlumniSummary alumniSummary = this.dataAccessFacade.getAlumniSummary(from, to);
        DelayedSummary delayedSummary = this.dataAccessFacade.getDelayedSummary(from, to);
        DropoutsSummary dropoutSummary = this.dataAccessFacade.getDropoutsSummary(from, to);

        return new StudentsSummaryResponse(activesSummary, alumniSummary, delayedSummary, dropoutSummary);
    }
}