package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;

public class StudentDataResponse {
    String registration;
    String gender;
    String maritalStatus;
    String curriculum;
    String affirmativePolicy;
    String admissionType;
    String admissionTerm;
    double entryGrade;
    double gpa;
    double iea;
    double mc;
    int mandatoryCredits;
    int complementaryCredits;
    int electiveCredits;
    int completedTerms;
    int institutionalEnrollments;
    int mobilityTerms;
    int suspendedTerms;

    public StudentDataResponse(String registration, String gender, String maritalStatus, String curriculum,
                               String affirmativePolicy, String admissionType, String admissionTerm,
                               double entryGrade, double gpa, double iea, double mc, int mandatoryCredits,
                               int complementaryCredits, int electiveCredits, int completedTerms,
                               int institutionalEnrollments, int mobilityTerms, int suspendedTerms) {
        this.registration = registration;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.curriculum = curriculum;
        this.affirmativePolicy = affirmativePolicy;
        this.admissionType = admissionType;
        this.admissionTerm = admissionTerm;
        this.entryGrade = entryGrade;
        this.gpa = gpa;
        this.iea = iea;
        this.mc = mc;
        this.mandatoryCredits = mandatoryCredits;
        this.complementaryCredits = complementaryCredits;
        this.electiveCredits = electiveCredits;
        this.completedTerms = completedTerms;
        this.institutionalEnrollments = institutionalEnrollments;
        this.mobilityTerms = mobilityTerms;
        this.suspendedTerms = suspendedTerms;
    }

    public StudentDataResponse(String registration, StudentData studentData) {
        this.registration = registration;
        this.gender = studentData.getGender();
        this.maritalStatus = studentData.getMaritalStatus();
        this.curriculum = studentData.getCurriculum();
        this.affirmativePolicy = studentData.getAffirmativePolicy();
        this.admissionType = studentData.getAdmissionStr();
        this.admissionTerm = studentData.getAdmissionTerm();
        this.entryGrade = studentData.getAdmissionGrade();
        this.gpa = studentData.getGpa();
        this.iea = studentData.getIea();
        this.mc = studentData.getMc();
        this.mandatoryCredits = studentData.getMandatoryCredits();
        this.complementaryCredits = studentData.getComplementaryCredits();
        this.electiveCredits = studentData.getElectiveCredits();
        this.completedTerms = studentData.getTermsCount();
        this.institutionalEnrollments = studentData.getInstitutionalTerms();
        this.mobilityTerms = studentData.getMobilityTerms();
        this.suspendedTerms = studentData.getSuspendedTerms();
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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

    public int getCompletedTerms() {
        return completedTerms;
    }

    public void setCompletedTerms(int completedTerms) {
        this.completedTerms = completedTerms;
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
}
