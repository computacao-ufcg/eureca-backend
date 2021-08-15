package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectsRetentionStatisticsResponse extends Range {
    private Collection<SubjectRetentionPerAdmissionTermSummary> subjectRetentionSummary;

    public SubjectsRetentionStatisticsResponse(Collection<SubjectRetentionPerAdmissionTermSummary> subjectRetentionSummary, String from, String to) {
        super(from, to);
        this.subjectRetentionSummary = subjectRetentionSummary;
    }

    public Collection<SubjectRetentionPerAdmissionTermSummary> getSubjectRetentionSummary() {
        return subjectRetentionSummary;
    }

    public void setSubjectRetentionSummary(Collection<SubjectRetentionPerAdmissionTermSummary> subjectRetentionSummary) {
        this.subjectRetentionSummary = subjectRetentionSummary;
    }
}
