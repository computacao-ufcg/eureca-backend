package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StudentDeficiency extends EurecaMultivaluedMapValue {
    Collection<Deficiency> deficiencyCode;

    public StudentDeficiency(Collection<Deficiency> deficiencyCode) {
        this.deficiencyCode = deficiencyCode;
    }

    public StudentDeficiency() {
        this.deficiencyCode = new ArrayList<>();
    }

    @Override
    public Collection<Deficiency> getCollection() {
        return this.deficiencyCode;
    }
    
    public Collection<Deficiency> getDeficiencyCode() {
        return deficiencyCode;
    }

    public void setDeficiencyCode(Collection<Deficiency> deficiencyCode) {
        this.deficiencyCode = deficiencyCode;
    }
}
