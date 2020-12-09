package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutDataResponse implements Comparable {
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

    @Override
    public int compareTo(Object o) {
        DropoutDataResponse other = (DropoutDataResponse) o;
        return this.getStudent().getRegistration().compareTo(other.getStudent().getRegistration());
    }
}
