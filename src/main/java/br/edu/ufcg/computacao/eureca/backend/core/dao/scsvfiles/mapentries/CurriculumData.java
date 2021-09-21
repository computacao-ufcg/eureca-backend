package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import br.edu.ufcg.computacao.eureca.backend.core.models.Curriculum;

import java.util.ArrayList;
import java.util.Collection;

public class CurriculumData implements EurecaMapValue {
    private String idealMandatoryCreditsList;
    private String idealOptionalCreditsList;
    private String idealElectiveCreditsList;
    private String idealComplementaryCreditsList;
    private String expectedMinAccumulatedCreditsList;
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
    private String mandatorySubjectCodes;
    private String optionalSubjectCodes;
    private String electiveSubjectCodes;
    private String complementarySubjectCodes;
    private String complementaryActivitiesCodes;

    public CurriculumData(String idealMandatoryCreditsList, String idealOptionalCreditsList, String idealElectiveCreditsList,
                          String idealComplementaryCreditsList, String expectedMinAccumulatedCreditsList,
                          int minMandatoryCreditsNeeded, int minOptionalCreditsNeeded,
                          int minElectiveCreditsNeeded, int minComplementaryCreditsNeeded, int minActivitiesNeeded,
                          int minNumberOfTerms, int maxNumberOfTerms, int minNumberOfEnrolledCredits,
                          int maxNumberOfEnrolledCredits, int exceptionalAdditionalEnrolledCredits,
                          String mandatorySubjectCodes, String optionalSubjectCodes, String electiveSubjectCodes,
                          String complementarySubjectCodes, String complementaryActivitiesCodes) {
        this.idealMandatoryCreditsList = idealMandatoryCreditsList;
        this.idealOptionalCreditsList = idealOptionalCreditsList;
        this.idealElectiveCreditsList = idealElectiveCreditsList;
        this.idealComplementaryCreditsList = idealComplementaryCreditsList;
        this.expectedMinAccumulatedCreditsList = expectedMinAccumulatedCreditsList;
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
        this.mandatorySubjectCodes = mandatorySubjectCodes;
        this.optionalSubjectCodes = optionalSubjectCodes;
        this.electiveSubjectCodes = electiveSubjectCodes;
        this.complementarySubjectCodes = complementarySubjectCodes;
        this.complementaryActivitiesCodes = complementaryActivitiesCodes;
    }

    public CurriculumData() {
    }

    public String getIdealMandatoryCreditsList() {
        return idealMandatoryCreditsList;
    }

    public void setIdealMandatoryCreditsList(String idealMandatoryCreditsList) {
        this.idealMandatoryCreditsList = idealMandatoryCreditsList;
    }

    public String getIdealOptionalCreditsList() {
        return idealOptionalCreditsList;
    }

    public void setIdealOptionalCreditsList(String idealOptionalCreditsList) {
        this.idealOptionalCreditsList = idealOptionalCreditsList;
    }

    public String getIdealElectiveCreditsList() {
        return idealElectiveCreditsList;
    }

    public void setIdealElectiveCreditsList(String idealElectiveCreditsList) {
        this.idealElectiveCreditsList = idealElectiveCreditsList;
    }

    public String getIdealComplementaryCreditsList() {
        return idealComplementaryCreditsList;
    }

    public void setIdealComplementaryCreditsList(String idealComplementaryCreditsList) {
        this.idealComplementaryCreditsList = idealComplementaryCreditsList;
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

    public String getMandatorySubjectCodes() {
        return mandatorySubjectCodes;
    }

    public void setMandatorySubjectCodes(String mandatorySubjectCodes) {
        this.mandatorySubjectCodes = mandatorySubjectCodes;
    }

    public String getOptionalSubjectCodes() {
        return optionalSubjectCodes;
    }

    public void setOptionalSubjectCodes(String optionalSubjectCodes) {
        this.optionalSubjectCodes = optionalSubjectCodes;
    }

    public String getElectiveSubjectCodes() {
        return electiveSubjectCodes;
    }

    public void setElectiveSubjectCodes(String electiveSubjectCodes) {
        this.electiveSubjectCodes = electiveSubjectCodes;
    }

    public String getComplementarySubjectCodes() {
        return complementarySubjectCodes;
    }

    public void setComplementarySubjectCodes(String complementarySubjectCodes) {
        this.complementarySubjectCodes = complementarySubjectCodes;
    }

    public String getComplementaryActivitiesCodes() {
        return complementaryActivitiesCodes;
    }

    public void setComplementaryActivitiesCodes(String complementaryActivitiesCodes) {
        this.complementaryActivitiesCodes = complementaryActivitiesCodes;
    }

    public String getExpectedMinAccumulatedCreditsList() {
        return expectedMinAccumulatedCreditsList;
    }

    public void setExpectedMinAccumulatedCreditsList(String expectedMinAccumulatedCreditsList) {
        this.expectedMinAccumulatedCreditsList = expectedMinAccumulatedCreditsList;
    }

    public int getExpectedMinAccumulatedCredits(int subjectIdealTerm) {
        ArrayList<Integer> expectedMinAccumulatedCreditsList = extractIntegerList(this.expectedMinAccumulatedCreditsList);
        return (subjectIdealTerm >= expectedMinAccumulatedCreditsList.size() ? -1 : expectedMinAccumulatedCreditsList.get(subjectIdealTerm));
    }

    public Curriculum createCurriculum(CurriculumKey key) {
        return new Curriculum(key.getCourseCode(), key.getCurriculumCode(), extractIntegerList(getIdealMandatoryCreditsList()),
                extractIntegerList(getIdealOptionalCreditsList()), extractIntegerList(getIdealElectiveCreditsList()),
                extractIntegerList(getIdealComplementaryCreditsList()), extractIntegerList(getExpectedMinAccumulatedCreditsList()),
                getMinMandatoryCreditsNeeded(), getMinOptionalCreditsNeeded(), getMinElectiveCreditsNeeded(),
                getMinComplementaryCreditsNeeded(), getMinActivitiesNeeded(), getMinNumberOfTerms(), getMaxNumberOfTerms(),
                getMinNumberOfEnrolledCredits(), getMaxNumberOfEnrolledCredits(), getExceptionalAdditionalEnrolledCredits(),
                extractStrList(getMandatorySubjectCodes()), extractStrList(getOptionalSubjectCodes()),
                extractStrList(getElectiveSubjectCodes()), extractStrList(getComplementarySubjectCodes()),
                extractStrList(getComplementaryActivitiesCodes()));
    }

    public Collection<String> getAllSubjectsList() {
        return extractStrList(getMandatorySubjectCodes() + "," + getOptionalSubjectCodes() + "," +
                getElectiveSubjectCodes() + "," + getComplementarySubjectCodes());
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
