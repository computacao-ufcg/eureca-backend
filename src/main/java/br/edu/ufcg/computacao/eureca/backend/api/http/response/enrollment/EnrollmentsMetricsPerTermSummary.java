package br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment;

import br.edu.ufcg.computacao.eureca.backend.core.models.Range;

import java.util.Collection;

public class EnrollmentsMetricsPerTermSummary extends Range implements Comparable {
    private String subjectCode;
    private String subjectName;
    private Collection<EnrollmentsMetricsPerTerm> terms;

    public EnrollmentsMetricsPerTermSummary(String from, String to, String subjectCode, String subjectName,
                                            Collection<EnrollmentsMetricsPerTerm> terms) {
        super(from, to);
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

    public Collection<EnrollmentsMetricsPerTerm> getTerms() {
        return terms;
    }

    public void setTerms(Collection<EnrollmentsMetricsPerTerm> terms) {
        this.terms = terms;
    }

    @Override
    public int compareTo(Object o) {
        EnrollmentsMetricsPerTermSummary other = (EnrollmentsMetricsPerTermSummary) o;
        return this.getSubjectCode().compareTo(other.getSubjectCode());
    }

    @Override
    public String toString() {
        return "EnrollmentsMetricsPerTermSummary{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", terms=" + terms +
                '}';
    }
}
