package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.Objects;

public class Enrollment implements Comparable {
    private String studentRegistration;
    private int term;
    private int totalCredits;
    private int totalBalance;
    private int mandatoryCreditsBalance;
    private int optionalCreditsBalance;
    private int complementaryCreditsBalance;
    private int electiveCreditsBalance;
    private String subjectName;
    private String classCode;

    public Enrollment(String studentRegistration, int term, int maxMandatoryCredits, int mandatoryCredits,
                      int maxOptionalCredits, int optionalCredits, int maxComplementaryCredits,
                      int complementaryCredits, int maxElectiveCredits, int electiveCredits, String subjectName,
                      String classCode) {
        this.studentRegistration = studentRegistration;
        this.term = term;
        this.totalCredits = mandatoryCredits + optionalCredits + electiveCredits + complementaryCredits;
        this.mandatoryCreditsBalance = maxMandatoryCredits - mandatoryCredits;
        this.optionalCreditsBalance = maxOptionalCredits - optionalCredits;
        this.complementaryCreditsBalance = maxComplementaryCredits - complementaryCredits;
        this.electiveCreditsBalance = maxElectiveCredits - electiveCredits;
        this.totalBalance = this.mandatoryCreditsBalance + this.optionalCreditsBalance + this.electiveCreditsBalance +
                this.complementaryCreditsBalance;
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

    public int getMandatoryCreditsBalance() {
        return mandatoryCreditsBalance;
    }

    public void setMandatoryCreditsBalance(int mandatoryCreditsBalance) {
        this.mandatoryCreditsBalance = mandatoryCreditsBalance;
    }

    public int getOptionalCreditsBalance() {
        return optionalCreditsBalance;
    }

    public void setOptionalCreditsBalance(int optionalCreditsBalance) {
        this.optionalCreditsBalance = optionalCreditsBalance;
    }

    public int getComplementaryCreditsBalance() {
        return complementaryCreditsBalance;
    }

    public void setComplementaryCreditsBalance(int complementaryCreditsBalance) {
        this.complementaryCreditsBalance = complementaryCreditsBalance;
    }

    public int getElectiveCreditsBalance() {
        return electiveCreditsBalance;
    }

    public void setElectiveCreditsBalance(int electiveCreditsBalance) {
        this.electiveCreditsBalance = electiveCreditsBalance;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
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
                getMandatoryCreditsBalance() == that.getMandatoryCreditsBalance() &&
                getOptionalCreditsBalance() == that.getOptionalCreditsBalance() &&
                getComplementaryCreditsBalance() == that.getComplementaryCreditsBalance() &&
                getElectiveCreditsBalance() == that.getElectiveCreditsBalance() &&
                getStudentRegistration().equals(that.getStudentRegistration()) &&
                getSubjectName().equals(that.getSubjectName()) &&
                getClassCode().equals(that.getClassCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentRegistration(), getTerm(), getMandatoryCreditsBalance(),
                getOptionalCreditsBalance(), getComplementaryCreditsBalance(),
                getElectiveCreditsBalance(), getSubjectName(), getClassCode());
    }

    @Override
    public int compareTo(Object o) {
        String thisKey = this.studentRegistration + this.subjectName + this.classCode;
        Enrollment other = (Enrollment) o;
        String otherKey = other.studentRegistration + other.subjectName + other.classCode;
        return thisKey.compareTo(otherKey);
    }
}
