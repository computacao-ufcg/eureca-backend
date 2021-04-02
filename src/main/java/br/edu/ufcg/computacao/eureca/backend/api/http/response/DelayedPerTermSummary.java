package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DelayedPerTermSummary {
    private String admissionTerm;
    private RiskClassCountSummary riskCount;

    public DelayedPerTermSummary(String admissionTerm, RiskClassCountSummary riskCount) {
        this.admissionTerm = admissionTerm;
        this.riskCount = riskCount;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public RiskClassCountSummary getRiskCount() {
        return riskCount;
    }

    public void setRiskCount(RiskClassCountSummary riskCount) {
        this.riskCount = riskCount;
    }
}
