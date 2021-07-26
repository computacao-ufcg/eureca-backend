package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectSummaryResponse extends RangeSummary {
    private Collection<SubjectMetricsPerTermSummary> subjects;

    public SubjectSummaryResponse(Collection<SubjectMetricsPerTermSummary> subjects, String from, String to) {
        super(from, to);
        this.subjects = subjects;
    }

    public Collection<SubjectMetricsPerTermSummary> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<SubjectMetricsPerTermSummary> subjects) {
        this.subjects = subjects;
    }
}
