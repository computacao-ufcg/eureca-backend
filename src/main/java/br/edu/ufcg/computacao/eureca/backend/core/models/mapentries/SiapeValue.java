package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class SiapeValue extends EurecaMapValue {
    String siape;

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public SiapeValue(String siape) {
        this.siape = siape;
    }

    public SiapeValue() {}

    @Override
    public String toString() {
        return this.siape;
    }
}
