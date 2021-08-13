package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class EnrollmentsCSV implements Comparable {
    private String term;
    private EnrollmentsPerSubjectSummary summary;

    public EnrollmentsCSV(String term, EnrollmentsPerSubjectSummary summary) {
        this.term = term;
        this.summary = summary;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public EnrollmentsPerSubjectSummary getSummary() {
        return summary;
    }

    public void setSummary(EnrollmentsPerSubjectSummary summary) {
        this.summary = summary;
    }

    @Override
    public int compareTo(Object o) {
        EnrollmentsCSV other = (EnrollmentsCSV) o;
        int result = this.getTerm().compareTo(other.getTerm());
        return (result == 0 ? this.getSummary().compareTo(other.getSummary()) : result);
    }

    @Override
    public String toString() {
        return "EnrollmentsCSV{" +
                "term='" + term + '\'' +
                ", summary=" + summary +
                '}';
    }
}
