package br.edu.ufcg.computacao.eureca.backend.api.http.response.subject;

import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectDemand;

import java.util.Collection;

public class SubjectDemandSummary {

    private Collection<SubjectDemand> mandatoryDemand;
    private Collection<SubjectDemand> optionalDemand;
    private Collection<SubjectDemand> complementaryDemand;
    private Collection<SubjectDemand> electiveDemand;

    public SubjectDemandSummary(Collection<SubjectDemand> mandatoryDemand, Collection<SubjectDemand> optionalDemand, Collection<SubjectDemand> complementaryDemand, Collection<SubjectDemand> electiveDemand) {
        this.mandatoryDemand = mandatoryDemand;
        this.optionalDemand = optionalDemand;
        this.complementaryDemand = complementaryDemand;
        this.electiveDemand = electiveDemand;
    }

    public Collection<SubjectDemand> getMandatoryDemand() {
        return mandatoryDemand;
    }

    public void setMandatoryDemand(Collection<SubjectDemand> mandatoryDemand) {
        this.mandatoryDemand = mandatoryDemand;
    }

    public Collection<SubjectDemand> getOptionalDemand() {
        return optionalDemand;
    }

    public void setOptionalDemand(Collection<SubjectDemand> optionalDemand) {
        this.optionalDemand = optionalDemand;
    }

    public Collection<SubjectDemand> getComplementaryDemand() {
        return complementaryDemand;
    }

    public void setComplementaryDemand(Collection<SubjectDemand> complementaryDemand) {
        this.complementaryDemand = complementaryDemand;
    }

    public Collection<SubjectDemand> getElectiveDemand() {
        return electiveDemand;
    }

    public void setElectiveDemand(Collection<SubjectDemand> electiveDemand) {
        this.electiveDemand = electiveDemand;
    }

    @Override
    public String toString() {
        return "SubjectDemandSummary{" +
                "mandatoryDemand=" + mandatoryDemand +
                ", optionalDemand=" + optionalDemand +
                ", complementaryDemand=" + complementaryDemand +
                ", electiveDemand=" + electiveDemand +
                '}';
    }
}
