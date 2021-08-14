package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class StudentsRetentionStatisticsResponse extends Range {
    private Collection<StudentsRetentionPerTermSummary> terms;

    public StudentsRetentionStatisticsResponse(Collection<StudentsRetentionPerTermSummary> terms, String from, String to) {
        super(from, to);
        this.terms = terms;
    }

    public Collection<StudentsRetentionPerTermSummary> getTerms() {
        return terms;
    }

    public void setTerms(Collection<StudentsRetentionPerTermSummary> terms) {
        this.terms = terms;
    }
}
