package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Term;

public class AlumniPerTermSummary implements Comparable, Term {
    private String graduationTerm;
    private int alumniCount;
    private double averageGpa;
    private double averageTerms;
    private double averageCost;

    public AlumniPerTermSummary(String graduationTerm, int alumniCount, double averageGpa, double averageTerms,
                                double averageCost) {
        this.graduationTerm = graduationTerm;
        this.alumniCount = alumniCount;
        this.averageGpa = averageGpa;
        this.averageTerms = averageTerms;
        this.averageCost = averageCost;
    }

    public double getAverageGpa() {
        return averageGpa;
    }

    public void setAverageGpa(double averageGpa) {
        this.averageGpa = averageGpa;
    }

    public int getAlumniCount() {
        return alumniCount;
    }

    public void setAlumniCount(int alumniCount) {
        this.alumniCount = alumniCount;
    }

    public String getGraduationTerm() {
        return graduationTerm;
    }

    public void setGraduationTerm(String graduationTerm) {
        this.graduationTerm = graduationTerm;
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
        AlumniPerTermSummary other = (AlumniPerTermSummary) o;
        return this.getGraduationTerm().compareTo(other.getGraduationTerm());
    }

    @Override
    public String getTerm() {
        return this.getGraduationTerm();
    }
}
