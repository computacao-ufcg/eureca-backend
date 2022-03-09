package br.edu.ufcg.computacao.eureca.backend.core.models;

public enum EurecaOperation {
    GET_ALUMNI_DIGEST("getAlumniDigest"),
    GET_ACTIVES_STATISTICS("getActivesStatistics"),
    GET_ACTIVES_CSV("getActivesCSV"),
    GET_ALUMNI_STATISTICS("getAlumniStatistics"),
    GET_ALUMNI_CSV("getAlumniCSV"),
    GET_DROPOUTS_STATISTICS("getDropoutsStatistics"),
    GET_DROPOUTS_CSV("getDropoutsCSV"),
    GET_STUDENTS_STATISTICS_SUMMARY("getStudentsStatisticsSummary"),
    GET_STUDENTS_RETENTION_STATISTICS("getStudentsRetentionStatistics"),
    GET_STUDENTS_RETENTION_CSV("getStudentsRetentionCSV"),
    GET_SUBJECTS_RETENTION_STATISTICS("getSubjectsRetentionStatistics"),
    GET_SUBJECTS_RETENTION_CSV("getSubjectsRetentionCSV"),
    GET_RETENTION_STATISTICS_SUMMARY("getRetentionStatisticsSummary"),
    GET_SUBJECTS_STATISTICS("getSubjectsStatistics"),
    GET_SUBJECTS_CSV("getSubjectsCSV"),
    GET_SUBJECTS_STATISTICS_SUMMARY("getSubjectsStatisticsSummary"),
    GET_TEACHERS_STATISTICS("getTeachersStatistics"),
    GET_TEACHERS_CSV("getTeachersCSV"),
    GET_TEACHERS_STATISTICS_SUMMARY("getTeachersStatisticsSummary"),
    GET_SUBJECT_ENROLLMENTS_STATISTICS("getSubjectEnrollmentsStatistics"),
    GET_SUBJECT_ENROLLMENTS_CSV("getSubjectEnrollmentsCSV"),
    GET_SUBJECT_ENROLLMENTS_STATISTICS_SUMMARY("getSubjectEnrollmentsStatisticsSummary"),
    GET_CURRICULUM_CODES("getCurriculumCodes"),
    GET_PROFILE("getProfile"),
    GET_PRE_ENROLLMENT("getPreEnrollment"),
    GET_DEMAND("getDemand"),
    GET_DEMAND_CSV("getDemandCSV"),
    GET_PRE_ENROLLMENTS("getActivesPreEnrollments"),
    GET_STUDENTS_EMAILS("getStudentsEmails");

    private String value;

    EurecaOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
