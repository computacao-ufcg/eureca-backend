package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;

public class Metrics {
    private double attemptedCredits;
    private double feasibility;
    private double successRate;
    private double averageLoad;
    private double cost;
    private double pace;
    private double courseDurationPrediction;
    private double risk;

    public Metrics(double attemptedCredits, double feasibility, double successRate, double averageLoad, double cost,
                   double pace, double courseDurationPrediction, double risk) {
        this.attemptedCredits = attemptedCredits;
        this.feasibility = feasibility;
        this.successRate = successRate;
        this.averageLoad = averageLoad;
        this.cost = cost;
        this.pace = pace;
        this.courseDurationPrediction = courseDurationPrediction;
        this.risk = risk;
    }

    public double getAttemptedCredits() {
        return attemptedCredits;
    }

    public void setAttemptedCredits(double attemptedCredits) {
        this.attemptedCredits = attemptedCredits;
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

    public double getCourseDurationPrediction() {
        return courseDurationPrediction;
    }

    public void setCourseDurationPrediction(double courseDurationPrediction) {
        this.courseDurationPrediction = courseDurationPrediction;
    }

    public double getRisk() {
        return risk;
    }

    public void setRisk(double risk) {
        this.risk = risk;
    }

    public RiskClass computeRiskClass() {
        double feasibility = this.getFeasibility();
        if (feasibility > 1) return RiskClass.UNFEASIBLE;
        if (feasibility < 0) return RiskClass.NOT_APPLICABLE;
        double risk = this.getRisk();
        if (risk > 0) return RiskClass.CRITICAL;
        double highRiskThreshold = (1.0 * (Curriculum.EXPECTED_NUMBER_OF_TERMS - Curriculum.MAX_NUMBER_OF_TERMS)) /
                (Curriculum.MAX_NUMBER_OF_TERMS - 1);
        if (risk >= highRiskThreshold) return RiskClass.LATE;
        double lowRiskThreshold = (1.0 * (Curriculum.MIN_NUMBER_OF_TERMS - Curriculum.MAX_NUMBER_OF_TERMS)) /
                (Curriculum.MAX_NUMBER_OF_TERMS - 1);
        if (risk >= lowRiskThreshold) return RiskClass.NORMAL;
        return RiskClass.ADVANCED;
    }
}
