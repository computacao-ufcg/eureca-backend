package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class ActivesPerTermSummary {

    String admissionTerm;
    ActivesSummary summary;

    public ActivesPerTermSummary(String admissionTerm, ActivesSummary summary) {
        this.admissionTerm = admissionTerm;
        this.summary = summary;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public ActivesSummary getSummary() {
        return summary;
    }

    public void setSummary(ActivesSummary summary) {
        this.summary = summary;
    }
}
