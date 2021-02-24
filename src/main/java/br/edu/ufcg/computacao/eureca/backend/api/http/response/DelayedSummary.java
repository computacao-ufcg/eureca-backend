package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class DelayedSummary  {

    private double averageAttemptedCredits;
    private double averageLoad;
    private double averageCost;
    private double averageCourseDurationPrediction;
    private double averageFeasibility;
    private double averagePace;
    private double averageRisk;
    private double averageSuccessRate;

    public DelayedSummary(double averageAttemptedCredits, double averageLoad, double averageCost,
                          double averageCourseDurationPrediction, double averageFeasibility,
                          double averagePace, double averageRisk, double averageSuccessRate) {
        this.averageAttemptedCredits = averageAttemptedCredits;
        this.averageLoad = averageLoad;
        this.averageCost = averageCost;
        this.averageCourseDurationPrediction = averageCourseDurationPrediction;
        this.averageFeasibility = averageFeasibility;
        this.averagePace = averagePace;
        this.averageRisk = averageRisk;
        this.averageSuccessRate = averageSuccessRate;
    }

    public DelayedSummary(Collection<DelayedDataResponse> delayedStudents) {
        double totalAttemptedCredits = 0;
        double totalLoad = 0;
        double totalCost = 0;
        double totalCourseDurationPrediction = 0;
        double totalFeasibility = 0;
        double totalPace = 0;
        double totalRisk = 0;
        double totalSuccessRate = 0;
        int totalDelayed = delayedStudents.size();

        for (DelayedDataResponse delayed : delayedStudents) {
            totalAttemptedCredits += delayed.getAttemptedCredits();
            totalLoad += delayed.getAverageLoad();
            totalCost += delayed.getCost();
            totalCourseDurationPrediction += delayed.getCourseDurationPrediction();
            totalFeasibility += delayed.getFeasibility();
            totalPace += delayed.getPace();
            totalRisk += delayed.getRisk();
            totalSuccessRate += delayed.getSuccessRate();
        }

        this.averageAttemptedCredits = totalAttemptedCredits / totalDelayed;
        this.averageLoad = totalLoad / totalDelayed;
        this.averageCost = totalCost / totalDelayed;
        this.averageCourseDurationPrediction = totalCourseDurationPrediction / totalDelayed;
        this.averageFeasibility = totalFeasibility / totalDelayed;
        this.averagePace = totalPace / totalDelayed;
        this.averageRisk = totalRisk / totalDelayed;
        this.averageSuccessRate = totalSuccessRate / totalDelayed;
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
