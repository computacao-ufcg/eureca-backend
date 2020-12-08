package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;

public class Description extends EurecaMapValue {
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Description(String description) {
        this.description = description;
    }

    public Description() {}

    @Override
    public String toString() {
        return this.description;
    }
}
