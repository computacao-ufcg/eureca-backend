package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class CpfRegistration extends EurecaMapKey {
    String cpf;
    String matricula;

    public CpfRegistration(String cpf, String matricula) {
        this.cpf = cpf;
        this.matricula = matricula;
    }

    public CpfRegistration() {}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.cpf == null) ? 0 : this.cpf.hashCode())
                + ((this.matricula == null) ? 0 : this.matricula.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CpfRegistration other = (CpfRegistration) obj;
        if (this.matricula == null) {
            if (other.getMatricula() != null) return false;
        } else if (this.cpf == null) {
            if (other.getCpf() != null) return false;
        } else if (!this.cpf.equals(other.getCpf()) ||
                    !this.matricula.equals(other.getMatricula())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.cpf + ":" + this.matricula;
    }
}
