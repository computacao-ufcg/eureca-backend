package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class AlumniDataResponse {
    double average_gpa;
    String graduation_term;
    int alumni_count;

    public AlumniDataResponse(double average_gpa, String graduation_term, int alumni_count) {
        this.average_gpa = average_gpa;
        this.graduation_term = graduation_term;
        this.alumni_count = alumni_count;
    }

    public double getAverage_gpa() {
        return average_gpa;
    }

    public void setAverage_gpa(double average_gpa) {
        this.average_gpa = average_gpa;
    }

    public String getGraduation_term() {
        return graduation_term;
    }

    public void setGraduation_term(String graduation_term) {
        this.graduation_term = graduation_term;
    }

    public int getAlumni_count() {
        return alumni_count;
    }

    public void setAlumni_count(int alumni_count) {
        this.alumni_count = alumni_count;
    }
}
