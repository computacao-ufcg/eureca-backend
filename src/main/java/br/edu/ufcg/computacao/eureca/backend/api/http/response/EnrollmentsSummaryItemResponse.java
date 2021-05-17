package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Objects;

public class EnrollmentsSummaryItemResponse {

    private String discipline;
    private int totalEnrollments;
    private double averageEnrollmentsPerClass;
    private int totalClasses;
    private String from;
    private String to;

    public EnrollmentsSummaryItemResponse(String discipline, int totalEnrollments, double averageEnrollmentsPerClass,
                                          int totalClasses, String from, String to) {
        this.discipline = discipline;
        this.totalEnrollments = totalEnrollments;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
        this.totalClasses = totalClasses;
        this.from = from;
        this.to = to;
    }

    public String getDiscipline() {
        return discipline;
    }

    public int getTotalEnrollments() {
        return totalEnrollments;
    }

    public double getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentsSummaryItemResponse that = (EnrollmentsSummaryItemResponse) o;
        return Objects.equals(discipline, that.discipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discipline);
    }

    @Override
    public String toString() {
        return "EnrollmentsStatisticsItemResponse{" +
                "discipline='" + discipline + '\'' +
                ", totalEnrollments=" + totalEnrollments +
                ", averageEnrollmentsPerClass=" + averageEnrollmentsPerClass +
                ", totalClasses=" + totalClasses +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
