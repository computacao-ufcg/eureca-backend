package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.TeachersGlossaryFields;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;
import br.edu.ufcg.computacao.eureca.backend.core.models.TermCount;

public class TeachersStatisticsSummaryResponse {
    private String curriculum;
    private String from;
    private String to;
    private MetricStatistics failedDueToAbsences;
    private MetricStatistics failedDueToGrade;
    private MetricStatistics failedDueToCanceling;
    private MetricStatistics success;
    private TermCount min;
    private TermCount max;
    private int total;
    private TeachersGlossaryFields glossary;

    public TeachersStatisticsSummaryResponse(String curriculum, String from, String to, MetricStatistics failedDueToAbsences,
                                             MetricStatistics failedDueToGrade, MetricStatistics failedDueToCanceling,
                                             MetricStatistics success, TermCount min, TermCount max, int total) {
        this.curriculum = curriculum;
        this.from = from;
        this.to = to;
        this.failedDueToAbsences = failedDueToAbsences;
        this.failedDueToGrade = failedDueToGrade;
        this.failedDueToCanceling = failedDueToCanceling;
        this.success = success;
        this.min = min;
        this.max = max;
        this.total = total;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MetricStatistics getFailedDueToAbsences() {
        return failedDueToAbsences;
    }

    public MetricStatistics getFailedDueToGrade() {
        return failedDueToGrade;
    }

    public MetricStatistics getFailedDueToCanceling() {
        return failedDueToCanceling;
    }

    public MetricStatistics getSuccess() {
        return success;
    }

    public TermCount getMin() {
        return min;
    }

    public TermCount getMax() {
        return max;
    }

    public int getTotal() {
        return total;
    }

    public TeachersGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(TeachersGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
