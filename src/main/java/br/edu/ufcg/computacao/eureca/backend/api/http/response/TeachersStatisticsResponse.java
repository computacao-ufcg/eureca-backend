package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class TeachersStatisticsResponse extends Range {
    private String courseCode;
    private String curriculumCode;
    private Collection<TeachersPerAcademicUnitPerTermSummary> teachers;

    public TeachersStatisticsResponse(Collection<TeachersPerAcademicUnitPerTermSummary> teachers, String courseCode, String curriculumCode, String from, String to) {
        super(from, to);
        this.teachers = teachers;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public Collection<TeachersPerAcademicUnitPerTermSummary> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<TeachersPerAcademicUnitPerTermSummary> teachers) {
        this.teachers = teachers;
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
