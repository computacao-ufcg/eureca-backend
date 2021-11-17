package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class TeacherData implements EurecaMapValue {
    private String academicUnitId;
    private String name;
    private String email;

    public TeacherData() {
    }

    public String getAcademicUnitId() {
        return academicUnitId;
    }

    public void setAcademicUnitId(String academicUnitId) {
        this.academicUnitId = academicUnitId;
    }

    public TeacherData(String acronym, String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }
}
