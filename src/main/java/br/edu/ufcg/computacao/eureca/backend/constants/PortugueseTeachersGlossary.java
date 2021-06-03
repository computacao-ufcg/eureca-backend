package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseTeachersGlossary {

    public TeachersGlossaryFields getGlossary() {
        Field failedDueToAbsences =  new Field("Reprovações por falta", "");
        Field failedDueToGrade = new Field("Reprovações por nota", "");;
        Field failedDueToCanceling = new Field("Trancamentos", "");;
        Field success = new Field("Taxa de Sucesso", "");
        Field min = new Field("Número mínimo", "");
        Field max = new Field("Número máximo", "");
        Field total = new Field("Total", "");

        return new TeachersGlossaryFields(failedDueToAbsences, failedDueToGrade, failedDueToCanceling, success, min, max, total);
    }
}
