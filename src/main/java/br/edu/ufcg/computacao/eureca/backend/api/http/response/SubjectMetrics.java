package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectMetrics {
    private int failedDueToAbsences;
    private int failedDueToGrade;
    private int cancelled;
    private int succeeded;
    private int ongoing;
    private int exempted;
    private int suspended;
    private int numberOfClasses;
    private int totalEnrolled;

    public SubjectMetrics(int failedDueToAbsences, int failedDueToGrade, int cancelled, int succeeded, int ongoing,
                          int exempted, int suspended, int numberOfClasses, int totalEnrolled) {
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.cancelled = cancelled;
        this.succeeded = succeeded;
        this.ongoing = ongoing;
        this.exempted = exempted;
        this.suspended = suspended;
        this.numberOfClasses = numberOfClasses;
        this.totalEnrolled = totalEnrolled;
    }

    public SubjectMetrics() {
    }

    public int getFailedDueToAbsences() {
        return failedDueToAbsences;
    }

    public void setFailedDueToAbsences(int failedDueToAbsences) {
        this.failedDueToAbsences = failedDueToAbsences;
    }

    public int getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public void setFailedDueToGrade(int failedDueToGrade) {
        this.failedDueToGrade = failedDueToGrade;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public int getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(int succeeded) {
        this.succeeded = succeeded;
    }

    public int getOngoing() {
        return ongoing;
    }

    public void setOngoing(int ongoing) {
        this.ongoing = ongoing;
    }

    public int getExempted() {
        return exempted;
    }

    public void setExempted(int exempted) {
        this.exempted = exempted;
    }

    public int getSuspended() {
        return suspended;
    }

    public void setSuspended(int suspended) {
        this.suspended = suspended;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public int getTotalEnrolled() {
        return totalEnrolled;
    }

    public void setTotalEnrolled(int totalEnrolled) {
        this.totalEnrolled = totalEnrolled;
    }

    public void add(SubjectMetrics other) {
        this.failedDueToAbsences += other.getFailedDueToAbsences();
        this.failedDueToGrade += other.getFailedDueToGrade();
        this.cancelled += other.getCancelled();
        this.succeeded += other.getSucceeded();
        this.ongoing += other.getOngoing();
        this.exempted += other.getExempted();
        this.suspended += other.getSuspended();
        this.numberOfClasses += other.getNumberOfClasses();
        this.totalEnrolled += other.getTotalEnrolled();
    }

    @Override
    public String toString() {
        return "SubjectMetrics{" +
                "failedDueToAbsences=" + failedDueToAbsences +
                ", failedDueToGrade=" + failedDueToGrade +
                ", cancelled=" + cancelled +
                ", succeeded=" + succeeded +
                ", ongoing=" + ongoing +
                ", exempted=" + exempted +
                ", suspended=" + suspended +
                ", numberOfClasses=" + numberOfClasses +
                ", totalEnrolled=" + totalEnrolled +
                '}';
    }
}
