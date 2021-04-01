package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class ActiveSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    Collection<String> sliderLabel;
    Collection<ActiveStatusSummary> actives;
    ActiveSummaryResume summary;

    public ActiveSummaryResponse(Collection<String> sliderLabel, Collection<ActiveStatusSummary> actives,
                                 ActiveSummaryResume summary) {
        this.sliderLabel = sliderLabel;
        this.actives = actives;
        this.summary = summary;
    }

    public Collection<String> getSliderLabel() {
        return sliderLabel;
    }

    public void setSliderLabel(Collection<String> sliderLabel) {
        this.sliderLabel = sliderLabel;
    }

    public Collection<ActiveStatusSummary> getActives() {
        return actives;
    }

    public void setActives(Collection<ActiveStatusSummary> actives) {
        this.actives = actives;
    }

    public ActiveSummaryResume getSummary() {
        return summary;
    }

    public void setSummary(ActiveSummaryResume summary) {
        this.summary = summary;
    }
}
