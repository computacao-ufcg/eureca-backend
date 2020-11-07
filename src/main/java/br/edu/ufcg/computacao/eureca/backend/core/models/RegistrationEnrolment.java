package br.edu.ufcg.computacao.eureca.backend.core.models;

public class RegistrationEnrolment extends EurecaMapKey {
    String matricula;
    int id_turma;

    public RegistrationEnrolment(String matricula, int id_turma) {
        this.matricula = matricula;
        this.id_turma = id_turma;
    }

    public RegistrationEnrolment() {}

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.id_turma + ((this.matricula == null) ? 0 : this.matricula.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RegistrationEnrolment other = (RegistrationEnrolment) obj;
        if (this.id_turma != other.getId_turma()) {
            return false;
        } else if (this.matricula == null) {
            if (other.getMatricula() != null) return false;
        } else if (!this.matricula.equals(other.getMatricula())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.matricula + ":" + this.id_turma;
    }
}
