package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.TeachersGlossaryFields;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class TeachersStatisticsSummaryResponse {
    private String courseCode;
    private String curriculumCode;
    private String from;
    private String to;
    private MetricStatistics succeeded;
    private MetricStatistics failedDueToAbsences;
    private MetricStatistics failedDueToGrade;
    private MetricStatistics suspended;
    private TermCount min;
    private TermCount max;
    private int teachersCount;
    private TeachersGlossaryFields glossary;

}
