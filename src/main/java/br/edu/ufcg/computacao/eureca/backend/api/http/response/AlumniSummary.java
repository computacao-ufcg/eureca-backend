package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class AlumniSummary {
    private int alumniCount;
    private double averageTerms;
    private double averageCost;
    private double averageGpa;
    private double averageDegreeCount;
    private int maxDegreeCount;
    private int minDegreeCount;
    private String maxDegreeCountTerm;
    private String minDegreeCountTerm;

    public AlumniSummary(int alumniCount, double averageTerms, double averageCost, double averageGpa,
                         double averageDegreeCount, int maxDegreeCount, int minDegreeCount, String maxDegreeCountTerm,
                         String minDegreeCountTerm) {
        this.alumniCount = alumniCount;
        this.averageTerms = averageTerms;
        this.averageCost = averageCost;
        this.averageGpa = averageGpa;
        this.averageDegreeCount = averageDegreeCount;
        this.maxDegreeCount = maxDegreeCount;
        this.minDegreeCount = minDegreeCount;
        this.maxDegreeCountTerm = maxDegreeCountTerm;
        this.minDegreeCountTerm = minDegreeCountTerm;
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
