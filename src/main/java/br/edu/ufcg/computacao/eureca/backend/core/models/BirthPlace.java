package br.edu.ufcg.computacao.eureca.backend.core.models;

public class BirthPlace extends EurecaMapValue {
    String municipio;
    String estado;

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BirthPlace(String municipio, String estado) {
        this.municipio = municipio;
        this.estado = estado;
    }

    public BirthPlace() {}

    @Override
    public String toString() {
        return this.municipio + ":" + this.estado;
    }
}
