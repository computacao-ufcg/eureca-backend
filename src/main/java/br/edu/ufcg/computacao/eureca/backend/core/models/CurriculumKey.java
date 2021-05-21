package br.edu.ufcg.computacao.eureca.backend.core.models;

public class CurriculumKey {
    private String code;

    public CurriculumKey(String code) {
        this.code = code;
    }

    public CurriculumKey() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
