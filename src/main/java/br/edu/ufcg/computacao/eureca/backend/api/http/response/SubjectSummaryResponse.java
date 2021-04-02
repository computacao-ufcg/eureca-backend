package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectSummaryResponse {
    private String term;
    private String code;
    private String name;
    private Collection<EnrollmentSummary> enrollments;
    private EnrollmentSummary summary;

    public SubjectSummaryResponse() {
    }

    public SubjectSummaryResponse(String term, String code, String name, Collection<EnrollmentSummary> enrollments) {
        this.term = term;
        this.code = code;
        this.name = name;
        this.enrollments = enrollments;
        this.summary = computeSummary();
    }

    private EnrollmentSummary computeSummary() {
        int passed = this.enrollments.stream().mapToInt(EnrollmentSummary::getPassed).sum();
        int failed = this.enrollments.stream().mapToInt(EnrollmentSummary::getFailed).sum();
        int absent = this.enrollments.stream().mapToInt(EnrollmentSummary::getAbsent).sum();
        int dropout = this.enrollments.stream().mapToInt(EnrollmentSummary::getDropout).sum();
        EnrollmentSummary summary = new EnrollmentSummary(passed, failed, absent, dropout);
        return summary;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
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

    public Collection<EnrollmentSummary> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Collection<EnrollmentSummary> enrollments) {
        this.enrollments = enrollments;
    }

    public EnrollmentSummary getSummary() {
        return summary;
    }

    public void setSummary(EnrollmentSummary summary) {
        this.summary = summary;
    }
}
