package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.CostClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;

public class MetricsSummary {
    private double termsCount;
    private Metrics metrics;
    private RiskClass riskClass;
    private CostClass costClass;

    public MetricsSummary(double termsCount, Metrics metrics) {
        this.termsCount = termsCount;
        this.metrics = metrics;
        this.riskClass = MetricsCalculator.computeRiskClass(metrics.getRisk());
        this.costClass = MetricsCalculator.computeCostClass(metrics.getCost());
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

    public CostClass getCostClass() {
        return costClass;
    }

    public void setCostClass(CostClass costClass) {
        this.costClass = costClass;
    }
}
