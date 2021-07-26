package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.AlumniGlossaryFields;

import java.util.Collection;

public class AlumniSummaryResponse extends RangeSummary {
    private Collection<AlumniPerTermSummary> alumniPerTermSummaries;
    private AlumniGlossaryFields glossary;

    public AlumniSummaryResponse(Collection<AlumniPerTermSummary> alumniPerTermSummaries, String from, String to) {
        super(from, to);
        this.alumniPerTermSummaries = alumniPerTermSummaries;
    }

    public Collection<AlumniPerTermSummary> getAlumniPerTermSummaries() {
        return alumniPerTermSummaries;
    }

    public void setAlumniPerTermSummaries(Collection<AlumniPerTermSummary> alumniPerTermSummaries) {
        this.alumniPerTermSummaries = alumniPerTermSummaries;
    }

    public AlumniGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(AlumniGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
