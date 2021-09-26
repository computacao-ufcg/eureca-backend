package br.edu.ufcg.computacao.eureca.backend.api.http.response.subject;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.DetailedSubjectDemand;

import java.util.Collection;

public class SubjectDemandSummary {

    private Collection<DetailedSubjectDemand> mandatoryDemand;
    private Collection<DetailedSubjectDemand> optionalDemand;
    private Collection<DetailedSubjectDemand> complementaryDemand;
    private Collection<DetailedSubjectDemand> electiveDemand;

    public SubjectDemandSummary(Collection<DetailedSubjectDemand> mandatoryDemand, Collection<DetailedSubjectDemand> optionalDemand, Collection<DetailedSubjectDemand> complementaryDemand, Collection<DetailedSubjectDemand> electiveDemand) {
        this.mandatoryDemand = mandatoryDemand;
        this.optionalDemand = optionalDemand;
        this.complementaryDemand = complementaryDemand;
        this.electiveDemand = electiveDemand;
    }

    public Collection<DetailedSubjectDemand> getMandatoryDemand() {
        return mandatoryDemand;
    }

    public void setMandatoryDemand(Collection<DetailedSubjectDemand> mandatoryDemand) {
        this.mandatoryDemand = mandatoryDemand;
    }

    public Collection<DetailedSubjectDemand> getOptionalDemand() {
        return optionalDemand;
    }

    public void setOptionalDemand(Collection<DetailedSubjectDemand> optionalDemand) {
        this.optionalDemand = optionalDemand;
    }

    public Collection<DetailedSubjectDemand> getComplementaryDemand() {
        return complementaryDemand;
    }

    public void setComplementaryDemand(Collection<DetailedSubjectDemand> complementaryDemand) {
        this.complementaryDemand = complementaryDemand;
    }

    public Collection<DetailedSubjectDemand> getElectiveDemand() {
        return electiveDemand;
    }

    public void setElectiveDemand(Collection<DetailedSubjectDemand> electiveDemand) {
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
