package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DelayedPerTermSummary implements Comparable {
    private String admissionTerm;
    private MetricsSummary metricsSummary;

    public DelayedPerTermSummary(String admissionTerm, MetricsSummary metricsSummary) {
        this.admissionTerm = admissionTerm;
        this.metricsSummary = metricsSummary;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public MetricsSummary getMetricsSummary() {
        return metricsSummary;
    }

    public void setMetricsSummary(MetricsSummary metricsSummary) {
        this.metricsSummary = metricsSummary;
    }

    @Override
    public int compareTo(Object o) {
        DelayedPerTermSummary other = (DelayedPerTermSummary) o;
        return this.getAdmissionTerm().compareTo(other.getAdmissionTerm());
    }
}
