package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;

public class SubjectsRetentionSummary {
    private MetricStatistics retentionStatistics;

    public SubjectsRetentionSummary(MetricStatistics retentionStatistics) {
        this.retentionStatistics = retentionStatistics;
    }

    public MetricStatistics getRetentionStatistics() {
        return retentionStatistics;
    }

    public void setRetentionStatistics(MetricStatistics retentionStatistics) {
        this.retentionStatistics = retentionStatistics;
    }
}
