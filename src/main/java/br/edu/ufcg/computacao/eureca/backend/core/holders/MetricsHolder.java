package br.edu.ufcg.computacao.eureca.backend.core.holders;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.StudentDataResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricsSummary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import org.apache.log4j.Logger;

public class MetricsHolder {
    private Logger LOGGER = Logger.getLogger(MetricsHolder.class);

    private DataAccessFacade dataAccessFacade;
    private Map<String, Integer> enrolledCredits;
    private Map<String, Metrics> metrics;
    private MetricsSummary summary;
    private static MetricsHolder instance;

    private MetricsHolder() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
        this.enrolledCredits = this.dataAccessFacade.getEnrollments();
        computeMetrics();
    }

    public Map<String, Integer> getEnrolledCredits() {
        return enrolledCredits;
    }

    public Map<String, Metrics> getMetrics() {
        return metrics;
    }

    public MetricsSummary getSummary() {
        return summary;
    }

    public RiskClass getRiskClass(String registration) {
        Metrics studentMetrics = getMetrics().get(registration);
        double feasibility = studentMetrics.getFeasibility();
        if (feasibility > 1) return RiskClass.UNFEASIBLE;
        if (feasibility < 0) return RiskClass.NOT_APPLICABLE;
        double risk = studentMetrics.getRisk();
        if (risk > 0) return RiskClass.CRITICAL;
        double highRiskThreshold = (1.0 * (Curriculum.EXPECTED_NUMBER_OF_TERMS - Curriculum.MAX_NUMBER_OF_TERMS)) /
                (Curriculum.MAX_NUMBER_OF_TERMS - 1);
        if (risk >= highRiskThreshold) return RiskClass.LATE;
        double lowRiskThreshold = (1.0 * (Curriculum.MIN_NUMBER_OF_TERMS - Curriculum.MAX_NUMBER_OF_TERMS)) /
                (Curriculum.MAX_NUMBER_OF_TERMS - 1);
        if (risk >= lowRiskThreshold) return RiskClass.NORMAL;
        return RiskClass.ADVANCED;
    }

    private void computeMetrics() {
        int activeCount = 0;
        double accumulatedCost = 0.0;
        double accumulatedCourseDurationPrediction = 0.0;
        this.metrics = new HashMap<>();
        Collection<StudentDataResponse> actives = this.dataAccessFacade.
                getActiveStudents(SystemConstants.FIRST_POSSIBLE_TERM, SystemConstants.LAST_POSSIBLE_TERM);
        for(StudentDataResponse student: actives) {
            activeCount++;
            double feasibility = computeFeasibility(student);
            double successRate = computeRate(student);
            double averageLoad = computeAverageLoad(student);
            double cost= computeAverageCost(student);
            accumulatedCost += cost;
            double pace = computePace(student);
            int courseDurationPrediction= computeCourseDurationPrediction(student);
            accumulatedCourseDurationPrediction += courseDurationPrediction;
            double risk = computeRisk(student);
            this.metrics.put(student.getRegistration(), new Metrics(feasibility, successRate, averageLoad, cost, pace,
                    courseDurationPrediction, risk));
        }
        double averageCost = 1.0 * accumulatedCost / activeCount;
        double averageCourseDurationPrediction = 1.0 * accumulatedCourseDurationPrediction / activeCount;
        this.summary = new MetricsSummary(activeCount, averageCost, averageCourseDurationPrediction);
    }

    private double computeFeasibility(StudentDataResponse student) {
        int completed = student.getCompletedTerms();
        if (completed > 0 && completed < Curriculum.MAX_NUMBER_OF_TERMS) {
            return (1.0 * (Curriculum.TOTAL_CREDITS_NEEDED - student.getCompletedCredits())) /
                    ((Curriculum.MAX_NUMBER_OF_TERMS - student.getCompletedTerms()) * Curriculum.MAX_NUMBER_OF_CREDITS
                    + Curriculum.EXCEPTIONAL_ADDITIONAL_CREDITS);
        } else {
            return -1.0;
        }
    }

    private double computeRate(StudentDataResponse student) {
        int completed = student.getCompletedCredits();
        int enrolled = this.getEnrolledCredits().get(student.getRegistration());
        if (enrolled > 0) {
            return 1.0 * completed / enrolled;
        } else {
            return -1.0;
        }
    }

    private double computeAverageLoad(StudentDataResponse student) {
        int completedTerms = student.getCompletedTerms();
        int enrolled = this.getEnrolledCredits().get(student.getRegistration());
        if (completedTerms > 0) {
            return 1.0 * enrolled / completedTerms;
        } else {
            return -1.0;
        }
    }

    private double computeAverageCost(StudentDataResponse student) {
        double rate = computeRate(student);
        double averageLoad = computeAverageLoad(student);
        if (rate > 0 && averageLoad > 0) {
            return ((1.0 * Curriculum.TOTAL_CREDITS_NEEDED / Curriculum.MIN_NUMBER_OF_TERMS) / (rate * averageLoad));
        } else {
            return -1.0;
        }
    }

    private double computePace(StudentDataResponse student) {
        int completedTerms = student.getCompletedTerms();
        if (completedTerms > 0) {
            return 1.0 * student.getCompletedCredits() / completedTerms;
        } else {
            return -1.0;
        }
    }

    private int computeCourseDurationPrediction(StudentDataResponse student) {
        int completedTerms = student.getCompletedTerms();
        int completedCredits = student.getCompletedCredits();
        if (completedTerms > 0 && completedCredits > 0) {
            double pace = computePace(student);
            int estimatedTermsNeeded = (int) Math.ceil((Curriculum.TOTAL_CREDITS_NEEDED - student.getCompletedCredits()) / pace);
            return completedTerms + estimatedTermsNeeded;
        } else {
            return -1;
        }
    }

    private double computeRisk(StudentDataResponse student) {
        int completedTerms = student.getCompletedTerms();
        int completedCredits = student.getCompletedCredits();
        if (completedTerms > 0 && completedCredits > 0) {
            int estimatedTermsNeeded = computeCourseDurationPrediction(student);
            double risk = (1.0 * (estimatedTermsNeeded - Curriculum.MAX_NUMBER_OF_TERMS)) /
                    (1.0 * Curriculum.MAX_NUMBER_OF_TERMS - 1);
            return (risk > 1.0 ? 1.0 : risk);
        } else {
            return -1.0;
        }
    }

    public static synchronized MetricsHolder getInstance() {
        if (instance == null) {
            instance = new MetricsHolder();
        }
        return instance;
    }
}
