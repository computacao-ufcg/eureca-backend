package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentMetrics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentMetricsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

import org.apache.log4j.Logger;
import java.util.Collection;

public class StudentMetricsCalculator {

    public static StudentMetrics computeMetrics(int attemptedCredits, int termsAccounted, int completedCredits, Curriculum curriculum) {
        return doComputeMetrics(attemptedCredits, termsAccounted, completedCredits, curriculum);
    }

    public static StudentMetrics computeMetrics(Student student) {
        int attemptedCredits = student.getAttemptedCredits();
        int termsAccounted = student.getCompletedTerms() + student.getInstitutionalEnrollments() + student.getInstitutionalEnrollments();
        int completedCredits = student.getCompletedCredits();
        Curriculum curriculum = student.getCurriculum();
        return doComputeMetrics(attemptedCredits, termsAccounted, completedCredits, curriculum);
    }

    public static RiskClass computeRiskClass(double risk, double lowestRisk) {
        double riskIncrement = 1.0 - lowestRisk;
        if (risk < 0.0) {
            return RiskClass.NOT_APPLICABLE;
        }
        if (risk < lowestRisk) {
            return RiskClass.INACCURATE;
        }
        if (risk < 1.0) {
            return RiskClass.SAFE;
        }
        if (risk < (1.0 + 1.0 * riskIncrement)) {
            return RiskClass.LOW;
        }
        if (risk < (1.0 + 2.0 * riskIncrement)) {
            return RiskClass.AVERAGE;
        }
        if (risk < (1.0 + 3.0 * riskIncrement)) {
            return RiskClass.HIGH;
        }
        return RiskClass.UNFEASIBLE;
    }

    public static CostClass computeCostClass(double cost, double costIncrement) {
        double lowestCost = 1.0;
        if (cost < 0.0) {
            return CostClass.NOT_APPLICABLE;
        }
        if (cost < lowestCost) {
            return CostClass.INACCURATE;
        }
        if (cost < lowestCost + 1.0 * costIncrement) {
            return CostClass.ADEQUATE;
        }
        if (cost < (lowestCost + 2.0 * costIncrement)) {
            return CostClass.REGULAR;
        }
        if (cost < (lowestCost + 3.0 * costIncrement)) {
            return CostClass.HIGH;
        }
        if (cost < (lowestCost + 4.0 * costIncrement)) {
            return CostClass.VERY_HIGH;
        }
        return CostClass.UNACCEPTABLE;
    }

    public static StudentMetricsSummary computeMetricsSummary(Collection<Student> students) {
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
        double aggregateLowestRisk = 0.0;
        double aggregateCostIncrement = 0.0;
        double v;
        for (Student item : students) {
            aggregateTerms += item.getCompletedTerms();
            StudentMetrics studentMetrics = StudentMetricsCalculator.computeMetrics(item);
            aggregateAttemptedCredits += studentMetrics.getAttemptedCredits();
            aggregateFeasibility += ((v = studentMetrics.getFeasibility()) == -1.0 ? 0.0 : v);
            aggregateSuccessRate += ((v = studentMetrics.getSuccessRate()) == -1.0 ? 0.0 : v);
            aggregateLoad += ((v = studentMetrics.getAverageLoad()) == -1.0 ? 0.0 : v);
            aggregateCost += ((v = studentMetrics.getCost()) == -1.0 ? 0.0 : v);
            aggregatePace += ((v = studentMetrics.getPace()) == -1.0 ? 0.0 : v);
            aggregateCourseDurationPrediction += ((v = studentMetrics.getCourseDurationPrediction()) == -1.0 ? 0.0 : v);
            aggregateRisk += ((v = studentMetrics.getRisk()) == -1.0 ? 0.0 : v);
            aggregateLowestRisk += item.getCurriculum().getMinNumberOfTerms() / ((item.getCurriculum().getMinNumberOfTerms() +
                    (item.getCurriculum().getMaxNumberOfTerms() - item.getCurriculum().getMinNumberOfTerms()) / 4.0));
            aggregateCostIncrement += ((item.getCurriculum().getMinNumberOfTerms() + (item.getCurriculum().getMaxNumberOfTerms() -
                    item.getCurriculum().getMinNumberOfTerms()) / 4.0) / item.getCurriculum().getMinNumberOfTerms()) - 1.0;
        }
        StudentMetrics metricsSummary = new StudentMetrics(aggregateAttemptedCredits/size,
                aggregateFeasibility/size, aggregateSuccessRate/size, aggregateLoad/size,
                aggregateCost/size, aggregatePace/size,
                aggregateCourseDurationPrediction/size,aggregateRisk/size);
        return (size == 0.0 ? null : new StudentMetricsSummary(aggregateTerms/size, metricsSummary,
                aggregateLowestRisk/size, aggregateCostIncrement/size));
    }

    private static StudentMetrics doComputeMetrics(int attemptedCredits, int termsAccounted, int completedCredits, Curriculum curriculum) {
        try {
            double feasibility = computeFeasibility(termsAccounted, completedCredits, curriculum);
            double successRate = computeSuccessRate(completedCredits, attemptedCredits);
            double averageLoad = computeAverageLoad(termsAccounted, attemptedCredits);
            double cost = computeCost(termsAccounted, completedCredits, attemptedCredits, curriculum);
            double pace = computePace(termsAccounted, completedCredits);
            int courseDurationPrediction = computeCourseDurationPrediction(termsAccounted, completedCredits, curriculum);
            double risk = computeRisk(termsAccounted, completedCredits, curriculum);
            return new StudentMetrics(attemptedCredits, feasibility, successRate, averageLoad, cost, pace,
                    courseDurationPrediction, risk);
        } catch (Exception e) {

            Logger LOGGER = Logger.getLogger(StudentMetricsCalculator.class);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private static double computeFeasibility(int completedTerms, int completedCredits, Curriculum curriculum) {
        if (completedTerms > 0) {
            double creditsMissing = 1.0 * (curriculum.getMinNumberOfCreditsNeeded() - completedCredits);
            double maxCredits = ((curriculum.getMaxNumberOfTerms() - completedTerms) * 1.0 *
                    curriculum.getMaxNumberOfEnrolledCredits() + curriculum.getExceptionalAdditionalEnrolledCredits());
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

    private static double computeCost(int completedTerms, int completedCredits, int attemptedCredits, Curriculum curriculum) {
        double rate = computeSuccessRate(completedCredits, attemptedCredits);
        double averageLoad = computeAverageLoad(completedTerms, attemptedCredits);
        if (rate > 0 && averageLoad > 0) {
            return ((1.0 * curriculum.getMinNumberOfCreditsNeeded() / curriculum.getMinNumberOfTerms()) / (rate * averageLoad));
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

    private static int computeCourseDurationPrediction(int completedTerms, int completedCredits, Curriculum curriculum) {
        if (completedTerms > 0 && completedCredits > 0) {
            double pace = computePace(completedTerms, completedCredits);
            int estimatedTermsNeeded = (int) Math.ceil((curriculum.getMinNumberOfCreditsNeeded() -
                    completedCredits) / pace);
            return completedTerms + estimatedTermsNeeded;
        } else {
            return -1;
        }
    }

    private static double computeRisk(int completedTerms, int completedCredits, Curriculum curriculum) {
        if (completedTerms > 0 && completedCredits > 0) {
            int estimatedTermsNeeded = computeCourseDurationPrediction(completedTerms, completedCredits, curriculum);
            return 1.0 * estimatedTermsNeeded / (curriculum.getMinNumberOfTerms() +
                    ((curriculum.getMaxNumberOfTerms() - curriculum.getMinNumberOfTerms()) / 4.0));
        } else {
            return -1.0;
        }
    }
}
