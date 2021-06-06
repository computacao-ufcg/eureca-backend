package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class EnrollmentData implements EurecaMapValue {
    private String classId;
    private int credits;
    private double grade;
    private String status;

    public EnrollmentData(String classId, int credits, double finalGrade, String status) {
        this.classId = classId;
        this.credits = credits;
        this.grade = finalGrade;
        this.status = status;
    }

    public EnrollmentData() {
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
                " classId=" + classId +
                ", credits=" + credits +
                ", grade='" + grade + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
