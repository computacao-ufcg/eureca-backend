package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import io.swagger.annotations.ApiModelProperty;

public class ActiveStatusSummary implements Comparable {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.REGISTRATION)
    String registration;
    @ApiModelProperty(position = 1, example = ApiDocumentation.Model.PERIOD)
    String admissionTerm;
    @ApiModelProperty(position = 2, example = ApiDocumentation.Model.NUMBER)
    int completedTerms;
    @ApiModelProperty(position = 3, example = ApiDocumentation.Model.PERCENTAGE)
    double progress;
    @ApiModelProperty(position = 3, example = ApiDocumentation.Model.RISK)
    RiskClass riskClass;

    public ActiveStatusSummary(String registration, String admissionTerm, int completedTerms, double progress,
                               RiskClass riskClass) {
        this.registration = registration;
        this.admissionTerm = admissionTerm;
        this.completedTerms = completedTerms;
        this.progress = progress;
        this.riskClass = riskClass;
    }

    public ActiveStatusSummary(ActiveSummary summary, RiskClass riskClass) {
        this.registration = summary.getRegistration();
        this.admissionTerm = summary.getAdmissionTerm();
        this.completedTerms = summary.getCompletedTerms();
        this.progress = summary.getProgress();
        this.riskClass = riskClass;
    }

    public String getRegistration() {
        return this.registration;
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

    public RiskClass getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(RiskClass riskClass) {
        this.riskClass = riskClass;
    }

    @Override
    public int compareTo(Object o) {
        ActiveStatusSummary other = (ActiveStatusSummary) o;
        return (new Registration(this.getRegistration())).compareTo((new Registration(other.getRegistration())));
    }
}
