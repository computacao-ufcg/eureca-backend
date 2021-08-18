package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class AcademicUnitData implements EurecaMapValue {
    private String acronym;
    private String name;

    public String getAcronym() {
        return acronym;
    }

    public AcademicUnitData() {
    }

    public AcademicUnitData(String acronym, String name) {
        this.acronym = acronym;
        this.name = name;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
