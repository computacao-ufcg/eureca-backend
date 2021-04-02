package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class DropoutsSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    private Collection<String> sliderLabel;
    private Collection<DropoutPerTermSummary> terms;

    public DropoutsSummaryResponse(Collection<String> sliderLabel, Collection<DropoutPerTermSummary> terms) {
        this.sliderLabel = sliderLabel;
        this.terms = terms;
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
}
