package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class AlumniPerTermSummary implements Comparable {
    String graduationTerm;
    double averageGpa;
    int alumniCount;

    public AlumniPerTermSummary(String graduationTerm, double averageGpa, int alumniCount) {
        this.graduationTerm = graduationTerm;
        this.averageGpa = averageGpa;
        this.alumniCount = alumniCount;
    }

    public double getAverageGpa() {
        return averageGpa;
    }

    public void setAverageGpa(double averageGpa) {
        this.averageGpa = averageGpa;
    }

    public String getGraduationTerm() {
        return graduationTerm;
    }

    public void setGraduationTerm(String graduationTerm) {
        this.graduationTerm = graduationTerm;
    }

    public int getAlumniCount() {
        return alumniCount;
    }

    public void setAlumniCount(int alumniCount) {
        this.alumniCount = alumniCount;
    }

    @Override
    public int compareTo(Object o) {
        AlumniPerTermSummary other = (AlumniPerTermSummary) o;
        return this.getGraduationTerm().compareTo(other.getGraduationTerm());
    }
}
