package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class TeacherStatisticsSummary {
    private int subjectsCount;
    private int classesCount;
    private int totalEnrolled;
    private double averageEnrollmentsPerClass;
    private double failedDueToAbsencesRate;
    private double failedDueToGradeRate;
    private double cancelledRate;
    private double succeededRate;
    private double ongoingRate;
    private double exemptedRate;
    private double suspendedRate;

    public TeacherStatisticsSummary(int subjectsCount, int classesCount, int totalEnrolled,
                                    double averageEnrollmentsPerClass, double failedDueToAbsencesRate,
                                    double failedDueToGradeRate, double cancelledRate, double succeededRate,
                                    double ongoingRate, double exemptedRate, double suspendedRate) {
        this.subjectsCount = subjectsCount;
        this.classesCount = classesCount;
        this.totalEnrolled = totalEnrolled;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
        this.failedDueToAbsencesRate = failedDueToAbsencesRate;
        this.failedDueToGradeRate = failedDueToGradeRate;
        this.cancelledRate = cancelledRate;
        this.succeededRate = succeededRate;
        this.ongoingRate = ongoingRate;
        this.exemptedRate = exemptedRate;
        this.suspendedRate = suspendedRate;
    }

    public int getSubjectsCount() {
        return subjectsCount;
    }

    public void setSubjectsCount(int subjectsCount) {
        this.subjectsCount = subjectsCount;
    }

    public int getClassesCount() {
        return classesCount;
    }

    public void setClassesCount(int classesCount) {
        this.classesCount = classesCount;
    }

    public int getTotalEnrolled() {
        return totalEnrolled;
    }

    public void setTotalEnrolled(int totalEnrolled) {
        this.totalEnrolled = totalEnrolled;
    }

    public double getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public void setAverageEnrollmentsPerClass(double averageEnrollmentsPerClass) {
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }

    public double getFailedDueToAbsencesRate() {
        return failedDueToAbsencesRate;
    }

    public void setFailedDueToAbsencesRate(double failedDueToAbsencesRate) {
        this.failedDueToAbsencesRate = failedDueToAbsencesRate;
    }

    public double getFailedDueToGradeRate() {
        return failedDueToGradeRate;
    }

    public void setFailedDueToGradeRate(double failedDueToGradeRate) {
        this.failedDueToGradeRate = failedDueToGradeRate;
    }

    public double getCancelledRate() {
        return cancelledRate;
    }

    public void setCancelledRate(double cancelledRate) {
        this.cancelledRate = cancelledRate;
    }

    public double getSucceededRate() {
        return succeededRate;
    }

    public void setSucceededRate(double succeededRate) {
        this.succeededRate = succeededRate;
    }

    public double getOngoingRate() {
        return ongoingRate;
    }

    public void setOngoingRate(double ongoingRate) {
        this.ongoingRate = ongoingRate;
    }

    public double getExemptedRate() {
        return exemptedRate;
    }

    public void setExemptedRate(double exemptedRate) {
        this.exemptedRate = exemptedRate;
    }

    public double getSuspendedRate() {
        return suspendedRate;
    }

    public void setSuspendedRate(double suspendedRate) {
        this.suspendedRate = suspendedRate;
    }
}
