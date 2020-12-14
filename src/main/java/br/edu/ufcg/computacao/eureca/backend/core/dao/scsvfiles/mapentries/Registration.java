package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

public class Registration extends EurecaMapKey implements Comparable {
    String registration;

    public Registration(String registration) {
        this.registration = registration;
    }

    public Registration() {}

    public String getRegistration() {
        return registration;
    }

    public void setValue(String registration) {
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

    @Override
    public int compareTo(Object o) {
        Registration other = (Registration) o;
        String parsedRegistration = getParsedRegistration(this.getRegistration());
        String otherParsedRegistration = getParsedRegistration(other.getRegistration());
        return parsedRegistration.compareTo(otherParsedRegistration);
    }

    private String getParsedRegistration(String registration) {
        String year = registration.substring(1,3);
        if (year.compareTo("50") >= 0) {
            return registration.substring(0,1) + "19" + registration.substring(1,9);
        } else {
            return registration.substring(0,1) + "20" + registration.substring(1,9);
        }
    }
}
