package br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject;

import java.util.Collection;

public class SubjectsRetentionResponse {
    private Collection<SubjectRetentionCSV> subjectRetention;

    public SubjectsRetentionResponse(Collection<SubjectRetentionCSV> subjectRetention) {
        this.subjectRetention = subjectRetention;
    }

    public Collection<SubjectRetentionCSV> getSubjectRetention() {
        return subjectRetention;
    }

    public void setSubjectRetention(Collection<SubjectRetentionCSV> subjectRetention) {
        this.subjectRetention = subjectRetention;
    }
}
