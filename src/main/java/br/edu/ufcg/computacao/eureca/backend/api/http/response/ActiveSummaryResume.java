package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Student;

import java.util.Collection;

public class ActiveSummaryResume {

    private int activesCount;
    private RiskClassCountSummary riskClassCount;
    private RiskClassPercentageSummary riskClassPercentage;

    public ActiveSummaryResume(int activesCount, RiskClassCountSummary riskClassCount, RiskClassPercentageSummary riskClassPercentage) {
        this.activesCount = activesCount;
        this.riskClassCount = riskClassCount;
        this.riskClassPercentage = riskClassPercentage;
    }

    public ActiveSummaryResume(Collection<Student> actives) {
        this.activesCount = actives.size();
        int advanced = 0, critical = 0, late = 0, normal = 0, notApplicable = 0, unfeasible = 0;
        for (Student active : actives) {
            switch (active.getRiskClass()) {
                case ADVANCED:
                    advanced++;
                    break;
                case CRITICAL:
                    critical++;
                    break;
                case LATE:
                    late++;
                    break;
                case NORMAL:
                    normal++;
                    break;
                case NOT_APPLICABLE:
                    notApplicable++;
                    break;
                case UNFEASIBLE:
                    unfeasible++;
                    break;
                default:
                    break;
            }
        }
        double advancedPercentage = advanced / (double) activesCount;
        double criticalPercentage = critical / (double) activesCount;
        double latePercentage = late / (double) activesCount;
        double normalPercentage = normal / (double) activesCount;
        double notApplicablePercentage = notApplicable / (double) activesCount;
        double unfeasiblePercentage = unfeasible/ (double) activesCount;

        this.riskClassCount = new RiskClassCountSummary(unfeasible, critical, late, normal, advanced, notApplicable);
        this.riskClassPercentage = new RiskClassPercentageSummary(unfeasiblePercentage, criticalPercentage, latePercentage, normalPercentage, advancedPercentage, notApplicablePercentage);
    }

    public int getActivesCount() {
        return activesCount;
    }

    public void setActivesCount(int activesCount) {
        this.activesCount = activesCount;
    }

    public RiskClassCountSummary getRiskClassCount() {
        return riskClassCount;
    }

    public void setRiskClassCount(RiskClassCountSummary riskClassCount) {
        this.riskClassCount = riskClassCount;
    }

    public RiskClassPercentageSummary getRiskClassPercentage() {
        return riskClassPercentage;
    }

    public void setRiskClassPercentage(RiskClassPercentageSummary riskClassPercentage) {
        this.riskClassPercentage = riskClassPercentage;
    }
}
