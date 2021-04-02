package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutPerTermSummary implements Comparable {
    private String dropoutTerm;
    private int dropoutCount;
    private DropoutReasonSummary reasons;
    private double averageTerms;
    private double averageCost;

    public DropoutPerTermSummary(String dropoutTerm, int dropoutCount, DropoutReasonSummary reasons, double averageTerms, double averageCost) {
        this.dropoutTerm = dropoutTerm;
        this.dropoutCount = dropoutCount;
        this.reasons = reasons;
        this.averageTerms = averageTerms;
        this.averageCost = averageCost;
    }

    public String getDropoutTerm() {
        return dropoutTerm;
    }

    public void setDropoutTerm(String dropoutTerm) {
        this.dropoutTerm = dropoutTerm;
    }

    public int getDropoutCount() {
        return dropoutCount;
    }

    public void setDropoutCount(int dropoutCount) {
        this.dropoutCount = dropoutCount;
    }

    public DropoutReasonSummary getReasons() {
        return reasons;
    }

    public void setReasons(DropoutReasonSummary reasons) {
        this.reasons = reasons;
    }

    public double getAverageTerms() {
        return averageTerms;
    }

    public void setAverageTerms(double averageTerms) {
        this.averageTerms = averageTerms;
    }

    public double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    @Override
    public int compareTo(Object o) {
        DropoutPerTermSummary other = (DropoutPerTermSummary) o;
        return this.getDropoutTerm().compareTo(other.getDropoutTerm());
    }
}
