package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.ArrayList;
import java.util.Collection;

public class Curriculum {
    private String course;
    private String code;
    private String idealMandatoryCredits;
    private Collection<Integer> idealMandatoryCreditsList;
    private String idealOptionalCredits;
    private Collection<Integer> idealOptionalCreditsList;
    private String idealElectiveCredits;
    private Collection<Integer> idealElectiveCreditsList;
    private String idealComplementaryCredits;
    private Collection<Integer> idealComplementaryCreditsList;
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

    public Curriculum(String course, String code, String idealMandatoryCredits, String idealOptionalCredits,
                      String idealElectiveCredits, String idealComplementaryCredits, int minMandatoryCreditsNeeded,
                      int minOptionalCreditsNeeded, int minElectiveCreditsNeeded, int minComplementaryCreditsNeeded,
                      int minActivitiesNeeded, int minNumberOfTerms, int maxNumberOfTerms, int minNumberOfEnrolledCredits,
                      int maxNumberOfEnrolledCredits, int exceptionalAdditionalEnrolledCredits,
                      String mandatorySubjects, String optionalSubjects, String electiveSubjects,
                      String complementarySubjects, String complementaryActivities) {
        this.course = course;
        this.code = code;
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
        this.idealMandatoryCreditsList = null;
        this.idealOptionalCreditsList = null;
        this.idealElectiveCreditsList = null;
        this.idealComplementaryCreditsList = null;
        this.mandatorySubjectsList = null;
        this.optionalSubjectsList = null;
        this.electiveSubjectsList = null;
        this.complementarySubjectsList = null;
        this.complementaryActivitiesList = null;
    }

    public Curriculum() {
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Collection<Integer> getIdealMandatoryCreditsList() {
        if (idealMandatoryCreditsList == null) {
            idealOptionalCreditsList = extractIntegerList(idealMandatoryCredits);
        }
        return idealMandatoryCreditsList;
    }

    public Collection<Integer> getIdealOptionalCreditsList() {
        if (idealOptionalCreditsList == null) {
            idealOptionalCreditsList = extractIntegerList(idealOptionalCredits);
        }
        return idealOptionalCreditsList;
    }

    public Collection<Integer> getIdealElectiveCreditsList() {
        if (idealElectiveCreditsList == null) {
            idealElectiveCreditsList = extractIntegerList(idealElectiveCredits);
        }
        return idealElectiveCreditsList;
    }

    public Collection<Integer> getIdealComplementaryCreditsList() {
        if (idealComplementaryCreditsList == null) {
            idealComplementaryCreditsList = extractIntegerList(idealComplementaryCredits);
        }
        return idealComplementaryCreditsList;
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

    private Collection<Integer> extractIntegerList(String idealCreditsStr) {
        String creditsArray[] = idealCreditsStr.split(",");
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i =0; i < creditsArray.length; i++) {
            ret.add(new Integer(creditsArray[i]));
        }
        return ret;
    }

    private Collection<String> extractStrList(String subjectsStr) {
        ArrayList<String> ret = new ArrayList<>();
        if (subjectsStr.equals("")) return ret;
        String creditsArray[] = subjectsStr.split(",");
        for (int i =0; i < creditsArray.length; i++) {
            ret.add(creditsArray[i]);
        }
        return ret;
    }

    @Override
    public String toString() {
        return "Curricula{" +
                "course='" + course + '\'' +
                ", code='" + code + '\'' +
                ", idealMandatoryCredits='" + idealMandatoryCredits + '\'' +
                ", idealOptionalCredits='" + idealOptionalCredits + '\'' +
                ", idealElectiveCredits='" + idealElectiveCredits + '\'' +
                ", idealComplementaryCredits='" + idealComplementaryCredits + '\'' +
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
                ", mandatorySubjects='" + mandatorySubjects + '\'' +
                ", optionalSubjects='" + optionalSubjects + '\'' +
                ", electiveSubjects='" + electiveSubjects + '\'' +
                ", complementarySubjects='" + complementarySubjects + '\'' +
                ", complementaryActivities='" + complementaryActivities + '\'' +
                '}';
    }
}
