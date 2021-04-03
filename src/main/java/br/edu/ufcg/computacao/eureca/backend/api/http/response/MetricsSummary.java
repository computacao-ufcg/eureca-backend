package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;

public class MetricsSummary {
    private double averageTermsCount;
    private Metrics averageMetrics;
    private RiskClass averageRiskClass;

    public MetricsSummary(double averageTermsCount, Metrics metrics) {
        this.averageTermsCount = averageTermsCount;
        this.averageMetrics = metrics;
        this.averageRiskClass = metrics.computeRiskClass();
    }

    public double getAverageTermsCount() {
        return averageTermsCount;
    }

    public void setAverageTermsCount(double averageTermsCount) {
        this.averageTermsCount = averageTermsCount;
    }

    public Metrics getAverageMetrics() {
        return averageMetrics;
    }

    public void setAverageMetrics(Metrics averageMetrics) {
        this.averageMetrics = averageMetrics;
    }

    public RiskClass getAverageRiskClass() {
        return averageRiskClass;
    }

    public void setAverageRiskClass(RiskClass averageRiskClass) {
        this.averageRiskClass = averageRiskClass;
    }
}
