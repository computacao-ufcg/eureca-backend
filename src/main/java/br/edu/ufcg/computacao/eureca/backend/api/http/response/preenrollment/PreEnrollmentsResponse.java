package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;

import java.util.Collection;

public class PreEnrollmentsResponse {

    private Collection<StudentPreEnrollmentResponse> activesPreEnrollment;
    private SubjectDemandSummary subjectDemandSummary;

    public PreEnrollmentsResponse(Collection<StudentPreEnrollmentResponse> activesPreEnrollment, SubjectDemandSummary subjectDemandSummary) {
        this.activesPreEnrollment = activesPreEnrollment;
        this.subjectDemandSummary = subjectDemandSummary;
    }

    public Collection<StudentPreEnrollmentResponse> getActivesPreEnrollment() {
        return activesPreEnrollment;
    }

    public void setActivesPreEnrollment(Collection<StudentPreEnrollmentResponse> activesPreEnrollment) {
        this.activesPreEnrollment = activesPreEnrollment;
    }

    public SubjectDemandSummary getSubjectDemandSummary() {
        return subjectDemandSummary;
    }

    public void setSubjectDemandSummary(SubjectDemandSummary subjectDemandSummary) {
        this.subjectDemandSummary = subjectDemandSummary;
    }

    @Override
    public String toString() {
        return "PreEnrollmentsResponse{" +
                "activesPreEnrollment=" + activesPreEnrollment +
                ", subjectDemandSummary=" + subjectDemandSummary +
                '}';
    }
}
