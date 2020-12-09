package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class AlumniDataResponse implements Comparable {
    double averageGpa;
    String graduationTerm;
    int alumniCount;

    public AlumniDataResponse(double averageGpa, String graduationTerm, int alumniCount) {
        this.averageGpa = averageGpa;
        this.graduationTerm = graduationTerm;
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
        AlumniDataResponse other = (AlumniDataResponse) o;
        return this.getGraduationTerm().compareTo(other.getGraduationTerm());
    }
}
