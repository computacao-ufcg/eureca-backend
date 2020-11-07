package br.edu.ufcg.computacao.eureca.backend.core.models;

public class StudentCourse extends EurecaMapValue {
    int id_ingresso;
    String semestre_ingresso;
    int id_curso;
    int id_situacao;
    String semestre_situacao;
    int id_situacao_vinculo;
    int id_cota;
    int id_tipo_escola;
    String ano_conclusao_ensino_medio;
    String curriculo;
    int carga_hor_obrig_int;
    int cred_obrig_int;
    int carga_hor_opt_int;
    int cred_opt_int;
    int carga_hor_comp_int;
    int cred_comp_int;
    double cra;
    double mc;
    double iea;
    int per_int;
    int tranc;
    int mat_inst;
    int mob_estudantil;
    int cred_matriculados;
    double media_geral_ingresso;

    public StudentCourse(int id_ingresso, String semestre_ingresso, int id_curso, int id_situacao, String semestre_situacao,
                         int id_situacao_vinculo, int id_cota, int id_tipo_escola, String ano_conclusao_ensino_medio, String curriculo,
                         int carga_hor_obrig_int, int cred_obrig_int, int carga_hor_opt_int, int cred_opt_int,
                         int carga_hor_comp_int, int cred_comp_int, double cra, double mc, double iea,
                         int per_int, int tranc, int mat_inst, int mob_estudantil, int cred_matriculados,
                         double media_geral_ingresso) {
        this.id_ingresso = id_ingresso;
        this.semestre_ingresso = semestre_ingresso;
        this.id_curso = id_curso;
        this.id_situacao = id_situacao;
        this.semestre_situacao = semestre_situacao;
        this.id_situacao_vinculo = id_situacao_vinculo;
        this.id_cota = id_cota;
        this.id_tipo_escola = id_tipo_escola;
        this.ano_conclusao_ensino_medio = ano_conclusao_ensino_medio;
        this.curriculo = curriculo;
        this.carga_hor_obrig_int = carga_hor_obrig_int;
        this.cred_obrig_int = cred_obrig_int;
        this.carga_hor_opt_int = carga_hor_opt_int;
        this.cred_opt_int = cred_opt_int;
        this.carga_hor_comp_int = carga_hor_comp_int;
        this.cred_comp_int = cred_comp_int;
        this.cra = cra;
        this.mc = mc;
        this.iea = iea;
        this.per_int = per_int;
        this.tranc = tranc;
        this.mat_inst = mat_inst;
        this.mob_estudantil = mob_estudantil;
        this.cred_matriculados = cred_matriculados;
        this.media_geral_ingresso = media_geral_ingresso;
    }

    public StudentCourse() {
    }

    public int getId_ingresso() {
        return id_ingresso;
    }

    public void setId_ingresso(int id_ingresso) {
        this.id_ingresso = id_ingresso;
    }

    public String getSemestre_ingresso() {
        return semestre_ingresso;
    }

    public void setSemestre_ingresso(String semestre_ingresso) {
        this.semestre_ingresso = semestre_ingresso;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_situacao() {
        return id_situacao;
    }

    public void setId_situacao(int id_situacao) {
        this.id_situacao = id_situacao;
    }

    public String getSemestre_situacao() {
        return semestre_situacao;
    }

    public void setSemestre_situacao(String semestre_situacao) {
        this.semestre_situacao = semestre_situacao;
    }

    public int getId_situacao_vinculo() {
        return id_situacao_vinculo;
    }

    public void setId_situacao_vinculo(int id_situacao_vinculo) {
        this.id_situacao_vinculo = id_situacao_vinculo;
    }

    public int getId_cota() {
        return id_cota;
    }

    public void setId_cota(int id_cota) {
        this.id_cota = id_cota;
    }

    public int getId_tipo_escola() {
        return id_tipo_escola;
    }

    public void setId_tipo_escola(int id_tipo_escola) {
        this.id_tipo_escola = id_tipo_escola;
    }

    public String getAno_conclusao_ensino_medio() {
        return ano_conclusao_ensino_medio;
    }

    public void setAno_conclusao_ensino_medio(String ano_conclusao_ensino_medio) {
        this.ano_conclusao_ensino_medio = ano_conclusao_ensino_medio;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public int getCarga_hor_obrig_int() {
        return carga_hor_obrig_int;
    }

    public void setCarga_hor_obrig_int(int carga_hor_obrig_int) {
        this.carga_hor_obrig_int = carga_hor_obrig_int;
    }

    public int getCred_obrig_int() {
        return cred_obrig_int;
    }

    public void setCred_obrig_int(int cred_obrig_int) {
        this.cred_obrig_int = cred_obrig_int;
    }

    public int getCarga_hor_opt_int() {
        return carga_hor_opt_int;
    }

    public void setCarga_hor_opt_int(int carga_hor_opt_int) {
        this.carga_hor_opt_int = carga_hor_opt_int;
    }

    public int getCred_opt_int() {
        return cred_opt_int;
    }

    public void setCred_opt_int(int cred_opt_int) {
        this.cred_opt_int = cred_opt_int;
    }

    public int getCarga_hor_comp_int() {
        return carga_hor_comp_int;
    }

    public void setCarga_hor_comp_int(int carga_hor_comp_int) {
        this.carga_hor_comp_int = carga_hor_comp_int;
    }

    public int getCred_comp_int() {
        return cred_comp_int;
    }

    public void setCred_comp_int(int cred_comp_int) {
        this.cred_comp_int = cred_comp_int;
    }

    public double getCra() {
        return cra;
    }

    public void setCra(double cra) {
        this.cra = cra;
    }

    public double getMc() {
        return mc;
    }

    public void setMc(double mc) {
        this.mc = mc;
    }

    public double getIea() {
        return iea;
    }

    public void setIea(double iea) {
        this.iea = iea;
    }

    public int getPer_int() {
        return per_int;
    }

    public void setPer_int(int per_int) {
        this.per_int = per_int;
    }

    public int getTranc() {
        return tranc;
    }

    public void setTranc(int tranc) {
        this.tranc = tranc;
    }

    public int getMat_inst() {
        return mat_inst;
    }

    public void setMat_inst(int mat_inst) {
        this.mat_inst = mat_inst;
    }

    public int getMob_estudantil() {
        return mob_estudantil;
    }

    public void setMob_estudantil(int mob_estudantil) {
        this.mob_estudantil = mob_estudantil;
    }

    public int getCred_matriculados() {
        return cred_matriculados;
    }

    public void setCred_matriculados(int cred_matriculados) {
        this.cred_matriculados = cred_matriculados;
    }

    public double getMedia_geral_ingresso() {
        return media_geral_ingresso;
    }

    public void setMedia_geral_ingresso(double media_geral_ingresso) {
        this.media_geral_ingresso = media_geral_ingresso;
    }

    @Override
    public String toString() {
        return id_ingresso + ":" + semestre_ingresso + ":" + id_curso + ":" + id_situacao + ":" + semestre_situacao +
                ":" + id_situacao_vinculo + ":" + id_cota + ":" + id_tipo_escola + ":" + ano_conclusao_ensino_medio +
                ":" + curriculo + ":" + carga_hor_obrig_int + ":" + cred_obrig_int + ":" + carga_hor_opt_int +
                ":" + cred_opt_int + ":" + carga_hor_comp_int + ":" + cred_comp_int + ":" + cra + ":" + mc + ":" +
                ":" + iea + ":" + per_int + ":" +tranc + ":" + mat_inst + ":" + mob_estudantil + ":" +
                ":" + cred_matriculados + ":" + media_geral_ingresso;
    }
}
