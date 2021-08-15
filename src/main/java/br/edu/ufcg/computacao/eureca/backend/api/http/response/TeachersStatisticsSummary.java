package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.TeachersGlossaryFields;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class TeachersStatisticsSummary {
    private String courseCode;
    private String curriculumCode;
    private String from;
    private String to;
    private TermCount min;
    private TermCount max;
    private double averageTeachersCount;
    private SubjectsStatisticsSummary averageSummary;

    public TeachersStatisticsSummary(String courseCode, String curriculumCode, String from, String to,
                                     TermCount min, TermCount max, double averageTeachersCount,
                                     SubjectsStatisticsSummary averageSummary) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.from = from;
        this.to = to;
        this.min = min;
        this.max = max;
        this.averageTeachersCount = averageTeachersCount;
        this.averageSummary = averageSummary;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
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

    public TermCount getMin() {
        return min;
    }

    public void setMin(TermCount min) {
        this.min = min;
    }

    public TermCount getMax() {
        return max;
    }

    public void setMax(TermCount max) {
        this.max = max;
    }

    public double getAverageTeachersCount() {
        return averageTeachersCount;
    }

    public void setAverageTeachersCount(double averageTeachersCount) {
        this.averageTeachersCount = averageTeachersCount;
    }

    public SubjectsStatisticsSummary getAverageSummary() {
        return averageSummary;
    }

    public void setAverageSummary(SubjectsStatisticsSummary averageSummary) {
        this.averageSummary = averageSummary;
    }
}
