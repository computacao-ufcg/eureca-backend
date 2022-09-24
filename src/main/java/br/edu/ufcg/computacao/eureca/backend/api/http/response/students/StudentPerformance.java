package br.edu.ufcg.computacao.eureca.backend.api.http.response.students;

public class StudentPerformance implements Comparable {
    private String registration;
    private String term;
    private double gpa;
    private int accumulatedTerms;
    private int accumulatedCredits;
    private int waivedCredits;
    private int attemptedCredits;
    private int successfulCredits;
    private int failedCredits;
    private int suspendedCredits;
    private double averageSpeed;

    public StudentPerformance(String registration, String term, double gpa, int accumulatedTerms, int accumulatedCredits,
                              int waivedCredits, int attemptedCredits, int successfulCredits, int failedCredits,
                              int suspendedCredits, double averageSpeed) {
        this.registration = registration;
        this.term = term;
        this.gpa = gpa;
        this.accumulatedTerms = accumulatedTerms;
        this.accumulatedCredits = accumulatedCredits;
        this.waivedCredits = waivedCredits;
        this.attemptedCredits = attemptedCredits;
        this.successfulCredits = successfulCredits;
        this.failedCredits = failedCredits;
        this.suspendedCredits = suspendedCredits;
        this.averageSpeed = averageSpeed;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getAccumulatedTerms() {
        return accumulatedTerms;
    }

    public void setAccumulatedTerms(int accumulatedTerms) {
        this.accumulatedTerms = accumulatedTerms;
    }

    public int getAccumulatedCredits() {
        return accumulatedCredits;
    }

    public void setAccumulatedCredits(int accumulatedCredits) {
        this.accumulatedCredits = accumulatedCredits;
    }

    public int getWaivedCredits() {
        return waivedCredits;
    }

    public void setWaivedCredits(int waivedCredits) {
        this.waivedCredits = waivedCredits;
    }

    public int getAttemptedCredits() {
        return attemptedCredits;
    }

    public void setAttemptedCredits(int attemptedCredits) {
        this.attemptedCredits = attemptedCredits;
    }

    public int getSuccessfulCredits() {
        return successfulCredits;
    }

    public void setSuccessfulCredits(int successfulCredits) {
        this.successfulCredits = successfulCredits;
    }

    public int getFailedCredits() {
        return failedCredits;
    }

    public void setFailedCredits(int failedCredits) {
        this.failedCredits = failedCredits;
    }

    public int getSuspendedCredits() {
        return suspendedCredits;
    }

    public void setSuspendedCredits(int suspendedCredits) {
        this.suspendedCredits = suspendedCredits;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    @Override
    public int compareTo(Object o) {
        StudentPerformance that = (StudentPerformance) o;
        return (this.getRegistration()+this.getTerm()).compareTo((that.getRegistration()+that.getTerm()));
    }
}
