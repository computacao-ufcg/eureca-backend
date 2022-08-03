package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class SubjectCodeTermClassIdKey implements EurecaMapKey {
    private String subjectCode;
    private String term;
    private String classId;

    public SubjectCodeTermClassIdKey(String subjectCode, String term, String classId) {
        this.subjectCode = subjectCode;
        this.term = term;
        this.classId = classId;
    }

    public SubjectCodeTermClassIdKey() {
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubjectCodeTermClassIdKey that = (SubjectCodeTermClassIdKey) o;
        return getSubjectCode().equals(that.getSubjectCode()) &&
                getTerm().equals(that.getTerm()) &&
                getClassId().equals(that.getClassId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectCode(), getTerm(), getClassId());
    }

    @Override
    public String toString() {
        return "SubjectCodeTermClassIdKey{" +
                "subjectCode='" + subjectCode + '\'' +
                ", term='" + term + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
