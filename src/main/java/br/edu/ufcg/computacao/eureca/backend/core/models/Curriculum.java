package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.ArrayList;
import java.util.Collection;

public class Curriculum {
    private String courseCode;
    private String curriculumCode;
    private ArrayList<Integer> idealMandatoryCreditsList;
    private ArrayList<Integer> idealOptionalCreditsList;
    private ArrayList<Integer> idealElectiveCreditsList;
    private ArrayList<Integer> idealComplementaryCreditsList;
    private ArrayList<Integer> expectedMinAccumulatedCredits;
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
    private Collection<String> mandatorySubjectsList;
    private Collection<String> optionalSubjectsList;
    private Collection<String> electiveSubjectsList;
    private Collection<String> complementarySubjectsList;
    private Collection<String> complementaryActivitiesList;

    public Curriculum(String courseCode, String curriculumCode, ArrayList<Integer> idealMandatoryCreditsList,
                      ArrayList<Integer> idealOptionalCreditsList, ArrayList<Integer> idealElectiveCreditsList,
                      ArrayList<Integer> idealComplementaryCreditsList, ArrayList<Integer> expectedMinAccumulatedCredits,
                      int minMandatoryCreditsNeeded, int minOptionalCreditsNeeded, int minElectiveCreditsNeeded,
                      int minComplementaryCreditsNeeded, int minActivitiesNeeded, int minNumberOfTerms,
                      int maxNumberOfTerms, int minNumberOfEnrolledCredits, int maxNumberOfEnrolledCredits,
                      int exceptionalAdditionalEnrolledCredits, Collection<String> mandatorySubjectsList,
                      Collection<String> optionalSubjectsList, Collection<String> electiveSubjectsList,
                      Collection<String> complementarySubjectsList, Collection<String> complementaryActivitiesList) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.idealMandatoryCreditsList = idealMandatoryCreditsList;
        this.idealOptionalCreditsList = idealOptionalCreditsList;
        this.idealElectiveCreditsList = idealElectiveCreditsList;
        this.idealComplementaryCreditsList = idealComplementaryCreditsList;
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
        this.mandatorySubjectsList = mandatorySubjectsList;
        this.optionalSubjectsList = optionalSubjectsList;
        this.electiveSubjectsList = electiveSubjectsList;
        this.complementarySubjectsList = complementarySubjectsList;
        this.complementaryActivitiesList = complementaryActivitiesList;
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

    public ArrayList<Integer> getIdealMandatoryCreditsList() {
        return idealMandatoryCreditsList;
    }

    public Integer getIdealMandatoryCredits(int index) {
        return idealMandatoryCreditsList.get(index);
    }

    public void setIdealMandatoryCreditsList(ArrayList<Integer> idealMandatoryCreditsList) {
        this.idealMandatoryCreditsList = idealMandatoryCreditsList;
    }

    public ArrayList<Integer> getIdealOptionalCreditsList() {
        return idealOptionalCreditsList;
    }

    public Integer getIdealOptionalCredits(int index) {
        return idealOptionalCreditsList.get(index);
    }

    public void setIdealOptionalCreditsList(ArrayList<Integer> idealOptionalCreditsList) {
        this.idealOptionalCreditsList = idealOptionalCreditsList;
    }

    public ArrayList<Integer> getIdealElectiveCreditsList() {
        return idealElectiveCreditsList;
    }

    public Integer getIdealElectiveCredits(int index) {
        return idealElectiveCreditsList.get(index);
    }

    public void setIdealElectiveCreditsList(ArrayList<Integer> idealElectiveCreditsList) {
        this.idealElectiveCreditsList = idealElectiveCreditsList;
    }

    public ArrayList<Integer> getIdealComplementaryCreditsList() {
        return idealComplementaryCreditsList;
    }

    public Integer getIdealComplementaryCredits(int index) {
        return idealComplementaryCreditsList.get(index);
    }

    public void setIdealComplementaryCreditsList(ArrayList<Integer> idealComplementaryCreditsList) {
        this.idealComplementaryCreditsList = idealComplementaryCreditsList;
    }

    public ArrayList<Integer> getExpectedMinAccumulatedCreditsList() {
        return expectedMinAccumulatedCredits;
    }

    public Integer getExpectedMinAccumulatedCredits(int index) {
        return expectedMinAccumulatedCredits.get(index);
    }

    public void setExpectedMinAccumulatedCredits(ArrayList<Integer> expectedMinAccumulatedCredits) {
        this.expectedMinAccumulatedCredits = expectedMinAccumulatedCredits;
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

    public Collection<String> getMandatorySubjectsList() {
        return mandatorySubjectsList;
    }

    public void setMandatorySubjectsList(Collection<String> mandatorySubjectsList) {
        this.mandatorySubjectsList = mandatorySubjectsList;
    }

    public Collection<String> getOptionalSubjectsList() {
        return optionalSubjectsList;
    }

    public void setOptionalSubjectsList(Collection<String> optionalSubjectsList) {
        this.optionalSubjectsList = optionalSubjectsList;
    }

    public Collection<String> getElectiveSubjectsList() {
        return electiveSubjectsList;
    }

    public void setElectiveSubjectsList(Collection<String> electiveSubjectsList) {
        this.electiveSubjectsList = electiveSubjectsList;
    }

    public Collection<String> getComplementarySubjectsList() {
        return complementarySubjectsList;
    }

    public void setComplementarySubjectsList(Collection<String> complementarySubjectsList) {
        this.complementarySubjectsList = complementarySubjectsList;
    }

    public Collection<String> getComplementaryActivitiesList() {
        return complementaryActivitiesList;
    }

    public void setComplementaryActivitiesList(Collection<String> complementaryActivitiesList) {
        this.complementaryActivitiesList = complementaryActivitiesList;
    }

    public double getMinNumberOfCreditsNeeded() {
        return this.getMinMandatoryCreditsNeeded() + this.getMinElectiveCreditsNeeded() +
                this.getMinOptionalCreditsNeeded() + getMinComplementaryCreditsNeeded();
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "courseCode='" + courseCode + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", idealMandatoryCreditsList=" + idealMandatoryCreditsList +
                ", idealOptionalCreditsList=" + idealOptionalCreditsList +
                ", idealElectiveCreditsList=" + idealElectiveCreditsList +
                ", idealComplementaryCreditsList=" + idealComplementaryCreditsList +
                ", expectedMinAccumulatedCredits=" + expectedMinAccumulatedCredits +
                ", minMandatoryCreditsNeeded=" + minMandatoryCreditsNeeded +
                ", minOptionalCreditsNeeded=" + minOptionalCreditsNeeded +
                ", minElectiveCreditsNeeded=" + minElectiveCreditsNeeded +
                ", minComplementaryCreditsNeeded=" + minComplementaryCreditsNeeded +
                ", minActivitiesNeeded=" + minActivitiesNeeded +
                ", minNumberOfTerms=" + minNumberOfTerms +
                ", maxNumberOfTerms=" + maxNumberOfTerms +
                ", minNumberOfEnrolledCredits=" + minNumberOfEnrolledCredits +
                ", maxNumberOfEnrolledCredits=" + maxNumberOfEnrolledCredits +
                ", exceptionalAdditionalEnrolledCredits=" + exceptionalAdditionalEnrolledCredits +
                ", mandatorySubjectsList=" + mandatorySubjectsList +
                ", optionalSubjectsList=" + optionalSubjectsList +
                ", electiveSubjectsList=" + electiveSubjectsList +
                ", complementarySubjectsList=" + complementarySubjectsList +
                ", complementaryActivitiesList=" + complementaryActivitiesList +
                '}';
    }
}
