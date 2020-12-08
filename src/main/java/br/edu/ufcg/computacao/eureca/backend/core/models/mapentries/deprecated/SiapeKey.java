package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapKey;

public class SiapeKey extends EurecaMapKey {
    String institutional_id;

    public String getInstitutional_id() {
        return institutional_id;
    }

    public void setInstitutional_id(String institutional_id) {
        this.institutional_id = institutional_id;
    }

    public SiapeKey(String institutional_id) {
        this.institutional_id = institutional_id;
    }

    public SiapeKey() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.institutional_id == null) ? 0 : this.institutional_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SiapeKey other = (SiapeKey) obj;
        if (this.institutional_id == null) {
            if (other.getInstitutional_id() != null) return false;
        } else if (!this.institutional_id.equals(other.getInstitutional_id())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.institutional_id;
    }
}
