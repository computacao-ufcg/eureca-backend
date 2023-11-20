package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.*;

public class DemandCSVResponse {
    private Collection<SubjectDemand> demand;

    public DemandCSVResponse(PreEnrollments preEnrollments) {
        Map<String, SubjectDemand> aggregatedDemand = new HashMap<>();
        this.demand = new TreeSet<>();
        Collection<DetailedSubjectDemand> detailedDemand = new HashSet<>();
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getElectiveDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getOptionalDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getComplementaryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getMandatoryDemand());
        detailedDemand.forEach(detailedSubjectDemand -> {
            this.demand.add(new SubjectDemand(detailedSubjectDemand.getDemand().getSubjectCode(),
                    detailedSubjectDemand.getDemand().getSubjectName(),
                    detailedSubjectDemand.getDemand().getCurriculumCode(),
                    detailedSubjectDemand.getDemand().getClassId(),
                    detailedSubjectDemand.getDemand().getTotalDemand()));
            SubjectDemand subjectAggregatedDemand = aggregatedDemand.get(detailedSubjectDemand.getDemand().getSubjectCode());
            if (subjectAggregatedDemand == null) {
                subjectAggregatedDemand = new SubjectDemand(detailedSubjectDemand.getDemand().getSubjectCode(),
                        detailedSubjectDemand.getDemand().getSubjectName(),
                        "Total",
                        "-",
                        0);
                aggregatedDemand.put(detailedSubjectDemand.getDemand().getSubjectCode(), subjectAggregatedDemand);
            }
            subjectAggregatedDemand.setTotalDemand(subjectAggregatedDemand.getTotalDemand() +
                    detailedSubjectDemand.getDemand().getTotalDemand());
        });
        this.demand.addAll(aggregatedDemand.values());
    }

    public Collection<SubjectDemand> getDemand() {
        return demand;
    }

    public void setDemand(Collection<SubjectDemand> demand) {
        this.demand = demand;
    }
}
