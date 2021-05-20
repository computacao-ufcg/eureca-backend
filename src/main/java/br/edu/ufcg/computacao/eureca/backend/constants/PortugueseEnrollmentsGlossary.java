package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseEnrollmentsGlossary {

    public EnrollmentsGlossaryFields getGlossary() {
        Field subjects = new Field("subjects", "");
        Field max = new Field("max", "");
        Field min = new Field("min", "");
        Field averageClassesPerDiscipline = new Field("averageClassesPerDiscipline", "");
        Field averageClassesPerPeriod = new Field("averageClassesPerPeriod", "");
        Field averageEnrollmentsPerClass = new Field("averageEnrollmentsPerClass", "");
        Field averageEnrollmentsPerPeriod = new Field("", "averageEnrollmentsPerPeriod");

        return new EnrollmentsGlossaryFields(subjects, max, min, averageClassesPerDiscipline, averageClassesPerPeriod,
                averageEnrollmentsPerClass, averageEnrollmentsPerPeriod);
    }
}
