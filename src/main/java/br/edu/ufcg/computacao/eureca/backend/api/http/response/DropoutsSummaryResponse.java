package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class DropoutsSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    Collection<String> sliderLabel;
    Collection<DropoutPerTermSummary> terms;
    DropoutsSummary summary;

    public DropoutsSummaryResponse(Collection<String> sliderLabel, Collection<DropoutPerTermSummary> terms,
                                   DropoutsSummary summary) {
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

    public DropoutsSummary getSummary() {
        return summary;
    }

    public void setSummary(DropoutsSummary summary) {
        this.summary = summary;
    }
}
