package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectsRetentionResponse implements Comparable {
    private String curriculumCode;
    private String subjectCode;
    private String subjectName;
    private int retention;

    public SubjectsRetentionResponse(String curriculumCode, String subjectCode, String subjectName, int retention) {
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.retention = retention;
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

    public int getRetention() {
        return retention;
    }

    public void setRetention(int retention) {
        this.retention = retention;
    }

    @Override
    public int compareTo(Object o) {
        String thisValue = curriculumCode+subjectCode;
        SubjectsRetentionResponse otherValue = (SubjectsRetentionResponse) o;
        return thisValue.compareTo((otherValue.getCurriculumCode()+otherValue.getSubjectCode()));
    }
}
