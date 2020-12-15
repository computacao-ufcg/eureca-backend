package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutPerTermSummary implements Comparable {
    String term;
    DropoutClassification reasons;

    public DropoutPerTermSummary(String term, DropoutClassification reasons) {
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
        DropoutPerTermSummary other = (DropoutPerTermSummary) o;
        return this.getTerm().compareTo(other.getTerm());
    }
}
