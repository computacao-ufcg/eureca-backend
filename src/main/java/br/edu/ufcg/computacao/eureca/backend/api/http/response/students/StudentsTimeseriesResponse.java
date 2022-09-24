package br.edu.ufcg.computacao.eureca.backend.api.http.response.students;

import java.util.Collection;

public class StudentsTimeseriesResponse {
    private Collection<StudentPerformance> students;

    public StudentsTimeseriesResponse(Collection<StudentPerformance> students) {
        this.students = students;
    }

    public Collection<StudentPerformance> getStudents() {
        return students;
    }

    public void setStudents(Collection<StudentPerformance> students) {
        this.students = students;
    }
}
