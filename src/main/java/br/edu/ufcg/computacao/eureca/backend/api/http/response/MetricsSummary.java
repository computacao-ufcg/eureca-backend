package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;

public class MetricsSummary {
    private double termsCount;
    private Metrics metrics;
    private RiskClass riskClass;

    public MetricsSummary(double termsCount, Metrics metrics) {
        this.termsCount = termsCount;
        this.metrics = metrics;
        this.riskClass = metrics.computeRiskClass();
    }

    public double getTermsCount() {
        return termsCount;
    }

    public void setTermsCount(double termsCount) {
        this.termsCount = termsCount;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public RiskClass getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(RiskClass riskClass) {
        this.riskClass = riskClass;
    }
}
