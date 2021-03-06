# coding: utf-8
import sys
from flask import request, jsonify
from flask_cors import cross_origin
from . import routes
sys.path.append("./models")
from Curso import Curso
from Disciplina import Disciplina

# Instância do model Curso que gerencia informações gerais sobre alunos do curso.
curso = Curso()

disciplina = Disciplina()

# Rota que retorna um json com todos os números de evadidos por período de todos os 
## motivos, que podem ter do id 1 ao 9, inclusive.
@routes.route("/api/statistics/students/escaped")
@cross_origin()
def escaped_from_period():

  # Acesso aos route params (parâmetros que são passados no endereço da rota).
  args = request.args

  return curso.get_escaped(args) 

# Rota responsável por retornar o número de alunos egressos (formados) do curso de 
## Computação e suas estatísticas de todos os períodos.
@routes.route("/api/statistics/students/graduated")
@cross_origin()
def graduates_by_period():

  # Acesso aos route params (parâmetros que são passados no endereço da rota).
  args = request.args

  return curso.get_graduates(args)

# Rota responsável por retornar informações sobre os alunos ativos do curso de Computação,
## informações estas que são a matrícula do aluno e a porcentagem concluída do curso com 
### base na quantidade de créditos que o aluno já possui.
@routes.route("/api/statistics/students/actives")
@cross_origin()
def active_students():
  args = request.args

  return curso.get_actives(args)

# Rota responsável por retornar as informações que vão compor o arquivo .csv de alunos
## ativos.
@routes.route("/api/statistics/students/actives/csv")
@cross_origin()
def export_to_csv_actives():
  args = request.args

  return curso.export_to_csv_actives(args)

# Rota responsável por retornar as informações que vão compor o arquivo .csv de alunos
## egressos ou graduados.
@routes.route("/api/statistics/students/graduated/csv")
@cross_origin()
def export_to_csv_graduates():
  args = request.args
  
  return curso.export_to_csv_graduates(args)

# Rota responsável por retornar as informações que vão compor o arquivo .csv de alunos
## evadidos.
@routes.route("/api/statistics/students/escaped/csv")
@cross_origin()
def export_to_csv_escaped():
  args = request.args

  return curso.export_to_csv_escaped(args)

# Rota responsável por retornar as informações de velocidade média de todos os alunos
## ativos de alunos com no mínimo de 1 período integralizado.
@routes.route("/api/statistics/average_speed")
@cross_origin()
def get_average_speed():
  return curso.get_average_speed()

# Rota responsável por retornar as informações de exequibilidade de todos os alunos
## ativos que possuem no mínimo 1 e no máximo 14 períodos integralizados.
@routes.route("/api/statistics/exequibility")
@cross_origin()
def get_practicability():
  return curso.get_practicability()

# Rota responsável por retornar as informações a cerca da taxa de sucesso de todos os 
## alunos que possuem mais de 0 créditos integralizados.
@routes.route("/api/statistics/success_rate")
@cross_origin()
def get_success_rate():
  return curso.get_success_rate()

@routes.route("/api/statistics/subjects/summary")
@cross_origin()
def get_rates_of_subjects():
  args = request.args
  
  response = disciplina.get_success_rates_of_all_subjects_group(args)
  
  return response

@routes.route("/api/statistics/subjects/metrics")
def get_metrics():
  args = request.args

  response = disciplina.get_metrics(args)

  return response

