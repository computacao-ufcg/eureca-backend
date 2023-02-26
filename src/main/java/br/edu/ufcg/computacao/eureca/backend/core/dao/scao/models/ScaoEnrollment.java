package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.ScaoValueConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.Constants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EnrollmentData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.RegistrationSubjectCodeTermKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "EURECA_MATRICULAS", schema = "SCA")
public class ScaoEnrollment {

    @EmbeddedId
    private ScaoEnrollmentId id;

    @Column(name="MAT_TUR_TURMA")
    private String classId;

    @Column(name="MAT_MEDIA_FINAL")
   private String grade;

    @Column(name="MAT_SITUACAO")
    private String status;

    @Column(name="MAT_TIPO_MATRICULA")
    private String enrollmentType;

    @Column(name="COD_CURSO")
    private String courseCode;

    public ScaoEnrollment(){
    }

    public ScaoEnrollmentId getId() {
        return id;
    }

    public void setId(ScaoEnrollmentId id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Double getGrade() {
        if (grade == null){
            return 0.0;
        }
        return Double.parseDouble(grade);
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        if (this.enrollmentType.equals(ScaoValueConstants.DISPENSA)) {
            return Constants.STR_EXEMPTED;
        }
        switch (this.status) {
            case ScaoValueConstants.EM_CURSO:
                return Constants.STR_ONGOING;
            case ScaoValueConstants.TRANCADO:
                return Constants.STR_SUSPENDED;
            case ScaoValueConstants.APROVADO:
                return Constants.STR_SUCCEEDED;
            case ScaoValueConstants.REPROVADO:
                return Constants.STR_FAILED_DUE_GRADE;
            case ScaoValueConstants.REPROVADO_POR_FALTA:
                return Constants.STR_FAILED_DUE_ABSENCE;
            case ScaoValueConstants.CANCELADO:
                return Constants.STR_CANCELLED;
            default: return Constants.STR_OTHER;
        }
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerm(){
        return this.id.getYearEnrollment() + "." + this.id.getTermEnrollment();
    }

    public String getRegistration(){
        return this.id.getRegistration();
    }

    public String getSubjectCode(){
        return this.id.getSubjectCode();
    }

    public void setEnrollmentType(String enrollmentType) {
        this.enrollmentType = enrollmentType;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public RegistrationSubjectCodeTermKey createKey() {
        return new RegistrationSubjectCodeTermKey(this.getRegistration(), this.getSubjectCode(), this.getTerm());
    }

    public EnrollmentData createData(int credits) {
        return new EnrollmentData(this.getClassId(), credits, this.getGrade(), this.getStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaoEnrollment that = (ScaoEnrollment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
