package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Range;

import java.util.Collection;

public class SubjectMetricsPerTermSummary extends Range implements Comparable {
    private String subjectCode;
    private String subjectName;
    private Collection<SubjectMetricsPerTerm> terms;

    public SubjectMetricsPerTermSummary(String from, String to, String subjectCode, String subjectName,
                                        Collection<SubjectMetricsPerTerm> terms) {
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

    public Collection<SubjectMetricsPerTerm> getTerms() {
        return terms;
    }

    public void setTerms(Collection<SubjectMetricsPerTerm> terms) {
        this.terms = terms;
    }

    @Override
    public int compareTo(Object o) {
        SubjectMetricsPerTermSummary other = (SubjectMetricsPerTermSummary) o;
        return subjectName.compareTo(other.getSubjectName());
    }
}
