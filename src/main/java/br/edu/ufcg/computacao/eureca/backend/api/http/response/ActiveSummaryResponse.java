package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class ActiveSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    Collection<String> sliderLabel;
    Collection<ActiveSummary> content;

    public Collection<String> getSliderLabel() {
        return sliderLabel;
    }

    public void setSliderLabel(Collection<String> sliderLabel) {
        this.sliderLabel = sliderLabel;
    }

    public Collection<ActiveSummary> getContent() {
        return content;
    }

    public void setContent(Collection<ActiveSummary> content) {
        this.content = content;
    }

    public ActiveSummaryResponse(Collection<String> sliderLabel, Collection<ActiveSummary> content) {
        this.sliderLabel = sliderLabel;
        this.content = content;
    }
}
