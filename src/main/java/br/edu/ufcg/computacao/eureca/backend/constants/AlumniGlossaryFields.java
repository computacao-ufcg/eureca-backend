package br.edu.ufcg.computacao.eureca.backend.constants;

public class AlumniGlossaryFields extends GlossaryFields {
    Field alumniTracked;
    Field consolidatedEmployers;
    Field employersInAcademy;
    Field employersInGovernment;
    Field employersInOngs;
    Field employersInIndustry;

    public AlumniGlossaryFields(Field alumniTracked, Field consolidatedEmployers, Field employersInAcademy,
    Field employersInGovernment, Field employersInOngs, Field employersInIndustry) {
        this.alumniTracked = alumniTracked;
        this.consolidatedEmployers = consolidatedEmployers;
        this.employersInAcademy = employersInAcademy;
        this.employersInGovernment = employersInGovernment;
        this.employersInOngs = employersInOngs;
        this.employersInIndustry = employersInIndustry;

    }

    public Field getAlumniTracked() {
        return alumniTracked;
    }

    public void setAlumniTracked(Field alumniTracked) {
        this.alumniTracked = alumniTracked;
    }

    public Field getConsolidatedEmployers() {
        return consolidatedEmployers;
    }

    public void setConsolidatedEmployers(Field consolidatedEmployers) {
        this.consolidatedEmployers = consolidatedEmployers;
    }

    public Field getEmployersInAcademy() {
        return employersInAcademy;
    }

    public void setEmployersInAcademy(Field employersInAcademy) {
        this.employersInAcademy = employersInAcademy;
    }

    public Field getEmployersInGovernment() {
        return employersInGovernment;
    }

    public void setEmployersInGovernment(Field employersInGovernment) {
        this.employersInGovernment = employersInGovernment;
    }

    public Field getEmployersInOngs() {
        return employersInOngs;
    }

    public void setEmployersInOngs(Field employersInOngs) {
        this.employersInOngs = employersInOngs;
    }

    public Field getEmployersInIndustry() {
        return employersInIndustry;
    }

    public void setEmployersInIndustry(Field employersInIndustry) {
        this.employersInIndustry = employersInIndustry;
    }
}
