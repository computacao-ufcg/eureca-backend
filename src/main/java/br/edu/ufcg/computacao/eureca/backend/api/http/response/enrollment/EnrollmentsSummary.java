package br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment;

import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class EnrollmentsSummary {
    private String from;
    private String to;
    private TermCount max;
    private TermCount min;
    private EnrollmentsStatisticsSummary summary;

    public EnrollmentsSummary(String from, String to, TermCount max, TermCount min, EnrollmentsStatisticsSummary summary) {
        this.from = from;
        this.to = to;
        this.max = max;
        this.min = min;
        this.summary = summary;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", max=" + max +
                ", min=" + min +
                ", summary=" + summary +
                '}';
    }
}
