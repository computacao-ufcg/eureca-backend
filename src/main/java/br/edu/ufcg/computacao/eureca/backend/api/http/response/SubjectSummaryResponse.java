package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectSummaryResponse extends RangeSummary {
    private Collection<SubjectMetricsSummary> subjects;

    public SubjectSummaryResponse(Collection<SubjectMetricsSummary> subjects, String from, String to) {
        super(from, to);
        this.subjects = subjects;
    }

    public Collection<SubjectMetricsSummary> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<SubjectMetricsSummary> subjects) {
        this.subjects = subjects;
    }
}
