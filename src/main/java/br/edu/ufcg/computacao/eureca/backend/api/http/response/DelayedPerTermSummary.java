package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DelayedPerTermSummary implements Comparable, SummaryPerTerm {
    private String admissionTerm;
    private StudentMetricsSummary metricsSummary;

    public DelayedPerTermSummary(String admissionTerm, StudentMetricsSummary metricsSummary) {
        this.admissionTerm = admissionTerm;
        this.metricsSummary = metricsSummary;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public StudentMetricsSummary getMetricsSummary() {
        return metricsSummary;
    }

    public void setMetricsSummary(StudentMetricsSummary metricsSummary) {
        this.metricsSummary = metricsSummary;
    }

    @Override
    public int compareTo(Object o) {
        DelayedPerTermSummary other = (DelayedPerTermSummary) o;
        return this.getAdmissionTerm().compareTo(other.getAdmissionTerm());
    }

    @Override
    public String getTerm() {
        return this.admissionTerm;
    }
}
