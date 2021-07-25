package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectMetricsPerTerm implements SummaryPerTerm {
    private String term;
    private SubjectMetrics metrics;

    public SubjectMetricsPerTerm(String term, SubjectMetrics metrics) {
        this.term = term;
        this.metrics = metrics;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public SubjectMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(SubjectMetrics metrics) {
        this.metrics = metrics;
    }
}
