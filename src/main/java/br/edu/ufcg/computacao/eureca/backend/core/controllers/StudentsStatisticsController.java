package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.CostClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.CollectionUtil;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

public class StudentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private final DataAccessFacade dataAccessFacade;

    public StudentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public Collection<StudentDataResponse> getActiveCSV(String from, String to) {
        Collection<StudentDataResponse> activeStudentsData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        actives.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            activeStudentsData.add(studentDataResponse);
        });
        return activeStudentsData;
    }

    public Collection<StudentDataResponse> getAlumniCSV(String from, String to) {
        Collection<StudentDataResponse> alumniData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getAlumni(from, to);
        actives.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            alumniData.add(studentDataResponse);
        });
        return alumniData;
    }

    public Collection<StudentDataResponse> getDropoutsCSV(String from, String to) {
        Collection<StudentDataResponse> dropoutsData = new TreeSet<>();
        Collection<Student> dropouts = this.dataAccessFacade.getDropouts(from, to);
        dropouts.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            dropoutsData.add(studentDataResponse);
        });
        return dropoutsData;
    }

    public ActivesSummaryResponse getActivesSummaryResponse(String from, String to) {
        Collection<ActivesPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> activesPerAdmissionTerm = this.dataAccessFacade.getActivesPerAdmissionTerm(from, to);

        for (String term : activesPerAdmissionTerm.keySet()) {
            Collection<Student> actives = activesPerAdmissionTerm.get(term);
            RiskClassCountSummary riskClassCount = getRiskClassCountSummary(actives);
            ActivesPerTermSummary termData = new ActivesPerTermSummary(term, riskClassCount);
            terms.add(termData);
        }

        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new ActivesSummaryResponse(terms, firstTerm, lastTerm);
    }

    public AlumniSummaryResponse getAlumniSummaryResponse(String from, String to) {
        Collection<AlumniPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> alumniPerGraduationTerm = this.dataAccessFacade.getAlumniPerGraduationTerm(from, to);

        for (String term : alumniPerGraduationTerm.keySet()) {
            Collection<Student> alumni = alumniPerGraduationTerm.get(term);
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
                aggregateCost += (MetricsCalculator.computeMetrics(attemptedCredits, termsAccounted,
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
            terms.add(termData);
        }
        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new AlumniSummaryResponse(terms, firstTerm, lastTerm);
    }

    public DropoutsSummaryResponse getDropoutsSummaryResponse(String from, String to) {
        Collection<DropoutPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> dropoutsPerDropoutTerm = this.dataAccessFacade.getDropoutsPerDropoutTerm(from, to);

        for (String term : dropoutsPerDropoutTerm.keySet()) {
            int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
            double aggregateTermsCount = 0.0;
            double aggregateCost = 0.0;
            Collection<Student> dropouts = dropoutsPerDropoutTerm.get(term);
            for (Student dropout : dropouts) {
                dropoutsCount[dropout.getStatusIndex()]++;
                aggregateTermsCount += dropout.getCompletedTerms();
                int attemptedCredits = dropout.getAttemptedCredits();
                int termsAccounted = dropout.getCompletedTerms() + dropout.getInstitutionalTerms() +
                        dropout.getInstitutionalTerms();
                int completedCredits = dropout.getCompletedCredits();
                aggregateCost += (MetricsCalculator.computeMetrics(attemptedCredits, termsAccounted,
                        completedCredits).getCost());
            }
            DropoutReasonSummary dropoutReasonSummary = new DropoutReasonSummary(dropoutsCount);
            int size = dropouts.size();
            double averageTerms = (size == 0 ? 0.0 : aggregateTermsCount/size);
            double averageCost = (size == 0 ? 0.0 : aggregateCost/size);
            terms.add(new DropoutPerTermSummary(term, size, dropoutReasonSummary, averageTerms, averageCost));
        }
        String firstTerm = CollectionUtil.getFirstTermFromSummaries(terms);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(terms);
        return new DropoutsSummaryResponse(terms, firstTerm, lastTerm);
    }

    public StudentsSummaryResponse getStudentsStatistics(String from, String to) {
        ActivesSummary activesSummary = getActivesSummary(from, to);
        AlumniSummary alumniSummary = getAlumniSummary(from, to);
        DropoutsSummary dropoutSummary = getDropoutsSummary(from, to);
        return new StudentsSummaryResponse(activesSummary, alumniSummary, dropoutSummary);
    }

    private ActivesSummary getActivesSummary(String from, String to) {
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        MetricsSummary summary = MetricsCalculator.computeMetricsSummary(actives);
        String firstTerm = CollectionUtil.getFirstTermFromStudents(actives);
        String lastTerm = CollectionUtil.getLastTermFromStudents(actives);
        return new ActivesSummary(actives.size(), summary, firstTerm, lastTerm);
    }

    private AlumniSummary getAlumniSummary(String from, String to) {
        Collection<AlumniPerTermSummary> alumniPerTermSummaries = this.dataAccessFacade.getAlumniPerTermSummary(from, to);

        double aggregateTerms = 0.0;
        double aggregateCost = 0.0;
        double aggregateGPA = 0;
        int maxAlumniCount = 0;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        int totalAlumniCount = 0;

        for (AlumniPerTermSummary item : alumniPerTermSummaries) {
            double averageTerms = item.getAverageTerms();
            double averageCost = item.getAverageCost();
            double averageGPA = item.getAverageGpa();
            int termAlumniCount = item.getAlumniCount();
            String term = item.getGraduationTerm();

            totalAlumniCount += termAlumniCount;
            if (termAlumniCount >= maxAlumniCount) {
                maxAlumniCount = termAlumniCount;
                maxAlumniCountTerm = term;
            }
            if (termAlumniCount <= minAlumniCount) {
                minAlumniCount = termAlumniCount;
                minAlumniCountTerm = term;
            }
            aggregateTerms += (averageTerms * termAlumniCount);
            aggregateCost += (averageCost * termAlumniCount);
            aggregateGPA += (averageGPA * termAlumniCount);
        }

        String firstTerm = CollectionUtil.getFirstTermFromSummaries(alumniPerTermSummaries);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(alumniPerTermSummaries);

        return new AlumniSummary(totalAlumniCount, (totalAlumniCount == 0 ? -1.0 : aggregateTerms/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateCost/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateGPA/totalAlumniCount),
                (alumniPerTermSummaries.size() == 0 ? -1.0 : (1.0*totalAlumniCount)/alumniPerTermSummaries.size()), maxAlumniCount, minAlumniCount,
                maxAlumniCountTerm, minAlumniCountTerm, firstTerm, lastTerm);
    }

    private DropoutsSummary getDropoutsSummary(String from, String to) {
        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsPerTermSummary(from, to);

        DropoutReasonSummary aggregateDropouts = new DropoutReasonSummary(0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0);
        double aggregateTermsCount = 0.0;
        double aggregateCost = 0.0;

        for (DropoutPerTermSummary item : dropouts) {
            aggregateTermsCount += (item.getAverageTerms() * item.getDropoutCount());
            aggregateCost += (item.getAverageCost() * item.getDropoutCount());
            aggregateDropouts.add(item.getReasons());
        }

        String firstTerm = CollectionUtil.getFirstTermFromSummaries(dropouts);
        String lastTerm = CollectionUtil.getLastTermFromSummaries(dropouts);
        int dropoutCount = aggregateDropouts.computeTotalDropouts() - aggregateDropouts.getReenterSameCourse();

        double averageTermsCount = (dropoutCount == 0 ? -1.0 : aggregateTermsCount/dropoutCount);
        double averageCost = (dropoutCount == 0 ? -1.0 : aggregateCost/dropoutCount);
        CostClass costClass = MetricsCalculator.computeCostClass(averageCost);

        return new DropoutsSummary(dropoutCount, averageTermsCount, averageCost, costClass, aggregateDropouts, firstTerm, lastTerm);
    }

    private RiskClassCountSummary getRiskClassCountSummary(Collection<Student> students) {
        int inaccurate = 0;
        int safe = 0;
        int low = 0;
        int average = 0;
        int high = 0;
        int unfeasible = 0;
        int notApplicable = 0;
        for (Student student : students) {
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
        return riskClassCount;
    }
}