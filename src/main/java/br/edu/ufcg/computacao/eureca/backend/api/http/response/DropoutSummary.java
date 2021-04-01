package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutSummary {
    private double grossDropoutAlumnusRate;
    private double grossDropoutEnrolledRate;
    private double netDropoutAlumnusRate;
    private double netDropoutEnrolledRate;
    private int grossDropoutCount;
    private int netDropoutCount;

    public DropoutSummary(double grossDropoutAlumnusRate, double grossDropoutEnrolledRate, double netDropoutAlumnusRate,
                          double netDropoutEnrolledRate, int grossDropoutCount, int netDropoutCount) {
        this.grossDropoutAlumnusRate = grossDropoutAlumnusRate;
        this.grossDropoutEnrolledRate = grossDropoutEnrolledRate;
        this.netDropoutAlumnusRate = netDropoutAlumnusRate;
        this.netDropoutEnrolledRate = netDropoutEnrolledRate;
        this.grossDropoutCount = grossDropoutCount;
        this.netDropoutCount = netDropoutCount;
    }

    public double getGrossDropoutAlumnusRate() {
        return grossDropoutAlumnusRate;
    }

    public void setGrossDropoutAlumnusRate(double grossDropoutAlumnusRate) {
        this.grossDropoutAlumnusRate = grossDropoutAlumnusRate;
    }

    public double getGrossDropoutEnrolledRate() {
        return grossDropoutEnrolledRate;
    }

    public void setGrossDropoutEnrolledRate(double grossDropoutEnrolledRate) {
        this.grossDropoutEnrolledRate = grossDropoutEnrolledRate;
    }

    public double getNetDropoutAlumnusRate() {
        return netDropoutAlumnusRate;
    }

    public void setNetDropoutAlumnusRate(double netDropoutAlumnusRate) {
        this.netDropoutAlumnusRate = netDropoutAlumnusRate;
    }

    public double getNetDropoutEnrolledRate() {
        return netDropoutEnrolledRate;
    }

    public void setNetDropoutEnrolledRate(double netDropoutEnrolledRate) {
        this.netDropoutEnrolledRate = netDropoutEnrolledRate;
    }

    public int getGrossDropoutCount() {
        return grossDropoutCount;
    }

    public void setGrossDropoutCount(int grossDropoutCount) {
        this.grossDropoutCount = grossDropoutCount;
    }

    public int getNetDropoutCount() {
        return netDropoutCount;
    }

    public void setNetDropoutCount(int netDropoutCount) {
        this.netDropoutCount = netDropoutCount;
    }
}
