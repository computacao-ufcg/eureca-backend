package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;

public class Student {
    private CpfRegistration id;
    private StudentData data;

    public Student(CpfRegistration id, StudentData data) {
        this.id = id;
        this.data = data;
    }

    public CpfRegistration getId() {
        return id;
    }

    public StudentData getStudentData() {
        return data;
    }
}
