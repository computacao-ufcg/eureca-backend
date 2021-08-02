package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectStatisticsSummary {
    private int subjectsCount;
    private SubjectMetricsStatistics subjectMetricsStatistics;

    public SubjectStatisticsSummary(int subjectsCount, SubjectMetricsStatistics subjectMetricsStatistics) {
        this.subjectsCount = subjectsCount;
        this.subjectMetricsStatistics = subjectMetricsStatistics;
    }

    public int getSubjectsCount() {
        return subjectsCount;
    }

    public void setSubjectsCount(int subjectsCount) {
        this.subjectsCount = subjectsCount;
    }

    public SubjectMetricsStatistics getSubjectMetricsStatistics() {
        return subjectMetricsStatistics;
    }

    public void setSubjectMetricsStatistics(SubjectMetricsStatistics subjectMetricsStatistics) {
        this.subjectMetricsStatistics = subjectMetricsStatistics;
    }
}
