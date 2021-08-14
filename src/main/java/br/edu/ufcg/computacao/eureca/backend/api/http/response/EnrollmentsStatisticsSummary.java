package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class EnrollmentsStatisticsSummary {
    private int subjectsCount;
    private double averageClassesPerSubject;
    private double averageClassesPerTerm;
    private double averageEnrollmentsPerSubject;
    private double averageEnrollmentsPerTerm;

    public EnrollmentsStatisticsSummary(int subjectsCount, double averageClassesPerSubject, double averageClassesPerTerm,
                                        double averageEnrollmentsPerSubject, double averageEnrollmentsPerTerm) {
        this.subjectsCount = subjectsCount;
        this.averageClassesPerSubject = averageClassesPerSubject;
        this.averageClassesPerTerm = averageClassesPerTerm;
        this.averageEnrollmentsPerSubject = averageEnrollmentsPerSubject;
        this.averageEnrollmentsPerTerm = averageEnrollmentsPerTerm;
    }

    public int getSubjectsCount() {
        return subjectsCount;
    }

    public double getAverageClassesPerSubject() {
        return averageClassesPerSubject;
    }

    public double getAverageClassesPerTerm() {
        return averageClassesPerTerm;
    }

    public double getAverageEnrollmentsPerSubject() {
        return averageEnrollmentsPerSubject;
    }

    public double getAverageEnrollmentsPerTerm() {
        return averageEnrollmentsPerTerm;
    }

    @Override
    public String toString() {
        return "EnrollmentsStatisticsSummaryResponse{" +
                ", subjectsCount=" + subjectsCount +
                ", averageClassesPerDiscipline=" + averageClassesPerSubject +
                ", averageClassesPerPeriod=" + averageClassesPerTerm +
                ", averageEnrollmentsPerSubject=" + averageEnrollmentsPerSubject +
                ", averageEnrollmentsPerPeriod=" + averageEnrollmentsPerTerm +
                '}';
    }
}
