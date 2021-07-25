package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectMetrics {
    private int failedDueToAbsences;
    private int failedDueToGrade;
    private int cancelled;
    private int succeeded;
    private int ongoing;
    private int exempted;
    private int totalEnrolled;

    public SubjectMetrics(int failedDueToAbsences, int failedDueToGrade, int cancelled,
                          int succeeded, int ongoing, int exempted, int totalEnrolled) {
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.cancelled = cancelled;
        this.succeeded = succeeded;
        this.ongoing = ongoing;
        this.exempted = exempted;
        this.totalEnrolled = totalEnrolled;
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

    public int getTotalEnrolled() {
        return totalEnrolled;
    }

    public void setTotalEnrolled(int totalEnrolled) {
        this.totalEnrolled = totalEnrolled;
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
                ", totalEnrolled=" + totalEnrolled +
                '}';
    }
}
