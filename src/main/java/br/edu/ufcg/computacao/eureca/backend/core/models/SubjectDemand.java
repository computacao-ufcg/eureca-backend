package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Map;

public class SubjectDemand {

    private String subjectCode;
    private String subjectName;
    private Map<Integer, Integer> demandByTerm;
    private int totalDemand;

    public SubjectDemand(Subject subject, Map<Integer, Integer> demandByTerm) {
        this.subjectCode = subject.getSubjectCode();
        this.subjectName = subject.getName();
        this.demandByTerm = demandByTerm;
        this.calculateTotalDemand();
    }

    private void calculateTotalDemand() {
        int totalDemand = 0;
        for (Integer demand : this.demandByTerm.values()) {
            totalDemand += demand;
        }
        this.totalDemand = totalDemand;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Map<Integer, Integer> getDemandByTerm() {
        return demandByTerm;
    }

    public void setDemandByTerm(Map<Integer, Integer> demandByTerm) {
        this.demandByTerm = demandByTerm;
    }

    public int getTotalDemand() {
        return totalDemand;
    }

    @Override
    public String toString() {
        return "SubjectDemand{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", demandByTerm=" + demandByTerm +
                ", totalDemand=" + totalDemand +
                '}';
    }
}
