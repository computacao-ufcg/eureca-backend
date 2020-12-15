package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class MetricsCalculator {
    private Logger LOGGER = Logger.getLogger(MetricsCalculator.class);

    private Map<Registration, Integer> attemptsMap;
    private static MetricsCalculator instance;

    private MetricsCalculator() {
        this.attemptsMap = new HashMap();
        DataAccessFacade dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
        Collection<AttemptsSummary> attemptsSummaries = dataAccessFacade.getAttemptsSummary();
        attemptsSummaries.forEach(item -> {
            this.attemptsMap.put(item.getRegistration(), new Integer(item.getAttemptedCredits()));
        });
    }

    public static synchronized MetricsCalculator getInstance() {
        if (instance == null) {
            instance = new MetricsCalculator();
        }
        return instance;
    }

    public static void create() {
        MetricsCalculator.getInstance();
    }

    public Metrics computeMetrics(Student student) {
        try {
            Registration registration = new Registration(student.getId().getRegistration());
            Integer attemptedCredits = this.attemptsMap.get(registration);
            double feasibility = computeFeasibility(student);
            double successRate = computeSuccessRate(student);
            double averageLoad = computeAverageLoad(student);
            double cost = computeCost(student);
            double pace = computePace(student);
            int courseDurationPrediction = computeCourseDurationPrediction(student);
            double risk = computeRisk(student);
            return new Metrics(attemptedCredits, feasibility, successRate, averageLoad, cost, pace,
                    courseDurationPrediction, risk);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private double computeFeasibility(Student student) {
        int completed = student.getStudentData().getCompletedTerms();
        if (completed > 0 && completed < Curriculum.MAX_NUMBER_OF_TERMS) {
            return (1.0 * (Curriculum.TOTAL_CREDITS_NEEDED - student.getStudentData().getCompletedCredits())) /
                    ((Curriculum.MAX_NUMBER_OF_TERMS - student.getStudentData().getCompletedTerms()) *
                            Curriculum.MAX_NUMBER_OF_CREDITS + Curriculum.EXCEPTIONAL_ADDITIONAL_CREDITS);
        } else {
            return -1.0;
        }
    }

    private double computeSuccessRate(Student student) {
        int completed = student.getStudentData().getCompletedCredits();
        Integer attempted = this.attemptsMap.get(new Registration(student.getId().getRegistration()));
        if (attempted != null && attempted.intValue() > 0) {
            return (1.0 * completed) / attempted.intValue();
        } else {
            return -1.0;
        }
    }

    private double computeAverageLoad(Student student) {
        int completedTerms = student.getStudentData().getCompletedTerms();
        Integer attempted = this.attemptsMap.get(new Registration(student.getId().getRegistration()));
        if (attempted != null && completedTerms > 0) {
            return (1.0 * attempted.intValue()) / completedTerms;
        } else {
            return -1.0;
        }
    }

    private double computeCost(Student student) {
        double rate = computeSuccessRate(student);
        double averageLoad = computeAverageLoad(student);
        if (rate > 0 && averageLoad > 0) {
            return ((1.0 * Curriculum.TOTAL_CREDITS_NEEDED / Curriculum.MIN_NUMBER_OF_TERMS) / (rate * averageLoad));
        } else {
            return -1.0;
        }
    }

    private double computePace(Student student) {
        int completedTerms = student.getStudentData().getCompletedTerms();
        if (completedTerms > 0) {
            return 1.0 * student.getStudentData().getCompletedCredits() / completedTerms;
        } else {
            return -1.0;
        }
    }

    private int computeCourseDurationPrediction(Student student) {
        int completedTerms = student.getStudentData().getCompletedTerms();
        int completedCredits = student.getStudentData().getCompletedCredits();
        if (completedTerms > 0 && completedCredits > 0) {
            double pace = computePace(student);
            int estimatedTermsNeeded = (int) Math.ceil((Curriculum.TOTAL_CREDITS_NEEDED -
                    student.getStudentData().getCompletedCredits()) / pace);
            return completedTerms + estimatedTermsNeeded;
        } else {
            return -1;
        }
    }

    private double computeRisk(Student student) {
        int completedTerms = student.getStudentData().getCompletedTerms();
        int completedCredits = student.getStudentData().getCompletedCredits();
        if (completedTerms > 0 && completedCredits > 0) {
            int estimatedTermsNeeded = computeCourseDurationPrediction(student);
            double risk = (1.0 * (estimatedTermsNeeded - Curriculum.MAX_NUMBER_OF_TERMS)) /
                    (1.0 * Curriculum.MAX_NUMBER_OF_TERMS - 1);
            return (Math.min(risk, 1.0));
        } else {
            return -1.0;
        }
    }
}
