package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class EnrolmentCode extends EurecaMapKey {
    int enrollment_id;

    public EnrolmentCode(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    public EnrolmentCode() {}

    public int getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    @Override
    public int hashCode() {
        return (int) this.enrollment_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        EnrolmentCode other = (EnrolmentCode) obj;
        if (this.enrollment_id != other.enrollment_id) return false;
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(this.enrollment_id);
    }
}
