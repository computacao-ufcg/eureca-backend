package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsPerTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsSummary;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.TeacherData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.TeachersSetAndEnrollments;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;

public class IndexesHolder {
    private final Logger LOGGER = Logger.getLogger(IndexesHolder.class);

    private final MapsHolder mapsHolder;
    // Map instances
    private final Map<NationalIdRegistrationKey, StudentData> studentsMap;
    private final Map<RegistrationSubjectCodeTermKey, EnrollmentData> enrollmentsMap;
    private final Map<SubjectKey, SubjectData> subjectsMap;
    private final Map<CurriculumKey, CurriculumData> curriculumMap;
    private final Map<SubjectCodeTermClassIdKey, TeachersListData> classesMap;
    private final Map<AcademicUnitKey, AcademicUnitData> academicUnitsMap;
    private final Map<TeacherKey, TeacherData> teachersMap;
    private final Map<ScheduleKey, ScheduleData> scheduleMap;
    // Student indexes
    private Map<String, NationalIdRegistrationKey> registrationMap;
    private Map<CurriculumKey, Collection<NationalIdRegistrationKey>> activesPerCurriculumMap;
    private Map<CurriculumKey, Map<String, Collection<NationalIdRegistrationKey>>> activesPerCurriculumPerAdmissionTermMap;
    private Map<CurriculumKey, Collection<NationalIdRegistrationKey>> alumniPerCurriculumMap;
    private Map<CurriculumKey, Map<String, Collection<NationalIdRegistrationKey>>> alumniPerCurriculumPerGraduationTermMap;
    private Map<CurriculumKey, Collection<NationalIdRegistrationKey>> dropoutsPerCurriculumMap;
    private Map<CurriculumKey, Map<String, Collection<NationalIdRegistrationKey>>> dropoutsPerCurriculumPerDropoutTermMap;
    // Enrollment indexes
    private Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerSubjectPerTermPerClass;
    private Map<String, Map<SubjectKey, Map<String, ClassEnrollments>>> enrollmentsPerTermPerSubjectPerClass;
    // Subject indexes
    private Map<NationalIdRegistrationKey, StudentCurriculumProgress> studentCurriculumProgressMap;
    private Map<String, Collection<SubjectKey>> subjectCurriculaMap;
    // Curricula indexes
    private Map<String, Collection<String>> courseCurriculaMap;
    private Map<CurriculumKey, Collection<AcademicUnitKey>> academicUnitKeysPerCurriculum;
    // Teacher indexes
    private Map<AcademicUnitKey, Collection<TeacherKey>> teachersPerAcademicUnit;
    // Class indexes
    private Map<TeacherKey, Map<CurriculumKey, Map<String, Map<SubjectKey, Map<String, ClassEnrollments>>>>> enrollmentDataPerTeacher;
    // AcademicUnit indexes
    private Map<AcademicUnitKey, Map<String, TeachersSetAndEnrollments>> enrollmentsPerAcademicUnitPerTerm;

    public IndexesHolder(MapsHolder mapsHolder) {
        this.mapsHolder = mapsHolder;
        this.studentsMap = this.mapsHolder.getMap("students");
        this.enrollmentsMap = this.mapsHolder.getMap("enrollments");
        this.subjectsMap = this.mapsHolder.getMap("subjects");
        this.curriculumMap = this.mapsHolder.getMap("curriculum");
        this.classesMap = this.mapsHolder.getMap("classes");
        this.academicUnitsMap = this.mapsHolder.getMap("academicUnits");
        this.teachersMap = this.mapsHolder.getMap("teachers");
        this.scheduleMap = this.mapsHolder.getMap("schedule");
        buildIndexes();
    }

    public Collection<NationalIdRegistrationKey> getAlumniPerCourseMap(String courseCode) throws InvalidParameterException {
        Collection<NationalIdRegistrationKey> alumni = new ArrayList<>();
        Collection<String> currilumCodes = this.getCurriculumCodes(courseCode);
        if (currilumCodes == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_S, courseCode));
        for (String curriculumCode : currilumCodes) {
            Collection<NationalIdRegistrationKey> partialList = this.alumniPerCurriculumMap.get(new CurriculumKey(courseCode, curriculumCode));
            if (partialList == null) continue;
            alumni.addAll(partialList);
        }
        return alumni;
    }

    public StudentCurriculumProgress getStudentCurriculumProgress(String registration) throws InvalidParameterException {
        NationalIdRegistrationKey studentId = this.registrationMap.get(registration);
        if (studentId == null) throw new InvalidParameterException(String.format(Messages.INVALID_STUDENT_S, registration));
        return this.studentCurriculumProgressMap.get(studentId);
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getActivesPerCoursePerAdmissionTerm(String courseCode,
                                                             String curriculumCode) throws InvalidParameterException {
        Map<String, Collection<NationalIdRegistrationKey>> ret =
                this.activesPerCurriculumPerAdmissionTermMap.get(new CurriculumKey(courseCode, curriculumCode));
        if (ret == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        return ret;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getAlumniPerGraduationTerm(String courseCode,
                                                            String curriculumCode) throws InvalidParameterException {
        Map<String, Collection<NationalIdRegistrationKey>> alumniMap = this.alumniPerCurriculumPerGraduationTermMap.get(new CurriculumKey(courseCode, curriculumCode));
        if (alumniMap == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        return alumniMap;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutsPerDropoutTerm(String courseCode,
                                                                                        String curriculumCode) throws InvalidParameterException {
        Map<String, Collection<NationalIdRegistrationKey>> dropoutMap = this.dropoutsPerCurriculumPerDropoutTermMap.get(new CurriculumKey(courseCode, curriculumCode));
        if (dropoutMap == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        return dropoutMap;
    }

    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerSubjectPerTermPerClass() {
        return this.enrollmentsPerSubjectPerTermPerClass;
    }

    public Map<String, Map<SubjectKey, Map<String, ClassEnrollments>>> getEnrollmentsPerTermPerSubjectPerClass() {
        return this.enrollmentsPerTermPerSubjectPerClass;
    }

    public Collection<String> getCurriculumCodes(String courseCode) throws InvalidParameterException {
        Collection<String> curriculumCodes = this.courseCurriculaMap.get(courseCode);
        if (curriculumCodes == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_S, courseCode));
        return curriculumCodes;
    }

    public Curriculum getCurriculum(String courseCode, String curriculumCode) throws InvalidParameterException {
        Map<CurriculumKey, CurriculumData> curriculumMap = this.mapsHolder.getMap("curriculum");
        CurriculumKey key = new CurriculumKey(courseCode, curriculumCode);
        CurriculumData ret = curriculumMap.get(key);
        if (ret == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        return ret.createCurriculum(key);
    }

    public Collection<Student> getAllActives(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<NationalIdRegistrationKey> actives = this.activesPerCurriculumMap.get(new
                CurriculumKey(courseCode, curriculumCode));
        if (actives == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        return getAllStudents(actives);
    }

    public Collection<Student> getAllAlumni(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<NationalIdRegistrationKey> alumni = this.alumniPerCurriculumMap.get(new
                CurriculumKey(courseCode, curriculumCode));
        if (alumni == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        return getAllStudents(alumni);
    }

    public Collection<Student> getAllDropouts(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<NationalIdRegistrationKey> dropouts = this.dropoutsPerCurriculumMap.get(new
                CurriculumKey(courseCode, curriculumCode));
        if (dropouts == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        return getAllStudents(dropouts);
    }

    public Collection<SubjectRetentionPerAdmissionTerm> getRetentionCount(String courseCode, String curriculumCode,
                                                                          String from, String to, String subjectCode) throws InvalidParameterException {
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        Map<String, SubjectRetentionPerAdmissionTerm> retention = new HashMap<>();
        Collection<NationalIdRegistrationKey> actives = this.activesPerCurriculumMap.get(new CurriculumKey(courseCode, curriculumCode));
        if (actives == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));

        for (NationalIdRegistrationKey active : actives) {
            StudentCurriculumProgress studentCurriculum = this.studentCurriculumProgressMap.get(active);
            StudentData studentData = this.studentsMap.get(active);
            String admissionTerm = studentData.getAdmissionTerm();
            if (admissionTerm.compareTo(from) >= 0 && admissionTerm.compareTo(to) <= 0) {
                if (studentCurriculum.getEnabled().contains(subjectKey)) {
                    CurriculumKey curriculumKey = new CurriculumKey(studentData.getCourseCode(),
                            studentData.getCurriculumCode());
                    CurriculumData curriculumData = this.curriculumMap.get(curriculumKey);
                    SubjectData subjectData = this.subjectsMap.get(subjectKey);
                    SubjectRetentionPerAdmissionTerm retentionPerTerm = retention.get(admissionTerm);
                    if (retentionPerTerm == null) retentionPerTerm = new
                            SubjectRetentionPerAdmissionTerm(admissionTerm, 0, 0);
                    retentionPerTerm.incrementPossible();
                    if (isAdequate(studentCurriculum, curriculumData, subjectData.getIdealTerm())) {
                        retentionPerTerm.incrementAdequate();
                    }
                    retention.put(admissionTerm, retentionPerTerm);
                }
            }
        }
        return new TreeSet<>(retention.values());
    }

    public Collection<SubjectRetentionCSV> getRetention(String courseCode, String curriculumCode, String from,
                                                        String to, String subjectCode) throws InvalidParameterException {
        Collection<SubjectRetentionCSV> responses = new TreeSet<>();
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);

        Collection<NationalIdRegistrationKey> actives = this.activesPerCurriculumMap.get(new
                CurriculumKey(courseCode, curriculumCode));
        if (actives == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));

        for (NationalIdRegistrationKey active : actives) {
            StudentCurriculumProgress studentCurriculum = this.studentCurriculumProgressMap.get(active);
            StudentData studentData = studentsMap.get(active);
            String admissionTerm = studentData.getAdmissionTerm();
            if (admissionTerm.compareTo(from) >= 0 && admissionTerm.compareTo(to) <= 0) {
                if (studentCurriculum.getEnabled().contains(subjectKey)) {
                    SubjectData subject = this.subjectsMap.get(subjectKey);
                    int idealTerm = subject.getIdealTerm();
                    String name = subject.getName();
                    SubjectRetentionCSV response = new SubjectRetentionCSV(courseCode, curriculumCode,
                            idealTerm, subjectCode, name, active.getRegistration(), studentData.getAttemptedCredits(),
                            studentData.getMandatoryCredits(), 0, studentData.getOptionalCredits(),
                            studentData.getComplementaryCredits(), studentData.getCompletedTerms(),
                            studentData.getSuspendedTerms(), studentData.getInstitutionalEnrollments(),
                            studentData.getMobilityTerms(), studentData.getGpa());
                    responses.add(response);
                    LOGGER.debug(response.toString());
                }
            }
        }
        return responses;
    }

    public Map<String, Map<String, ClassEnrollments>> getSubjectMetricsPerTerm(String courseCode, String curriculumCode,
                                                                               String subjectCode) {
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        return this.enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
    }

    public AcademicUnitData getAuData(AcademicUnitKey academicUnitKey) {
        return this.academicUnitsMap.get(academicUnitKey);
    }

    public Collection<TeacherStatistics> getTeachersPerTerm(String academicUnitId, String courseCode,
                                                            String curriculumCode, String from, String to) {
        Collection<TeacherStatistics> response = new TreeSet<>();
        Collection<TeacherKey> teachers = this.teachersPerAcademicUnit.get(new AcademicUnitKey(academicUnitId));
        for (TeacherKey teacherKey : teachers) {
            Map<CurriculumKey, Map<String, Map<SubjectKey, Map<String, ClassEnrollments>>>> enrollmentsPerCurriuclum =
                    this.enrollmentDataPerTeacher.get(teacherKey);
            if (enrollmentsPerCurriuclum == null) continue;
            CurriculumKey curriculumKey = new CurriculumKey(courseCode, curriculumCode);
            Map<String, Map<SubjectKey, Map<String, ClassEnrollments>>> enrollmentsPerTerm =
                    enrollmentsPerCurriuclum.get(curriculumKey);
            if (enrollmentsPerTerm == null) continue;
            Collection<TeacherStatisticsPerTerm> terms = new TreeSet<>();
            TreeSet<String> termsSet = new TreeSet<>();
            enrollmentsPerTerm.keySet().forEach(term -> {
                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    Map<SubjectKey, Map<String, ClassEnrollments>> enrollmentsPerSubject = enrollmentsPerTerm.get(term);
                    int subjectsCount = enrollmentsPerSubject.keySet().size();
                    int classesCount = 0;
                    int totalEnrolled = 0;
                    int failedDueToAbsence = 0;
                    int failedDueToGrade = 0;
                    int cancelled = 0;
                    int succeeded = 0;
                    int ongoing = 0;
                    int exempted = 0;
                    int suspended = 0;
                    for (SubjectKey subjectKey : enrollmentsPerSubject.keySet()) {
                        Map<String, ClassEnrollments> enrollmentsPerClass = enrollmentsPerSubject.get(subjectKey);
                        classesCount += enrollmentsPerClass.keySet().size();
                        for (String classId : enrollmentsPerClass.keySet()) {
                            ClassEnrollments classEnrollments = enrollmentsPerClass.get(classId);
                            totalEnrolled += classEnrollments.getNumberOfEnrolleds();
                            failedDueToAbsence += classEnrollments.getNumberFailedDueToAbsence();
                            failedDueToGrade += classEnrollments.getNumberFailedDueToGrade();
                            cancelled += classEnrollments.getNumberCancelled();
                            succeeded += classEnrollments.getNumberSucceeded();
                            ongoing += classEnrollments.getNumberOngoing();
                            exempted += classEnrollments.getNumberExempted();
                            suspended += classEnrollments.getNumberSuspended();
                        }
                    }
                    TeacherStatisticsSummary teacherStatisticsSummary = new TeacherStatisticsSummary(subjectsCount,
                            classesCount, totalEnrolled, (double) totalEnrolled/classesCount,
                            (double) failedDueToAbsence/totalEnrolled,
                            (double) failedDueToGrade/totalEnrolled,
                            (double) cancelled/totalEnrolled, (double) succeeded/totalEnrolled,
                            (double) ongoing/totalEnrolled, (double) exempted/totalEnrolled,
                            (double) suspended/totalEnrolled);
                    terms.add(new TeacherStatisticsPerTerm(term, teacherStatisticsSummary));
                    termsSet.add(term);
                }
            });
            int termsListSize = termsSet.size();
            if (termsListSize > 0) {
                String first = termsSet.first();
                String last = termsSet.last();

                TeacherData teacherData = this.teachersMap.get(teacherKey);
                TeacherStatistics teachersData = new TeacherStatistics(first, last, teacherKey.getId(),
                        teacherData.getName(), terms);
                response.add(teachersData);
            }
        }
        return response;
    }

    public Map<SubjectScheduleKey, Map<String, Schedule>> getAllSchedules(String courseCode, String curriculumCode, String term) {
        Map<SubjectScheduleKey, Map<String, Schedule>> allSchedules = new HashMap<>();

        for (Map.Entry<ScheduleKey, ScheduleData> entry : this.scheduleMap.entrySet()) {
            ScheduleKey key = entry.getKey();
            ScheduleData value = entry.getValue();

            SubjectScheduleKey subjectScheduleKey = key.createSubjectScheduleKey();
            Schedule schedule = value.createSchedule(key);

            if (!allSchedules.containsKey(subjectScheduleKey)) {
                allSchedules.put(subjectScheduleKey, new HashMap<>());
            }

            if (key.getCourseCode().equals(courseCode) && key.getCurriculumCode().equals(curriculumCode) && key.getTerm().equals(term)) {
                allSchedules.get(subjectScheduleKey).put(key.getClassCode(), schedule);
            }
        }

        return allSchedules;
    }

    public Map<String, TeachersStatisticsSummary> getTeachersPerAcademicUnit(String courseCode, String curriculumCode,
                                                                             String from, String to) throws InvalidParameterException {
        Map<String, TeachersStatisticsSummary> response = new HashMap<>();
        CurriculumKey curriculumKey = new CurriculumKey(courseCode, curriculumCode);
        Collection<AcademicUnitKey> academicUnitKeys = this.academicUnitKeysPerCurriculum.get(curriculumKey);
        if (academicUnitKeys == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, curriculumCode, courseCode));

        academicUnitKeys.forEach(academicUnitKey -> {
            Map<String, TeachersSetAndEnrollments> enrollmentsIndexMap = this.enrollmentsPerAcademicUnitPerTerm.get(academicUnitKey);
            int aggregateTeachersCount = 0;
            int aggreateSuccess = 0;
            int aggregateFailedDueToGrade = 0;
            int aggregateFailedDueToAbsence = 0;;
            int aggregateSuspended = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            String minTerm = "";
            String maxTerm = "";
            ArrayList<String> termsList = new ArrayList<>();
            int numTerms = enrollmentsIndexMap.keySet().size();
            for (String term : enrollmentsIndexMap.keySet()) {
                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    TeachersSetAndEnrollments teachersAndEnrollments = enrollmentsIndexMap.get(term);
                    int teachersCount = teachersAndEnrollments.getTeachers().size();
                    aggregateTeachersCount += teachersCount;
                    if (teachersCount < min) {
                        min = teachersCount;
                        minTerm = term;
                    }
                    if (teachersCount > max) {
                        max = teachersCount;
                        maxTerm = term;
                    }
                    aggreateSuccess += teachersAndEnrollments.getSucceededCount();
                    aggregateFailedDueToGrade += teachersAndEnrollments.getFailedDueToGradeCount();
                    aggregateFailedDueToAbsence += teachersAndEnrollments.getFailedDueToAbsenceCount();
                    aggregateSuspended += teachersAndEnrollments.getSuspendedCount();
                    termsList.add(term);
                }
            }
            int termsListSize = termsList.size();
            if (termsListSize > 0) {
                Collections.sort(termsList);
                String first = termsList.get(0);
                String last = termsList.get(termsListSize - 1);

                TeachersStatisticsSummary teacherStatisticsSummary = new TeachersStatisticsSummary(first, last,
                        (double) aggregateTeachersCount / numTerms,
                        new TermCount(min, minTerm),
                        new TermCount(max, maxTerm),
                        (double) aggreateSuccess / numTerms,
                        (double) aggregateFailedDueToGrade / numTerms,
                        (double) aggregateFailedDueToAbsence / numTerms,
                        (double) aggregateSuspended / numTerms);
                response.put(academicUnitKey.getCode(), teacherStatisticsSummary);
            }
        });
        return response;
    }

    private void buildIndexes() {
        buildStudentIndexes();
        buildEnrollmentIndexes();
        buildTeachersIndex();
        buildSubjectIndexes();
        buildCurriculaIndexes();
        buildClassesIndexes();
    }

    private void buildStudentIndexes() {
        this.registrationMap = new HashMap<>();
        this.activesPerCurriculumMap = new HashMap<>();
        this.activesPerCurriculumPerAdmissionTermMap = new HashMap<>();
        this.alumniPerCurriculumMap = new HashMap<>();
        this.alumniPerCurriculumPerGraduationTermMap = new HashMap<>();
        this.dropoutsPerCurriculumMap = new HashMap<>();
        this.dropoutsPerCurriculumPerDropoutTermMap = new HashMap<>();
        this.studentCurriculumProgressMap = new HashMap<>();

        this.studentsMap.forEach((k, v) -> {
            this.registrationMap.put(k.getRegistration(), k);
            if (v.isActive()) {
                // All actives per course
                addToStudentIndex(k, v, this.activesPerCurriculumMap);
                // Actives per course per term
                addToStudentPerTermIndex(k, v, v.getAdmissionTerm(), this.activesPerCurriculumPerAdmissionTermMap);
                // Setup student progress on his/her curriculum
                this.studentCurriculumProgressMap.put(k, new StudentCurriculumProgress(v.getCompletedTerms(),
                        v.getMandatoryCredits(), v.getOptionalCredits(), v.getComplementaryCredits(),
                        v.getEnrolledCredits()));
            }
            if (v.isAlumnus()) {
                // All alumni per course
                addToStudentIndex(k, v, this.alumniPerCurriculumMap);
                // Alumni per course per graduation term
                addToStudentPerTermIndex(k, v, v.getStatusTerm(), this.alumniPerCurriculumPerGraduationTermMap);
            }
            if (v.isDropout()) {
                // All dropouts per course
                addToStudentIndex(k, v, this.dropoutsPerCurriculumMap);
                // Alumni per course per graduation term
                addToStudentPerTermIndex(k, v, v.getStatusTerm(), this.dropoutsPerCurriculumPerDropoutTermMap);
            }
        });
    }

    private void buildEnrollmentIndexes() {
        this.enrollmentsPerSubjectPerTermPerClass = new HashMap<>();
        this.enrollmentsPerTermPerSubjectPerClass = new HashMap<>();
        Map<Registration, Integer> attemptsSummary = new HashMap<>();

        this.enrollmentsMap.forEach((enrollmentKey, enrollmentData) -> {
            NationalIdRegistrationKey studentId = registrationMap.get(enrollmentKey.getRegistration());
            StudentData studentData = this.studentsMap.get(studentId);
            String course = studentData.getCourseCode();
            String curriculum = studentData.getCurriculumCode();
            SubjectKey subjectKey = new SubjectKey(course, curriculum, enrollmentKey.getSubjectCode());
            String term = enrollmentKey.getTerm();
            String classId = enrollmentData.getClassId();

            Map<String, Map<String, ClassEnrollments>> subjectEnrollmentsPerTermPerClass =
                    this.enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
            if (subjectEnrollmentsPerTermPerClass == null) {
                subjectEnrollmentsPerTermPerClass = new HashMap<>();
            }
            Map<SubjectKey, Map<String, ClassEnrollments>> termEnrollmentsPerSubjectPerClass =
                    this.enrollmentsPerTermPerSubjectPerClass.get(term);
            if (termEnrollmentsPerSubjectPerClass == null) {
                termEnrollmentsPerSubjectPerClass = new HashMap<>();
            }

            Map<String, ClassEnrollments> termEnrollmentsPerClass = subjectEnrollmentsPerTermPerClass.get(term);
            if (termEnrollmentsPerClass == null) {
                termEnrollmentsPerClass = new HashMap<>();
            }
            Map<String, ClassEnrollments> subjectEnrollmentsPerClass = termEnrollmentsPerSubjectPerClass.get(subjectKey);
            if (subjectEnrollmentsPerClass == null) {
                subjectEnrollmentsPerClass = new HashMap<>();
            }

            ClassEnrollments classEnrollments = termEnrollmentsPerClass.get(classId);
            if (classEnrollments == null) {
                classEnrollments = new ClassEnrollments();
            }
            updateClassEnrollments(classEnrollments, studentId, subjectKey, enrollmentData.getStatus());

            // The same ClassEnrollment is inserted in both indexes
            subjectEnrollmentsPerClass.put(enrollmentData.getClassId(), classEnrollments);
            subjectEnrollmentsPerTermPerClass.put(enrollmentKey.getTerm(), termEnrollmentsPerClass);
            this.enrollmentsPerSubjectPerTermPerClass.put(subjectKey, subjectEnrollmentsPerTermPerClass);

            termEnrollmentsPerClass.put(enrollmentData.getClassId(), classEnrollments);
            termEnrollmentsPerSubjectPerClass.put(subjectKey, termEnrollmentsPerClass);
            this.enrollmentsPerTermPerSubjectPerClass.put(term, termEnrollmentsPerSubjectPerClass);

            if (!enrollmentData.getStatus().equals(SystemConstants.STATUS_ONGOING) &&
                    !enrollmentData.getStatus().equals(SystemConstants.STATUS_CANCELLED)) {
                Integer currentCount = attemptsSummary.get(new Registration(enrollmentKey.getRegistration()));
                if (currentCount == null) {
                    currentCount = enrollmentData.getCredits();
                } else {
                    currentCount += enrollmentData.getCredits();
                }
                attemptsSummary.put(new Registration(enrollmentKey.getRegistration()), currentCount);
            }
        });
        attemptsSummary.forEach((registration, credits) -> {
            NationalIdRegistrationKey key = registrationMap.get(registration.getRegistration());
            StudentData student = getStudent(key);
            if (student != null) {
                student.setAttemptedCredits(credits);
                updateStudent(key, student);
            }
        });
    }

    private void buildTeachersIndex() {
        this.teachersPerAcademicUnit = new HashMap<>();
        this.teachersMap.forEach((teacherKey, teacherData) -> {
            AcademicUnitKey academicUnitKey = new AcademicUnitKey(teacherData.getAcademicUnitId());
            Collection<TeacherKey> teachers = this.teachersPerAcademicUnit.get(academicUnitKey);
            if (teachers == null) teachers = new TreeSet<>();
            teachers.add(teacherKey);
            this.teachersPerAcademicUnit.put(academicUnitKey, teachers);
        });
    }

    private void buildSubjectIndexes() {
        this.subjectCurriculaMap = new HashMap<>();
        this.subjectsMap.forEach((subjectKey, subjectData) -> {
            Collection<SubjectKey> subjectKeys = this.subjectCurriculaMap.get(subjectKey.getSubjectCode());
            if (subjectKeys == null) subjectKeys = new TreeSet<>();
            subjectKeys.add(subjectKey);
            this.subjectCurriculaMap.put(subjectKey.getSubjectCode(), subjectKeys);

            LOGGER.debug(String.format(Messages.PROCESSING_S_FOR_D_STUDENTS, subjectKey.getSubjectCode() + ":" +
                            subjectKey.getCurriculumCode(), this.activesPerCurriculumMap.get(
                            new CurriculumKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode())).size()));
            this.activesPerCurriculumMap.get(new CurriculumKey(subjectKey.getCourseCode(),
                    subjectKey.getCurriculumCode())).forEach(studentKey -> {
                StudentCurriculumProgress studentCurriculumProgress = this.studentCurriculumProgressMap.get(studentKey);
                if (hasNotCompleted(studentCurriculumProgress, subjectKey, subjectData) && !isDisabled(studentCurriculumProgress, subjectKey) && isNotOngoing(studentCurriculumProgress, subjectKey)) {
                    if (isEnabled(studentCurriculumProgress, subjectKey, subjectData)) {
                        studentCurriculumProgress.getEnabled().add(subjectKey);
                        CurriculumData curriculumData = this.curriculumMap.get(new CurriculumKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode()));
                        if (isAdequate(studentCurriculumProgress, curriculumData, subjectData.getIdealTerm())) {
                            studentCurriculumProgress.getAdequate().add(subjectKey);
                            LOGGER.debug(String.format(Messages.STUDENT_S_SHOULD_ENROLL_SUBJECT_S,
                                    studentKey.getRegistration(), subjectKey.getSubjectCode()));
                        } else {
                            LOGGER.debug(String.format(Messages.STUDENT_S_CAN_BUT_SHOULD_NOT_ENROLL_SUBJECT_S,
                                    studentKey.getRegistration(), subjectKey.getSubjectCode()));
                        }
                    } else {
                        studentCurriculumProgress.getDisabled().add(subjectKey);
                        LOGGER.debug(String.format(Messages.STUDENT_S_DISABLED_FOR_SUBJECT_S,
                                studentKey.getRegistration(), subjectKey.getSubjectCode()));
                    }
                } else {
                    LOGGER.debug(String.format(Messages.STUDENT_S_HAS_COMPLETED_SUBJECT_S,
                            studentKey.getRegistration(), subjectKey.getSubjectCode()));
                }
            });
        });
    }

    private void buildCurriculaIndexes() {
        this.academicUnitKeysPerCurriculum = new HashMap<>();
        this.courseCurriculaMap = new HashMap<>();
        this.curriculumMap.forEach((curriculumKey, curriculumData) -> {
            Collection<String> curriculumCodes = this.courseCurriculaMap.get(curriculumKey.getCourseCode());
            if (curriculumCodes == null) {
                curriculumCodes = new TreeSet<>();
            }
            curriculumCodes.add(curriculumKey.getCurriculumCode());
            this.courseCurriculaMap.put(curriculumKey.getCourseCode(), curriculumCodes);

            Collection<AcademicUnitKey> academicUnitIds = new ArrayList<>();
            Collection<String> subjectCodes = new TreeSet<>();
            subjectCodes.addAll(curriculumData.getAllSubjectsList());
            for (String subjectCode : subjectCodes) {
                SubjectKey subjectKey = new SubjectKey(curriculumKey.getCourseCode(),
                        curriculumKey.getCurriculumCode(), subjectCode);
                SubjectData subjectData = this.subjectsMap.get(subjectKey);
                if (subjectData == null) continue;
                String academicUnitId = subjectData.getAcademicUnitId();
                academicUnitIds.add(new AcademicUnitKey(academicUnitId));
            }
            this.academicUnitKeysPerCurriculum.put(curriculumKey, academicUnitIds);
        });
    }

    private void buildClassesIndexes() {
        this.enrollmentDataPerTeacher = new HashMap<>();
        this.enrollmentsPerAcademicUnitPerTerm = new HashMap<>();
        for (Map.Entry<SubjectCodeTermClassIdKey, TeachersListData> entry : this.classesMap.entrySet()) {
            SubjectCodeTermClassIdKey subjectCodeTermClassId = entry.getKey();
            TeachersListData teachersList = entry.getValue();
            String term = subjectCodeTermClassId.getTerm();
            String classId = subjectCodeTermClassId.getClassId();
            Collection<SubjectKey> subjectKeys = this.subjectCurriculaMap.get(subjectCodeTermClassId.getSubjectCode());
            if (subjectKeys == null) continue;
            for (SubjectKey subjectKey : subjectKeys) {
                CurriculumKey curriculumKey = new CurriculumKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode());
                Collection<AcademicUnitKey> academicUnitKeys = this.academicUnitKeysPerCurriculum.get(curriculumKey);

                Map<String, Map<String, ClassEnrollments>> enrollmentsMap = this.enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
                if (enrollmentsMap == null) {
                    LOGGER.info(String.format(Messages.COULD_NOT_FIND_ENTRY_S, subjectCodeTermClassId.toString()));
                    continue;
                }
                Map<String, ClassEnrollments> enrollmentsPerTermMap = enrollmentsMap.get(term);
                if (enrollmentsPerTermMap == null) {
                    LOGGER.info(String.format(Messages.COULD_NOT_FIND_TERM_S, subjectCodeTermClassId.toString()));
                    continue;
                }
                ClassEnrollments enrollments = enrollmentsPerTermMap.get(classId);
                if (enrollments == null) {
                    LOGGER.info(String.format(Messages.COULD_NOT_FIND_CLASS_S, subjectCodeTermClassId.toString()));
                    continue;
                }

                String[] teachersId = teachersList.getTeachers().split(",");
                for (String teacherId : teachersId) {
                    TeacherKey teacherKey = new TeacherKey(teacherId);
                    Map<CurriculumKey, Map<String, Map<SubjectKey, Map<String, ClassEnrollments>>>> teachersMap =
                            this.enrollmentDataPerTeacher.get(teacherKey);
                    if (teachersMap == null) teachersMap = new HashMap<>();
                    Map<String, Map<SubjectKey, Map<String, ClassEnrollments>>> termsMap = teachersMap.get(curriculumKey);
                    if (termsMap == null) termsMap = new HashMap<>();
                    Map<SubjectKey, Map<String, ClassEnrollments>> subjectsMap = termsMap.get(subjectKey);
                    if (subjectsMap == null) subjectsMap = new HashMap<>();
                    Map<String, ClassEnrollments> classMap = new HashMap<>();
                    classMap.put(subjectCodeTermClassId.getClassId(), enrollments);
                    subjectsMap.put(subjectKey, classMap);
                    termsMap.put(subjectCodeTermClassId.getTerm(), subjectsMap);
                    teachersMap.put(curriculumKey, termsMap);
                    this.enrollmentDataPerTeacher.put(teacherKey, teachersMap);
                }

                academicUnitKeys.forEach(academicUnitKey -> {
                    Map<String, TeachersSetAndEnrollments> terms = this.enrollmentsPerAcademicUnitPerTerm.get(academicUnitKey);
                    if (terms == null) terms = new HashMap<>();
                    TeachersSetAndEnrollments teachersSetAndEnrollments = terms.get(term);
                    Set<String> newTeachers = new TreeSet<>(Arrays.asList(teachersId));
                    if (teachersSetAndEnrollments == null) {
                        teachersSetAndEnrollments = new TeachersSetAndEnrollments(newTeachers,
                                enrollments.getNumberSucceeded(), enrollments.getNumberFailedDueToGrade(),
                                enrollments.getNumberFailedDueToAbsence(), enrollments.getNumberSuspended());
                    } else {
                        teachersSetAndEnrollments.getTeachers().addAll(newTeachers);
                        teachersSetAndEnrollments.addEnrollments(enrollments);
                    }
                    terms.put(term, teachersSetAndEnrollments);
                    this.enrollmentsPerAcademicUnitPerTerm.put(academicUnitKey, terms);
                });
            }
        }
    }

    private void addToStudentIndex(NationalIdRegistrationKey k, StudentData v, Map<CurriculumKey,
            Collection<NationalIdRegistrationKey>> map) {
        LOGGER.debug(String.format(Messages.INDEX_ACTIVE_S, v.getName()));
        Collection<NationalIdRegistrationKey> activesPerCourse = map.get(new
                CurriculumKey(v.getCourseCode(), v.getCurriculumCode()));
        if (activesPerCourse == null) activesPerCourse = new ArrayList<>();
        activesPerCourse.add(k);
        map.put(new CurriculumKey(v.getCourseCode(), v.getCurriculumCode()), activesPerCourse);
    }

    private void addToStudentPerTermIndex(NationalIdRegistrationKey k, StudentData v, String term, Map<CurriculumKey,
            Map<String, Collection<NationalIdRegistrationKey>>> perTermMap) {
        Map<String, Collection<NationalIdRegistrationKey>> activesPerCoursePerAdmissionTerm =
                perTermMap.get(new CurriculumKey(v.getCourseCode(), v.getCurriculumCode()));
        if (activesPerCoursePerAdmissionTerm == null) activesPerCoursePerAdmissionTerm = new HashMap<>();
        Collection<NationalIdRegistrationKey> activesPerAdmissionTerm = activesPerCoursePerAdmissionTerm.get(term);
        if (activesPerAdmissionTerm == null) activesPerAdmissionTerm = new ArrayList<>();
        activesPerAdmissionTerm.add(k);
        activesPerCoursePerAdmissionTerm.put(term, activesPerAdmissionTerm);
        perTermMap.put(new CurriculumKey(v.getCourseCode(), v.getCurriculumCode()), activesPerCoursePerAdmissionTerm);
    }

    private boolean hasNotCompleted(StudentCurriculumProgress curriculum, SubjectKey subjectKey, SubjectData subjectData) {
        if (curriculum.getCompleted().contains(subjectKey)) return false;
        for (String subjectCode : subjectData.getEquivalentCodesList()) {
            SubjectKey equivalentSubjectKey = new SubjectKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode(),
                    subjectCode);
            if (curriculum.getCompleted().contains(equivalentSubjectKey)) {
                curriculum.getCompleted().add(equivalentSubjectKey);
                return false;
            }
        }
        return true;
    }

    private boolean isDisabled(StudentCurriculumProgress curriculum, SubjectKey subjectKey) {
        return curriculum.getDisabled().contains(subjectKey);
    }

    private boolean isNotOngoing(StudentCurriculumProgress curriculum, SubjectKey subjectKey) {
        return !curriculum.getOngoing().contains(subjectKey);
    }

    private boolean isEnabled(StudentCurriculumProgress curriculum, SubjectKey subjectKey, SubjectData subjectData) {
        for (String subjectCode : subjectData.getPreRequirementsList()) {
            SubjectKey preRequirementKey = new SubjectKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode(),
                    subjectCode);
            SubjectData preRequirementData = this.subjectsMap.get(preRequirementKey);
            if (hasNotCompleted(curriculum, preRequirementKey, preRequirementData) &&
                    isNotOngoing(curriculum, preRequirementKey)) {
                LOGGER.debug(String.format(Messages.NOT_ENABLED_FOR_SUBJECT_S_PRE_REQUIEREMNT_S,
                        subjectKey.getSubjectCode(),
                        (hasNotCompleted(curriculum, preRequirementKey, preRequirementData) ? Messages.HAS_NOT_COMPLETED : Messages.HAS_COMPLETED),
                        (isNotOngoing(curriculum, subjectKey) ? Messages.IS_NOT_ENROLLED : Messages.IS_ENROLLED), preRequirementKey.getSubjectCode()));
                return false;
            }
        }
        return true;
    }

    private boolean isAdequate(StudentCurriculumProgress studentCurriculum, CurriculumData curriculumData,
                               int subjectIdealTerm) {
        int potentiallyAccumulatedCredits = studentCurriculum.getCompletedMandatoryCredits() +
                studentCurriculum.getCompletedOptionalCredits() + studentCurriculum.getCompletedComplementaryCredits() +
                studentCurriculum.getEnrolledCredits();
        int expectedAccumulatedCredits = curriculumData.getExpectedMinAccumulatedCredits(subjectIdealTerm);
        return potentiallyAccumulatedCredits >= expectedAccumulatedCredits;
    }

    private void updateClassEnrollments(ClassEnrollments classEnrollments, NationalIdRegistrationKey studentId,
                                        SubjectKey subjectKey, String status) {
        StudentCurriculumProgress studentCurriculumProgress;
        switch(status) {
            case SystemConstants.STATUS_SUCCEEDED:
                classEnrollments.getSucceeded().add(studentId.getRegistration());
                if ((studentCurriculumProgress = retrieveCurriculum(studentId)) != null) {
                    studentCurriculumProgress.getCompleted().add(subjectKey);
                    LOGGER.debug(String.format(Messages.STUDENT_S_COMPLETED_S, studentId.getRegistration(),
                            subjectKey.getSubjectCode()));
                }
                break;
            case SystemConstants.STATUS_EXEMPTED:
                classEnrollments.getExempted().add(studentId.getRegistration());
                if ((studentCurriculumProgress = retrieveCurriculum(studentId)) != null) {
                    studentCurriculumProgress.getCompleted().add(subjectKey);
                    LOGGER.debug(String.format(Messages.STUDENT_S_IS_EXEMPTED_S, studentId.getRegistration(),
                            subjectKey.getSubjectCode()));
                }
                break;
            case SystemConstants.STATUS_ONGOING:
                classEnrollments.getOngoing().add(studentId.getRegistration());
                if ((studentCurriculumProgress = retrieveCurriculum(studentId)) != null) {
                    studentCurriculumProgress.getOngoing().add(subjectKey);
                    LOGGER.debug(String.format(Messages.STUDENT_S_IS_ENROLLED_IN_S, studentId.getRegistration(),
                            subjectKey.getSubjectCode()));
                }
                break;
            case SystemConstants.STATUS_FAILED_DUE_GRADE:
                classEnrollments.getFailedDueToGrade().add(studentId.getRegistration());
                break;
            case SystemConstants.STATUS_FAILED_DUE_ABSENCE:
                classEnrollments.getFailedDueToAbsence().add(studentId.getRegistration());
                break;
            case SystemConstants.STATUS_SUSPENDED:
                classEnrollments.getSuspended().add(studentId.getRegistration());
                break;
            default:
                classEnrollments.getCancelled().add(studentId.getRegistration());
                break;
        }
    }

    private StudentCurriculumProgress retrieveCurriculum(NationalIdRegistrationKey studentId) {
        StudentCurriculumProgress curriculum = null;
        StudentData student = this.studentsMap.get(studentId);
        if (student.isActive()) {
            curriculum = this.studentCurriculumProgressMap.get(studentId);
        }
        return curriculum;
    }

    private StudentData getStudent(NationalIdRegistrationKey key) {
        return this.studentsMap.get(key);
    }

    private void updateStudent(NationalIdRegistrationKey studentKey, StudentData student) {
        this.studentsMap.replace(studentKey, student);
    }

    private Collection<Student> getAllStudents(Collection<NationalIdRegistrationKey> studentKeys) throws InvalidParameterException {
        Collection<Student> allStudents = new ArrayList<>();
        Map<NationalIdRegistrationKey, StudentData> mapStudents = this.mapsHolder.getMap("students");
        for (NationalIdRegistrationKey k : studentKeys) {
            StudentData studentData = mapStudents.get(k);
            Curriculum curriculum = getCurriculum(studentData.getCourseCode(), studentData.getCurriculumCode());
            allStudents.add(studentData.createStudent(k, curriculum));
        }
        return allStudents;
    }
}
