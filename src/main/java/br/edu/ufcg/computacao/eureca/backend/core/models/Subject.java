package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.ArrayList;
import java.util.Collection;

public class Subject {
    private String courseCode;
    private String curriculumCode;
    private String subjectCode;
    private String academicUnit;
    private String type;
    private int credits;
    private int hours;
    private String name;
    private String equivalentCodes;
    private Collection<String> equivalentCodesList;
    private int idealTerm;
    private String preRequirements;
    private Collection<String> preRequirementsList;

    public Subject(String courseCode, String curriculumCode, String subjectCode, String academicUnit, String type,
                   int credits, int hours, String name, String equivalentCodes, int idealTerm, String preRequirements) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.academicUnit = academicUnit;
        this.type = type;
        this.credits = credits;
        this.hours = hours;
        this.name = name;
        this.equivalentCodes = equivalentCodes;
        this.idealTerm = idealTerm;
        this.preRequirements = preRequirements;
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

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getAcademicUnit() {
        return academicUnit;
    }

    public void setAcademicUnit(String academicUnit) {
        this.academicUnit = academicUnit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquivalentCodes() {
        return equivalentCodes;
    }

    public void setEquivalentCodes(String equivalentCodes) {
        this.equivalentCodes = equivalentCodes;
    }

    public int getIdealTerm() {
        return idealTerm;
    }

    public void setIdealTerm(int idealTerm) {
        this.idealTerm = idealTerm;
    }

    public String getPreRequirements() {
        return preRequirements;
    }

    public void setPreRequirements(String preRequirements) {
        this.preRequirements = preRequirements;
    }

    public Collection<String> getEquivalentCodesList() {
        if (equivalentCodesList == null) {
            equivalentCodesList = extractStrList(equivalentCodes);
        }
        return equivalentCodesList;
    }

    public Collection<String> getPreRequirementsList() {
        if (preRequirementsList == null) {
            preRequirementsList = extractStrList(preRequirements);
        }
        return preRequirementsList;
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
        return "Subject{" +
                "courseCode='" + courseCode + '\'' +
                "curriculumCode='" + curriculumCode + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", academicUnit='" + academicUnit + '\'' +
                ", type='" + type + '\'' +
                ", credits=" + credits +
                ", hours=" + hours +
                ", name='" + name + '\'' +
                ", equivalentCodes='" + equivalentCodes + '\'' +
                ", equivalentCodesList=" + equivalentCodesList +
                ", idealTerm='" + idealTerm + '\'' +
                ", preRequirements='" + preRequirements + '\'' +
                ", preRequirementsList=" + preRequirementsList +
                '}';
    }
}
