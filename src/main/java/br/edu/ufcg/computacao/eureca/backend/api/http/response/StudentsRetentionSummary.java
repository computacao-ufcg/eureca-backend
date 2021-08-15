package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class StudentsRetentionSummary {
    private int studentsRetentionCount;
    private StudentMetricsSummary average;

    public StudentsRetentionSummary(int studentsRetentionCount, StudentMetricsSummary average) {
        this.studentsRetentionCount = studentsRetentionCount;
        this.average = average;
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
