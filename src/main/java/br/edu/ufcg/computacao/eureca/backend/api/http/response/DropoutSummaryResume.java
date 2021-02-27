package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutSummaryResume extends DropoutSummary {

    private DropoutClassification reasons;

    public DropoutSummaryResume(double grossDropoutAlumnusRate, double grossDropoutEnrolledRate, double netDropoutAlumnusRate, double netDropoutEnrolledRate, int grossDropoutCount, int netDropoutCount, DropoutClassification reasons) {
        super(grossDropoutAlumnusRate, grossDropoutEnrolledRate, netDropoutAlumnusRate, netDropoutEnrolledRate, grossDropoutCount, netDropoutCount);
        this.reasons = reasons;
    }

    public DropoutSummaryResume(DropoutSummary dropoutSummary, DropoutClassification reasons) {
        this(dropoutSummary.getGrossDropoutAlumnusRate(), dropoutSummary.getGrossDropoutEnrolledRate(),
                dropoutSummary.getNetDropoutAlumnusRate(), dropoutSummary.getNetDropoutEnrolledRate(),
                dropoutSummary.getGrossDropoutCount(), dropoutSummary.getNetDropoutCount(),
                reasons);
    }

    public DropoutClassification getReasons() {
        return reasons;
    }

    public void setReasons(DropoutClassification reasons) {
        this.reasons = reasons;
    }
}
