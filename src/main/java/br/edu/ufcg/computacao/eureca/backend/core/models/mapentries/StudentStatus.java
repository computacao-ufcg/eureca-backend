package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public enum StudentStatus {
    ACTIVE("Ativo"),
    DROPOUT("Inativo"),
    ALUMNUS("GRADUADO");

    private String value;

    private StudentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
