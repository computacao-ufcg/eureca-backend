package br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.refator;

public class AverageRates {
    private double averageSuccessRate;
    private double averageFailureDueToGradeRate;
    private double averageFailureDueToAbsenceRate;
    private double averageSuspendedRate;

    public AverageRates(double averageSuccessRate, double averageFailureDueToGradeRate, double averageFailureDueToAbsenceRate, double averageSuspendedRate) {
        this.averageSuccessRate = averageSuccessRate;
        this.averageFailureDueToGradeRate = averageFailureDueToGradeRate;
        this.averageFailureDueToAbsenceRate = averageFailureDueToAbsenceRate;
        this.averageSuspendedRate = averageSuspendedRate;

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
