package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.function.Predicate;
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
    public Collection<Student> getDelayed(String from, String to) {
        return this.getActives(from, to)
                .stream()
                .filter(item -> item.computeRiskClass().equals(RiskClass.AVERAGE) ||
                        item.computeRiskClass().equals(RiskClass.HIGH) ||
                        item.computeRiskClass().equals(RiskClass.UNFEASIBLE))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Enrollment> getEnrollments(String from, String to, String courseCode, String curriculumCode) {
        Collection<Enrollment> enrollments = this.indexesHolder.getAllEnrollments();
        return this.getFilteredEnrollments(from, to, courseCode, curriculumCode, enrollments);
    }

    private Collection<Enrollment> getFilteredEnrollments(String from, String to, String courseCode, String curriculumCode, Collection<Enrollment> enrollments) {
        return enrollments
                .stream()
                .filter(isFromCourse(courseCode))
                .filter(range(from, to))
                .collect(Collectors.toList());
    }

    private Predicate<Enrollment> isFromCourse(String courseCode) {
        return enrollment -> enrollment.getCode().equals(courseCode);
    }

    private Predicate<Enrollment> range(String from, String to) {
        return enrollment -> enrollment.getTerm().compareTo(from) >= 0 && enrollment.getTerm().compareTo(to) <= 0;
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
    public Student getStudent(String registration) {
        Map<String, NationalIdRegistrationKey> registrationMap = this.indexesHolder.getRegistrationMap();
        Map<NationalIdRegistrationKey, StudentData> studentsMap = this.mapsHolder.getMap("students");
        NationalIdRegistrationKey key = registrationMap.get(registration);
        StudentData studentData = studentsMap.get(key);
        return studentData.createStudent(key);
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
    public int getRetentionCount(String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getRetentionCount(courseCode, curriculumCode, subjectCode);
    }

    @Override
    public Collection<SubjectsRetentionResponse> getRetention(String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getRetention(courseCode, curriculumCode, subjectCode);
    }

    @Override
    public TreeSet<String> getTermsForCurriculum(String courseCode, String curriculum) {
        return this.indexesHolder.getTermsPerCurriculum(courseCode, curriculum);
    }

    @Override
    public int getNumberOfClassesPerSubject(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getNumberOfClassesPerSubject(from, to, courseCode, curriculumCode, subjectCode);
    }

    public MetricStatistics getEnrollmentsStatistics(String from, String to, String courseCode, String curriculumCode,
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
