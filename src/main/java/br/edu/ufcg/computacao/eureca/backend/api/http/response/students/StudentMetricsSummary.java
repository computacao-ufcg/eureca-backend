package br.edu.ufcg.computacao.eureca.backend.api.http.response.students;

import br.edu.ufcg.computacao.eureca.backend.core.models.CostClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;

public class StudentMetricsSummary {
    private double termsCount;
    private StudentMetrics metrics;
    private RiskClass riskClass;
    private CostClass costClass;

    public StudentMetricsSummary(double termsCount, StudentMetrics metrics, double averageLowestRisk, double averageCostIncrement) {
        this.termsCount = termsCount;
        this.metrics = metrics;
        this.riskClass = StudentMetricsCalculator.computeRiskClass(metrics.getRisk(), averageLowestRisk);
        this.costClass = StudentMetricsCalculator.computeCostClass(metrics.getCost(), averageCostIncrement);
    }

    public double getTermsCount() {
        return termsCount;
    }

    public void setTermsCount(double termsCount) {
        this.termsCount = termsCount;
    }

    public StudentMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(StudentMetrics metrics) {
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
