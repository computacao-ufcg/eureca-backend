package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.Glossary;

import java.util.Objects;

public class TeachersSummaryItemResponse {
    private String teacherName;
    private double averageFailDueToGrade;
    private double averageFailDueToAbsences;
    private double lockingRate;
    private int totalEnrollments;
    private String from;
    private String to;
    private Glossary glossary;

    public TeachersSummaryItemResponse(String teacherName, double averageFailDueToGrade, double averageFailDueToAbsences,
                                       double lockingRate, int totalEnrollments, String from, String to, Glossary glossary) {
        this.teacherName = teacherName;
        this.averageFailDueToGrade = averageFailDueToGrade;
        this.averageFailDueToAbsences = averageFailDueToAbsences;
        this.lockingRate = lockingRate;
        this.totalEnrollments = totalEnrollments;
        this.from = from;
        this.to = to;
        this.glossary = glossary;
    }

    public String getTeacherName() {
        return teacherName;
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

    public int getTotalEnrollments() {
        return totalEnrollments;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Glossary getGlossary() {
        return glossary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachersSummaryItemResponse that = (TeachersSummaryItemResponse) o;
        return teacherName.equals(that.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherName);
    }

    @Override
    public String toString() {
        return "TeachersSummaryItemResponse{" +
                "teacherName='" + teacherName + '\'' +
                ", averageFailDueToGrade=" + averageFailDueToGrade +
                ", averageFailDueToAbsences=" + averageFailDueToAbsences +
                ", lockingRate=" + lockingRate +
                ", totalEnrollments=" + totalEnrollments +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", glossary='" + glossary + '\'' +
                '}';
    }
}
