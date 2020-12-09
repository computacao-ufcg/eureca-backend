package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

public class ActiveSummary implements Comparable {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.REGISTRATION)
    String registration;
    @ApiModelProperty(position = 1, example = ApiDocumentation.Model.PERIOD)
    String admissionTerm;
    @ApiModelProperty(position = 2, example = ApiDocumentation.Model.NUMBER)
    int completedTerms;
    @ApiModelProperty(position = 3, example = ApiDocumentation.Model.PERCENTAGE)
    double progress;

    public ActiveSummary(String registration, String admissionTerm, int completedTerms, double progress) {
        this.registration = registration;
        this.admissionTerm = admissionTerm;
        this.completedTerms = completedTerms;
        this.progress = progress;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public int getCompletedTerms() {
        return completedTerms;
    }

    public void setCompletedTerms(int completedTerms) {
        this.completedTerms = completedTerms;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    @Override
    public int compareTo(Object o) {
        ActiveSummary other = (ActiveSummary) o;
        return this.getRegistration().compareTo(other.getRegistration());
    }
}
