package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class EnrolmentCode extends EurecaMapKey {
    int id_turma;

    public EnrolmentCode(int id_turma) {
        this.id_turma = id_turma;
    }

    public EnrolmentCode() {}

    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    @Override
    public int hashCode() {
        return (int) this.id_turma;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        EnrolmentCode other = (EnrolmentCode) obj;
        if (this.id_turma != other.id_turma) return false;
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id_turma);
    }
}
