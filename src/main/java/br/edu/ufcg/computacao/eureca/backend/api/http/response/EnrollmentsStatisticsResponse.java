package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class EnrollmentsStatisticsResponse {
    private Collection<EnrollmentsNewNameS> enrollmentsSummary;

    public EnrollmentsStatisticsResponse(Collection<EnrollmentsNewNameS> enrollmentsSummary) {
        this.enrollmentsSummary = enrollmentsSummary;
    }

    public Collection<EnrollmentsNewNameS> getEnrollmentsSummary() {
        return enrollmentsSummary;
    }

    public void setEnrollmentsSummary(Collection<EnrollmentsNewNameS> enrollmentsSummary) {
        this.enrollmentsSummary = enrollmentsSummary;
    }
}
