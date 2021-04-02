package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public enum EurecaOperation {
    GET_ACTIVES("getActives"),
    GET_ACTIVES_CSV("getActiveCSV"),
    GET_ALUMNI("getAlumni"),
    GET_ALUMNI_CSV("getAlumniCSV"),
    GET_ALUMNI_BASIC_DATA("getAlumniBasicData"),
    GET_DROPOUTS("getDropouts"),
    GET_DROPOUTS_CSV("getDropoutsCSV"),
    GET_DELAYED("getDelayed"),
    GET_DELAYED_CSV("getDelayedCSV"),
    GET_STUDENTS_STATISTICS("getStudentsStatistics"),
    GET_SUBJECTS_STATISTICS("getSubjectsStatistics");

    private String value;

    EurecaOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
