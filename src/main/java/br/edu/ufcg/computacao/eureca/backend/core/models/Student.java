package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;

public class Student implements Comparable {
    private Registration registration;
    private String nationalId;
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

    public Student(String registration, String nationalId, String name, String birthDate, String email, String gender,
                   String maritalStatus, String nationality, String placeOfBirth, String race, String statusStr,
                   StudentStatus status, String statusTerm, String admissionStr, String admissionTerm,
                   String affirmativePolicy, String secondarySchool, String secondarySchoolGraduationYear,
                   String course, String curriculum, int mandatoryHours, int mandatoryCredits, int electiveHours,
                   int electiveCredits, int complementaryHours, int complementaryCredits, int attemptedCredits,
                   double gpa, double mc, double iea, int completedTerms, int suspendedTerms, int institutionalTerms,
                   int mobilityTerms, int enrolledCredits, double admissionGrade) {
        this.registration = new Registration(registration);
        this.nationalId = nationalId;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.nationality = nationality;
        this.placeOfBirth = placeOfBirth;
        this.race = race;
        this.statusStr = statusStr;
        this.status = status;
        this.statusTerm = statusTerm;
        this.admissionStr = admissionStr;
        this.admissionTerm = admissionTerm;
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
        this.attemptedCredits = attemptedCredits;
        this.gpa = gpa;
        this.mc = mc;
        this.iea = iea;
        this.completedTerms = completedTerms;
        this.suspendedTerms = suspendedTerms;
        this.institutionalTerms = institutionalTerms;
        this.mobilityTerms = mobilityTerms;
        this.enrolledCredits = enrolledCredits;
        this.admissionGrade = admissionGrade;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public String getStatusTerm() {
        return statusTerm;
    }

    public void setStatusTerm(String statusTerm) {
        this.statusTerm = statusTerm;
    }

    public String getAdmissionStr() {
        return admissionStr;
    }

    public void setAdmissionStr(String admissionStr) {
        this.admissionStr = admissionStr;
    }

    public String getAdmissionTerm() {
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

    public boolean isActive() {
        return this.status.equals(StudentStatus.ACTIVE);
    }

    public boolean isAlumnus() {
        return this.status.equals(StudentStatus.ALUMNUS);
    }

    public boolean isDropout() {
        return this.status.equals(StudentStatus.DROPOUT);
    }

    public int getCompletedCredits() {
        int complementary = (this.getComplementaryCredits() > 8 ? 8 : this.getComplementaryCredits());
        return this.getMandatoryCredits() + this.getElectiveCredits() + complementary;
    }

    public RiskClass computeRiskClass() {
        StudentMetrics studentMetrics = MetricsCalculator.computeMetrics(this);
        return MetricsCalculator.computeRiskClass(studentMetrics.getRisk());
    }

    public int getStatusIndex() {
        if (this.statusStr.contains(SystemConstants.FAILED_3_TIMES)) {
            return SystemConstants.FAILED_3_TIMES_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.REENTER_SAME_COURSE)) {
            return SystemConstants.REENTER_SAME_COURSE_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.REENTER_OTHER_COURSE)) {
            return SystemConstants.REENTER_OTHER_COURSE_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.FAILED_ALL)) {
            return SystemConstants.FAILED_ALL_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.CANCELLED)) {
            return SystemConstants.CANCELLED_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.CANCELLED_BY_DECREE)) {
            return SystemConstants.CANCELLED_BY_DECREE_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.CANCELLED_COURSE_CHANGE)) {
            return SystemConstants.CANCELLED_COURSE_CHANGE_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.CANCELLED_UPON_REQUEST)) {
            return SystemConstants.CANCELLED_UPON_REQUEST_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.LEFT_WITHOUT_NOTICE)) {
            return SystemConstants.LEFT_WITHOUT_NOTICE_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.MISSED_GRADUATION)) {
            return SystemConstants.MISSED_GRADUATION_INDEX;
        }
        else if (this.statusStr.contains(SystemConstants.TRANSFERRED)) {
            return SystemConstants.TRANSFERRED_INDEX;
        }
        else {
            return SystemConstants.UNKNOWN;
        }
    }

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        return this.getRegistration().compareTo(other.getRegistration());
    }

    @Override
    public String toString() {
        return "Student{" +
                "registration='" + registration + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", nationality='" + nationality + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", race='" + race + '\'' +
                ", statusStr='" + statusStr + '\'' +
                ", status=" + status +
                ", statusTerm='" + statusTerm + '\'' +
                ", admissionStr='" + admissionStr + '\'' +
                ", admissionTerm='" + admissionTerm + '\'' +
                ", affirmativePolicy='" + affirmativePolicy + '\'' +
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
                ", attemptedCredits=" + attemptedCredits +
                ", gpa=" + gpa +
                ", mc=" + mc +
                ", iea=" + iea +
                ", completedTerms=" + completedTerms +
                ", suspendedTerms=" + suspendedTerms +
                ", institutionalTerms=" + institutionalTerms +
                ", mobilityTerms=" + mobilityTerms +
                ", enrolledCredits=" + enrolledCredits +
                ", admissionGrade=" + admissionGrade +
                '}';
    }
}
