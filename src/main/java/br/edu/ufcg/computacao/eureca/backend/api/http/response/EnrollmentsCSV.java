package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class EnrollmentsCSV implements Comparable {
    private String courseCode;
    private String curriculumCode;
    private String subjectCode;
    private String subjectName;
    private String term;
    private String classId;
    private int enrollmentsCount;
    private int succeededCount;
    private int cancelledCount;
    private int exemptedCount;
    private int ongoingCount;
    private int failedDueToGradeCount;
    private int failedDueToAbsenceCount;
    private int suspendedCount;

    public EnrollmentsCSV(String courseCode, String curriculumCode, String subjectCode, String subjectName, String term,
                          String classId, int enrollmentsCount, int succeededCount, int cancelledCount,
                          int exemptedCount, int ongoingCount, int failedDueToGradeCount, int failedDueToAbsenceCount,
                          int suspendedCount) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.term = term;
        this.classId = classId;
        this.enrollmentsCount = enrollmentsCount;
        this.succeededCount = succeededCount;
        this.cancelledCount = cancelledCount;
        this.exemptedCount = exemptedCount;
        this.ongoingCount = ongoingCount;
        this.failedDueToGradeCount = failedDueToGradeCount;
        this.failedDueToAbsenceCount = failedDueToAbsenceCount;
        this.suspendedCount = suspendedCount;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getEnrollmentsCount() {
        return enrollmentsCount;
    }

    public void setEnrollmentsCount(int enrollmentsCount) {
        this.enrollmentsCount = enrollmentsCount;
    }

    public int getSucceededCount() {
        return succeededCount;
    }

    public void setSucceededCount(int succeededCount) {
        this.succeededCount = succeededCount;
    }

    public int getCancelledCount() {
        return cancelledCount;
    }

    public void setCancelledCount(int cancelledCount) {
        this.cancelledCount = cancelledCount;
    }

    public int getExemptedCount() {
        return exemptedCount;
    }

    public void setExemptedCount(int exemptedCount) {
        this.exemptedCount = exemptedCount;
    }

    public int getOngoingCount() {
        return ongoingCount;
    }

    public void setOngoingCount(int ongoingCount) {
        this.ongoingCount = ongoingCount;
    }

    public int getFailedDueToGradeCount() {
        return failedDueToGradeCount;
    }

    public void setFailedDueToGradeCount(int failedDueToGradeCount) {
        this.failedDueToGradeCount = failedDueToGradeCount;
    }

    public int getFailedDueToAbsenceCount() {
        return failedDueToAbsenceCount;
    }

    public void setFailedDueToAbsenceCount(int failedDueToAbsenceCount) {
        this.failedDueToAbsenceCount = failedDueToAbsenceCount;
    }

    public int getSuspendedCount() {
        return suspendedCount;
    }

    public void setSuspendedCount(int suspendedCount) {
        this.suspendedCount = suspendedCount;
    }

    @Override
    public int compareTo(Object o) {
        String thisValue = this.getCourseCode() + this.getCurriculumCode() + this.getSubjectCode();
        EnrollmentsCSV other = (EnrollmentsCSV) o;
        String otherValue = other.getCourseCode() + other.getCurriculumCode() + other.getSubjectCode();
        return thisValue.compareTo(otherValue);
    }

    @Override
    public String toString() {
        return "EnrollmentsCSV{" +
                "courseCode='" + courseCode + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", term='" + term + '\'' +
                ", classId='" + classId + '\'' +
                ", enrollmentsCount=" + enrollmentsCount +
                ", succeededCount=" + succeededCount +
                ", cancelledCount=" + cancelledCount +
                ", exemptedCount=" + exemptedCount +
                ", ongoingCount=" + ongoingCount +
                ", failedDueToGradeCount=" + failedDueToGradeCount +
                ", failedDueToAbsenceCount=" + failedDueToAbsenceCount +
                ", suspendedCount=" + suspendedCount +
                '}';
    }
}
