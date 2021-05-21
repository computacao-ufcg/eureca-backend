package br.edu.ufcg.computacao.eureca.backend.core.models;

public class CurriculumData {
    private String idealMandatoryCredits;
    private String idealOptionalCredits;
    private String idealElectiveCredits;
    private String idealComplementaryCredits;
    private int minMandatoryCreditsNeeded;
    private int minOptionalCreditsNeeded;
    private int minElectiveCreditsNeeded;
    private int minComplementaryCreditsNeeded;
    private int minActivitiesNeeded;
    private int minNumberOfTerms;
    private int maxNumberOfTerms;
    private int minNumberOfEnrolledCredits;
    private int maxNumberOfEnrolledCredits;
    private int exceptionalAdditionalEnrolledCredits;
    private String mandatorySubjects;
    private String optionalSubjects;
    private String electiveSubjects;
    private String complementarySubjects;
    private String complementaryActivities;

    public CurriculumData(String idealMandatoryCredits, String idealOptionalCredits, String idealElectiveCredits,
                          String idealComplementaryCredits, int minMandatoryCreditsNeeded, int minOptionalCreditsNeeded,
                          int minElectiveCreditsNeeded, int minComplementaryCreditsNeeded, int minActivitiesNeeded,
                          int minNumberOfTerms, int maxNumberOfTerms, int minNumberOfEnrolledCredits,
                          int maxNumberOfEnrolledCredits, int exceptionalAdditionalEnrolledCredits,
                          String mandatorySubjects, String optionalSubjects, String electiveSubjects,
                          String complementarySubjects, String complementaryActivities) {
        this.idealMandatoryCredits = idealMandatoryCredits;
        this.idealOptionalCredits = idealOptionalCredits;
        this.idealElectiveCredits = idealElectiveCredits;
        this.idealComplementaryCredits = idealComplementaryCredits;
        this.minMandatoryCreditsNeeded = minMandatoryCreditsNeeded;
        this.minOptionalCreditsNeeded = minOptionalCreditsNeeded;
        this.minElectiveCreditsNeeded = minElectiveCreditsNeeded;
        this.minComplementaryCreditsNeeded = minComplementaryCreditsNeeded;
        this.minActivitiesNeeded = minActivitiesNeeded;
        this.minNumberOfTerms = minNumberOfTerms;
        this.maxNumberOfTerms = maxNumberOfTerms;
        this.minNumberOfEnrolledCredits = minNumberOfEnrolledCredits;
        this.maxNumberOfEnrolledCredits = maxNumberOfEnrolledCredits;
        this.exceptionalAdditionalEnrolledCredits = exceptionalAdditionalEnrolledCredits;
        this.mandatorySubjects = mandatorySubjects;
        this.optionalSubjects = optionalSubjects;
        this.electiveSubjects = electiveSubjects;
        this.complementarySubjects = complementarySubjects;
        this.complementaryActivities = complementaryActivities;
    }

    public CurriculumData() {
    }

    public String getIdealMandatoryCredits() {
        return idealMandatoryCredits;
    }

    public void setIdealMandatoryCredits(String idealMandatoryCredits) {
        this.idealMandatoryCredits = idealMandatoryCredits;
    }

    public String getIdealOptionalCredits() {
        return idealOptionalCredits;
    }

    public void setIdealOptionalCredits(String idealOptionalCredits) {
        this.idealOptionalCredits = idealOptionalCredits;
    }

    public String getIdealElectiveCredits() {
        return idealElectiveCredits;
    }

    public void setIdealElectiveCredits(String idealElectiveCredits) {
        this.idealElectiveCredits = idealElectiveCredits;
    }

    public String getIdealComplementaryCredits() {
        return idealComplementaryCredits;
    }

    public void setIdealComplementaryCredits(String idealComplementaryCredits) {
        this.idealComplementaryCredits = idealComplementaryCredits;
    }

    public int getMinMandatoryCreditsNeeded() {
        return minMandatoryCreditsNeeded;
    }

    public void setMinMandatoryCreditsNeeded(int minMandatoryCreditsNeeded) {
        this.minMandatoryCreditsNeeded = minMandatoryCreditsNeeded;
    }

    public int getMinOptionalCreditsNeeded() {
        return minOptionalCreditsNeeded;
    }

    public void setMinOptionalCreditsNeeded(int minOptionalCreditsNeeded) {
        this.minOptionalCreditsNeeded = minOptionalCreditsNeeded;
    }

    public int getMinElectiveCreditsNeeded() {
        return minElectiveCreditsNeeded;
    }

    public void setMinElectiveCreditsNeeded(int minElectiveCreditsNeeded) {
        this.minElectiveCreditsNeeded = minElectiveCreditsNeeded;
    }

    public int getMinComplementaryCreditsNeeded() {
        return minComplementaryCreditsNeeded;
    }

    public void setMinComplementaryCreditsNeeded(int minComplementaryCreditsNeeded) {
        this.minComplementaryCreditsNeeded = minComplementaryCreditsNeeded;
    }

    public int getMinActivitiesNeeded() {
        return minActivitiesNeeded;
    }

    public void setMinActivitiesNeeded(int minActivitiesNeeded) {
        this.minActivitiesNeeded = minActivitiesNeeded;
    }

    public int getMinNumberOfTerms() {
        return minNumberOfTerms;
    }

    public void setMinNumberOfTerms(int minNumberOfTerms) {
        this.minNumberOfTerms = minNumberOfTerms;
    }

    public int getMaxNumberOfTerms() {
        return maxNumberOfTerms;
    }

    public void setMaxNumberOfTerms(int maxNumberOfTerms) {
        this.maxNumberOfTerms = maxNumberOfTerms;
    }

    public int getMinNumberOfEnrolledCredits() {
        return minNumberOfEnrolledCredits;
    }

    public void setMinNumberOfEnrolledCredits(int minNumberOfEnrolledCredits) {
        this.minNumberOfEnrolledCredits = minNumberOfEnrolledCredits;
    }

    public int getMaxNumberOfEnrolledCredits() {
        return maxNumberOfEnrolledCredits;
    }

    public void setMaxNumberOfEnrolledCredits(int maxNumberOfEnrolledCredits) {
        this.maxNumberOfEnrolledCredits = maxNumberOfEnrolledCredits;
    }

    public int getExceptionalAdditionalEnrolledCredits() {
        return exceptionalAdditionalEnrolledCredits;
    }

    public void setExceptionalAdditionalEnrolledCredits(int exceptionalAdditionalEnrolledCredits) {
        this.exceptionalAdditionalEnrolledCredits = exceptionalAdditionalEnrolledCredits;
    }

    public String getMandatorySubjects() {
        return mandatorySubjects;
    }

    public void setMandatorySubjects(String mandatorySubjects) {
        this.mandatorySubjects = mandatorySubjects;
    }

    public String getOptionalSubjects() {
        return optionalSubjects;
    }

    public void setOptionalSubjects(String optionalSubjects) {
        this.optionalSubjects = optionalSubjects;
    }

    public String getElectiveSubjects() {
        return electiveSubjects;
    }

    public void setElectiveSubjects(String electiveSubjects) {
        this.electiveSubjects = electiveSubjects;
    }

    public String getComplementarySubjects() {
        return complementarySubjects;
    }

    public void setComplementarySubjects(String complementarySubjects) {
        this.complementarySubjects = complementarySubjects;
    }

    public String getComplementaryActivities() {
        return complementaryActivities;
    }

    public void setComplementaryActivities(String complementaryActivities) {
        this.complementaryActivities = complementaryActivities;
    }
}
