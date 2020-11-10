package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutClassification {
    int failed_3_times;
    int transferred;
    int reenter_same_course;
    int reenter_other_course;
    int failed_all;
    int cancelled;
    int cancelled_by_decree;
    int cancelled_change_course;
    int cancelled_upon_request;
    int left_without_notice;

    public DropoutClassification(int failed_3_times, int transferred, int reenter_same_course, int reenter_other_course,
                                 int failed_all, int cancelled, int cancelled_by_decree,
                                 int cancelled_change_course, int cancelled_upon_request, int left_without_notice) {
        this.failed_3_times = failed_3_times;
        this.transferred = transferred;
        this.reenter_same_course = reenter_same_course;
        this.reenter_other_course = reenter_other_course;
        this.failed_all = failed_all;
        this.cancelled = cancelled;
        this.cancelled_by_decree = cancelled_by_decree;
        this.cancelled_change_course = cancelled_change_course;
        this.cancelled_upon_request = cancelled_upon_request;
        this.left_without_notice = left_without_notice;
    }

    public int getFailed_3_times() {
        return failed_3_times;
    }

    public void setFailed_3_times(int failed_3_times) {
        this.failed_3_times = failed_3_times;
    }

    public int getTransferred() {
        return transferred;
    }

    public void setTransferred(int transferred) {
        this.transferred = transferred;
    }

    public int getReenter_same_course() {
        return reenter_same_course;
    }

    public void setReenter_same_course(int reenter_same_course) {
        this.reenter_same_course = reenter_same_course;
    }

    public int getReenter_other_course() {
        return reenter_other_course;
    }

    public void setReenter_other_course(int reenter_other_course) {
        this.reenter_other_course = reenter_other_course;
    }

    public int getFailed_all() {
        return failed_all;
    }

    public void setFailed_all(int failed_all) {
        this.failed_all = failed_all;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public int getCancelled_by_decree() {
        return cancelled_by_decree;
    }

    public void setCancelled_by_decree(int cancelled_by_decree) {
        this.cancelled_by_decree = cancelled_by_decree;
    }

    public int getCancelled_change_course() {
        return cancelled_change_course;
    }

    public void setCancelled_change_course(int cancelled_change_course) {
        this.cancelled_change_course = cancelled_change_course;
    }

    public int getCancelled_upon_request() {
        return cancelled_upon_request;
    }

    public void setCancelled_upon_request(int cancelled_upon_request) {
        this.cancelled_upon_request = cancelled_upon_request;
    }

    public int getLeft_without_notice() {
        return left_without_notice;
    }

    public void setLeft_without_notice(int left_without_notice) {
        this.left_without_notice = left_without_notice;
    }
}