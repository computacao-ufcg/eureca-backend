package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.CostClass;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;

public class AlumniSummary {
    private int alumniCount;
    private double averageTermsCount;
    private double averageCost;
    private CostClass costClass;
    private double averageGpa;
    private double averageDegreeCount;
    private int maxDegreeCount;
    private int minDegreeCount;
    private String maxDegreeCountTerm;
    private String minDegreeCountTerm;

    public AlumniSummary(int alumniCount, double averageTermsCount, double averageCost, double averageGpa,
                         double averageDegreeCount, int maxDegreeCount, int minDegreeCount, String maxDegreeCountTerm,
                         String minDegreeCountTerm) {
        this.alumniCount = alumniCount;
        this.averageTermsCount = averageTermsCount;
        this.averageCost = averageCost;
        this.costClass = MetricsCalculator.computeCostClass(this.averageCost);
        this.averageGpa = averageGpa;
        this.averageDegreeCount = averageDegreeCount;
        this.maxDegreeCount = maxDegreeCount;
        this.minDegreeCount = minDegreeCount;
        this.maxDegreeCountTerm = maxDegreeCountTerm;
        this.minDegreeCountTerm = minDegreeCountTerm;
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

    public double getAverageGpa() {
        return averageGpa;
    }

    public void setAverageGpa(double averageGpa) {
        this.averageGpa = averageGpa;
    }

    public int getMaxDegreeCount() {
        return maxDegreeCount;
    }

    public void setMaxDegreeCount(int maxDegreeCount) {
        this.maxDegreeCount = maxDegreeCount;
    }

    public double getAverageDegreeCount() {
        return averageDegreeCount;
    }

    public void setAverageDegreeCount(double averageDegreeCount) {
        this.averageDegreeCount = averageDegreeCount;
    }

    public int getMinDegreeCount() {
        return minDegreeCount;
    }

    public void setMinDegreeCount(int minDegreeCount) {
        this.minDegreeCount = minDegreeCount;
    }

    public String getMaxDegreeCountTerm() {
        return maxDegreeCountTerm;
    }

    public void setMaxDegreeCountTerm(String maxDegreeCountTerm) {
        this.maxDegreeCountTerm = maxDegreeCountTerm;
    }

    public String getMinDegreeCountTerm() {
        return minDegreeCountTerm;
    }

    public void setMinDegreeCountTerm(String minDegreeCountTerm) {
        this.minDegreeCountTerm = minDegreeCountTerm;
    }

    public int getAlumniCount() {
        return alumniCount;
    }

    public void setAlumniCount(int alumniCount) {
        this.alumniCount = alumniCount;
    }
}
