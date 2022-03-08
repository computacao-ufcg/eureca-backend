package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Collection;

public class PreEnrollmentData {

    private String studentRegistration;
    private String term;
    private int nextTerm;
    private int idealMandatoryCredits;
    private int idealOptionalCredits;
    private int idealComplementaryCredits;
    private int idealElectiveCredits;
    private Collection<SubjectSchedule> availableMandatorySubjects;
    private Collection<SubjectSchedule> availableComplementarySubjects;
    private Collection<SubjectSchedule> availableOptionalSubjects;
    private Collection<SubjectSchedule> availableElectiveSubjects;
    private Collection<SubjectSchedule> prioritizedOptionalSubjects;
    private Collection<SubjectSchedule> prioritizedElectiveSubjects;
    private Collection<SubjectSchedule> prioritizedComplementarySubjects;
    private Collection<SubjectSchedule> prioritizedMandatorySubjects;


    public PreEnrollmentData(String studentRegistration, String term, int nextTerm, int idealMandatoryCredits,
                             int idealOptionalCredits, int idealComplementaryCredits, int idealElectiveCredits,
                             Collection<SubjectSchedule> availableMandatorySubjects,
                             Collection<SubjectSchedule> availableComplementarySubjects,
                             Collection<SubjectSchedule> availableOptionalSubjects,
                             Collection<SubjectSchedule> availableElectiveSubjects,
                             Collection<SubjectSchedule> prioritizedOptionalSubjects,
                             Collection<SubjectSchedule> prioritizedElectiveSubjects,
                             Collection<SubjectSchedule> prioritizedComplementarySubjects,
                             Collection<SubjectSchedule> prioritizedMandatorySubjects) {
        this.studentRegistration = studentRegistration;
        this.term = term;
        this.nextTerm = nextTerm;
        this.idealMandatoryCredits = idealMandatoryCredits;
        this.idealOptionalCredits = idealOptionalCredits;
        this.idealComplementaryCredits = idealComplementaryCredits;
        this.idealElectiveCredits = idealElectiveCredits;
        this.availableMandatorySubjects = availableMandatorySubjects;
        this.availableComplementarySubjects = availableComplementarySubjects;
        this.availableOptionalSubjects = availableOptionalSubjects;
        this.availableElectiveSubjects = availableElectiveSubjects;
        this.prioritizedOptionalSubjects = prioritizedOptionalSubjects;
        this.prioritizedElectiveSubjects = prioritizedElectiveSubjects;
        this.prioritizedComplementarySubjects = prioritizedComplementarySubjects;
        this.prioritizedMandatorySubjects = prioritizedMandatorySubjects;
    }

    public String getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(String studentRegistration) {
        this.studentRegistration = studentRegistration;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getNextTerm() {
        return nextTerm;
    }

    public void setNextTerm(int nextTerm) {
        this.nextTerm = nextTerm;
    }

    public int getIdealMandatoryCredits() {
        return idealMandatoryCredits;
    }

    public void setIdealMandatoryCredits(int idealMandatoryCredits) {
        this.idealMandatoryCredits = idealMandatoryCredits;
    }

    public int getIdealOptionalCredits() {
        return idealOptionalCredits;
    }

    public void setIdealOptionalCredits(int idealOptionalCredits) {
        this.idealOptionalCredits = idealOptionalCredits;
    }

    public int getIdealComplementaryCredits() {
        return idealComplementaryCredits;
    }

    public void setIdealComplementaryCredits(int idealComplementaryCredits) {
        this.idealComplementaryCredits = idealComplementaryCredits;
    }

    public int getIdealElectiveCredits() {
        return idealElectiveCredits;
    }

    public void setIdealElectiveCredits(int idealElectiveCredits) {
        this.idealElectiveCredits = idealElectiveCredits;
    }

    public Collection<SubjectSchedule> getAvailableMandatorySubjects() {
        return availableMandatorySubjects;
    }

    public void setAvailableMandatorySubjects(Collection<SubjectSchedule> availableMandatorySubjects) {
        this.availableMandatorySubjects = availableMandatorySubjects;
    }

    public Collection<SubjectSchedule> getAvailableComplementarySubjects() {
        return availableComplementarySubjects;
    }

    public void setAvailableComplementarySubjects(Collection<SubjectSchedule> availableComplementarySubjects) {
        this.availableComplementarySubjects = availableComplementarySubjects;
    }

    public Collection<SubjectSchedule> getAvailableOptionalSubjects() {
        return availableOptionalSubjects;
    }

    public void setAvailableOptionalSubjects(Collection<SubjectSchedule> availableOptionalSubjects) {
        this.availableOptionalSubjects = availableOptionalSubjects;
    }

    public Collection<SubjectSchedule> getAvailableElectiveSubjects() {
        return availableElectiveSubjects;
    }

    public void setAvailableElectiveSubjects(Collection<SubjectSchedule> availableElectiveSubjects) {
        this.availableElectiveSubjects = availableElectiveSubjects;
    }

    public Collection<SubjectSchedule> getPrioritizedOptionalSubjects() {
        return prioritizedOptionalSubjects;
    }

    public void setPrioritizedOptionalSubjects(Collection<SubjectSchedule> prioritizedOptionalSubjects) {
        this.prioritizedOptionalSubjects = prioritizedOptionalSubjects;
    }

    public Collection<SubjectSchedule> getPrioritizedElectiveSubjects() {
        return prioritizedElectiveSubjects;
    }

    public void setPrioritizedElectiveSubjects(Collection<SubjectSchedule> prioritizedElectiveSubjects) {
        this.prioritizedElectiveSubjects = prioritizedElectiveSubjects;
    }

    public Collection<SubjectSchedule> getPrioritizedComplementarySubjects() {
        return prioritizedComplementarySubjects;
    }

    public void setPrioritizedComplementarySubjects(Collection<SubjectSchedule> prioritizedComplementarySubjects) {
        this.prioritizedComplementarySubjects = prioritizedComplementarySubjects;
    }

    public Collection<SubjectSchedule> getPrioritizedMandatorySubjects() {
        return prioritizedMandatorySubjects;
    }

    public void setPrioritizedMandatorySubjects(Collection<SubjectSchedule> prioritizedMandatorySubjects) {
        this.prioritizedMandatorySubjects = prioritizedMandatorySubjects;
    }
}
