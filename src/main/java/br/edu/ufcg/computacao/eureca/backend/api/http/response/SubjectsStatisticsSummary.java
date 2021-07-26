package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricSummary;

public class SubjectsStatisticsSummary {
    private int subjectsCount;
    private int enrollmentsCount;
    private MetricSummary failedDueToAbsences;
    private MetricSummary failedDueToGrade;
    private MetricSummary cancelled;
    private MetricSummary succeeded;
    private MetricSummary exempted;

    public SubjectsStatisticsSummary(int subjectsCount, int enrollmentsCount, MetricSummary failedDueToAbsences,
                                     MetricSummary failedDueToGrade, MetricSummary cancelled, MetricSummary succeeded,
                                     MetricSummary exempted) {
        this.subjectsCount = subjectsCount;
        this.enrollmentsCount = enrollmentsCount;
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.cancelled = cancelled;
        this.succeeded = succeeded;
        this.exempted = exempted;
    }

    public int getSubjectsCount() {
        return subjectsCount;
    }

    public void setSubjectsCount(int subjectsCount) {
        this.subjectsCount = subjectsCount;
    }

    public int getEnrollmentsCount() {
        return enrollmentsCount;
    }

    public void setEnrollmentsCount(int enrollmentsCount) {
        this.enrollmentsCount = enrollmentsCount;
    }

    public MetricSummary getFailedDueToAbsences() {
        return failedDueToAbsences;
    }

    public void setFailedDueToAbsences(MetricSummary failedDueToAbsences) {
        this.failedDueToAbsences = failedDueToAbsences;
    }

    public MetricSummary getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public void setFailedDueToGrade(MetricSummary failedDueToGrade) {
        this.failedDueToGrade = failedDueToGrade;
    }

    public MetricSummary getCancelled() {
        return cancelled;
    }

    public void setCancelled(MetricSummary cancelled) {
        this.cancelled = cancelled;
    }

    public MetricSummary getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(MetricSummary succeeded) {
        this.succeeded = succeeded;
    }

    public MetricSummary getExempted() {
        return exempted;
    }

    public void setExempted(MetricSummary exempted) {
        this.exempted = exempted;
    }
}
