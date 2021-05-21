package br.edu.ufcg.computacao.eureca.backend.core.models;

public class TeacherData {
    private String teachers;

    public TeacherData(String teachers) {
        this.teachers = teachers;
    }

    public TeacherData() {
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }
}
