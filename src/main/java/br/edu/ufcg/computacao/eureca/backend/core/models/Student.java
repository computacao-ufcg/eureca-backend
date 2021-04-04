package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;

public class Student implements Comparable {
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

    public RiskClass computeRiskClass() {
        Metrics studentMetrics = MetricsCalculator.computeMetrics(this.getStudentData());
        return MetricsCalculator.computeRiskClass(studentMetrics.getRisk());
    }

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        return (new Registration(this.getId().getRegistration())).
                compareTo((new Registration(other.getId().getRegistration())));
    }
}
