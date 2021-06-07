package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class SubjectKey implements EurecaMapKey {
    private String curriculumCode;
    private String subjectCode;

    public SubjectKey(String curriculumCode, String subjectCode) {
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
    }

    public SubjectKey() {
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectKey that = (SubjectKey) o;
        return getCurriculumCode().equals(that.getCurriculumCode()) &&
                getSubjectCode().equals(that.getSubjectCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurriculumCode(), getSubjectCode());
    }

    @Override
    public String toString() {
        return "SubjectKey{" +
                "curriculumCode='" + curriculumCode + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                '}';
    }
}
