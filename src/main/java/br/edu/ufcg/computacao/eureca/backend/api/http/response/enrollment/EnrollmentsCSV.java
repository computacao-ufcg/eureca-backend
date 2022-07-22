package br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.refactor.CourseData;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.refator.CounterData;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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

    public EnrollmentsCSV(CourseData courseData, CounterData counterData) {
        this.courseCode = courseData.getCourseCode();
        this.curriculumCode = courseData.getCurriculumCode();
        this.subjectCode = courseData.getSubjectCode();
        this.subjectName = courseData.getSubjectName();
        this.term = courseData.getTerm();
        this.classId = courseData.getClassId();
        this.enrollmentsCount = counterData.getEnrollmentsCount();
        this.succeededCount = counterData.getSucceededCount();
        this.cancelledCount = counterData.getCancelledCount();
        this.exemptedCount = counterData.getExemptedCount();
        this.ongoingCount = counterData.getOngoingCount();
        this.failedDueToGradeCount = counterData.getFailedDueToGradeCount();
        this.failedDueToAbsenceCount = counterData.getFailedDueToAbsenceCount();
        this.suspendedCount = counterData.getSuspendedCount();
    }

    @Override
    public int compareTo(Object o) {
        String thisValue = this.getCourseCode() + this.getCurriculumCode() + this.getSubjectCode() + this.getTerm() + this.getClassId();
        EnrollmentsCSV other = (EnrollmentsCSV) o;
        String otherValue = other.getCourseCode() + other.getCurriculumCode() + other.getSubjectCode() + other.getTerm() + other.getClassId();
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
