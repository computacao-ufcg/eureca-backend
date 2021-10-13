package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentPreEnrollmentResponse {

    private String studentRegistration;
    private Set<Subject> subjects;
    private int term;
    private int maxCredits;
    private int totalCredits;
    private int maxMandatoryCredits;
    private int mandatoryCredits;
    private int maxOptionalCredits;
    private int optionalCredits;
    private int maxComplementaryCredits;
    private int complementaryCredits;
    private int maxElectiveCredits;
    private int electiveCredits;

    public StudentPreEnrollmentResponse(String studentRegistration, int term, int maxMandatoryCredits, int maxOptionalCredits, int maxComplementaryCredits, int maxElectiveCredits) {
        this.studentRegistration = studentRegistration;
        this.subjects = new HashSet<>();
        this.term = term;
        this.maxMandatoryCredits = maxMandatoryCredits;
        this.maxOptionalCredits = maxOptionalCredits;
        this.maxComplementaryCredits = maxComplementaryCredits;
        this.maxElectiveCredits = maxElectiveCredits;
        this.maxCredits = this.maxMandatoryCredits + this.maxOptionalCredits + this.maxComplementaryCredits + this.maxElectiveCredits;
    }

    public boolean isFull() {
        return this.totalCredits >= this.maxCredits;
    }

    public boolean isOptionalFull() {
        return this.isFull() || this.optionalCredits >= this.maxOptionalCredits;
    }

    public boolean isComplementaryFull() {
        return this.isFull() || this.complementaryCredits >= this.maxComplementaryCredits;
    }

    public boolean isElectiveFull() {
        return this.isFull() || this.electiveCredits >= this.maxElectiveCredits;
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

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
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

    public int getMaxMandatoryCredits() {
        return maxMandatoryCredits;
    }

    public void setMaxMandatoryCredits(int maxMandatoryCredits) {
        this.maxMandatoryCredits = maxMandatoryCredits;
    }

    public int getMaxOptionalCredits() {
        return maxOptionalCredits;
    }

    public void setMaxOptionalCredits(int maxOptionalCredits) {
        this.maxOptionalCredits = maxOptionalCredits;
    }

    public int getMaxComplementaryCredits() {
        return maxComplementaryCredits;
    }

    public void setMaxComplementaryCredits(int maxComplementaryCredits) {
        this.maxComplementaryCredits = maxComplementaryCredits;
    }

    public int getMaxElectiveCredits() {
        return maxElectiveCredits;
    }

    public void setMaxElectiveCredits(int maxElectiveCredits) {
        this.maxElectiveCredits = maxElectiveCredits;
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
        boolean added = false;
        switch (subject.getType()) {
            case "M":
                added = this.addMandatorySubject(subject);
                break;
            case "O":
                added = this.addOptionalSubject(subject);
                break;
            case "C":
                added = this.addComplementarySubject(subject);
                break;
            case "E":
                added = this.addElectiveSubject(subject);
                break;
        }

        if (added) {
            this.totalCredits += subject.getCredits();
        }
    }

    private boolean addMandatorySubject(Subject subject) {
        boolean isPossibleToAdd = subject.getCredits() + this.mandatoryCredits <= this.maxMandatoryCredits;
        if (isPossibleToAdd) {
            isPossibleToAdd = this.subjects.add(subject);
            if (isPossibleToAdd) {
                this.mandatoryCredits += subject.getCredits();
            }
        }
        return isPossibleToAdd;
    }

    private boolean addOptionalSubject(Subject subject) {
        boolean isPossibleToAdd = subject.getCredits() + this.optionalCredits <= this.maxOptionalCredits;
        if (isPossibleToAdd) {
            isPossibleToAdd = this.subjects.add(subject);
            if (isPossibleToAdd) {
                this.optionalCredits += subject.getCredits();
            }
        }
        return isPossibleToAdd;
    }

    private boolean addComplementarySubject(Subject subject) {
        boolean isPossibleToAdd = subject.getCredits() + this.complementaryCredits <= this.maxComplementaryCredits;
        if (isPossibleToAdd) {
            isPossibleToAdd = this.subjects.add(subject);
            if (isPossibleToAdd) {
                this.complementaryCredits += subject.getCredits();
            }
        }
        return isPossibleToAdd;
    }

    private boolean addElectiveSubject(Subject subject) {
        boolean isPossibleToAdd = subject.getCredits() + this.electiveCredits <= this.maxElectiveCredits;
        if (isPossibleToAdd) {
            isPossibleToAdd = this.subjects.add(subject);
            if (isPossibleToAdd) {
                this.electiveCredits += subject.getCredits();
            }
        }
        return isPossibleToAdd;
    }
}
