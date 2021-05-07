package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseGlossary implements Glossary {
    public GlossaryFields getGlossary() {
        Field active = new Field("Ativo", "Discente regularmente matriculado.");
        Field delayed = new Field("Retido", "Discente com risco de evadir. Esses são os discentes na classe de risco 'Alto' ou 'Inviável'.");
        Field dropout = new Field("Evadido", "Discente que foi desvinculado antes de graduar.");
        Field alumnus = new Field("Egresso", "Discente graduado.");
        Field averageRisk = new Field("Risco", "Essa métrica indica o risco do discente evadir " +
                "ou a média desse risco para um grupo de discentes. Ela pode assumir os valores 'Inexistente', " +
                "'Baixo', 'Médio', 'Alto' ou 'Inviável'. Discentes com risco 'Alto' ou 'Inviável' são considerados "+
                "retidos. Essa métrica pode assumir dois outros valores em situações especiais: 'Não aplicável', " +
                "quando os dados do dicente são insuficientes para calcular seu risco; e 'Inexato', quando os dados " +
                "do discente não estão corretos.");
        Field averageLoad = new Field("Carga", "Essa métrica indica a quantidade média de " +
                "créditos por período que foram cursados por um discente ou a média dessa carga para um grupo de " +
                "discentes.");
        Field successRate = new Field("Taxa de Sucesso", "Essa métrica é calculada como a razão entre " +
                "o número de créditos integralizados até o momento e o número agregado de créditos matriculados.");
        Field predictedGraduation = new Field("Previsão de Conclusão", "Essa métrica é uma previsão " +
                "do número de períodos que um discente irá levar para concluir o curso, ou a média dessa previsão " +
                "para um grupo de discentes.");
        Field averageCost = new Field("Custo", "Esta métrica dá uma ideia do custo de um discente ou " +
                "a média desse custo para um grupo de discentes. Ela pode assumir os valores 'Adequado', 'Regular', " +
                "'Alto', 'Muito alto' ou 'Inaceitável'. Essa métrica pode assumir ainda dois outros valores em situações " +
                "especiais: 'Não aplicável', quando os dados do dicente são insuficientes para calcular seu custo; " +
                "e 'Inexato', quando os dados do discente não estão corretos.");
        Field averageTime = new Field("Tempo Médio", "Essa métrica indica o tempo médio de " +
                "permanência de um grupo de discentes no curso.");
        Field minimumAlumni = new Field("Mínimo", "Número mínimo de egressos por período considerando " +
                "todos os períodos desde o início da operação do curso.");
        Field maximumAlumni = new Field("Máximo", "Número máximo de egressos por período considerando " +
                "todos os períodos desde o início da operação do curso.");
        Field averageAlumni = new Field("Média", "Número médio de egressos por período considerando " +
                "todos os períodos desde o início da operação do curso.");
        Field averageGpa = new Field("CRA Médio", "Esta métrica é o coeficiente de rendimento escolar " +
                "de um discente ou a média desse coeficiente para um grupo de discentes.");
        Field rejoinCount = new Field("Reingressos", "Número de discentes que reingressaram no curso.");
        Field canceledCount = new Field("Cancelamentos", "Número de discentes que cancelaram suas matrículas");
        Field abandonCount = new Field("Abandonos", "Número de discentes que abandonaram o curso.");
        Field transferCount = new Field("Transferências", "Número de discentes que foram transferidos.");

        return new GlossaryFields(active, delayed, dropout, alumnus, averageRisk, averageLoad, successRate,
                predictedGraduation, averageCost, averageTime, minimumAlumni, maximumAlumni, averageAlumni,
                averageGpa, rejoinCount, canceledCount, abandonCount, transferCount);
    }
}
