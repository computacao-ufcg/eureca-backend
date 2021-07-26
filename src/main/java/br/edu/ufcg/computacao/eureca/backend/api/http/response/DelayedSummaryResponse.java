package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class DelayedSummaryResponse extends RangeSummary {
    private Collection<DelayedPerTermSummary> terms;

    public DelayedSummaryResponse(Collection<DelayedPerTermSummary> terms, String from, String to) {
        super(from, to);
        this.terms = terms;
    }

    public Collection<DelayedPerTermSummary> getTerms() {
        return terms;
    }

    public void setTerms(Collection<DelayedPerTermSummary> terms) {
        this.terms = terms;
    }
}
