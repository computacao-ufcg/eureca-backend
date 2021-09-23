package br.edu.ufcg.computacao.eureca.backend.api.http.response.active;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentPreEnrollment;

import java.util.Collection;

public class ActivesPreEnrollmentResponse {

    private Collection<StudentPreEnrollment> activesPreEnrollment;
    private SubjectDemandSummary subjectDemandSummary;

    public ActivesPreEnrollmentResponse(Collection<StudentPreEnrollment> activesPreEnrollment, SubjectDemandSummary subjectDemandSummary) {
        this.activesPreEnrollment = activesPreEnrollment;
        this.subjectDemandSummary = subjectDemandSummary;
    }

    public Collection<StudentPreEnrollment> getActivesPreEnrollment() {
        return activesPreEnrollment;
    }

    public void setActivesPreEnrollment(Collection<StudentPreEnrollment> activesPreEnrollment) {
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
        return "ActivesPreEnrollmentResponse{" +
                "activesPreEnrollment=" + activesPreEnrollment +
                ", subjectDemandSummary=" + subjectDemandSummary +
                '}';
    }
}
