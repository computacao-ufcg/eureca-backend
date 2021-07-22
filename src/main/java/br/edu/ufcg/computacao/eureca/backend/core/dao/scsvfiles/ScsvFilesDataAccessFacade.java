package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class ScsvFilesDataAccessFacade implements DataAccessFacade {
    private final Logger LOGGER = Logger.getLogger(ScsvFilesDataAccessFacade.class);
    private MapsHolder mapsHolder;
    private IndexesHolder indexesHolder;

    public ScsvFilesDataAccessFacade(String mapsListFile) {
        this.mapsHolder = new MapsHolder(mapsListFile);
        this.indexesHolder = new IndexesHolder(this.mapsHolder);
    }

    @Override
    public Collection<Student> getActives(String from, String to) {
        return getFilteredStudents(StudentClassification.ACTIVE, from, to);
    }

    @Override
    public Collection<Student> getAlumni(String from, String to) {
        return getFilteredStudents(StudentClassification.ALUMNUS, from, to);
    }

    @Override
    public Collection<Student> getDropouts(String from, String to) {
        return getFilteredStudents(StudentClassification.DROPOUT, from, to);
    }

    @Override
    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerTermPerSubject(String from, String to, String courseCode, String curriculumCode) {
        return this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass(from, to);
    }

    @Override
    public Collection<String> getCurriculumCodes(String courseCode) {
        return this.indexesHolder.getCurricula(courseCode);
    }

    @Override
    public Collection<SubjectMetricsPerTerm> getSubjectMetricsPerTerm(String from, String to, String courseCode,
                                                                      String curriculumCode, String subjectCode) {
        Collection<SubjectMetricsPerTerm> response = new TreeSet<>();
        Map<String, Map<String, ClassEnrollments>> terms = this.indexesHolder.getSubjectMetricsPerTerm(from, to, courseCode, curriculumCode, subjectCode);
        for (String term : terms.keySet()) {
            int failedDueToAbsences = 0;
            int failedDueToGrade = 0;
            int cancelled = 0;
            int succeeded = 0;
            int ongoing = 0;
            int exempted = 0;
            int totalEnrolled = 0;
            Map<String, ClassEnrollments> classes = terms.get(term);
            for (ClassEnrollments enrollments: classes.values()) {
                failedDueToAbsences += enrollments.getNumberFailedDueToAbsence();
                failedDueToGrade += enrollments.getNumberFailedDueToGrade();
                cancelled += enrollments.getNumberCancelled();
                succeeded += enrollments.getNumberSucess();
                ongoing += enrollments.getNumberOngoing();
                exempted += enrollments.getNumberExempted();
                totalEnrolled += enrollments.getNumberOfEnrolleds();
            }
            SubjectMetrics metrics = new SubjectMetrics(failedDueToAbsences, failedDueToGrade, cancelled,
                    succeeded, ongoing, exempted, totalEnrolled);
            SubjectMetricsPerTerm metricsPerTerm = new SubjectMetricsPerTerm(term, metrics);
            response.add(metricsPerTerm);
        }
        return response;
    }

    @Override
    public Collection<ActivesPerTermSummary> getActivesPerTermSummary(String from, String to) {
        Collection<ActivesPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getActiveByAdmissionTerm();
        Map<NationalIdRegistrationKey, StudentData> studentsMap = mapsHolder.getMap("students");

        for (Map.Entry<String, Collection<NationalIdRegistrationKey>> entry : index.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                RiskClassCountSummary riskClassCount = getRiskClassCountSummary(entry.getValue(), studentsMap);
                ActivesPerTermSummary termData = new ActivesPerTermSummary(term, riskClassCount);
                terms.add(termData);
            }
        }
        return terms;
    }

    @Override
    public Collection<AlumniPerTermSummary> getAlumniPerTermSummary(String from, String to) {
        Collection<AlumniPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<NationalIdRegistrationKey>> map = indexesHolder.getAlumniByGraduationTerm();
        Map<NationalIdRegistrationKey, StudentData> studentsMap = mapsHolder.getMap("students");
        for (Map.Entry<String, Collection<NationalIdRegistrationKey>> entry : map.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Collection<NationalIdRegistrationKey> studentIds = entry.getValue();
                int termAlumniCount = studentIds.size();
                double aggregateGPA = 0.0;
                double aggregateTermsCount = 0.0;
                double aggregateCost = 0.0;
                for (NationalIdRegistrationKey id : studentIds) {
                    StudentData alumnus = studentsMap.get(id);
                    aggregateGPA += alumnus.getGpa();
                    aggregateTermsCount += alumnus.getCompletedTerms();
                    int attemptedCredits = alumnus.getAttemptedCredits();
                    int termsAccounted = alumnus.getCompletedTerms() + alumnus.getInstitutionalTerms() +
                            alumnus.getInstitutionalTerms();
                    int completedCredits = alumnus.getCompletedCredits();
                    aggregateCost += (MetricsCalculator.computeMetrics(attemptedCredits, termsAccounted,
                            completedCredits).getCost());
                }

                double averageGPA = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f", aggregateGPA/termAlumniCount).replace(",", "."));
                double averageTermsCount = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f", aggregateTermsCount/termAlumniCount).replace(",", "."));
                double averageCost = termAlumniCount == 0 ? 0.0 : Double.parseDouble(String.format("%.2f", aggregateCost/termAlumniCount).replace(",", "."));

                AlumniPerTermSummary termData = new AlumniPerTermSummary(term, termAlumniCount,
                        averageGPA,
                        averageTermsCount,
                        averageCost
                );
                terms.add(termData);
            }
        }
        return terms;
    }

    @Override
    public Collection<DropoutPerTermSummary> getDropoutsPerTermSummary(String from, String to) {
        Collection<DropoutPerTermSummary> dropoutSummaryResponses = new TreeSet<>();
        Map<String, Collection<NationalIdRegistrationKey>> dropouts = this.indexesHolder.getDropoutByDropoutTerm();
        Map<NationalIdRegistrationKey, StudentData> studentsMap = this.mapsHolder.getMap("students");
        dropouts.forEach((k, v) -> {
            if (k.compareTo(from) >= 0 && k.compareTo(to) <= 0) {
                int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
                double aggregateTermsCount = 0.0;
                double aggregateCost = 0.0;
                for (NationalIdRegistrationKey id : v) {
                    StudentData dropout = studentsMap.get(id);
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
                int size = v.size();
                double averageTerms = (size == 0 ? 0.0 : aggregateTermsCount/size);
                double averageCost = (size == 0 ? 0.0 : aggregateCost/size);
                dropoutSummaryResponses.add(new DropoutPerTermSummary(k, size, dropoutReasonSummary, averageTerms, averageCost));
            }
        });
        return dropoutSummaryResponses;
    }

    @Override
    public Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to) {
        String parsedFrom = "1" + from.substring(2,4) + from.substring(5,6) + "00000";
        String parsedTo = "1" + to.substring(2,4) + to.substring(5,6) + "99999";
        Collection<AlumniDigestResponse> alumniBasicData = new TreeSet<>();
        Collection<NationalIdRegistrationKey> alumni = this.indexesHolder.getAlumni();
        Map<NationalIdRegistrationKey, StudentData> studentsMap = this.mapsHolder.getMap("students");
        for (NationalIdRegistrationKey item : alumni) {
            if (new Registration(item.getRegistration()).compareTo(new Registration(parsedFrom)) >= 0 &&
                    new Registration(item.getRegistration()).compareTo(new Registration(parsedTo)) <= 0) {
                StudentData alumnus = studentsMap.get(item);
                AlumniDigestResponse basicData = new AlumniDigestResponse(item.getRegistration(), alumnus.getName(),
                        2, 1, alumnus.getAdmissionTerm(), alumnus.getStatusTerm());
                alumniBasicData.add(basicData);
            }
        }
        return alumniBasicData;
    }

    @Override
    public StudentsSummaryResponse getStudentsStatistics(String from, String to) {
        ActivesSummary activesSummary = getActivesSummary(from, to);
        AlumniSummary alumniSummary = getAlumniSummary(from, to);
        DropoutsSummary dropoutSummary = getDropoutsSummary(from, to);
        return new StudentsSummaryResponse(activesSummary, alumniSummary, dropoutSummary);
    }

    @Override
    public ActivesSummaryResponse getActivesSummaryResponse(String from, String to) {
        Collection<ActivesPerTermSummary> activesPerTermSummaries = this.getActivesPerTermSummary(from, to);
        String firstTerm = this.getFirstTermFromSummaries(activesPerTermSummaries);
        String lastTerm = this.getLastTermFromSummaries(activesPerTermSummaries);
        return new ActivesSummaryResponse(activesPerTermSummaries, firstTerm, lastTerm);
    }

    @Override
    public AlumniSummaryResponse getAlumniSummaryResponse(String from, String to) {
        Collection<AlumniPerTermSummary> alumniPerTermSummaries = this.getAlumniPerTermSummary(from, to);
        String firstTerm = this.getFirstTermFromSummaries(alumniPerTermSummaries);
        String lastTerm = this.getLastTermFromSummaries(alumniPerTermSummaries);
        return new AlumniSummaryResponse(alumniPerTermSummaries, firstTerm, lastTerm);
    }

    @Override
    public DropoutsSummaryResponse getDropoutsSummaryResponse(String from, String to) {
        Collection<DropoutPerTermSummary> dropoutPerTermSummaries = this.getDropoutsPerTermSummary(from, to);
        String firstTerm = this.getFirstTermFromSummaries(dropoutPerTermSummaries);
        String lastTerm = this.getLastTermFromSummaries(dropoutPerTermSummaries);
        return new DropoutsSummaryResponse(dropoutPerTermSummaries, firstTerm, lastTerm);
    }

    @Override
    public Curriculum getCurriculum(String course, String code) {
        Map<CurriculumKey, CurriculumData> curriculumMap = this.mapsHolder.getMap("curriculum");
        CurriculumKey key = new CurriculumKey(course, code);
        CurriculumData ret = curriculumMap.get(key);
        return ret.getCurriculum(key);
    }

    @Override
    public Subject getSubject(String courseCode, String curriculumCode, String subjectCode) {
        Map<SubjectKey, SubjectData> subjectMap = this.mapsHolder.getMap("subjects");
        SubjectKey key = new SubjectKey(courseCode, curriculumCode, subjectCode);
        SubjectData subjectData = subjectMap.get(key);
        return subjectData.createSubject(key);
    }

    @Override
    public MetricStatistics getSucceededStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_SUCCEEDED);
    }

    @Override
    public MetricStatistics getExemptedStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_EXEMPTED);
    }

    @Override
    public MetricStatistics getOngoingStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_ONGOING);
    }

    @Override
    public MetricStatistics getFailedDueToGradeStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_FAILED_DUE_GRADE);
    }

    @Override
    public MetricStatistics getFailedDueToAbsencesStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_FAILED_DUE_ABSENCE);
    }

    @Override
    public MetricStatistics getSuspendedStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_SUSPENDED);
    }

    @Override
    public MetricStatistics getCancelledStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_CANCELLED);
    }

    @Override
    public Collection<Student> getDelayed(String from, String to) {
        return this.getActives(from, to)
                .stream()
                .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                        item.computeRiskClass().equals(RiskClass.HIGH) ||
                        item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<DelayedPerTermSummary> getDelayedPerTermSummary(String from, String to) {
        Collection<DelayedPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getActiveByAdmissionTerm();
        Map<NationalIdRegistrationKey, StudentData> studentsMap = mapsHolder.getMap("students");

        for (Map.Entry<String, Collection<NationalIdRegistrationKey>> entry : index.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Collection<Student> delayed = new TreeSet<>();
                entry.getValue().forEach(cpfRegistration -> {
                    StudentData studentData = studentsMap.get(cpfRegistration);
                    delayed.add(studentData.createStudent(cpfRegistration));
                });
                MetricsSummary metricsSummary = MetricsCalculator.computeMetricsSummary(delayed);
                DelayedPerTermSummary termData = new DelayedPerTermSummary(term, metricsSummary);
                terms.add(termData);
            }
        }
        return terms;
    }

    @Override
    public DelayedSummary getDelayedSummary(String from, String to) {
        Collection<Student> delayed = this.getDelayed(from, to);
        MetricsSummary summary = MetricsCalculator.computeMetricsSummary(delayed);
        String firstTerm = this.getFirstTermFromStudents(delayed);
        String lastTerm = this.getLastTermFromStudents(delayed);
        return new DelayedSummary(delayed.size(), summary, firstTerm, lastTerm);
    }

    @Override
    public SubjectRetentionSummary getSubjectRetentionSummary(String courseCode, String curriculumCode) {
        int subjectCount = 0;
        int minimum;
        int maximum;
        double firstQuartile;
        double median;
        double thirdQuartile;
        double average;

        Curriculum curriculum = this.getCurriculum(courseCode, curriculumCode);
        Collection<String> subjects = curriculum.getMandatorySubjectsList();
        Collection<Integer> retentionSet = new TreeSet<>();
        int totalRetention = 0;
        for (String subjectCode : subjects) {
            subjectCount++;
            int retention = getRetentionCount(courseCode, curriculumCode, subjectCode);
            totalRetention += retention;
            retentionSet.add(retention);
        }
        Integer[] retentionArray = (Integer[]) retentionSet.toArray();
        int size = retentionArray.length;
        minimum = retentionArray[0];
        maximum = retentionArray[size-1];
        average = (double) totalRetention/size;
        if (size >= 4) {
            int quartilSize = size / 4;
            firstQuartile = retentionArray[(quartilSize == 0 ? 0 : quartilSize - 1)];
            if (size % 2 == 0) {
                median = (double) (retentionArray[2 * quartilSize] + retentionArray[2 * quartilSize + 1]) / 2;
            } else {
                median = (double) retentionArray[2 * quartilSize];
            }
            thirdQuartile = retentionArray[3 * quartilSize - 1];
        } else {
            firstQuartile = -1;
            median = -1;
            thirdQuartile = -1;
        }
        SubjectRetentionSummary response = new SubjectRetentionSummary(subjectCount, minimum, maximum,
                firstQuartile, median, thirdQuartile, average);
        return response;
    }

    @Override
    public DelayedSummaryResponse getDelayedSummaryResponse(String from, String to) {
        Collection<DelayedPerTermSummary> delayedPerTermSummaries = this.getDelayedPerTermSummary(from, to);
        String firstTerm = this.getFirstTermFromSummaries(delayedPerTermSummaries);
        String lastTerm = this.getLastTermFromSummaries(delayedPerTermSummaries);
        return new DelayedSummaryResponse(delayedPerTermSummaries, firstTerm, lastTerm);
    }

    @Override
    public Collection<SubjectRetentionSummaryResponse> getSubjectsRetentionSummary(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionSummaryResponse> response = new TreeSet<>();
        Curriculum curriculum = this.getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        Collection<String> subjectCodes = curriculum.getMandatorySubjectsList();
        subjectCodes.forEach(item -> {
            int retention = getRetentionCount(courseCode, curriculumCode, item);
            Subject subject = getSubject(courseCode, curriculumCode, item);
            response.add(new SubjectRetentionSummaryResponse(subject.getIdealTerm(), item, subject.getName(), retention));
        });
        return response;
    }

    @Override
    public Collection<SubjectRetentionResponse> getSubjectsRetention(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionResponse> response = new TreeSet<>();
        Curriculum curriculum = this.getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        Collection<String> subjectCodes = curriculum.getMandatorySubjectsList();
        subjectCodes.forEach(item -> {
            response.addAll(this.indexesHolder.getRetention(courseCode, curriculumCode, item));
        });
        return response;
    }

    @Override
    public TreeSet<String> getTermsForCurriculum(String courseCode, String curriculum) {
        return this.indexesHolder.getTermsPerCurriculum(courseCode, curriculum);
    }

    @Override
    public int getNumberOfClassesPerSubject(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getNumberOfClassesPerSubject(from, to, courseCode, curriculumCode, subjectCode);
    }

    private ActivesSummary getActivesSummary(String from, String to) {
        Collection<Student> actives = this.getActives(from, to);
        MetricsSummary summary = MetricsCalculator.computeMetricsSummary(actives);
        String firstTerm = this.getFirstTermFromStudents(actives);
        String lastTerm = this.getLastTermFromStudents(actives);
        return new ActivesSummary(actives.size(), summary, firstTerm, lastTerm);
    }

    private AlumniSummary getAlumniSummary(String from, String to) {
        Collection<AlumniPerTermSummary> alumniPerTermSummaries = this.getAlumniPerTermSummary(from, to);

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

        String firstTerm = this.getFirstTermFromSummaries(alumniPerTermSummaries);
        String lastTerm = this.getLastTermFromSummaries(alumniPerTermSummaries);

        return new AlumniSummary(totalAlumniCount, (totalAlumniCount == 0 ? -1.0 : aggregateTerms/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateCost/totalAlumniCount),
                (totalAlumniCount == 0 ? -1.0 : aggregateGPA/totalAlumniCount),
                (alumniPerTermSummaries.size() == 0 ? -1.0 : (1.0*totalAlumniCount)/alumniPerTermSummaries.size()), maxAlumniCount, minAlumniCount,
                maxAlumniCountTerm, minAlumniCountTerm, firstTerm, lastTerm);
    }

    private DropoutsSummary getDropoutsSummary(String from, String to) {
        Collection<DropoutPerTermSummary> dropouts = this.getDropoutsPerTermSummary(from, to);

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

        String firstTerm = this.getFirstTermFromSummaries(dropouts);
        String lastTerm = this.getLastTermFromSummaries(dropouts);
        int dropoutCount = aggregateDropouts.computeTotalDropouts() - aggregateDropouts.getReenterSameCourse();

        double averageTermsCount = (dropoutCount == 0 ? -1.0 : aggregateTermsCount/dropoutCount);
        double averageCost = (dropoutCount == 0 ? -1.0 : aggregateCost/dropoutCount);
        CostClass costClass = MetricsCalculator.computeCostClass(averageCost);

        return new DropoutsSummary(dropoutCount, averageTermsCount, averageCost, costClass, aggregateDropouts, firstTerm, lastTerm);
    }

    private int getRetentionCount(String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getRetentionCount(courseCode, curriculumCode, subjectCode);
    }

    private String getFirstTermFromSummaries(Collection<? extends SummaryPerTerm> summaries) {
        return this.getTermsFromSummaries(summaries).first();
    }

    private String getLastTermFromSummaries(Collection<? extends SummaryPerTerm> summaries) {
        return this.getTermsFromSummaries(summaries).last();
    }

    private TreeSet<String> getTermsFromSummaries(Collection<? extends SummaryPerTerm> summaryPerTerms) {
        return summaryPerTerms
                .stream()
                .map(SummaryPerTerm::getTerm)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private String getFirstTermFromStudents(Collection<Student> students) {
        return this.getTermsFromStudents(students).first();
    }

    private String getLastTermFromStudents(Collection<Student> students) {
        return this.getTermsFromStudents(students).last();
    }

    private TreeSet<String> getTermsFromStudents(Collection<Student> students) {
        return students
                .stream()
                .map(Student::getAdmissionTerm)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private MetricStatistics getEnrollmentsStatistics(String from, String to, String courseCode, String curriculumCode,
                                                      String subjectCode, String status) {
        final int[] min = {Integer.MAX_VALUE};
        final int[] max = {0};
        final int[] total = {0};
        Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> allEnrollments =
                this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass(courseCode, curriculumCode);
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        if (allEnrollments != null) {
            Map<String, Map<String, ClassEnrollments>> enrollments = allEnrollments.get(subjectKey);
            if (enrollments != null) {
                enrollments.forEach((term, classes) -> {
                    if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                        classes.forEach((classId, classEnrollments) -> {
                            int numberOfEnrollments = getClassEnrollmentsPerStatus(classEnrollments, status);
                            if (min[0] > numberOfEnrollments) min[0] = numberOfEnrollments;
                            if (max[0] < numberOfEnrollments) max[0] = numberOfEnrollments;
                            total[0] += numberOfEnrollments;
                        });
                    }
                });
            }
        }
        MetricStatistics ret = new MetricStatistics(min[0], max[0], total[0]);
        return ret;
    }

    private int getClassEnrollmentsPerStatus(ClassEnrollments classEnrollments, String status) {
        switch(status) {
            case SystemConstants.STATUS_SUCCEEDED:
                return classEnrollments.getNumberSucess();
            case SystemConstants.STATUS_EXEMPTED:
                return classEnrollments.getNumberExempted();
            case SystemConstants.STATUS_ONGOING:
                return classEnrollments.getNumberOngoing();
            case SystemConstants.STATUS_FAILED_DUE_GRADE:
                return classEnrollments.getNumberFailedDueToGrade();
            case SystemConstants.STATUS_FAILED_DUE_ABSENCE:
                return classEnrollments.getNumberFailedDueToAbsence();
            case SystemConstants.STATUS_SUSPENDED:
                return classEnrollments.getNumberSuspended();
            default:
                return classEnrollments.getNumberCancelled();
        }
    }

    private Collection<Student> getFilteredStudents(StudentClassification status, String from, String to) {
        Collection<Student> filteredStudents = new TreeSet<>();
        Collection<Student> allStudents = getAllStudentsByStatus(status);
        allStudents.forEach(item -> {
            String studentTerm = getGroupingTerm(status, item);
            if (studentTerm != null && studentTerm.compareTo(from) >= 0 && studentTerm.compareTo(to) <= 0) {
                filteredStudents.add(item);
            }
        });
        return filteredStudents;
    }

    private String getGroupingTerm(StudentClassification status, Student item) {
        switch(status) {
            case ALUMNUS:
            case DROPOUT:
                return item.getStatusTerm();
            case ACTIVE:
            case DELAYED:
            default:
                return item.getAdmissionTerm();
        }
    }

    private Collection<Student> getAllStudentsByStatus(StudentClassification status) {
        switch(status) {
            case ALUMNUS:
                return this.indexesHolder.getAllAlumni();
            case DROPOUT:
                return this.indexesHolder.getAllDropouts();
            case ACTIVE:
            case DELAYED:
            default:
                return this.indexesHolder.getAllActives();
        }
    }

    private RiskClassCountSummary getRiskClassCountSummary(Collection<NationalIdRegistrationKey> studentIds,
                                                           Map<NationalIdRegistrationKey, StudentData> studentsMap) {
        int inaccurate = 0;
        int safe = 0;
        int low = 0;
        int average = 0;
        int high = 0;
        int unfeasible = 0;
        int notApplicable = 0;
        for (NationalIdRegistrationKey id : studentIds) {
            Student student = studentsMap.get(id).createStudent(id);
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
