package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class RegistrationSubjectCodeTermKey implements EurecaMapKey {
    private String registration;
    private String subjectCode;
    private String term;

    public RegistrationSubjectCodeTermKey(String registration, String subjectCode, String term) {
        this.registration = registration;
        this.subjectCode = subjectCode;
        this.term = term;
    }

    public RegistrationSubjectCodeTermKey() {
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegistrationSubjectCodeTermKey that = (RegistrationSubjectCodeTermKey) o;
        return Objects.equals(getRegistration(), that.getRegistration()) &&
                Objects.equals(getSubjectCode(), that.getSubjectCode()) &&
                Objects.equals(getTerm(), that.getTerm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistration(), getSubjectCode(), getTerm());
    }

    @Override
    public String toString() {
        return this.registration + ':' + this.subjectCode + ':' + this.term;
    }
}
