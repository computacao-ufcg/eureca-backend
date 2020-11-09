package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class CourseCode extends EurecaMapKey {
    long codigo_disciplina;

    public CourseCode(long codigo_disciplina) {
        this.codigo_disciplina = codigo_disciplina;
    }

    public CourseCode() {}

    public long getCodigo_disciplina() {
        return codigo_disciplina;
    }

    public void setCodigo_disciplina(int codigo_disciplina) {
        this.codigo_disciplina = codigo_disciplina;
    }

    @Override
    public int hashCode() {
        return (int) this.codigo_disciplina;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CourseCode other = (CourseCode) obj;
        if (this.codigo_disciplina != other.codigo_disciplina) return false;
        return true;
    }

    @Override
    public String toString() {
        return Long.toString(this.codigo_disciplina);
    }
}
