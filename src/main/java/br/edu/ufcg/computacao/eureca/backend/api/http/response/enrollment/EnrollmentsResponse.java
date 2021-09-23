package br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment;

import java.util.Collection;

public class EnrollmentsResponse {
    private Collection<EnrollmentsCSV> enrollments;

    public EnrollmentsResponse(Collection<EnrollmentsCSV> enrollments) {
        this.enrollments = enrollments;
    }

    public Collection<EnrollmentsCSV> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Collection<EnrollmentsCSV> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "EnrollmentsResponse{" + "enrollmentsSummary=" + enrollments + '}';
    }
}
