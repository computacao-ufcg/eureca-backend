package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.*;

public class StudentPreEnrollmentResponse implements Comparable {

    private String studentRegistration;
    private Collection<Enrollment> enrollments;
    private int term;
    private int maxCredits;
    private int maxMandatoryCredits;
    private int mandatoryCredits;
    private int maxOptionalCredits;
    private int optionalCredits;
    private int maxComplementaryCredits;
    private int complementaryCredits;
    private int maxElectiveCredits;
    private int electiveCredits;
    private boolean isPossibleGraduate;

    public StudentPreEnrollmentResponse(StudentPreEnrollment studentPreEnrollment) {
        this.studentRegistration = studentPreEnrollment.getStudentRegistration();
        this.enrollments = new TreeSet<>();
        studentPreEnrollment.getSubjects().values().forEach(scheduleResponse -> {
            this.enrollments.add(new Enrollment(studentPreEnrollment.getStudentRegistration(),
                            studentPreEnrollment.getTerm(),
                            studentPreEnrollment.getMaxCredits(),
                            studentPreEnrollment.getMandatoryCredits(),
                            studentPreEnrollment.getOptionalCredits(),
                            studentPreEnrollment.getComplementaryCredits(),
                            studentPreEnrollment.getElectiveCredits(),
                            scheduleResponse.getSubjectName(),
                            scheduleResponse.getSchedule().getClassCode()));
        });
        this.term = studentPreEnrollment.getTerm();
        this.maxCredits = studentPreEnrollment.getMaxCredits();
        this.maxMandatoryCredits = studentPreEnrollment.getMaxMandatoryCredits();
        this.mandatoryCredits = studentPreEnrollment.getMandatoryCredits();
        this.maxOptionalCredits = studentPreEnrollment.getMaxOptionalCredits();
        this.optionalCredits = studentPreEnrollment.getOptionalCredits();
        this.maxComplementaryCredits = studentPreEnrollment.getMaxComplementaryCredits();
        this.complementaryCredits = studentPreEnrollment.getComplementaryCredits();
        this.maxElectiveCredits = studentPreEnrollment.getMaxElectiveCredits();
        this.electiveCredits = studentPreEnrollment.getElectiveCredits();
        this.isPossibleGraduate = studentPreEnrollment.isPossibleGraduate();
    }

    public String getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(String studentRegistration) {
        this.studentRegistration = studentRegistration;
    }

    public Collection<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Collection<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(int maxCredits) {
        this.maxCredits = maxCredits;
    }

    public int getMaxMandatoryCredits() {
        return maxMandatoryCredits;
    }

    public void setMaxMandatoryCredits(int maxMandatoryCredits) {
        this.maxMandatoryCredits = maxMandatoryCredits;
    }

    public int getMandatoryCredits() {
        return mandatoryCredits;
    }

    public void setMandatoryCredits(int mandatoryCredits) {
        this.mandatoryCredits = mandatoryCredits;
    }

    public int getMaxOptionalCredits() {
        return maxOptionalCredits;
    }

    public void setMaxOptionalCredits(int maxOptionalCredits) {
        this.maxOptionalCredits = maxOptionalCredits;
    }

    public int getOptionalCredits() {
        return optionalCredits;
    }

    public void setOptionalCredits(int optionalCredits) {
        this.optionalCredits = optionalCredits;
    }

    public int getMaxComplementaryCredits() {
        return maxComplementaryCredits;
    }

    public void setMaxComplementaryCredits(int maxComplementaryCredits) {
        this.maxComplementaryCredits = maxComplementaryCredits;
    }

    public int getComplementaryCredits() {
        return complementaryCredits;
    }

    public void setComplementaryCredits(int complementaryCredits) {
        this.complementaryCredits = complementaryCredits;
    }

    public int getMaxElectiveCredits() {
        return maxElectiveCredits;
    }

    public void setMaxElectiveCredits(int maxElectiveCredits) {
        this.maxElectiveCredits = maxElectiveCredits;
    }

    public int getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(int electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public boolean isPossibleGraduate() {
        return isPossibleGraduate;
    }

    public void setPossibleGraduate(boolean possibleGraduate) {
        isPossibleGraduate = possibleGraduate;
    }

    @Override
    public int compareTo(Object o) {
        StudentPreEnrollmentResponse other = (StudentPreEnrollmentResponse) o;
        return this.studentRegistration.compareTo(other.getStudentRegistration());
    }
}
