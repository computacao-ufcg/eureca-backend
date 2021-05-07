package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class RiskClassCountSummary {
    private int inaccurate;
    private int safe;
    private int low;
    private int average;
    private int high;
    private int unfeasible;
    private int notApplicable;

    public RiskClassCountSummary(int inaccurate, int safe, int low, int average, int high, int unfeasible, int notApplicable) {
        this.inaccurate = inaccurate;
        this.safe = safe;
        this.low = low;
        this.average = average;
        this.high = high;
        this.unfeasible = unfeasible;
        this.notApplicable = notApplicable;
    }

    public int getInaccurate() {
        return inaccurate;
    }

    public void setInaccurate(int inaccurate) {
        this.inaccurate = inaccurate;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getUnfeasible() {
        return unfeasible;
    }

    public void setUnfeasible(int unfeasible) {
        this.unfeasible = unfeasible;
    }

    public int getNotApplicable() {
        return notApplicable;
    }

    public void setNotApplicable(int notApplicable) {
        this.notApplicable = notApplicable;
    }

    public void add(RiskClassCountSummary other) {
        this.inaccurate += other.getInaccurate();
        this.safe += other.getSafe();
        this.low += other.getLow();
        this.average += other.getAverage();
        this.high += other.getHigh();
        this.unfeasible += other.getUnfeasible();
        this.notApplicable += other.getNotApplicable();
    }
}