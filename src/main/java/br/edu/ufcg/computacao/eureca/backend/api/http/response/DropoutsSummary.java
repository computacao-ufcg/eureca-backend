package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutsSummary {
    private DropoutReasonSummary dropouts;
    private int dropoutCount;
    private double dropoutAlumnusRate;
    private double dropoutEnrolledRate;

    public DropoutsSummary(DropoutReasonSummary dropouts, int dropoutCount, double dropoutAlumnusRate,
                           double dropoutEnrolledRate) {
        this.dropouts = dropouts;
        this.dropoutCount = dropoutCount;
        this.dropoutAlumnusRate = dropoutAlumnusRate;
        this.dropoutEnrolledRate = dropoutEnrolledRate;
    }

    public DropoutReasonSummary getDropouts() {
        return dropouts;
    }

    public void setDropouts(DropoutReasonSummary dropouts) {
        this.dropouts = dropouts;
    }

    public int getDropoutCount() {
        return dropoutCount;
    }

    public void setDropoutCount(int dropoutCount) {
        this.dropoutCount = dropoutCount;
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
