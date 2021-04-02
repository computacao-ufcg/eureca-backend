package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DelayedSummary  {
    private int delayedCount;
    private MetricsSummary summary;

    public DelayedSummary(int delayedCount, MetricsSummary summary) {
        this.delayedCount = delayedCount;
        this.summary = summary;
    }

    public int getDelayedCount() {
        return delayedCount;
    }

    public void setDelayedCount(int delayedCount) {
        this.delayedCount = delayedCount;
    }

    public MetricsSummary getSummary() {
        return summary;
    }

    public void setSummary(MetricsSummary summary) {
        this.summary = summary;
    }
}
