package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;

import java.util.Collection;
import java.util.TreeSet;

public class StudentCurriculumProgress {
    private int completedTerms;
    private int completedMandatoryCredits;
    private int completedOptionalCredits;
    private int completedElectiveCredits;
    private int completedComplementaryCredits;
    private int completedComplementaryActivities;
    private int enrolledCredits;
    private Collection<SubjectKey> completed;
    private Collection<SubjectKey> enabled;
    private Collection<SubjectKey> disabled;
    private Collection<SubjectKey> ongoing;
    private Collection<SubjectKey> adequate;

    public StudentCurriculumProgress(int completedTerms, int completedMandatoryCredits, int completedOptionalCredits,
                                     int completedElectiveCredits, int completedComplementaryCredits,
                                     int completedComplementaryActivities, int enrolledCredits) {
        this.completedTerms = completedTerms;
        this.completedMandatoryCredits = completedMandatoryCredits;
        this.completedOptionalCredits = completedOptionalCredits;
        this.completedElectiveCredits = completedElectiveCredits;
        this.completedComplementaryCredits = completedComplementaryCredits;
        this.completedComplementaryActivities = completedComplementaryActivities;
        this.enrolledCredits = enrolledCredits;
        this.completed = new TreeSet<>();
        this.enabled = new TreeSet<>();
        this.disabled = new TreeSet<>();
        this.ongoing = new TreeSet<>();
        this.adequate = new TreeSet<>();
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
        return completedComplementaryCredits + completedMandatoryCredits + completedOptionalCredits +
                completedElectiveCredits + completedComplementaryActivities;
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
        return enrolledCredits;
    }

    public void setEnrolledCredits(int enrolledCredits) {
        this.enrolledCredits = enrolledCredits;
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

    @Override
    public String toString() {
        return "StudentCurriculumProgress{" +
                "completedTerms=" + completedTerms +
                ", completedMandatoryCredits=" + completedMandatoryCredits +
                ", completedOptionalCredits=" + completedOptionalCredits +
                ", completedElectiveCredits=" + completedElectiveCredits +
                ", completedComplementaryCredits=" + completedComplementaryCredits +
                ", completedComplementaryActivities=" + completedComplementaryActivities +
                ", enrolledCredits=" + enrolledCredits +
                ", completed=" + completed +
                ", enabled=" + enabled +
                ", disabled=" + disabled +
                ", ongoing=" + ongoing +
                ", adequate=" + adequate +
                '}';
    }
}
