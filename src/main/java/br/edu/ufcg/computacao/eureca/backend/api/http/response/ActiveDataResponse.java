package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class ActiveDataResponse {
    String matricula;
    double cra;
    int periodos_integralizados;
    int cred_comp_int;
    int cred_obrig_int;
    int cred_opt_int;
    String curriculo;
    String estado_civil;
    String genero;
    double iea;
    int matriculas_institucionais;
    double mc;
    double media_geral_ingresso;
    int mobilidade_estudantil;
    String periodo_ingresso;
    int trancamentos_totais;
    String cota;

    public ActiveDataResponse(String matricula, double cra, int periodos_integralizados, int cred_comp_int,
                              int cred_obrig_int, int cred_opt_int, String curriculo, String estado_civil,
                              String genero, double iea, int matriculas_institucionais, double mc,
                              double media_geral_ingresso, int mobilidade_estudantil, String periodo_ingresso,
                              int trancamentos_totais, String cota) {
        this.matricula = matricula;
        this.cra = cra;
        this.periodos_integralizados = periodos_integralizados;
        this.cred_comp_int = cred_comp_int;
        this.cred_obrig_int = cred_obrig_int;
        this.cred_opt_int = cred_opt_int;
        this.curriculo = curriculo;
        this.estado_civil = estado_civil;
        this.genero = genero;
        this.iea = iea;
        this.matriculas_institucionais = matriculas_institucionais;
        this.mc = mc;
        this.media_geral_ingresso = media_geral_ingresso;
        this.mobilidade_estudantil = mobilidade_estudantil;
        this.periodo_ingresso = periodo_ingresso;
        this.trancamentos_totais = trancamentos_totais;
        this.cota = cota;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getCra() {
        return cra;
    }

    public void setCra(double cra) {
        this.cra = cra;
    }

    public int getPeriodos_integralizados() {
        return periodos_integralizados;
    }

    public void setPeriodos_integralizados(int periodos_integralizados) {
        this.periodos_integralizados = periodos_integralizados;
    }

    public int getCred_comp_int() {
        return cred_comp_int;
    }

    public void setCred_comp_int(int cred_comp_int) {
        this.cred_comp_int = cred_comp_int;
    }

    public int getCred_obrig_int() {
        return cred_obrig_int;
    }

    public void setCred_obrig_int(int cred_obrig_int) {
        this.cred_obrig_int = cred_obrig_int;
    }

    public int getCred_opt_int() {
        return cred_opt_int;
    }

    public void setCred_opt_int(int cred_opt_int) {
        this.cred_opt_int = cred_opt_int;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getIea() {
        return iea;
    }

    public void setIea(double iea) {
        this.iea = iea;
    }

    public int getMatriculas_institucionais() {
        return matriculas_institucionais;
    }

    public void setMatriculas_institucionais(int matriculas_institucionais) {
        this.matriculas_institucionais = matriculas_institucionais;
    }

    public double getMc() {
        return mc;
    }

    public void setMc(double mc) {
        this.mc = mc;
    }

    public double getMedia_geral_ingresso() {
        return media_geral_ingresso;
    }

    public void setMedia_geral_ingresso(double media_geral_ingresso) {
        this.media_geral_ingresso = media_geral_ingresso;
    }

    public int getMobilidade_estudantil() {
        return mobilidade_estudantil;
    }

    public void setMobilidade_estudantil(int mobilidade_estudantil) {
        this.mobilidade_estudantil = mobilidade_estudantil;
    }

    public String getPeriodo_ingresso() {
        return periodo_ingresso;
    }

    public void setPeriodo_ingresso(String periodo_ingresso) {
        this.periodo_ingresso = periodo_ingresso;
    }

    public int getTrancamentos_totais() {
        return trancamentos_totais;
    }

    public void setTrancamentos_totais(int trancamentos_totais) {
        this.trancamentos_totais = trancamentos_totais;
    }

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }
}
