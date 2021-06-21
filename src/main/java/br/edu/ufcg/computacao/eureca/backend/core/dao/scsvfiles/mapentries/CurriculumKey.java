package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class CurriculumKey implements EurecaMapKey {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurriculumKey that = (CurriculumKey) o;
        return getCourse().equals(that.getCourse()) &&
                getCode().equals(that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getCode());
    }
}
