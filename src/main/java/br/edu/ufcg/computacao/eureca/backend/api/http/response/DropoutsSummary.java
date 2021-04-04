package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutsSummary {
    private int dropoutCount;
    private double averageTermsCount;
    private double averageCost;
    private DropoutReasonSummary dropouts;
    private double dropoutAlumnusRate;
    private double dropoutEnrolledRate;

    public DropoutsSummary(int dropoutCount, double averageTermsCount, double averageCost, DropoutReasonSummary dropouts,
                           double dropoutAlumnusRate, double dropoutEnrolledRate) {
        this.dropoutCount = dropoutCount;
        this.averageTermsCount = averageTermsCount;
        this.averageCost = averageCost;
        this.dropouts = dropouts;
        this.dropoutAlumnusRate = dropoutAlumnusRate;
        this.dropoutEnrolledRate = dropoutEnrolledRate;
    }

    public int getDropoutCount() {
        return dropoutCount;
    }

    public void setDropoutCount(int dropoutCount) {
        this.dropoutCount = dropoutCount;
    }

    public double getAverageTermsCount() {
        return averageTermsCount;
    }

    public void setAverageTermsCount(double averageTermsCount) {
        this.averageTermsCount = averageTermsCount;
    }

    public double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    public DropoutReasonSummary getDropouts() {
        return dropouts;
    }

    public void setDropouts(DropoutReasonSummary dropouts) {
        this.dropouts = dropouts;
    }

    public double getDropoutAlumnusRate() {
        return dropoutAlumnusRate;
    }

    public void setDropoutAlumnusRate(double dropoutAlumnusRate) {
        this.dropoutAlumnusRate = dropoutAlumnusRate;
    }

    public double getDropoutEnrolledRate() {
        return dropoutEnrolledRate;
    }

    public void setDropoutEnrolledRate(double dropoutEnrolledRate) {
        this.dropoutEnrolledRate = dropoutEnrolledRate;
    }
}
