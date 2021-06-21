package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class TeacherData implements EurecaMapValue {
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
