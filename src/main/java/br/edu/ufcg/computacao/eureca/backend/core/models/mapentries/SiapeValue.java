package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class SiapeValue extends EurecaMapValue {
    String institutional_id;

    public String getInstitutional_id() {
        return institutional_id;
    }

    public void setInstitutional_id(String institutional_id) {
        this.institutional_id = institutional_id;
    }

    public SiapeValue(String institutional_id) {
        this.institutional_id = institutional_id;
    }

    public SiapeValue() {}

    @Override
    public String toString() {
        return this.institutional_id;
    }
}
