package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutPerTermSummary implements Comparable {
    String term;
    DropoutReasonSummary reasons;

    public DropoutPerTermSummary(String term, DropoutReasonSummary reasons) {
        this.term = term;
        this.reasons = reasons;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public DropoutReasonSummary getReasons() {
        return reasons;
    }

    public void setReasons(DropoutReasonSummary reasons) {
        this.reasons = reasons;
    }

    @Override
    public int compareTo(Object o) {
        DropoutPerTermSummary other = (DropoutPerTermSummary) o;
        return this.getTerm().compareTo(other.getTerm());
    }
}
