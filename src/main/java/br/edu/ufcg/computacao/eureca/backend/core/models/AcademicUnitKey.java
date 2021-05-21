package br.edu.ufcg.computacao.eureca.backend.core.models;

public class AcademicUnitKey {
    private String code;

    public AcademicUnitKey(String code) {
        this.code = code;
    }

    public AcademicUnitKey() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
