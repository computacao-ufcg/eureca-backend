package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.RiskClassCountSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.active.ActivesPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.active.ActivesStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.active.ActivesSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutReasonSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutsSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentMetricsSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;

public class StudentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private final DataAccessFacade dataAccessFacade;

    public StudentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentsResponse getActiveCSV(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<StudentCSV> activeStudentsData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getActives(courseCode, curriculumCode, from, to);
        actives.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            activeStudentsData.add(studentDataResponse);
        });
        return new StudentsResponse(activeStudentsData);
    }

    public StudentsResponse getAlumniCSV(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<StudentCSV> alumniData = new TreeSet<>();
        Collection<Student> actives = this.dataAccessFacade.getAlumni(courseCode, curriculumCode, from, to);
        actives.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            alumniData.add(studentDataResponse);
        });
        return new StudentsResponse(alumniData);
    }

    public StudentsResponse getDropoutsCSV(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<StudentCSV> dropoutsData = new TreeSet<>();
        Collection<Student> dropouts = this.dataAccessFacade.getDropouts(courseCode, curriculumCode, from, to);
        dropouts.forEach(item -> {
            StudentCSV studentDataResponse = new StudentCSV(item);
            dropoutsData.add(studentDataResponse);
        });
        return new StudentsResponse(dropoutsData);
    }

    public ActivesStatisticsResponse getActivesStatistics(String courseCode, String curriculumCode, String from,
                                                          String to) throws EurecaException {
        Collection<ActivesPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> activesPerAdmissionTerm =
                this.dataAccessFacade.getActivesPerAdmissionTerm(courseCode, curriculumCode, from, to);

        ArrayList<String> termsList = new ArrayList<>();
        for (String term : activesPerAdmissionTerm.keySet()) {
            Collection<Student> actives = activesPerAdmissionTerm.get(term);
            terms.add(getActivesPerTermSummary(term, actives));
            termsList.add(term);
        }
        Collections.sort(termsList);
        String firstTerm = termsList.get(0);
        String lastTerm = termsList.get(termsList.size() - 1);
        return new ActivesStatisticsResponse(terms, courseCode, curriculumCode, firstTerm, lastTerm);
    }

    public AlumniStatisticsResponse getAlumniStatistics(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<AlumniPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> alumniPerGraduationTerm = this.dataAccessFacade.getAlumniPerGraduationTerm(courseCode, curriculumCode, from, to);

        ArrayList<String> termsList = new ArrayList<>();
        for (String term : alumniPerGraduationTerm.keySet()) {
            Collection<Student> alumni = alumniPerGraduationTerm.get(term);
            terms.add(getAlumniPerTermSummary(term, alumni));
            termsList.add(term);
        }
        Collections.sort(termsList);
        String firstTerm = termsList.get(0);
        String lastTerm = termsList.get(termsList.size() - 1);
        return new AlumniStatisticsResponse(terms, courseCode, curriculumCode, firstTerm, lastTerm);
    }

    public DropoutsStatisticsResponse getDropoutsStatistics(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<DropoutPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<Student>> dropoutsPerDropoutTerm = this.dataAccessFacade.getDropoutsPerDropoutTerm(courseCode, curriculumCode, from, to);

        ArrayList<String> termsList = new ArrayList();
        for (String term : dropoutsPerDropoutTerm.keySet()) {
            Collection<Student> dropouts = dropoutsPerDropoutTerm.get(term);
            terms.add(getDropoutsPerTermSummary(term, dropouts));
            termsList.add(term);
        }
        Collections.sort(termsList);
        String firstTerm = termsList.get(0);
        String lastTerm = termsList.get(termsList.size() - 1);
        return new DropoutsStatisticsResponse(terms, courseCode, curriculumCode, firstTerm, lastTerm);
    }

    public StudentsStatisticsSummaryResponse getStudentsStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        ActivesSummary activesSummary = getActivesSummary(courseCode, curriculumCode, from, to);
        AlumniSummary alumniSummary = getAlumniSummary(courseCode, curriculumCode, from, to);
        DropoutsSummary dropoutSummary = getDropoutsSummary(courseCode, curriculumCode, from, to);
        return new StudentsStatisticsSummaryResponse(courseCode, curriculumCode, activesSummary, alumniSummary, dropoutSummary);
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
            int termsAccounted = alumnus.getCompletedTerms() + alumnus.getInstitutionalEnrollments() +
                    alumnus.getInstitutionalEnrollments();
            int completedCredits = alumnus.getCompletedCredits();
            Curriculum curriculum = alumnus.getCurriculum();
            aggregateCost += (StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted,
                    completedCredits, curriculum).getCost());
        }

        double averageGPA = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f",
                aggregateGPA/termAlumniCount).replace(",", "."));
        double averageTermsCount = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f",
                aggregateTermsCount/termAlumniCount).replace(",", "."));
        double averageCost = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f",
                aggregateCost/termAlumniCount).replace(",", "."));
        return new AlumniPerTermSummary(term, termAlumniCount, averageGPA,
                averageTermsCount, averageCost);
    }

    private DropoutPerTermSummary getDropoutsPerTermSummary(String term, Collection<Student> dropouts) {
        int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
        double aggregateTermsCount = 0.0;
        double aggregateCost = 0.0;
        for (Student dropout : dropouts) {
            dropoutsCount[dropout.getStatusIndex()]++;
            aggregateTermsCount += dropout.getCompletedTerms();
            int attemptedCredits = dropout.getAttemptedCredits();
            int termsAccounted = dropout.getCompletedTerms() + dropout.getInstitutionalEnrollments() +
                    dropout.getInstitutionalEnrollments();
            int completedCredits = dropout.getCompletedCredits();
            Curriculum curriculum = dropout.getCurriculum();
            aggregateCost += (StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted,
                    completedCredits, curriculum).getCost());
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
        RiskClassCountSummary riskClassCount = new RiskClassCountSummary(inaccurate, safe, low, average, high, unfeasible, notApplicable);
        return new ActivesPerTermSummary(term, riskClassCount);
    }


    private ActivesSummary getActivesSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<Student> actives = this.dataAccessFacade.getActives(courseCode, curriculumCode, from, to);
        StudentMetricsSummary summary = StudentMetricsCalculator.computeMetricsSummary(actives);
        Range limits = getRange(actives);
        return new ActivesSummary(limits.getFrom(), limits.getTo(), actives.size(), summary);
    }

    private Range getRange(Collection<Student> actives) {
        ArrayList<String> terms = new ArrayList<>();
        actives.forEach(active -> { terms.add(active.getAdmissionTerm()); });
        Collections.sort(terms);
        return new Range(terms.get(0), terms.get(terms.size() - 1));
    }

    private AlumniSummary getAlumniSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<Student> alumni = this.dataAccessFacade.getAlumni(courseCode, curriculumCode, from, to);
        double aggregateGPA = 0;
        int aggregateTermsCount = 0;
        double aggregateCostIncrement = 0.0;
        double aggregateCost = 0.0;
        int totalAlumniCount = 0;
        Map<String, Collection<String>> alumniPerTermMap = new HashMap<>();
        for (Student alumnus : alumni) {
            aggregateGPA += alumnus.getGpa();
            aggregateTermsCount += alumnus.getCompletedTerms();
            int attemptedCredits = alumnus.getAttemptedCredits();
            int termsAccounted = alumnus.getCompletedTerms() + alumnus.getInstitutionalEnrollments() + alumnus.getInstitutionalEnrollments();
            int completedCredits = alumnus.getCompletedCredits();
            Curriculum curriculum = alumnus.getCurriculum();
            aggregateCostIncrement += ((alumnus.getCurriculum().getMinNumberOfTerms() + (alumnus.getCurriculum().getMaxNumberOfTerms() -
                    alumnus.getCurriculum().getMinNumberOfTerms()) / 4.0) / alumnus.getCurriculum().getMinNumberOfTerms()) - 1.0;
            aggregateCost += (StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted, completedCredits, curriculum).getCost());
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
        String firstTerm = "9999.9";
        String lastTerm = "0000.0";
        int totalTermsCount = alumniPerTermMap.keySet().size();

        for(String term : alumniPerTermMap.keySet()) {
            if (term.compareTo(firstTerm) < 0) {
                firstTerm = term;
            }
            if (term.compareTo(lastTerm) > 0) {
                lastTerm = term;
            }
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
                (totalAlumniCount == 0 ? -1.0 : aggregateCostIncrement/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateGPA/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : (double) totalAlumniCount/totalTermsCount),
                maxAlumniCount, minAlumniCount, maxAlumniCountTerm, minAlumniCountTerm);
    }

    private DropoutsSummary getDropoutsSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<Student> dropouts = this.dataAccessFacade.getDropouts(courseCode, curriculumCode, from, to);
        int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
        double aggregateTermsCount = 0.0;
        double aggregateCost = 0.0;
        double aggregateLowestCost = 0.0;
        String firstTerm = "9999.9";
        String lastTerm = "0000.0";

        for(Student dropout : dropouts) {
            aggregateTermsCount += dropout.getCompletedTerms();
            int attemptedCredits = dropout.getAttemptedCredits();
            int termsAccounted = dropout.getCompletedTerms() + dropout.getInstitutionalEnrollments() + dropout.getInstitutionalEnrollments();
            int completedCredits = dropout.getCompletedCredits();
            Curriculum curriculum = dropout.getCurriculum();
            aggregateCost += (StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted, completedCredits, curriculum).getCost());
            aggregateLowestCost += ((curriculum.getMinNumberOfTerms() + (curriculum.getMaxNumberOfTerms() -
                curriculum.getMinNumberOfTerms()) / 4.0) / curriculum.getMinNumberOfTerms()) - 1.0;
            dropoutsCount[dropout.getStatusIndex()]++;
            String term = dropout.getAdmissionTerm();
            if (term.compareTo(firstTerm) < 0) {
                firstTerm = term;
            }
            if (term.compareTo(lastTerm) > 0) {
                lastTerm = term;
            }
        }
        DropoutReasonSummary aggregateDropouts = new DropoutReasonSummary(dropoutsCount);
        int dropoutCount = aggregateDropouts.computeTotalDropouts() - aggregateDropouts.getReenterSameCourse();

        double averageTermsCount = (dropoutCount == 0 ? -1.0 : aggregateTermsCount/dropoutCount);
        double averageCost = (dropoutCount == 0 ? -1.0 : aggregateCost/dropoutCount);
        double averageLowestCost = (dropoutCount == 0 ? -1.0 : aggregateLowestCost/dropoutCount);
        CostClass costClass = StudentMetricsCalculator.computeCostClass(averageCost, averageLowestCost);

        return new DropoutsSummary(firstTerm, lastTerm, dropoutCount, averageTermsCount, averageCost, costClass, aggregateDropouts);
    }

}