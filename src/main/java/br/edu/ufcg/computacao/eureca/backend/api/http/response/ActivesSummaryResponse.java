package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class ActivesSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.SLIDER_LABEL)
    Collection<String> sliderLabel;
    Collection<ActivesPerTermSummary> actives;
    ActivesSummary summary;

    public ActivesSummaryResponse(Collection<String> sliderLabel, Collection<ActivesPerTermSummary> actives,
                                  ActivesSummary summary) {
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

    public Collection<ActivesPerTermSummary> getActives() {
        return actives;
    }

    public void setActives(Collection<ActivesPerTermSummary> actives) {
        this.actives = actives;
    }

    public ActivesSummary getSummary() {
        return summary;
    }

    public void setSummary(ActivesSummary summary) {
        this.summary = summary;
    }
}
