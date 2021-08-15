package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.TeachersGlossaryFields;

import java.util.Collection;
import java.util.Map;

public class TeachersStatisticsSummaryResponse {
    private Collection<String> academicUnits;
    private Map<String, TeachersStatisticsSummary> summaryMap;
    private TeachersGlossaryFields glossary;

    public TeachersStatisticsSummaryResponse(Collection<String> academicUnits, Map<String, TeachersStatisticsSummary> summaryMap) {
        this.academicUnits = academicUnits;
        this.summaryMap = summaryMap;
    }

    public Collection<String> getAcademicUnits() {
        return academicUnits;
    }

    public void setAcademicUnits(Collection<String> academicUnits) {
        this.academicUnits = academicUnits;
    }

    public Map<String, TeachersStatisticsSummary> getSummaryMap() {
        return summaryMap;
    }

    public void setSummaryMap(Map<String, TeachersStatisticsSummary> summaryMap) {
        this.summaryMap = summaryMap;
    }

    public TeachersGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(TeachersGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
