package br.edu.ufcg.computacao.eureca.backend.core.models;

public class SubjectKey {
    private String code;

    public SubjectKey(String code) {
        this.code = code;
    }

    public SubjectKey() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
