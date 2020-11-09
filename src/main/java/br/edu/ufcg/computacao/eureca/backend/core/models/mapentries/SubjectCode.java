package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class SubjectCode extends EurecaMapKey {
    String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public SubjectCode(String codigo) {
        this.codigo = codigo;
    }

    public SubjectCode() {}

    @Override
    public String toString() {
        return this.codigo;
    }
}
