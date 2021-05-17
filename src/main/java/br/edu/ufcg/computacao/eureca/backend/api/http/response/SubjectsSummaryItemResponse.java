package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Objects;

public class SubjectsSummaryItemResponse {

    private String name;
    private double averageSuccess;
    private double averageFailDueToGrade;
    private double averageFailDueToAbsences;
    private double lockingRate;
    private double absoluteRetention;
    private double relativeRetention;
    private String from;
    private String to;

    public SubjectsSummaryItemResponse(String name, double averageSuccess, double averageFailDueToGrade,
                                       double averageFailDueToAbsences, double lockingRate, double absoluteRetention,
                                       double relativeRetention, String from, String to) {
        this.name = name;
        this.averageSuccess = averageSuccess;
        this.averageFailDueToGrade = averageFailDueToGrade;
        this.averageFailDueToAbsences = averageFailDueToAbsences;
        this.lockingRate = lockingRate;
        this.absoluteRetention = absoluteRetention;
        this.relativeRetention = relativeRetention;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public double getAverageSuccess() {
        return averageSuccess;
    }

    public double getAverageFailDueToGrade() {
        return averageFailDueToGrade;
    }

    public double getAverageFailDueToAbsences() {
        return averageFailDueToAbsences;
    }

    public double getLockingRate() {
        return lockingRate;
    }

    public double getAbsoluteRetention() {
        return absoluteRetention;
    }

    public double getRelativeRetention() {
        return relativeRetention;
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
        SubjectsSummaryItemResponse that = (SubjectsSummaryItemResponse) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "SubjectsStatistics{" +
                "name='" + name + '\'' +
                ", averageSuccess=" + averageSuccess +
                ", averageFailDueToGrade=" + averageFailDueToGrade +
                ", averageFailDueToAbsences=" + averageFailDueToAbsences +
                ", lockingRate=" + lockingRate +
                ", absoluteRetention=" + absoluteRetention +
                ", relativeRetention=" + relativeRetention +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
