package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentMetrics;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;

public class Student implements Comparable {
    private Registration registration;
    private String nationalId;
    private String name;
    private String birthDate;
    private String email;
    private String gender;
    private String maritalStatus;
    private String nationality;
    private String country;
    private String placeOfBirth;
    private String race;
    private String disabilities;
    private String statusStr;
    private StudentStatus status;
    private String statusTerm;
    private String admissionStr;
    private String admissionTerm;
    private String affirmativePolicy;
    private String secondarySchool;
    private String secondarySchoolGraduationYear;
    private String courseCode;
    private String curriculumCode;
    private Curriculum curriculum;
    private int mandatoryHours;
    private int mandatoryCredits;
    private int optionalHours;
    private int optionalCredits;
    private int complementaryHours;
    private int complementaryCredits;
    private int attemptedCredits;
    private double gpa;
    private double mc;
    private double iea;
    private int completedTerms;
    private int suspendedTerms;
    private int institutionalEnrollments;
    private int mobilityTerms;
    private int enrolledCredits;
    private double admissionGrade;

    public Student(String registration, String nationalId, String name, String birthDate, String email, String gender,
                   String maritalStatus, String nationality, String country, String placeOfBirth, String race, String statusStr,
                   StudentStatus status, String statusTerm, String admissionStr, String admissionTerm, String disabilities,
                   String affirmativePolicy, String secondarySchool, String secondarySchoolGraduationYear,
                   String courseCode, String curriculumCode, Curriculum curriculum, int mandatoryHours, int mandatoryCredits, int optionalHours,
                   int optionalCredits, int complementaryHours, int complementaryCredits, int attemptedCredits,
                   double gpa, double mc, double iea, int completedTerms, int suspendedTerms, int institutionalEnrollments,
                   int mobilityTerms, int enrolledCredits, double admissionGrade) {
        this.registration = new Registration(registration);
        this.nationalId = nationalId;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.nationality = nationality;
        this.country = country;
        this.placeOfBirth = placeOfBirth;
        this.race = race;
        this.statusStr = statusStr;
        this.status = status;
        this.statusTerm = statusTerm;
        this.admissionStr = admissionStr;
        this.admissionTerm = admissionTerm;
        this.disabilities = disabilities;
        this.affirmativePolicy = affirmativePolicy;
        this.secondarySchool = secondarySchool;
        this.secondarySchoolGraduationYear = secondarySchoolGraduationYear;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.curriculum = curriculum;
        this.mandatoryHours = mandatoryHours;
        this.mandatoryCredits = mandatoryCredits;
        this.optionalHours = optionalHours;
        this.optionalCredits = optionalCredits;
        this.complementaryHours = complementaryHours;
        this.complementaryCredits = complementaryCredits;
        this.attemptedCredits = attemptedCredits;
        this.gpa = gpa;
        this.mc = mc;
        this.iea = iea;
        this.completedTerms = completedTerms;
        this.suspendedTerms = suspendedTerms;
        this.institutionalEnrollments = institutionalEnrollments;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(String disabilities) {
        this.disabilities = disabilities;
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

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
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

    public int getOptionalHours() {
        return optionalHours;
    }

    public void setOptionalHours(int optionalHours) {
        this.optionalHours = optionalHours;
    }

    public int getOptionalCredits() {
        return optionalCredits;
    }

    public void setOptionalCredits(int optionalCredits) {
        this.optionalCredits = optionalCredits;
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

    public int getInstitutionalEnrollments() {
        return institutionalEnrollments;
    }

    public void setInstitutionalEnrollments(int institutionalEnrollments) {
        this.institutionalEnrollments = institutionalEnrollments;
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
        return this.getMandatoryCredits() + this.getOptionalCredits() + this.getComplementaryCredits();
    }

    public RiskClass computeRiskClass() {
        StudentMetrics studentMetrics = StudentMetricsCalculator.computeMetrics(this);
        double desiredAverageDuration = (this.getCurriculum().getMinNumberOfTerms() +
                (this.getCurriculum().getMaxNumberOfTerms() - this.getCurriculum().getMinNumberOfTerms()) / 4.0);
        double lowestRisk = this.getCurriculum().getMinNumberOfTerms() / desiredAverageDuration;
        return StudentMetricsCalculator.computeRiskClass(studentMetrics.getRisk(), lowestRisk);
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
                ", country='" + country + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", race='" + race + '\'' +
                ", statusStr='" + statusStr + '\'' +
                ", status=" + status +
                ", statusTerm='" + statusTerm + '\'' +
                ", admissionStr='" + admissionStr + '\'' +
                ", admissionTerm='" + admissionTerm + '\'' +
                ", disabilities='" + disabilities + '\'' +
                ", affirmativePolicy='" + affirmativePolicy + '\'' +
                ", secondarySchool='" + secondarySchool + '\'' +
                ", secondarySchoolGraduationYear='" + secondarySchoolGraduationYear + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", curriculum='" + curriculum + '\'' +
                ", mandatoryHours=" + mandatoryHours +
                ", mandatoryCredits=" + mandatoryCredits +
                ", optionalHours=" + optionalHours +
                ", optionalCredits=" + optionalCredits +
                ", complementaryHours=" + complementaryHours +
                ", complementaryCredits=" + complementaryCredits +
                ", attemptedCredits=" + attemptedCredits +
                ", gpa=" + gpa +
                ", mc=" + mc +
                ", iea=" + iea +
                ", completedTerms=" + completedTerms +
                ", suspendedTerms=" + suspendedTerms +
                ", institutionalTerms=" + institutionalEnrollments +
                ", mobilityTerms=" + mobilityTerms +
                ", enrolledCredits=" + enrolledCredits +
                ", admissionGrade=" + admissionGrade +
                '}';
    }
}
