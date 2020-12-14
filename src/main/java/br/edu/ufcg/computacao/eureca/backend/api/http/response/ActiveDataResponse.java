package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class ActiveDataResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    Collection<String> sliderLabel;
    Collection<ActiveData> content;
    RiskSummary summary;

    public ActiveDataResponse(Collection<String> sliderLabel, Collection<ActiveData> content, RiskSummary summary) {
        this.sliderLabel = sliderLabel;
        this.content = content;
        this.summary = summary;
    }

    public Collection<String> getSliderLabel() {
        return sliderLabel;
    }

    public void setSliderLabel(Collection<String> sliderLabel) {
        this.sliderLabel = sliderLabel;
    }

    public Collection<ActiveData> getContent() {
        return content;
    }

    public void setContent(Collection<ActiveData> content) {
        this.content = content;
    }

    public RiskSummary getSummary() {
        return summary;
    }

    public void setSummary(RiskSummary summary) {
        this.summary = summary;
    }
}
