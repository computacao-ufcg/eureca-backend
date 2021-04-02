package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class ActivesSummary {
    private int activesCount;
    private MetricsSummary summary;

    public ActivesSummary(int activesCount, MetricsSummary summary) {
        this.activesCount = activesCount;
        this.summary = summary;
    }

    public int getActivesCount() {
        return activesCount;
    }

    public void setActivesCount(int activesCount) {
        this.activesCount = activesCount;
    }

    public MetricsSummary getSummary() {
        return summary;
    }

    public void setSummary(MetricsSummary summary) {
        this.summary = summary;
    }
}
