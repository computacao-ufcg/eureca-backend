package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectRetentionDigest implements Comparable {
    private int idealTerm;
    private String subjectCode;
    private String subjectName;
    private int retention;

    public SubjectRetentionDigest(int idealTerm, String subjectCode, String subjectName, int retention) {
        this.idealTerm = idealTerm;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.retention = retention;
    }

    public int getIdealTerm() {
        return idealTerm;
    }

    public void setIdealTerm(int idealTerm) {
        this.idealTerm = idealTerm;
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
        SubjectRetentionDigest otherValue = (SubjectRetentionDigest) o;
        if (this.idealTerm != otherValue.idealTerm) return this.idealTerm - otherValue.idealTerm;
        return this.subjectCode.compareTo(otherValue.getSubjectCode());
    }

    @Override
    public String toString() {
        return idealTerm + ";" + subjectCode + ";" + subjectName + ";" + retention;
    }
}