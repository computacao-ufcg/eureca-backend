package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Set;
import java.util.TreeSet;

public class ClassEnrollments {
    private Set<String> succeeded;
    private Set<String> cancelled;
    private Set<String> exempted;
    private Set<String> ongoing;
    private Set<String> failedDueToGrade;
    private Set<String> failedDueToAbsence;
    private Set<String> suspended;

    public ClassEnrollments() {
        this.succeeded = new TreeSet<>();
        this.cancelled = new TreeSet<>();
        this.exempted = new TreeSet<>();
        this.ongoing = new TreeSet<>();
        this.failedDueToGrade = new TreeSet<>();
        this.failedDueToAbsence = new TreeSet<>();
        this.suspended = new TreeSet<>();
    }

    public int getNumberOfEnrolleds() {
        return this.succeeded.size() + this.cancelled.size() + this.ongoing.size() +
                this.failedDueToGrade.size() + this.failedDueToAbsence.size() + this.suspended.size();
    }

    public int getNumberSucceeded() {
        return this.succeeded.size();
    }

    public int getNumberCancelled() {
        return this.cancelled.size();
    }

    public int getNumberExempted() {
        return this.exempted.size();
    }

    public int getNumberOngoing() {
        return this.ongoing.size();
    }

    public int getNumberFailedDueToGrade() {
        return this.failedDueToGrade.size();
    }

    public int getNumberFailedDueToAbsence() {
        return this.failedDueToAbsence.size();
    }

    public int getNumberSuspended() {
        return this.suspended.size();
    }

    public Set<String> getEnrolleds() {
        Set<String> ret = new TreeSet<>();
        ret.addAll(this.succeeded);
        ret.addAll(this.cancelled);
        ret.addAll(this.exempted);
        ret.addAll(this.ongoing);
        ret.addAll(this.failedDueToGrade);
        ret.addAll(this.failedDueToAbsence);
        ret.addAll(this.suspended);
        return ret;
    }

    public Set<String> getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Set<String> succeeded) {
        this.succeeded = succeeded;
    }

    public Set<String> getCancelled() {
        return cancelled;
    }

    public void setCancelled(Set<String> cancelled) {
        this.cancelled = cancelled;
    }

    public Set<String> getExempted() {
        return exempted;
    }

    public void setExempted(Set<String> exempted) {
        this.exempted = exempted;
    }

    public Set<String> getOngoing() {
        return ongoing;
    }

    public void setOngoing(Set<String> ongoing) {
        this.ongoing = ongoing;
    }

    public Set<String> getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public void setFailedDueToGrade(Set<String> failedDueToGrade) {
        this.failedDueToGrade = failedDueToGrade;
    }

    public Set<String> getFailedDueToAbsence() {
        return failedDueToAbsence;
    }

    public void setFailedDueToAbsence(Set<String> failedDueToAbsence) {
        this.failedDueToAbsence = failedDueToAbsence;
    }

    public Set<String> getSuspended() {
        return suspended;
    }

    public void setSuspended(Set<String> suspended) {
        this.suspended = suspended;
    }
}
