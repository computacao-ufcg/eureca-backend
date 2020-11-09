package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

import java.util.ArrayList;
import java.util.Collection;

public class Absence extends EurecaMultivaluedMapValue {
    Collection<Integer> lecture;

    public Absence(Collection<Integer> lecture) {
        this.lecture = lecture;
    }

    public Absence() {
        this.lecture = new ArrayList<>();
    }

    @Override
    public Collection<Integer> getCollection() {
        return this.lecture;
    }
    
    public Collection<Integer> getLecture() {
        return lecture;
    }

    public void setLecture(Collection<Integer> lecture) {
        this.lecture = lecture;
    }
}
