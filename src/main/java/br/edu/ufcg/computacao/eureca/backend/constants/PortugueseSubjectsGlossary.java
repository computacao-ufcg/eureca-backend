package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseSubjectsGlossary implements Glossary {

    @Override
    public SubjectsGlossaryFields getGlossary() {
        Field mandatory = new Field("Obrigatórias", "Termo usado para se referir a disciplinas obrigatórias.");
        Field optional = new Field("Optativas", "Termo usado para se referir a disciplinas optativas.");
        Field elective = new Field("Eletivas", "Termo usado para se referir a disciplinas eletivas.");
        Field complementary = new Field("Complementares", "Termo usado para se referir a disciplinas complementares.");

        return new SubjectsGlossaryFields(mandatory, optional, elective, complementary);
    }
}
