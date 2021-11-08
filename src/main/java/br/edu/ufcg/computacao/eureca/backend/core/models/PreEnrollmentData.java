package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Collection;

public class PreEnrollmentData {

    private String studentRegistration;
    private int nextTerm;
    private int idealMandatoryCredits;
    private int idealOptionalCredits;
    private int idealComplementaryCredits;
    private int idealElectiveCredits;
    private Collection<Subject> availableMandatorySubjects;
    private Collection<Subject> availableComplementarySubjects;
    private Collection<Subject> availableOptionalSubjects;
    private Collection<Subject> availableElectiveSubjects;
    private Collection<Subject> prioritizedOptionalSubjects;
    private Collection<Subject> prioritizedElectiveSubjects;
    private Collection<Subject> prioritizedMandatorySubjects;

    public PreEnrollmentData(String studentRegistration, int nextTerm, int idealMandatoryCredits, int idealOptionalCredits, int idealComplementaryCredits, int idealElectiveCredits, Collection<Subject> availableMandatorySubjects, Collection<Subject> availableComplementarySubjects, Collection<Subject> availableOptionalSubjects, Collection<Subject> availableElectiveSubjects, Collection<Subject> prioritizedOptionalSubjects, Collection<Subject> prioritizedElectiveSubjects, Collection<Subject> prioritizedMandatorySubjects) {
        this.studentRegistration = studentRegistration;
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
        this.prioritizedMandatorySubjects = prioritizedMandatorySubjects;
    }

    public String getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(String studentRegistration) {
        this.studentRegistration = studentRegistration;
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

    public Collection<Subject> getAvailableMandatorySubjects() {
        return availableMandatorySubjects;
    }

    public void setAvailableMandatorySubjects(Collection<Subject> availableMandatorySubjects) {
        this.availableMandatorySubjects = availableMandatorySubjects;
    }

    public Collection<Subject> getAvailableComplementarySubjects() {
        return availableComplementarySubjects;
    }

    public void setAvailableComplementarySubjects(Collection<Subject> availableComplementarySubjects) {
        this.availableComplementarySubjects = availableComplementarySubjects;
    }

    public Collection<Subject> getAvailableOptionalSubjects() {
        return availableOptionalSubjects;
    }

    public void setAvailableOptionalSubjects(Collection<Subject> availableOptionalSubjects) {
        this.availableOptionalSubjects = availableOptionalSubjects;
    }

    public Collection<Subject> getAvailableElectiveSubjects() {
        return availableElectiveSubjects;
    }

    public void setAvailableElectiveSubjects(Collection<Subject> availableElectiveSubjects) {
        this.availableElectiveSubjects = availableElectiveSubjects;
    }

    public Collection<Subject> getPrioritizedOptionalSubjects() {
        return prioritizedOptionalSubjects;
    }

    public void setPrioritizedOptionalSubjects(Collection<Subject> prioritizedOptionalSubjects) {
        this.prioritizedOptionalSubjects = prioritizedOptionalSubjects;
    }

    public Collection<Subject> getPrioritizedElectiveSubjects() {
        return prioritizedElectiveSubjects;
    }

    public void setPrioritizedElectiveSubjects(Collection<Subject> prioritizedElectiveSubjects) {
        this.prioritizedElectiveSubjects = prioritizedElectiveSubjects;
    }

    public Collection<Subject> getPrioritizedMandatorySubjects() {
        return prioritizedMandatorySubjects;
    }

    public void setPrioritizedMandatorySubjects(Collection<Subject> prioritizedMandatorySubjects) {
        this.prioritizedMandatorySubjects = prioritizedMandatorySubjects;
    }
}
