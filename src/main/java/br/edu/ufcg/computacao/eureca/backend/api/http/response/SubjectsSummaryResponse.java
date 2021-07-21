package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.SubjectsGlossaryFields;

public class SubjectsSummaryResponse {
    private String course;
    private String curriculum;
    private String from;
    private String to;
    private SubjectsStatisticsSummary mandatory;
    private SubjectsStatisticsSummary optional;
    private SubjectsStatisticsSummary elective;
    private SubjectsStatisticsSummary complementary;
    private SubjectsGlossaryFields glossary;

    public SubjectsSummaryResponse(String course, String curriculum, String from, String to,
                                   SubjectsStatisticsSummary mandatory, SubjectsStatisticsSummary optional,
                                   SubjectsStatisticsSummary elective, SubjectsStatisticsSummary complementary) {
        this.course = course;
        this.curriculum = curriculum;
        this.from = from;
        this.to = to;
        this.mandatory = mandatory;
        this.optional = optional;
        this.elective = elective;
        this.complementary = complementary;
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

    public SubjectsStatisticsSummary getMandatory() {
        return mandatory;
    }

    public void setMandatory(SubjectsStatisticsSummary mandatory) {
        this.mandatory = mandatory;
    }

    public SubjectsStatisticsSummary getOptional() {
        return optional;
    }

    public void setOptional(SubjectsStatisticsSummary optional) {
        this.optional = optional;
    }

    public SubjectsStatisticsSummary getElective() {
        return elective;
    }

    public void setElective(SubjectsStatisticsSummary elective) {
        this.elective = elective;
    }

    public SubjectsStatisticsSummary getComplementary() {
        return complementary;
    }

    public void setComplementary(SubjectsStatisticsSummary complementary) {
        this.complementary = complementary;
    }

    public SubjectsGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(SubjectsGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
