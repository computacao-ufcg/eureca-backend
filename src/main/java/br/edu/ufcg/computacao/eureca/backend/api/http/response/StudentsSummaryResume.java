package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class StudentsSummaryResume {

    private ActiveSummaryResume activesSummary;
    private AlumniSummary alumniSummary;
    private DelayedSummary delayedSummary;
    private DropoutSummaryResume dropoutsSummary;

    public StudentsSummaryResume(ActiveSummaryResume activesSummary, AlumniSummary alumniSummary, DelayedSummary delayedSummary, DropoutSummaryResume dropoutsSummary) {
        this.activesSummary = activesSummary;
        this.alumniSummary = alumniSummary;
        this.delayedSummary = delayedSummary;
        this.dropoutsSummary = dropoutsSummary;
    }

    public ActiveSummaryResume getActivesSummary() {
        return activesSummary;
    }

    public void setActivesSummary(ActiveSummaryResume activesSummary) {
        this.activesSummary = activesSummary;
    }

    public AlumniSummary getAlumniSummary() {
        return alumniSummary;
    }

    public void setAlumniSummary(AlumniSummary alumniSummary) {
        this.alumniSummary = alumniSummary;
    }

    public DelayedSummary getDelayedSummary() {
        return delayedSummary;
    }

    public void setDelayedSummary(DelayedSummary delayedSummary) {
        this.delayedSummary = delayedSummary;
    }

    public DropoutSummaryResume getDropoutsSummary() {
        return dropoutsSummary;
    }

    public void setDropoutsSummary(DropoutSummaryResume dropoutsSummary) {
        this.dropoutsSummary = dropoutsSummary;
    }
}
