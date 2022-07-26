package br.edu.ufcg.computacao.eureca.backend.core.models;

public enum SubjectType {
    MANDATORY("mandatory"),
    OPTIONAL("optional"),
    ELECTIVE("elective"),
    COMPLEMENTARY("complementary"),
    ALL("all");

    private String value;

    SubjectType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static SubjectType toEnum(String value) {
        for (SubjectType subjectType : SubjectType.values()) {
            if (subjectType.getValue().equals(value)) {
                return subjectType;
            }
        }
        return null;
    }
}
