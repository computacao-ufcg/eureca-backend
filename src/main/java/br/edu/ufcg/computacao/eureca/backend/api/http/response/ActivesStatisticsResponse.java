package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class ActivesStatisticsResponse extends RangeSummary {
    private Collection<ActivesPerTermSummary> activesPerTermSummaries;

    public ActivesStatisticsResponse(Collection<ActivesPerTermSummary> activesPerTermSummaries, String from, String to) {
        super(from, to);
        this.activesPerTermSummaries = activesPerTermSummaries;
    }

    public Collection<ActivesPerTermSummary> getActivesPerTermSummaries() {
        return activesPerTermSummaries;
    }

    public void setActivesPerTermSummaries(Collection<ActivesPerTermSummary> activesPerTermSummaries) {
        this.activesPerTermSummaries = activesPerTermSummaries;
    }
}
