package br.edu.ufcg.computacao.eureca.backend.api.http.response.subject;

import br.edu.ufcg.computacao.eureca.backend.core.models.Term;

public class SubjectMetricsPerTerm implements Comparable, Term {
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

    @Override
    public int compareTo(Object o) {
        SubjectMetricsPerTerm other = (SubjectMetricsPerTerm) o;
        return term.compareTo(other.getTerm());
    }
}
