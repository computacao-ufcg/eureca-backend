package br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher;

import java.util.Collection;

public class TeachersResponse {
    private Collection<TeacherCSV> teachers;

    public TeachersResponse(Collection<TeacherCSV> teachers) {
        this.teachers = teachers;
    }

    public Collection<TeacherCSV> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<TeacherCSV> teachers) {
        this.teachers = teachers;
    }
}
