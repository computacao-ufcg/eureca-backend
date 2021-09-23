package br.edu.ufcg.computacao.eureca.backend.api.http.response.subject;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;

public class SubjectMetricsStatistics {
    private MetricStatistics failedDueToAbsences;
    private MetricStatistics failedDueToGrade;
    private MetricStatistics cancelled;
    private MetricStatistics succeeded;
    private MetricStatistics ongoing;
    private MetricStatistics exempted;
    private MetricStatistics suspended;
    private MetricStatistics totalEnrolled;
    private MetricStatistics numberOfClasses;
    private MetricStatistics averageEnrollmentsPerClass;

    public SubjectMetricsStatistics(MetricStatistics failedDueToAbsences, MetricStatistics failedDueToGrade,
                                    MetricStatistics cancelled, MetricStatistics succeeded, MetricStatistics ongoing,
                                    MetricStatistics exempted, MetricStatistics suspended,
                                    MetricStatistics totalEnrolled, MetricStatistics numberOfClasses,
                                    MetricStatistics averageEnrollmentsPerClass) {
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.cancelled = cancelled;
        this.succeeded = succeeded;
        this.ongoing = ongoing;
        this.exempted = exempted;
        this.suspended = suspended;
        this.totalEnrolled = totalEnrolled;
        this.numberOfClasses = numberOfClasses;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }

    public MetricStatistics getFailedDueToAbsences() {
        return failedDueToAbsences;
    }

    public void setFailedDueToAbsences(MetricStatistics failedDueToAbsences) {
        this.failedDueToAbsences = failedDueToAbsences;
    }

    public MetricStatistics getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public void setFailedDueToGrade(MetricStatistics failedDueToGrade) {
        this.failedDueToGrade = failedDueToGrade;
    }

    public MetricStatistics getCancelled() {
        return cancelled;
    }

    public void setCancelled(MetricStatistics cancelled) {
        this.cancelled = cancelled;
    }

    public MetricStatistics getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(MetricStatistics succeeded) {
        this.succeeded = succeeded;
    }

    public MetricStatistics getOngoing() {
        return ongoing;
    }

    public void setOngoing(MetricStatistics ongoing) {
        this.ongoing = ongoing;
    }

    public MetricStatistics getExempted() {
        return exempted;
    }

    public void setExempted(MetricStatistics exempted) {
        this.exempted = exempted;
    }

    public MetricStatistics getSuspended() {
        return suspended;
    }

    public void setSuspended(MetricStatistics suspended) {
        this.suspended = suspended;
    }

    public MetricStatistics getTotalEnrolled() {
        return totalEnrolled;
    }

    public void setTotalEnrolled(MetricStatistics totalEnrolled) {
        this.totalEnrolled = totalEnrolled;
    }

    public MetricStatistics getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(MetricStatistics numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public MetricStatistics getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public void setAverageEnrollmentsPerClass(MetricStatistics averageEnrollmentsPerClass) {
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }
}
