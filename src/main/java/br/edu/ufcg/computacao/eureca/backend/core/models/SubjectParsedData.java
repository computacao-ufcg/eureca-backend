package br.edu.ufcg.computacao.eureca.backend.core.models;

public class SubjectParsedData {
    private String code;
    private String academicUnit;
    private String type;
    private int credits;
    private int hours;
    private String name;
    private String equivalentCodes[];
    private String idealTerm;
    private String preRequirements[];

    public SubjectParsedData(String code, SubjectData data) {
        this.code = code;
        this.academicUnit = data.getAcademicUnit();
        this.type = data.getType();
        this.credits = data.getCredits();
        this.hours = data.getHours();
        this.name = data.getName();
        this.equivalentCodes = data.getEquivalentCodes().split(",");
        this.idealTerm = data.getIdealTerm();
        this.preRequirements = data.getPreRequirements().split(",");
    }

    public SubjectParsedData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String[] getEquivalentCodes() {
        return equivalentCodes;
    }

    public void setEquivalentCodes(String[] equivalentCodes) {
        this.equivalentCodes = equivalentCodes;
    }

    public String getIdealTerm() {
        return idealTerm;
    }

    public void setIdealTerm(String idealTerm) {
        this.idealTerm = idealTerm;
    }

    public String[] getPreRequirements() {
        return preRequirements;
    }

    public void setPreRequirements(String[] preRequirements) {
        this.preRequirements = preRequirements;
    }
}
