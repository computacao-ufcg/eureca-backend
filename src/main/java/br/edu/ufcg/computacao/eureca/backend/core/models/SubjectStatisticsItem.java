package br.edu.ufcg.computacao.eureca.backend.core.models;

public class SubjectStatisticsItem {
    private int disciplines;
    private MetricSummary failedDueToAbsences;
    private MetricSummary failedDueToGrade;
    private MetricSummary failedDueToCanceling;
    private MetricSummary success;
    private MetricSummary relativeRetention;
    private MetricSummary absoluteRetention;

    public SubjectStatisticsItem(int disciplines, MetricSummary failedDueToAbsences,
                                 MetricSummary failedDueToGrade, MetricSummary failedDueToCanceling, MetricSummary success, MetricSummary relativeRetention,
                                 MetricSummary absoluteRetention) {
        this.disciplines = disciplines;
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.failedDueToCanceling = failedDueToCanceling;
        this.success = success;
        this.relativeRetention = relativeRetention;
        this.absoluteRetention = absoluteRetention;
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

    public MetricSummary getSuccess() {
        return success;
    }

    public void setSuccess(MetricSummary success) {
        this.success = success;
    }

    public MetricSummary getRelativeRetention() {
        return relativeRetention;
    }

    public void setRelativeRetention(MetricSummary relativeRetention) {
        this.relativeRetention = relativeRetention;
    }

    public MetricSummary getAbsoluteRetention() {
        return absoluteRetention;
    }

    public void setAbsoluteRetention(MetricSummary absoluteRetention) {
        this.absoluteRetention = absoluteRetention;
    }
}
