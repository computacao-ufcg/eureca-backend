package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class AlumniPerTermSummary implements Comparable {
    private String graduationTerm;
    private int alumniCount;
    private double averageGpa;

    public AlumniPerTermSummary(String graduationTerm, int alumniCount, double averageGpa) {
        this.graduationTerm = graduationTerm;
        this.alumniCount = alumniCount;
        this.averageGpa = averageGpa;
    }

    public double getAverageGpa() {
        return averageGpa;
    }

    public void setAverageGpa(double averageGpa) {
        this.averageGpa = averageGpa;
    }

    public int getAlumniCount() {
        return alumniCount;
    }

    public void setAlumniCount(int alumniCount) {
        this.alumniCount = alumniCount;
    }

    public String getGraduationTerm() {
        return graduationTerm;
    }

    public void setGraduationTerm(String graduationTerm) {
        this.graduationTerm = graduationTerm;
    }

    @Override
    public int compareTo(Object o) {
        AlumniPerTermSummary other = (AlumniPerTermSummary) o;
        return this.getGraduationTerm().compareTo(other.getGraduationTerm());
    }
}
