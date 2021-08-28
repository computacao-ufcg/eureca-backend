package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Term;

public class EnrollmentsMetricsPerTerm implements Comparable, Term {
    private String term;
    private int enrollmentsCount;
    private int classesCount;
    private double averageEnrollmentsPerClass;

    public EnrollmentsMetricsPerTerm(String term, int enrollmentsCount, int classesCount, double averageEnrollmentsPerClass) {
        this.term = term;
        this.enrollmentsCount = enrollmentsCount;
        this.classesCount = classesCount;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }

    @Override
    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getEnrollmentsCount() {
        return enrollmentsCount;
    }

    public void setEnrollmentsCount(int enrollmentsCount) {
        this.enrollmentsCount = enrollmentsCount;
    }

    public int getClassesCount() {
        return classesCount;
    }

    public void setClassesCount(int classesCount) {
        this.classesCount = classesCount;
    }

    public double getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public void setAverageEnrollmentsPerClass(double averageEnrollmentsPerClass) {
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }

    @Override
    public int compareTo(Object o) {
        EnrollmentsMetricsPerTerm other = (EnrollmentsMetricsPerTerm) o;
        return this.getTerm().compareTo(other.getTerm());
    }

    @Override
    public String toString() {
        return "EnrollmentsMetricsPerTerm{" +
                "term='" + term + '\'' +
                ", enrollmentsCount=" + enrollmentsCount +
                ", classesCount=" + classesCount +
                ", averageEnrollmentsPerClass=" + averageEnrollmentsPerClass +
                '}';
    }
}
