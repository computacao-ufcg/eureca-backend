package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;

public class BirthPlace extends EurecaMapValue {
    String city;
    String state;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BirthPlace(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public BirthPlace() {}

    @Override
    public String toString() {
        return this.city + ":" + this.state;
    }
}
