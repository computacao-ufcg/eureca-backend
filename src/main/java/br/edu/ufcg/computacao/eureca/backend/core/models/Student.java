package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;
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

    public RiskClass getRiskClass() {
        Metrics studentMetrics = MetricsCalculator.getInstance().computeMetrics(this);
        double feasibility = studentMetrics.getFeasibility();
        if (feasibility > 1) return RiskClass.UNFEASIBLE;
        if (feasibility < 0) return RiskClass.NOT_APPLICABLE;
        double risk = studentMetrics.getRisk();
        if (risk > 0) return RiskClass.CRITICAL;
        double highRiskThreshold = (1.0 * (Curriculum.EXPECTED_NUMBER_OF_TERMS - Curriculum.MAX_NUMBER_OF_TERMS)) /
                (Curriculum.MAX_NUMBER_OF_TERMS - 1);
        if (risk >= highRiskThreshold) return RiskClass.LATE;
        double lowRiskThreshold = (1.0 * (Curriculum.MIN_NUMBER_OF_TERMS - Curriculum.MAX_NUMBER_OF_TERMS)) /
                (Curriculum.MAX_NUMBER_OF_TERMS - 1);
        if (risk >= lowRiskThreshold) return RiskClass.NORMAL;
        return RiskClass.ADVANCED;
    }

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        return (new Registration(this.getId().getRegistration())).
                compareTo((new Registration(other.getId().getRegistration())));
    }
}
