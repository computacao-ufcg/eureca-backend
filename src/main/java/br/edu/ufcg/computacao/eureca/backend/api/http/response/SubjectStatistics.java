package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricSummary;

public class SubjectStatistics {
    private String courseCode;
    private String curriculumCode;
    private String subjectCode;
    private int classesCount;
    private MetricSummary failedDueToAbsences;
    private MetricSummary failedDueToGrade;
    private MetricSummary failedDueToCanceling;
    private MetricSummary succeeded;
    private MetricSummary exempted;
    private MetricSummary retention;

    public SubjectStatistics(String courseCode, String curriculumCode, String subjectCode, int classesCount,
                             MetricSummary failedDueToAbsences, MetricSummary failedDueToGrade,
                             MetricSummary failedDueToCanceling, MetricSummary succeeded, MetricSummary exempted,
                             MetricSummary retention) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.classesCount = classesCount;
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.failedDueToCanceling = failedDueToCanceling;
        this.succeeded = succeeded;
        this.exempted = exempted;
        this.retention = retention;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getClassesCount() {
        return classesCount;
    }

    public void setClassesCount(int classesCount) {
        this.classesCount = classesCount;
    }

    public MetricSummary getFailedDueToAbsences() {
        return failedDueToAbsences;
    }

    public void setFailedDueToAbsences(MetricSummary failedDueToAbsences) {
        this.failedDueToAbsences = failedDueToAbsences;
    }

    public MetricSummary getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public void setFailedDueToGrade(MetricSummary failedDueToGrade) {
        this.failedDueToGrade = failedDueToGrade;
    }

    public MetricSummary getFailedDueToCanceling() {
        return failedDueToCanceling;
    }

    public void setFailedDueToCanceling(MetricSummary failedDueToCanceling) {
        this.failedDueToCanceling = failedDueToCanceling;
    }

    public MetricSummary getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(MetricSummary succeeded) {
        this.succeeded = succeeded;
    }

    public MetricSummary getExempted() {
        return exempted;
    }

    public void setExempted(MetricSummary exempted) {
        this.exempted = exempted;
    }

    public MetricSummary getRetention() {
        return retention;
    }

    public void setRetention(MetricSummary retention) {
        this.retention = retention;
    }
}
