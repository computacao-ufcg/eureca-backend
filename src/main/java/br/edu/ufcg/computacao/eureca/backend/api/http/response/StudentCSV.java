package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;

public class StudentCSV implements Comparable {
    private String registration;
    private String name;
    private String gender;
    private String maritalStatus;
    private String curriculum;
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
        this.name = student.getName();
        this.gender = student.getGender();
        this.maritalStatus = student.getMaritalStatus();
        this.curriculum = student.getCurriculum();
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
        this.electiveCredits = student.getElectiveCredits();
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
            this.riskClass = StudentMetricsCalculator.computeRiskClass(metrics.getRisk());
        } else {
            this.riskClass = RiskClass.NOT_APPLICABLE;
        }
        this.costClass = StudentMetricsCalculator.computeCostClass(this.cost);
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
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
