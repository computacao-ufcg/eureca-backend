package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class TeacherStatisticsPerTerm implements Comparable, SummaryPerTerm {
    private String term;
    private TeacherStatisticsSummary termSummary;

    public TeacherStatisticsPerTerm(String term, TeacherStatisticsSummary termSummary) {
        this.term = term;
        this.termSummary = termSummary;
    }

    @Override
    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public TeacherStatisticsSummary getTermSummary() {
        return termSummary;
    }

    public void setTermSummary(TeacherStatisticsSummary termSummary) {
        this.termSummary = termSummary;
    }

    @Override
    public int compareTo(Object o) {
        TeacherStatisticsPerTerm other = (TeacherStatisticsPerTerm) o;
        return this.getTerm().compareTo(other.getTerm());
    }
}
