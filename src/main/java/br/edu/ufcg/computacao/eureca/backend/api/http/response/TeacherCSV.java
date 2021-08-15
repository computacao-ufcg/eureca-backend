package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class TeacherCSV implements Comparable {
    private String teacherId;
    private String teacherName;
    private String courseCode;
    private String curriculumCode;
    private String term;
    private SubjectsStatisticsSummary averageMetrics;

    public TeacherCSV(String teacherId, String teacherName, String courseCode, String curriculumCode, String term,
                      SubjectsStatisticsSummary averageMetrics) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.term = term;
        this.averageMetrics = averageMetrics;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public SubjectsStatisticsSummary getAverageMetrics() {
        return averageMetrics;
    }

    public void setAverageMetrics(SubjectsStatisticsSummary averageMetrics) {
        this.averageMetrics = averageMetrics;
    }

    @Override
    public String toString() {
        return "TeacherCSV{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", term='" + term + '\'' +
                ", averageMetrics=" + averageMetrics +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        TeacherCSV other = (TeacherCSV) o;
        return this.getTeacherId().compareTo(other.getTeacherId());
    }
}
