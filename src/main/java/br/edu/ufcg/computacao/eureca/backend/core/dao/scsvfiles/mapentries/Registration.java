package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class Registration extends EurecaMapKey {
    String registration;

    public Registration(String registration) {
        this.registration = registration;
    }

    public Registration() {}

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
        result = prime * result + ((this.registration == null) ? 0 : this.registration.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Registration other = (Registration) obj;
        if (this.registration == null) {
            if (other.getRegistration() != null) return false;
        } else if (!this.registration.equals(other.getRegistration())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.registration;
    }
}
