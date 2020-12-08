package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;

public class SubjectData extends EurecaMapValue {
    int type_id;
    int credits;
    int hours;
    String name;

    public SubjectData(int type_id, int credits, int hours, String name) {
        this.type_id = type_id;
        this.credits = credits;
        this.hours = hours;
        this.name = name;
    }

    public SubjectData() {
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return type_id + ":" + credits + ":" + hours + ":" + name;
    }
}
