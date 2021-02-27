package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DelayedSummary  {

    private int delayedCount;
    private double averageAttemptedCredits;
    private double averageLoad;
    private double averageCost;
    private double averageCourseDurationPrediction;
    private double averageFeasibility;
    private double averagePace;
    private double averageRisk;
    private double averageSuccessRate;

    public DelayedSummary(int delayedCount, double averageAttemptedCredits, double averageLoad, double averageCost,
                          double averageCourseDurationPrediction, double averageFeasibility,
                          double averagePace, double averageRisk, double averageSuccessRate) {
        this.delayedCount = delayedCount;
        this.averageAttemptedCredits = averageAttemptedCredits;
        this.averageLoad = averageLoad;
        this.averageCost = averageCost;
        this.averageCourseDurationPrediction = averageCourseDurationPrediction;
        this.averageFeasibility = averageFeasibility;
        this.averagePace = averagePace;
        this.averageRisk = averageRisk;
        this.averageSuccessRate = averageSuccessRate;
    }

    public int getDelayedCount() {
        return delayedCount;
    }

    public void setDelayedCount(int delayedCount) {
        this.delayedCount = delayedCount;
    }

    public double getAverageAttemptedCredits() {
        return averageAttemptedCredits;
    }

    public void setAverageAttemptedCredits(double averageAttemptedCredits) {
        this.averageAttemptedCredits = averageAttemptedCredits;
    }

    public double getAverageLoad() {
        return averageLoad;
    }

    public void setAverageLoad(double averageLoad) {
        this.averageLoad = averageLoad;
    }

    public double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    public double getAverageCourseDurationPrediction() {
        return averageCourseDurationPrediction;
    }

    public void setAverageCourseDurationPrediction(double averageCourseDurationPrediction) {
        this.averageCourseDurationPrediction = averageCourseDurationPrediction;
    }

    public double getAverageFeasibility() {
        return averageFeasibility;
    }

    public void setAverageFeasibility(double averageFeasibility) {
        this.averageFeasibility = averageFeasibility;
    }

    public double getAveragePace() {
        return averagePace;
    }

    public void setAveragePace(double averagePace) {
        this.averagePace = averagePace;
    }

    public double getAverageRisk() {
        return averageRisk;
    }

    public void setAverageRisk(double averageRisk) {
        this.averageRisk = averageRisk;
    }

    public double getAverageSuccessRate() {
        return averageSuccessRate;
    }

    public void setAverageSuccessRate(double averageSuccessRate) {
        this.averageSuccessRate = averageSuccessRate;
    }
}
