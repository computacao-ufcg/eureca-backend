package br.edu.ufcg.computacao.eureca.backend.api.http.response.subject;

import java.util.Collection;

public class SubjectsResponse {
    private Collection<SubjectCSV> subjects;

    public SubjectsResponse(Collection<SubjectCSV> subjects) {
        this.subjects = subjects;
    }

    public Collection<SubjectCSV> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<SubjectCSV> subjects) {
        this.subjects = subjects;
    }
}
