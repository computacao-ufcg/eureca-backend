package br.edu.ufcg.computacao.eureca.backend.core.models.abstractions;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.StudentData;

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
