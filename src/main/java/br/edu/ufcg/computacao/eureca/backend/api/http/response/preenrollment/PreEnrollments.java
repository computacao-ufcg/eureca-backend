package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;

import java.util.Collection;

public class PreEnrollments {

    private Collection<StudentPreEnrollment> activesPreEnrollment;
    private SubjectDemandSummary subjectDemandSummary;

    public PreEnrollments(Collection<StudentPreEnrollment> activesPreEnrollment, SubjectDemandSummary subjectDemandSummary) {
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
        return "PreEnrollments{" +
                "activesPreEnrollment=" + activesPreEnrollment +
                ", subjectDemandSummary=" + subjectDemandSummary +
                '}';
    }
}
