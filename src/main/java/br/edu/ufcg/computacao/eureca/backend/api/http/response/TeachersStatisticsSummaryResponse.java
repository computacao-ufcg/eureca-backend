package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.TeachersGlossaryFields;

import java.util.Collection;
import java.util.Map;

public class TeachersStatisticsSummaryResponse {
    private String course;
    private String curriculum;
    private Map<String, TeachersStatisticsSummary> summaryMap;
    private TeachersGlossaryFields glossary;

    public TeachersStatisticsSummaryResponse(String course, String curriculum,
                          Map<String, TeachersStatisticsSummary> summaryMap) {
        this.course = course;
        this.curriculum = curriculum;
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
