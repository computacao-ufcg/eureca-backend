package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Student;

import java.util.Collection;

public class ActiveSummaryResume {

    private int activesCount;
    private RiskClassCountSummary riskClassCount;
    private RiskClassPercentageSummary riskClassPercentage;

    public ActiveSummaryResume(int activesCount, RiskClassCountSummary riskClassCount, RiskClassPercentageSummary riskClassPercentage) {
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
