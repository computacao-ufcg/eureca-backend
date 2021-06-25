# Eureca
> Projeto promovido pela coordenação do curso de Ciência da Computação da UFCG
## Tecnologias
* Java
* Spring Boot
* Swagger
## Deploy
É necessário adicionar o seu user ao grupo de usuários do docker, usando o seguinte comando e depois reiniciar a máquina.

```sudo usermod -aG docker $USER```

Depois é só executar o script build_tag_push.sh:

```bash build_tag_push.sh <git-branch> <docker-tag>```

Onde **<git-branch>** é o nome do branch que será usado para todos os repositórios e **<docker-tag>** é o rótulo
que será colocado na imagem construída e armazenada no Docker Hub.

## Glossário
Para fins de padronização no código a tabela a seguir trás termos comuns do projeto para que sejam reutilizados

| Termo   | Definição                                           |
|---------|-----------------------------------------------------|
| Actives | Alunos ativos - discentes regularmente matriculados |
| Delayed | Alunos atrasados                                    |  
| Droupouts |  Alunos desistentes - Discentes que foram desvinculados antes de se graduarem.|   
| Reenter |  Alunos reingressantes |   
| Alumni |  Egressos - discentes graduados.|   
| Term |  período|   
| Risk | risco - Essa métrica indica o risco do discente evadir ou a média desse risco para um grupo de discentes. Existe um mapeamento do valor dessa métrica para uma classe de risco, dependendo do valor do risco. A classe de risco pode assumir os valores 'Inexistente', 'Baixo', 'Médio', 'Alto' ou 'Inviável'. Discentes com risco 'Alto' ou 'Inviável' são considerados retidos. A classe de risco pode assumir dois outros valores em situações especiais: 'Não aplicável', quando os dados do discente são insuficientes para calcular seu risco; e 'Inexato', quando os dados do discente não estão corretos.|   
|Level |  tipo do grau (undergraduate=1, master=2, doctorate=3). |   
| Cost |  Esta métrica dá uma ideia do custo de um discente ou a média desse custo para um grupo de discentes. Existe um mapeamento do valor dessa métrica para uma classe de custo, dependendo do valor do custo. A classe de custo pode assumir os valores 'Adequado', 'Regular', 'Alto', 'Muito alto' ou 'Inaceitável'. A classe de custo pode assumir ainda dois outros valores em situações especiais: 'Não aplicável', quando os dados do discente são insuficientes para calcular seu custo; e 'Inexato', quando os dados do discente não estão corretos.|   
| Registration | matrícula do aluno|   
| NationalId |  CPF|   
| Absences |  Faltas do aluno|  
| Suspended | Trancado|   
| Cancelled |  Cancelado|   
| Transferred |  Transferido|   
| Retention |  Número de matrículas que, de acordo com o percurso mínimo proposto, deveriam já ter cursado uma disciplina, mas ainda não o fizeram.|   
| Feasibility |  Refere-se à viabilidade da execução curricular do aluno. Quanto mais perto de 1 maior o risco dessa execução ser inexequível.|   
| Pace |  Ritmo de execução do curso|   
| CourseDurationPrediction |  Tempo estimado para que um aluno conclua o curso|  
| AverageLoad |  Quantidade média de disciplinas que o aluno cursa em um período|  
| Inaccurate |  Termo utilizado para se referir a métricas com valores imprecisos.|  
| MobilityTerms |  Número de períodos em que o aluno esteve em mobilidade acadêmica.|  
| Enrollments |  Matrículas em uma turma/disciplina|  
| Subjects |  Disciplinas|  
| Teachers |  Professores|  
| Complementary |  Termo usado para se referir a cŕetidos/ horas/ disciplinas complementares|  
| Elective |  Termo usado para se referir a cŕetidos/ horas/ disciplinas eletivas|  
| Mandatory |  Termo usado para se referir a cŕetidos/horas/disciplinas obrigatórias|  