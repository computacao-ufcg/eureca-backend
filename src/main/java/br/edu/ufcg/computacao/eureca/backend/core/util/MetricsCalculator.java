package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.MetricsSummary;
import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

import org.apache.log4j.Logger;

import java.util.Collection;

public class MetricsCalculator {
    private Logger LOGGER = Logger.getLogger(MetricsCalculator.class);

    public static Metrics computeMetrics(StudentData student) {
        try {
            int attemptedCredits = student.getAttemptedCredits();
            int termsAccounted = student.getCompletedTerms() + student.getInstitutionalTerms() + student.getInstitutionalTerms();
            int completedCredits = student.getCompletedCredits();
            double feasibility = computeFeasibility(termsAccounted, completedCredits);
            double successRate = computeSuccessRate(completedCredits, attemptedCredits);
            double averageLoad = computeAverageLoad(termsAccounted, attemptedCredits);
            double cost = computeCost(termsAccounted, completedCredits, attemptedCredits);
            double pace = computePace(termsAccounted, completedCredits);
            int courseDurationPrediction = computeCourseDurationPrediction(termsAccounted, completedCredits);
            double risk = computeRisk(termsAccounted, completedCredits);
            return new Metrics(attemptedCredits, feasibility, successRate, averageLoad, cost, pace,
                    courseDurationPrediction, risk);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static double computeFeasibility(int completedTerms, int completedCredits) {
        if (completedTerms > 0) {
            double creditsMissing = 1.0 * (Curriculum.TOTAL_CREDITS_NEEDED - completedCredits);
            double maxCredits = ((Curriculum.MAX_NUMBER_OF_TERMS - completedTerms) * 1.0 *
                    Curriculum.MAX_NUMBER_OF_CREDITS + Curriculum.EXCEPTIONAL_ADDITIONAL_CREDITS);
            return (creditsMissing < 0 ? 0.0 : (maxCredits <= 0 ? -1.0 : creditsMissing/maxCredits));
        } else {
            return -1.0;
        }
    }

    private static double computeSuccessRate(int completedCredits, int attemptedCredits) {
        if (attemptedCredits > 0) {
            return (1.0 * completedCredits) / attemptedCredits;
        } else {
            return -1.0;
        }
    }

    private static double computeAverageLoad(int completedTerms, int attemptedCredits) {
        if (completedTerms > 0) {
            return (1.0 * attemptedCredits) / completedTerms;
        } else {
            return -1.0;
        }
    }

    private static double computeCost(int completedTerms, int completedCredits, int attemptedCredits) {
        double rate = computeSuccessRate(completedCredits, attemptedCredits);
        double averageLoad = computeAverageLoad(completedTerms, attemptedCredits);
        if (rate > 0 && averageLoad > 0) {
            return ((1.0 * Curriculum.TOTAL_CREDITS_NEEDED / Curriculum.MIN_NUMBER_OF_TERMS) / (rate * averageLoad));
        } else {
            return -1.0;
        }
    }

    private static double computePace(int completedTerms, int completedCredits) {
        if (completedTerms > 0) {
            return 1.0 * completedCredits / completedTerms;
        } else {
            return -1.0;
        }
    }

    private static int computeCourseDurationPrediction(int completedTerms, int completedCredits) {
        if (completedTerms > 0 && completedCredits > 0) {
            double pace = computePace(completedTerms, completedCredits);
            int estimatedTermsNeeded = (int) Math.ceil((Curriculum.TOTAL_CREDITS_NEEDED -
                    completedCredits) / pace);
            return completedTerms + estimatedTermsNeeded;
        } else {
            return -1;
        }
    }

    private static double computeRisk(int completedTerms, int completedCredits) {
        if (completedTerms > 0 && completedCredits > 0) {
            int estimatedTermsNeeded = computeCourseDurationPrediction(completedTerms, completedCredits);
            return 1.0 * estimatedTermsNeeded / (Curriculum.MIN_NUMBER_OF_TERMS +
                    ((Curriculum.MAX_NUMBER_OF_TERMS - Curriculum.MIN_NUMBER_OF_TERMS) / 4.0));
        } else {
            return -1.0;
        }
    }

    public static RiskClass computeRiskClass(double risk) {
        double desiredAverageDuration = (Curriculum.MIN_NUMBER_OF_TERMS +
                (Curriculum.MAX_NUMBER_OF_TERMS - Curriculum.MIN_NUMBER_OF_TERMS) / 4.0);
        double lowestRisk = Curriculum.MIN_NUMBER_OF_TERMS / desiredAverageDuration;
        double riskIncrement = 1.0 - lowestRisk;
        if (risk < 0.0) return RiskClass.NOT_APPLICABLE;
        if (risk < lowestRisk) return RiskClass.INACCURATE;
        if (risk < 1.0) return RiskClass.SAFE;
        if (risk < (1.0 + 1.0 * riskIncrement)) return RiskClass.LOW;
        if (risk < (1.0 + 2.0 * riskIncrement)) return RiskClass.AVERAGE;
        if (risk < (1.0 + 3.0 * riskIncrement)) return RiskClass.HIGH;
        return RiskClass.UNFEASIBLE;
    }

    public static CostClass computeCostClass(double cost) {
        double lowestCost = 1.0;
        double costIncrement = ((Curriculum.MIN_NUMBER_OF_TERMS + (Curriculum.MAX_NUMBER_OF_TERMS -
                Curriculum.MIN_NUMBER_OF_TERMS) / 4.0) / Curriculum.MIN_NUMBER_OF_TERMS) - 1.0;
        if (cost < 0.0) return CostClass.NOT_APPLICABLE;
        if (cost < lowestCost) return CostClass.INACCURATE;
        if (cost < lowestCost + 1.0 * costIncrement) return CostClass.ADEQUATE;
        if (cost < (lowestCost + 2.0 * costIncrement)) return CostClass.REGULAR;
        if (cost < (lowestCost + 3.0 * costIncrement)) return CostClass.HIGH;
        if (cost < (lowestCost + 4.0 * costIncrement)) return CostClass.VERY_HIGH;
        return CostClass.UNACCEPTABLE;
    }

    public static MetricsSummary computeMetricsSummary(Collection<Student> students) {
        double size = 1.0 * students.size();
        double aggregateTerms = 0.0;
        double aggregateAttemptedCredits = 0.0;
        double aggregateFeasibility = 0.0;
        double aggregateSuccessRate = 0.0;
        double aggregateLoad = 0.0;
        double aggregateCost = 0.0;
        double aggregatePace = 0.0;
        double aggregateCourseDurationPrediction = 0.0;
        double aggregateRisk = 0.0;
        double v;
        for (Student item : students) {
            aggregateTerms += item.getStudentData().getCompletedTerms();
            Metrics studentMetrics = MetricsCalculator.computeMetrics(item.getStudentData());
            aggregateAttemptedCredits += studentMetrics.getAttemptedCredits();
            aggregateFeasibility += ((v = studentMetrics.getFeasibility()) == -1.0 ? 0.0 : v);
            aggregateSuccessRate += ((v = studentMetrics.getSuccessRate()) == -1.0 ? 0.0 : v);
            aggregateLoad += ((v = studentMetrics.getAverageLoad()) == -1.0 ? 0.0 : v);
            aggregateCost += ((v = studentMetrics.getCost()) == -1.0 ? 0.0 : v);
            aggregatePace += ((v = studentMetrics.getPace()) == -1.0 ? 0.0 : v);
            aggregateCourseDurationPrediction += ((v = studentMetrics.getCourseDurationPrediction()) == -1.0 ? 0.0 : v);
            aggregateRisk += ((v = studentMetrics.getRisk()) == -1.0 ? 0.0 : v);
        }
        Metrics metricsSummary = new Metrics(aggregateAttemptedCredits/size,
                aggregateFeasibility/size, aggregateSuccessRate/size, aggregateLoad/size,
                aggregateCost/size, aggregatePace/size,
                aggregateCourseDurationPrediction/size,aggregateRisk/size);
        return (size == 0.0 ? null : new MetricsSummary(aggregateTerms/size, metricsSummary));
    }
}
