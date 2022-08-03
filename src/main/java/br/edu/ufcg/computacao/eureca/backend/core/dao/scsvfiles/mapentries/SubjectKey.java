package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class SubjectKey implements EurecaMapKey, Comparable {
    private String courseCode;
    private String curriculumCode;
    private String subjectCode;

    public SubjectKey(String courseCode, String curriculumCode, String subjectCode) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
    }

    public SubjectKey() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubjectKey that = (SubjectKey) o;
        return getCourseCode().equals(that.getCourseCode()) &&
                getCurriculumCode().equals(that.getCurriculumCode()) &&
                getSubjectCode().equals(that.getSubjectCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurriculumCode(), getSubjectCode());
    }

    @Override
    public String toString() {
        return "SubjectKey{" +
                "courseCode='" + courseCode + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        SubjectKey other = (SubjectKey) o;
        String otherValue = other.getCourseCode() + other.getCurriculumCode() + other.getSubjectCode();
        return (courseCode + curriculumCode + subjectCode).compareTo(otherValue);
    }
}
