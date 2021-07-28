package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectResponse {
    private Collection<SubjectCSV> subjects;

    public SubjectResponse(Collection<SubjectCSV> subjects) {
        this.subjects = subjects;
    }

    public Collection<SubjectCSV> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<SubjectCSV> subjects) {
        this.subjects = subjects;
    }
}
