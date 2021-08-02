package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class AlumniStatisticsResponse extends RangeSummary {
    private Collection<AlumniPerTermSummary> alumniPerTermSummaries;

    public AlumniStatisticsResponse(Collection<AlumniPerTermSummary> alumniPerTermSummaries, String from, String to) {
        super(from, to);
        this.alumniPerTermSummaries = alumniPerTermSummaries;
    }

    public Collection<AlumniPerTermSummary> getAlumniPerTermSummaries() {
        return alumniPerTermSummaries;
    }

    public void setAlumniPerTermSummaries(Collection<AlumniPerTermSummary> alumniPerTermSummaries) {
        this.alumniPerTermSummaries = alumniPerTermSummaries;
    }
}
