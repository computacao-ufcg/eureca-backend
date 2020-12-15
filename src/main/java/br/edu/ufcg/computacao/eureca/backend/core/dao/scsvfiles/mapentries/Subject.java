package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class Subject extends EurecaMapValue {
    int credits;
    int hours;
    String subjectName;
    String subjectType;
    double finalGrade;
    String status;

    public Subject(int credits, int hours, String subjectName,
                   String subjectType, double finalGrade, String status) {
        this.credits = credits;
        this.hours = hours;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.finalGrade = finalGrade;
        this.status = status;
    }

    public Subject() {
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subject{" +
                ", credits=" + credits +
                ", hours=" + hours +
                ", subjectName='" + subjectName + '\'' +
                ", subjectType='" + subjectType + '\'' +
                ", finalGrade='" + finalGrade + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
