package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class AlumniSummaryResponse {
    //@ApiModelProperty(position = 0, example = ApiDocumentation.Model.REGISTRATION)
    double average_gpa;
    int max_degree_count;
    double average_degree_count;
    int min_degree_count;
    String max_degree_count_term;
    String min_degree_count_term;
    Collection<AlumniDataResponse> terms;
    int total_degree_count;

    public AlumniSummaryResponse(double average_gpa, int max_degree_count, double average_degree_count, int min_degree_count,
                                 String max_degree_count_term, String min_degree_count_term,
                                 Collection<AlumniDataResponse> terms, int total_degree_count) {
        this.average_gpa = average_gpa;
        this.max_degree_count = max_degree_count;
        this.average_degree_count = average_degree_count;
        this.min_degree_count = min_degree_count;
        this.max_degree_count_term = max_degree_count_term;
        this.min_degree_count_term = min_degree_count_term;
        this.terms = terms;
        this.total_degree_count = total_degree_count;
    }

    public double getAverage_gpa() {
        return average_gpa;
    }

    public void setAverage_gpa(double average_gpa) {
        this.average_gpa = average_gpa;
    }

    public int getMax_degree_count() {
        return max_degree_count;
    }

    public void setMax_degree_count(int max_degree_count) {
        this.max_degree_count = max_degree_count;
    }

    public double getAverage_degree_count() {
        return average_degree_count;
    }

    public void setAverage_degree_count(double average_degree_count) {
        this.average_degree_count = average_degree_count;
    }

    public int getMin_degree_count() {
        return min_degree_count;
    }

    public void setMin_degree_count(int min_degree_count) {
        this.min_degree_count = min_degree_count;
    }

    public String getMax_degree_count_term() {
        return max_degree_count_term;
    }

    public void setMax_degree_count_term(String max_degree_count_term) {
        this.max_degree_count_term = max_degree_count_term;
    }

    public String getMin_degree_count_term() {
        return min_degree_count_term;
    }

    public void setMin_degree_count_term(String min_degree_count_term) {
        this.min_degree_count_term = min_degree_count_term;
    }

    public Collection<AlumniDataResponse> getTerms() {
        return terms;
    }

    public void setTerms(Collection<AlumniDataResponse> terms) {
        this.terms = terms;
    }

    public int getTotal_degree_count() {
        return total_degree_count;
    }

    public void setTotal_degree_count(int total_degree_count) {
        this.total_degree_count = total_degree_count;
    }
}
