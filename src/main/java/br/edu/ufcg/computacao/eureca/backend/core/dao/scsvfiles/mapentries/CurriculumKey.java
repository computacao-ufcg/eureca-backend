package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class CurriculumKey implements EurecaMapKey {
    private String courseCode;
    private String curriculumCode;

    public CurriculumKey(String courseCode, String curriculumCode) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public CurriculumKey() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurriculumKey that = (CurriculumKey) o;
        return getCourseCode().equals(that.getCourseCode()) &&
                getCurriculumCode().equals(that.getCurriculumCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseCode(), getCurriculumCode());
    }

    @Override
    public String toString() {
        return this.courseCode + ":" + this.curriculumCode;
    }
}
