package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class TeachersSummaryResponse {

    private MetricsSummary failedDueToAbsences;
    private MetricsSummary failedDueToGrade;
    private MetricsSummary failedDueToCanceling;
    private MetricsSummary success;
    private TermCount min;
    private TermCount max;
    private int total;

    public TeachersSummaryResponse(MetricsSummary failedDueToAbsences, MetricsSummary failedDueToGrade, MetricsSummary failedDueToCanceling,
                                   MetricsSummary success, TermCount min, TermCount max, int total) {
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.failedDueToCanceling = failedDueToCanceling;
        this.success = success;
        this.min = min;
        this.max = max;
        this.total = total;
    }

    public MetricsSummary getFailedDueToAbsences() {
        return failedDueToAbsences;
    }

    public MetricsSummary getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public MetricsSummary getFailedDueToCanceling() {
        return failedDueToCanceling;
    }

    public MetricsSummary getSuccess() {
        return success;
    }

    public TermCount getMin() {
        return min;
    }

    public TermCount getMax() {
        return max;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "TeachersSummaryResponse{" +
                "failedDueToAbsences=" + failedDueToAbsences +
                ", failedDueToGrade=" + failedDueToGrade +
                ", failedDueToCanceling=" + failedDueToCanceling +
                ", success=" + success +
                ", min=" + min +
                ", max=" + max +
                ", total=" + total +
                '}';
    }
}
