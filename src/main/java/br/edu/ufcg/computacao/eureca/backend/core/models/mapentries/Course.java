package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class Course extends EurecaMapValue {
    int id_tipo_disciplina;
    int id_unidade_academica;

    public Course(int id_tipo_disciplina, int id_unidade_academica) {
        this.id_tipo_disciplina = id_tipo_disciplina;
        this.id_unidade_academica = id_unidade_academica;
    }

    public Course() {}

    public int getId_tipo_disciplina() {
        return id_tipo_disciplina;
    }

    public void setId_tipo_disciplina(int id_tipo_disciplina) {
        this.id_tipo_disciplina = id_tipo_disciplina;
    }

    public int getId_unidade_academica() {
        return id_unidade_academica;
    }

    public void setId_unidade_academica(int id_unidade_academica) {
        this.id_unidade_academica = id_unidade_academica;
    }

    @Override
    public String toString() {
        return this.id_tipo_disciplina + ":" + this.id_unidade_academica;
    }
}
