package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.EnrollmentsGlossaryFields;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class EnrollmentsSummaryResponse {
    private String curriculum;
    private String from;
    private String to;
    private int subjects;
    private TermCount max;
    private TermCount min ;
    private double averageClassesPerDiscipline;
    private double averageClassesPerPeriod;
    private double averageEnrollmentsPerClass;
    private double averageEnrollmentsPerPeriod;
    private EnrollmentsGlossaryFields glossary;

    public EnrollmentsSummaryResponse(String curriculum, String from, String to, int subjects, TermCount max, TermCount min, double averageClassesPerDiscipline, double averageClassesPerPeriod, double averageEnrollmentsPerClass, double averageEnrollmentsPerPeriod) {
        this.curriculum = curriculum;
        this.from = from;
        this.to = to;
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

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public EnrollmentsGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(EnrollmentsGlossaryFields glossary) {
        this.glossary = glossary;
    }

    @Override
    public String toString() {
        return "EnrollmentsSummaryResponse{" +
                "curriculum='" + curriculum + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subjects=" + subjects +
                ", max=" + max +
                ", min=" + min +
                ", averageClassesPerDiscipline=" + averageClassesPerDiscipline +
                ", averageClassesPerPeriod=" + averageClassesPerPeriod +
                ", averageEnrollmentsPerClass=" + averageEnrollmentsPerClass +
                ", averageEnrollmentsPerPeriod=" + averageEnrollmentsPerPeriod +
                '}';
    }
}
