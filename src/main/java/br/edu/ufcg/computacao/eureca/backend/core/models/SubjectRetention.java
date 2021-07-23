package br.edu.ufcg.computacao.eureca.backend.core.models;

public class SubjectRetention {
    private String subjectCode;
    private String subjectName;
    private int retention;

    public SubjectRetention(String subjectCode, String subjectName, int retention) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.retention = retention;
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
}
