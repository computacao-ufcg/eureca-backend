package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.TeachersGlossaryFields;

import java.util.Collection;
import java.util.Map;

public class TeachersStatisticsSummaryResponse {
    private String course;
    private String curriculum;
    private String from;
    private String to;
    private Collection<String> academicUnits;
    private Map<String, TeachersStatisticsSummary> summaryMap;
    private TeachersGlossaryFields glossary;

    public TeachersStatisticsSummaryResponse(String course, String curriculum, String from, String to,
                          Collection<String> academicUnits, Map<String, TeachersStatisticsSummary> summaryMap) {
        this.course = course;
        this.curriculum = curriculum;
        this.from = from;
        this.to = to;
        this.academicUnits = academicUnits;
        this.summaryMap = summaryMap;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public Collection<String> getAcademicUnits() {
        return academicUnits;
    }

    public void setAcademicUnits(Collection<String> academicUnits) {
        this.academicUnits = academicUnits;
    }

    public Map<String, TeachersStatisticsSummary> getSummaryMap() {
        return summaryMap;
    }

    public void setSummaryMap(Map<String, TeachersStatisticsSummary> summaryMap) {
        this.summaryMap = summaryMap;
    }

    public TeachersGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(TeachersGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
