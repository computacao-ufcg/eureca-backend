package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.*;

public class ActiveDataResponse {
    String registration;
    double gpa;
    int termsCompleted;
    int complementaryCredits;
    int mandatoryCredits;
    int electiveCredits;
    String curriculum;
    String maritalStatus;
    String gender;
    double iea;
    int institutionalEnrollments;
    double mc;
    double entryGrade;
    int mobilityTerms;
    String admissionTerm;
    int suspendedTerms;
    String affirmativePolicy;

    public ActiveDataResponse(String registration, double gpa, int termsCompleted, int complementaryCredits,
                              int mandatoryCredits, int electiveCredits, String curriculum, String maritalStatus,
                              String gender, double iea, int institutionalEnrollments, double mc,
                              double entryGrade, int mobilityTerms, String admissionTerm,
                              int suspendedTerms, String affirmativePolicy) {
        this.registration = registration;
        this.gpa = gpa;
        this.termsCompleted = termsCompleted;
        this.complementaryCredits = complementaryCredits;
        this.mandatoryCredits = mandatoryCredits;
        this.electiveCredits = electiveCredits;
        this.curriculum = curriculum;
        this.maritalStatus = maritalStatus;
        this.gender = gender;
        this.iea = iea;
        this.institutionalEnrollments = institutionalEnrollments;
        this.mc = mc;
        this.entryGrade = entryGrade;
        this.mobilityTerms = mobilityTerms;
        this.admissionTerm = admissionTerm;
        this.suspendedTerms = suspendedTerms;
        this.affirmativePolicy = affirmativePolicy;
    }

    public ActiveDataResponse(String registration, StudentData studentData) {
        this.registration = registration;
        this.gpa = studentData.getGpa();
        this.termsCompleted = studentData.getTermsCount();
        this.complementaryCredits = studentData.getComplementaryCredits();
        this.mandatoryCredits = studentData.getMandatoryCredits();
        this.electiveCredits = studentData.getElectiveCredits();
        this.curriculum = studentData.getCurriculum();
        this.maritalStatus = studentData.getMaritalStatus();
        this.gender = studentData.getGender();
        this.iea = studentData.getIea();
        this.institutionalEnrollments = studentData.getInstitutionalTerms();
        this.mc = studentData.getMc();
        this.entryGrade = studentData.getAdmissionGrade();
        this.mobilityTerms = studentData.getMobilityTerms();
        this.admissionTerm = studentData.getAdmissionTerm();
        this.suspendedTerms = studentData.getSuspendedTerms();
        this.affirmativePolicy = studentData.getAffirmativePolicy();
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getTermsCompleted() {
        return termsCompleted;
    }

    public void setTermsCompleted(int termsCompleted) {
        this.termsCompleted = termsCompleted;
    }

    public int getComplementaryCredits() {
        return complementaryCredits;
    }

    public void setComplementaryCredits(int complementaryCredits) {
        this.complementaryCredits = complementaryCredits;
    }

    public int getMandatoryCredits() {
        return mandatoryCredits;
    }

    public void setMandatoryCredits(int mandatoryCredits) {
        this.mandatoryCredits = mandatoryCredits;
    }

    public int getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(int electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getIea() {
        return iea;
    }

    public void setIea(double iea) {
        this.iea = iea;
    }

    public int getInstitutionalEnrollments() {
        return institutionalEnrollments;
    }

    public void setInstitutionalEnrollments(int institutionalEnrollments) {
        this.institutionalEnrollments = institutionalEnrollments;
    }

    public double getMc() {
        return mc;
    }

    public void setMc(double mc) {
        this.mc = mc;
    }

    public double getEntryGrade() {
        return entryGrade;
    }

    public void setEntryGrade(double entryGrade) {
        this.entryGrade = entryGrade;
    }

    public int getMobilityTerms() {
        return mobilityTerms;
    }

    public void setMobilityTerms(int mobilityTerms) {
        this.mobilityTerms = mobilityTerms;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public int getSuspendedTerms() {
        return suspendedTerms;
    }

    public void setSuspendedTerms(int suspendedTerms) {
        this.suspendedTerms = suspendedTerms;
    }

    public String getAffirmativePolicy() {
        return affirmativePolicy;
    }

    public void setAffirmativePolicy(String affirmativePolicy) {
        this.affirmativePolicy = affirmativePolicy;
    }
}
