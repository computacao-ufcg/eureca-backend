package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;

public class DropoutClassification {
    int failed3Times;
    int reenterSameCourse;
    int reenterOtherCourse;
    int failedAll;
    int cancelled;
    int cancelledByDecree;
    int cancelledCourseChange;
    int cancelledUponRequest;
    int leftWithoutNotice;
    int missedGraduation;
    int transferred;

    public DropoutClassification(int[] dropouts) {
        this.failed3Times = dropouts[SystemConstants.FAILED_3_TIMES_INDEX];
        this.reenterSameCourse = dropouts[SystemConstants.REENTER_SAME_COURSE_INDEX];
        this.reenterOtherCourse = dropouts[SystemConstants.REENTER_OTHER_COURSE_INDEX];
        this.failedAll = dropouts[SystemConstants.FAILED_ALL_INDEX];
        this.cancelled = dropouts[SystemConstants.CANCELLED_INDEX];
        this.cancelledByDecree = dropouts[SystemConstants.CANCELLED_BY_DECREE_INDEX];
        this.cancelledCourseChange = dropouts[SystemConstants.CANCELLED_COURSE_CHANGE_INDEX];
        this.cancelledUponRequest = dropouts[SystemConstants.CANCELLED_UPON_REQUEST_INDEX];
        this.leftWithoutNotice = dropouts[SystemConstants.LEFT_WITHOUT_NOTICE_INDEX];
        this.missedGraduation = dropouts[SystemConstants.MISSED_GRADUATION_INDEX];
        this.transferred = dropouts[SystemConstants.TRANSFERRED_INDEX];
    }

    public int getFailed3Times() {
        return failed3Times;
    }

    public void setFailed3Times(int failed3Times) {
        this.failed3Times = failed3Times;
    }

    public int getTransferred() {
        return transferred;
    }

    public void setTransferred(int transferred) {
        this.transferred = transferred;
    }

    public int getReenterSameCourse() {
        return reenterSameCourse;
    }

    public void setReenterSameCourse(int reenterSameCourse) {
        this.reenterSameCourse = reenterSameCourse;
    }

    public int getReenterOtherCourse() {
        return reenterOtherCourse;
    }

    public void setReenterOtherCourse(int reenterOtherCourse) {
        this.reenterOtherCourse = reenterOtherCourse;
    }

    public int getFailedAll() {
        return failedAll;
    }

    public void setFailedAll(int failedAll) {
        this.failedAll = failedAll;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public int getCancelledByDecree() {
        return cancelledByDecree;
    }

    public void setCancelledByDecree(int cancelledByDecree) {
        this.cancelledByDecree = cancelledByDecree;
    }

    public int getCancelledCourseChange() {
        return cancelledCourseChange;
    }

    public void setCancelledCourseChange(int cancelledCourseChange) {
        this.cancelledCourseChange = cancelledCourseChange;
    }

    public int getCancelledUponRequest() {
        return cancelledUponRequest;
    }

    public void setCancelledUponRequest(int cancelledUponRequest) {
        this.cancelledUponRequest = cancelledUponRequest;
    }

    public int getLeftWithoutNotice() {
        return leftWithoutNotice;
    }

    public void setLeftWithoutNotice(int leftWithoutNotice) {
        this.leftWithoutNotice = leftWithoutNotice;
    }

    public int getMissedGraduation() {
        return missedGraduation;
    }

    public void setMissedGraduation(int missedGraduation) {
        this.missedGraduation = missedGraduation;
    }

    public int getTotalDropouts() {
        return this.cancelled + this.cancelledByDecree + this.cancelledCourseChange + this.cancelledUponRequest +
                this.failed3Times + this.failedAll + this.leftWithoutNotice + this.missedGraduation +
                this.reenterOtherCourse + this.reenterSameCourse + this.transferred;
    }
}