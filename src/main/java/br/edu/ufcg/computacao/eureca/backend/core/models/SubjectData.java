package br.edu.ufcg.computacao.eureca.backend.core.models;

public class SubjectData {
    private String academicUnit;
    private String type;
    private int credits;
    private int hours;
    private String name;
    private String equivalentCodes;
    private String idealTerm;
    private String preRequirements;

    public SubjectData(String academicUnit, String type, int credits, int hours, String name, String equivalentCodes,
                       String idealTerm, String preRequirements) {
        this.academicUnit = academicUnit;
        this.type = type;
        this.credits = credits;
        this.hours = hours;
        this.name = name;
        this.equivalentCodes = equivalentCodes;
        this.idealTerm = idealTerm;
        this.preRequirements = preRequirements;
    }

    public SubjectData() {
    }

    public String getAcademicUnit() {
        return academicUnit;
    }

    public void setAcademicUnit(String academicUnit) {
        this.academicUnit = academicUnit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquivalentCodes() {
        return equivalentCodes;
    }

    public void setEquivalentCodes(String equivalentCodes) {
        this.equivalentCodes = equivalentCodes;
    }

    public String getIdealTerm() {
        return idealTerm;
    }

    public void setIdealTerm(String idealTerm) {
        this.idealTerm = idealTerm;
    }

    public String getPreRequirements() {
        return preRequirements;
    }

    public void setPreRequirements(String preRequirements) {
        this.preRequirements = preRequirements;
    }
}
