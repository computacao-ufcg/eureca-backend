package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.CostClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StudentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public StudentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to) {
        return this.dataAccessFacade.getAlumniPerStudentSummary(from, to);
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

    public Collection<StudentDataResponse> getDelayedCSV(String from, String to) {
        Collection<StudentDataResponse> delayedData = new TreeSet<>();
        Collection<Student> delayed = this.dataAccessFacade.getDelayed(from, to);
        delayed.forEach(item -> {
            StudentDataResponse studentDataResponse = new StudentDataResponse(item);
            delayedData.add(studentDataResponse);
        });
        return delayedData;
    }

    public ActivesSummaryResponse getActivesSummaryResponse(String from, String to) {
        Collection<ActivesPerTermSummary> terms = this.dataAccessFacade.getActivesPerTermSummary(from, to);
        Collection<String> sliderLabel = this.getSliderLabel(terms, ActivesPerTermSummary::getAdmissionTerm);
        return new ActivesSummaryResponse(sliderLabel, terms);
    }

    public AlumniSummaryResponse getAlumniSummaryResponse(String from, String to) {
        Collection<AlumniPerTermSummary> terms = this.dataAccessFacade.getAlumniPerTermSummary(from, to);
        Collection<String> sliderLabel = this.getSliderLabel(terms, AlumniPerTermSummary::getGraduationTerm);
        return new AlumniSummaryResponse(sliderLabel, terms);
    }

    public DropoutsSummaryResponse getDropoutsSummaryResponse(String from, String to) {
        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsPerTermSummary(from, to);
        Collection<String> sliderLabel = this.getSliderLabel(dropouts, DropoutPerTermSummary::getDropoutTerm);
        return new DropoutsSummaryResponse(sliderLabel, dropouts);
    }

    public DelayedSummaryResponse getDelayedSummaryResponse(String from, String to) {
        Collection<DelayedPerTermSummary> terms = this.dataAccessFacade.getDelayedPerTermSummary(from, to);
        Collection<String> sliderLabel = this.getSliderLabel(terms, DelayedPerTermSummary::getAdmissionTerm);
        return new DelayedSummaryResponse(sliderLabel, terms);
    }

    public StudentsSummaryResponse getStudentsSummaryResponse(String from, String to) {
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        Collection<AlumniPerTermSummary> alumni = this.dataAccessFacade.getAlumniPerTermSummary(from, to);
        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsPerTermSummary(from, to);
        Collection<Student> delayed = this.dataAccessFacade.getDelayed(from, to);

        ActivesSummary activesSummary = this.getActivesSummary(actives);
        AlumniSummary alumniSummary = this.getAlumniSummary(alumni);
        DelayedSummary delayedSummary = this.getDelayedSummary(delayed);
        DropoutsSummary dropoutSummary = this.getDropoutsSummary(dropouts);

        return new StudentsSummaryResponse(activesSummary, alumniSummary, delayedSummary, dropoutSummary);
    }

    private <T> Collection<String> getSliderLabel(Collection<T> terms, Function<T, String> function) {
        return terms
                .stream()
                .map(function)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private ActivesSummary getActivesSummary(Collection<Student> actives) {
        MetricsSummary summary = MetricsCalculator.computeMetricsSummary(actives);
        return new ActivesSummary(actives.size(), summary);
    }

    private AlumniSummary getAlumniSummary(Collection<AlumniPerTermSummary> terms) {
        double aggreagateTerms = 0.0;
        double aggregateCost = 0.0;
        double aggregateGPA = 0;
        int maxAlumniCount = 0;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        int totalAlumniCount = 0;

        for (AlumniPerTermSummary item : terms) {
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
            aggreagateTerms += (averageTerms * termAlumniCount);
            aggregateCost += (averageCost * termAlumniCount);
            aggregateGPA += (averageGPA * termAlumniCount);
        }

        return new AlumniSummary(totalAlumniCount, (totalAlumniCount == 0 ? -1.0 : aggreagateTerms/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateCost/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateGPA/totalAlumniCount),
                (terms.size() == 0 ? -1.0 : (1.0*totalAlumniCount)/terms.size()), maxAlumniCount, minAlumniCount,
                maxAlumniCountTerm, minAlumniCountTerm);
    }

    private DropoutsSummary getDropoutsSummary(Collection<DropoutPerTermSummary> dropouts) {
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

        int dropoutCount = aggregateDropouts.computeTotalDropouts() - aggregateDropouts.getReenterSameCourse();

        double averageTermsCount = (dropoutCount == 0 ? -1.0 : aggregateTermsCount/dropoutCount);
        double averageCost = (dropoutCount == 0 ? -1.0 : aggregateCost/dropoutCount);
        CostClass costClass = MetricsCalculator.computeCostClass(averageCost);

        return new DropoutsSummary(dropoutCount, averageTermsCount, averageCost, costClass, aggregateDropouts);
    }

    private DelayedSummary getDelayedSummary(Collection<Student> delayed) {
        MetricsSummary summary = MetricsCalculator.computeMetricsSummary(delayed);
        return new DelayedSummary(delayed.size(), summary);
    }
}