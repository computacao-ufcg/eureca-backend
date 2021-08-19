package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models;

import br.edu.ufcg.computacao.eureca.backend.core.models.ClassEnrollments;

import java.util.Set;

public class TeachersSetAndEnrollments {
    private Set<String> teachers;
    private int succeededCount;
    private int failedDueToGradeCount;
    private int failedDueToAbsenceCount;
    private int suspendedCount;

    public TeachersSetAndEnrollments(Set<String> teachers, int succeededCount, int failedDueToGradeCount,
                                     int failedDueToAbsenceCount, int suspendedCount) {
        this.teachers = teachers;
        this.succeededCount = succeededCount;
        this.failedDueToGradeCount = failedDueToGradeCount;
        this.failedDueToAbsenceCount = failedDueToAbsenceCount;
        this.suspendedCount = suspendedCount;
    }

    public Set<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<String> teachers) {
        this.teachers = teachers;
    }

    public int getSucceededCount() {
        return succeededCount;
    }

    public void setSucceededCount(int succeededCount) {
        this.succeededCount = succeededCount;
    }

    public int getFailedDueToGradeCount() {
        return failedDueToGradeCount;
    }

    public void setFailedDueToGradeCount(int failedDueToGradeCount) {
        this.failedDueToGradeCount = failedDueToGradeCount;
    }

    public int getFailedDueToAbsenceCount() {
        return failedDueToAbsenceCount;
    }

    public void setFailedDueToAbsenceCount(int failedDueToAbsenceCount) {
        this.failedDueToAbsenceCount = failedDueToAbsenceCount;
    }

    public int getSuspendedCount() {
        return suspendedCount;
    }

    public void setSuspendedCount(int suspendedCount) {
        this.suspendedCount = suspendedCount;
    }

    public void addEnrollments(ClassEnrollments enrollments) {
        this.succeededCount += enrollments.getNumberSucceeded();
        this.failedDueToGradeCount += enrollments.getNumberFailedDueToGrade();
        this.failedDueToAbsenceCount += enrollments.getNumberFailedDueToAbsence();
        this.suspendedCount += enrollments.getNumberSuspended();
    }
}
