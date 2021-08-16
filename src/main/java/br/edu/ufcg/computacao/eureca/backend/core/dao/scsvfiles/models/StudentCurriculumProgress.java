package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;

import java.util.Collection;
import java.util.TreeSet;

public class StudentCurriculumProgress {
    private int currentTerm;
    private int accumulatedCredits;
    private int enrolledCredits;
    private Collection<SubjectKey> completed;
    private Collection<SubjectKey> enabled;
    private Collection<SubjectKey> disabled;
    private Collection<SubjectKey> ongoing;
    private Collection<SubjectKey> adequate;

    public StudentCurriculumProgress(int currentTerm, int accumulatedCredits, int enrolledCredits) {
        this.currentTerm = currentTerm;
        this.accumulatedCredits = accumulatedCredits;
        this.enrolledCredits = enrolledCredits;
        this.completed = new TreeSet<>();
        this.enabled = new TreeSet<>();
        this.disabled = new TreeSet<>();
        this.ongoing = new TreeSet<>();
        this.adequate = new TreeSet<>();
    }

    public int getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(int currentTerm) {
        this.currentTerm = currentTerm;
    }

    public int getAccumulatedCredits() {
        return accumulatedCredits;
    }

    public void setAccumulatedCredits(int accumulatedCredits) {
        this.accumulatedCredits = accumulatedCredits;
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
}
