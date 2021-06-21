package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.SubjectsGlossaryFields;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectStatisticsItem;

public class SubjectSummaryResponse {
    private String course;
    private String curriculum;
    private String from;
    private String to;
    private SubjectStatisticsItem mandatory;
    private SubjectStatisticsItem optional;
    private SubjectStatisticsItem elective;
    private SubjectStatisticsItem complementary;
    private SubjectsGlossaryFields glossary;

    public SubjectSummaryResponse(String course, String curriculum, String from, String to,
                                  SubjectStatisticsItem mandatory, SubjectStatisticsItem optional,
                                  SubjectStatisticsItem elective, SubjectStatisticsItem complementary) {
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

    public SubjectStatisticsItem getMandatory() {
        return mandatory;
    }

    public void setMandatory(SubjectStatisticsItem mandatory) {
        this.mandatory = mandatory;
    }

    public SubjectStatisticsItem getOptional() {
        return optional;
    }

    public void setOptional(SubjectStatisticsItem optional) {
        this.optional = optional;
    }

    public SubjectStatisticsItem getElective() {
        return elective;
    }

    public void setElective(SubjectStatisticsItem elective) {
        this.elective = elective;
    }

    public SubjectStatisticsItem getComplementary() {
        return complementary;
    }

    public void setComplementary(SubjectStatisticsItem complementary) {
        this.complementary = complementary;
    }

    public SubjectsGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(SubjectsGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
