package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import br.edu.ufcg.computacao.eureca.backend.core.models.Curriculum;

import java.util.ArrayList;
import java.util.Collection;

public class CurriculumData implements EurecaMapValue {
    private String idealMandatoryCredits;
    private String idealOptionalCredits;
    private String idealElectiveCredits;
    private String idealComplementaryCredits;
    private String expectedMinAccumulatedCredits;
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
                          String idealComplementaryCredits, String expectedMinAccumulatedCredits,
                          int minMandatoryCreditsNeeded, int minOptionalCreditsNeeded,
                          int minElectiveCreditsNeeded, int minComplementaryCreditsNeeded, int minActivitiesNeeded,
                          int minNumberOfTerms, int maxNumberOfTerms, int minNumberOfEnrolledCredits,
                          int maxNumberOfEnrolledCredits, int exceptionalAdditionalEnrolledCredits,
                          String mandatorySubjects, String optionalSubject, String electiveSubjects,
                          String complementarySubjects, String complementaryActivities) {
        this.idealMandatoryCredits = idealMandatoryCredits;
        this.idealOptionalCredits = idealOptionalCredits;
        this.idealElectiveCredits = idealElectiveCredits;
        this.idealComplementaryCredits = idealComplementaryCredits;
        this.expectedMinAccumulatedCredits = expectedMinAccumulatedCredits;
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
        this.optionalSubjects = optionalSubject;
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

    public String getExpectedMinAccumulatedCredits() {
        return expectedMinAccumulatedCredits;
    }

    public void setExpectedMinAccumulatedCredits(String expectedMinAccumulatedCredits) {
        this.expectedMinAccumulatedCredits = expectedMinAccumulatedCredits;
    }

    public int getExpectedMinAccumulatedCredits(int subjectIdealTerm) {
        ArrayList<Integer> expectedMinAccumulatedCreditsList = extractIntegerList(this.expectedMinAccumulatedCredits);
        return (subjectIdealTerm >= expectedMinAccumulatedCreditsList.size() ? -1 : expectedMinAccumulatedCreditsList.get(subjectIdealTerm));
    }

    public Curriculum createCurriculum(CurriculumKey key) {
        return new Curriculum(key.getCourseCode(), key.getCurriculumCode(), extractIntegerList(getIdealMandatoryCredits()),
                extractIntegerList(getIdealOptionalCredits()), extractIntegerList(getIdealElectiveCredits()),
                extractIntegerList(getIdealComplementaryCredits()), extractIntegerList(getExpectedMinAccumulatedCredits()),
                getMinMandatoryCreditsNeeded(), getMinOptionalCreditsNeeded(), getMinElectiveCreditsNeeded(),
                getMinComplementaryCreditsNeeded(), getMinActivitiesNeeded(), getMinNumberOfTerms(), getMaxNumberOfTerms(),
                getMinNumberOfEnrolledCredits(), getMaxNumberOfEnrolledCredits(), getExceptionalAdditionalEnrolledCredits(),
                extractStrList(getMandatorySubjects()), extractStrList(getOptionalSubjects()),
                extractStrList(getElectiveSubjects()), extractStrList(getComplementarySubjects()),
                extractStrList(getComplementaryActivities()));
    }

    public Collection<String> getAllSubjectsList() {
        return extractStrList(getMandatorySubjects() + "," + getOptionalSubjects() + "," +
                getElectiveSubjects() + "," + getComplementarySubjects());
    }

    private ArrayList<Integer> extractIntegerList(String idealCreditsStr) {
        String creditsArray[] = idealCreditsStr.split(",");
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 0; i < creditsArray.length; i++) {
            ret.add(i, new Integer(creditsArray[i]));
        }
        return ret;
    }

    private Collection<String> extractStrList(String subjectsStr) {
        String creditsArray[] = subjectsStr.split(",");
        ArrayList<String> ret = new ArrayList<>();
        for (int i =0; i < creditsArray.length; i++) {
            ret.add(creditsArray[i]);
        }
        return ret;
    }
}
