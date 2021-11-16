package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Objects;

public class SubjectScheduleKey {

    private String courseCode;
    private String curriculumCode;
    private String subjectCode;
    private String term;

    public SubjectScheduleKey(String courseCode, String curriculumCode, String subjectCode, String term) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.term = term;
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

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectScheduleKey that = (SubjectScheduleKey) o;
        return Objects.equals(courseCode, that.courseCode) && Objects.equals(curriculumCode, that.curriculumCode) && Objects.equals(subjectCode, that.subjectCode) && Objects.equals(term, that.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, curriculumCode, subjectCode, term);
    }
}
