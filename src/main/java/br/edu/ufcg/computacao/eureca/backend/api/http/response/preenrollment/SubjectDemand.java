package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

public class SubjectDemand implements Comparable {
    private String subjectCode;
    private String subjectName;
    private String curriculumCode;
    private String classId;
    private int totalDemand;

    public SubjectDemand(String subjectCode, String subjectName, String curriculumCode, String classId, int totalDemand) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.curriculumCode = curriculumCode;
        this.classId = classId;
        this.totalDemand = totalDemand;
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

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getTotalDemand() {
        return totalDemand;
    }

    public void setTotalDemand(int totalDemand) {
        this.totalDemand = totalDemand;
    }

    @Override
    public String toString() {
        return "SubjectDemand{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", classId='" + classId + '\'' +
                ", totalDemand=" + totalDemand +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        SubjectDemand other = (SubjectDemand) o;
        int ret = this.getCurriculumCode().compareTo(other.getCurriculumCode());
        if (ret != 0) return ret;
        ret = this.getSubjectCode().compareTo(other.getSubjectCode());
        if (ret != 0) return ret;
        return this.getClassId().compareTo(other.getClassId());
    }
}
