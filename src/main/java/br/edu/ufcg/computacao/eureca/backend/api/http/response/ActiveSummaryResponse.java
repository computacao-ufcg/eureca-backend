package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

public class ActiveSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.REGISTRATION)
    String registration;
    @ApiModelProperty(position = 1, example = ApiDocumentation.Model.PERIOD)
    String admission_term;
    @ApiModelProperty(position = 2, example = ApiDocumentation.Model.NUMBER)
    int terms_done;
    @ApiModelProperty(position = 3, example = ApiDocumentation.Model.PERCENTAGE)
    double progress;

    public ActiveSummaryResponse(String registration, String admission_term, int terms_done, double progress) {
        this.registration = registration;
        this.admission_term = admission_term;
        this.terms_done = terms_done;
        this.progress = progress;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getAdmission_term() {
        return admission_term;
    }

    public void setAdmission_term(String admission_term) {
        this.admission_term = admission_term;
    }

    public int getTerms_done() {
        return terms_done;
    }

    public void setTerms_done(int terms_done) {
        this.terms_done = terms_done;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
