package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
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

    private <T> Collection<String> getSliderLabel(Collection<T> terms, Function<T, String> function) {
        return terms
                .stream()
                .map(function)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private synchronized AlumniSummary getAlumniSummary(Collection<AlumniPerTermSummary> terms) {
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

    public synchronized AlumniSummaryResponse getAlumniSummaryResponse(String from, String to) {
        Collection<AlumniPerTermSummary> terms = this.dataAccessFacade.getAlumniPerTermSummary(from, to);
        AlumniSummary alumniSummary = this.getAlumniSummary(terms);
        Collection<String> sliderLabel = this.getSliderLabel(terms, AlumniPerTermSummary::getGraduationTerm);
        return new AlumniSummaryResponse(sliderLabel, alumniSummary, terms);
    }

    public synchronized ActiveSummaryResponse getActiveSummaryResponse(String from, String to) {
        Collection<ActiveStatusSummary> activeStatusSummaries = new TreeSet<>();
        Collection<ActiveSummary> summary = this.dataAccessFacade.getActiveSummary(from, to);
        Collection<String> sliderLabel = this.getSliderLabel(summary, ActiveSummary::getAdmissionTerm);

        int activesCount = 0;
        int unfeasible = 0, critical = 0, late = 0, normal = 0, advanced = 0, notApplicable = 0;
        for (ActiveSummary item : summary) {
            activesCount++;
            Student active = this.dataAccessFacade.getStudent(item.getRegistration());
            RiskClass riskClass = active.getRiskClass();
            switch(riskClass) {
                case UNFEASIBLE:
                    unfeasible++;
                    break;
                case CRITICAL:
                    critical++;
                    break;
                case LATE:
                    late++;
                    break;
                case NORMAL:
                    normal++;
                    break;
                case ADVANCED:
                    advanced++;
                    break;
                default:
                    notApplicable++;
                    break;
            }
            ActiveStatusSummary statusSummary = new ActiveStatusSummary(item, riskClass);
            activeStatusSummaries.add(statusSummary);
        }

        RiskClassCountSummary countSummary = new RiskClassCountSummary(unfeasible, critical, late, normal, advanced,
                notApplicable);

        RiskClassPercentageSummary percentageSummary =
                new RiskClassPercentageSummary((1.0 * unfeasible)/activesCount,
                        (1.0 * critical)/activesCount, (1.0 * late)/activesCount,
                        (1.0 * normal)/activesCount, (1.0 * advanced)/activesCount,
                        (1.0 * notApplicable)/activesCount);
        ActiveSummaryResume riskSummary = new ActiveSummaryResume(activesCount, countSummary, percentageSummary);
        return new ActiveSummaryResponse(sliderLabel, activeStatusSummaries, riskSummary);
    }

    private synchronized DropoutSummary getDropoutSummary(Collection<DropoutPerTermSummary> dropouts, int activeCount, int alumniCount) {
        int grossDropoutCount = 0;
        int dropoutReenterSameCourse = 0;

        for (DropoutPerTermSummary item : dropouts) {
            dropoutReenterSameCourse += item.getReasons().getReenterSameCourse();
            grossDropoutCount += item.getReasons().getTotalDropouts();
        }
        int enrolled = grossDropoutCount + activeCount + alumniCount;
        int netDropoutCount = grossDropoutCount - dropoutReenterSameCourse;

        double grossDropoutAlumnusRate = 1.0 * grossDropoutCount / alumniCount;
        double grossDropoutEnrolledRate = 1.0 * grossDropoutCount / enrolled;
        double netDropoutAlumnusRate = 1.0 * netDropoutCount / alumniCount;
        double netDropoutEnrolledRate = 1.0 * netDropoutCount / enrolled;

        return new DropoutSummary(grossDropoutAlumnusRate, grossDropoutEnrolledRate, netDropoutAlumnusRate, netDropoutEnrolledRate, grossDropoutCount, netDropoutCount);
    }

    public synchronized DropoutSummaryResponse getDropoutsSummaryResponse(String from, String to) {
        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsSummary(from, to);
        Collection<String> sliderLabel = this.getSliderLabel(dropouts, DropoutPerTermSummary::getTerm);

        int activeCount = this.dataAccessFacade.getActives(from, to).size();
        int alumniCount = this.dataAccessFacade.getAlumni(from, to).size();
        DropoutSummary summary = this.getDropoutSummary(dropouts, activeCount, alumniCount);

        return new DropoutSummaryResponse(sliderLabel, dropouts, summary);
    }

    private synchronized DelayedSummary getDelayedSummary(Collection<DelayedDataResponse> delayedStudents) {
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

        for (DelayedDataResponse delayed : delayedStudents) {
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

    private synchronized ActiveSummaryResume getActiveSummaryResume(Collection<Student> actives) {
        int activesCount = actives.size();
        int advanced = 0, critical = 0, late = 0, normal = 0, notApplicable = 0, unfeasible = 0;
        for (Student active : actives) {
            switch (active.getRiskClass()) {
                case ADVANCED:
                    advanced++;
                    break;
                case CRITICAL:
                    critical++;
                    break;
                case LATE:
                    late++;
                    break;
                case NORMAL:
                    normal++;
                    break;
                case NOT_APPLICABLE:
                    notApplicable++;
                    break;
                case UNFEASIBLE:
                    unfeasible++;
                    break;
                default:
                    break;
            }
        }

        double advancedPercentage = advanced / (double) activesCount;
        double criticalPercentage = critical / (double) activesCount;
        double latePercentage = late / (double) activesCount;
        double normalPercentage = normal / (double) activesCount;
        double notApplicablePercentage = notApplicable / (double) activesCount;
        double unfeasiblePercentage = unfeasible/ (double) activesCount;

        RiskClassCountSummary riskClassCount = new RiskClassCountSummary(unfeasible, critical, late, normal, advanced, notApplicable);
        RiskClassPercentageSummary riskClassPercentage = new RiskClassPercentageSummary(unfeasiblePercentage, criticalPercentage, latePercentage,
                normalPercentage, advancedPercentage, notApplicablePercentage);

        return new ActiveSummaryResume(activesCount, riskClassCount, riskClassPercentage);
    }

    private synchronized DropoutClassification getDropoutClassification(Collection<DropoutPerTermSummary> dropouts) {
        int failed3Times = 0;
        int reenterSameCourse = 0;
        int reenterOtherCourse = 0;
        int failedAll = 0;
        int cancelled = 0;
        int cancelledByDecree = 0;
        int cancelledCourseChange = 0;
        int cancelledUponRequest = 0;
        int leftWithoutNotice = 0;
        int missedGraduation = 0;
        int transferred = 0;

        for (DropoutPerTermSummary dropout : dropouts) {
            failed3Times += dropout.getReasons().getFailed3Times();
            reenterSameCourse += dropout.getReasons().getReenterSameCourse();
            reenterOtherCourse += dropout.getReasons().getReenterOtherCourse();
            failedAll += dropout.getReasons().getFailedAll();
            cancelled += dropout.getReasons().getCancelled();
            cancelledByDecree += dropout.getReasons().getCancelledByDecree();
            cancelledCourseChange += dropout.getReasons().getCancelledCourseChange();
            cancelledUponRequest += dropout.getReasons().getCancelledUponRequest();
            leftWithoutNotice += dropout.getReasons().getLeftWithoutNotice();
            missedGraduation += dropout.getReasons().getMissedGraduation();
            transferred += dropout.getReasons().getTransferred();
        }

        int[] reasons = new int[] {
                failed3Times,
                reenterSameCourse,
                reenterOtherCourse,
                failedAll,
                cancelled,
                cancelledByDecree,
                cancelledCourseChange,
                cancelledUponRequest,
                leftWithoutNotice,
                missedGraduation,
                transferred
        };

        return new DropoutClassification(reasons);
    }

    private synchronized DropoutSummaryResume getDropoutSummaryResume(Collection<DropoutPerTermSummary> dropouts, int activeCount, int alumniCount) {
        DropoutSummary dropoutSummary = this.getDropoutSummary(dropouts, activeCount, alumniCount);
        DropoutClassification dropoutClassification = getDropoutClassification(dropouts);

        return new DropoutSummaryResume(dropoutSummary, dropoutClassification);
    }

    public synchronized StudentsSummaryResponse getStudentsSummaryResume(String from, String to) {
        Collection<Student> actives = this.dataAccessFacade.getActives(from, to);
        Collection<AlumniPerTermSummary> alumni = this.dataAccessFacade.getAlumniPerTermSummary(from, to);
        Collection<DropoutPerTermSummary> dropouts = this.dataAccessFacade.getDropoutsSummary(from, to);
        Collection<Student> delayed = this.dataAccessFacade.getDelayed(from, to);

        Collection<DelayedDataResponse> delayedData = delayed
                .stream()
                .map(DelayedDataResponse::new)
                .collect(Collectors.toList());

        int alumniCount = this.dataAccessFacade.getAlumni(from, to).size();
        int activesCount = actives.size();

        AlumniSummary alumniSummary = this.getAlumniSummary(alumni);
        ActiveSummaryResume activeSummary = this.getActiveSummaryResume(actives);
        DelayedSummary delayedSummary = this.getDelayedSummary(delayedData);
        DropoutSummaryResume dropoutSummary = this.getDropoutSummaryResume(dropouts, activesCount, alumniCount);

        return new StudentsSummaryResponse(activeSummary, alumniSummary, delayedSummary, dropoutSummary);
    }
}
