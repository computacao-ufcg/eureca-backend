package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseGlossary implements Glossary {
    public GlossaryFields getGlossary() {
        Field active = new Field("Ativo", "Descrição");
        Field delayed = new Field("Retido", "Descrição");
        Field dropout = new Field("Evadido", "Descrição");
        Field alumnus = new Field("Egresso", "Descrição");
        Field averageRisk = new Field("Risco Médio", "Descrição");
        Field averageLoad = new Field("Carga Média", "Descrição");
        Field successRate = new Field("Taxa de Sucesso", "Descrição");
        Field predictedGraduation = new Field("Previsão de Conclusão", "Descrição");
        Field averageCost = new Field("Custo Médio", "Descrição");
        Field averageTime = new Field("Tempo Médio", "Descrição");
        Field minimumAlumni = new Field("Mínimo", "Descrição");
        Field maximumAlumni = new Field("Máximo", "Descrição");
        Field averageAlumni = new Field("Média", "Descrição");
        Field averageGpa = new Field("CRA Médio", "Descrição");
        Field rejoinCount = new Field("Reingressos", "Descrição");
        Field canceledCount = new Field("Cancelamentos", "Descrição");
        Field abandonCount = new Field("Abandonos", "Descrição");
        Field transferCount = new Field("Transferências", "Descrição");

        return new GlossaryFields(active, delayed, dropout, alumnus, averageRisk, averageLoad, successRate,
                predictedGraduation, averageCost, averageTime, minimumAlumni, maximumAlumni, averageAlumni,
                averageGpa, rejoinCount, canceledCount, abandonCount, transferCount);
    }
}
