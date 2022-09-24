package br.edu.ufcg.computacao.eureca.backend.api.http.response.students;

import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;

import java.time.LocalDate;
import java.time.Period;

public class StudentCSV implements Comparable {
    private String registration;
    private String courseCode;
    private String curriculumCode;
    private String name;
    private String gender;
    private int age;
    private String maritalStatus;
    private String affirmativePolicy;
    private String admissionType;
    private String admissionTerm;
    private String statusStr;
    private String statusTerm;
    private double entryGrade;
    private double gpa;
    private double iea;
    private double mc;
    private int mandatoryCredits;
    private int complementaryCredits;
    private int electiveCredits;
    private int completedTerms;
    private double attemptedCredits;
    private int institutionalEnrollments;
    private int mobilityTerms;
    private int suspendedTerms;
    private double feasibility;
    private double successRate;
    private double averageLoad;
    private double cost;
    private double pace;
    private double courseDurationPrediction;
    private double risk;
    private RiskClass riskClass;
    private CostClass costClass;

    public StudentCSV(Student student) {
        this.registration = student.getRegistration().toString();
        this.courseCode = student.getCourseCode();
        this.curriculumCode = student.getCurriculumCode();
        this.name = student.getName();
        this.gender = student.getGender();
        this.age = calculateAge(student.getBirthDate());
        this.maritalStatus = student.getMaritalStatus();
        this.affirmativePolicy = student.getAffirmativePolicy();
        this.admissionType = student.getAdmissionStr();
        this.admissionTerm = student.getAdmissionTerm();
        this.statusStr = student.getStatusStr();
        this.statusTerm = student.getStatusTerm();
        this.entryGrade = student.getAdmissionGrade();
        this.gpa = student.getGpa();
        this.iea = student.getIea();
        this.mc = student.getMc();
        this.mandatoryCredits = student.getMandatoryCredits();
        this.complementaryCredits = student.getComplementaryCredits();
        this.electiveCredits = student.getOptionalCredits();
        this.completedTerms = student.getCompletedTerms();
        this.attemptedCredits = student.getAttemptedCredits();
        this.institutionalEnrollments = student.getInstitutionalEnrollments();
        this.mobilityTerms = student.getMobilityTerms();
        this.suspendedTerms = student.getSuspendedTerms();
        StudentMetrics metrics = StudentMetricsCalculator.computeMetrics(student);
        this.feasibility = metrics.getFeasibility();
        this.successRate = metrics.getSuccessRate();
        this.averageLoad = metrics.getAverageLoad();
        this.cost = metrics.getCost();
        this.pace = metrics.getPace();
        this.courseDurationPrediction = metrics.getCourseDurationPrediction();
        this.risk = metrics.getRisk();
        if (student.isActive()) {
            double desiredAverageDuration = (student.getCurriculum().getMinNumberOfTerms() +
                    (student.getCurriculum().getMaxNumberOfTerms() - student.getCurriculum().getMinNumberOfTerms()) / 4.0);
            double lowestRisk = student.getCurriculum().getMinNumberOfTerms() / desiredAverageDuration;
            this.riskClass = StudentMetricsCalculator.computeRiskClass(metrics.getRisk(), lowestRisk);
        } else {
            this.riskClass = RiskClass.NOT_APPLICABLE;
        }
        double costIncrement = ((student.getCurriculum().getMinNumberOfTerms() + (student.getCurriculum().getMaxNumberOfTerms() -
                student.getCurriculum().getMinNumberOfTerms()) / 4.0) / student.getCurriculum().getMinNumberOfTerms()) - 1.0;
        this.costClass = StudentMetricsCalculator.computeCostClass(this.cost, costIncrement);
    }

    private int calculateAge(String birthDate) {
        String day = birthDate.substring(0,2);
        String month = birthDate.substring(3,5);
        String year = birthDate.substring(6,10);
        LocalDate dob = LocalDate.parse(year + "-" + month + "-" + day);
        LocalDate curDate = LocalDate.now();
        if ((dob != null) && (curDate != null))
        {
            return Period.between(dob, curDate).getYears();
        } else {
            return 0;
        }
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAffirmativePolicy() {
        return affirmativePolicy;
    }

    public void setAffirmativePolicy(String affirmativePolicy) {
        this.affirmativePolicy = affirmativePolicy;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatusTerm() {
        return statusTerm;
    }

    public void setStatusTerm(String statusTerm) {
        this.statusTerm = statusTerm;
    }

    public double getEntryGrade() {
        return entryGrade;
    }

    public void setEntryGrade(double entryGrade) {
        this.entryGrade = entryGrade;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getIea() {
        return iea;
    }

    public void setIea(double iea) {
        this.iea = iea;
    }

    public double getMc() {
        return mc;
    }

    public void setMc(double mc) {
        this.mc = mc;
    }

    public int getMandatoryCredits() {
        return mandatoryCredits;
    }

    public void setMandatoryCredits(int mandatoryCredits) {
        this.mandatoryCredits = mandatoryCredits;
    }

    public int getComplementaryCredits() {
        return complementaryCredits;
    }

    public void setComplementaryCredits(int complementaryCredits) {
        this.complementaryCredits = complementaryCredits;
    }

    public int getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(int electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public int computeCompletedCredits() {
        int complementary = (this.getComplementaryCredits() > 8 ? 8 : this.getComplementaryCredits());
        return this.getMandatoryCredits() + this.getElectiveCredits() + complementary;
    }

    public int getCompletedTerms() {
        return completedTerms;
    }

    public void setCompletedTerms(int completedTerms) {
        this.completedTerms = completedTerms;
    }

    public double getAttemptedCredits() {
        return attemptedCredits;
    }

    public void setAttemptedCredits(double attemptedCredits) {
        this.attemptedCredits = attemptedCredits;
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

    public int getSuspendedTerms() {
        return suspendedTerms;
    }

    public void setSuspendedTerms(int suspendedTerms) {
        this.suspendedTerms = suspendedTerms;
    }

    public double getFeasibility() {
        return feasibility;
    }

    public void setFeasibility(double feasibility) {
        this.feasibility = feasibility;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }

    public double getAverageLoad() {
        return averageLoad;
    }

    public void setAverageLoad(double averageLoad) {
        this.averageLoad = averageLoad;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPace() {
        return pace;
    }

    public void setPace(double pace) {
        this.pace = pace;
    }

    public double getCourseDurationPrediction() {
        return courseDurationPrediction;
    }

    public void setCourseDurationPrediction(double courseDurationPrediction) {
        this.courseDurationPrediction = courseDurationPrediction;
    }

    public double getRisk() {
        return risk;
    }

    public void setRisk(double risk) {
        this.risk = risk;
    }

    public RiskClass getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(RiskClass riskClass) {
        this.riskClass = riskClass;
    }

    public CostClass getCostClass() {
        return costClass;
    }

    public void setCostClass(CostClass costClass) {
        this.costClass = costClass;
    }

    @Override
    public int compareTo(Object o) {
        StudentCSV other = (StudentCSV) o;
        return (new Registration(this.getRegistration())).compareTo((new Registration(other.getRegistration())));
    }
}
