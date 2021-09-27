package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import br.edu.ufcg.computacao.eureca.backend.core.models.Curriculum;

import java.util.ArrayList;
import java.util.Collection;

public class CurriculumData implements EurecaMapValue {
    private String idealMandatoryCreditsList;
    private String idealOptionalCreditsList;
    private String idealElectiveCreditsList;
    private String idealComplementaryCreditsList;
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
    private ArrayList<Integer> idealMandatoryCredits;
    private ArrayList<Integer> idealOptionalCredits;
    private ArrayList<Integer> idealElectiveCredits;
    private ArrayList<Integer> idealComplementaryCredits;
    private ArrayList<Integer> expectedMinAccumulatedCredits;

    public CurriculumData(String idealMandatoryCreditsList, String idealOptionalCreditsList, String idealElectiveCreditsList,
                          String idealComplementaryCreditsList, int minMandatoryCreditsNeeded, int minOptionalCreditsNeeded,
                          int minElectiveCreditsNeeded, int minComplementaryCreditsNeeded, int minActivitiesNeeded,
                          int minNumberOfTerms, int maxNumberOfTerms, int minNumberOfEnrolledCredits,
                          int maxNumberOfEnrolledCredits, int exceptionalAdditionalEnrolledCredits,
                          String mandatorySubjectCodes, String optionalSubjectCodes, String electiveSubjectCodes,
                          String complementarySubjectCodes, String complementaryActivitiesCodes) {
        this.idealMandatoryCreditsList = idealMandatoryCreditsList;
        this.idealOptionalCreditsList = idealOptionalCreditsList;
        this.idealElectiveCreditsList = idealElectiveCreditsList;
        this.idealComplementaryCreditsList = idealComplementaryCreditsList;
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

    private void parseLists() {
        if (this.expectedMinAccumulatedCredits != null) return;
        this.idealMandatoryCredits = extractIntegerList(this.idealMandatoryCreditsList);
        this.idealOptionalCredits = extractIntegerList(this.idealOptionalCreditsList);
        this.idealComplementaryCredits = extractIntegerList(this.idealComplementaryCreditsList);
        this.idealElectiveCredits = extractIntegerList(this.idealElectiveCreditsList);
        this.expectedMinAccumulatedCredits = new ArrayList<>();
        int previousTermIdealCredits = 0;
        for (int i = 0; i < this.minNumberOfTerms; i++) {
            int currentTermIdealCredits = this.idealMandatoryCredits.get(i) + this.idealComplementaryCredits.get(i) +
                    this.idealOptionalCredits.get(i) + this.idealElectiveCredits.get(i);
            int minExpectedAccumulatedCredits = previousTermIdealCredits + currentTermIdealCredits/2;
            this.expectedMinAccumulatedCredits.add(i, minExpectedAccumulatedCredits);
            previousTermIdealCredits += currentTermIdealCredits;
        }
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

    public ArrayList<Integer> getIdealMandatoryCredits() {
        parseLists();
        return idealMandatoryCredits;
    }

    public ArrayList<Integer> getIdealOptionalCredits() {
        parseLists();
        return idealOptionalCredits;
    }

    public ArrayList<Integer> getIdealElectiveCredits() {
        parseLists();
        return idealElectiveCredits;
    }

    public ArrayList<Integer> getIdealComplementaryCredits() {
        parseLists();
        return idealComplementaryCredits;
    }

    public ArrayList<Integer> getExpectedMinAccumulatedCredits() {
        parseLists();
        return expectedMinAccumulatedCredits;
    }

    public int getExpectedMinAccumulatedCredits(int subjectIdealTerm) {
        parseLists();
        return (subjectIdealTerm > expectedMinAccumulatedCredits.size() ?
                expectedMinAccumulatedCredits.get(expectedMinAccumulatedCredits.size() - 1) :
                expectedMinAccumulatedCredits.get(subjectIdealTerm - 1));
    }

    public Curriculum createCurriculum(CurriculumKey key) {
        return new Curriculum(key.getCourseCode(), key.getCurriculumCode(), getIdealMandatoryCredits(),
                getIdealOptionalCredits(), getIdealElectiveCredits(), getIdealComplementaryCredits(),
                getExpectedMinAccumulatedCredits(),
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
