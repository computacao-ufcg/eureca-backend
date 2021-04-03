package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;

public class StudentDataResponse implements Comparable {
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
    private int attemptedCredits;
    private int institutionalEnrollments;
    private int mobilityTerms;
    private int suspendedTerms;
    private double feasibility;
    private double successRate;
    private double averageLoad;
    private double cost;
    private double pace;
    private int courseDurationPrediction;
    private double risk;
    private RiskClass riskClass;

    public StudentDataResponse(String registration, StudentData studentData, Metrics metrics, RiskClass riskClass) {
        this.registration = registration;
        this.name = studentData.getName();
        this.gender = studentData.getGender();
        this.maritalStatus = studentData.getMaritalStatus();
        this.curriculum = studentData.getCurriculum();
        this.affirmativePolicy = studentData.getAffirmativePolicy();
        this.admissionType = studentData.getAdmissionStr();
        this.admissionTerm = studentData.getAdmissionTerm();
        this.statusStr = studentData.getStatusStr();
        this.statusTerm = studentData.getStatusTerm();
        this.entryGrade = studentData.getAdmissionGrade();
        this.gpa = studentData.getGpa();
        this.iea = studentData.getIea();
        this.mc = studentData.getMc();
        this.mandatoryCredits = studentData.getMandatoryCredits();
        this.complementaryCredits = studentData.getComplementaryCredits();
        this.electiveCredits = studentData.getElectiveCredits();
        this.completedTerms = studentData.getCompletedTerms();
        this.attemptedCredits = metrics.getAttemptedCredits();
        this.institutionalEnrollments = studentData.getInstitutionalTerms();
        this.mobilityTerms = studentData.getMobilityTerms();
        this.suspendedTerms = studentData.getSuspendedTerms();
        this.feasibility = metrics.getFeasibility();
        this.successRate = metrics.getSuccessRate();
        this.averageLoad = metrics.getAverageLoad();
        this.cost = metrics.getCost();
        this.pace = metrics.getPace();
        this.courseDurationPrediction = metrics.getCourseDurationPrediction();
        this.risk = metrics.getRisk();
        this.riskClass = riskClass;
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

    public int getCompletedCredits() {
        int complementary = (this.getComplementaryCredits() > 8 ? 8 : this.getComplementaryCredits());
        return this.getMandatoryCredits() + this.getElectiveCredits() + complementary;
    }

    public int getCompletedTerms() {
        return completedTerms;
    }

    public void setCompletedTerms(int completedTerms) {
        this.completedTerms = completedTerms;
    }

    public int getAttemptedCredits() {
        return attemptedCredits;
    }

    public void setAttemptedCredits(int attemptedCredits) {
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

    public int getCourseDurationPrediction() {
        return courseDurationPrediction;
    }

    public void setCourseDurationPrediction(int courseDurationPrediction) {
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

    @Override
    public int compareTo(Object o) {
        StudentDataResponse other = (StudentDataResponse) o;
        return (new Registration(this.getRegistration())).compareTo((new Registration(other.getRegistration())));
    }
}
