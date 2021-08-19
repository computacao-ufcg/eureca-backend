package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectCSV implements Comparable {
    private String courseCode;
    private String curriculumCode;
    private String subjectCode;
    private String subjectName;
    private String term;
    private SubjectMetrics metrics;

    public SubjectCSV(String courseCode, String curriculumCode, String subjectCode, String subjectName, String term,
                      SubjectMetrics metrics) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.term = term;
        this.metrics = metrics;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public SubjectMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(SubjectMetrics metrics) {
        this.metrics = metrics;
    }

    @Override
    public int compareTo(Object o) {
        String thisValue = courseCode + curriculumCode + subjectCode + term;
        SubjectCSV other = (SubjectCSV) o;
        String otherValue = other.getCourseCode() + other.getCurriculumCode()+ other.getSubjectCode() + other.getTerm();
        return thisValue.compareTo(otherValue);
    }

    @Override
    public String toString() {
        return "SubjectCSV{" +
                "courseCode='" + courseCode + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", term='" + term + '\'' +
                ", metrics='" + metrics + '\'' +
                '}';
    }
}
