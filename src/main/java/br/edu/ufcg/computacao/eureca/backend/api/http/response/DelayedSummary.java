package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DelayedSummary extends RangeSummary {
    private int delayedCount;
    private MetricsSummary average;

    public DelayedSummary(int delayedCount, MetricsSummary average, String from, String to) {
        super(from, to);
        this.delayedCount = delayedCount;
        this.average = average;
    }

    public int getDelayedCount() {
        return delayedCount;
    }

    public void setDelayedCount(int delayedCount) {
        this.delayedCount = delayedCount;
    }

    public MetricsSummary getAverage() {
        return average;
    }

    public void setAverage(MetricsSummary average) {
        this.average = average;
    }
}
