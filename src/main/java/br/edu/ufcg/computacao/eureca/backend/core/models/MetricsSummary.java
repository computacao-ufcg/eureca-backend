package br.edu.ufcg.computacao.eureca.backend.core.models;

public class MetricsSummary {
    private int activeStudentsCount;
    private double averageCost;
    private double averageCourseDurationPrediction;

    public MetricsSummary(int activeStudentsCount, double averageCost, double averageCourseDurationPrediction) {
        this.activeStudentsCount = activeStudentsCount;
        this.averageCost = averageCost;
        this.averageCourseDurationPrediction = averageCourseDurationPrediction;
    }

    public int getActiveStudentsCount() {
        return activeStudentsCount;
    }

    public void setActiveStudentsCount(int activeStudentsCount) {
        this.activeStudentsCount = activeStudentsCount;
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
}
