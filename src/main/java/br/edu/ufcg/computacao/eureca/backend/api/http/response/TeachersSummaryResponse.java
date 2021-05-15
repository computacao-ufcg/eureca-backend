package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class TeachersSummaryResponse {
    private String curriculum;
    private String from;
    private String to;
    private MetricSummary failedDueToAbsences;
    private MetricSummary failedDueToGrade;
    private MetricSummary failedDueToCanceling;
    private MetricSummary success;
    private TermCount min;
    private TermCount max;
    private int total;

    public TeachersSummaryResponse(String curriculum, String from, String to, MetricSummary failedDueToAbsences, MetricSummary failedDueToGrade, MetricSummary failedDueToCanceling,
                                   MetricSummary success, TermCount min, TermCount max, int total) {
        this.curriculum = curriculum;
        this.from = from;
        this.to = to;
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.failedDueToCanceling = failedDueToCanceling;
        this.success = success;
        this.min = min;
        this.max = max;
        this.total = total;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
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

    public MetricSummary getFailedDueToAbsences() {
        return failedDueToAbsences;
    }

    public MetricSummary getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public MetricSummary getFailedDueToCanceling() {
        return failedDueToCanceling;
    }

    public MetricSummary getSuccess() {
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
                "curriculum='" + curriculum + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", failedDueToAbsences=" + failedDueToAbsences +
                ", failedDueToGrade=" + failedDueToGrade +
                ", failedDueToCanceling=" + failedDueToCanceling +
                ", success=" + success +
                ", min=" + min +
                ", max=" + max +
                ", total=" + total +
                '}';
    }
}
