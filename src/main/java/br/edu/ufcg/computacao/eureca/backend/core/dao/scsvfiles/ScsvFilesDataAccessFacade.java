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
    public Collection<Student> getActives(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        return getFilteredStudents(StudentClassification.ACTIVE, courseCode, curriculumCode, from, to);
    }

    @Override
    public Collection<Student> getAlumni(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        return getFilteredStudents(StudentClassification.ALUMNUS, courseCode, curriculumCode, from, to);
    }

    @Override
    public Collection<Student> getDropouts(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        return getFilteredStudents(StudentClassification.DROPOUT, courseCode, curriculumCode, from, to);
    }

    @Override
    public Map<String, Collection<Student>> getActivesPerAdmissionTerm(String courseCode, String curriculumCode,
                                                        String from, String to) throws InvalidParameterException {
        Map<String, Collection<NationalIdRegistrationKey>> index =
                indexesHolder.getActivesPerCoursePerAdmissionTerm(courseCode, curriculumCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Map<String, Collection<Student>> getAlumniPerGraduationTerm(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getAlumniPerGraduationTerm(courseCode, curriculumCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Map<String, Collection<Student>> getDropoutsPerDropoutTerm(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getDropoutsPerDropoutTerm(courseCode, curriculumCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Collection<AlumniDigest> getAlumniPerStudentSummary(String courseCode, String from, String to) throws InvalidParameterException {
        String parsedFrom = "1" + from.substring(2,4) + from.substring(5,6) + "00000";
        String parsedTo = "1" + to.substring(2,4) + to.substring(5,6) + "99999";
        Collection<AlumniDigest> alumniBasicData = new TreeSet<>();
        Collection<NationalIdRegistrationKey> alumni = this.indexesHolder.getAlumniPerCourseMap(courseCode);
        Map<NationalIdRegistrationKey, StudentData> studentsMap = this.mapsHolder.getMap("students");
        for (NationalIdRegistrationKey item : alumni) {
            if (new Registration(item.getRegistration()).compareTo(new Registration(parsedFrom)) >= 0 &&
                    new Registration(item.getRegistration()).compareTo(new Registration(parsedTo)) <= 0) {
                StudentData alumnus = studentsMap.get(item);
                AlumniDigest basicData = new AlumniDigest(item.getRegistration(), alumnus.getName(),
                        2, 1, alumnus.getAdmissionTerm(), alumnus.getStatusTerm());
                alumniBasicData.add(basicData);
            }
        }
        return alumniBasicData;
    }

    @Override
    public Curriculum getCurriculum(String courseCode, String curriculumCode) throws InvalidParameterException {
        return this.indexesHolder.getCurriculum(courseCode, curriculumCode);
    }

    @Override
    public Collection<String> getCurriculumCodes(String courseCode) throws InvalidParameterException {
        return this.indexesHolder.getCurriculumCodes(courseCode);
    }

    @Override
    public ProfileResponse getProfile(String userId) throws InvalidParameterException {
        Map<UserKey, ProfileData> profileMap = this.mapsHolder.getMap("profile");
        ProfileData profileData = profileMap.get(new UserKey(userId));
        if (profileData == null) throw new InvalidParameterException(String.format(Messages.INVALID_USER_S, userId));
        return new ProfileResponse(profileData.getCourseCode(), profileData.getCourseName());
    }

    @Override
    public Collection<SubjectMetricsPerTermSummary> getSubjectMetricsPerTermSummary(String courseCode,
             String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException {
        Collection<String> subjectCodes = getSubjectCodes(courseCode, curriculumCode, subjectType);
        if (subjectCodes == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
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
        if (curriculum == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        SubjectsStatisticsSummary mandatory = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getMandatorySubjectsList());
        SubjectsStatisticsSummary optional = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getOptionalSubjectsList());
        SubjectsStatisticsSummary elective = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getElectiveSubjectsList());
        SubjectsStatisticsSummary complementary = buildSubjectSummary(courseCode, curriculumCode, from, to,
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
    public EnrollmentsStatisticsSummaryResponse getEnrollmentsStatisticsSummary(String courseCode, String curriculumCode,
                                                              String from, String to) throws InvalidParameterException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        EnrollmentsSummary mandatory = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getMandatorySubjectsList());
        EnrollmentsSummary optional = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getOptionalSubjectsList());
        EnrollmentsSummary elective = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getElectiveSubjectsList());
        EnrollmentsSummary complementary = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getComplementarySubjectsList());
        EnrollmentsStatisticsSummaryResponse ret = new EnrollmentsStatisticsSummaryResponse(courseCode, curriculumCode,
                mandatory, optional, elective, complementary);
        return ret;
    }

    @Override
    public Collection<EnrollmentsMetricsPerTermSummary> getEnrollmentsPerTermSummary(String courseCode,
             String curriculumCode, String from, String to, SubjectType subjectType) throws InvalidParameterException {
        Collection<String> subjectCodes = getSubjectCodes(courseCode, curriculumCode, subjectType);
        if (subjectCodes == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        Collection<EnrollmentsMetricsPerTermSummary> enrollmentsMetricsPerTerms = new TreeSet<>();
        for (String subjectCode : subjectCodes) {
            Collection<EnrollmentsMetricsPerTerm> metricsPerTerms = new TreeSet<>();
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            Map<String, Map<String, ClassEnrollments>> terms = this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass().get(subjectKey);
            if (terms != null) {
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
                                classesCount, (double) enrollmentsCount / classesCount);
                        metricsPerTerms.add(metricsPerTerm);
                    }
                }
            }
            Subject subject = getSubject(courseCode, curriculumCode, subjectCode);
            enrollmentsMetricsPerTerms.add(new EnrollmentsMetricsPerTermSummary(subjectCode, subject.getName(), metricsPerTerms));
        }
        return enrollmentsMetricsPerTerms;
    }

    @Override
    public Collection<SubjectRetentionPerAdmissionTermSummary> getSubjectsRetentionSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<SubjectRetentionPerAdmissionTermSummary> response = new TreeSet<>();
        Collection<String> subjectCodes = getSubjectsList(courseCode, curriculumCode);

        for(String subjectCode : subjectCodes) {
            Collection<SubjectRetentionPerAdmissionTerm> retentionPerTerm = this.indexesHolder.getRetentionCount(courseCode, curriculumCode, from, to, subjectCode);
            if (retentionPerTerm == null) continue;
            Subject subject = getSubject(courseCode, curriculumCode, subjectCode);
            response.add(new SubjectRetentionPerAdmissionTermSummary(subjectCode, subject.getName(), subject.getIdealTerm(), retentionPerTerm));
        }
        return response;
    }

    @Override
    public Collection<SubjectRetentionCSV> getSubjectsRetention(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<SubjectRetentionCSV> response = new TreeSet<>();
        Collection<String> subjectCodes = getSubjectsList(courseCode, curriculumCode);

        for(String subjectCode : subjectCodes) {
            Collection<SubjectRetentionCSV> partialList = this.indexesHolder.getRetention(courseCode, curriculumCode, from, to, subjectCode);
            if (partialList == null) continue;
            response.addAll(partialList);
        }
        return response;
    }

    @Override
    public TeachersStatisticsResponse getTeachersPerTermSummary(String courseCode, String curriculumCode, String from, String to, String academicUnitId) throws InvalidParameterException {
        AcademicUnitData auData = this.indexesHolder.getAuData(new AcademicUnitKey(academicUnitId));
        if (auData == null) throw new InvalidParameterException(String.format(Messages.INVALID_ACADEMIC_UNIT_S, academicUnitId));
        Collection<TeacherStatistics> teachers = this.indexesHolder.getTeachersPerTerm(academicUnitId, courseCode, curriculumCode, from, to);
        return new TeachersStatisticsResponse(academicUnitId, auData.getAcronym(), auData.getName(), courseCode,
                curriculumCode, from, to, teachers);
    }

    @Override
    public Map<String, TeachersStatisticsSummary> getTeachersPerAcademicUnit(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        return this.indexesHolder.getTeachersPerAcademicUnit(courseCode, curriculumCode, from, to);
    }

    private Subject getSubject(SubjectKey subjectKey) {
        Map<SubjectKey, SubjectData> subjectMap = this.mapsHolder.getMap("subjects");
        SubjectData subjectData = subjectMap.get(subjectKey);
        return subjectData.createSubject(subjectKey);
    }

    private Map<String, Collection<Student>> getStudentMapFromIndex(String from, String to,
                         Map<String, Collection<NationalIdRegistrationKey>> index) throws InvalidParameterException {
        Map<NationalIdRegistrationKey, StudentData> studentsMap = mapsHolder.getMap("students");
        Map<String, Collection<Student>> termsMap = new HashMap<>();
        for (Map.Entry<String, Collection<NationalIdRegistrationKey>> entry : index.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Collection<Student> actives = new TreeSet<>();
                for (NationalIdRegistrationKey cpfRegistration : entry.getValue()) {
                    StudentData studentData = studentsMap.get(cpfRegistration);
                    Curriculum curriculum = getCurriculum(studentData.getCourseCode(), studentData.getCurriculumCode());
                    actives.add(studentData.createStudent(cpfRegistration, curriculum));
                }
                termsMap.put(term, actives);
            }
        }
        return termsMap;
    }

    private Collection<String> getSubjectCodes(String courseCode, String curriculumCode, SubjectType subjectType) throws InvalidParameterException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, curriculumCode, courseCode));
        Collection<String> subjectCodes = null;
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

    private SubjectsStatisticsSummary buildSubjectSummary(String courseCode, String curriculumCode, String from, String to,
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
        return new SubjectsStatisticsSummary(subjectCodes.size(), metrics);
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
        List<Double> numberOfClassesList = new ArrayList<>();
        List<Double> averageEnrollmentsPerClassList = new ArrayList<>();

        for (SubjectMetrics subjectMetrics : metricsPerSubject) {
            failedDueToAbsencesList.add((double) subjectMetrics.getFailedDueToAbsences());
            failedDueToGradeList.add((double) subjectMetrics.getFailedDueToAbsences());
            cancelledList.add((double) subjectMetrics.getCancelled());
            succeededList.add((double) subjectMetrics.getSucceeded());
            exemptedList.add((double) subjectMetrics.getExempted());
            suspendedList.add((double) subjectMetrics.getSuspended());
            totalEnrolledList.add((double) subjectMetrics.getTotalEnrolled());
            numberOfClassesList.add((double) subjectMetrics.getNumberOfClasses());
            averageEnrollmentsPerClassList.add((double) subjectMetrics.getTotalEnrolled()/subjectMetrics.getNumberOfClasses());
        }
        MetricStatistics failedDueToAbsences = new MetricStatistics(failedDueToAbsencesList);
        MetricStatistics failedDueToGrade = new MetricStatistics(failedDueToGradeList);
        MetricStatistics cancelled = new MetricStatistics(cancelledList);
        MetricStatistics succeeded = new MetricStatistics(succeededList);
        MetricStatistics ongoing = new MetricStatistics(ongoingList);
        MetricStatistics exempted = new MetricStatistics(exemptedList);
        MetricStatistics suspended = new MetricStatistics(suspendedList);
        MetricStatistics totalEnrolled = new MetricStatistics(totalEnrolledList);
        MetricStatistics numberOfClasses = new MetricStatistics(numberOfClassesList);
        MetricStatistics averageEnrollmentsPerClass = new MetricStatistics(averageEnrollmentsPerClassList);

        return new SubjectMetricsStatistics(failedDueToAbsences, failedDueToGrade, cancelled, succeeded, ongoing,
                exempted, suspended, totalEnrolled, numberOfClasses, averageEnrollmentsPerClass);
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
        int subjectsCount = 0;
        ArrayList<String> termsList = new ArrayList();

        Collection<String> terms = this.indexesHolder.getEnrollmentsPerTermPerSubjectPerClass().keySet();
        for(String term : terms) {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                int enrollmentsPerTerm = 0;

                Map<SubjectKey, Map<String, ClassEnrollments>> enrollmentsPerSubject =
                        this.indexesHolder.getEnrollmentsPerTermPerSubjectPerClass().get(term);
                for(String subjectCode : subjectCodes) {
                    SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
                    Map<String, ClassEnrollments> enrollmentsPerClassData = enrollmentsPerSubject.get(subjectKey);
                    if (enrollmentsPerClassData != null && enrollmentsPerClassData.size() > 0) {
                        subjectsCount++;
                        for(String classId : enrollmentsPerClassData.keySet()) {
                            classesCount++;
                            ClassEnrollments enrollmentsData = enrollmentsPerClassData.get(classId);
                            enrollmentsPerTerm += enrollmentsData.getNumberOfEnrolleds();
                        }
                    }
                }
                if (enrollmentsPerTerm > 0) {
                    termsList.add(term);
                    termsCount++;
                    enrollmentsCount += enrollmentsPerTerm;
                    if (enrollmentsPerTerm < min) {
                        minTerm = term;
                        min = enrollmentsPerTerm;
                    }
                    if (enrollmentsPerTerm > max) {
                        maxTerm = term;
                        max = enrollmentsPerTerm;
                    }
                }
            }
        }
        EnrollmentsStatisticsSummary summary = new EnrollmentsStatisticsSummary(
                (double) subjectsCount/termsCount,
                (double) classesCount/subjectsCount,
                (double) classesCount/termsCount,
                (double) enrollmentsCount/subjectsCount,
                (double) enrollmentsCount/termsCount);
        Collections.sort(termsList);
        String first = termsList.get(0);
        String last = (termsList.size() == 0 ? termsList.get(0) : termsList.get(termsList.size() - 1));
        return new EnrollmentsSummary(first, last, new TermCount(min, minTerm), new TermCount(max, maxTerm), summary);
    }

    private SubjectMetrics getSubjectMetricsStatistics(String from, String to, @NotNull Map<String, Map<String, ClassEnrollments>> enrollments) {
        SubjectMetrics subjectMetrics = new SubjectMetrics();
        for (String term : enrollments.keySet()) {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Map<String, ClassEnrollments> classes = enrollments.get(term);
                SubjectMetrics otherSubjectMetrics = computeSubjectMetrics(classes.values());
                subjectMetrics.add(otherSubjectMetrics);
            }
        }
        return subjectMetrics;
    }

    private Collection<Student> getFilteredStudents(StudentClassification status, String courseCode,
                                   String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<Student> filteredStudents = new TreeSet<>();
        Collection<Student> allStudents = getAllStudentsByStatusPerCourse(status, courseCode, curriculumCode);
        allStudents.forEach(item -> {
            String studentTerm = getGroupingTerm(status, item);
            if (studentTerm != null && studentTerm.compareTo(from) >= 0 && studentTerm.compareTo(to) <= 0) {
                filteredStudents.add(item);
            }
        });
        return filteredStudents;
    }

    private Collection<String> getSubjectsList(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<String> subjectList;
        Curriculum curriculum = this.getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        subjectList = curriculum.getMandatorySubjectsList();
        subjectList.addAll(curriculum.getComplementarySubjectsList());
        return subjectList;
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

    private Collection<Student> getAllStudentsByStatusPerCourse(StudentClassification status, String courseCode, String curriculumCode) throws InvalidParameterException {
        switch(status) {
            case ALUMNUS:
                return this.indexesHolder.getAllAlumni(courseCode, curriculumCode);
            case DROPOUT:
                return this.indexesHolder.getAllDropouts(courseCode, curriculumCode);
            case ACTIVE:
            default:
                return this.indexesHolder.getAllActives(courseCode, curriculumCode);
        }
    }
}
