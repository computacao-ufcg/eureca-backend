package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class EnrollmentsStatisticsResponse extends Range {
    private String courseCode;
    private String curriculumCode;
    private Collection<EnrollmentsMetricsPerTermSummary> enrollmentsSummary;

    public EnrollmentsStatisticsResponse(Collection<EnrollmentsMetricsPerTermSummary> enrollmentsSummary, String courseCode, String curriculumCode, String from, String to) {
        super(from, to);
        this.enrollmentsSummary = enrollmentsSummary;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public Collection<EnrollmentsMetricsPerTermSummary> getEnrollmentsSummary() {
        return enrollmentsSummary;
    }

    public void setEnrollmentsSummary(Collection<EnrollmentsMetricsPerTermSummary> enrollmentsSummary) {
        this.enrollmentsSummary = enrollmentsSummary;
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
}
