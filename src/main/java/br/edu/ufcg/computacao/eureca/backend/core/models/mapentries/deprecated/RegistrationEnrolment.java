package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapKey;

public class RegistrationEnrolment extends EurecaMapKey {
    String registration;
    int enrollment_id;

    public RegistrationEnrolment(String registration, int enrollment_id) {
        this.registration = registration;
        this.enrollment_id = enrollment_id;
    }

    public RegistrationEnrolment() {}

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.enrollment_id + ((this.registration == null) ? 0 : this.registration.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RegistrationEnrolment other = (RegistrationEnrolment) obj;
        if (this.enrollment_id != other.getEnrollment_id()) {
            return false;
        } else if (this.registration == null) {
            if (other.getRegistration() != null) return false;
        } else if (!this.registration.equals(other.getRegistration())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.registration + ":" + this.enrollment_id;
    }
}
