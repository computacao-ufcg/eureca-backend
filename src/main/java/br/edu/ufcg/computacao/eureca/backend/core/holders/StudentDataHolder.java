package br.edu.ufcg.computacao.eureca.backend.core.holders;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.DelayedDataResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.StudentDataResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StudentDataHolder {
    private Logger LOGGER = Logger.getLogger(StudentDataHolder.class);

    private static StudentDataHolder instance;
    private DataAccessFacade dataAccessFacade;

    private StudentDataHolder() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public static synchronized StudentDataHolder getInstance() {
        if (instance == null) {
            instance = new StudentDataHolder();
        }
        return instance;
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

    public Collection<DelayedDataResponse> getDelayedCSV(String from, String to) {
        return this.dataAccessFacade.getDelayed(from, to)
                .stream()
                .map(DelayedDataResponse::new)
                .collect(Collectors.toSet());
    }
}
