package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

public class SubjectDemand implements Comparable {
    private String subjectCode;
    private String subjectName;
    private int totalDemand;

    public SubjectDemand(String subjectCode, String subjectName, int totalDemand) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.totalDemand = totalDemand;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getTotalDemand() {
        return totalDemand;
    }

    @Override
    public String toString() {
        return "DetailedSubjectDemand{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", totalDemand=" + totalDemand +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        SubjectDemand other = (SubjectDemand) o;
        return this.getTotalDemand() - other.getTotalDemand();
    }
}
