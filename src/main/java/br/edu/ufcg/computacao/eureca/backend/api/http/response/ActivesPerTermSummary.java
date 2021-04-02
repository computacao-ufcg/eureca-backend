package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class ActivesPerTermSummary {
    private String admissionTerm;
    private RiskClassCountSummary riskClassCount;

    public ActivesPerTermSummary(String admissionTerm, RiskClassCountSummary riskClassCount) {
        this.admissionTerm = admissionTerm;
        this.riskClassCount = riskClassCount;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public RiskClassCountSummary getRiskClassCount() {
        return riskClassCount;
    }

    public void setRiskClassCount(RiskClassCountSummary riskClassCount) {
        this.riskClassCount = riskClassCount;
    }
}
