package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class TeachersResponse {
    private Collection<TeachersCSV> teachers;

    public TeachersResponse(Collection<TeachersCSV> teachers) {
        this.teachers = teachers;
    }

    public Collection<TeachersCSV> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<TeachersCSV> teachers) {
        this.teachers = teachers;
    }
}
