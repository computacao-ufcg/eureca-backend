package br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher;

import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class TeachersStatisticsSummary {
    private String from;
    private String to;
    private double teachersCount;
    private TermCount min;
    private TermCount max;
    private double averageSuccessRate;
    private double averageFailureDueToGradeRate;
    private double averageFailureDueToAbsenceRate;
    private double averageSuspendedRate;

    public TeachersStatisticsSummary(String from, String to, double teachersCount, TermCount min, TermCount max,
                                     double averageSuccessRate, double averageFailureDueToGradeRate,
                                     double averageFailureDueToAbsenceRate, double averageSuspendedRate) {
        this.from = from;
        this.to = to;
        this.teachersCount = teachersCount;
        this.min = min;
        this.max = max;
        this.averageSuccessRate = averageSuccessRate;
        this.averageFailureDueToGradeRate = averageFailureDueToGradeRate;
        this.averageFailureDueToAbsenceRate = averageFailureDueToAbsenceRate;
        this.averageSuspendedRate = averageSuspendedRate;
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

    public double getTeachersCount() {
        return teachersCount;
    }

    public void setTeachersCount(double teachersCount) {
        this.teachersCount = teachersCount;
    }

    public TermCount getMin() {
        return min;
    }

    public void setMin(TermCount min) {
        this.min = min;
    }

    public TermCount getMax() {
        return max;
    }

    public void setMax(TermCount max) {
        this.max = max;
    }

    public double getAverageSuccessRate() {
        return averageSuccessRate;
    }

    public void setAverageSuccessRate(double averageSuccessRate) {
        this.averageSuccessRate = averageSuccessRate;
    }

    public double getAverageFailureDueToGradeRate() {
        return averageFailureDueToGradeRate;
    }

    public void setAverageFailureDueToGradeRate(double averageFailureDueToGradeRate) {
        this.averageFailureDueToGradeRate = averageFailureDueToGradeRate;
    }

    public double getAverageFailureDueToAbsenceRate() {
        return averageFailureDueToAbsenceRate;
    }

    public void setAverageFailureDueToAbsenceRate(double averageFailureDueToAbsenceRate) {
        this.averageFailureDueToAbsenceRate = averageFailureDueToAbsenceRate;
    }

    public double getAverageSuspendedRate() {
        return averageSuspendedRate;
    }

    public void setAverageSuspendedRate(double averageSuspendedRate) {
        this.averageSuspendedRate = averageSuspendedRate;
    }
}
