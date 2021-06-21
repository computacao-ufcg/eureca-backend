package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class CodeTermClassIdKey implements EurecaMapKey {
    private String code;
    private String term;
    private String classId;

    public CodeTermClassIdKey(String code, String term, String classId) {
        this.code = code;
        this.term = term;
        this.classId = classId;
    }

    public CodeTermClassIdKey() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeTermClassIdKey that = (CodeTermClassIdKey) o;
        return getCode().equals(that.getCode()) &&
                getTerm().equals(that.getTerm()) &&
                getClassId().equals(that.getClassId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getTerm(), getClassId());
    }
}
