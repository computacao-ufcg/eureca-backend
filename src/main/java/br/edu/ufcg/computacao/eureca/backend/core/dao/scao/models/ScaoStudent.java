package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models;

import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.ScaoValueConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.caches.ScaoPlacesOfBirthCache;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.Constants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.NationalIdRegistrationKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "EURECA_ALUNO", schema = "SCA")
public class ScaoStudent {
    @Id
    @Column(name="ALU_MATRICULA")
    private String registration;

    @Column(name="ALU_CPF")
    private String citizenId;

    @Column(name="ALU_NOME")
    private String name;

    @Column(name="ALU_DT_NASC")
    private String birthDate;

    @Column(name="ALU_EMAIL")
    private String email;

    @Column(name="ALU_SEXO")
    private String gender;

    @Column(name="ALU_ESTADO_CIVIL" )
    private String maritalStatus;

    @Column(name="ALU_NAC_CODIGO")
    private String nationality;

    @Column(name="ALU_NAT_MUN_CODIGO")
    private String placeOfBirth;

    @Column(name="ALU_SITUACAO")
    private String status;

    @Column(name="ALU_FORMA_INGRESSO")
    private String admissionType;

    @Column(name = "ALU_ANO_INGRESSO")
    private String admissionYear;

    @Column(name = "ALU_PERIODO_INGRESSO")
    private String admissionPeriod;

    @Column(name="ALU_TIPO_RESERVA_VAGAS")
    private String affirmativePolicy;

    @Column(name="ALU_TIPO_ENSINO_MEDIO")
    private String secondarySchoolType;

    @Column(name = "ALU_ANO_CONC_ENSINO_MEDIO")
    private String secondarySchoolGraduationYear;

    @Column(name="ALU_CCU_CUR_COD_CURSO")
    private String courseCode;

    @Column(name="ALU_CCU_COD_CURRICULO")
    private String curriculumCode;

    @Column(name="ALU_FORMA_EVASAO")
    private String inactivityReason;

    @Column(name="ALU_ANO_EVASA")
    private String inactivityYear;

    @Column(name="ALU_PERIODO_EVASAO")
    private String inactivityPeriod;

    public ScaoStudent() {
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        if (this.gender == null) return Constants.STR_NOT_INFORMED;
        switch(this.gender) {
            case ScaoValueConstants.MASCULINO:
                return Constants.STR_MASCULINO;
            case ScaoValueConstants.FEMININO:
                return Constants.STR_FEMININO;
            default:
                return Constants.STR_OTHER;
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        if (this.maritalStatus == null) return Constants.STR_NOT_INFORMED;
        switch(this.maritalStatus) {
            case ScaoValueConstants.SOLTEIRO:
                return Constants.STR_SOLTEIRO;
            case ScaoValueConstants.CASADO:
                return Constants.STR_CASADO;
            case ScaoValueConstants.VIUVO:
                return Constants.STR_VIUVO;
            case ScaoValueConstants.SEPARADO:
                return Constants.STR_SEPARADO;
            case ScaoValueConstants.DIVORCIADO:
                return Constants.STR_DIVORCIADO;
            default:
                return Constants.STR_OTHER;
        }
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getNationality() {
        if (this.nationality == null) return Constants.STR_NOT_INFORMED;
        switch(this.nationality) {
            case ScaoValueConstants.BRASILEIRA:
                return Constants.STR_BRASILEIRA;
            case ScaoValueConstants.ESTRANGEIRA:
                return Constants.STR_ESTRANGEIRA;
            case ScaoValueConstants.NATURALIZADA:
                return Constants.STR_NATURALIZADA;
            default:
                return Constants.STR_OTHER;
        }
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPlaceOfBirth() {
        try {
            return ScaoPlacesOfBirthCache.getInstance().getPlaceOfBirth(placeOfBirth);
        } catch(Exception e) {
            return Constants.STR_NOT_INFORMED;
        }
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }


    public String getStatus() {
        if (this.status != null && this.status.equals(ScaoValueConstants.ATIVO)) {
            return Constants.STR_ACTIVE;
        }
        if (isAlumnus()) {
            return Constants.STR_GRADUADO;
        }
        return Constants.STR_INACTIVE;
    }

    private boolean isAlumnus() {
        if (this.inactivityReason != null && (this.inactivityReason.equals(ScaoValueConstants.CONCLUSAO) ||
                this.inactivityReason.equals(ScaoValueConstants.GRADUADO_JUDICIAL))) {
            return true;
        }
        return false;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }

    public String getAdmissionPeriod() {
        return admissionPeriod;
    }

    public void setAdmissionPeriod(String admissionPeriod) {
        this.admissionPeriod = admissionPeriod;
    }

    public String getAffirmativePolicy() {
        if (this.affirmativePolicy == null) return "A0";
        switch(this.affirmativePolicy) {
            case ScaoValueConstants.L1:
                return "L1";
            case ScaoValueConstants.L2:
                return "L2";
            case ScaoValueConstants.L5:
                return "L5";
            case ScaoValueConstants.L6:
                return "L6";
            case ScaoValueConstants.L9:
                return "L9";
            case ScaoValueConstants.L10:
                return "L10";
            case ScaoValueConstants.L13:
                return "L13";
            case ScaoValueConstants.L14:
                return "L14";
            default:
                return "A0";
        }
    }

    public void setAffirmativePolicy(String affirmativePolicy) {
        this.affirmativePolicy = affirmativePolicy;
    }

    public String getSecondarySchoolType() {
        if (this.secondarySchoolType == null) return Constants.STR_NOT_INFORMED;
        switch(this.secondarySchoolType) {
            case ScaoValueConstants.PRIVADA:
                return Constants.STR_PRIVADA;
            case ScaoValueConstants.PUBLICA:
                return Constants.STR_PUBLICA;
            case ScaoValueConstants.MAJORITARIAMENTE_PUBLICA:
                return Constants.STR_MAJORITARIAMENTE_PUBLICA;
            case ScaoValueConstants.MAJORITARIAMENTE_PRIVADA:
                return Constants.STR_MAJORITARIAMENTE_PRIVADA;
            default:
                return Constants.STR_OTHER;
        }
    }

    public void setSecondarySchoolType(String secondarySchoolType) {
        this.secondarySchoolType = secondarySchoolType;
    }

    public String getSecondarySchoolGraduationYear() {
        return secondarySchoolGraduationYear;
    }

    public void setSecondarySchoolGraduationYear(String secondarySchoolGraduationYear) {
        this.secondarySchoolGraduationYear = secondarySchoolGraduationYear;
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

    public String getInactivityYear() {
        return inactivityYear;
    }

    public void setInactivityYear(String inactivityYear) {
        this.inactivityYear = inactivityYear;
    }

    public String getInactivityPeriod() {
        return inactivityPeriod;
    }

    public void setInactivityPeriod(String inactivityPeriod) {
        this.inactivityPeriod = inactivityPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaoStudent that = (ScaoStudent) o;
        return Objects.equals(registration, that.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration);
    }

    public StudentData createData() {
        return new StudentData(this.name, buildStatusStr(), buildAdmissionStr(), this.getAffirmativePolicy(),
                getBirthDate(), this.getSecondarySchoolType(), this.getSecondarySchoolGraduationYear(),
                this.email, this.getGender(), this.getMaritalStatus(), this.getNationality(), "",
                this.getPlaceOfBirth(), "", "", this.courseCode, this.curriculumCode, 0, 0, 0, 0, 0, 0,
                0, 0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0,0.0);
    }

    private String getBirthDate() {
        if (this.birthDate == null) {
            return "";
        } else {
            return this.birthDate.substring(8,10) + "/" + this.birthDate.substring(5,7) + "/" + this.birthDate.substring(0,4);
        }
    }

    private String buildAdmissionStr() {
        String admissionStr;
        if (this.admissionType == null) admissionStr = Constants.STR_NOT_INFORMED;
        switch(this.admissionType) {
            case Constants.CODE_VESTIBULAR:
            case Constants.CODE_VESTIBULAR_ENEM:
                admissionStr = Constants.STR_VESTIBULAR;
                break;
            case Constants.CODE_SISU:
                admissionStr = Constants.STR_SISU;
                break;
            case Constants.CODE_CONVENIO:
            case Constants.CODE_CONVENIO_INTERNACIONAL:
            case Constants.CODE_PEC_G:
            case Constants.CODE_PEC_RP:
            case Constants.CODE_PARFOR:
                admissionStr = Constants.STR_CONVENIO;
                break;
            case Constants.CODE_ALUNO_ESPECIAL:
            case Constants.CODE_DECISAO_CAMARA:
            case Constants.CODE_JUDICIAL:
                admissionStr = Constants.STR_JUDICIAL;
                break;
            case Constants.CODE_NOVO_CURRICULO:
            case Constants.CODE_ADM_REINGRESSO:
                admissionStr = Constants.STR_REINGRESSO;
                break;
            case Constants.CODE_ADM_REOPCAO:
                admissionStr = Constants.STR_REOPCAO;
                break;
            case Constants.CODE_TRANSFERENCIA:
            case Constants.CODE_TRANSFERENCIA_EX_OFFICIO:
            case Constants.CODE_TRANSFERENCIA_JUDICIAL:
                admissionStr = Constants.STR_TRANSFERENCIA;
                break;
            case Constants.CODE_ADM_GRADUADO:
                admissionStr = Constants.STR_GRADUADO;
                break;
            case Constants.CODE_INTERCAMBIO:
                admissionStr = Constants.STR_INTERCAMBIO;
                break;
            default:
                admissionStr = Constants.STR_NOT_INFORMED;
                break;
        }
        return admissionStr + " " + this.admissionYear + "." + this.admissionPeriod;
    }

    private String buildStatusStr() {
        String statusStr;
        if (this.inactivityReason == null) {
            statusStr = Constants.STR_NOT_INFORMED;
        } else if (this.inactivityReason.equals(Constants.CODE_ACTIVE)) {
            return Constants.STR_ACTIVE;
        } else {
            switch(this.inactivityReason) {
                case Constants.CODE_CONCLUSAO:
                    statusStr = Constants.STR_GRADUADO;
                    break;
                case Constants.CODE_MUDANCA_CURSO:
                    statusStr = SystemConstants.CANCELLED_COURSE_CHANGE;
                    break;
                case Constants.CODE_INGRESSO_OUTRO_CURSO:
                    statusStr = SystemConstants.REENTER_OTHER_COURSE;
                    break;
                case Constants.CODE_REMANEJADO:
                    statusStr = SystemConstants.TRANSFERRED;
                    break;
                case Constants.CODE_ABANDONO:
                    statusStr = SystemConstants.LEFT_WITHOUT_NOTICE;
                    break;
                case Constants.CODE_CANCELAMENTO_A_PEDIDO:
                    statusStr = SystemConstants.CANCELLED_UPON_REQUEST;
                    break;
                case Constants.CODE_CANCELAMENTO_PEC:
                case Constants.CODE_CANCELAMENTO:
                    statusStr = SystemConstants.CANCELLED;
                    break;
                case Constants.CODE_CANCELAMENTO_JUDICIAL:
                    statusStr = SystemConstants.CANCELLED_BY_DECREE;
                    break;
                case Constants.CODE_JUBILADO_FALTA:
                    statusStr = SystemConstants.FAILED_ALL;
                    break;
                case Constants.CODE_JUBILADO_DESEMPENHO:
                    statusStr = SystemConstants.FAILED_3_TIMES;
                    break;
                case Constants.CODE_FALECIMENTO:
                case Constants.CODE_TERMINO_CONVENIO:
                case Constants.CODE_TERMINO_INTERCAMBIO:
                    statusStr = SystemConstants.OTHERS;
                    break;
                case Constants.CODE_SUSPENSAO:
                case Constants.CODE_SUSPENSO_DEBITO_BIBLIOTECA:
                    statusStr = SystemConstants.SUSPENSION;
                    break;
                case Constants.CODE_CANCELAMENTO_NOVO_CURICULO:
                case Constants.CODE_INGRESSO_MESMO_CURSO:
                    statusStr = SystemConstants.REENTER_SAME_COURSE;
                    break;
                case Constants.CODE_NAO_COLOU_GRAU:
                    statusStr = SystemConstants.MISSED_GRADUATION;
                    break;
                case Constants.CODE_FALTA_CADASTRO:
                case Constants.CODE_FALTA_REMANEJAMENTO:
                case Constants.CODE_FALTA_PRIMEIRA_MATRICULA:
                case Constants.CODE_DESVINCULO_SEM_MATRICULA:
                case Constants.CODE_AGUARDANDO_CADASTRAMENTO:
                case Constants.CODE_DOCUMENTACAO_PENDENTE:
                    statusStr = SystemConstants.GIVEN_UP;
                    break;
                default:
                    statusStr = Constants.STR_NOT_INFORMED;
            }
        }
        return Constants.STR_INACTIVE + " (" + statusStr + " " + this.inactivityYear + "." + this.inactivityPeriod + ")";
    }

    public NationalIdRegistrationKey createKey() {
        return new NationalIdRegistrationKey(this.citizenId, this.registration);
    }
}
