package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectRetentionCSV implements Comparable {
    private String courseCode;
    private String curriculumCode;
    private int idealTerm;
    private String subjectCode;
    private String subjectName;
    private String registration;
    private int attemptedCredits;
    private int mandatoryCredits;
    private int optionalCredits;
    private int electiveCredits;
    private int complementaryCredits;
    private int completedTerms;
    private int suspendedTerms;
    private int institutionalTerms;
    private int mobilityTerms;
    private double gpa;

    public SubjectRetentionCSV(String courseCode, String curriculumCode, int idealTerm, String subjectCode, String subjectName,
                               String registration, int attemptedCredits, int mandatoryCredits, int optionalCredits,
                               int electiveCredits, int complementaryCredits, int completedTerms,
                               int suspendedTerms, int institutionalTerms, int mobilityTerms, double gpa) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.idealTerm = idealTerm;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.registration = registration;
        this.attemptedCredits = attemptedCredits;
        this.mandatoryCredits = mandatoryCredits;
        this.optionalCredits = optionalCredits;
        this.electiveCredits = electiveCredits;
        this.complementaryCredits = complementaryCredits;
        this.completedTerms = completedTerms;
        this.suspendedTerms = suspendedTerms;
        this.institutionalTerms = institutionalTerms;
        this.mobilityTerms = mobilityTerms;
        this.gpa = gpa;
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

    public int getIdealTerm() {
        return idealTerm;
    }

    public void setIdealTerm(int idealTerm) {
        this.idealTerm = idealTerm;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getAttemptedCredits() {
        return attemptedCredits;
    }

    public void setAttemptedCredits(int attemptedCredits) {
        this.attemptedCredits = attemptedCredits;
    }

    public int getMandatoryCredits() {
        return mandatoryCredits;
    }

    public void setMandatoryCredits(int mandatoryCredits) {
        this.mandatoryCredits = mandatoryCredits;
    }

    public int getOptionalCredits() {
        return optionalCredits;
    }

    public void setOptionalCredits(int optionalCredits) {
        this.optionalCredits = optionalCredits;
    }

    public int getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(int electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public int getComplementaryCredits() {
        return complementaryCredits;
    }

    public void setComplementaryCredits(int complementaryCredits) {
        this.complementaryCredits = complementaryCredits;
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Object o) {
        String thisValue = courseCode + curriculumCode + subjectCode + registration;
        SubjectRetentionCSV otherValue = (SubjectRetentionCSV) o;
        return thisValue.compareTo((otherValue.getCourseCode() + otherValue.getCurriculumCode()+
                otherValue.getSubjectCode() + otherValue.getRegistration()));
    }

    @Override
    public String toString() {
        return courseCode + ";" + curriculumCode + ";" + idealTerm + ";" + subjectCode +  ";" + subjectName + ";" +
                registration + ";" + attemptedCredits + ";" + mandatoryCredits + ";" + optionalCredits + ";" +
                electiveCredits + ";" + complementaryCredits + ";" + completedTerms + ";" + suspendedTerms + ";" +
                institutionalTerms + ";" + mobilityTerms + ";" + gpa;
    }
}
