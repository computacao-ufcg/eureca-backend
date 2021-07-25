package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseTeachersGlossary implements Glossary {

    public TeachersGlossaryFields getGlossary() {
        Field failedDueToAbsences =  new Field("Reprovações/falta", "Média de reprovações por falta.");
        Field failedDueToGrade = new Field("Reprovações/nota", "Média de reprovações por nota.");;
        Field failedDueToCanceling = new Field("Trancamentos", "Média de trancamentos.");;
        Field success = new Field("Taxa de Sucesso", "Essa métrica é calculada como a razão entre o número" +
                "de créditos integralizados até o momento e o número agregado de créditos matriculados.");
        Field min = new Field("Número mínimo", "Número mínimo de docentes por período considerando todos" +
                "os períodos desde o início da operação do curso.");
        Field max = new Field("Número máximo", "Número máximo de docentes por período considerando todos" +
                "os períodos desde o início da operação do curso.");
        Field total = new Field("Total", "");

        return new TeachersGlossaryFields(failedDueToAbsences, failedDueToGrade, failedDueToCanceling, success, min, max, total);
    }
}
