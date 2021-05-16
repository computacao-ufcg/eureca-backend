package br.edu.ufcg.computacao.eureca.backend.core.models;

public class MetricSummary {
    private double min;
    private double max;
    private double average;

    public MetricSummary(double min, double max, double average) {
        this.min = min;
        this.max = max;
        this.average = average;
    }

    @Override
    public String toString() {
        return "MetricSummary{" +
                "min=" + min +
                ", max=" + max +
                ", average=" + average +
                '}';
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
