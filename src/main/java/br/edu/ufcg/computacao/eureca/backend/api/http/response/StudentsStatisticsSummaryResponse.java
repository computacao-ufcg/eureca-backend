package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.StudentsGlossaryFields;

public class StudentsStatisticsSummaryResponse {
    private String courseCode;
    private String curriculumCode;
    private String from;
    private String to;
    private ActivesSummary activesSummary;
    private AlumniSummary alumniSummary;
    private DropoutsSummary dropoutsSummary;
    private StudentsGlossaryFields glossary;

    public StudentsStatisticsSummaryResponse(String courseCode, String curriculumCode, String from, String to,
                        ActivesSummary activesSummary, AlumniSummary alumniSummary, DropoutsSummary dropoutsSummary) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.from = from;
        this.to = to;
        this.activesSummary = activesSummary;
        this.alumniSummary = alumniSummary;
        this.dropoutsSummary = dropoutsSummary;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
