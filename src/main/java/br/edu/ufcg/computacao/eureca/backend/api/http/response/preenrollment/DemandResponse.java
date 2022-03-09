package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;

public class DemandResponse {

    private SubjectDemandSummary subjectDemandSummary;

    public DemandResponse(SubjectDemandSummary subjectDemandSummary) {
        this.subjectDemandSummary = subjectDemandSummary;
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
                ", subjectDemandSummary=" + subjectDemandSummary +
                '}';
    }
}
