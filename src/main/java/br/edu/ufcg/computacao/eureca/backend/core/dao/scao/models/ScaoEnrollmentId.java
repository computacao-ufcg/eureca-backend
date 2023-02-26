package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ScaoEnrollmentId implements Serializable {

    @Column(name="MAT_ALU_MATRICULA")
    private String registration;

    @Column(name="MAT_TUR_DIS_DISCIPLINA")
    private String subjectCode;

    @Column(name="MAT_TUR_ANO")
    private String yearEnrollment;

    @Column(name="MAT_TUR_PERIODO")
    private String termEnrollment;

    public ScaoEnrollmentId(){}

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getYearEnrollment() {
        return yearEnrollment;
    }

    public void setYearEnrollment(String yearEnrollment) {
        this.yearEnrollment = yearEnrollment;
    }

    public String getTermEnrollment() {
        return termEnrollment;
    }

    public void setTermEnrollment(String termEnrollment) {
        this.termEnrollment = termEnrollment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaoEnrollmentId that = (ScaoEnrollmentId) o;
        return Objects.equals(registration, that.registration) && Objects.equals(subjectCode, that.subjectCode) && Objects.equals(yearEnrollment, that.yearEnrollment) && Objects.equals(termEnrollment, that.termEnrollment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration, subjectCode, yearEnrollment, termEnrollment);
    }
}
