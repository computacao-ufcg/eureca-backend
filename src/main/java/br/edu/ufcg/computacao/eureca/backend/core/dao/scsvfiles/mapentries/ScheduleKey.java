package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class ScheduleKey implements EurecaMapKey, Comparable {

    private String courseCode;
    private String curriculumCode;
    private String subjectCode;
    private String classCode;

    public ScheduleKey(String courseCode, String curriculumCode, String subjectCode, String classCode) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.classCode = classCode;
    }

    public ScheduleKey() {}

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

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleKey that = (ScheduleKey) o;
        return Objects.equals(courseCode, that.courseCode) && Objects.equals(curriculumCode, that.curriculumCode) && Objects.equals(subjectCode, that.subjectCode) && Objects.equals(classCode, that.classCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, curriculumCode, subjectCode, classCode);
    }

    @Override
    public int compareTo(Object o) {
        ScheduleKey other = (ScheduleKey) o;
        String otherValue = other.getCourseCode() + other.getCurriculumCode() + other.getSubjectCode() + other.getClassCode();
        return (courseCode + curriculumCode + subjectCode + classCode).compareTo(otherValue);
    }
}
