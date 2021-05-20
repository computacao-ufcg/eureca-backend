package br.edu.ufcg.computacao.eureca.backend.constants;

public class TeachersGlossaryFields {
    Field failedDueToAbsences;
    Field failedDueToGrade;
    Field failedDueToCanceling;
    Field success;
    Field min;
    Field max;
    Field total;

    public TeachersGlossaryFields(Field failedDueToAbsences,
                                  Field failedDueToGrade,
                                  Field failedDueToCanceling,
                                  Field success, Field min,
                                  Field max,
                                  Field total) {
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.failedDueToCanceling = failedDueToCanceling;
        this.success = success;
        this.min = min;
        this.max = max;
        this.total = total;
    }

    public Field getFailedDueToAbsences() {
        return failedDueToAbsences;
    }

    public void setFailedDueToAbsences(Field failedDueToAbsences) {
        this.failedDueToAbsences = failedDueToAbsences;
    }

    public Field getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public void setFailedDueToGrade(Field failedDueToGrade) {
        this.failedDueToGrade = failedDueToGrade;
    }

    public Field getFailedDueToCanceling() {
        return failedDueToCanceling;
    }

    public void setFailedDueToCanceling(Field failedDueToCanceling) {
        this.failedDueToCanceling = failedDueToCanceling;
    }

    public Field getSuccess() {
        return success;
    }

    public void setSuccess(Field success) {
        this.success = success;
    }

    public Field getMin() {
        return min;
    }

    public void setMin(Field min) {
        this.min = min;
    }

    public Field getMax() {
        return max;
    }

    public void setMax(Field max) {
        this.max = max;
    }

    public Field getTotal() {
        return total;
    }

    public void setTotal(Field total) {
        this.total = total;
    }
}
