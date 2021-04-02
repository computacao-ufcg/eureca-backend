package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class DelayedSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    Collection<String> sliderLabel;
    Collection<DelayedPerTermSummary> terms;
    DelayedSummary summary;

    public DelayedSummaryResponse(Collection<String> sliderLabel, Collection<DelayedPerTermSummary> terms,
                                  DelayedSummary summary) {
        this.sliderLabel = sliderLabel;
        this.terms = terms;
        this.summary = summary;
    }

    public Collection<String> getSliderLabel() {
        return sliderLabel;
    }

    public void setSliderLabel(Collection<String> sliderLabel) {
        this.sliderLabel = sliderLabel;
    }

    public Collection<DelayedPerTermSummary> getTerms() {
        return terms;
    }

    public void setTerms(Collection<DelayedPerTermSummary> terms) {
        this.terms = terms;
    }

    public DelayedSummary getSummary() {
        return summary;
    }

    public void setSummary(DelayedSummary summary) {
        this.summary = summary;
    }
}
