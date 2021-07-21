package br.edu.ufcg.computacao.eureca.backend.core.models;

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
    GET_SUBJECTS_STATISTICS_SUMMARY("getSubjectsStatisticsSummary"),
    GET_SUBJECTS_STATISTICS_CSV("getSubjectsStatisticsCSV"),
    GET_SUBJECTS_MANDATORY_STATISTICS("getSubjectsMandatoryStatistics"),
    GET_SUBJECTS_OPTIONAL_STATISTICS("getSubjectsOptionalStatistics"),
    GET_SUBJECTS_ELECTIVE_STATISTICS("getSubjectsElectiveStatistics"),
    GET_SUBJECTS_COMPLEMENTARY_STATISTICS("getSubjectsComplementaryStatistics"),
    GET_TEACHERS_STATISTICS("getTeachersStatistics"),
    GET_TEACHERS_STATISTICS_CSV("getTeachersStatisticsCSV"),
    GET_ENROLLMENTS_STATISTICS("getEnrollmentsStatistics"),
    GET_ENROLLMENTS_STATISTICS_CSV("getEnrollmentsStatisticsCSV");

    private String value;

    EurecaOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
