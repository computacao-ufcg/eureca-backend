package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseSubjectsGlossary implements Glossary {

    @Override
    public SubjectsGlossaryFields getGlossary() {
        Field mandatory = new Field("Obrigatórias", "");
        Field optional = new Field("Optativas", "");
        Field elective = new Field("Eletivas", "");
        Field complementary = new Field("Complementares", "");

        return new SubjectsGlossaryFields(mandatory, optional, elective, complementary);
    }
}
