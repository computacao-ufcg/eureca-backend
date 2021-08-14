package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class TeachersStatisticsResponse extends Range {
    private Collection<TeachersPerAcademicUnitPerTermSummary> teachers;

    public TeachersStatisticsResponse(Collection<TeachersPerAcademicUnitPerTermSummary> teachers, String from, String to) {
        super(from, to);
        this.teachers = teachers;
    }

    public Collection<TeachersPerAcademicUnitPerTermSummary> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<TeachersPerAcademicUnitPerTermSummary> teachers) {
        this.teachers = teachers;
    }
}
