package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class TeachersPerAcademicUnitPerTermSummary implements Comparable {
    private String auCode;
    private String auAcronym;
    private String auName;
    Collection<TeachersPerAcademicUnitPerTerm> terms;

    @Override
    public int compareTo(Object o) {
        TeachersPerAcademicUnitPerTermSummary other = (TeachersPerAcademicUnitPerTermSummary) o;
        return this.auCode.compareTo(other.auCode);
    }
}
