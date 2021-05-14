package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class EnrollmentsSummaryResponse {
    private int subjects;
    private TermCount max;
    private TermCount min ;
    private double averageClassesPerDiscipline;
    private double averageClassesPerPeriod;
    private double averageEnrollmentsPerClass;
    private double averageEnrollmentsPerPeriod;

    public EnrollmentsSummaryResponse(int subjects, TermCount max, TermCount min, double averageClassesPerDiscipline, double averageClassesPerPeriod, double averageEnrollmentsPerClass, double averageEnrollmentsPerPeriod) {
        this.subjects = subjects;
        this.max = max;
        this.min = min;
        this.averageClassesPerDiscipline = averageClassesPerDiscipline;
        this.averageClassesPerPeriod = averageClassesPerPeriod;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
        this.averageEnrollmentsPerPeriod = averageEnrollmentsPerPeriod;
    }

    public int getSubjects() {
        return subjects;
    }

    public TermCount getMax() {
        return max;
    }

    public TermCount getMin() {
        return min;
    }

    public double getAverageClassesPerDiscipline() {
        return averageClassesPerDiscipline;
    }

    public double getAverageClassesPerPeriod() {
        return averageClassesPerPeriod;
    }

    public double getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public double getAverageEnrollmentsPerPeriod() {
        return averageEnrollmentsPerPeriod;
    }

    @Override
    public String toString() {
        return "EnrollmentsSummaryResponse{" +
                "subjects=" + subjects +
                ", max=" + max +
                ", min=" + min +
                ", averageClassesPerDiscipline=" + averageClassesPerDiscipline +
                ", averageClassesPerPeriod=" + averageClassesPerPeriod +
                ", averageEnrollmentsPerClass=" + averageEnrollmentsPerClass +
                ", averageEnrollmentsPerPeriod=" + averageEnrollmentsPerPeriod +
                '}';
    }
}
