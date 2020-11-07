package br.edu.ufcg.computacao.eureca.backend.core.models;

public class Subject extends EurecaMapValue {
    int tipo;
    int creditos;
    int horas;
    String nome;

    public Subject(int tipo, int creditos, int horas, String nome) {
        this.tipo = tipo;
        this.creditos = creditos;
        this.horas = horas;
        this.nome = nome;
    }

    public Subject() {
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo + ":" + creditos + ":" + horas + ":" + nome;
    }
}
