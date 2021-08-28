package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.RetentionGlossaryFields;

public class RetentionStatisticsSummaryResponse {
    private String courseCode;
    private String curriculumCode;
    private StudentsRetentionSummary studentsRetentionSummary;
    private SubjectsRetentionSummary subjectsRetentionSummary;
    private RetentionGlossaryFields glossary;

    public RetentionStatisticsSummaryResponse(String courseCode, String curriculumCode,
                StudentsRetentionSummary studentsRetentionSummary, SubjectsRetentionSummary subjectsRetentionSummary) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.studentsRetentionSummary = studentsRetentionSummary;
        this.subjectsRetentionSummary = subjectsRetentionSummary;
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
