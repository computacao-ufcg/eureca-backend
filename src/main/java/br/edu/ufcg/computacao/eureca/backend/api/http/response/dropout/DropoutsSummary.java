package br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout;

import br.edu.ufcg.computacao.eureca.backend.core.models.CostClass;

public class DropoutsSummary {
    private String from;
    private String to;
    private int dropoutCount;
    private double averageTermsCount;
    private double averageCost;
    private CostClass costClass;
    private DropoutReasonSummary dropouts;

    public DropoutsSummary(String from, String to, int dropoutCount, double averageTermsCount, double averageCost, CostClass costClass, DropoutReasonSummary dropouts) {
        this.from = from;
        this.to = to;
        this.dropoutCount = dropoutCount;
        this.averageTermsCount = averageTermsCount;
        this.averageCost = averageCost;
        this.costClass = costClass;
        this.dropouts = dropouts;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public CostClass getCostClass() {
        return costClass;
    }

    public void setCostClass(CostClass costClass) {
        this.costClass = costClass;
    }

    public DropoutReasonSummary getDropouts() {
        return dropouts;
    }

    public void setDropouts(DropoutReasonSummary dropouts) {
        this.dropouts = dropouts;
    }
}
