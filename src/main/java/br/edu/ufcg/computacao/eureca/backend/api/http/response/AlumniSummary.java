package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;
import java.util.TreeSet;

public class AlumniSummary {
    double averageGpa;
    int maxDegreeCount;
    double averageDegreeCount;
    int minDegreeCount;
    String maxDegreeCountTerm;
    String minDegreeCountTerm;
    int totalDegreeCount;

    public AlumniSummary(double averageGpa, int maxDegreeCount, double averageDegreeCount, int minDegreeCount,
                         String maxDegreeCountTerm, String minDegreeCountTerm, int totalDegreeCount) {
        this.averageGpa = averageGpa;
        this.maxDegreeCount = maxDegreeCount;
        this.averageDegreeCount = averageDegreeCount;
        this.minDegreeCount = minDegreeCount;
        this.maxDegreeCountTerm = maxDegreeCountTerm;
        this.minDegreeCountTerm = minDegreeCountTerm;
        this.totalDegreeCount = totalDegreeCount;
    }

    public AlumniSummary(Collection<AlumniPerTermSummary> terms) {
        double accumulatedGPA = 0;
        int maxAlumniCount = 0;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        int totalAlumniCount = 0;

        for (AlumniPerTermSummary item : terms) {
            double averageGPA = item.getAverageGpa();
            int termAlumniCount = item.getAlumniCount();
            String term = item.getGraduationTerm();
            totalAlumniCount += termAlumniCount;
            if (termAlumniCount >= maxAlumniCount) {
                maxAlumniCount = termAlumniCount;
                maxAlumniCountTerm = term;
            }
            if (termAlumniCount <= minAlumniCount) {
                minAlumniCount = termAlumniCount;
                minAlumniCountTerm = term;
            }
            accumulatedGPA += averageGPA * termAlumniCount;
        }
        this.averageGpa = (totalAlumniCount == 0 ? -1.0 : accumulatedGPA/totalAlumniCount);
        this.maxDegreeCount = maxAlumniCount;
        this.averageDegreeCount = (terms.size() == 0 ? -1.0 : (1.0*totalAlumniCount)/terms.size());
        this.minDegreeCount = minAlumniCount;
        this.maxDegreeCountTerm = maxAlumniCountTerm;
        this.minDegreeCountTerm = minAlumniCountTerm;
        this.totalDegreeCount = totalAlumniCount;
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

    public int getTotalDegreeCount() {
        return totalDegreeCount;
    }

    public void setTotalDegreeCount(int totalDegreeCount) {
        this.totalDegreeCount = totalDegreeCount;
    }
}
