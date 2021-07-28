package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.CostClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.CollectionUtil;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;
import org.apache.log4j.Logger;

import java.util.*;

public class StudentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private final DataAccessFacade dataAccessFacade;

    public StudentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentResponse getActiveCSV(String from, String to) {
        Collection<StudentCSV> activeStudentsData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        actives.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            activeStudentsData.add(studentDataResponse);
        });
        return new StudentResponse(activeStudentsData);
    }

    public StudentResponse getAlumniCSV(String from, String to) {
        Collection<StudentCSV> alumniData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getAlumni(from, to);
        actives.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            alumniData.add(studentDataResponse);
        });
        return new StudentResponse(alumniData);
    }

    public StudentResponse getDropoutsCSV(String from, String to) {
        Collection<StudentCSV> dropoutsData = new TreeSet<>();
        Collection<Student> dropouts = this.dataAccessFacade.getDropouts(from, to);
        dropouts.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            dropoutsData.add(studentDataResponse);
        });
        return new StudentResponse(dropoutsData);
    }

    public ActivesStatisticsResponse getActivesSummaryResponse(String from, String to) {
        Collection<ActivesPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> activesPerAdmissionTerm = this.dataAccessFacade.getActivesPerAdmissionTerm(from, to);

        for (String term : activesPerAdmissionTerm.keySet()) {
            Collection<Student> actives = activesPerAdmissionTerm.get(term);
            terms.add(getActivesPerTermSummary(term, actives));
        }

        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new ActivesStatisticsResponse(terms, firstTerm, lastTerm);
    }

    public AlumniStatisticsResponse getAlumniSummaryResponse(String from, String to) {
        Collection<AlumniPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> alumniPerGraduationTerm = this.dataAccessFacade.getAlumniPerGraduationTerm(from, to);
        for (String term : alumniPerGraduationTerm.keySet()) {
            Collection<Student> alumni = alumniPerGraduationTerm.get(term);
            terms.add(getAlumniPerTermSummary(term, alumni));
        }
        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new AlumniStatisticsResponse(terms, firstTerm, lastTerm);
    }

    public DropoutsStatisticsResponse getDropoutsSummaryResponse(String from, String to) {
        Collection<DropoutPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> dropoutsPerDropoutTerm = this.dataAccessFacade.getDropoutsPerDropoutTerm(from, to);

        for (String term : dropoutsPerDropoutTerm.keySet()) {
            Collection<Student> dropouts = dropoutsPerDropoutTerm.get(term);
            terms.add(getDropoutsPerTermSummary(term, dropouts));
        }
        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new DropoutsStatisticsResponse(terms, firstTerm, lastTerm);
    }

    public StudentsStatisticsSummaryResponse getStudentsStatistics(String from, String to) {
        ActivesSummary activesSummary = getActivesSummary(from, to);
        AlumniSummary alumniSummary = getAlumniSummary(from, to);
        DropoutsSummary dropoutSummary = getDropoutsSummary(from, to);
        return new StudentsStatisticsSummaryResponse(activesSummary, alumniSummary, dropoutSummary);
    }

    private AlumniPerTermSummary getAlumniPerTermSummary(String term, Collection<Student> alumni) {
        int termAlumniCount = alumni.size();
        double aggregateGPA = 0.0;
        double aggregateTermsCount = 0.0;
        double aggregateCost = 0.0;
        for (Student alumnus : alumni) {
            aggregateGPA += alumnus.getGpa();
            aggregateTermsCount += alumnus.getCompletedTerms();
            int attemptedCredits = alumnus.getAttemptedCredits();
            int termsAccounted = alumnus.getCompletedTerms() + alumnus.getInstitutionalTerms() +
                    alumnus.getInstitutionalTerms();
            int completedCredits = alumnus.getCompletedCredits();
            aggregateCost += (StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted,
                    completedCredits).getCost());
        }

        double averageGPA = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f",
                aggregateGPA/termAlumniCount).replace(",", "."));
        double averageTermsCount = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f",
                aggregateTermsCount/termAlumniCount).replace(",", "."));
        double averageCost = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f",
                aggregateCost/termAlumniCount).replace(",", "."));
        AlumniPerTermSummary termData = new AlumniPerTermSummary(term, termAlumniCount, averageGPA,
                averageTermsCount, averageCost);
        return termData;
    }

    private DropoutPerTermSummary getDropoutsPerTermSummary(String term, Collection<Student> dropouts) {
        int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
        double aggregateTermsCount = 0.0;
        double aggregateCost = 0.0;
        for (Student dropout : dropouts) {
            dropoutsCount[dropout.getStatusIndex()]++;
            aggregateTermsCount += dropout.getCompletedTerms();
            int attemptedCredits = dropout.getAttemptedCredits();
            int termsAccounted = dropout.getCompletedTerms() + dropout.getInstitutionalTerms() +
                    dropout.getInstitutionalTerms();
            int completedCredits = dropout.getCompletedCredits();
            aggregateCost += (StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted,
                    completedCredits).getCost());
        }
        DropoutReasonSummary dropoutReasonSummary = new DropoutReasonSummary(dropoutsCount);
        int size = dropouts.size();
        double averageTerms = (size == 0 ? 0.0 : aggregateTermsCount/size);
        double averageCost = (size == 0 ? 0.0 : aggregateCost/size);
        return new DropoutPerTermSummary(term, size, dropoutReasonSummary, averageTerms, averageCost);
    }

    private ActivesPerTermSummary getActivesPerTermSummary(String term, Collection<Student> actives) {
        int inaccurate = 0;
        int safe = 0;
        int low = 0;
        int average = 0;
        int high = 0;
        int unfeasible = 0;
        int notApplicable = 0;
        for (Student student : actives) {
            RiskClass riskClass = student.computeRiskClass();
            switch (riskClass) {
                case INACCURATE:
                    inaccurate++;
                    break;
                case SAFE:
                    safe++;
                    break;
                case LOW:
                    low++;
                    break;
                case AVERAGE:
                    average++;
                    break;
                case HIGH:
                    high++;
                    break;
                case UNFEASIBLE:
                    unfeasible++;
                    break;
                case NOT_APPLICABLE:
                default:
                    notApplicable++;
                    break;
            }
        }
        RiskClassCountSummary riskClassCount = new
                RiskClassCountSummary(inaccurate, safe, low, average, high, unfeasible, notApplicable);
        ActivesPerTermSummary termData = new ActivesPerTermSummary(term, riskClassCount);
        return termData;
    }


    private ActivesSummary getActivesSummary(String from, String to) {
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        StudentMetricsSummary summary = StudentMetricsCalculator.computeMetricsSummary(actives);
        String firstTerm = CollectionUtil.getFirstTermFromStudents(actives);
        String lastTerm = CollectionUtil.getLastTermFromStudents(actives);
        return new ActivesSummary(firstTerm, lastTerm, actives.size(), summary);
    }

    private AlumniSummary getAlumniSummary(String from, String to) {
        Collection<Student> alumni = this.dataAccessFacade.getAlumni(from, to);
        double aggregateGPA = 0;
        int aggregateTermsCount = 0;
        double aggregateCost = 0.0;
        int totalAlumniCount = 0;
        Map<String, Collection<String>> alumniPerTermMap = new HashMap<>();
        for (Student alumnus : alumni) {
            aggregateGPA += alumnus.getGpa();
            aggregateTermsCount += alumnus.getCompletedTerms();
            int attemptedCredits = alumnus.getAttemptedCredits();
            int termsAccounted = alumnus.getCompletedTerms() + alumnus.getInstitutionalTerms() + alumnus.getInstitutionalTerms();
            int completedCredits = alumnus.getCompletedCredits();
            aggregateCost += (StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted, completedCredits).getCost());
            totalAlumniCount++;
            Collection<String> alumniPerTerm = alumniPerTermMap.get(alumnus.getStatusTerm());
            if (alumniPerTerm == null) {
                alumniPerTerm = new ArrayList<>();
            }
            alumniPerTerm.add(alumnus.getRegistration().toString());
            alumniPerTermMap.put(alumnus.getStatusTerm(), alumniPerTerm);
        }

        int maxAlumniCount = Integer.MIN_VALUE;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        String firstTerm = "0000.0";
        String lastTerm = "9999.9";
        int totalTermsCount = alumniPerTermMap.keySet().size();

        for(String term : alumniPerTermMap.keySet()) {
            if (term.compareTo(firstTerm) < 0) firstTerm = term;
            if (term.compareTo(lastTerm) > 0) lastTerm = term;
            Collection<String> alumniPerTerm = alumniPerTermMap.get(term);
            int size = alumniPerTerm.size();
            if (size > maxAlumniCount) {
                maxAlumniCount = size;
                maxAlumniCountTerm = term;
            }
            if (size < minAlumniCount) {
                minAlumniCount = size;
                minAlumniCountTerm = term;
            }
        }

        return new AlumniSummary(firstTerm, lastTerm, totalAlumniCount,
                (totalAlumniCount == 0 ? -1.0 : (double) aggregateTermsCount/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateCost/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateGPA/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : (double) totalAlumniCount/totalTermsCount),
                maxAlumniCount, minAlumniCount, maxAlumniCountTerm, minAlumniCountTerm);
    }

    private DropoutsSummary getDropoutsSummary(String from, String to) {
        Collection<Student> dropouts = this.dataAccessFacade.getDropouts(from, to);
        int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
        double aggregateTermsCount = 0.0;
        double aggregateCost = 0.0;
        String firstTerm = "0000.0";
        String lastTerm = "9999.9";

        for(Student dropout : dropouts) {
            aggregateTermsCount += dropout.getCompletedTerms();
            int attemptedCredits = dropout.getAttemptedCredits();
            int termsAccounted = dropout.getCompletedTerms() + dropout.getInstitutionalTerms() + dropout.getInstitutionalTerms();
            int completedCredits = dropout.getCompletedCredits();
            aggregateCost += (StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted, completedCredits).getCost());
            dropoutsCount[dropout.getStatusIndex()]++;
            String term = dropout.getAdmissionTerm();
            if (term.compareTo(firstTerm) < 0) firstTerm = term;
            if (term.compareTo(lastTerm) > 0) lastTerm = term;
        }
        DropoutReasonSummary aggregateDropouts = new DropoutReasonSummary(dropoutsCount);
        int dropoutCount = aggregateDropouts.computeTotalDropouts() - aggregateDropouts.getReenterSameCourse();

        double averageTermsCount = (dropoutCount == 0 ? -1.0 : aggregateTermsCount/dropoutCount);
        double averageCost = (dropoutCount == 0 ? -1.0 : aggregateCost/dropoutCount);
        CostClass costClass = StudentMetricsCalculator.computeCostClass(averageCost);

        return new DropoutsSummary(firstTerm, lastTerm, dropoutCount, averageTermsCount, averageCost, costClass, aggregateDropouts);
    }
}