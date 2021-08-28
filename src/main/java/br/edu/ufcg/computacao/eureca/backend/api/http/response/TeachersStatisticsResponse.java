package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Range;

import java.util.Collection;

public class TeachersStatisticsResponse extends Range {
    private String auCode;
    private String auAcronym;
    private String auName;
    private String courseCode;
    private String curriculumCode;
    private Collection<TeacherStatistics> teachers;

    public TeachersStatisticsResponse(String auCode, String auAcronym, String auName,
                                      String courseCode, String curriculumCode, String from, String to,
                                      Collection<TeacherStatistics> teachers) {
        super(from, to);
        this.auCode = auCode;
        this.auAcronym = auAcronym;
        this.auName = auName;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.teachers = teachers;
    }

    public String getAuCode() {
        return auCode;
    }

    public void setAuCode(String auCode) {
        this.auCode = auCode;
    }

    public String getAuAcronym() {
        return auAcronym;
    }

    public void setAuAcronym(String auAcronym) {
        this.auAcronym = auAcronym;
    }

    public String getAuName() {
        return auName;
    }

    public void setAuName(String auName) {
        this.auName = auName;
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

    public Collection<TeacherStatistics> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<TeacherStatistics> teachers) {
        this.teachers = teachers;
    }
}
