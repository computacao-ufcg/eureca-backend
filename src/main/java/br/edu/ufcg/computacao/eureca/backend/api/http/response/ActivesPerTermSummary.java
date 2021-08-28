package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Term;

public class ActivesPerTermSummary implements Comparable, Term {
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

    @Override
    public int compareTo(Object o) {
        ActivesPerTermSummary other = (ActivesPerTermSummary) o;
        return this.getAdmissionTerm().compareTo(other.getAdmissionTerm());
    }

    @Override
    public String getTerm() {
        return this.admissionTerm;
    }
}
