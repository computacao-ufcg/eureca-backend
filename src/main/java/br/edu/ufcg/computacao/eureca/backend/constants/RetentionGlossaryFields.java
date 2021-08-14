package br.edu.ufcg.computacao.eureca.backend.constants;

public class RetentionGlossaryFields extends GlossaryFields {
    Field studentRetention;
    Field averageRisk;
    Field averageLoad;
    Field successRate;
    Field predictedGraduation;
    Field averageCost;
    Field averageTime;
    Field averageGpa;
    Field rejoinCount;
    Field canceledCount;
    Field dropoutCount;
    Field transferCount;

    public RetentionGlossaryFields(Field studentRetention, Field averageRisk,
                                   Field averageLoad, Field successRate, Field predictedGraduation, Field averageCost,
                                   Field averageTime,
                                   Field averageGpa, Field rejoinCount, Field canceledCount, Field dropoutCount,
                                   Field transferCount) {
        this.studentRetention = studentRetention;
        this.averageRisk = averageRisk;
        this.averageLoad = averageLoad;
        this.successRate = successRate;
        this.predictedGraduation = predictedGraduation;
        this.averageCost = averageCost;
        this.averageTime = averageTime;
        this.averageGpa = averageGpa;
        this.rejoinCount = rejoinCount;
        this.canceledCount = canceledCount;
        this.dropoutCount = dropoutCount;
        this.transferCount = transferCount;
    }

    public Field getStudentRetention() {
        return studentRetention;
    }

    public void setStudentRetention(Field studentRetention) {
        this.studentRetention = studentRetention;
    }

    public Field getAverageRisk() {
        return averageRisk;
    }

    public void setAverageRisk(Field averageRisk) {
        this.averageRisk = averageRisk;
    }

    public Field getAverageLoad() {
        return averageLoad;
    }

    public void setAverageLoad(Field averageLoad) {
        this.averageLoad = averageLoad;
    }

    public Field getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Field successRate) {
        this.successRate = successRate;
    }

    public Field getPredictedGraduation() {
        return predictedGraduation;
    }

    public void setPredictedGraduation(Field predictedGraduation) {
        this.predictedGraduation = predictedGraduation;
    }

    public Field getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(Field averageCost) {
        this.averageCost = averageCost;
    }

    public Field getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(Field averageTime) {
        this.averageTime = averageTime;
    }

    public Field getAverageGpa() {
        return averageGpa;
    }

    public void setAverageGpa(Field averageGpa) {
        this.averageGpa = averageGpa;
    }

    public Field getRejoinCount() {
        return rejoinCount;
    }

    public void setRejoinCount(Field rejoinCount) {
        this.rejoinCount = rejoinCount;
    }

    public Field getCanceledCount() {
        return canceledCount;
    }

    public void setCanceledCount(Field canceledCount) {
        this.canceledCount = canceledCount;
    }

    public Field getDropoutCount() {
        return dropoutCount;
    }

    public void setDropoutCount(Field dropoutCount) {
        this.dropoutCount = dropoutCount;
    }

    public Field getTransferCount() {
        return transferCount;
    }

    public void setTransferCount(Field transferCount) {
        this.transferCount = transferCount;
    }
}
