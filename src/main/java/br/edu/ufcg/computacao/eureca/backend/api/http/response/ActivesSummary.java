package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class ActivesSummary {
    private int activesCount;
    private RiskClassCountSummary riskClassCount;

    public ActivesSummary(int activesCount, RiskClassCountSummary riskClassCount) {
        this.activesCount = activesCount;
        this.riskClassCount = riskClassCount;
    }

    public int getActivesCount() {
        return activesCount;
    }

    public void setActivesCount(int activesCount) {
        this.activesCount = activesCount;
    }

    public RiskClassCountSummary getRiskClassCount() {
        return riskClassCount;
    }

    public void setRiskClassCount(RiskClassCountSummary riskClassCount) {
        this.riskClassCount = riskClassCount;
    }

    public void add(ActivesSummary other) {
        this.activesCount += other.getActivesCount();
        this.riskClassCount.add(other.getRiskClassCount());
    }
}
