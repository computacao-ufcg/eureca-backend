package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutDataResponse {
    String affirmativePolicy;
    double gpa;
    int complementaryCredits;
    int mandatoryCredits;
    int electiveCredits;
    String curriculum;
    String maritalStatus;
    String gender;
    double iea;
    String registration;
    int institutionalEnrollments;
    double mc;
    double entryGrade;
    int mobilityTerms;
    String dropoutReason;
    String admissionTerm;
    int termsCompleted;
    int suspendedTerms;

    public DropoutDataResponse(String affirmativePolicy, double gpa, int complementaryCredits, int mandatoryCredits, int electiveCredits,
                               String curriculum, String marital_status, String gender, double iea, String registration,
                               int institutionalEnrollments, double mc, double entryGrade,
                               int mobilityTerms, String dropoutReason, String admissionTerm,
                               int termsCompleted, int suspendedTerms) {
        this.affirmativePolicy = affirmativePolicy;
        this.gpa = gpa;
        this.complementaryCredits = complementaryCredits;
        this.mandatoryCredits = mandatoryCredits;
        this.electiveCredits = electiveCredits;
        this.curriculum = curriculum;
        this.maritalStatus = marital_status;
        this.gender = gender;
        this.iea = iea;
        this.registration = registration;
        this.institutionalEnrollments = institutionalEnrollments;
        this.mc = mc;
        this.entryGrade = entryGrade;
        this.mobilityTerms = mobilityTerms;
        this.dropoutReason = dropoutReason;
        this.admissionTerm = admissionTerm;
        this.termsCompleted = termsCompleted;
        this.suspendedTerms = suspendedTerms;
    }

    public String getAffirmativePolicy() {
        return affirmativePolicy;
    }

    public void setAffirmativePolicy(String affirmativePolicy) {
        this.affirmativePolicy = affirmativePolicy;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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

    public String getDropoutReason() {
        return dropoutReason;
    }

    public void setDropoutReason(String dropoutReason) {
        this.dropoutReason = dropoutReason;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public int getTermsCompleted() {
        return termsCompleted;
    }

    public void setTermsCompleted(int termsCompleted) {
        this.termsCompleted = termsCompleted;
    }

    public int getSuspendedTerms() {
        return suspendedTerms;
    }

    public void setSuspendedTerms(int suspendedTerms) {
        this.suspendedTerms = suspendedTerms;
    }
}
