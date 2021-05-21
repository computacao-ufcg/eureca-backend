package br.edu.ufcg.computacao.eureca.backend.core.models;

public class AcademicUnitData {
    private String acronym;

    public String getAcronym() {
        return acronym;
    }

    public AcademicUnitData() {
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public AcademicUnitData(String acronym) {
        this.acronym = acronym;
    }
}
