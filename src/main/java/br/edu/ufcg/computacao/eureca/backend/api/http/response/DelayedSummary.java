package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DelayedSummary extends RangeSummary {
    private int delayedCount;
    private StudentMetricsSummary average;

    public DelayedSummary(int delayedCount, StudentMetricsSummary average, String from, String to) {
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

    public StudentMetricsSummary getAverage() {
        return average;
    }

    public void setAverage(StudentMetricsSummary average) {
        this.average = average;
    }
}
