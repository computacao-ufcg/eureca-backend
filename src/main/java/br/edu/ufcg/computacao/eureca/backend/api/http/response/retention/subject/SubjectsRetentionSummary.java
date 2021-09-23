package br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;

public class SubjectsRetentionSummary {
    private String from;
    private String to;
    private MetricStatistics adequate;
    private MetricStatistics possible;

    public SubjectsRetentionSummary(String from, String to, MetricStatistics adequate, MetricStatistics possible) {
        this.from = from;
        this.to = to;
        this.adequate = adequate;
        this.possible = possible;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MetricStatistics getAdequate() {
        return adequate;
    }

    public void setAdequate(MetricStatistics adequate) {
        this.adequate = adequate;
    }

    public MetricStatistics getPossible() {
        return possible;
    }

    public void setPossible(MetricStatistics possible) {
        this.possible = possible;
    }
}
