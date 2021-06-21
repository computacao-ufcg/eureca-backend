package br.edu.ufcg.computacao.eureca.backend.core.models;

public class SubjectStatisticsItem {
    private int disciplines;
    private MetricSummary failedDueToAbsences;
    private MetricSummary failedDueToGrade;
    private MetricSummary failedDueToCanceling;
    private MetricSummary succeeded;
    private MetricSummary exempted;
    private MetricSummary retention;

    public SubjectStatisticsItem(int disciplines, MetricSummary failedDueToAbsences,
                                 MetricSummary failedDueToGrade, MetricSummary failedDueToCanceling, MetricSummary succeeded, MetricSummary exempted,
                                 MetricSummary retention) {
        this.disciplines = disciplines;
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.failedDueToCanceling = failedDueToCanceling;
        this.succeeded = succeeded;
        this.exempted = exempted;
        this.retention = retention;
    }

    public int getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(int disciplines) {
        this.disciplines = disciplines;
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

    public MetricSummary getFailedDueToCanceling() {
        return failedDueToCanceling;
    }

    public void setFailedDueToCanceling(MetricSummary failedDueToCanceling) {
        this.failedDueToCanceling = failedDueToCanceling;
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

    public MetricSummary getRetention() {
        return retention;
    }

    public void setRetention(MetricSummary retention) {
        this.retention = retention;
    }
}
