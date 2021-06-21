package br.edu.ufcg.computacao.eureca.backend.core.models;

public class AcademicUnit {
    private String code;
    private String acronym;

    public AcademicUnit(String code, String acronym) {
        this.code = code;
        this.acronym = acronym;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Override
    public String toString() {
        return "AcademicUnit{" +
                "code='" + code + '\'' +
                ", acronym='" + acronym + '\'' +
                '}';
    }
}
