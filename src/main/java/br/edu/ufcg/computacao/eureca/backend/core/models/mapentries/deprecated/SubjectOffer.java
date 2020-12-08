package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;

public class SubjectOffer extends EurecaMapValue {
    int type_id;
    int department_id;

    public SubjectOffer(int type_id, int department_id) {
        this.type_id = type_id;
        this.department_id = department_id;
    }

    public SubjectOffer() {}

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return this.type_id + ":" + this.department_id;
    }
}
