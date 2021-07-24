package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseEnrollmentsGlossary implements Glossary {

    @Override
    public EnrollmentsGlossaryFields getGlossary() {
        Field subjects = new Field("Disciplinas", "");
        Field max = new Field("Número máximo", "Número máximo de matrículas por período considerando todos"+
                "os períodos desde o início da operação do curso.");
        Field min = new Field("Número mínimo", "Número mínimo de matrículas por período considerando todos"+
                "os períodos desde o início da operação do curso.");
        Field averageClassesPerDiscipline = new Field("Média turmas/disciplina", "Média de turmas por disciplina.");
        Field averageClassesPerPeriod = new Field("Média de turmas/período", "Média de turmas por período.");
        Field averageEnrollmentsPerClass = new Field("Média de matrículas/turma", "Média de matrículas por turma.");
        Field averageEnrollmentsPerPeriod = new Field("Média de matrículas/período", "Média de matrículas por período.");

        return new EnrollmentsGlossaryFields(subjects, max, min, averageClassesPerDiscipline, averageClassesPerPeriod,
                averageEnrollmentsPerClass, averageEnrollmentsPerPeriod);
    }
}
