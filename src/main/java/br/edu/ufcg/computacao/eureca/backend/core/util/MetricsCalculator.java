package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

import org.apache.log4j.Logger;

public class MetricsCalculator {
    private Logger LOGGER = Logger.getLogger(MetricsCalculator.class);

    public static Metrics computeMetrics(StudentData student) {
        try {
            int attemptedCredits = student.getAttemptedCredits();
            int completedTerms = student.getCompletedTerms();
            int completedCredits = student.getCompletedCredits();
            double feasibility = computeFeasibility(completedTerms, completedCredits);
            double successRate = computeSuccessRate(completedCredits, attemptedCredits);
            double averageLoad = computeAverageLoad(completedTerms, attemptedCredits);
            double cost = computeCost(completedTerms, completedCredits, attemptedCredits);
            double pace = computePace(completedTerms, completedCredits);
            int courseDurationPrediction = computeCourseDurationPrediction(completedTerms, completedCredits);
            double risk = computeRisk(completedTerms, completedCredits);
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
            double maxCredits = ((Curriculum.MAX_NUMBER_OF_TERMS - completedTerms) *
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
            double risk = (1.0 * (estimatedTermsNeeded - Curriculum.MAX_NUMBER_OF_TERMS)) /
                    (1.0 * Curriculum.MAX_NUMBER_OF_TERMS - 1);
            return (Math.min(risk, 1.0));
        } else {
            return -1.0;
        }
    }
}
