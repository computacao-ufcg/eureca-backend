package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.Glossary;

import java.util.Objects;

public class TeachersCSV {
    private String teacherName;
    private double averageFailDueToGrade;
    private double averageFailDueToAbsences;
    private double suspendedRate;
    private double successRate;
    private int totalEnrollments;
    private String from;
    private String to;
    private Glossary glossary;

    public TeachersCSV(String teacherName, double averageFailDueToGrade, double averageFailDueToAbsences,
                       double suspendedRate, double successRate, int totalEnrollments, String from, String to,
                       Glossary glossary) {
        this.teacherName = teacherName;
        this.averageFailDueToGrade = averageFailDueToGrade;
        this.averageFailDueToAbsences = averageFailDueToAbsences;
        this.suspendedRate = suspendedRate;
        this.successRate = successRate;
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

    public double getSuspendedRate() {
        return suspendedRate;
    }

    public double getSuccessRate() {
        return successRate;
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
        TeachersCSV that = (TeachersCSV) o;
        return teacherName.equals(that.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherName);
    }

    @Override
    public String toString() {
        return "TeachersCSV{" +
                "teacherName='" + teacherName + '\'' +
                ", averageFailDueToGrade=" + averageFailDueToGrade +
                ", averageFailDueToAbsences=" + averageFailDueToAbsences +
                ", lockingRate=" + suspendedRate +
                ", successRate=" + successRate +
                ", totalEnrollments=" + totalEnrollments +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", glossary='" + glossary + '\'' +
                '}';
    }
}
