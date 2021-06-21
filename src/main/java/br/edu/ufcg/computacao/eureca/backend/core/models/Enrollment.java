package br.edu.ufcg.computacao.eureca.backend.core.models;

public class Enrollment {
    private String registration;
    private String code;
    private String term;
    private String classId;
    private int credits;
    private double grade;
    private String status;

    public Enrollment(String registration, String code, String term, String classId, int credits, double grade,
                      String status) {
        this.registration = registration;
        this.code = code;
        this.term = term;
        this.classId = classId;
        this.credits = credits;
        this.grade = grade;
        this.status = status;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "registration='" + registration + '\'' +
                ", code='" + code + '\'' +
                ", term='" + term + '\'' +
                ", classId='" + classId + '\'' +
                ", credits=" + credits +
                ", grade=" + grade +
                ", status='" + status + '\'' +
                '}';
    }
}
