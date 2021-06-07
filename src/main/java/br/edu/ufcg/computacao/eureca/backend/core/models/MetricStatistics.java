package br.edu.ufcg.computacao.eureca.backend.core.models;

public class MetricStatistics {
    private int min;
    private int max;
    private int total;

    public MetricStatistics(int min, int max, int total) {
        this.min = min;
        this.max = max;
        this.total = total;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
