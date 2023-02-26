package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "DISCIPLINAS", schema = "SCA")
public class ScaoSubject {

    @Id
    @Column(name="DIS_DISCIPLINA")
    private String subjectCode;

    @Column(name="DIS_DESCRICAO")
    private String name;

    @Column(name="DIS_QTD_CR")
    private String credits;

    @Column(name="DIS_QTD_CH")
    private String hours;

    @Column(name="DIS_CONTABILIZA_CRE")
    private String accountedInGpa;

    public ScaoSubject(){
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        if (credits == null) return -1;
        return Integer.parseInt(credits);
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public int getHours() {
        if (hours == null) return -1;
        return Integer.parseInt(hours);
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getAccountedInGpa() {
        return accountedInGpa;
    }

    public void setAccountedInGpa(String accountedInGpa) {
        this.accountedInGpa = accountedInGpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaoSubject that = (ScaoSubject) o;
        return Objects.equals(subjectCode, that.subjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectCode);
    }

//    public SubjectSummary createSubject() {
//        return  new SubjectSummary(this.getCourseCode(), this.getCurriculumCode(), this.getSubjectCode(),
//                this.getSubjectType(), this.getCredits(), this.getHours());
//    }
}
