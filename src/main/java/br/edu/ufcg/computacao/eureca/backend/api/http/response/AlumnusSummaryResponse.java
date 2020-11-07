package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

public class AlumnusSummaryResponse {
    //@ApiModelProperty(position = 0, example = ApiDocumentation.Model.REGISTRATION)
    double cra_medio;
    int max_graduados;
    int media_graduados;
    int min_graduados;
    String periodo_max_graduados;
    String periodo_min_graduados;
    Collection<PerPeriodData> periodos;
    int total_graduados;

    public AlumnusSummaryResponse(double cra_medio, int max_graduados, int media_graduados, int min_graduados,
                                  String periodo_max_graduados, String periodo_min_graduados,
                                  Collection<PerPeriodData> periodos, int total_graduados) {
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

    public int getMedia_graduados() {
        return media_graduados;
    }

    public void setMedia_graduados(int media_graduados) {
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

    public Collection<PerPeriodData> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(Collection<PerPeriodData> periodos) {
        this.periodos = periodos;
    }

    public int getTotal_graduados() {
        return total_graduados;
    }

    public void setTotal_graduados(int total_graduados) {
        this.total_graduados = total_graduados;
    }

    private class PerPeriodData {
        double  cra_medio;
        int qtd_egressos;
        String semestre_ingresso;

        public PerPeriodData(double cra_medio, int qtd_egressos, String semestre_ingresso) {
            this.cra_medio = cra_medio;
            this.qtd_egressos = qtd_egressos;
            this.semestre_ingresso = semestre_ingresso;
        }

        public double getCra_medio() {
            return cra_medio;
        }

        public void setCra_medio(double cra_medio) {
            this.cra_medio = cra_medio;
        }

        public int getQtd_egressos() {
            return qtd_egressos;
        }

        public void setQtd_egressos(int qtd_egressos) {
            this.qtd_egressos = qtd_egressos;
        }

        public String getSemestre_ingresso() {
            return semestre_ingresso;
        }

        public void setSemestre_ingresso(String semestre_ingresso) {
            this.semestre_ingresso = semestre_ingresso;
        }
    }
}
