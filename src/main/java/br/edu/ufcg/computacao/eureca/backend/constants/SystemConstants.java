package br.edu.ufcg.computacao.eureca.backend.constants;

public class SystemConstants {
    public static final String SERVICE_BASE_ENDPOINT = "/api/";
    public static final String API_VERSION_NUMBER = "1.0.0";
    public static final String CONFIG_FILE = "eureca.conf";
    public static final String ACTIVE = "Ativo";
    public static final String REGULAR = "REGULAR";
    public static final String GRADUATED = "GRADUADO";
    public static final String FAILED_3_TIMES = "CANCELADO 3 REPROV MESMA DISCIPLINA";
    public static final String REENTER_SAME_COURSE = "CANCELADO NOVO INGRESSO MESMO CURSO";
    public static final String REENTER_NEW_COURSE = "CANCELADO NOVO INGRESSO OUTRO CURSO";
    public static final String FAILED_ALL = "CANCELADO REPROVOU TODAS POR FALTAS";
    public static final String CANCELLED = "CANCELAMENTO DE MATRICULA";
    public static final String CANCELLED_BY_DECREE = "CANCELAMENTO P/ DECISAO JUDICIAL";
    public static final String CANCELLED_CHANGE_COURSE = "CANCELAMENTO P/ MUDANCA CURSO";
    public static final String CANCELLED_UPON_REQUEST = "CANCELAMENTO P/ SOLICITACAO ALUNO";
    public static final String LEFT_WITHOUT_NOTICE = "CANCELAMENTO POR ABANDONO";
    public static final String MISSED_GRADUATION = "CONCLUIDO - NAO COLOU GRAU";
    public static final String TRANSFERRED = "TRANSFERIDO PARA OUTRA IES";
}
