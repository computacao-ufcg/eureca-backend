package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMultivaluedMapValue;

import java.util.ArrayList;
import java.util.Collection;

public class EnrolmentTeacher extends EurecaMultivaluedMapValue {
    Collection<SiapeValue> teachers;

    public EnrolmentTeacher(Collection<SiapeValue> teachers) {
        this.teachers = teachers;
    }

    public EnrolmentTeacher() {
        this.teachers = new ArrayList<>();
    }

    @Override
    public Collection<SiapeValue> getCollection() {
        return this.teachers;
    }
    
    public Collection<SiapeValue> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<SiapeValue> teachers) {
        this.teachers = teachers;
    }
}
