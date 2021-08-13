package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DelayedSummary {
    private String from;
    private String to;
    private int delayedCount;
    private StudentMetricsSummary average;

    public DelayedSummary(String from, String to, int delayedCount, StudentMetricsSummary average) {
        this.from = from;
        this.to = to;
        this.delayedCount = delayedCount;
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

    public int getDelayedCount() {
        return delayedCount;
    }

    public void setDelayedCount(int delayedCount) {
        this.delayedCount = delayedCount;
    }

    public StudentMetricsSummary getAverage() {
        return average;
    }

    public void setAverage(StudentMetricsSummary average) {
        this.average = average;
    }
}
