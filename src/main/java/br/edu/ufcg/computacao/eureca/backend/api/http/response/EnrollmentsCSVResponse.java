package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.Glossary;
import br.edu.ufcg.computacao.eureca.backend.constants.PortugueseStudentsGlossary;

import java.util.Collection;

public class EnrollmentsCSVResponse {

    private Collection<EnrollmentsSummaryItemResponse> enrollmentsSummary;
    private Glossary glossary;

    public EnrollmentsCSVResponse(Collection<EnrollmentsSummaryItemResponse> enrollmentsSummary, Glossary glossary) {
        this.enrollmentsSummary = enrollmentsSummary;
        this.glossary = glossary;
    }

    public EnrollmentsCSVResponse(Collection<EnrollmentsSummaryItemResponse> enrollmentsSummary) {
        this(enrollmentsSummary, new PortugueseStudentsGlossary());
    }

    public Collection<EnrollmentsSummaryItemResponse> getEnrollmentsSummary() {
        return enrollmentsSummary;
    }

    public void setEnrollmentsSummary(Collection<EnrollmentsSummaryItemResponse> enrollmentsSummary) {
        this.enrollmentsSummary = enrollmentsSummary;
    }

    public Glossary getGlossary() {
        return glossary;
    }

    public void setGlossary(Glossary glossary) {
        this.glossary = glossary;
    }

    @Override
    public String toString() {
        return "EnrollmentsCSVResponse{" +
                "enrollmentsSummary=" + enrollmentsSummary +
                ", glossary=" + glossary +
                '}';
    }
}
