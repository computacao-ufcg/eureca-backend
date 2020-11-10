package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class Cpf extends EurecaMapKey {
    String national_id;

    public Cpf(String national_id) {
        this.national_id = national_id;
    }

    public Cpf() {}

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.national_id == null) ? 0 : this.national_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Cpf other = (Cpf) obj;
        if (this.national_id == null) {
            if (other.getNational_id() != null) return false;
        } else if (!this.national_id.equals(other.getNational_id())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.national_id;
    }
}
