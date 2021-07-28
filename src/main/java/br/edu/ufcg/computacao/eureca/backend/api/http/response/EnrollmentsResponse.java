package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class EnrollmentsResponse {
    private Collection<EnrollmentsCSV> enrollmentsSummary;

    public EnrollmentsResponse(Collection<EnrollmentsCSV> enrollmentsSummary) {
        this.enrollmentsSummary = enrollmentsSummary;
    }

    public Collection<EnrollmentsCSV> getEnrollmentsSummary() {
        return enrollmentsSummary;
    }

    public void setEnrollmentsSummary(Collection<EnrollmentsCSV> enrollmentsSummary) {
        this.enrollmentsSummary = enrollmentsSummary;
    }

    @Override
    public String toString() {
        return "EnrollmentsResponse{" + "enrollmentsSummary=" + enrollmentsSummary + '}';
    }
}
