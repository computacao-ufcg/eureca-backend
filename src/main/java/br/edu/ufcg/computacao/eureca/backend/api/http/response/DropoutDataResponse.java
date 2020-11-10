package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutDataResponse {
    String affirmative_action;
    double gpa;
    int complementary_credits;
    int mandatory_credits;
    int elective_credits;
    String curriculum;
    String marital_status;
    String gender;
    double iea;
    String registration;
    int institutional_terms;
    double mc;
    double entry_grade;
    int mobility_terms;
    String dropout_reason;
    String admission_term;
    int terms_done;
    int suspended_terms;

    public DropoutDataResponse(String affirmative_action, double gpa, int complementary_credits, int mandatory_credits, int elective_credits,
                               String curriculum, String marital_status, String gender, double iea, String registration,
                               int institutional_terms, double mc, double entry_grade,
                               int mobility_terms, String dropout_reason, String admission_term,
                               int terms_done, int suspended_terms) {
        this.affirmative_action = affirmative_action;
        this.gpa = gpa;
        this.complementary_credits = complementary_credits;
        this.mandatory_credits = mandatory_credits;
        this.elective_credits = elective_credits;
        this.curriculum = curriculum;
        this.marital_status = marital_status;
        this.gender = gender;
        this.iea = iea;
        this.registration = registration;
        this.institutional_terms = institutional_terms;
        this.mc = mc;
        this.entry_grade = entry_grade;
        this.mobility_terms = mobility_terms;
        this.dropout_reason = dropout_reason;
        this.admission_term = admission_term;
        this.terms_done = terms_done;
        this.suspended_terms = suspended_terms;
    }

    public String getAffirmative_action() {
        return affirmative_action;
    }

    public void setAffirmative_action(String affirmative_action) {
        this.affirmative_action = affirmative_action;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getComplementary_credits() {
        return complementary_credits;
    }

    public void setComplementary_credits(int complementary_credits) {
        this.complementary_credits = complementary_credits;
    }

    public int getMandatory_credits() {
        return mandatory_credits;
    }

    public void setMandatory_credits(int mandatory_credits) {
        this.mandatory_credits = mandatory_credits;
    }

    public int getElective_credits() {
        return elective_credits;
    }

    public void setElective_credits(int elective_credits) {
        this.elective_credits = elective_credits;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
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

    public int getInstitutional_terms() {
        return institutional_terms;
    }

    public void setInstitutional_terms(int institutional_terms) {
        this.institutional_terms = institutional_terms;
    }

    public double getMc() {
        return mc;
    }

    public void setMc(double mc) {
        this.mc = mc;
    }

    public double getEntry_grade() {
        return entry_grade;
    }

    public void setEntry_grade(double entry_grade) {
        this.entry_grade = entry_grade;
    }

    public int getMobility_terms() {
        return mobility_terms;
    }

    public void setMobility_terms(int mobility_terms) {
        this.mobility_terms = mobility_terms;
    }

    public String getDropout_reason() {
        return dropout_reason;
    }

    public void setDropout_reason(String dropout_reason) {
        this.dropout_reason = dropout_reason;
    }

    public String getAdmission_term() {
        return admission_term;
    }

    public void setAdmission_term(String admission_term) {
        this.admission_term = admission_term;
    }

    public int getTerms_done() {
        return terms_done;
    }

    public void setTerms_done(int terms_done) {
        this.terms_done = terms_done;
    }

    public int getSuspended_terms() {
        return suspended_terms;
    }

    public void setSuspended_terms(int suspended_terms) {
        this.suspended_terms = suspended_terms;
    }
}
