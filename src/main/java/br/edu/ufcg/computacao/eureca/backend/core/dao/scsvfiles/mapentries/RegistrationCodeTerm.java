package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class RegistrationCodeTerm extends EurecaMapKey {
    private String registration;
    private String code;
    private String term;

    public RegistrationCodeTerm(String registration, String code, String term) {
        this.registration = registration;
        this.code = code;
        this.term = term;
    }

    public RegistrationCodeTerm() {
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationCodeTerm that = (RegistrationCodeTerm) o;
        return Objects.equals(getRegistration(), that.getRegistration()) &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getTerm(), that.getTerm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistration(), getCode(), getTerm());
    }

    @Override
    public String toString() {
        return this.registration + ':' + this.code + ':' + this.term;
    }
}
