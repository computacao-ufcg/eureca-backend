package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public class DemandCSVResponse {

    private Collection<SubjectDemand> demand;

    public DemandCSVResponse(PreEnrollments preEnrollments) {
        this.demand = new TreeSet<>();
        Collection<DetailedSubjectDemand> detailedDemand = new HashSet<>();
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getElectiveDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getOptionalDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getComplementaryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getMandatoryDemand());
        detailedDemand.forEach(detailedSubjectDemand -> {
            this.demand.add(new SubjectDemand(detailedSubjectDemand.getDemand().getSubjectCode(),
                    detailedSubjectDemand.getDemand().getSubjectName(),
                    detailedSubjectDemand.getDemand().getTotalDemand()));
        });
    }

    public Collection<SubjectDemand> getDemand() {
        return demand;
    }

    public void setDemand(Collection<SubjectDemand> demand) {
        this.demand = demand;
    }
}
