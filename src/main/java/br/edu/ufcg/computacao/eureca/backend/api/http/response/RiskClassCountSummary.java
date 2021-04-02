package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class RiskClassCountSummary {
    int unfeasible;
    int critical;
    int late;
    int normal;
    int advanced;
    int notApplicable;

    public RiskClassCountSummary(int unfeasible, int critical, int late, int normal, int advanced, int notApplicable) {
        this.unfeasible = unfeasible;
        this.critical = critical;
        this.late = late;
        this.normal = normal;
        this.advanced = advanced;
        this.notApplicable = notApplicable;
    }

    public int getUnfeasible() {
        return unfeasible;
    }

    public void setUnfeasible(int unfeasible) {
        this.unfeasible = unfeasible;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getAdvanced() {
        return advanced;
    }

    public void setAdvanced(int advanced) {
        this.advanced = advanced;
    }

    public int getNotApplicable() {
        return notApplicable;
    }

    public void setNotApplicable(int notApplicable) {
        this.notApplicable = notApplicable;
    }

    public void add(RiskClassCountSummary other) {
        this.unfeasible += other.getUnfeasible();
        this.critical = getCritical();
        this.late += other.getLate();
        this.normal += other.getNormal();
        this.advanced += other.getAdvanced();
        this.notApplicable += other.getNotApplicable();
    }
}