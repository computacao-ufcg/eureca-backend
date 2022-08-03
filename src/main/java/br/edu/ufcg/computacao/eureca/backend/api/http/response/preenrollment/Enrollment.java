package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.Objects;

public class Enrollment implements Comparable {
    private String studentRegistration;
    private int term;
    private int totalCredits;
    private int deficit;
    private int mandatoryCredits;
    private int optionalCredits;
    private int complementaryCredits;
    private int electiveCredits;
    private String subjectName;
    private String classCode;

    public Enrollment(String studentRegistration, int term, int maxCredits, int mandatoryCredits,
                      int complementaryCredits, int optionalCredits, int electiveCredits, String subjectName,
                      String classCode) {
        this.studentRegistration = studentRegistration;
        this.term = term;
        this.totalCredits = mandatoryCredits + optionalCredits + electiveCredits + complementaryCredits;
        this.mandatoryCredits = mandatoryCredits;
        this.optionalCredits = optionalCredits;
        this.complementaryCredits = complementaryCredits;
        this.electiveCredits = electiveCredits;
        this.deficit = maxCredits - this.totalCredits;
        this.subjectName = subjectName;
        this.classCode = classCode;
    }

    public String getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(String studentRegistration) {
        this.studentRegistration = studentRegistration;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getMandatoryCredits() {
        return mandatoryCredits;
    }

    public void setMandatoryCredits(int mandatoryCredits) {
        this.mandatoryCredits = mandatoryCredits;
    }

    public int getOptionalCredits() {
        return optionalCredits;
    }

    public void setOptionalCredits(int optionalCredits) {
        this.optionalCredits = optionalCredits;
    }

    public int getComplementaryCredits() {
        return complementaryCredits;
    }

    public void setComplementaryCredits(int complementaryCredits) {
        this.complementaryCredits = complementaryCredits;
    }

    public int getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(int electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getDeficit() {
        return deficit;
    }

    public void setDeficit(int deficit) {
        this.deficit = deficit;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return getTerm() == that.getTerm() &&
                getMandatoryCredits() == that.getMandatoryCredits() &&
                getOptionalCredits() == that.getOptionalCredits() &&
                getComplementaryCredits() == that.getComplementaryCredits() &&
                getElectiveCredits() == that.getElectiveCredits() &&
                getStudentRegistration().equals(that.getStudentRegistration()) &&
                getSubjectName().equals(that.getSubjectName()) &&
                getClassCode().equals(that.getClassCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentRegistration(), getTerm(), getMandatoryCredits(),
                getOptionalCredits(), getComplementaryCredits(),
                getElectiveCredits(), getSubjectName(), getClassCode());
    }

    @Override
    public int compareTo(Object o) {
        String thisKey = this.studentRegistration + this.subjectName + this.classCode;
        Enrollment other = (Enrollment) o;
        String otherKey = other.studentRegistration + other.subjectName + other.classCode;
        return thisKey.compareTo(otherKey);
    }
}
