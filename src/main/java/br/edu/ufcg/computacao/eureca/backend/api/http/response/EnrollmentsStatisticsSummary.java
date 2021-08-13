package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class EnrollmentsStatisticsSummary {
    private int subjectsCount;
    private double averageClassesPerSubjet;
    private double averageClassesPerTerm;
    private double averageEnrollmentsPerClass;
    private double averageEnrollmentsPerTerm;

    public EnrollmentsStatisticsSummary(int subjectsCount, double averageClassesPerSubjet, double averageClassesPerTerm,
                                        double averageEnrollmentsPerClass, double averageEnrollmentsPerTerm) {
        this.subjectsCount = subjectsCount;
        this.averageClassesPerSubjet = averageClassesPerSubjet;
        this.averageClassesPerTerm = averageClassesPerTerm;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
        this.averageEnrollmentsPerTerm = averageEnrollmentsPerTerm;
    }

    public int getSubjectsCount() {
        return subjectsCount;
    }

    public double getAverageClassesPerSubjet() {
        return averageClassesPerSubjet;
    }

    public double getAverageClassesPerTerm() {
        return averageClassesPerTerm;
    }

    public double getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public double getAverageEnrollmentsPerTerm() {
        return averageEnrollmentsPerTerm;
    }

    @Override
    public String toString() {
        return "EnrollmentsStatisticsSummaryResponse{" +
                ", subjectsCount=" + subjectsCount +
                ", averageClassesPerDiscipline=" + averageClassesPerSubjet +
                ", averageClassesPerPeriod=" + averageClassesPerTerm +
                ", averageEnrollmentsPerClass=" + averageEnrollmentsPerClass +
                ", averageEnrollmentsPerPeriod=" + averageEnrollmentsPerTerm +
                '}';
    }
}
