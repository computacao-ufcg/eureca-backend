package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapKey;

public class IdCode extends EurecaMapKey {
    int id;

    public IdCode(int id) {
        this.id = id;
    }

    public IdCode() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        IdCode other = (IdCode) obj;
        if (this.id != other.id) return false;
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id);
    }
}
