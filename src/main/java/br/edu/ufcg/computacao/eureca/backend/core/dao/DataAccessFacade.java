package br.edu.ufcg.computacao.eureca.backend.core.dao;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.AttemptsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;

import java.util.Collection;

public interface DataAccessFacade {
    Collection<Student> getActives(String from, String to);

    Collection<Student> getAlumni(String from, String to);

    Collection<Student> getDropouts(String from, String to);

    Collection<Student> getDelayed(String from, String to);

    Collection<ActiveSummary> getActiveSummary(String from, String to);

    Collection<AlumniPerTermSummary> getAlumniPerTermSummary(String from, String to);

    Collection<DropoutPerTermSummary> getDropoutsSummary(String from, String to);

    Collection<AlumniPerStudentSummary> getAlumniPerStudentSummary(String from, String to);

    Collection<AttemptsSummary> getAttemptsSummary();

    Student getStudent(String registration);
}
