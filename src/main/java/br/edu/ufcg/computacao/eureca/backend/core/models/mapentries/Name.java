package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class Name extends EurecaMapValue {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Name(String name) {
        this.name = name;
    }

    public Name() {}

    @Override
    public String toString() {
        return this.name;
    }
}
