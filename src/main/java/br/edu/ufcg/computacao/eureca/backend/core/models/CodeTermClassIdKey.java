package br.edu.ufcg.computacao.eureca.backend.core.models;

public class CodeTermClassIdKey {
    private String code;
    private String term;
    private String classID;

    public CodeTermClassIdKey(String code, String term, String classID) {
        this.code = code;
        this.term = term;
        this.classID = classID;
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

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
}
