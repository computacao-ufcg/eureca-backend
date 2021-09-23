package br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni;

import br.edu.ufcg.computacao.eureca.backend.constants.AlumniGlossaryFields;

import java.util.Collection;

public class AlumniResponse {
    private Collection<AlumniDigest> alumniDigest;
    private AlumniGlossaryFields glossary;

    public AlumniResponse(Collection<AlumniDigest> alumniDigest) {
        this.alumniDigest = alumniDigest;
    }

    public Collection<AlumniDigest> getAlumniDigest() {
        return alumniDigest;
    }

    public void setAlumniDigest(Collection<AlumniDigest> alumniDigest) {
        this.alumniDigest = alumniDigest;
    }

    public AlumniGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(AlumniGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
