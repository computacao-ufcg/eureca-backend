package br.edu.ufcg.computacao.eureca.backend.core.models;

public enum SubjectType {
    MANDATORY("mandatory"),
    OPTIONAL("optional"),
    ELECTIVE("elective"),
    COMPLEMENTARY("complementary");

    private String value;

    SubjectType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
