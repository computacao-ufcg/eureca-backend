package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class DropoutSummaryResume extends DropoutSummary {

    private DropoutClassification reasons;

    public DropoutSummaryResume(double grossDropoutAlumnusRate, double grossDropoutEnrolledRate, double netDropoutAlumnusRate, double netDropoutEnrolledRate, int grossDropoutCount, int netDropoutCount, DropoutClassification reasons) {
        super(grossDropoutAlumnusRate, grossDropoutEnrolledRate, netDropoutAlumnusRate, netDropoutEnrolledRate, grossDropoutCount, netDropoutCount);
        this.reasons = reasons;
    }

    public DropoutSummaryResume(Collection<DropoutPerTermSummary> dropouts, int activeCount, int alumniCount) {
        super(dropouts, activeCount, alumniCount);

        int failed3Times = 0;
        int reenterSameCourse = 0;
        int reenterOtherCourse = 0;
        int failedAll = 0;
        int cancelled = 0;
        int cancelledByDecree = 0;
        int cancelledCourseChange = 0;
        int cancelledUponRequest = 0;
        int leftWithoutNotice = 0;
        int missedGraduation = 0;
        int transferred = 0;

        for (DropoutPerTermSummary dropout : dropouts) {
            failed3Times += dropout.getReasons().getFailed3Times();
            reenterSameCourse += dropout.getReasons().getReenterSameCourse();
            reenterOtherCourse += dropout.getReasons().getReenterOtherCourse();
            failedAll += dropout.getReasons().getFailedAll();
            cancelled += dropout.getReasons().getCancelled();
            cancelledByDecree += dropout.getReasons().getCancelledByDecree();
            cancelledCourseChange += dropout.getReasons().getCancelledCourseChange();
            cancelledUponRequest += dropout.getReasons().getCancelledUponRequest();
            leftWithoutNotice += dropout.getReasons().getLeftWithoutNotice();
            missedGraduation += dropout.getReasons().getMissedGraduation();
            transferred += dropout.getReasons().getTransferred();
        }

        int[] reasons = new int[] {
                failed3Times,
                reenterSameCourse,
                reenterOtherCourse,
                failedAll,
                cancelled,
                cancelledByDecree,
                cancelledCourseChange,
                cancelledUponRequest,
                leftWithoutNotice,
                missedGraduation,
                transferred
        };
        this.reasons = new DropoutClassification(reasons);
    }

    public DropoutClassification getReasons() {
        return reasons;
    }

    public void setReasons(DropoutClassification reasons) {
        this.reasons = reasons;
    }
}
