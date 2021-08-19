package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectsStatisticsSummary {
    private double subjectsCount;
    private SubjectMetricsStatistics subjectMetricsStatistics;

    public SubjectsStatisticsSummary(double subjectsCount, SubjectMetricsStatistics subjectMetricsStatistics) {
        this.subjectsCount = subjectsCount;
        this.subjectMetricsStatistics = subjectMetricsStatistics;
    }

    public double getSubjectsCount() {
        return subjectsCount;
    }

    public void setSubjectsCount(double subjectsCount) {
        this.subjectsCount = subjectsCount;
    }

    public SubjectMetricsStatistics getSubjectMetricsStatistics() {
        return subjectMetricsStatistics;
    }

    public void setSubjectMetricsStatistics(SubjectMetricsStatistics subjectMetricsStatistics) {
        this.subjectMetricsStatistics = subjectMetricsStatistics;
    }
}
