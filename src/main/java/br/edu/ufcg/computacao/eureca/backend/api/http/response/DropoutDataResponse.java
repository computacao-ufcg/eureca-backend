package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutDataResponse {
    StudentDataResponse student;
    String dropoutReason;

    public DropoutDataResponse(StudentDataResponse student, String dropoutReason) {
        this.student = student;
        this.dropoutReason = dropoutReason;
    }

    public StudentDataResponse getStudent() {
        return student;
    }

    public void setStudent(StudentDataResponse student) {
        this.student = student;
    }

    public String getDropoutReason() {
        return dropoutReason;
    }

    public void setDropoutReason(String dropoutReason) {
        this.dropoutReason = dropoutReason;
    }
}
