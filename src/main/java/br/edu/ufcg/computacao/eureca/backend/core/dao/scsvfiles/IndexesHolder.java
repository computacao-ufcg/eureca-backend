package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectRetentionPerAdmissionTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectRetentionCSV;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.TeacherData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.StudentCurriculumProgress;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

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
    private final Map<SubjectCodeTermClassIdKey, TeacherData> classesMap;
    private final Map<AcademicUnitKey, AcademicUnitData> academicUnitsMap;
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
    // Curricula indexes
    private Map<String, Collection<String>> courseCurriculaMap;
    // AcademicUnit indexes
    private Map<String, Collection<String>> subjectsPerAcademicUnit;

    public IndexesHolder(MapsHolder mapsHolder) {
        this.mapsHolder = mapsHolder;
        this.studentsMap = this.mapsHolder.getMap("students");
        this.enrollmentsMap = this.mapsHolder.getMap("enrollments");
        this.subjectsMap = this.mapsHolder.getMap("subjects");
        this.curriculumMap = this.mapsHolder.getMap("curriculum");
        this.classesMap = this.mapsHolder.getMap("classes");
        this.academicUnitsMap = this.mapsHolder.getMap("academicUnits");
        buildIndexes();
    }

    public Collection<NationalIdRegistrationKey> getAlumniPerCourseMap(String courseCode) {
        Collection<NationalIdRegistrationKey> alumni = new TreeSet<>();
        Collection<String> currilumCodes = this.getCurricula(courseCode);
        currilumCodes.forEach(curriculumCode -> {
            alumni.addAll(this.alumniPerCurriculumMap.get(courseCode + ":" + curriculumCode));
        });
        return alumni;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getActivesPerCoursePerAdmissionTerm(String courseCode, String curriculumCode) {
        return this.activesPerCurriculumPerAdmissionTermMap.get(new CurriculumKey(courseCode, curriculumCode));
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getAlumniPerGraduationTerm(String courseCode, String curriculumCode) {
        return this.alumniPerCurriculumPerGraduationTermMap.get(new CurriculumKey(courseCode, curriculumCode));
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutsPerDropoutTerm(String courseCode, String curriculumCode) {
        return this.dropoutsPerCurriculumPerDropoutTermMap.get(new CurriculumKey(courseCode, curriculumCode));
    }

    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerSubjectPerTermPerClass() {
        return this.enrollmentsPerSubjectPerTermPerClass;
    }

    public Map<String, Map<SubjectKey, Map<String, ClassEnrollments>>> getEnrollmentsPerTermPerSubjectPerClass() {
        return this.enrollmentsPerTermPerSubjectPerClass;
    }

    private void buildIndexes() {
        buildStudentIndexes();
        buildEnrollmentIndexes();
        buildSubjectIndexes();
        buildCurriculaIndexes();
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
                this.studentCurriculumProgressMap.put(k, new StudentCurriculumProgress((v.getCompletedTerms() + 1),
                        (v.getMandatoryCredits() + v.getOptionalCredits() + v.getComplementaryCredits()),
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

    private void addToStudentIndex(NationalIdRegistrationKey k, StudentData v, Map<CurriculumKey, Collection<NationalIdRegistrationKey>> map) {
        LOGGER.debug(String.format(Messages.INDEX_ACTIVE_S, v.getName()));
        Collection<NationalIdRegistrationKey> activesPerCourse = map.get(new CurriculumKey(v.getCourseCode(), v.getCurriculumCode()));
        if (activesPerCourse == null) activesPerCourse = new ArrayList<>();
        activesPerCourse.add(k);
        map.put(new CurriculumKey(v.getCourseCode(), v.getCurriculumCode()), activesPerCourse);
    }

    private void addToStudentPerTermIndex(NationalIdRegistrationKey k, StudentData v, String term, Map<CurriculumKey, Map<String, Collection<NationalIdRegistrationKey>>> perTermMap) {
        Map<String, Collection<NationalIdRegistrationKey>> activesPerCoursePerAdmissionTerm = perTermMap.get(new CurriculumKey(v.getCourseCode(), v.getCurriculumCode()));
        if (activesPerCoursePerAdmissionTerm == null) activesPerCoursePerAdmissionTerm = new HashMap<>();
        Collection<NationalIdRegistrationKey> activesPerAdmissionTerm = activesPerCoursePerAdmissionTerm.get(term);
        if (activesPerAdmissionTerm == null) activesPerAdmissionTerm = new ArrayList<>();
        activesPerAdmissionTerm.add(k);
        activesPerCoursePerAdmissionTerm.put(term, activesPerAdmissionTerm);
        perTermMap.put(new CurriculumKey(v.getCourseCode(), v.getCurriculumCode()), activesPerCoursePerAdmissionTerm);
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
            // The same ClassEnrollment is inserted in both indexes
            ClassEnrollments classEnrollments = termEnrollmentsPerClass.get(classId);
            if (classEnrollments == null) {
                classEnrollments = new ClassEnrollments();
            }
            updateClassEnrollments(classEnrollments, studentId, subjectKey, enrollmentData.getStatus());

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

    private void buildSubjectIndexes() {
        this.subjectsMap.forEach((subjectKey, subjectData) -> {
            LOGGER.debug(String.format(Messages.PROCESSING_S_FOR_D_STUDENTS, subjectKey.getSubjectCode() + ":" +
                            subjectKey.getCurriculumCode(), this.activesPerCurriculumMap.get(subjectKey.getCourseCode() +
                            ":" + subjectKey.getCurriculumCode()).size()));
            this.activesPerCurriculumMap.get(subjectKey.getCourseCode() + ":" + subjectKey.getCurriculumCode()).forEach(studentKey -> {
                StudentCurriculumProgress studentCurriculumProgress = this.studentCurriculumProgressMap.get(studentKey);
                if (!hasCompleted(studentCurriculumProgress, subjectKey, subjectData) &&
                            !isDisabled(studentCurriculumProgress, subjectKey) &&
                            !isOngoing(studentCurriculumProgress, subjectKey)) {
                    if (isEnabled(studentCurriculumProgress, subjectKey, subjectData)) {
                        studentCurriculumProgress.getEnabled().add(subjectKey);
                        CurriculumData curriculumData = this.curriculumMap.get(new
                                CurriculumKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode()));
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
        this.courseCurriculaMap = new HashMap<>();
        this.curriculumMap.forEach((k, v) -> {
            Collection<String> curriculumCodes = this.courseCurriculaMap.get(k.getCourseCode());
            if (curriculumCodes == null) {
                curriculumCodes = new TreeSet<>();
            }
            curriculumCodes.add(k.getCurriculumCode());
            this.courseCurriculaMap.put(k.getCourseCode(), curriculumCodes);
        });
    }

    private boolean hasCompleted(StudentCurriculumProgress curriculum, SubjectKey subjectKey, SubjectData subjectData) {
        if (curriculum.getCompleted().contains(subjectKey)) return true;
        for (String subjectCode : subjectData.getEquivalentCodesList()) {
            SubjectKey equivalentSubjectKey = new SubjectKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode(),
                    subjectCode);
            if (curriculum.getCompleted().contains(equivalentSubjectKey)) {
                curriculum.getCompleted().add(equivalentSubjectKey);
                return true;
            }
        }
        return false;
    }

    private boolean isDisabled(StudentCurriculumProgress curriculum, SubjectKey subjectKey) {
        return curriculum.getDisabled().contains(subjectKey);
    }

    private boolean isOngoing(StudentCurriculumProgress curriculum, SubjectKey subjectKey) {
        return curriculum.getOngoing().contains(subjectKey);
    }

    private boolean isEnabled(StudentCurriculumProgress curriculum, SubjectKey subjectKey, SubjectData subjectData) {
        for (String subjectCode : subjectData.getPreRequirementsList()) {
            SubjectKey preRequirementKey = new SubjectKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode(),
                    subjectCode);
            SubjectData preRequirementData = this.subjectsMap.get(preRequirementKey);
            if (!hasCompleted(curriculum, preRequirementKey, preRequirementData) && !isOngoing(curriculum, preRequirementKey)) {
                LOGGER.debug(String.format(Messages.NOT_ENABLED_FOR_SUBJECT_S_PRE_REQUIEREMNT_S, subjectKey.getSubjectCode(),
                        (!hasCompleted(curriculum, preRequirementKey, preRequirementData) ? Messages.HAS_NOT_COMPLETED : Messages.HAS_COMPLETED),
                        (!isOngoing(curriculum, subjectKey) ? Messages.IS_NOT_ENROLLED : Messages.IS_ENROLLED), preRequirementKey.getSubjectCode()));
                return false;
            }
        }
        return true;
    }

    private boolean isAdequate(StudentCurriculumProgress studentCurriculum, CurriculumData curriculumData, int subjectIdealTerm) {
        int potentiallyAccumulatedCredits = studentCurriculum.getAccumulatedCredits() + studentCurriculum.getEnrolledCredits();
        int expectedAccumulatedCredits = curriculumData.getExpectedMinAccumulatedCreditsList(subjectIdealTerm);
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
                    LOGGER.debug(String.format(Messages.STUDENT_S_COMPLETED_S, studentId.getRegistration(), subjectKey.getSubjectCode()));
                }
                break;
            case SystemConstants.STATUS_EXEMPTED:
                classEnrollments.getExempted().add(studentId.getRegistration());
                if ((studentCurriculumProgress = retrieveCurriculum(studentId)) != null) {
                    studentCurriculumProgress.getCompleted().add(subjectKey);
                    LOGGER.debug(String.format(Messages.STUDENT_S_IS_EXEMPTED_S, studentId.getRegistration(), subjectKey.getSubjectCode()));
                }
                break;
            case SystemConstants.STATUS_ONGOING:
                classEnrollments.getOngoing().add(studentId.getRegistration());
                if ((studentCurriculumProgress = retrieveCurriculum(studentId)) != null) {
                    studentCurriculumProgress.getOngoing().add(subjectKey);
                    LOGGER.debug(String.format(Messages.STUDENT_S_IS_ENROLLED_IN_S, studentId.getRegistration(), subjectKey.getSubjectCode()));
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

    public Collection<String> getCurricula(String courseCode) {
        return this.courseCurriculaMap.get(courseCode);
    }

    public Curriculum getCurriculum(String course, String code) {
        Map<CurriculumKey, CurriculumData> curriculumMap = this.mapsHolder.getMap("curriculum");
        CurriculumKey key = new CurriculumKey(course, code);
        CurriculumData ret = curriculumMap.get(key);
        return ret.createCurriculum(key);
    }

    public Collection<Student> getAllActives(String courseCode, String curriculumCode) {
        Collection<NationalIdRegistrationKey> actives = this.activesPerCurriculumMap.get(courseCode + ":" + curriculumCode);
        return getAllStudents(actives);
    }

    public Collection<Student> getAllAlumni(String courseCode, String curriculumCode) {
        Collection<NationalIdRegistrationKey> alumni = this.alumniPerCurriculumMap.get(courseCode + ":" + curriculumCode);
        return getAllStudents(alumni);
    }

    public Collection<Student> getAllDropouts(String courseCode, String curriculumCode) {
        Collection<NationalIdRegistrationKey> dropouts = this.dropoutsPerCurriculumMap.get(courseCode + ":" + curriculumCode);
        return getAllStudents(dropouts);
    }

    private Collection<Student> getAllStudents(Collection<NationalIdRegistrationKey> studentKeys) {
        Collection<Student> allStudents = new ArrayList<>();
        Map<NationalIdRegistrationKey, StudentData> mapStudents = this.mapsHolder.getMap("students");
        studentKeys.forEach(k -> {
            StudentData studentData = mapStudents.get(k);
            Curriculum curriculum = getCurriculum(studentData.getCourseCode(), studentData.getCurriculumCode());
            allStudents.add(studentData.createStudent(k, curriculum));
        });
        return allStudents;
    }

    public Collection<Enrollment> getAllEnrollments() {
        Collection<Enrollment> enrollments = new ArrayList<>();
        for (Map.Entry<RegistrationSubjectCodeTermKey, EnrollmentData> entry : this.enrollmentsMap.entrySet()) {
            RegistrationSubjectCodeTermKey key = entry.getKey();
            EnrollmentData data = entry.getValue();
            Enrollment enrollment = data.createEnrollment(key);
            enrollments.add(enrollment);
        }
        return enrollments;
    }

    public Collection<SubjectRetentionPerAdmissionTerm> getRetentionCount(String courseCode, String curriculumCode, String subjectCode, String from, String to) {
        Map<String, SubjectRetentionPerAdmissionTerm> retention = new HashMap<>();
        for (NationalIdRegistrationKey active : this.activesPerCurriculumMap.get(new CurriculumKey(courseCode, curriculumCode))) {
            StudentCurriculumProgress studentCurriculum = this.studentCurriculumProgressMap.get(active);
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            StudentData studentData = this.studentsMap.get(active);
            String admissionTerm = studentData.getAdmissionTerm();
            if (admissionTerm.compareTo(from) >= 0 && admissionTerm.compareTo(to) <= 0) {
                if (studentCurriculum.getEnabled().contains(subjectKey)) {
                    CurriculumKey curriculumKey = new CurriculumKey(studentData.getCourseCode(), studentData.getCurriculumCode());
                    CurriculumData curriculumData = this.curriculumMap.get(curriculumKey);
                    SubjectData subjectData = this.subjectsMap.get(subjectKey);
                    SubjectRetentionPerAdmissionTerm retentionPerTerm = retention.get(admissionTerm);
                    if (retentionPerTerm == null) retentionPerTerm = new SubjectRetentionPerAdmissionTerm(admissionTerm, 0, 0);
                    retentionPerTerm.incrementPossible();
                    if (isAdequate(studentCurriculum, curriculumData, subjectData.getIdealTerm())) {
                        retentionPerTerm.incrementAdequate();
                    }
                    retention.put(admissionTerm, retentionPerTerm);
                }
            }
        }
        Collection<SubjectRetentionPerAdmissionTerm> response = new TreeSet<>();
        response.addAll(retention.values());
        return response;
    }

    public Collection<SubjectRetentionCSV> getRetention(String courseCode, String curriculumCode, String from, String to, String subjectCode) {
        Collection<SubjectRetentionCSV> responses = new TreeSet<>();
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        for (NationalIdRegistrationKey active : this.activesPerCurriculumMap.get(new CurriculumKey(courseCode, curriculumCode))) {
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
                            studentData.getComplementaryCredits(), studentData.getCompletedTerms(), studentData.getSuspendedTerms(),
                            studentData.getInstitutionalEnrollments(), studentData.getMobilityTerms(), studentData.getGpa());
                    responses.add(response);
                    LOGGER.debug(response.toString());
                }
            }
        }
        return responses;
    }

    public Map<String, Map<String, ClassEnrollments>> getSubjectMetricsPerTerm(String courseCode, String curriculumCode, String subjectCode) {
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        return this.enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
    }
}
