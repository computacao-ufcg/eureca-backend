package br.edu.ufcg.computacao.eureca.backend.core.models.abstractions;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.StudentCourse;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.StudentPersonalData;

public class Student {
    private CpfRegistration id;
    private StudentPersonalData personalData;
    private StudentCourse academicData;

    public Student(CpfRegistration id, StudentPersonalData personalData, StudentCourse academicData) {
        this.id = id;
        this.personalData = personalData;
        this.academicData = academicData;
    }

    public CpfRegistration getId() {
        return id;
    }

    public StudentPersonalData getPersonalData() {
        return personalData;
    }

    public StudentCourse getAcademicData() {
        return academicData;
    }
}
