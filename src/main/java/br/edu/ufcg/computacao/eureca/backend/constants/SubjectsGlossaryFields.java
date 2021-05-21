package br.edu.ufcg.computacao.eureca.backend.constants;

public class SubjectsGlossaryFields {
    Field mandatory;
    Field optional;
    Field elective;
    Field complementary;

    public SubjectsGlossaryFields(Field mandatory, Field optional, Field elective, Field complementary) {
        this.mandatory = mandatory;
        this.optional = optional;
        this.elective = elective;
        this.complementary = complementary;
    }

    public Field getMandatory() {
        return mandatory;
    }

    public void setMandatory(Field mandatory) {
        this.mandatory = mandatory;
    }

    public Field getOptional() {
        return optional;
    }

    public void setOptional(Field optional) {
        this.optional = optional;
    }

    public Field getElective() {
        return elective;
    }

    public void setElective(Field elective) {
        this.elective = elective;
    }

    public Field getComplementary() {
        return complementary;
    }

    public void setComplementary(Field complementary) {
        this.complementary = complementary;
    }
}
