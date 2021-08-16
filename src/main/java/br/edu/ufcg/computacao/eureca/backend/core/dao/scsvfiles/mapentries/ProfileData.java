package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class ProfileData implements EurecaMapValue {
    private String courseCode;
    private String courseName;

    public ProfileData(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public ProfileData() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
