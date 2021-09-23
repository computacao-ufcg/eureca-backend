package br.edu.ufcg.computacao.eureca.backend.api.http.response.curriculum;

import java.util.Collection;

public class CurriculumCodesResponse {
    private Collection<String> curriculumCodes;

    public Collection<String> getCurriculumCodes() {
        return curriculumCodes;
    }

    public void setCurriculumCodes(Collection<String> curriculumCodes) {
        this.curriculumCodes = curriculumCodes;
    }

    public CurriculumCodesResponse(Collection<String> curriculumCodes) {
        this.curriculumCodes = curriculumCodes;
    }
}
