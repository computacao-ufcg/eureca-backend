package br.edu.ufcg.computacao.eureca.backend.constants;

public class EnrollmentsGlossaryFields {
    Field subjects;
    Field max;
    Field min ;
    Field averageClassesPerDiscipline;
    Field averageClassesPerPeriod;
    Field averageEnrollmentsPerClass;
    Field averageEnrollmentsPerPeriod;

    public EnrollmentsGlossaryFields(Field subjects,
                                     Field max,
                                     Field min,
                                     Field averageClassesPerDiscipline,
                                     Field averageClassesPerPeriod,
                                     Field averageEnrollmentsPerClass,
                                     Field averageEnrollmentsPerPeriod) {
        this.subjects = subjects;
        this.max = max;
        this.min = min;
        this.averageClassesPerDiscipline = averageClassesPerDiscipline;
        this.averageClassesPerPeriod = averageClassesPerPeriod;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
        this.averageEnrollmentsPerPeriod = averageEnrollmentsPerPeriod;
    }

    public Field getSubjects() {
        return subjects;
    }

    public void setSubjects(Field subjects) {
        this.subjects = subjects;
    }

    public Field getMax() {
        return max;
    }

    public void setMax(Field max) {
        this.max = max;
    }

    public Field getMin() {
        return min;
    }

    public void setMin(Field min) {
        this.min = min;
    }

    public Field getAverageClassesPerDiscipline() {
        return averageClassesPerDiscipline;
    }

    public void setAverageClassesPerDiscipline(Field averageClassesPerDiscipline) {
        this.averageClassesPerDiscipline = averageClassesPerDiscipline;
    }

    public Field getAverageClassesPerPeriod() {
        return averageClassesPerPeriod;
    }

    public void setAverageClassesPerPeriod(Field averageClassesPerPeriod) {
        this.averageClassesPerPeriod = averageClassesPerPeriod;
    }

    public Field getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public void setAverageEnrollmentsPerClass(Field averageEnrollmentsPerClass) {
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }

    public Field getAverageEnrollmentsPerPeriod() {
        return averageEnrollmentsPerPeriod;
    }

    public void setAverageEnrollmentsPerPeriod(Field averageEnrollmentsPerPeriod) {
        this.averageEnrollmentsPerPeriod = averageEnrollmentsPerPeriod;
    }
}
