package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;

public class Disability extends EurecaMapValue {
    int disability_id;

    public int getDisability_id() {
        return disability_id;
    }

    public void setDisability_id(int disability_id) {
        this.disability_id = disability_id;
    }

    public Disability(int disability_id) {
        this.disability_id = disability_id;
    }

    public Disability() {}

    @Override
    public String toString() {
        return Integer.toString(this.disability_id);
    }
}
