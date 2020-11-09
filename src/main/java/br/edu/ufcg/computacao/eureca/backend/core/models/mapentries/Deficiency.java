package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class Deficiency extends EurecaMapValue {
    int id_deficiencia;

    public int getId_deficiencia() {
        return id_deficiencia;
    }

    public void setId_deficiencia(int id_deficiencia) {
        this.id_deficiencia = id_deficiencia;
    }

    public Deficiency(int id_deficiencia) {
        this.id_deficiencia = id_deficiencia;
    }

    public Deficiency() {}

    @Override
    public String toString() {
        return Integer.toString(this.id_deficiencia);
    }
}
