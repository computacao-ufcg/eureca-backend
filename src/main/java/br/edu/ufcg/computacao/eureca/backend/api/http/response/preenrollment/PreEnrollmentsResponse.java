package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;

import java.util.Collection;
import java.util.TreeSet;

public class PreEnrollmentsResponse {

    private Collection<StudentPreEnrollmentResponse> activesPreEnrollment;
    private SubjectDemandSummary subjectDemandSummary;

    public PreEnrollmentsResponse(Collection<StudentPreEnrollment> activesPreEnrollment, SubjectDemandSummary subjectDemandSummary) {
        this.activesPreEnrollment = new TreeSet<>();
        activesPreEnrollment.forEach(studentPreEnrollment -> {
            StudentPreEnrollmentResponse response = new StudentPreEnrollmentResponse(studentPreEnrollment);
            this.activesPreEnrollment.add(response);
        });
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
        return "PreEnrollments{" +
                "activesPreEnrollment=" + activesPreEnrollment +
                ", subjectDemandSummary=" + subjectDemandSummary +
                '}';
    }
}
