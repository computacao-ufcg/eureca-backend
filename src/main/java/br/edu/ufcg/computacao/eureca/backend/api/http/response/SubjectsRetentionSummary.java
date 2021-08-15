package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;

public class SubjectsRetentionSummary {
    private MetricStatistics adequate;
    private MetricStatistics possible;

    public SubjectsRetentionSummary(MetricStatistics adequate, MetricStatistics possible) {
        this.adequate = adequate;
        this.possible = possible;
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
