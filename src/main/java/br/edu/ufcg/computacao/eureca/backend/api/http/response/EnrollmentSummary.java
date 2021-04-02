package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class EnrollmentSummary {
    private int studentCount;
    private int passed;
    private int failed;
    private int absent;
    private int dropout;
    private double successRate;
    private double failureRate;
    private double dropoutRate;

    public EnrollmentSummary() {
    }

    public EnrollmentSummary(int passed, int failed, int absent, int dropout) {
        this.passed = passed;
        this.failed = failed;
        this.absent = absent;
        this.dropout = dropout;
        this.studentCount = this.passed + this.failed + this.absent + this.dropout;
        this.successRate = (1.0*this.passed) / this.studentCount;
        this.failureRate = (1.0*(this.failed + this.absent)) / this.studentCount;
        this.dropoutRate = (1.0*this.dropout) / this.studentCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getDropout() {
        return dropout;
    }

    public void setDropout(int dropout) {
        this.dropout = dropout;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }

    public double getFailureRate() {
        return failureRate;
    }

    public void setFailureRate(double failureRate) {
        this.failureRate = failureRate;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public double getDropoutRate() {
        return dropoutRate;
    }

    public void setDropoutRate(double dropoutRate) {
        this.dropoutRate = dropoutRate;
    }
}
