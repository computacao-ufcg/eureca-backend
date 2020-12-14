package br.edu.ufcg.computacao.eureca.backend.core.models;

public class Metrics {
    private double feasibility;
    private double successRate;
    private double averageLoad;
    private double cost;
    private double pace;
    private int courseDurationPrediction;
    private double risk;

    public Metrics(double feasibility, double successRate, double averageLoad, double cost, double pace,
                   int courseDurationPrediction, double risk) {
        this.feasibility = feasibility;
        this.successRate = successRate;
        this.averageLoad = averageLoad;
        this.cost = cost;
        this.pace = pace;
        this.courseDurationPrediction = courseDurationPrediction;
        this.risk = risk;
    }

    public double getFeasibility() {
        return feasibility;
    }

    public void setFeasibility(double feasibility) {
        this.feasibility = feasibility;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }

    public double getAverageLoad() {
        return averageLoad;
    }

    public void setAverageLoad(double averageLoad) {
        this.averageLoad = averageLoad;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPace() {
        return pace;
    }

    public void setPace(double pace) {
        this.pace = pace;
    }

    public int getCourseDurationPrediction() {
        return courseDurationPrediction;
    }

    public void setCourseDurationPrediction(int courseDurationPrediction) {
        this.courseDurationPrediction = courseDurationPrediction;
    }

    public double getRisk() {
        return risk;
    }

    public void setRisk(double risk) {
        this.risk = risk;
    }
}
