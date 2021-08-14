package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectMetricsPerTermSummary implements Comparable {
    private String subjectCode;
    private String subjectName;
    private Collection<SubjectMetricsPerTerm> terms;

    public SubjectMetricsPerTermSummary(String subjectCode, String subjectName, Collection<SubjectMetricsPerTerm> terms) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.terms = terms;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Collection<SubjectMetricsPerTerm> getTerms() {
        return terms;
    }

    public void setTerms(Collection<SubjectMetricsPerTerm> terms) {
        this.terms = terms;
    }

    @Override
    public int compareTo(Object o) {
        SubjectMetricsPerTermSummary other = (SubjectMetricsPerTermSummary) o;
        return subjectCode.compareTo(other.getSubjectCode());
    }
}
