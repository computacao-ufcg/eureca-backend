package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.RetentionGlossaryFields;

public class RetentionStatisticsSummaryResponse {
    private StudentsRetentionSummary studentsRetentionSummary;
    private SubjectsRetentionSummary subjectsRetentionSummary;
    private RetentionGlossaryFields glossary;

    public RetentionStatisticsSummaryResponse(StudentsRetentionSummary studentsRetentionSummary, SubjectsRetentionSummary subjectsRetentionSummary) {
        this.studentsRetentionSummary = studentsRetentionSummary;
        this.subjectsRetentionSummary = subjectsRetentionSummary;
    }

    public StudentsRetentionSummary getStudentsRetentionSummary() {
        return studentsRetentionSummary;
    }

    public void setStudentsRetentionSummary(StudentsRetentionSummary studentsRetentionSummary) {
        this.studentsRetentionSummary = studentsRetentionSummary;
    }

    public SubjectsRetentionSummary getSubjectsRetentionSummary() {
        return subjectsRetentionSummary;
    }

    public void setSubjectsRetentionSummary(SubjectsRetentionSummary subjectsRetentionSummary) {
        this.subjectsRetentionSummary = subjectsRetentionSummary;
    }

    public RetentionGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(RetentionGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
