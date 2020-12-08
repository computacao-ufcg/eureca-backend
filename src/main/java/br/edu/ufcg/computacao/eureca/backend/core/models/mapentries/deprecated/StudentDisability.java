package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMultivaluedMapValue;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated.Disability;

import java.util.ArrayList;
import java.util.Collection;

public class StudentDisability extends EurecaMultivaluedMapValue {
    Collection<Disability> disability_id;

    public StudentDisability(Collection<Disability> disability_id) {
        this.disability_id = disability_id;
    }

    public StudentDisability() {
        this.disability_id = new ArrayList<>();
    }

    @Override
    public Collection<Disability> getCollection() {
        return this.disability_id;
    }
    
    public Collection<Disability> getDisability_id() {
        return disability_id;
    }

    public void setDisability_id(Collection<Disability> disability_id) {
        this.disability_id = disability_id;
    }
}
