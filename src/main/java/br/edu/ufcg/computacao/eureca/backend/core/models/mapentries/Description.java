package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class Description extends EurecaMapValue {
    String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Description(String descricao) {
        this.descricao = descricao;
    }

    public Description() {}

    @Override
    public String toString() {
        return this.descricao;
    }
}
