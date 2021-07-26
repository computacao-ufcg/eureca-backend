package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectMetricsPerTermSummary {
    private String code;
    private String name;
    private Collection<SubjectMetricsPerTerm> terms;

    public SubjectMetricsPerTermSummary(String code, String name, Collection<SubjectMetricsPerTerm> terms) {
        this.code = code;
        this.name = name;
        this.terms = terms;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SubjectMetricsPerTerm> getTerms() {
        return terms;
    }

    public void setTerms(Collection<SubjectMetricsPerTerm> terms) {
        this.terms = terms;
    }
}
