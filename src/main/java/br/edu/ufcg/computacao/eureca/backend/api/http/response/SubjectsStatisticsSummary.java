package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectsStatisticsSummary {
    private String from;
    private String to;
    private double subjectsCount;
    private SubjectMetricsStatistics subjectMetricsStatistics;

    public SubjectsStatisticsSummary(String from, String to, double subjectsCount, SubjectMetricsStatistics subjectMetricsStatistics) {
        this.from = from;
        this.to = to;
        this.subjectsCount = subjectsCount;
        this.subjectMetricsStatistics = subjectMetricsStatistics;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
