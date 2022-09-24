package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Objects;

public class OperableTerm implements Term, Comparable {
    private int year;
    private int term;

    public OperableTerm(String termStr) {
        if (termStr == null || termStr.length() != 6) {
            this.year = 0;
            this.term = 0;
        } else {
            this.year = Integer.getInteger(termStr.substring(0,3), 0);
            this.term = Integer.getInteger(termStr.substring(5,5), 0);
        }
    }

    public OperableTerm(int year, int term) {
        this.year =year;
        this.term = term;
    }

    public OperableTerm next() {
        int newYear = this.year;
        int newTerm = this.term++;
        if (this.term == 3) {
            newYear++;
            newTerm = 0;
        }
        return new OperableTerm(newYear, newTerm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperableTerm that = (OperableTerm) o;
        return year == that.year && term == that.term;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, getTerm());
    }

    public String getTerm() {
        return String.format("%04d.%1d", this.year, this.term);
    }

    @Override
    public int compareTo(Object o) {
        OperableTerm that = (OperableTerm) o;
        if (this.year < that.year) return -1;
        if (this.year > that.year) {
            return 1;
        } else {
            return (this.term - that.term);
        }
    }
}
