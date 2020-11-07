package br.edu.ufcg.computacao.eureca.backend.core.models;

public class Name extends EurecaMapValue {
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Name(String nome) {
        this.nome = nome;
    }

    public Name() {}

    @Override
    public String toString() {
        return this.nome;
    }
}
