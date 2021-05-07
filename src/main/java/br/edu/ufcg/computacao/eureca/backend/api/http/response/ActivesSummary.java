package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class ActivesSummary {
    private int activesCount;
    private MetricsSummary average;

    public ActivesSummary(int activesCount, MetricsSummary average) {
        this.activesCount = activesCount;
        this.average = average;
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
