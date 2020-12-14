package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class RiskSummary {
    int activesCount;
    RiskClassCountSummary riskClassCount;
    RiskClassPercentageSummary riskClassPercentage;

    public RiskSummary(int activesCount, RiskClassCountSummary riskClassCount, RiskClassPercentageSummary riskClassPercentage) {
        this.activesCount = activesCount;
        this.riskClassCount = riskClassCount;
        this.riskClassPercentage = riskClassPercentage;
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

    public RiskClassPercentageSummary getRiskClassPercentage() {
        return riskClassPercentage;
    }

    public void setRiskClassPercentage(RiskClassPercentageSummary riskClassPercentage) {
        this.riskClassPercentage = riskClassPercentage;
    }
}
