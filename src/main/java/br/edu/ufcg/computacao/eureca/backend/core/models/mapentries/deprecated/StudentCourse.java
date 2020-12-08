package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;

public class StudentCourse extends EurecaMapValue {
    int admission_id;
    String admission_term;
    int course_id;
    int status_id;
    String term_status;
    int detailed_status_id;
    int affirmative_action_id;
    int secondary_school_id;
    String year_graduation_secondary_school;
    String curriculum;
    int mandatory_hours;
    int mandatory_credits;
    int elective_hours;
    int elective_credits;
    int complementary_hours;
    int complementary_credits;
    double gpa;
    double mc;
    double iea;
    int terms_count;
    int suspended_terms;
    int institutional_terms;
    int mobility_terms;
    int enrolled_credits;
    double admission_grade;

    public StudentCourse(int admission_id, String admission_term, int course_id, int status_id, String term_status,
                         int detailed_status_id, int affirmative_action_id, int secondary_school_id, String year_graduation_secondary_school, String curriculum,
                         int mandatory_hours, int mandatory_credits, int elective_hours, int elective_credits,
                         int complementary_hours, int complementary_credits, double gpa, double mc, double iea,
                         int terms_count, int suspended_terms, int institutional_terms, int mobility_terms, int enrolled_credits,
                         double admission_grade) {
        this.admission_id = admission_id;
        this.admission_term = admission_term;
        this.course_id = course_id;
        this.status_id = status_id;
        this.term_status = term_status;
        this.detailed_status_id = detailed_status_id;
        this.affirmative_action_id = affirmative_action_id;
        this.secondary_school_id = secondary_school_id;
        this.year_graduation_secondary_school = year_graduation_secondary_school;
        this.curriculum = curriculum;
        this.mandatory_hours = mandatory_hours;
        this.mandatory_credits = mandatory_credits;
        this.elective_hours = elective_hours;
        this.elective_credits = elective_credits;
        this.complementary_hours = complementary_hours;
        this.complementary_credits = complementary_credits;
        this.gpa = gpa;
        this.mc = mc;
        this.iea = iea;
        this.terms_count = terms_count;
        this.suspended_terms = suspended_terms;
        this.institutional_terms = institutional_terms;
        this.mobility_terms = mobility_terms;
        this.enrolled_credits = enrolled_credits;
        this.admission_grade = admission_grade;
    }

    public StudentCourse() {
    }

    public int getAdmission_id() {
        return admission_id;
    }

    public void setAdmission_id(int admission_id) {
        this.admission_id = admission_id;
    }

    public String getAdmission_term() {
        return admission_term;
    }

    public void setAdmission_term(String admission_term) {
        this.admission_term = admission_term;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getTerm_status() {
        return term_status;
    }

    public void setTerm_status(String term_status) {
        this.term_status = term_status;
    }

    public int getDetailed_status_id() {
        return detailed_status_id;
    }

    public void setDetailed_status_id(int detailed_status_id) {
        this.detailed_status_id = detailed_status_id;
    }

    public int getAffirmative_action_id() {
        return affirmative_action_id;
    }

    public void setAffirmative_action_id(int affirmative_action_id) {
        this.affirmative_action_id = affirmative_action_id;
    }

    public int getSecondary_school_id() {
        return secondary_school_id;
    }

    public void setSecondary_school_id(int secondary_school_id) {
        this.secondary_school_id = secondary_school_id;
    }

    public String getYear_graduation_secondary_school() {
        return year_graduation_secondary_school;
    }

    public void setYear_graduation_secondary_school(String year_graduation_secondary_school) {
        this.year_graduation_secondary_school = year_graduation_secondary_school;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public int getMandatory_hours() {
        return mandatory_hours;
    }

    public void setMandatory_hours(int mandatory_hours) {
        this.mandatory_hours = mandatory_hours;
    }

    public int getMandatory_credits() {
        return mandatory_credits;
    }

    public void setMandatory_credits(int mandatory_credits) {
        this.mandatory_credits = mandatory_credits;
    }

    public int getElective_hours() {
        return elective_hours;
    }

    public void setElective_hours(int elective_hours) {
        this.elective_hours = elective_hours;
    }

    public int getElective_credits() {
        return elective_credits;
    }

    public void setElective_credits(int elective_credits) {
        this.elective_credits = elective_credits;
    }

    public int getComplementary_hours() {
        return complementary_hours;
    }

    public void setComplementary_hours(int complementary_hours) {
        this.complementary_hours = complementary_hours;
    }

    public int getComplementary_credits() {
        return complementary_credits;
    }

    public void setComplementary_credits(int complementary_credits) {
        this.complementary_credits = complementary_credits;
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

    public int getTerms_count() {
        return terms_count;
    }

    public void setTerms_count(int terms_count) {
        this.terms_count = terms_count;
    }

    public int getSuspended_terms() {
        return suspended_terms;
    }

    public void setSuspended_terms(int suspended_terms) {
        this.suspended_terms = suspended_terms;
    }

    public int getInstitutional_terms() {
        return institutional_terms;
    }

    public void setInstitutional_terms(int institutional_terms) {
        this.institutional_terms = institutional_terms;
    }

    public int getMobility_terms() {
        return mobility_terms;
    }

    public void setMobility_terms(int mobility_terms) {
        this.mobility_terms = mobility_terms;
    }

    public int getEnrolled_credits() {
        return enrolled_credits;
    }

    public void setEnrolled_credits(int enrolled_credits) {
        this.enrolled_credits = enrolled_credits;
    }

    public double getAdmission_grade() {
        return admission_grade;
    }

    public void setAdmission_grade(double admission_grade) {
        this.admission_grade = admission_grade;
    }

    @Override
    public String toString() {
        return admission_id + ":" + admission_term + ":" + course_id + ":" + status_id + ":" + term_status +
                ":" + detailed_status_id + ":" + affirmative_action_id + ":" + secondary_school_id + ":" + year_graduation_secondary_school +
                ":" + curriculum + ":" + mandatory_hours + ":" + mandatory_credits + ":" + elective_hours +
                ":" + elective_credits + ":" + complementary_hours + ":" + complementary_credits + ":" + gpa + ":" + mc + ":" +
                ":" + iea + ":" + terms_count + ":" + suspended_terms + ":" + institutional_terms + ":" + mobility_terms + ":" +
                ":" + enrolled_credits + ":" + admission_grade;
    }
}
