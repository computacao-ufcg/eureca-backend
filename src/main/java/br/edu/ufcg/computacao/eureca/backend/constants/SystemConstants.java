package br.edu.ufcg.computacao.eureca.backend.constants;

public class SystemConstants {
    public static final String SERVICE_BASE_ENDPOINT = "/api/";
    public static final String API_VERSION_NUMBER = "1.0.0";
    public static final String CONFIG_FILE = "eureca.conf";
    public static final String FAILED_3_TIMES = "CANCELADO 3 REPROV MESMA DISCIPLINA";
    public static final String REENTER_SAME_COURSE = "CANCELADO NOVO INGRESSO MESMO CURSO";
    public static final String REENTER_OTHER_COURSE = "CANCELADO NOVO INGRESSO OUTRO CURSO";
    public static final String FAILED_ALL = "CANCELADO REPROVOU TODAS POR FALTAS";
    public static final String CANCELLED = "CANCELAMENTO DE MATRICULA";
    public static final String CANCELLED_BY_DECREE = "CANCELAMENTO P/ DECISAO JUDICIAL";
    public static final String CANCELLED_COURSE_CHANGE = "CANCELAMENTO P/ MUDANCA CURSO";
    public static final String CANCELLED_UPON_REQUEST = "CANCELAMENTO P/ SOLICITACAO ALUNO";
    public static final String LEFT_WITHOUT_NOTICE = "CANCELAMENTO POR ABANDONO";
    public static final String MISSED_GRADUATION = "CONCLUIDO - NAO COLOU GRAU";
    public static final String TRANSFERRED = "TRANSFERIDO PARA OUTRA IES";
    public static final int DROPOUT_TYPES_COUNT = 12;
    public static final int FAILED_3_TIMES_INDEX = 0;
    public static final int REENTER_SAME_COURSE_INDEX = 1;
    public static final int REENTER_OTHER_COURSE_INDEX = 2;
    public static final int FAILED_ALL_INDEX = 3;
    public static final int CANCELLED_INDEX = 4;
    public static final int CANCELLED_BY_DECREE_INDEX = 5;
    public static final int CANCELLED_COURSE_CHANGE_INDEX = 6;
    public static final int CANCELLED_UPON_REQUEST_INDEX = 7;
    public static final int LEFT_WITHOUT_NOTICE_INDEX = 8;
    public static final int MISSED_GRADUATION_INDEX = 9;
    public static final int TRANSFERRED_INDEX = 10;
    public static final int UNKNOWN = 11;
    public static final String FIRST_POSSIBLE_TERM = "1950.0";
    public static final String LAST_POSSIBLE_TERM = "2049.9";
}
