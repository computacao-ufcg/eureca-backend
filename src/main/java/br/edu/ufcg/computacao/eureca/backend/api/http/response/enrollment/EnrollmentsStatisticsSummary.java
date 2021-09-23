package br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment;

public class EnrollmentsStatisticsSummary {
    private double averageSubjectsCount;
    private double averageClassesPerSubject;
    private double averageClassesPerTerm;
    private double averageEnrollmentsPerSubject;
    private double averageEnrollmentsPerTerm;

    public EnrollmentsStatisticsSummary(double averageSubjectsCount, double averageClassesPerSubject, double averageClassesPerTerm,
                                        double averageEnrollmentsPerSubject, double averageEnrollmentsPerTerm) {
        this.averageSubjectsCount = averageSubjectsCount;
        this.averageClassesPerSubject = averageClassesPerSubject;
        this.averageClassesPerTerm = averageClassesPerTerm;
        this.averageEnrollmentsPerSubject = averageEnrollmentsPerSubject;
        this.averageEnrollmentsPerTerm = averageEnrollmentsPerTerm;
    }

    public double getAverageSubjectsCount() {
        return averageSubjectsCount;
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
                ", subjectsCount=" + averageSubjectsCount +
                ", averageClassesPerDiscipline=" + averageClassesPerSubject +
                ", averageClassesPerPeriod=" + averageClassesPerTerm +
                ", averageEnrollmentsPerSubject=" + averageEnrollmentsPerSubject +
                ", averageEnrollmentsPerPeriod=" + averageEnrollmentsPerTerm +
                '}';
    }
}
