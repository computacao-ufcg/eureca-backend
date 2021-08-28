package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class StudentsRetentionSummary {
    private String from;
    private String to;
    private int studentsRetentionCount;
    private StudentMetricsSummary average;

    public StudentsRetentionSummary(String from, String to, int studentsRetentionCount, StudentMetricsSummary average) {
        this.from = from;
        this.to = to;
        this.studentsRetentionCount = studentsRetentionCount;
        this.average = average;
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

    public int getStudentsRetentionCount() {
        return studentsRetentionCount;
    }

    public void setStudentsRetentionCount(int studentsRetentionCount) {
        this.studentsRetentionCount = studentsRetentionCount;
    }

    public StudentMetricsSummary getAverage() {
        return average;
    }

    public void setAverage(StudentMetricsSummary average) {
        this.average = average;
    }
}
