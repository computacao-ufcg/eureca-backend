package br.edu.ufcg.computacao.eureca.backend.core.models;

public class CpfRegistrationKey {
    private String nationalId;
    private String registration;

    public CpfRegistrationKey(String nationalId, String registration) {
        this.nationalId = nationalId;
        this.registration = registration;
    }

    public CpfRegistrationKey(CpfRegistrationKey key) {
        this.nationalId = key.getNationalId();
        this.registration = key.getRegistration();
    }

    public CpfRegistrationKey() {}

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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
        result = prime * result + ((this.nationalId == null) ? 0 : this.nationalId.hashCode())
                + ((this.registration == null) ? 0 : this.registration.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CpfRegistrationKey other = (CpfRegistrationKey) obj;
        if (this.registration == null) {
            if (other.getRegistration() != null) return false;
        } else if (this.nationalId == null) {
            if (other.getNationalId() != null) return false;
        } else if (!this.nationalId.equals(other.getNationalId()) ||
                    !this.registration.equals(other.getRegistration())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.nationalId + ":" + this.registration;
    }
}