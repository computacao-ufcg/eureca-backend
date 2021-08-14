package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class TeachersPerAcademicUnitPerTerm implements Comparable, SummaryPerTerm {
    private String term;

    @Override
    public String getTerm() {
        return this.term;
    }

    @Override
    public int compareTo(Object o) {
        TeachersPerAcademicUnitPerTerm other = (TeachersPerAcademicUnitPerTerm) o;
        return this.getTerm().compareTo(other.getTerm());
    }
}
