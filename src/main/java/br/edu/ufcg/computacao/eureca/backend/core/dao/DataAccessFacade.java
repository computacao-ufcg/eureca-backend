package br.edu.ufcg.computacao.eureca.backend.core.dao;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;

import java.util.Collection;
import java.util.Map;

public interface DataAccessFacade {
    Collection<StudentDataResponse> getActiveStudents(String from, String to);

    Collection<ActiveData> getActiveStudentsSummary(String from, String to);

    Collection<AlumniData> getAllAlumni(String from, String to);

    AlumniDataResponse getAlumniSummary(String from, String to);

    Collection<DropoutData> getAllDropouts(String from, String to);

    Collection<DropoutSummaryResponse> getDropoutsSummary(String from, String to);

    Collection<AlumnusBasicData> getAlumniBasicData(String from, String to);

    Map<String, Integer> getEnrollments();
}
