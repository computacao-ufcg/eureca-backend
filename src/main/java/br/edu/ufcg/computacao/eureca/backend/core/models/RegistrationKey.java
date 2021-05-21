package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Objects;

public class RegistrationKey implements Comparable {
    private String registration;

    public RegistrationKey(String registration) {
        this.registration = registration;
    }

    public RegistrationKey() {}

    public String getRegistration() {
        return registration;
    }

    public void setValue(String registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationKey that = (RegistrationKey) o;
        return Objects.equals(getRegistration(), that.getRegistration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistration());
    }

    @Override
    public String toString() {
        return this.registration;
    }

    @Override
    public int compareTo(Object o) {
        RegistrationKey other = (RegistrationKey) o;
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
