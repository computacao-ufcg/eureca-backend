package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class DelayedStatisticsResponse extends Range {
    private Collection<DelayedPerTermSummary> terms;

    public DelayedStatisticsResponse(Collection<DelayedPerTermSummary> terms, String from, String to) {
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
