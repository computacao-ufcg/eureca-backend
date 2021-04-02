package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutPerTermSummary implements Comparable {
    private String dropoutTerm;
    private DropoutReasonSummary reasons;

    public DropoutPerTermSummary(String dropoutTerm, DropoutReasonSummary reasons) {
        this.dropoutTerm = dropoutTerm;
        this.reasons = reasons;
    }

    public String getDropoutTerm() {
        return dropoutTerm;
    }

    public void setDropoutTerm(String dropoutTerm) {
        this.dropoutTerm = dropoutTerm;
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
        return this.getDropoutTerm().compareTo(other.getDropoutTerm());
    }
}
