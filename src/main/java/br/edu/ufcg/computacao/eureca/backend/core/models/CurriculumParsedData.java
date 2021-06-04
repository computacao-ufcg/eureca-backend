package br.edu.ufcg.computacao.eureca.backend.core.models;

public class CurriculumParsedData {
    private int idealTerms;
    private int idealMandatoryCredits[];
    private int idealOptionalCredits[];
    private int idealElectiveCredits[];
    private int idealComplementaryCredits[];
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
    private String mandatorySubjects[];
    private String optionalSubjects[];
    private String electiveSubjects[];
    private String complementarySubjects[];
    private String complementaryActivities[];

    public CurriculumParsedData(CurriculumData curriculumData) {
        this.idealMandatoryCredits = parseStrToIntArray(curriculumData.getIdealMandatoryCredits());
        this.idealTerms = this.idealMandatoryCredits.length;
        this.idealOptionalCredits = parseStrToIntArray(curriculumData.getIdealMandatoryCredits());
        this.idealElectiveCredits = parseStrToIntArray(curriculumData.getIdealMandatoryCredits());
        this.idealComplementaryCredits = parseStrToIntArray(curriculumData.getIdealMandatoryCredits());
        this.minMandatoryCreditsNeeded = curriculumData.getMinMandatoryCreditsNeeded();
        this.minOptionalCreditsNeeded = curriculumData.getMinOptionalCreditsNeeded();
        this.minElectiveCreditsNeeded = curriculumData.getMinElectiveCreditsNeeded();
        this.minComplementaryCreditsNeeded = curriculumData.getMinComplementaryCreditsNeeded();
        this.minActivitiesNeeded = curriculumData.getMinActivitiesNeeded();
        this.minNumberOfTerms = curriculumData.getMinNumberOfTerms();
        this.maxNumberOfTerms = maxNumberOfTerms;
        this.minNumberOfEnrolledCredits = minNumberOfEnrolledCredits;
        this.maxNumberOfEnrolledCredits = maxNumberOfEnrolledCredits;
        this.exceptionalAdditionalEnrolledCredits = exceptionalAdditionalEnrolledCredits;
        this.mandatorySubjects = parseStrToStrArray(curriculumData.getMandatorySubjects());
        this.optionalSubjects = parseStrToStrArray(curriculumData.getOptionalSubjects());
        this.electiveSubjects = parseStrToStrArray(curriculumData.getElectiveSubjects());
        this.complementarySubjects = parseStrToStrArray(curriculumData.getComplementarySubjects());
        this.complementaryActivities = parseStrToStrArray(curriculumData.getComplementaryActivities());
    }

    private int[] parseStrToIntArray(String str) {
        String tmp[] = parseStrToStrArray(str);
        int[] ret = new int[tmp.length];
        for (int i=0; i < ret.length; i++) {
            ret[i] = Integer.parseInt(tmp[i]);
        }
        return ret;
    }

    private String[] parseStrToStrArray(String str) {
        String ret[] = str.split(",");
        return ret;
    }

    public CurriculumParsedData() {
    }

    public int getIdealTerms() {
        return idealTerms;
    }

    public void setIdealTerms(int idealTerms) {
        this.idealTerms = idealTerms;
    }

    public int[] getIdealMandatoryCredits() {
        return idealMandatoryCredits;
    }

    public void setIdealMandatoryCredits(int[] idealMandatoryCredits) {
        this.idealMandatoryCredits = idealMandatoryCredits;
    }

    public int[] getIdealOptionalCredits() {
        return idealOptionalCredits;
    }

    public void setIdealOptionalCredits(int[] idealOptionalCredits) {
        this.idealOptionalCredits = idealOptionalCredits;
    }

    public int[] getIdealElectiveCredits() {
        return idealElectiveCredits;
    }

    public void setIdealElectiveCredits(int[] idealElectiveCredits) {
        this.idealElectiveCredits = idealElectiveCredits;
    }

    public int[] getIdealComplementaryCredits() {
        return idealComplementaryCredits;
    }

    public void setIdealComplementaryCredits(int[] idealComplementaryCredits) {
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

    public String[] getMandatorySubjects() {
        return mandatorySubjects;
    }

    public void setMandatorySubjects(String[] mandatorySubjects) {
        this.mandatorySubjects = mandatorySubjects;
    }

    public String[] getOptionalSubjects() {
        return optionalSubjects;
    }

    public void setOptionalSubjects(String[] optionalSubjects) {
        this.optionalSubjects = optionalSubjects;
    }

    public String[] getElectiveSubjects() {
        return electiveSubjects;
    }

    public void setElectiveSubjects(String[] electiveSubjects) {
        this.electiveSubjects = electiveSubjects;
    }

    public String[] getComplementarySubjects() {
        return complementarySubjects;
    }

    public void setComplementarySubjects(String[] complementarySubjects) {
        this.complementarySubjects = complementarySubjects;
    }

    public String[] getComplementaryActivities() {
        return complementaryActivities;
    }

    public void setComplementaryActivities(String[] complementaryActivities) {
        this.complementaryActivities = complementaryActivities;
    }
}
