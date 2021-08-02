package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.StudentsGlossaryFields;

public class StudentsStatisticsSummaryResponse {
    private ActivesSummary activesSummary;
    private AlumniSummary alumniSummary;
    private DropoutsSummary dropoutsSummary;
    private StudentsGlossaryFields glossary;

    public StudentsStatisticsSummaryResponse(ActivesSummary activesSummary, AlumniSummary alumniSummary, DropoutsSummary dropoutsSummary) {
        this.activesSummary = activesSummary;
        this.alumniSummary = alumniSummary;
        this.dropoutsSummary = dropoutsSummary;
    }

    public ActivesSummary getActivesSummary() {
        return activesSummary;
    }

    public void setActivesSummary(ActivesSummary activesSummary) {
        this.activesSummary = activesSummary;
    }

    public AlumniSummary getAlumniSummary() {
        return alumniSummary;
    }

    public void setAlumniSummary(AlumniSummary alumniSummary) {
        this.alumniSummary = alumniSummary;
    }

    public DropoutsSummary getDropoutsSummary() {
        return dropoutsSummary;
    }

    public void setDropoutsSummary(DropoutsSummary dropoutsSummary) {
        this.dropoutsSummary = dropoutsSummary;
    }

    public StudentsGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(StudentsGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
