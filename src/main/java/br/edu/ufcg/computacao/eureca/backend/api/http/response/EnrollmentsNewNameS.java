package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class EnrollmentsNewNameS implements Comparable, SummaryPerTerm {
    private String term;
    private EnrollmentsStatisticsSummary termSummary;
    private Collection<EnrollmentsPerSubjectSummary> perSubjectSummary;

    public EnrollmentsNewNameS(String term, EnrollmentsStatisticsSummary termSummary,
                               Collection<EnrollmentsPerSubjectSummary> perSubjectSummary) {
        this.term = term;
        this.termSummary
                = termSummary;
        this.perSubjectSummary = perSubjectSummary;
    }

    @Override
    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public EnrollmentsStatisticsSummary getTermSummary() {
        return termSummary;
    }

    public void setTermSummary(EnrollmentsStatisticsSummary termSummary) {
        this.termSummary = termSummary;
    }

    public Collection<EnrollmentsPerSubjectSummary> getPerSubjectSummary() {
        return perSubjectSummary;
    }

    public void setPerSubjectSummary(Collection<EnrollmentsPerSubjectSummary> perSubjectSummary) {
        this.perSubjectSummary = perSubjectSummary;
    }

    @Override
    public int compareTo(Object o) {
        EnrollmentsNewNameS other = (EnrollmentsNewNameS) o;
        return this.getTerm().compareTo(other.getTerm());
    }
}
