package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class StudentsRetentionStatisticsResponse extends Range {
    private String courseCode;
    private String curriculumCode;
    private Collection<StudentsRetentionPerTermSummary> terms;

    public StudentsRetentionStatisticsResponse(Collection<StudentsRetentionPerTermSummary> terms, String courseCode, String curriculumCode, String from, String to) {
        super(from, to);
        this.terms = terms;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public Collection<StudentsRetentionPerTermSummary> getTerms() {
        return terms;
    }

    public void setTerms(Collection<StudentsRetentionPerTermSummary> terms) {
        this.terms = terms;
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
}
