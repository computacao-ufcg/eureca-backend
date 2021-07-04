package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectsStatisticsSummary;

public class SubjectPerTermSummary implements Comparable {
    private String term;
    private SubjectsStatisticsSummary summary;

    public SubjectPerTermSummary(String term, SubjectsStatisticsSummary summary) {
        this.term = term;
        this.summary = summary;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public SubjectsStatisticsSummary getSummary() {
        return summary;
    }

    public void setSummary(SubjectsStatisticsSummary summary) {
        this.summary = summary;
    }

    @Override
    public int compareTo(Object o) {
        SubjectPerTermSummary other = (SubjectPerTermSummary) o;
        return this.getTerm().compareTo(other.getTerm());
    }
}
