package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class AlumniSummaryResponse {
    double averageGpa;
    int maxDegreeCount;
    double averageDegreeCount;
    int minDegreeCount;
    String maxDegreeCountTerm;
    String minDegreeCountTerm;
    Collection<AlumniDataResponse> terms;
    int totalDegreeCount;

    public AlumniSummaryResponse(double averageGpa, int maxDegreeCount, double averageDegreeCount, int minDegreeCount,
                                 String maxDegreeCountTerm, String minDegreeCountTerm,
                                 Collection<AlumniDataResponse> terms, int totalDegreeCount) {
        this.averageGpa = averageGpa;
        this.maxDegreeCount = maxDegreeCount;
        this.averageDegreeCount = averageDegreeCount;
        this.minDegreeCount = minDegreeCount;
        this.maxDegreeCountTerm = maxDegreeCountTerm;
        this.minDegreeCountTerm = minDegreeCountTerm;
        this.terms = terms;
        this.totalDegreeCount = totalDegreeCount;
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

    public Collection<AlumniDataResponse> getTerms() {
        return terms;
    }

    public void setTerms(Collection<AlumniDataResponse> terms) {
        this.terms = terms;
    }

    public int getTotalDegreeCount() {
        return totalDegreeCount;
    }

    public void setTotalDegreeCount(int totalDegreeCount) {
        this.totalDegreeCount = totalDegreeCount;
    }
}
