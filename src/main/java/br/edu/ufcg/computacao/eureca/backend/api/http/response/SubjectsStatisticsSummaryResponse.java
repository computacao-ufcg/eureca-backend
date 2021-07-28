package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.SubjectsGlossaryFields;

public class SubjectsStatisticsSummaryResponse {
    private String course;
    private String curriculum;
    private String from;
    private String to;
    private SubjectStatisticsSummary mandatory;
    private SubjectStatisticsSummary optional;
    private SubjectStatisticsSummary elective;
    private SubjectStatisticsSummary complementary;
    private SubjectsGlossaryFields glossary;

    public SubjectsStatisticsSummaryResponse(String course, String curriculum, String from, String to,
                                             SubjectStatisticsSummary mandatory, SubjectStatisticsSummary optional,
                                             SubjectStatisticsSummary elective, SubjectStatisticsSummary complementary) {
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

    public SubjectStatisticsSummary getMandatory() {
        return mandatory;
    }

    public void setMandatory(SubjectStatisticsSummary mandatory) {
        this.mandatory = mandatory;
    }

    public SubjectStatisticsSummary getOptional() {
        return optional;
    }

    public void setOptional(SubjectStatisticsSummary optional) {
        this.optional = optional;
    }

    public SubjectStatisticsSummary getElective() {
        return elective;
    }

    public void setElective(SubjectStatisticsSummary elective) {
        this.elective = elective;
    }

    public SubjectStatisticsSummary getComplementary() {
        return complementary;
    }

    public void setComplementary(SubjectStatisticsSummary complementary) {
        this.complementary = complementary;
    }

    public SubjectsGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(SubjectsGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
