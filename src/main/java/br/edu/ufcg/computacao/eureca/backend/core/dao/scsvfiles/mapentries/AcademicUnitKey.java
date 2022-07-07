package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Objects;

public class AcademicUnitKey implements EurecaMapKey {
    private String code;

    public AcademicUnitKey(String code) {
        this.code = code;
    }

    public AcademicUnitKey() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcademicUnitKey that = (AcademicUnitKey) o;
        return getCode().equals(that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
}
