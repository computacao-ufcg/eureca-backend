package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class DropoutSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    Collection<String> sliderLabel;
    Collection<DropoutPerTermSummary> terms;
    DropoutSummary summary;

    public DropoutSummaryResponse(Collection<String> sliderLabel, Collection<DropoutPerTermSummary> terms,
                                  DropoutSummary summary) {
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

    public Collection<DropoutPerTermSummary> getTerms() {
        return terms;
    }

    public void setTerms(Collection<DropoutPerTermSummary> terms) {
        this.terms = terms;
    }

    public DropoutSummary getSummary() {
        return summary;
    }

    public void setSummary(DropoutSummary summary) {
        this.summary = summary;
    }
}
