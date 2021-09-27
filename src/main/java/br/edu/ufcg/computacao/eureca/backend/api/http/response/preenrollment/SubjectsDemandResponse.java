package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.Collection;

public class SubjectsDemandResponse {
    Collection<SubjectDemand> demand;

    public SubjectsDemandResponse(Collection<SubjectDemand> demand) {
        this.demand = demand;
    }

    public Collection<SubjectDemand> getDemand() {
        return demand;
    }

    public void setDemand(Collection<SubjectDemand> demand) {
        this.demand = demand;
    }
}
