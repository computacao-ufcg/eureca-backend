package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectRetentionStatisticsResponse {
    private Collection<SubjectRetentionDigest> subjectRetentionSummary;

    public SubjectRetentionStatisticsResponse(Collection<SubjectRetentionDigest> subjectRetentionSummary) {
        this.subjectRetentionSummary = subjectRetentionSummary;
    }

    public Collection<SubjectRetentionDigest> getSubjectRetentionSummary() {
        return subjectRetentionSummary;
    }

    public void setSubjectRetentionSummary(Collection<SubjectRetentionDigest> subjectRetentionSummary) {
        this.subjectRetentionSummary = subjectRetentionSummary;
    }
}
