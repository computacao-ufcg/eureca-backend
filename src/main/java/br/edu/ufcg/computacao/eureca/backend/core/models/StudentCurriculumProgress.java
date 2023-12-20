package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;

import java.util.Collection;
import java.util.TreeSet;

public class StudentCurriculumProgress {
    private String curriculumCode;
    private int completedTerms;
    private int termsLeft;
    private int completedMandatoryCredits;
    private int completedOptionalCredits;
    private int completedElectiveCredits;
    private int completedComplementaryCredits;
    private int completedComplementaryActivities;
    private int enrolledMandatoryCredits;
    private int enrolledOptionalCredits;
    private int enrolledComplementaryCredits;
    private int enrolledElectiveCredits;
    private double speed;
    private int projectedCompleted;
    private Collection<SubjectKey> completed;
    private Collection<SubjectKey> enabled;
    private Collection<SubjectKey> disabled;
    private Collection<SubjectKey> ongoing;
    private Collection<SubjectKey> adequate;
    private String status;

    public StudentCurriculumProgress(String curriculumCode, int completedTerms,
                                     int completedMandatoryCredits, int completedOptionalCredits,
                                     int completedElectiveCredits, int completedComplementaryCredits,
                                     int completedComplementaryActivities, int enrolledMandatoryCredits,
                                     int enrolledOptionalCredits, int enrolledComplementaryCredits,
                                     int enrolledElectiveCredits) {
        this.curriculumCode = curriculumCode;
        this.completedTerms = completedTerms;
        this.completedMandatoryCredits = completedMandatoryCredits;
        this.completedOptionalCredits = completedOptionalCredits;
        this.completedElectiveCredits = completedElectiveCredits;
        this.completedComplementaryCredits = completedComplementaryCredits;
        this.completedComplementaryActivities = completedComplementaryActivities;
        this.enrolledMandatoryCredits = enrolledMandatoryCredits;
        this.enrolledOptionalCredits = enrolledOptionalCredits;
        this.enrolledComplementaryCredits = enrolledComplementaryCredits;
        this.enrolledElectiveCredits = enrolledElectiveCredits;
        this.completed = new TreeSet<>();
        this.enabled = new TreeSet<>();
        this.disabled = new TreeSet<>();
        this.ongoing = new TreeSet<>();
        this.adequate = new TreeSet<>();
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    public int getCompletedTerms() {
        return completedTerms;
    }

    public void setCompletedTerms(int completedTerms) {
        this.completedTerms = completedTerms;
    }

    public int getCompletedMandatoryCredits() {
        return completedMandatoryCredits;
    }

    public int getCompletedCredits() {
        return completedComplementaryCredits + completedMandatoryCredits + completedOptionalCredits + completedElectiveCredits;
    }

    public void setCompletedMandatoryCredits(int completedMandatoryCredits) {
        this.completedMandatoryCredits = completedMandatoryCredits;
    }

    public int getCompletedOptionalCredits() {
        return completedOptionalCredits;
    }

    public void setCompletedOptionalCredits(int completedOptionalCredits) {
        this.completedOptionalCredits = completedOptionalCredits;
    }

    public int getCompletedElectiveCredits() {
        return completedElectiveCredits;
    }

    public void setCompletedElectiveCredits(int completedElectiveCredits) {
        this.completedElectiveCredits = completedElectiveCredits;
    }

    public int getCompletedComplementaryCredits() {
        return completedComplementaryCredits;
    }

    public void setCompletedComplementaryCredits(int completedComplementaryCredits) {
        this.completedComplementaryCredits = completedComplementaryCredits;
    }

    public int getCompletedComplementaryActivities() {
        return completedComplementaryActivities;
    }

    public void setCompletedComplementaryActivities(int completedComplementaryActivities) {
        this.completedComplementaryActivities = completedComplementaryActivities;
    }

    public int getEnrolledCredits() {
        return enrolledMandatoryCredits + enrolledOptionalCredits +
                enrolledComplementaryCredits + enrolledElectiveCredits;
    }

    public int getEnrolledMandatoryCredits() {
        return enrolledMandatoryCredits;
    }

    public void setEnrolledMandatoryCredits(int enrolledMandatoryCredits) {
        this.enrolledMandatoryCredits = enrolledMandatoryCredits;
    }

    public int getEnrolledOptionalCredits() {
        return enrolledOptionalCredits;
    }

    public void setEnrolledOptionalCredits(int enrolledOptionalCredits) {
        this.enrolledOptionalCredits = enrolledOptionalCredits;
    }

    public int getEnrolledComplementaryCredits() {
        return enrolledComplementaryCredits;
    }

    public void setEnrolledComplementaryCredits(int enrolledComplementaryCredits) {
        this.enrolledComplementaryCredits = enrolledComplementaryCredits;
    }

    public int getEnrolledElectiveCredits() {
        return enrolledElectiveCredits;
    }

    public int getTermsLeft() {
        return termsLeft;
    }

    public void setTermsLeft(int termsLeft) {
        this.termsLeft = termsLeft;
    }

    public void setEnrolledElectiveCredits(int enrolledElectiveCredits) {
        this.enrolledElectiveCredits = enrolledElectiveCredits;
    }

    public Collection<SubjectKey> getCompleted() {
        return completed;
    }

    public void setCompleted(Collection<SubjectKey> completed) {
        this.completed = completed;
    }

    public Collection<SubjectKey> getEnabled() {
        return enabled;
    }

    public void setEnabled(Collection<SubjectKey> enabled) {
        this.enabled = enabled;
    }

    public Collection<SubjectKey> getDisabled() {
        return disabled;
    }

    public void setDisabled(Collection<SubjectKey> disabled) {
        this.disabled = disabled;
    }

    public Collection<SubjectKey> getOngoing() {
        return ongoing;
    }

    public void setOngoing(Collection<SubjectKey> ongoing) {
        this.ongoing = ongoing;
    }

    public Collection<SubjectKey> getAdequate() {
        return adequate;
    }

    public void setAdequate(Collection<SubjectKey> adequate) {
        this.adequate = adequate;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getProjectedCompleted() {
        return projectedCompleted;
    }

    public void setProjectedCompleted(int projectedCompleted) {
        this.projectedCompleted = projectedCompleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentCurriculumProgress{" +
                "curriculumCode='" + curriculumCode + '\'' +
                ", completedTerms=" + completedTerms +
                ", termsLeft=" + termsLeft +
                ", completedMandatoryCredits=" + completedMandatoryCredits +
                ", completedOptionalCredits=" + completedOptionalCredits +
                ", completedElectiveCredits=" + completedElectiveCredits +
                ", completedComplementaryCredits=" + completedComplementaryCredits +
                ", completedComplementaryActivities=" + completedComplementaryActivities +
                ", enrolledMandatoryCredits=" + enrolledMandatoryCredits +
                ", enrolledOptionalCredits=" + enrolledOptionalCredits +
                ", enrolledComplementaryCredits=" + enrolledComplementaryCredits +
                ", enrolledElectiveCredits=" + enrolledElectiveCredits +
                ", speed=" + speed +
                ", projectedCompleted=" + projectedCompleted +
                ", completed=" + completed +
                ", enabled=" + enabled +
                ", disabled=" + disabled +
                ", ongoing=" + ongoing +
                ", adequate=" + adequate +
                ", status='" + status + '\'' +
                '}';
    }
}
