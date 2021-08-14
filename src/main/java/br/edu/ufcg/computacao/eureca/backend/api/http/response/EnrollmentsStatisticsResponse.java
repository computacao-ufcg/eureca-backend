package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class EnrollmentsStatisticsResponse extends Range {
    private Collection<EnrollmentsMetricsPerTermSummary> enrollmentsSummary;

    public EnrollmentsStatisticsResponse(Collection<EnrollmentsMetricsPerTermSummary> enrollmentsSummary, String from, String to) {
        super(from, to);
        this.enrollmentsSummary = enrollmentsSummary;
    }

    public Collection<EnrollmentsMetricsPerTermSummary> getEnrollmentsSummary() {
        return enrollmentsSummary;
    }

    public void setEnrollmentsSummary(Collection<EnrollmentsMetricsPerTermSummary> enrollmentsSummary) {
        this.enrollmentsSummary = enrollmentsSummary;
    }
}
