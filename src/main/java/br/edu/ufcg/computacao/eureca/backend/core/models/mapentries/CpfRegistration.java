package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class CpfRegistration extends EurecaMapKey {
    String national_id;
    String registration;

    public CpfRegistration(String national_id, String registration) {
        this.national_id = national_id;
        this.registration = registration;
    }

    public CpfRegistration() {}

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.national_id == null) ? 0 : this.national_id.hashCode())
                + ((this.registration == null) ? 0 : this.registration.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CpfRegistration other = (CpfRegistration) obj;
        if (this.registration == null) {
            if (other.getRegistration() != null) return false;
        } else if (this.national_id == null) {
            if (other.getNational_id() != null) return false;
        } else if (!this.national_id.equals(other.getNational_id()) ||
                    !this.registration.equals(other.getRegistration())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.national_id + ":" + this.registration;
    }
}
