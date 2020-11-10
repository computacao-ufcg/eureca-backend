package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutSummaryResponse {
    String term;
    DropoutClassification reasons;

    public DropoutSummaryResponse(String term, DropoutClassification reasons) {
        this.term = term;
        this.reasons = reasons;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public DropoutClassification getReasons() {
        return reasons;
    }

    public void setReasons(DropoutClassification reasons) {
        this.reasons = reasons;
    }
}
