package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.constants.AlumniGlossaryFields;

import java.util.Collection;

public class AlumniResponse {
    private Collection<AlumniDigestResponse> alumniDigest;
    private AlumniGlossaryFields glossary;

    public AlumniResponse(Collection<AlumniDigestResponse> alumniDigest) {
        this.alumniDigest = alumniDigest;
    }

    public Collection<AlumniDigestResponse> getAlumniDigest() {
        return alumniDigest;
    }

    public void setAlumniDigest(Collection<AlumniDigestResponse> alumniDigest) {
        this.alumniDigest = alumniDigest;
    }

    public AlumniGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(AlumniGlossaryFields glossary) {
        this.glossary = glossary;
    }
}
