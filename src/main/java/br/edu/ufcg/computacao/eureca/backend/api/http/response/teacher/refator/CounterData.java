package br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.refator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounterData {

    private int enrollmentsCount;
    private int succeededCount;
    private int cancelledCount;
    private int exemptedCount;
    private int ongoingCount;
    private int failedDueToGradeCount;
    private int failedDueToAbsenceCount;
    private int suspendedCount;

    public CounterData(int enrollmentsCount, int succeededCount, int cancelledCount, int exemptedCount, int ongoingCount, int failedDueToGradeCount, int failedDueToAbsenceCount, int suspendedCount) {
        this.enrollmentsCount = enrollmentsCount;
        this.succeededCount = succeededCount;
        this.cancelledCount = cancelledCount;
        this.exemptedCount = exemptedCount;
        this.ongoingCount = ongoingCount;
        this.failedDueToGradeCount = failedDueToGradeCount;
        this.failedDueToAbsenceCount = failedDueToAbsenceCount;
        this.suspendedCount = suspendedCount;
    }
}
