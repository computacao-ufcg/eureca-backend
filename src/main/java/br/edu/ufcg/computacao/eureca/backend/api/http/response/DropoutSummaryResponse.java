package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutSummaryResponse implements Comparable {
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

    @Override
    public int compareTo(Object o) {
        DropoutSummaryResponse other = (DropoutSummaryResponse) o;
        return this.getTerm().compareTo(other.getTerm());
    }
}
