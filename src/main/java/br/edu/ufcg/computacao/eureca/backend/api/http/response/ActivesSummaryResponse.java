package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class ActivesSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    private Collection<String> sliderLabel;
    private Collection<ActivesPerTermSummary> terms;

    public ActivesSummaryResponse(Collection<String> sliderLabel, Collection<ActivesPerTermSummary> terms) {
        this.sliderLabel = sliderLabel;
        this.terms = terms;
    }

    public Collection<String> getSliderLabel() {
        return sliderLabel;
    }

    public void setSliderLabel(Collection<String> sliderLabel) {
        this.sliderLabel = sliderLabel;
    }

    public Collection<ActivesPerTermSummary> getTerms() {
        return terms;
    }

    public void setTerms(Collection<ActivesPerTermSummary> terms) {
        this.terms = terms;
    }
}
