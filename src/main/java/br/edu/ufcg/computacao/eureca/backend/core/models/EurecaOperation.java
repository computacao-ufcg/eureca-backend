package br.edu.ufcg.computacao.eureca.backend.core.models;

public enum EurecaOperation {
    GET_ACTIVES("getActives"),
    GET_ACTIVES_CSV("getActiveCSV"),
    GET_ALUMNI("getAlumni"),
    GET_ALUMNI_CSV("getAlumniCSV"),
    GET_DROPOUTS("getDropouts"),
    GET_DROPOUTS_CSV("getDropoutsCSV");

    private String value;

    EurecaOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
