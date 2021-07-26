package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public abstract class RangeSummary {
    private String from;
    private String to;

    public RangeSummary(String from, String to) {
        this.from = from;
        this.to = to;
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
}
