package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class AlumniDataResponse {
    double cra_medio;
    String periodo_conclusao;
    int qtd_egressos;

    public AlumniDataResponse(double cra_medio, String periodo_conclusao, int qtd_egressos) {
        this.cra_medio = cra_medio;
        this.periodo_conclusao = periodo_conclusao;
        this.qtd_egressos = qtd_egressos;
    }

    public double getCra_medio() {
        return cra_medio;
    }

    public void setCra_medio(double cra_medio) {
        this.cra_medio = cra_medio;
    }

    public String getPeriodo_conclusao() {
        return periodo_conclusao;
    }

    public void setPeriodo_conclusao(String periodo_conclusao) {
        this.periodo_conclusao = periodo_conclusao;
    }

    public int getQtd_egressos() {
        return qtd_egressos;
    }

    public void setQtd_egressos(int qtd_egressos) {
        this.qtd_egressos = qtd_egressos;
    }
}
