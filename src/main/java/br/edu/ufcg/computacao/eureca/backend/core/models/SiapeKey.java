package br.edu.ufcg.computacao.eureca.backend.core.models;

public class SiapeKey extends EurecaMapKey {
    String siape;

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public SiapeKey(String siape) {
        this.siape = siape;
    }

    public SiapeKey() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.siape == null) ? 0 : this.siape.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SiapeKey other = (SiapeKey) obj;
        if (this.siape == null) {
            if (other.getSiape() != null) return false;
        } else if (!this.siape.equals(other.getSiape())) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.siape;
    }
}
