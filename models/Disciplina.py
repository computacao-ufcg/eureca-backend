# coding: utf-8
import sys
sys.path.append("./connection")
from Connection import Connection
from flask import jsonify

class Disciplina():

  def __init__(self):
    self.connection = Connection()


  # Calcula as taxas de sucesso por tipo de disciplinas. Onde é passado o tipo da disciplina
  ## e são retornados os números de matrículas totais e aprovadas, para que sejam calculadas
  ### as taxas de sucesso de cada uma dessas disciplinas. 
  def get_success_rates_by_subject_group(self, id_tipo_disciplina, args):

    base_query = 'SELECT "Disciplina".codigo, COUNT("DiscenteDisciplina".*) \
      FROM "DiscenteDisciplina" \
      INNER JOIN "DiscenteVinculo" \
        ON "DiscenteDisciplina".matricula = "DiscenteVinculo".matricula \
      INNER JOIN "Turma" \
        ON "DiscenteDisciplina".id_turma = "Turma".id \
      INNER JOIN "Disciplina" \
        ON "Turma".id_disciplina = "Disciplina".id \
      INNER JOIN "Curriculo" \
        ON "Disciplina".codigo = "Curriculo".codigo_disciplina \
      AND "DiscenteVinculo".id_curso = 5 \
      AND "Curriculo".id_tipo_disciplina = ' + str(id_tipo_disciplina)

    # caso seja passado apenas o filtro 'de'.
    if (len(args) == 1):
      periodo = args.get('from')

      matriculas_totais = base_query + 'AND "Turma".periodo=\'' + str(periodo) + '\' \
        GROUP BY "Disciplina".codigo'

      matriculas_aprovadas = base_query + 'AND "Turma".periodo=\'' + str(periodo) + '\' \
        AND "DiscenteDisciplina".id_situacao = 1 \
        GROUP BY "Disciplina".codigo'

    # caso sejam passados os filtros 'de' e 'ate'.
    elif (len(args) == 2):
      minimo = args.get('from')
      maximo = args.get('to')

      if (minimo > maximo):
        return { "error": "Parameters or invalid request" }, 404

      matriculas_totais = base_query + 'AND "Turma".periodo BETWEEN \'' + str(minimo) + '\' AND \'' + str(maximo) + '\'\
        GROUP BY "Disciplina".codigo'

      matriculas_aprovadas = base_query + 'AND "Turma".periodo BETWEEN \'' + str(minimo) + '\' AND \'' + str(maximo) + '\'\
        AND "DiscenteDisciplina".id_situacao = 1 \
        GROUP BY "Disciplina".codigo'

    # caso não seja passado filtro algum.
    else:
      matriculas_totais = base_query + 'GROUP BY "Disciplina".codigo'

      matriculas_aprovadas = base_query + 'AND "DiscenteDisciplina".id_situacao = 1 \
        GROUP BY "Disciplina".codigo'

    total = self.connection.select(matriculas_totais)
    aprovadas = self.connection.select(matriculas_aprovadas)
    
    if(total is None or aprovadas is None):
      return []

    else:
      dic_total = {}
      dic_aprovadas = {}

      for i in range(len(total)):
        dic_total[total[i][0]] = total[i][1]

      for i in range(len(aprovadas)):
        dic_aprovadas[aprovadas[i][0]] = aprovadas[i][1]

      # calcula a taxa de sucesso para cada uma das disciplinas de um agrupamento.
      success_rates = []
      for i in range(len(total)):
        if (total[i][0] in dic_aprovadas and i <= len(aprovadas)-1):
          success_rate = dic_aprovadas[aprovadas[i][0]] / dic_total[total[i][0]]
          success_rates.append(round(success_rate * 100, 2) )
        else:
          if (i == len(aprovadas)-1):
            break
          success_rates.append(0)

      success_rates.sort()

      return success_rates


  # Calcula a posição central do array de dados para o boxplot, no que depender se o tamanho
  ## do array é ímpar ou par.
  def get_mid(self, data):
    size = len(data)

    if (size > 1):
      if (size % 2 == 0):
        index = size // 2
        mid_1 = data[index]
        mid_2 = data[index - 1]
        result = (mid_1 + mid_2) / 2
      else:
        index = size // 2
        result = data[index]

    return result

  
  # Calcula os quantis para o boxplot: q1, q2 e q3.
  def get_values_boxplot(self, data):
    size = len(data)
    index = size // 2
    mid_1 = data[index]
    mid_2 = data[index - 1]

    if (size >= 5):
      if (size % 2 == 0):
        q1 = self.get_mid(data[:index-1])
        q2 = (mid_1 + mid_2) / 2
        q3 = self.get_mid(data[index+1:size])
      else:
        q1 = self.get_mid(data[:index])
        q2 = data[index]
        q3 = self.get_mid(data[index+1:size])
      
      return [q1, q2, q3]
    
    else:
      return [0, 0, 0]
  

  # Calcula os pontos fora dos limites superior e inferior do boxplot.
  def get_outliers(self, data, lim_inf, lim_sup):
    outliers = []
    for i in range(len(data)):
      if (data[i] < lim_inf or data[i] > lim_sup):
        outliers.append(round(data[i], 2))
    
    return outliers

  
  # Obtém uma lista dos períodos para os labels do Slider no frontend.
  def get_periods(self):
    periodos = 'SELECT DISTINCT "Turma".periodo FROM "Turma" \
      ORDER BY "Turma".periodo'

    periodos = self.connection.select(periodos)

    lista_periodos = []
    for i in range(len(periodos)-1):
      lista_periodos.append(periodos[i][0])
    
    return lista_periodos


  # Processa a querie para cada um dos agrupamentos e retorna a resposta json com todas as
  ## informações para os boxplot's de cada um dos grupos de disciplinas.
  def get_success_rates_of_all_subjects_group(self, args):
    labels = ['Obrigatórias', 'Optativas gerais', 'Optativas específicas', 'Complementares',
      'Extracurriculares']

    success_rates = []
    for i in range(1, 4):
      success_rates.append(self.get_success_rates_by_subject_group(i, args))

    response = []
    for i in range(len(success_rates)):
      if (len(success_rates[i]) == 0):
        response.append({
          "group": labels[i],
          "data": {
            "lim_inf": 0,
            "lim_sup": 0,
            "q1": 0,
            "q2": 0,
            "q3": 0,
            "outliers": [],
          }
        })
      
      else:
        q1, q2, q3 = self.get_values_boxplot(success_rates[i])

        lim_inf = max(success_rates[i][0], q1 - (1.5 * (q3 - q1)))
        lim_sup = min(success_rates[i][len(success_rates[i]) - 1], q3 + (1.5 * (q3 - q1)))

        outliers = self.get_outliers(success_rates[i], lim_inf, lim_sup)
        
        response.append({
          "group": labels[i],
          "data": {
            "lim_inf": round(lim_inf, 2),
            "lim_sup": round(lim_sup, 2),
            "q1": round(q1, 2),
            "q2": round(q2, 2),
            "q3": round(q3, 2),
            "outliers": outliers,
          }
        })

    if (len(args) == 0):
      periodos = self.get_periods()

      return jsonify(
        content=response,
        slider_label=periodos
      )

    else:
      return jsonify(
        response
      )


  # Função que efetua o cálculo de métricas sobre as turmas de uma determinada disciplina em
  ## um intervalo de períodos.
  def get_class_overview_success_and_statistics(self, args):
    subject_code = args.get('subject')
    metric = args.get('metric')

    turmas_query = 'SELECT "Turma".periodo, "DiscenteDisciplina".id_turma, \
        COUNT("DiscenteDisciplina".*), "TurmaProfessor".siape \
      FROM "DiscenteDisciplina" \
      INNER JOIN "DiscenteVinculo" \
        ON "DiscenteDisciplina".matricula = "DiscenteVinculo".matricula \
      INNER JOIN "Turma" \
        ON "DiscenteDisciplina".id_turma = "Turma".id \
      INNER JOIN "TurmaProfessor" \
        ON "Turma".id = "TurmaProfessor".id_turma \
      INNER JOIN "Disciplina" \
        ON "Turma".id_disciplina = "Disciplina".id \
      AND "Disciplina".codigo = \'' + subject_code + '\''

    turmas_aprovadas_query = turmas_query + 'AND "DiscenteDisciplina".id_situacao = 1'

    # caso sejam passados três filtros na rota: subject, metric e from.  
    if (len(args) == 3):
      minimo = args.get('from')

      turmas_query += 'AND "Turma".periodo = \'' + minimo + '\' \
        GROUP BY "DiscenteDisciplina".id_turma, "Turma".periodo, "TurmaProfessor".siape'

      turmas_aprovadas_query += 'AND "Turma".periodo = \'' + minimo + '\' \
        GROUP BY "DiscenteDisciplina".id_turma, "Turma".periodo, "TurmaProfessor".siape'

    # caso sejam passados quatro filtros na rota: subject, metric, from e to.
    elif (len(args) == 4):
      minimo = args.get('from')
      maximo = args.get('to')

      turmas_query += 'AND "Turma".periodo BETWEEN \'' + minimo + '\' AND \'' + maximo +'\' \
        GROUP BY "DiscenteDisciplina".id_turma, "Turma".periodo, "TurmaProfessor".siape'  

      turmas_aprovadas_query += 'AND "Turma".periodo BETWEEN \'' + minimo + '\' AND \'' + maximo +'\' \
        GROUP BY "DiscenteDisciplina".id_turma, "Turma".periodo, "TurmaProfessor".siape'
    
    # caso sejam passados apenas dois filtros na rota: subject e metric.
    else:
      turmas_query += 'GROUP BY "DiscenteDisciplina".id_turma, "Turma".periodo, "TurmaProfessor".siape'

      turmas_aprovadas_query += 'GROUP BY "DiscenteDisciplina".id_turma, "Turma".periodo, "TurmaProfessor".siape'

    turmas_totais = self.connection.select(turmas_query)

    turmas_aprovadas = self.connection.select(turmas_aprovadas_query)

    # Resposta padrão para o caso de que não hajam resultados válidos para os parâmetros passados.
    if (len(turmas_totais) == 0):
      return jsonify(
        subject_code=subject_code,
        error="Non-existent subject"
      )

    # Criação de um mapa que agrupa as informações de número de alunos e professores das turmas
    ## por período.
    dic_disciplinas = {}
    for i in range(len(turmas_totais)):
      if (turmas_totais[i][0] not in dic_disciplinas):
        dic_disciplinas[turmas_totais[i][0]] = { "students": [], "teachers": [], "approved": [] }
        dic_disciplinas[turmas_totais[i][0]]["students"].append(turmas_totais[i][2])
        dic_disciplinas[turmas_totais[i][0]]["teachers"].append(turmas_totais[i][3])
      else:
        dic_disciplinas[turmas_totais[i][0]]["students"].append(turmas_totais[i][2])
        dic_disciplinas[turmas_totais[i][0]]["teachers"].append(turmas_totais[i][3])

    # Caso a métrica selecionada seja a 'class_overview', ela retorna um resumo das turmas de uma
    ## determinada disciplina, informações tais como o número de alunos por turma, os professores 
    ### dessas turmas e o número total de alunos de uma disciplina por período.
    classes = []
    if (metric == 'class_overview'):
      for i in dic_disciplinas:
        classes.append({
          "period": i,
          "total": sum(dic_disciplinas[i]["students"]),
          "teachers": dic_disciplinas[i]["teachers"],
          "students": dic_disciplinas[i]["students"]
        })
    
    # Caso a métrica selecionada seja a 'class_statistics', ela retorna em um período, a turma que
    ## teve mais alunos, menos alunos e a média de alunos entre todas as turmas do período.
    elif (metric == 'class_statistics'):
      for i in dic_disciplinas:
        classes.append({
          "period": i,
          "minimum": min(dic_disciplinas[i]["students"]),
          "maximum": max(dic_disciplinas[i]["students"]),
          "average": round(sum(dic_disciplinas[i]["students"]) / len(dic_disciplinas[i]["students"]), 2)
        })
    
    # Caso a métrica selecionada seja a 'success_overview', ela retorna em um período, a taxa de
    ## sucesso de cada uma das turmas, que é o quociente entre o número de aprovadas na turma
    ### e o número total de alunos na turma. Além da média das taxas de sucesso do período.
    elif (metric == 'success_overview'):
      for i in range(len(turmas_aprovadas)):
        if (turmas_aprovadas[i][0] in dic_disciplinas):
          dic_disciplinas[turmas_aprovadas[i][0]]["approved"].append(turmas_aprovadas[i][2])

      for i in dic_disciplinas: 
        success_rates = []
        for j in range(len(dic_disciplinas[i]["students"])):
          if (len(dic_disciplinas[i]["students"]) == len(dic_disciplinas[i]["approved"])):
            success_rates.append(round(dic_disciplinas[i]["approved"][j] / dic_disciplinas[i]["students"][j], 2))
        
        if (len(success_rates) == 1):
          classes.append({
            "period": i,
            "rates_by_class": {
              "t1": success_rates[0],
            },
            "teachers": dic_disciplinas[i]["teachers"],
            "total": round(sum(success_rates), 2)
          })        
        elif (len(success_rates) == 2):
          classes.append({
            "period": i,
            "rates_by_class": {
              "t1": success_rates[0],
              "t2": success_rates[1],
            },
            "teachers": dic_disciplinas[i]["teachers"],
            "total": round(sum(success_rates), 2)
          })
        elif (len(success_rates) == 3):
          classes.append({
            "period": i,
            "rates_by_class": {
              "t1": success_rates[0],
              "t2": success_rates[1],
              "t3": success_rates[2],
            },
            "teachers": dic_disciplinas[i]["teachers"],
            "total": round(sum(success_rates), 2)
          })
        
    return jsonify(
      subject_code=subject_code,
      classes=classes
    )


  # Função que a partir da métrica selecionada na rota delega para outra função executar o 
  ## processamento da métrica.
  def get_metrics(self, args): 
    metric = args.get('metric')
    result = self.get_class_overview_success_and_statistics(args)

    return result
