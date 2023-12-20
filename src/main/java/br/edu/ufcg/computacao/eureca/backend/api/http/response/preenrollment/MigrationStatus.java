package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

public class MigrationStatus implements Comparable {
    private String registration;
    private int termsLeft;
    private int completedCredits;
    private double speed;
    private int projectedCompleted;
    private String status;

    public MigrationStatus(String registration, int termsLeft, int completedCredits, double speed,
                           int projectedCompleted, String status) {
        this.registration = registration;
        this.termsLeft = termsLeft;
        this.completedCredits = completedCredits;
        this.speed = speed;
        this.projectedCompleted = projectedCompleted;
        this.status = status;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getTermsLeft() {
        return termsLeft;
    }

    public void setTermsLeft(int termsLeft) {
        this.termsLeft = termsLeft;
    }

    public int getCompletedCredits() {
        return completedCredits;
    }

    public void setCompletedCredits(int completedCredits) {
        this.completedCredits = completedCredits;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getProjectedCompleted() {
        return projectedCompleted;
    }

    public void setProjectedCompleted(int projectedCompleted) {
        this.projectedCompleted = projectedCompleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Object o) {
        MigrationStatus that = (MigrationStatus) o;
        return this.registration.compareTo(that.registration);
    }
    @Override
    public String toString() {
        return "MigrationStatus{" +
                "registration='" + registration + '\'' +
                ", termsLeft=" + termsLeft +
                ", completedCredits=" + completedCredits +
                ", speed=" + speed +
                ", projectedMaxCompleted=" + projectedCompleted +
                ", status='" + status + '\'' +
                '}';
    }
}
