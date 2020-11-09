package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class AlumniSummaryResponse {
    //@ApiModelProperty(position = 0, example = ApiDocumentation.Model.REGISTRATION)
    double cra_medio;
    int max_graduados;
    double media_graduados;
    int min_graduados;
    String periodo_max_graduados;
    String periodo_min_graduados;
    Collection<AlumniDataResponse> periodos;
    int total_graduados;

    public AlumniSummaryResponse(double cra_medio, int max_graduados, double media_graduados, int min_graduados,
                                 String periodo_max_graduados, String periodo_min_graduados,
                                 Collection<AlumniDataResponse> periodos, int total_graduados) {
        this.cra_medio = cra_medio;
        this.max_graduados = max_graduados;
        this.media_graduados = media_graduados;
        this.min_graduados = min_graduados;
        this.periodo_max_graduados = periodo_max_graduados;
        this.periodo_min_graduados = periodo_min_graduados;
        this.periodos = periodos;
        this.total_graduados = total_graduados;
    }

    public double getCra_medio() {
        return cra_medio;
    }

    public void setCra_medio(double cra_medio) {
        this.cra_medio = cra_medio;
    }

    public int getMax_graduados() {
        return max_graduados;
    }

    public void setMax_graduados(int max_graduados) {
        this.max_graduados = max_graduados;
    }

    public double getMedia_graduados() {
        return media_graduados;
    }

    public void setMedia_graduados(double media_graduados) {
        this.media_graduados = media_graduados;
    }

    public int getMin_graduados() {
        return min_graduados;
    }

    public void setMin_graduados(int min_graduados) {
        this.min_graduados = min_graduados;
    }

    public String getPeriodo_max_graduados() {
        return periodo_max_graduados;
    }

    public void setPeriodo_max_graduados(String periodo_max_graduados) {
        this.periodo_max_graduados = periodo_max_graduados;
    }

    public String getPeriodo_min_graduados() {
        return periodo_min_graduados;
    }

    public void setPeriodo_min_graduados(String periodo_min_graduados) {
        this.periodo_min_graduados = periodo_min_graduados;
    }

    public Collection<AlumniDataResponse> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(Collection<AlumniDataResponse> periodos) {
        this.periodos = periodos;
    }

    public int getTotal_graduados() {
        return total_graduados;
    }

    public void setTotal_graduados(int total_graduados) {
        this.total_graduados = total_graduados;
    }
}
