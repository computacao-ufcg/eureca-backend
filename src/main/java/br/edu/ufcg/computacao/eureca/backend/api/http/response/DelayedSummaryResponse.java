package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class DelayedSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    private Collection<String> sliderLabel;
    private Collection<DelayedPerTermSummary> terms;

    public DelayedSummaryResponse(Collection<String> sliderLabel, Collection<DelayedPerTermSummary> terms) {
        this.sliderLabel = sliderLabel;
        this.terms = terms;
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
}
