package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentStatus;
import org.apache.log4j.Logger;

public class StudentData implements EurecaMapValue {
    private Logger LOGGER = Logger.getLogger(StudentData.class);

    private String name;
    private String birthDate;
    private String email;
    private String gender;
    private String maritalStatus;
    private String nationality;
    private String placeOfBirth;
    private String race;
    private String statusStr;
    private StudentStatus status;
    private String statusTerm;
    private String admissionStr;
    private String admissionTerm;
    private String affirmativePolicy;
    private String secondarySchool;
    private String secondarySchoolGraduationYear;
    private String course;
    private String curriculum;
    private int mandatoryHours;
    private int mandatoryCredits;
    private int electiveHours;
    private int electiveCredits;
    private int complementaryHours;
    private int complementaryCredits;
    private int attemptedCredits;
    private double gpa;
    private double mc;
    private double iea;
    private int completedTerms;
    private int suspendedTerms;
    private int institutionalTerms;
    private int mobilityTerms;
    private int enrolledCredits;
    private double admissionGrade;

    public StudentData(String name, String birthDate, String email, String gender, String maritalStatus,
                       String nationality, String placeOfBirth, String race, String statusStr,
                       String admissionStr, String affirmativePolicy, String secondarySchool,
                       String secondarySchoolGraduationYear, String course, String curriculum, int mandatoryHours,
                       int mandatoryCredits, int electiveHours, int electiveCredits, int complementaryHours,
                       int complementaryCredits, double gpa, double mc, double iea, int completedTerms, int suspendedTerms,
                       int institutionalTerms, int mobilityTerms, int enrolledCredits, double admissionGrade) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.nationality = nationality;
        this.placeOfBirth = placeOfBirth;
        this.race = race;
        this.statusStr = statusStr;
        this.admissionStr = admissionStr;
        this.affirmativePolicy = affirmativePolicy;
        this.secondarySchool = secondarySchool;
        this.secondarySchoolGraduationYear = secondarySchoolGraduationYear;
        this.course = course;
        this.curriculum = curriculum;
        this.mandatoryHours = mandatoryHours;
        this.mandatoryCredits = mandatoryCredits;
        this.electiveHours = electiveHours;
        this.electiveCredits = electiveCredits;
        this.complementaryHours = complementaryHours;
        this.complementaryCredits = complementaryCredits;
        this.gpa = gpa;
        this.mc = mc;
        this.iea = iea;
        this.completedTerms = completedTerms;
        this.suspendedTerms = suspendedTerms;
        this.institutionalTerms = institutionalTerms;
        this.mobilityTerms = mobilityTerms;
        this.enrolledCredits = enrolledCredits;
        this.admissionGrade = admissionGrade;
        parseStatusStr(this.statusStr);
        parseAdmissionStr(this.admissionStr);
    }

    public StudentData() {
    }

    public boolean isActive() {
        if (this.status == null) parseStatusStr(this.statusStr);
        return this.status.equals(StudentStatus.ACTIVE);
    }

    public boolean isAlumnus() {
        if (this.status == null) parseStatusStr(this.statusStr);
        return this.status.equals(StudentStatus.ALUMNUS);
    }

    public boolean isDropout() {
        if (this.status == null) parseStatusStr(this.statusStr);
        return this.status.equals(StudentStatus.DROPOUT);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
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
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getStatusStr() {
        if (this.status == null) parseStatusStr(this.statusStr);
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatusTerm() {
        if (this.status == null) parseStatusStr(this.statusStr);
        return statusTerm;
    }

    public void setStatusTerm(String statusTerm) {
        this.statusTerm = statusTerm;
    }

    public StudentStatus getStatus() {
        if (this.status == null) parseStatusStr(this.statusStr);
        return this.status;
    }

    public String getAdmissionStr() {
        if (this.admissionTerm == null) parseAdmissionStr(this.admissionStr);
        return admissionStr;
    }

    public void setAdmissionStr(String admissionStr) {
        this.admissionStr = admissionStr;
    }

    public String getAdmissionTerm() {
        if (this.admissionTerm == null) parseAdmissionStr(this.admissionStr);
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public String getAffirmativePolicy() {
        return affirmativePolicy;
    }

    public void setAffirmativePolicy(String affirmativePolicy) {
        this.affirmativePolicy = affirmativePolicy;
    }

    public String getSecondarySchool() {
        return secondarySchool;
    }

    public void setSecondarySchool(String secondarySchool) {
        this.secondarySchool = secondarySchool;
    }

    public String getSecondarySchoolGraduationYear() {
        return secondarySchoolGraduationYear;
    }

    public void setSecondarySchoolGraduationYear(String secondarySchoolGraduationYear) {
        this.secondarySchoolGraduationYear = secondarySchoolGraduationYear;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public int getMandatoryHours() {
        return mandatoryHours;
    }

    public void setMandatoryHours(int mandatoryHours) {
        this.mandatoryHours = mandatoryHours;
    }

    public int getMandatoryCredits() {
        return mandatoryCredits;
    }

    public void setMandatoryCredits(int mandatoryCredits) {
        this.mandatoryCredits = mandatoryCredits;
    }

    public int getElectiveHours() {
        return electiveHours;
    }

    public void setElectiveHours(int electiveHours) {
        this.electiveHours = electiveHours;
    }

    public int getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(int electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public int getComplementaryHours() {
        return complementaryHours;
    }

    public void setComplementaryHours(int complementaryHours) {
        this.complementaryHours = complementaryHours;
    }

    public int getComplementaryCredits() {
        return complementaryCredits;
    }

    public void setComplementaryCredits(int complementaryCredits) {
        this.complementaryCredits = complementaryCredits;
    }

    public int getAttemptedCredits() {
        return attemptedCredits;
    }

    public void setAttemptedCredits(int attemptedCredits) {
        this.attemptedCredits = attemptedCredits;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getMc() {
        return mc;
    }

    public void setMc(double mc) {
        this.mc = mc;
    }

    public double getIea() {
        return iea;
    }

    public void setIea(double iea) {
        this.iea = iea;
    }

    public int getCompletedTerms() {
        return completedTerms;
    }

    public void setCompletedTerms(int completedTerms) {
        this.completedTerms = completedTerms;
    }

    public int getSuspendedTerms() {
        return suspendedTerms;
    }

    public void setSuspendedTerms(int suspendedTerms) {
        this.suspendedTerms = suspendedTerms;
    }

    public int getInstitutionalTerms() {
        return institutionalTerms;
    }

    public void setInstitutionalTerms(int institutionalTerms) {
        this.institutionalTerms = institutionalTerms;
    }

    public int getMobilityTerms() {
        return mobilityTerms;
    }

    public void setMobilityTerms(int mobilityTerms) {
        this.mobilityTerms = mobilityTerms;
    }

    public int getEnrolledCredits() {
        return enrolledCredits;
    }

    public void setEnrolledCredits(int enrolledCredits) {
        this.enrolledCredits = enrolledCredits;
    }

    public double getAdmissionGrade() {
        return admissionGrade;
    }

    public void setAdmissionGrade(double admissionGrade) {
        this.admissionGrade = admissionGrade;
    }

    public Student createStudent(NationalIdRegistrationKey id) {
        return new Student(id.getRegistration(), id.getNationalId(), getName(), getBirthDate(), getEmail(), getGender(),
                getMaritalStatus(), getNationality(), getPlaceOfBirth(), getRace(), getStatusStr(), getStatus(),
                getStatusTerm(), getAdmissionStr(), getAdmissionTerm(), getAffirmativePolicy(), getSecondarySchool(),
                getSecondarySchoolGraduationYear(), getCourse(), getCurriculum(), getMandatoryHours(), getMandatoryCredits(),
                getElectiveHours(), getElectiveCredits(), getComplementaryHours(), getComplementaryCredits(),
                getAttemptedCredits(), getGpa(), getMc(), getIea(), getCompletedTerms(), getSuspendedTerms(),
                getInstitutionalTerms(), getMobilityTerms(), getEnrolledCredits(), getAdmissionGrade());
    }

    public int getCompletedCredits() {
        int complementary = (this.getComplementaryCredits() > 8 ? 8 : this.getComplementaryCredits());
        return this.getMandatoryCredits() + this.getElectiveCredits() + complementary;
    }

    private void parseAdmissionStr(String admission) {
        int termEnd = admission.length();
        this.admissionTerm = admission.substring(termEnd - 6, termEnd);
        this.admissionStr = admission.substring(0, (termEnd - 7));
    }

    private void parseStatusStr(String statusStr) {
        LOGGER.debug(String.format(Messages.PARSE_STATUS_STR, statusStr));
        String activeStatus = StudentStatus.ACTIVE.getValue();
        if (statusStr.equals(activeStatus)) {
            this.status = StudentStatus.ACTIVE;
            this.statusTerm = "Current";
        } else {
            StringBuilder sb = new StringBuilder(statusStr);
            int firstSpace = sb.indexOf(" ");
            sb.delete(0, (firstSpace + 1));
            int op = sb.indexOf("(");
            sb.delete(op, (op + 1));
            int cp = sb.indexOf(")");
            sb.delete(cp, (cp + 1));
            String filteredStatus = sb.toString();
            int termEnd = filteredStatus.length();
            this.statusTerm = filteredStatus.substring(termEnd - 6, termEnd);
            this.statusStr = filteredStatus.substring(0, (termEnd - 7));
            String alumnusStatus = StudentStatus.ALUMNUS.getValue();
            if (this.statusStr.equals(alumnusStatus)) {
                this.status = StudentStatus.ALUMNUS;
            } else {
                this.status = StudentStatus.DROPOUT;
            }
        }
        LOGGER.debug(this.status.toString() + " " + this.statusTerm);
    }

    @Override
    public String toString() {
        return "StudentCSV{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", nationality='" + nationality + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", race='" + race + '\'' +
                ", status='" + statusStr + '\'' +
                ", termStatus='" + statusTerm + '\'' +
                ", admission='" + admissionStr + '\'' +
                ", admissionTerm='" + admissionTerm + '\'' +
                ", affirmativeAction='" + affirmativePolicy + '\'' +
                ", secondarySchool='" + secondarySchool + '\'' +
                ", secondarySchoolGraduationYear='" + secondarySchoolGraduationYear + '\'' +
                ", course='" + course + '\'' +
                ", curriculum='" + curriculum + '\'' +
                ", mandatoryHours=" + mandatoryHours +
                ", mandatoryCredits=" + mandatoryCredits +
                ", electiveHours=" + electiveHours +
                ", electiveCredits=" + electiveCredits +
                ", complementaryHours=" + complementaryHours +
                ", complementaryCredits=" + complementaryCredits +
                ", gpa=" + gpa +
                ", mc=" + mc +
                ", iea=" + iea +
                ", termsCount=" + completedTerms +
                ", suspendedTerms=" + suspendedTerms +
                ", institutionalTerms=" + institutionalTerms +
                ", mobilityTerms=" + mobilityTerms +
                ", enrolledCredits=" + enrolledCredits +
                ", admissionGrade=" + admissionGrade +
                '}';
    }
}
