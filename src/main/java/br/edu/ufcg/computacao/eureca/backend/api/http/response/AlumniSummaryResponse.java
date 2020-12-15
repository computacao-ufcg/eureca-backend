package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class AlumniSummaryResponse {
    Collection<String> sliderLabel;
    AlumniSummary summary;
    Collection<AlumniPerTermSummary> terms;

    public AlumniSummaryResponse(Collection<String> sliderLabel, AlumniSummary summary,
                                 Collection<AlumniPerTermSummary> terms) {
        this.sliderLabel = sliderLabel;
        this.summary = summary;
        this.terms = terms;
    }

    public Collection<String> getSliderLabel() {
        return sliderLabel;
    }

    public void setSliderLabel(Collection<String> sliderLabel) {
        this.sliderLabel = sliderLabel;
    }

    public AlumniSummary getSummary() {
        return summary;
    }

    public void setSummary(AlumniSummary summary) {
        this.summary = summary;
    }

    public Collection<AlumniPerTermSummary> getTerms() {
        return terms;
    }

    public void setTerms(Collection<AlumniPerTermSummary> terms) {
        this.terms = terms;
    }
}
