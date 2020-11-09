package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutSummaryResponse {
    String periodo;
    DropoutClassification tags;

    public DropoutSummaryResponse(String periodo, DropoutClassification tags) {
        this.periodo = periodo;
        this.tags = tags;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public DropoutClassification getTags() {
        return tags;
    }

    public void setTags(DropoutClassification tags) {
        this.tags = tags;
    }
}
