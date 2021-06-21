package br.edu.ufcg.computacao.eureca.backend.core.models;

public class EnvironmentVariables {
    private String currentCourse;
    private String currentCurriculum;

    public EnvironmentVariables(String currentCourse, String currentCurriculum) {
        this.currentCourse = currentCourse;
        this.currentCurriculum = currentCurriculum;
    }

    public String getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(String currentCourse) {
        this.currentCourse = currentCourse;
    }

    public String getCurrentCurriculum() {
        return currentCurriculum;
    }

    public void setCurrentCurriculum(String currentCurriculum) {
        this.currentCurriculum = currentCurriculum;
    }
}
