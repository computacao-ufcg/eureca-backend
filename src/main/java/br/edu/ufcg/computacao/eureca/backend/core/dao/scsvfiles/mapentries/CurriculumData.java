package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import br.edu.ufcg.computacao.eureca.backend.core.models.Curriculum;

import java.util.ArrayList;
import java.util.Collection;

public class CurriculumData implements EurecaMapValue {
    private String idealMandatoryCredits;
    private ArrayList<Integer> idealMandatoryCreditsList;
    private String idealOptionalCredits;
    private ArrayList<Integer> idealOptionalCreditsList;
    private String idealElectiveCredits;
    private ArrayList<Integer> idealElectiveCreditsList;
    private String idealComplementaryCredits;
    private ArrayList<Integer> idealComplementaryCreditsList;
    private String expectedMinAccumulatedCredits;
    private ArrayList<Integer> expectedMinAccumulatedCreditsList;
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
    private Collection<String> mandatorySubjectsList;
    private String optionalSubjects;
    private Collection<String> optionalSubjectsList;
    private String electiveSubjects;
    private Collection<String> electiveSubjectsList;
    private String complementarySubjects;
    private Collection<String> complementarySubjectsList;
    private String complementaryActivities;
    private Collection<String> complementaryActivitiesList;

    public CurriculumData(String idealMandatoryCredits, String idealOptionalCredits, String idealElectiveCredits,
                          String idealComplementaryCredits, String expectedMinAccumulatedCredits,
                          int minMandatoryCreditsNeeded, int minOptionalCreditsNeeded,
                          int minElectiveCreditsNeeded, int minComplementaryCreditsNeeded, int minActivitiesNeeded,
                          int minNumberOfTerms, int maxNumberOfTerms, int minNumberOfEnrolledCredits,
                          int maxNumberOfEnrolledCredits, int exceptionalAdditionalEnrolledCredits,
                          String mandatorySubjects, String optionalSubjects, String electiveSubjects,
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
        this.optionalSubjects = optionalSubjects;
        this.electiveSubjects = electiveSubjects;
        this.complementarySubjects = complementarySubjects;
        this.complementaryActivities = complementaryActivities;
        this.idealMandatoryCreditsList = extractIntegerList(idealMandatoryCredits);
        this.idealOptionalCreditsList = extractIntegerList(idealOptionalCredits);
        this.idealElectiveCreditsList = extractIntegerList(idealElectiveCredits);
        this.idealComplementaryCreditsList = extractIntegerList(idealComplementaryCredits);
        this.expectedMinAccumulatedCreditsList = extractIntegerList(expectedMinAccumulatedCredits);
        this.mandatorySubjectsList = null;
        this.optionalSubjectsList = null;
        this.electiveSubjectsList = null;
        this.complementarySubjectsList = null;
        this.complementaryActivitiesList = null;
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

    public int getIdealMandatoryCreditsList(int index) {
        if (idealMandatoryCreditsList == null) {
            idealMandatoryCreditsList = extractIntegerList(this.idealMandatoryCredits);
        }
        return idealMandatoryCreditsList.get(index - 1).intValue();
    }

    public int getIdealOptionalCreditsList(int index) {
        if (idealOptionalCreditsList == null) {
            idealOptionalCreditsList = extractIntegerList(this.idealOptionalCredits);
        }
        return idealOptionalCreditsList.get(index - 1).intValue();
    }

    public int getIdealElectiveCreditsList(int index) {
        if (idealElectiveCreditsList == null) {
            idealElectiveCreditsList = extractIntegerList(this.idealElectiveCredits);
        }
        return idealElectiveCreditsList.get(index - 1).intValue();
    }

    public int getIdealComplementaryCreditsList(int index) {
        if (idealComplementaryCreditsList == null) {
            idealComplementaryCreditsList = extractIntegerList(this.idealComplementaryCredits);
        }
        return idealComplementaryCreditsList.get(index - 1).intValue();
    }

    public int getExpectedMinAccumulatedCreditsList(int index) {
        if (expectedMinAccumulatedCreditsList == null) {
            expectedMinAccumulatedCreditsList = extractIntegerList(this.expectedMinAccumulatedCredits);
        }
        return expectedMinAccumulatedCreditsList.get(index - 1).intValue();
    }

    public Collection<String> getMandatorySubjectsList() {
        if (mandatorySubjectsList == null) {
            mandatorySubjectsList = extractStrList(mandatorySubjects);
        }
        return mandatorySubjectsList;
    }

    public Collection<String> getOptionalSubjectsList() {
        if (optionalSubjectsList == null) {
            optionalSubjectsList = extractStrList(optionalSubjects);
        }
        return optionalSubjectsList;
    }

    public Collection<String> getElectiveSubjectsList() {
        if (electiveSubjectsList == null) {
            electiveSubjectsList = extractStrList(electiveSubjects);
        }
        return electiveSubjectsList;
    }

    public Collection<String> getComplementarySubjectsList() {
        if (complementarySubjectsList == null) {
            complementarySubjectsList = extractStrList(complementarySubjects);
        }
        return complementarySubjectsList;
    }

    public Collection<String> getComplementaryActivitiesList() {
        if (complementaryActivitiesList == null) {
            complementaryActivitiesList = extractStrList(complementaryActivities);
        }
        return complementaryActivitiesList;
    }

    public Curriculum createCurriculum(CurriculumKey key) {
        return new Curriculum(key.getCourse(), key.getCode(), getIdealMandatoryCredits(), getIdealOptionalCredits(),
                getIdealElectiveCredits(), getIdealComplementaryCredits(), getMinMandatoryCreditsNeeded(),
                getMinOptionalCreditsNeeded(), getMinElectiveCreditsNeeded(), getMinComplementaryCreditsNeeded(),
                getMinActivitiesNeeded(), getMinNumberOfTerms(), getMaxNumberOfTerms(), getMinNumberOfEnrolledCredits(),
                getMaxNumberOfEnrolledCredits(), getExceptionalAdditionalEnrolledCredits(), getMandatorySubjects(),
                getOptionalSubjects(), getElectiveSubjects(), getComplementarySubjects(), getComplementaryActivities());
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
