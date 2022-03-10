package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Collection;

public class PreEnrollmentData {
    private Collection<SubjectSchedule> availableMandatorySubjects;
    private Collection<SubjectSchedule> availableComplementarySubjects;
    private Collection<SubjectSchedule> availableOptionalSubjects;
    private Collection<SubjectSchedule> availableElectiveSubjects;
    private Collection<SubjectSchedule> prioritizedOptionalSubjects;
    private Collection<SubjectSchedule> prioritizedElectiveSubjects;
    private Collection<SubjectSchedule> prioritizedComplementarySubjects;
    private Collection<SubjectSchedule> prioritizedMandatorySubjects;

    public PreEnrollmentData(Collection<SubjectSchedule> availableMandatorySubjects,
                             Collection<SubjectSchedule> availableComplementarySubjects,
                             Collection<SubjectSchedule> availableOptionalSubjects,
                             Collection<SubjectSchedule> availableElectiveSubjects,
                             Collection<SubjectSchedule> prioritizedOptionalSubjects,
                             Collection<SubjectSchedule> prioritizedElectiveSubjects,
                             Collection<SubjectSchedule> prioritizedComplementarySubjects,
                             Collection<SubjectSchedule> prioritizedMandatorySubjects) {
        this.availableMandatorySubjects = availableMandatorySubjects;
        this.availableComplementarySubjects = availableComplementarySubjects;
        this.availableOptionalSubjects = availableOptionalSubjects;
        this.availableElectiveSubjects = availableElectiveSubjects;
        this.prioritizedOptionalSubjects = prioritizedOptionalSubjects;
        this.prioritizedElectiveSubjects = prioritizedElectiveSubjects;
        this.prioritizedComplementarySubjects = prioritizedComplementarySubjects;
        this.prioritizedMandatorySubjects = prioritizedMandatorySubjects;
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
