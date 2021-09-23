package br.edu.ufcg.computacao.eureca.backend.api.http.response.students;

import java.util.Collection;

public class StudentsResponse {
    private Collection<StudentCSV> students;

    public StudentsResponse(Collection<StudentCSV> students) {
        this.students = students;
    }

    public Collection<StudentCSV> getStudents() {
        return students;
    }

    public void setStudents(Collection<StudentCSV> students) {
        this.students = students;
    }
}
