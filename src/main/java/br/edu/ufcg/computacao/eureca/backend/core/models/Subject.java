package br.edu.ufcg.computacao.eureca.backend.core.models;

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
    private Collection<String> equivalentCodesList;
    private int idealTerm;
    private Collection<String> preRequirementsList;

    public Subject(String courseCode, String curriculumCode, String subjectCode, String academicUnit, String type,
                   int credits, int hours, String name, Collection<String> equivalentCodesList, int idealTerm,
                   Collection<String> preRequirementsList) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.academicUnit = academicUnit;
        this.type = type;
        this.credits = credits;
        this.hours = hours;
        this.name = name;
        this.equivalentCodesList = equivalentCodesList;
        this.idealTerm = idealTerm;
        this.preRequirementsList = preRequirementsList;
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

    public int getIdealTerm() {
        return idealTerm;
    }

    public void setIdealTerm(int idealTerm) {
        this.idealTerm = idealTerm;
    }

    public Collection<String> getEquivalentCodesList() {
        return equivalentCodesList;
    }

    public Collection<String> getPreRequirementsList() {
        return preRequirementsList;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "courseCode='" + courseCode + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", academicUnit='" + academicUnit + '\'' +
                ", type='" + type + '\'' +
                ", credits=" + credits +
                ", hours=" + hours +
                ", name='" + name + '\'' +
                ", equivalentCodesList=" + equivalentCodesList +
                ", idealTerm=" + idealTerm +
                ", preRequirementsList=" + preRequirementsList +
                '}';
    }
}
