package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseEnrollmentsGlossary implements Glossary {

    @Override
    public EnrollmentsGlossaryFields getGlossary() {
        Field subjects = new Field("Disciplinas", "");
        Field max = new Field("Número máximo", "");
        Field min = new Field("Número mínimo", "");
        Field averageClassesPerDiscipline = new Field("Média de turmas disciplina", "");
        Field averageClassesPerPeriod = new Field("Média de turmas período", "");
        Field averageEnrollmentsPerClass = new Field("Média de matrículas turma", "");
        Field averageEnrollmentsPerPeriod = new Field("Média de matrículas período", "");

        return new EnrollmentsGlossaryFields(subjects, max, min, averageClassesPerDiscipline, averageClassesPerPeriod,
                averageEnrollmentsPerClass, averageEnrollmentsPerPeriod);
    }
}
