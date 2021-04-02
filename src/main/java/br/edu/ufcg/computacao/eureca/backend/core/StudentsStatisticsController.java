package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
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
        int activeCount = this.dataAccessFacade.getActives(from, to).size();
        int alumniCount = this.dataAccessFacade.getAlumni(from, to).size();
        Collection<String> sliderLabel = this.getSliderLabel(dropouts, DropoutPerTermSummary::getDropoutTerm);
        return new DropoutsSummaryResponse(sliderLabel, dropouts);
    }

    public DelayedSummaryResponse getDelayedSummaryResponse(String from, String to) {
        Collection<DelayedPerTermSummary> terms = this.dataAccessFacade.getDelayedPerTermSummary(from, to);
        Collection<String> sliderLabel = this.getSliderLabel(terms, DelayedPerTermSummary::getAdmissionTerm);
        return new DelayedSummaryResponse(sliderLabel, terms);
    }

    public StudentsSummaryResponse getStudentsSummaryResponse(String from, String to) {
        Collection<ActivesPerTermSummary> actives = this.dataAccessFacade.getActivesPerTermSummary(from, to);
        Collection<AlumniPerTermSummary> alumni = this.dataAccessFacade.getAlumniPerTermSummary(from, to);
        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsPerTermSummary(from, to);
        Collection<DelayedPerTermSummary> delayed = this.dataAccessFacade.getDelayedPerTermSummary(from, to);

        int alumniCount = this.dataAccessFacade.getAlumni(from, to).size();
        int activesCount = actives.size();

        AlumniSummary alumniSummary = this.getAlumniSummary(alumni);
        ActivesSummary activesSummary = this.getActivesSummary(actives);
        DelayedSummary delayedSummary = this.getDelayedSummary(delayedData);
        DropoutsSummary dropoutSummary = this.getDropoutsSummary(dropouts, activesCount, alumniCount);

        return new StudentsSummaryResponse(activesSummary, alumniSummary, delayedSummary, dropoutSummary);
    }

    private <T> Collection<String> getSliderLabel(Collection<T> terms, Function<T, String> function) {
        return terms
                .stream()
                .map(function)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private ActivesSummary getActivesSummary(Collection<ActivesPerTermSummary> terms) {
        ActivesSummary summary = new ActivesSummary(0, new RiskClassCountSummary(0, 0, 0, 0, 0, 0));
        for (ActivesPerTermSummary item : terms) {
            summary.add(item.getSummary());
        }
        return summary;
    }

    private AlumniSummary getAlumniSummary(Collection<AlumniPerTermSummary> terms) {
        double accumulatedGPA = 0;
        int maxAlumniCount = 0;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        int totalAlumniCount = 0;

        for (AlumniPerTermSummary item : terms) {
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
            accumulatedGPA += averageGPA * termAlumniCount;
        }

        return new AlumniSummary((totalAlumniCount == 0 ? -1.0 : accumulatedGPA/totalAlumniCount),
                (terms.size() == 0 ? -1.0 : (1.0*totalAlumniCount)/terms.size()), maxAlumniCount, minAlumniCount,
                maxAlumniCountTerm, minAlumniCountTerm, totalAlumniCount);
    }

    private DropoutsSummary getDropoutsSummary(Collection<DropoutPerTermSummary> dropouts, int activeCount, int alumniCount) {
        DropoutReasonSummary aggregateDropouts = new DropoutReasonSummary(0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0);

        for (DropoutPerTermSummary item : dropouts) {
            aggregateDropouts.add(item.getReasons());
        }

        int dropoutCount = aggregateDropouts.getTotalDropouts() - aggregateDropouts.getReenterSameCourse();
        int enrolled = dropoutCount + activeCount + alumniCount;

        double dropoutAlumnusRate = 1.0 * dropoutCount / alumniCount;
        double dropoutEnrolledRate = 1.0 * dropoutCount / enrolled;

        return new DropoutsSummary(aggregateDropouts, dropoutCount, dropoutAlumnusRate, dropoutEnrolledRate);
    }

    private DelayedSummary getDelayedSummary(Collection<DelayedPerTermSummary> delayedStudents) {
        double totalAttemptedCredits = 0;
        double totalLoad = 0;
        double totalCost = 0;
        double totalCourseDurationPrediction = 0;
        double totalFeasibility = 0;
        double totalPace = 0;
        double totalRisk = 0;
        double totalSuccessRate = 0;
        int totalDelayed = delayedStudents.size();
        double v;

        for (DelayedPerTermSummary delayed : delayedStudents) {
            totalAttemptedCredits += delayed.getAttemptedCredits();
            totalLoad += ((v = delayed.getAverageLoad()) == -1.0 ? 0 : v);
            totalCost += ((v = delayed.getCost()) == -1.0 ? 0 : v);
            totalCourseDurationPrediction += ((v = delayed.getCourseDurationPrediction()) == -1.0 ? 0 : v);
            totalFeasibility += ((v = delayed.getFeasibility()) == -1.0 ? 0 : v);
            totalPace += ((v = delayed.getPace()) == -1.0 ? 0 : v);
            totalRisk += ((v = delayed.getRisk()) == -1.0 ? 0 : v);
            totalSuccessRate += ((v = delayed.getSuccessRate()) == -1.0 ? 0 : v);
        }

        double averageAttemptedCredits = totalAttemptedCredits / totalDelayed;
        double averageLoad = totalLoad / totalDelayed;
        double averageCost = totalCost / totalDelayed;
        double averageCourseDurationPrediction = totalCourseDurationPrediction / totalDelayed;
        double averageFeasibility = totalFeasibility / totalDelayed;
        double averagePace = totalPace / totalDelayed;
        double averageRisk = totalRisk / totalDelayed;
        double averageSuccessRate = totalSuccessRate / totalDelayed;

        return new DelayedSummary(totalDelayed, averageAttemptedCredits, averageLoad, averageCost,
                averageCourseDurationPrediction, averageFeasibility, averagePace,
                averageRisk, averageSuccessRate);
    }
}
