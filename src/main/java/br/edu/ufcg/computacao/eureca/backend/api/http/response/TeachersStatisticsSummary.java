package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class TeachersStatisticsSummary {
    private double teachersCount;
    private TermCount min;
    private TermCount max;
    private double averageSuccessRate;
    private double averageFailureDueToGradeRate;
    private double averageFailureDueToAbsenceRate;
    private double averageSuspendedRate;

    public TeachersStatisticsSummary(double teachersCount, TermCount min, TermCount max,
                                     double averageSuccessRate, double averageFailureDueToGradeRate,
                                     double averageFailureDueToAbsenceRate, double averageSuspendedRate) {
        this.teachersCount = teachersCount;
        this.min = min;
        this.max = max;
        this.averageSuccessRate = averageSuccessRate;
        this.averageFailureDueToGradeRate = averageFailureDueToGradeRate;
        this.averageFailureDueToAbsenceRate = averageFailureDueToAbsenceRate;
        this.averageSuspendedRate = averageSuspendedRate;
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
