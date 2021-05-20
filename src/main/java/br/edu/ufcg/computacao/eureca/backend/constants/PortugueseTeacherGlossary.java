package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseTeacherGlossary {

    public TeachersGlossaryFields getGlossary() {
        Field failedDueToAbsences =  new Field("failedDueToAbsences", "");
        Field failedDueToGrade = new Field("failedDueToGrade", "");;
        Field failedDueToCanceling = new Field("failedDueToCanceling", "");;
        Field success = new Field("success", "");
        Field min = new Field("min", "");
        Field max = new Field("max", "");
        Field total = new Field("total", "");

        return new TeachersGlossaryFields(failedDueToAbsences, failedDueToGrade, failedDueToCanceling, success, min, max, total);
    }
}
