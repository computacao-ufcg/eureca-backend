package br.edu.ufcg.computacao.eureca.backend.constants;

public class ApiDocumentation {
    public static class ApiInfo {
        public static final String CONTACT_NAME = "Computação@UFCG";
        public static final String CONTACT_URL = "https://computacao.ufcg.edu.br/";
        public static final String CONTACT_EMAIL = "fubica@computacao.ufcg.edu.br";
        public static final String API_TITLE = "Eureca";
        public static final String API_DESCRIPTION = "Essa API permite a gerência de infomações sobre os cursos de graduação da UFCG";
    }

    public static class Model {
        public static final String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2uLMdBAGXUCZIHDf1NSs" +
                "Qvh9r72noQEMHQXw+lbKYuxxVzoMKjfa0qXPDvWIQ4E5wJO/VskhBNRZQsWbHPqk" +
                "MFzKlonzu+7KNzyF7Dd0E0KBGfzNWTSeaPXvpUgG7uRULn206mmgOTRWeG+HXbzF" +
                "jtpOVif3F0w+yQsQ2nSFVPTXXdX7pEAbDIMdH0I+Nb3y1Yl5ZJsO+rZcIUK0td7k" +
                "xM+BnKyQTyLkWIocwiw6WXHLOrwEKYDzv35uSha8+iB68kXbehWJxD7mG//WdVzW" +
                "3Rf7gmkApzPbOkeMoKOZJOS7DNkeOl150WbilLURQ7gHH6EiyDqskIlyRYiW6FDF" +
                "+wIDAQAB";
        public static final String REGISTRATION = "\"100110007\"";
        public static final String PERIOD = "\"2017.1\"";
        public static final String NUMBER = "5";
        public static final String PERCENTAGE = "44.3";
        public static final String SLIDER_LABEL = "[\"2013.2\", \"2014.1\", \"2014.2\"]";
        public static final String RISK = "NORMAL";
    }

    public static class Common {
        public static final String FROM = "Período inicial";
        public static final String TO = "Período final";
        public static final String LANGUAGE = "Língua";
        public static final String COURSE = "Código do curso";
        public static final String CURRICULUM = "Código do currículo";
        public static final String ACADEMIC_UNIT = "Código da Unidade Acadêmica";
        public static final String STUDENT_REGISTRATION = "Matrícula do aluno.";
    }

    public static class Alumni {
        public static final String API = "Egressos.";
        public static final String GET_ALUMNI = "Retorna a lista de egressos.";
    }

    public static class Profile {
        public static final String API = "Perfil.";
        public static final String GET_PROFILE = "Retorna o perfil do usuário.";
    }

    public static class Version {
        public static final String API = "Versão do serviço.";
        public static final String GET_VERSION = "Retorna a versão da API do serviço.";
    }

    public class Token {
        public static final String AUTHENTICATION_TOKEN = "'Token' gerado pelo serviço de autenticação.";
    }

    public static class PublicKey {
        public static final String API = "Chave pública do serviço.";
        public static final String GET_PUBLICKEY = "Retorna a chave pública do serviço.";
    }

    public static class Curricula {
        public static final String API = "Currículos de cursos.";
        public static final String GET_CURRICULA = "Retorna uma lista com os currículos do curso corrente.";
    }

    public static class EnrollmentsStatistics {
        public static final String API = "Estatísticas de matrículas.";
        public static final String GET_ENROLLMENTS = "Retorna um sumário dos dados das matrículas.";
        public static final String GET_MANDATORY_SUBJECTS_ENROLLMENTS_CSV = "Retorna os dados completos das matrículas em disciplinas obrigatórias.";
        public static final String GET_MANDATOTY_SUBJECTS_ENROLLMENTS = "Retorna um sumário dos dados das matrículas em disciplinas obrigatórias.";
        public static final String GET_OPTIONAL_SUBJECTS_ENROLLMENTS_CSV = "Retorna os dados completos das matrículas em disciplinas optativas.";
        public static final String GET_OPTIONAL_SUBJECTS_ENROLLMENTS = "Retorna um sumário dos dados das matrículas em disciplinas optativas";
        public static final String GET_ELECTIVE_SUBJECTS_ENROLLMENTS_CSV = "Retorna os dados completos das matrículas em disciplinas eletivas.";
        public static final String GET_ELECTIVE_SUBJECTS_ENROLLMENTS = "Retorna um sumário dos dados das matrículas em disciplinas eletivas.";
        public static final String GET_COMPLEMENTARY_SUBJECTS_ENROLLMENTS_CSV = "Retorna os dados completos das matrículas em disciplinas complementares.";
        public static final String GET_COMPLEMENTARY_SUBJECTS_ENROLLMENTS = "Retorna um sumário dos dados das matrículas em disciplinas complementares.";
    }

    public static class RetentionStatistics {
        public static final String API = "Estatísticas de retenção.";
        public static final String GET_SUBJECT = "Retorna um sumário da retenção por aluno.";
        public static final String GET_SUBJECT_CSV = "Retorna os dados completos da retenção por aluno.";
        public static final String GET_STUDENT = "Retorna um sumário dos dados da retenção por disciplina do currículo e curso selecionados.";
        public static final String GET_STUDENT_CSV = "Retorna os dados da retenção por disciplina.";
        public static final String GET_SUMMARY = "Retornar um sumário geral dos dados dos discentes.";
    }

    public static class StudentStatistics {
        public static final String API = "Estatísticas dos discentes.";
        public static final String GET_ACTIVES = "Retorna um sumário dos dados dos discentes ativos.";
        public static final String GET_ACTIVES_CSV = "Retorna os dados completos dos discentes ativos.";
        public static final String GET_ALUMNI = "Retorna um sumário dos dados dos egressos.";
        public static final String GET_ALUMNI_CSV = "Retorna os dados completos dos egressos.";
        public static final String GET_DROPOUT = "Retorna um sumário dos dados dos discentes evadidos.";
        public static final String GET_DROPOUT_CSV = "Retorna os dados completos dos discentes evadidos.";
        public static final String GET_SUMMARY = "Retornar um sumário geral dos dados dos discentes.";
    }

    public static class Communication {
        public static final String API = "Buscas por emails";
        public static final String STUDENT_EMAILS_SEARCH = "Buscas por emails de dicentes";
        public static final String SUBJECTS_EMAILS_SEARCH = "Buscas por emails de dicentes de uma disciplina";
        public static final String TEACHERS_EMAILS_SEARCH = "Buscas por emails de professores";
    }


    public static class EmailSearch {
        public static final String NAME = "Nome do estudante";
        public static final String STATUS = "Status do estudante (Todos, Ativos, Evadidos ou Egressos)";
        public static final String GENDER = "Gênero do estudante";
        public static final String CRA = "Cra do estudante";
        public static final String CRA_OPERATION = "Operação de busca que se deseja fazer de acordo com um valor de cra";
        public static final String ENROLLED_CREDITS = "Créditos matriculados pelo estudante";
        public static final String ADMISSION_TERM = "Período de admissão do estudante";
        public static final String ENROLLED_CREDITS_OPERATION = "Operação de busca que se deseja fazer de acordo com um valor de creditos matriculados";
        public static final String SUBJECT_NAME = "Nome da disciplina na qual se deve buscar os alunos matriculados";
        public static final String SUBJECT_TYPE = "Tipo da disciplina na qual se deve buscar os alunos matriculados";
        public static final String ACADEMIC_UNIT = "Unidade acadêmica da disciplina na qual se deve buscar os alunos matriculados";
        public static final String TERM = "Período pelo qual deve ser feito a busca";
        public static final String TEACHER_NAME = "Nome do professor que se deve buscar";
        public static final String TEACHER_ID = "Id do professor que se deve buscar";
        public static final String TEACHER_ACADEMIC_UNIT = "Unidade acadêmica do professor que se deve buscar";
    }

    public static class SubjectStatistics {
        public static final String API = "Estatísticas de disciplinas.";
        public static final String GET_MANDATORY = "Retorna um sumário dos dados das disciplinas obrigatórias do currículo e curso selecionados.";
        public static final String GET_OPTIONAL = "Retorna um sumário dos dados das disciplinas optativas do currículo e curso selecionados.";
        public static final String GET_ELECTIVE = "Retorna um sumário dos dados das disciplinas eletivas do currículo e curso selecionados.";
        public static final String GET_COMPLEMENTARY = "Retorna um sumário dos dados das disciplinas complementares do currículo e curso selecionados.";
        public static final String GET_SUBJECTS_MANDATORY_CSV = "Retorna os dados completos das disciplinas obrigatórias do currículo e curso selecionados.";
        public static final String GET_SUBJECTS_OPTIONAL_CSV = "Retorna os dados completos das disciplinas optativas do currículo e curso selecionados.";
        public static final String GET_SUBJECTS_ELECTIVE_CSV = "Retorna os dados completos das disciplinas eletivas do currículo e curso selecionados.";
        public static final String GET_SUBJECTS_COMPLEMENTARY_CSV = "Retorna os dados completos das disciplinas complementares do currículo e curso selecionados.";
        public static final String GET_SUBJECTS_SUMMARY = "Retorna um sumário dos dados das disciplinas do currículo e curso selecionados.";
    }

    public static class TeachersStatistics {
        public static final String API = "Estatísticas dos docentes.";
        public static final String GET_TEACHERS = "Retorna um sumário dos dados dos docentes.";
        public static final String GET_TEACHERS_CSV = "Retorna os dados completos dos docentes.";
    }

    public static class PreEnrollment {
        public static final String API = "Pré-matrícula de um ou mais alunos.";
        public static final String GET_PRE_ENROLLMENT = "Retorna uma possível pré-matrícula de um aluno específico.";
        public static final String GET_PRE_ENROLLMENTS = "Retorna todas as pre-matriculas dos ativos e, juntamente, um sumario com a demanda de cada disciplina.";
        public static final String GET_DEMAND = "Retorna a demanda de cada disciplina, baseado na pré-matrícula calculada.";
        public static final String MAX_CREDITS = "Número máximo de créditos na pré-matrícula.";
        public static final String ELECTIVE_PRIORITY_LIST = "Códigos das disciplinas eletivas a serem priorizadas (separadas por vírgula)";
        public static final String OPTIONAL_PRIORITY_LIST = "Códigos das disciplinas optativas a serem priorizadas (separadas por vírgula)";
        public static final String MANDATORY_PRIORITY_LIST = "Códigos das disciplinas obrigatorias a serem priorizadas (separadas por vírgula)";
        public static final String TERM = "O período da matrícula.";
    }
}
