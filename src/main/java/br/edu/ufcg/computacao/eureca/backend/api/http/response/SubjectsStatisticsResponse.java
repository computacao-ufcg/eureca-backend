package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectsStatisticsResponse extends Range {
    private String courseCode;
    private String curriculumCode;
    private Collection<SubjectMetricsPerTermSummary> subjects;

    public SubjectsStatisticsResponse(Collection<SubjectMetricsPerTermSummary> subjects, String courseCode, String curriculumCode, String from, String to) {
        super(from, to);
        this.subjects = subjects;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public Collection<SubjectMetricsPerTermSummary> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<SubjectMetricsPerTermSummary> subjects) {
        this.subjects = subjects;
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
