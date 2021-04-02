package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.AlumniDigestResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.StudentDataResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.TreeSet;

public class StudentsDataFetcher {
    private Logger LOGGER = Logger.getLogger(StudentsDataFetcher.class);

    private DataAccessFacade dataAccessFacade;

    public StudentsDataFetcher() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public Collection<StudentDataResponse> getActiveCSV(String from, String to) {
        Collection<StudentDataResponse> activeStudentsData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        actives.forEach(item -> {
            Metrics metrics = MetricsCalculator.getInstance().computeMetrics(item);
            StudentDataResponse studentDataResponse = new StudentDataResponse(item.getId().getRegistration(),
                    item.getStudentData(), metrics);
            activeStudentsData.add(studentDataResponse);
        });
        return activeStudentsData;
    }

    public Collection<StudentDataResponse> getAlumniCSV(String from, String to) {
        Collection<StudentDataResponse> alumniData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getAlumni(from, to);
        actives.forEach(item -> {
            Metrics metrics = MetricsCalculator.getInstance().computeMetrics(item);
            StudentDataResponse studentDataResponse = new StudentDataResponse(item.getId().getRegistration(),
                    item.getStudentData(), metrics);
            alumniData.add(studentDataResponse);
        });
        return alumniData;
    }

    public Collection<StudentDataResponse> getDropoutsCSV(String from, String to) {
        Collection<StudentDataResponse> dropoutsData = new TreeSet<>();
        Collection<Student> dropouts = this.dataAccessFacade.getDropouts(from, to);
        dropouts.forEach(item -> {
            Metrics metrics = MetricsCalculator.getInstance().computeMetrics(item);
            StudentDataResponse studentDataResponse = new StudentDataResponse(item.getId().getRegistration(),
                    item.getStudentData(), metrics);
            dropoutsData.add(studentDataResponse);
        });
        return dropoutsData;
    }

    public Collection<StudentDataResponse> getDelayedCSV(String from, String to) {
        Collection<StudentDataResponse> delayedData = new TreeSet<>();
        Collection<Student> delayed = this.dataAccessFacade.getDelayed(from, to);
        delayed.forEach(item -> {
            Metrics metrics = MetricsCalculator.getInstance().computeMetrics(item);
            StudentDataResponse studentDataResponse = new StudentDataResponse(item.getId().getRegistration(),
                    item.getStudentData(), metrics);
            delayedData.add(studentDataResponse);
        });
        return delayedData;
    }

    public Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to) {
        return this.dataAccessFacade.getAlumniPerStudentSummary(from, to);
    }

}
