package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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
    private Collection<String> coRequirementsList;
    private boolean isComposed;

    public Subject(String courseCode, String curriculumCode, String subjectCode, String academicUnit, String type,
                   int credits, int hours, String name, Collection<String> equivalentCodesList, int idealTerm,
                   Collection<String> preRequirementsList, Collection<String> coRequirementsList) {
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
        this.coRequirementsList = coRequirementsList;
        this.isComposed = coRequirementsList != null && !coRequirementsList.isEmpty();
    }

    public Subject(Subject other) {
        this.courseCode = other.courseCode;
        this.curriculumCode = other.curriculumCode;
        this.subjectCode = other.subjectCode;
        this.academicUnit = other.academicUnit;
        this.type = other.type;
        this.credits = other.credits;
        this.hours = other.hours;
        this.name = other.name;
        this.equivalentCodesList = new ArrayList<>(other.equivalentCodesList);
        this.idealTerm = other.idealTerm;
        this.preRequirementsList = new ArrayList(other.preRequirementsList);
        this.coRequirementsList = new ArrayList<>(other.coRequirementsList);
        this.isComposed = coRequirementsList != null && !coRequirementsList.isEmpty();
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

    public Collection<String> getCoRequirementsList() {
        return coRequirementsList;
    }

    public void setCoRequirementsList(Collection<String> coRequirementsList) {
        this.coRequirementsList = coRequirementsList;
    }

    public boolean isComposed() {
        return isComposed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return courseCode.equals(subject.courseCode) && curriculumCode.equals(subject.curriculumCode) && subjectCode.equals(subject.subjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, curriculumCode, subjectCode);
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
                ", coRequirementsList=" + coRequirementsList +
                '}';
    }
}
