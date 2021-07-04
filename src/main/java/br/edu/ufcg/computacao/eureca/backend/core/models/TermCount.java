package br.edu.ufcg.computacao.eureca.backend.core.models;

public class TermCount {
    private int count;
    private String term;

    public TermCount(int count, String term) {
        this.count = count;
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TermCount{" +
                "count=" + count +
                ", term='" + term + '\'' +
                '}';
    }
}
