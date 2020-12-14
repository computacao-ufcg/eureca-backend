package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class RiskClassPercentageSummary {
    double unfeasible;
    double critical;
    double late;
    double normal;
    double advanced;
    double notApplicable;

    public RiskClassPercentageSummary(double unfeasible, double critical, double late, double normal, double advanced,
                                      double notApplicable) {
        this.unfeasible = unfeasible;
        this.critical = critical;
        this.late = late;
        this.normal = normal;
        this.advanced = advanced;
        this.notApplicable = notApplicable;
    }

    public double getUnfeasible() {
        return unfeasible;
    }

    public void setUnfeasible(double unfeasible) {
        this.unfeasible = unfeasible;
    }

    public double getCritical() {
        return critical;
    }

    public void setCritical(double critical) {
        this.critical = critical;
    }

    public double getLate() {
        return late;
    }

    public void setLate(double late) {
        this.late = late;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public double getAdvanced() {
        return advanced;
    }

    public void setAdvanced(double advanced) {
        this.advanced = advanced;
    }

    public double getNotApplicable() {
        return notApplicable;
    }

    public void setNotApplicable(double notApplicable) {
        this.notApplicable = notApplicable;
    }
}