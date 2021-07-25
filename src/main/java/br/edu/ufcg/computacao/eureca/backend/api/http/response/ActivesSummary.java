package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class ActivesSummary {
    private String from;
    private String to;
    private int activesCount;
    private MetricsSummary average;

    public ActivesSummary(String from, String to, int activesCount, MetricsSummary average) {
        this.from = from;
        this.to = to;
        this.activesCount = activesCount;
        this.average = average;
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

    public int getActivesCount() {
        return activesCount;
    }

    public void setActivesCount(int activesCount) {
        this.activesCount = activesCount;
    }

    public MetricsSummary getAverage() {
        return average;
    }

    public void setAverage(MetricsSummary average) {
        this.average = average;
    }
}
