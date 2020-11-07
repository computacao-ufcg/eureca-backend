package br.edu.ufcg.computacao.eureca.backend.core.models;

public class Cpf extends EurecaMapKey {
    String cpf;

    public Cpf(String cpf) {
        this.cpf = cpf;
    }

    public Cpf() {}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.cpf == null) ? 0 : this.cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Cpf other = (Cpf) obj;
        if (this.cpf == null) {
            if (other.getCpf() != null) return false;
        } else if (!this.cpf.equals(other.getCpf())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.cpf;
    }
}
