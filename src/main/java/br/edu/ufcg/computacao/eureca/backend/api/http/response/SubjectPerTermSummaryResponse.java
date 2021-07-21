package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class SubjectPerTermSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    private Collection<String> sliderLabel;
    private Collection<SubjectPerTermSummary> terms;

    public SubjectPerTermSummaryResponse(Collection<String> sliderLabel, Collection<SubjectPerTermSummary> terms) {
        this.sliderLabel = sliderLabel;
        this.terms = terms;
    }

    public Collection<String> getSliderLabel() {
        return sliderLabel;
    }

    public void setSliderLabel(Collection<String> sliderLabel) {
        this.sliderLabel = sliderLabel;
    }

    public Collection<SubjectPerTermSummary> getTerms() {
        return terms;
    }

    public void setTerms(Collection<SubjectPerTermSummary> terms) {
        this.terms = terms;
    }
}
