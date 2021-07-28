package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectRetentionResponse {
    private Collection<SubjectRetentionCSV> subjectRetention;

    public SubjectRetentionResponse(Collection<SubjectRetentionCSV> subjectRetention) {
        this.subjectRetention = subjectRetention;
    }

    public Collection<SubjectRetentionCSV> getSubjectRetention() {
        return subjectRetention;
    }

    public void setSubjectRetention(Collection<SubjectRetentionCSV> subjectRetention) {
        this.subjectRetention = subjectRetention;
    }
}
