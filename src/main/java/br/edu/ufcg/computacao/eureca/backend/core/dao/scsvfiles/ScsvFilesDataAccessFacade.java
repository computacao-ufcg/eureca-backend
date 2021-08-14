package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.StudentClassification;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.*;

public class ScsvFilesDataAccessFacade implements DataAccessFacade {
    private final Logger LOGGER = Logger.getLogger(ScsvFilesDataAccessFacade.class);
    private MapsHolder mapsHolder;
    private IndexesHolder indexesHolder;

    public ScsvFilesDataAccessFacade(String mapsListFile) {
        this.mapsHolder = new MapsHolder(mapsListFile);
        this.indexesHolder = new IndexesHolder(this.mapsHolder);
    }

    @Override
    public Collection<Student> getActives(String courseCode, String from, String to) {
        return getFilteredStudents(StudentClassification.ACTIVE, courseCode, from, to);
    }

    @Override
    public Collection<Student> getAlumni(String courseCode, String from, String to) {
        return getFilteredStudents(StudentClassification.ALUMNUS, courseCode, from, to);
    }

    @Override
    public Collection<Student> getDropouts(String courseCode, String from, String to) {
        return getFilteredStudents(StudentClassification.DROPOUT, courseCode, from, to);
    }

    @Override
    public Map<String, Collection<Student>> getActivesPerAdmissionTerm(String courseCode, String from, String to) {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getActivesPerCoursePerAdmissionTerm(courseCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Map<String, Collection<Student>> getAlumniPerGraduationTerm(String courseCode, String from, String to) {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getAlumniPerGraduationTerm(courseCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Map<String, Collection<Student>> getDropoutsPerDropoutTerm(String courseCode, String from, String to) {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getDropoutsPerDropoutTerm(courseCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String courseCode, String from, String to) {
        String parsedFrom = "1" + from.substring(2,4) + from.substring(5,6) + "00000";
        String parsedTo = "1" + to.substring(2,4) + to.substring(5,6) + "99999";
        Collection<AlumniDigestResponse> alumniBasicData = new TreeSet<>();
        Collection<NationalIdRegistrationKey> alumni = this.indexesHolder.getAlumniPerCourseMap(courseCode);
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
    public Curriculum getCurriculum(String courseCode, String curriculumCode) {
        return this.indexesHolder.getCurriculum(courseCode, curriculumCode);
    }

    @Override
    public Collection<String> getCurriculumCodes(String courseCode) {
        return this.indexesHolder.getCurricula(courseCode);
    }

    @Override
    public ProfileResponse getProfile(String userId) {
        Map<UserKey, ProfileData> profileMap = this.mapsHolder.getMap("profile");
        ProfileData profileData = profileMap.get(new UserKey(userId));
        return new ProfileResponse(profileData.getCourseCode(), profileData.getCourseName());
    }

    @Override
    public Collection<SubjectMetricsPerTermSummary> getSubjectMetricsPerTermSummary(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException {
        Collection<String> subjectCodes = getSubjectCodes(courseCode, curriculumCode, subjectType);
        Collection<SubjectMetricsPerTermSummary> subjectMetricsPerTerms = new TreeSet<>();
        for (String subjectCode : subjectCodes) {
            Collection<SubjectMetricsPerTerm> response = new TreeSet<>();
            Map<String, Map<String, ClassEnrollments>> terms = this.indexesHolder.getSubjectMetricsPerTerm(courseCode, curriculumCode, subjectCode);
            for (String term : terms.keySet()) {
                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    Map<String, ClassEnrollments> classes = terms.get(term);
                    SubjectMetrics metrics = computeSubjectMetrics(classes.values());
                    SubjectMetricsPerTerm metricsPerTerm = new SubjectMetricsPerTerm(term, metrics);
                    response.add(metricsPerTerm);
                }
            }
            Subject subject = getSubject(courseCode, curriculumCode, subjectCode);
            subjectMetricsPerTerms.add(new SubjectMetricsPerTermSummary(subjectCode, subject.getName(), response));
        }
        return subjectMetricsPerTerms;
    }

    @Override
    public SubjectsStatisticsSummaryResponse getSubjectStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        SubjectStatisticsSummary mandatory = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getMandatorySubjectsList());
        SubjectStatisticsSummary optional = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getOptionalSubjectsList());
        SubjectStatisticsSummary elective = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getElectiveSubjectsList());
        SubjectStatisticsSummary complementary = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getComplementarySubjectsList());
        SubjectsStatisticsSummaryResponse ret = new SubjectsStatisticsSummaryResponse(courseCode, curriculumCode,
                from, to, mandatory, optional, elective, complementary);
        return ret;
    }

    @Override
    public Subject getSubject(String courseCode, String curriculumCode, String subjectCode) {
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        return getSubject(subjectKey);
    }

    private Subject getSubject(SubjectKey subjectKey) {
        Map<SubjectKey, SubjectData> subjectMap = this.mapsHolder.getMap("subjects");
        SubjectData subjectData = subjectMap.get(subjectKey);
        return subjectData.createSubject(subjectKey);
    }

    @Override
    public Collection<EnrollmentsPerSubjectData> getEnrollmentsPerSubjectPerTerm(String courseCode, String curriculumCode,
                                     String from, String to, SubjectType subjectType) throws InvalidParameterException {

        Collection<String> subjectCodes = getSubjectCodes(courseCode, curriculumCode, subjectType);
        Collection<EnrollmentsPerSubjectData> enrollmentsPerTerm = new TreeSet<>();
        for (String subjectCode : subjectCodes) {
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            Subject subject = getSubject(subjectKey);
            Map<String, Map<String, ClassEnrollments>> enrollments =
                    this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass().get(subjectKey);
            Map<String, Map<String, ClassEnrollments>> filteredEnrollments = new HashMap();
            enrollments.keySet().forEach(term -> {
                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    Map<String, ClassEnrollments> classes = enrollments.get(term);
                    filteredEnrollments.put(term, classes);
                }
            });
            enrollmentsPerTerm.add(new EnrollmentsPerSubjectData(subjectCode, subject.getName(), filteredEnrollments));
        }
        return enrollmentsPerTerm;
    }

    @Override
    public EnrollmentsStatisticsSummaryResponse getEnrollmentsStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        EnrollmentsSummary mandatory = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getMandatorySubjectsList());
        EnrollmentsSummary optional = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getOptionalSubjectsList());
        EnrollmentsSummary elective = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getElectiveSubjectsList());
        EnrollmentsSummary complementary = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getComplementarySubjectsList());
        EnrollmentsStatisticsSummaryResponse ret = new EnrollmentsStatisticsSummaryResponse(courseCode, curriculumCode,
                from, to, mandatory, optional, elective, complementary);
        return ret;
    }

    @Override
    public Collection<EnrollmentsMetricsPerTermSummary> getEnrollmentsPerTermSummary(String courseCode, String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException {
        Collection<String> subjectCodes = getSubjectCodes(courseCode, curriculumCode, subjectType);
        Collection<EnrollmentsMetricsPerTermSummary> enrollmentsMetricsPerTerms = new TreeSet<>();
        for (String subjectCode : subjectCodes) {
            Collection<EnrollmentsMetricsPerTerm> response = new TreeSet<>();
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            Map<String, Map<String, ClassEnrollments>> terms = this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass().get(subjectKey);
            for (String term : terms.keySet()) {
                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    Map<String, ClassEnrollments> classes = terms.get(term);
                    int classesCount = classes.keySet().size();
                    int enrollmentsCount = 0;
                    for (String classId : classes.keySet()) {
                        ClassEnrollments classEnrollments = classes.get(classId);
                        enrollmentsCount += classEnrollments.getNumberOfEnrolleds();
                    }
                    EnrollmentsMetricsPerTerm metricsPerTerm = new EnrollmentsMetricsPerTerm(term, enrollmentsCount,
                            classesCount, (double) enrollmentsCount/classesCount);
                    response.add(metricsPerTerm);
                }
            }
            Subject subject = getSubject(courseCode, curriculumCode, subjectCode);
            enrollmentsMetricsPerTerms.add(new EnrollmentsMetricsPerTermSummary(subjectCode, subject.getName(), response));
        }
        return enrollmentsMetricsPerTerms;
    }

    @Override
    public Collection<SubjectRetentionDigest> getSubjectsRetentionSummary(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionDigest> response = new TreeSet<>();
        Collection<String> subjectCodes = getMandatorySubjectsList(courseCode, curriculumCode);
        subjectCodes.forEach(item -> {
            int retention = getRetentionCount(courseCode, curriculumCode, item);
            Subject subject = getSubject(courseCode, curriculumCode, item);
            response.add(new SubjectRetentionDigest(subject.getIdealTerm(), item, subject.getName(), retention));
        });
        return response;
    }

    @Override
    public Collection<SubjectRetentionCSV> getSubjectsRetention(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionCSV> response = new TreeSet<>();
        Collection<String> subjectCodes = getMandatorySubjectsList(courseCode, curriculumCode);
        subjectCodes.forEach(item -> {
            response.addAll(this.indexesHolder.getRetention(courseCode, curriculumCode, item));
        });
        return response;
    }

    private Map<String, Collection<Student>> getStudentMapFromIndex(String from, String to,
                                                                    Map<String, Collection<NationalIdRegistrationKey>> index) {
        Map<NationalIdRegistrationKey, StudentData> studentsMap = mapsHolder.getMap("students");
        Map<String, Collection<Student>> termsMap = new HashMap<>();
        for (Map.Entry<String, Collection<NationalIdRegistrationKey>> entry : index.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Collection<Student> actives = new TreeSet<>();
                entry.getValue().forEach(cpfRegistration -> {
                    StudentData studentData = studentsMap.get(cpfRegistration);
                    Curriculum curriculum = getCurriculum(studentData.getCourseCode(), studentData.getCurriculumCode());
                    actives.add(studentData.createStudent(cpfRegistration, curriculum));
                });
                termsMap.put(term, actives);
            }
        }
        return termsMap;
    }

    private Collection<String> getSubjectCodes(String courseCode, String curriculumCode, SubjectType subjectType) throws InvalidParameterException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, curriculumCode, courseCode));
        }        Collection<String> subjectCodes = null;
        switch (subjectType) {
            case MANDATORY:
                subjectCodes = curriculum.getMandatorySubjectsList();
                break;
            case OPTIONAL:
                subjectCodes = curriculum.getOptionalSubjectsList();
                break;
            case ELECTIVE:
                subjectCodes = curriculum.getElectiveSubjectsList();
                break;
            case COMPLEMENTARY:
                subjectCodes = curriculum.getComplementarySubjectsList();
                break;
        }
        return subjectCodes;
    }

    private SubjectMetrics computeSubjectMetrics(Collection<ClassEnrollments> classes) {
        int failedDueToAbsences = 0;
        int failedDueToGrade = 0;
        int cancelled = 0;
        int succeeded = 0;
        int ongoing = 0;
        int exempted = 0;
        int suspended = 0;
        int numberOfClasses = 0;
        int totalEnrolled = 0;

        for (ClassEnrollments enrollments : classes) {
            failedDueToAbsences += enrollments.getNumberFailedDueToAbsence();
            failedDueToGrade += enrollments.getNumberFailedDueToGrade();
            cancelled += enrollments.getNumberCancelled();
            succeeded += enrollments.getNumberSucceeded();
            ongoing += enrollments.getNumberOngoing();
            exempted += enrollments.getNumberExempted();
            suspended += enrollments.getNumberSuspended();
            numberOfClasses++;
            totalEnrolled += enrollments.getNumberOfEnrolleds();
        }
        return new SubjectMetrics(failedDueToAbsences, failedDueToGrade, cancelled,
                succeeded, ongoing, exempted, suspended, numberOfClasses, totalEnrolled);
    }

    private SubjectStatisticsSummary buildSubjectSummary(String courseCode, String curriculumCode, String from, String to,
                                                         Collection<String> subjectCodes) {

        Collection<SubjectMetrics> metricsPerSubject = new ArrayList<>();
        for(String subjectCode : subjectCodes) {
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            Map<String, Map<String, ClassEnrollments>> enrollments = this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass().get(subjectKey);
            if (enrollments != null) {
                SubjectMetrics subjectMetrics = getSubjectMetricsStatistics(from, to, enrollments);
                if (subjectMetrics != null) metricsPerSubject.add(subjectMetrics);
            }
        }
        SubjectMetricsStatistics metrics = computeSubjectMetricsStatistics(metricsPerSubject);
        return new SubjectStatisticsSummary(subjectCodes.size(), metrics);
    }

    private SubjectMetricsStatistics computeSubjectMetricsStatistics(Collection<SubjectMetrics> metricsPerSubject) {
        List<Double> failedDueToAbsencesList = new ArrayList<>();
        List<Double> failedDueToGradeList = new ArrayList<>();
        List<Double> cancelledList = new ArrayList<>();
        List<Double> succeededList = new ArrayList<>();
        List<Double> ongoingList = new ArrayList<>();
        List<Double> exemptedList = new ArrayList<>();
        List<Double> suspendedList = new ArrayList<>();
        List<Double> totalEnrolledList = new ArrayList<>();
        for (SubjectMetrics subjectMetrics : metricsPerSubject) {
            failedDueToAbsencesList.add((double) subjectMetrics.getFailedDueToAbsences());
            failedDueToGradeList.add((double) subjectMetrics.getFailedDueToAbsences());
            cancelledList.add((double) subjectMetrics.getCancelled());
            succeededList.add((double) subjectMetrics.getSucceeded());
            exemptedList.add((double) subjectMetrics.getExempted());
            suspendedList.add((double) subjectMetrics.getSuspended());
            totalEnrolledList.add((double) subjectMetrics.getTotalEnrolled());
        }
        MetricStatistics failedDueToAbsences = new MetricStatistics(failedDueToAbsencesList);
        MetricStatistics failedDueToGrade = new MetricStatistics(failedDueToGradeList);
        MetricStatistics cancelled = new MetricStatistics(cancelledList);
        MetricStatistics succeeded = new MetricStatistics(succeededList);
        MetricStatistics ongoing = new MetricStatistics(ongoingList);
        MetricStatistics exempted = new MetricStatistics(exemptedList);
        MetricStatistics suspended = new MetricStatistics(suspendedList);
        MetricStatistics totalEnrolled = new MetricStatistics(totalEnrolledList);

        return new SubjectMetricsStatistics(failedDueToAbsences, failedDueToGrade, cancelled, succeeded, ongoing,
                exempted, suspended, totalEnrolled);
    }

    private EnrollmentsSummary buildEnrollmentSummary(String courseCode, String curriculumCode, String from, String to,
                                                         Collection<String> subjectCodes) {

        String maxTerm = "0000.0";
        String minTerm = "9999.9";
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int classesCount = 0;
        int enrollmentsCount = 0;
        int termsCount = 0;

        Collection<String> terms = this.indexesHolder.getEnrollmentsPerTermPerSubjectPerClass().keySet();
        for(String term : terms) {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                int enrollmentsPerTerm = 0;
                termsCount++;
                Map<SubjectKey, Map<String, ClassEnrollments>> enrollmentsPerSubject =
                        this.indexesHolder.getEnrollmentsPerTermPerSubjectPerClass().get(term);
                for(String subjectCode : subjectCodes) {
                    SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
                    Map<String, ClassEnrollments> enrollmentsPerClassData = enrollmentsPerSubject.get(subjectKey);
                    if (enrollmentsPerClassData != null) {
                        for(String classId : enrollmentsPerClassData.keySet()) {
                            classesCount++;
                            ClassEnrollments enrollmentsData = enrollmentsPerClassData.get(classId);
                            enrollmentsPerTerm += enrollmentsData.getNumberOfEnrolleds();
                        }
                    }
                }
                enrollmentsCount += enrollmentsPerTerm;
                if (enrollmentsPerTerm < min) {
                    minTerm = term;
                    min = enrollmentsPerTerm;
                }
                if (enrollmentsPerTerm < max) {
                    maxTerm = term;
                    max = enrollmentsPerTerm;
                }
            }
        }
        int subjectCount = subjectCodes.size();
        EnrollmentsStatisticsSummary summary = new EnrollmentsStatisticsSummary(subjectCount,
                (double) classesCount/subjectCount,
                (double) classesCount/termsCount,
                (double) enrollmentsCount/subjectCount,
                (double) enrollmentsCount/termsCount);
        return new EnrollmentsSummary(new TermCount(min, minTerm), new TermCount(max, maxTerm), summary);
    }

    private int getRetentionCount(String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getRetentionCount(courseCode, curriculumCode, subjectCode);
    }

    private SubjectMetrics getSubjectMetricsStatistics(String from, String to, @NotNull Map<String, Map<String, ClassEnrollments>> enrollments) {
        SubjectMetrics subjectMetrics = null;
        for (String term : enrollments.keySet()) {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Map<String, ClassEnrollments> classes = enrollments.get(term);
                subjectMetrics = computeSubjectMetrics(classes.values());
            }
        }
        return subjectMetrics;
    }

    private Collection<Student> getFilteredStudents(StudentClassification status, String courseCode, String from, String to) {
        Collection<Student> filteredStudents = new TreeSet<>();
        Collection<Student> allStudents = getAllStudentsByStatusPerCourse(status, courseCode);
        allStudents.forEach(item -> {
            String studentTerm = getGroupingTerm(status, item);
            if (studentTerm != null && studentTerm.compareTo(from) >= 0 && studentTerm.compareTo(to) <= 0) {
                filteredStudents.add(item);
            }
        });
        return filteredStudents;
    }

    private Collection<String> getMandatorySubjectsList(String courseCode, String curriculumCode) throws InvalidParameterException {
        Curriculum curriculum = this.getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        return curriculum.getMandatorySubjectsList();
    }

    private String getGroupingTerm(StudentClassification status, Student item) {
        switch(status) {
            case ALUMNUS:
            case DROPOUT:
                return item.getStatusTerm();
            case ACTIVE:
            default:
                return item.getAdmissionTerm();
        }
    }

    private Collection<Student> getAllStudentsByStatusPerCourse(StudentClassification status, String courseCode) {
        switch(status) {
            case ALUMNUS:
                return this.indexesHolder.getAllAlumni(courseCode);
            case DROPOUT:
                return this.indexesHolder.getAllDropouts(courseCode);
            case ACTIVE:
            default:
                return this.indexesHolder.getAllActives(courseCode);
        }
    }
}
