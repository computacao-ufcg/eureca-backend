package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.RetentionGlossaryFields;

public class RetentionSummaryResponse {
    private DelayedSummary delayedSummary;
    private SubjectRetentionSummary subjectRetentionSummary;
    private RetentionGlossaryFields glossary;

    public RetentionSummaryResponse(DelayedSummary delayedSummary, SubjectRetentionSummary subjectRetentionSummary) {
        this.delayedSummary = delayedSummary;
        this.subjectRetentionSummary = subjectRetentionSummary;
    }

    public DelayedSummary getDelayedSummary() {
        return delayedSummary;
    }

    public void setDelayedSummary(DelayedSummary delayedSummary) {
        this.delayedSummary = delayedSummary;
    }

    public SubjectRetentionSummary getSubjectRetentionSummary() {
        return subjectRetentionSummary;
    }

    public void setSubjectRetentionSummary(SubjectRetentionSummary subjectRetentionSummary) {
        this.subjectRetentionSummary = subjectRetentionSummary;
    }

    public RetentionGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(RetentionGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
