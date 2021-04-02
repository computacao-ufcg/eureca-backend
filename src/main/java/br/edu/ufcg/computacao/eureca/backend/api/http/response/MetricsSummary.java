package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class MetricsSummary {
    private double averageTermsCount;
    private double averageAttemptedCredits;
    private double averageFeasibility;
    private double averageSuccessRate;
    private double averageLoad;
    private double averageCost;
    private double averagePace;
    private double averageCourseDurationPrediction;
    private double averageRisk;

    public MetricsSummary(double averageTermsCount, double averageAttemptedCredits, double averageFeasibility,
                          double averageSuccessRate, double averageLoad, double averageCost, double averagePace,
                          double averageCourseDurationPrediction, double averageRisk) {
        this.averageTermsCount = averageTermsCount;
        this.averageAttemptedCredits = averageAttemptedCredits;
        this.averageFeasibility = averageFeasibility;
        this.averageSuccessRate = averageSuccessRate;
        this.averageLoad = averageLoad;
        this.averageCost = averageCost;
        this.averagePace = averagePace;
        this.averageCourseDurationPrediction = averageCourseDurationPrediction;
        this.averageRisk = averageRisk;
    }

    public double getAverageTermsCount() {
        return averageTermsCount;
    }

    public void setAverageTermsCount(double averageTermsCount) {
        this.averageTermsCount = averageTermsCount;
    }

    public double getAverageAttemptedCredits() {
        return averageAttemptedCredits;
    }

    public void setAverageAttemptedCredits(double averageAttemptedCredits) {
        this.averageAttemptedCredits = averageAttemptedCredits;
    }

    public double getAverageFeasibility() {
        return averageFeasibility;
    }

    public void setAverageFeasibility(double averageFeasibility) {
        this.averageFeasibility = averageFeasibility;
    }

    public double getAverageSuccessRate() {
        return averageSuccessRate;
    }

    public void setAverageSuccessRate(double averageSuccessRate) {
        this.averageSuccessRate = averageSuccessRate;
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

    public double getAveragePace() {
        return averagePace;
    }

    public void setAveragePace(double averagePace) {
        this.averagePace = averagePace;
    }

    public double getAverageCourseDurationPrediction() {
        return averageCourseDurationPrediction;
    }

    public void setAverageCourseDurationPrediction(double averageCourseDurationPrediction) {
        this.averageCourseDurationPrediction = averageCourseDurationPrediction;
    }

    public double getAverageRisk() {
        return averageRisk;
    }

    public void setAverageRisk(double averageRisk) {
        this.averageRisk = averageRisk;
    }
}
