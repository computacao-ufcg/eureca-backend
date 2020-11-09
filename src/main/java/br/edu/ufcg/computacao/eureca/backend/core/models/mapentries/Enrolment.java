package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class Enrolment extends EurecaMapValue {
    int id_disciplina;
    int turma;
    String periodo;
    int id_horario;
    int id_sala;

    public Enrolment(int id_disciplina, int turma, String periodo, int id_horario, int id_sala) {
        this.id_disciplina = id_disciplina;
        this.turma = turma;
        this.periodo = periodo;
        this.id_horario = id_horario;
        this.id_sala = id_sala;
    }

    public Enrolment() {}

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    @Override
    public String toString() {
        return this.id_disciplina + ":" + this.turma + ":" + this.periodo + ":" + this.id_horario + ":" + this.id_sala;
    }
}
