package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class EnrollmentsSummary {
    private TermCount max;
    private TermCount min;
    private EnrollmentsStatisticsSummary summary;

    public EnrollmentsSummary(TermCount max, TermCount min, EnrollmentsStatisticsSummary summary) {
        this.max = max;
        this.min = min;
        this.summary = summary;
    }

    public TermCount getMax() {
        return max;
    }

    public void setMax(TermCount max) {
        this.max = max;
    }

    public TermCount getMin() {
        return min;
    }

    public void setMin(TermCount min) {
        this.min = min;
    }

    public EnrollmentsStatisticsSummary getSummary() {
        return summary;
    }

    public void setSummary(EnrollmentsStatisticsSummary summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "EnrollmentsSummary{" +
                "max=" + max +
                ", min=" + min +
                ", summary=" + summary +
                '}';
    }
}
