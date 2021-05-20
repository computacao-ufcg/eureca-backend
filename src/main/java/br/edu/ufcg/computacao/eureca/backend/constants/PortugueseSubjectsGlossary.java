package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseSubjectsGlossary {

    public SubjectsGlossaryFields getGlossary() {
        Field mandatory = new Field("mandatory", "");
        Field optional = new Field("optional", "");
        Field elective = new Field("elective", "");
        Field complementary = new Field("complementary", "");

        return new SubjectsGlossaryFields(mandatory, optional, elective, complementary);
    }
}
