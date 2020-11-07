package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import io.swagger.annotations.ApiModelProperty;

public class ActiveSummaryResponse {
    @ApiModelProperty(position = 0, example = ApiDocumentation.Model.REGISTRATION)
    String matricula;
    @ApiModelProperty(position = 1, example = ApiDocumentation.Model.PERIOD)
    String periodo_ingresso;
    @ApiModelProperty(position = 2, example = ApiDocumentation.Model.NUMBER)
    int periodos_integralizados;
    @ApiModelProperty(position = 3, example = ApiDocumentation.Model.PERCENTAGE)
    double porcentagem_concluida;

    public ActiveSummaryResponse(String matricula, String periodo_ingresso, int periodos_integralizados, double concluded) {
        this.matricula = matricula;
        this.periodo_ingresso = periodo_ingresso;
        this.periodos_integralizados = periodos_integralizados;
        this.porcentagem_concluida = concluded;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPeriodo_ingresso() {
        return periodo_ingresso;
    }

    public void setPeriodo_ingresso(String periodo_ingresso) {
        this.periodo_ingresso = periodo_ingresso;
    }

    public int getPeriodos_integralizados() {
        return periodos_integralizados;
    }

    public void setPeriodos_integralizados(int periodos_integralizados) {
        this.periodos_integralizados = periodos_integralizados;
    }

    public double getPorcentagem_concluida() {
        return porcentagem_concluida;
    }

    public void setPorcentagem_concluida(double porcentagem_concluida) {
        this.porcentagem_concluida = porcentagem_concluida;
    }
}
