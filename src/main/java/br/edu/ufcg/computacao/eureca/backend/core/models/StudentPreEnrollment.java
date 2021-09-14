package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.HashSet;
import java.util.Set;

public class StudentPreEnrollment {

    private String studentRegistration;
    private Set<Subject> subjects;
    private int maxCredits;
    private int totalCredits;
    private int mandatoryCredits;
    private int optionalCredits;
    private int complementaryCredits;
    private int electiveCredits;

    public StudentPreEnrollment(String studentRegistration, Set<Subject> subjects, int maxCredits) {
        this.studentRegistration = studentRegistration;
        this.subjects = subjects;
        this.maxCredits = maxCredits;
        this.totalCredits = 0;
        this.mandatoryCredits = 0;
        this.optionalCredits = 0;
        this.complementaryCredits = 0;
        this.electiveCredits = 0;
    }

    public StudentPreEnrollment(String studentRegistration, int maxCredits) {
        this(studentRegistration, new HashSet<>(), maxCredits);
    }

    public boolean isFull() {
        return this.totalCredits >= this.maxCredits;
    }

    public String getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(String studentRegistration) {
        this.studentRegistration = studentRegistration;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(int maxCredits) {
        this.maxCredits = maxCredits;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getMandatoryCredits() {
        return mandatoryCredits;
    }

    public void setMandatoryCredits(int mandatoryCredits) {
        this.mandatoryCredits = mandatoryCredits;
    }

    public int getOptionalCredits() {
        return optionalCredits;
    }

    public void setOptionalCredits(int optionalCredits) {
        this.optionalCredits = optionalCredits;
    }

    public int getComplementaryCredits() {
        return complementaryCredits;
    }

    public void setComplementaryCredits(int complementaryCredits) {
        this.complementaryCredits = complementaryCredits;
    }

    public int getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(int electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public void addSubject(Subject subject) {
        if (!this.subjects.contains(subject) && this.totalCredits + subject.getCredits() <= this.maxCredits) {
            this.subjects.add(subject);

            switch (subject.getType()) {
                case "M":
                    this.mandatoryCredits += subject.getCredits();
                    break;
                case "O":
                    this.optionalCredits += subject.getCredits();
                    break;
                case "C":
                    this.complementaryCredits += subject.getCredits();
                    break;
                case "E":
                    this.electiveCredits += subject.getCredits();
            }

            this.totalCredits += subject.getCredits();
        }
    }
}
