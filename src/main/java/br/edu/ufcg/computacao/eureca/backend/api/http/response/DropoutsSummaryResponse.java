package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class DropoutsSummaryResponse extends Summary {
    private Collection<DropoutPerTermSummary> dropoutPerTermSummaries;

    public DropoutsSummaryResponse(Collection<DropoutPerTermSummary> dropoutPerTermSummaries, String from, String to) {
        super(from, to);
        this.dropoutPerTermSummaries = dropoutPerTermSummaries;
    }

    public Collection<DropoutPerTermSummary> getDropoutPerTermSummaries() {
        return dropoutPerTermSummaries;
    }

    public void setDropoutPerTermSummaries(Collection<DropoutPerTermSummary> dropoutPerTermSummaries) {
        this.dropoutPerTermSummaries = dropoutPerTermSummaries;
    }
}
