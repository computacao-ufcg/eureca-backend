package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;

import java.util.Map;

public class DetailedSubjectDemand implements Comparable {
    private Map<Integer, Integer> demandByTerm;
    private SubjectDemand demand;

    public DetailedSubjectDemand(Subject subject, Map<Integer, Integer> demandByTerm) {
        this.demandByTerm = demandByTerm;
        this.demand = new SubjectDemand(subject.getSubjectCode(), subject.getName(), this.calculateTotalDemand());
    }

    private int calculateTotalDemand() {
        int totalDemand = 0;
        for (Integer demand : this.demandByTerm.values()) {
            totalDemand += demand;
        }
        return totalDemand;
    }

    public Map<Integer, Integer> getDemandByTerm() {
        return demandByTerm;
    }

    public void setDemandByTerm(Map<Integer, Integer> demandByTerm) {
        this.demandByTerm = demandByTerm;
    }

    public SubjectDemand getDemand() {
        return demand;
    }

    public void setDemand(SubjectDemand demand) {
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "DetailedSubjectDemand{" +
                "demandByTerm=" + demandByTerm +
                ", demand=" + demand +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        DetailedSubjectDemand other = (DetailedSubjectDemand) o;
        return this.demand.getSubjectName().compareTo(other.demand.getSubjectName());
    }
}
