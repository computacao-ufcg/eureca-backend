package br.edu.ufcg.computacao.eureca.backend.core.models;

public class CurriculumKey {
    private String course;
    private String code;

    public CurriculumKey(String course, String code) {
        this.course = course;
        this.code = code;
    }

    public CurriculumKey() {
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
