package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;

public class SubjectRetentionSummary {
    private MetricStatistics retentionStatistics;

    public SubjectRetentionSummary(MetricStatistics retentionStatistics) {
        this.retentionStatistics = retentionStatistics;
    }

    public MetricStatistics getRetentionStatistics() {
        return retentionStatistics;
    }

    public void setRetentionStatistics(MetricStatistics retentionStatistics) {
        this.retentionStatistics = retentionStatistics;
    }
}