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

    public static class Alumni {
        public static final String API = "Egressos.";
        public static final String GET = "Retorna a lista de egressos.";
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

    public static class StudentStatistics {
        public static final String API = "Estatísticas dos discentes.";
        public static final String GET_ACTIVES = "Retorna um sumário dos dados dos discentes ativos.";
        public static final String GET_ACTIVES_CSV = "Retorna os dados completos dos discentes ativos.";
        public static final String GET_ALUMNI = "Retorna um sumário dos dados dos egressos.";
        public static final String GET_ALUMNI_CSV = "Retorna os dados completos dos egressos.";
        public static final String GET_DROPOUT = "Retorna um sumário dos dados dos discentes evadidos.";
        public static final String GET_DROPOUT_CSV = "Retorna os dados completos dos discentes evadidos.";
        public static final String GET_DELAYED = "Retorna um sumário dos dados dos discentes atrasados.";
        public static final String GET_DELAYED_CSV = "Retorna os dados completos dos discentes retidos.";
        public static final String GET_STUDENTS_SUMMARY = "Retornar um sumário geral dos dados dos discentes.";
    }

    public static class Statistics {
        public static final String FROM = "Período inicial";
        public static final String TO = "Período final";
        public static final String LANGUAGE = "Língua";
    }

    public static class EnrollmentsStatistics {
        public static final String API = "Estatísticas de matrículas.";
        public static final String GET_ENROLLMENTS = "Retorna um sumário dos dados das matrículas.";
        public static final String GET_ENROLLMENTS_CSV = "Retorna os dados completos das matrículas do curso.";
    }

    public static class TeachersStatistics {
        public static final String API = "Estatísticas dos docentes.";
        public static final String GET_TEACHERS = "Retorna um sumário dos dados dos docentes.";
        public static final String GET_TEACHERS_CSV = "Retorna os dados completos dos docentes.";
    }

    public static class SubjectStatistics {
        public static final String API = "Estatísticas de disciplinas.";
        public static final String GET_SUBJECTS = "Retorna um sumário dos dados das disciplinas.";
        public static final String GET_MANDATORY = "Retorna um sumário dos dados das disciplinas obrigatórias do currículo e curso selecionado.";
        public static final String GET_OPTIONAL = "Retorna um sumário dos dados das disciplinas optativas do currículo e curso selecionado.";
        public static final String GET_ELECTIVE = "Retorna um sumário dos dados das disciplinas eletivas do currículo e curso selecionado.";
        public static final String GET_COMPLEMENTARY = "Retorna um sumário dos dados das disciplinas complementares do currículo e curso selecionado.";
        public static final String GET_SUBJECTS_SUMMARY = "Retorna um sumário geral dos dados das disciplinas do currículo e curso selecionado.";
        public static final String GET_SUBJECTS_CSV = "Retorna os dados completos das disciplinas do currículo e curso selecionado.";
    }
}
