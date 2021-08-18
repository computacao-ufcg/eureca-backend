package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class TeachersListData implements EurecaMapValue {
    private String teachers;

    public TeachersListData(String teachers) {
        this.teachers = teachers;
    }

    public TeachersListData() {
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }
}
