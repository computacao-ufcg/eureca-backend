package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

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
    private Map<String, Collection<NationalIdRegistrationKey>> activesPerCourseMap;
    private Map<String, Map<String, Collection<NationalIdRegistrationKey>>> activesPerCoursePerAdmissionTermMap;
    private Map<String, Collection<NationalIdRegistrationKey>> alumniPerCourseMap;
    private Map<String, Map<String, Collection<NationalIdRegistrationKey>>> alumniPerCoursePerGraduationTermMap;
    private Map<String, Collection<NationalIdRegistrationKey>> dropoutsPerCourseMap;
    private Map<String, Map<String, Collection<NationalIdRegistrationKey>>> dropoutsPerCoursePerDropoutTermMap;
    // Enrollment indexes
//    private Map<CurriculumKey, Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>>> enrollmentsPerCurriculumPerSubjectPerTermPerClass;
//    private Map<AcademicUnitKey, Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>>> enrollmentsPerAcademicUnitPerSubjectPerTermPerClass;
//    private Map<AcademicUnitKey, Collection<String>> termsPerAcademicUnit;
//    private Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerSubjectPerTermPerClass;
//    private Map<CurriculumKey, TreeSet<String>> termsPerCurriculum;
//    private Map<SubjectKey, Map<String, Collection<String>>> classesPerSubjectPerTerm;
    private Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerSubjectPerTermPerClass;
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
        return this.alumniPerCourseMap.get(courseCode);
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getActivesPerCoursePerAdmissionTerm(String courseCode) {
        return this.activesPerCoursePerAdmissionTermMap.get(courseCode);
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getAlumniPerGraduationTerm(String courseCode) {
        return this.alumniPerCoursePerGraduationTermMap.get(courseCode);
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutsPerDropoutTerm(String courseCode) {
        return this.dropoutsPerCoursePerDropoutTermMap.get(courseCode);
    }

    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerSubjectPerTermPerClass() {
        return this.enrollmentsPerSubjectPerTermPerClass;
    }

    private void buildIndexes() {
        buildStudentIndexes();
        buildEnrollmentIndexes();
        buildSubjectIndexes();
        buildCurriculaIndexes();
    }

    private void buildStudentIndexes() {
        this.registrationMap = new HashMap<>();
        this.activesPerCourseMap = new HashMap<>();
        this.activesPerCoursePerAdmissionTermMap = new HashMap<>();
        this.alumniPerCourseMap = new HashMap<>();
        this.alumniPerCoursePerGraduationTermMap = new HashMap<>();
        this.dropoutsPerCourseMap = new HashMap<>();
        this.dropoutsPerCoursePerDropoutTermMap = new HashMap<>();
        this.studentCurriculumProgressMap = new HashMap<>();

        this.studentsMap.forEach((k, v) -> {
            this.registrationMap.put(k.getRegistration(), k);
            if (v.isActive()) {
                // All actives per course
                addToStudentIndex(k, v, this.activesPerCourseMap);
                // Actives per course per term
                addToStudentPerTermIndex(k, v, v.getAdmissionTerm(), this.activesPerCoursePerAdmissionTermMap);
                // Setup student progress on his/her curriculum
                this.studentCurriculumProgressMap.put(k, new StudentCurriculumProgress((v.getCompletedTerms() + 1),
                        (v.getMandatoryCredits() + v.getOptionalCredits() + v.getComplementaryCredits()),
                                v.getEnrolledCredits()));
            }
            if (v.isAlumnus()) {
                // All alumni per course
                addToStudentIndex(k, v, this.alumniPerCourseMap);
                // Alumni per course per graduation term
                addToStudentPerTermIndex(k, v, v.getStatusTerm(), this.alumniPerCoursePerGraduationTermMap);
            }
            if (v.isDropout()) {
                // All dropouts per course
                addToStudentIndex(k, v, this.dropoutsPerCourseMap);
                // Alumni per course per graduation term
                addToStudentPerTermIndex(k, v, v.getStatusTerm(), this.dropoutsPerCoursePerDropoutTermMap);
            }
        });
    }

    private void addToStudentIndex(NationalIdRegistrationKey k, StudentData v, Map<String, Collection<NationalIdRegistrationKey>> map) {
        LOGGER.debug(String.format(Messages.INDEX_ACTIVE_S, v.getName()));
        Collection<NationalIdRegistrationKey> activesPerCourse = map.get(v.getCourseCode());
        if (activesPerCourse == null) activesPerCourse = new ArrayList<>();
        activesPerCourse.add(k);
        map.put(v.getCourseCode(), activesPerCourse);
    }

    private void addToStudentPerTermIndex(NationalIdRegistrationKey k, StudentData v, String term, Map<String, Map<String, Collection<NationalIdRegistrationKey>>> perTermMap) {
        Map<String, Collection<NationalIdRegistrationKey>> activesPerCoursePerAdmissionTerm = perTermMap.get(v.getCourseCode());
        if (activesPerCoursePerAdmissionTerm == null) activesPerCoursePerAdmissionTerm = new HashMap<>();
        Collection<NationalIdRegistrationKey> activesPerAdmissionTerm = activesPerCoursePerAdmissionTerm.get(term);
        if (activesPerAdmissionTerm == null) activesPerAdmissionTerm = new ArrayList<>();
        activesPerAdmissionTerm.add(k);
        activesPerCoursePerAdmissionTerm.put(term, activesPerAdmissionTerm);
        perTermMap.put(v.getCourseCode(), activesPerCoursePerAdmissionTerm);
    }

    private void buildEnrollmentIndexes() {
        this.enrollmentsPerSubjectPerTermPerClass = new HashMap<>();
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
            Map<String, ClassEnrollments> termEnrollmentsPerClass = subjectEnrollmentsPerTermPerClass.get(term);
            if (termEnrollmentsPerClass == null) {
                termEnrollmentsPerClass = new HashMap<>();
            }
            ClassEnrollments classEnrollments = termEnrollmentsPerClass.get(classId);
            if (classEnrollments == null) {
                classEnrollments = new ClassEnrollments();
            }

            updateClassEnrollments(classEnrollments, studentId, subjectKey, enrollmentData.getStatus());
            termEnrollmentsPerClass.put(enrollmentData.getClassId(), classEnrollments);
            subjectEnrollmentsPerTermPerClass.put(enrollmentKey.getTerm(), termEnrollmentsPerClass);
            this.enrollmentsPerSubjectPerTermPerClass.put(subjectKey, subjectEnrollmentsPerTermPerClass);

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

//    private void buildEnrollmentsPerCurriculum() {
//        this.enrollmentsPerCurriculumPerSubjectPerTermPerClass = new HashMap<>();
//        this.enrollmentsPerSubjectPerTermPerClass = new HashMap<>();
//        this.termsPerCurriculum = new HashMap<>();
//        this.classesPerSubjectPerTerm = new HashMap<>();
//        Map<Registration, Integer> attemptsSummary = new HashMap<>();
//
//        this.enrollmentsMap.forEach((enrollmentKey, enrollmentData) -> {
//            NationalIdRegistrationKey studentId = registrationMap.get(enrollmentKey.getRegistration());
//            StudentData studentData = this.studentsMap.get(studentId);
//            String course = studentData.getCourseCode();
//            String curriculum = studentData.getCurriculumCode();
//            CurriculumKey curriculumKey = new CurriculumKey(course, curriculum);
//            SubjectKey subjectKey = new SubjectKey(course, curriculum, enrollmentKey.getSubjectCode());
//
//            Collection<String> terms = this.termsPerCurriculum.get(curriculumKey);
//            if (terms == null) terms = new TreeSet<>();
//            terms.add(enrollmentKey.getTerm());
//            this.termsPerCurriculum.put(curriculumKey, terms);
//
//            Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerSubjectPerTermPerClass =
//                    this.enrollmentsPerCurriculumPerSubjectPerTermPerClass.get(curriculumKey);
//            if (enrollmentsPerSubjectPerTermPerClass == null) {
//                enrollmentsPerSubjectPerTermPerClass = new HashMap<>();
//            }
//            this.enrollmentsPerSubjectPerTermPerClass.putAll(enrollmentsPerSubjectPerTermPerClass);
//
//            Map<String, Map<String, ClassEnrollments>> enrollmentsPerTermPerClass =
//                    enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
//            if (enrollmentsPerTermPerClass == null) {
//                enrollmentsPerTermPerClass = new HashMap<>();
//            }
//            Map<String, ClassEnrollments> enrollmentsPerClass = enrollmentsPerTermPerClass.get(enrollmentKey.getTerm());
//            if (enrollmentsPerClass == null) {
//                enrollmentsPerClass = new HashMap<>();
//            }
//            ClassEnrollments classEnrollments = enrollmentsPerClass.get(enrollmentData.getClassId());
//            if (classEnrollments == null) {
//                classEnrollments = new ClassEnrollments();
//            }
//            addEnrolment(classEnrollments, studentId, subjectKey, enrollmentData.getStatus());
//            enrollmentsPerClass.put(enrollmentData.getClassId(), classEnrollments);
//            enrollmentsPerTermPerClass.put(enrollmentKey.getTerm(), enrollmentsPerClass);
//            enrollmentsPerSubjectPerTermPerClass.put(subjectKey, enrollmentsPerTermPerClass);
//            this.enrollmentsPerCurriculumPerSubjectPerTermPerClass.put(curriculumKey, enrollmentsPerSubjectPerTermPerClass);
//
//            Map<String, Collection<String>> classesPerTerm = this.classesPerSubjectPerTerm.get(subjectKey);
//            if (classesPerTerm == null) {
//                classesPerTerm = new HashMap<>();
//            }
//            Collection<String> classes = classesPerTerm.get(enrollmentKey.getTerm());
//            if (classes == null) {
//                classes = new TreeSet<>();
//            }
//            classes.add(enrollmentData.getClassId());
//            classesPerTerm.put(enrollmentKey.getTerm(), classes);
//            this.classesPerSubjectPerTerm.put(subjectKey, classesPerTerm);
//            LOGGER.debug(String.format("inserting: (%s, %s, %s) %d", enrollmentKey.getSubjectCode(), enrollmentKey.getTerm(), enrollmentData.getClassId(), classes.size()));
//
//            if (!enrollmentData.getStatus().equals(SystemConstants.STATUS_ONGOING) &&
//                    !enrollmentData.getStatus().equals(SystemConstants.STATUS_CANCELLED)) {
//                Integer currentCount = attemptsSummary.get(new Registration(enrollmentKey.getRegistration()));
//                if (currentCount == null) {
//                    currentCount = enrollmentData.getCredits();
//                } else {
//                    currentCount += enrollmentData.getCredits();
//                }
//                attemptsSummary.put(new Registration(enrollmentKey.getRegistration()), currentCount);
//            }
//        });
//        attemptsSummary.forEach((registration, credits) -> {
//            NationalIdRegistrationKey key = registrationMap.get(registration.getRegistration());
//            StudentData student = getStudent(key);
//            if (student != null) {
//                student.setAttemptedCredits(credits);
//                updateStudent(key, student);
//            }
//        });
//    }

//    private void buildEnrollmentsPerAcademicUnit() {
//        this.enrollmentsPerAcademicUnitPerSubjectPerTermPerClass = new HashMap<>();
//        this.termsPerAcademicUnit = new HashMap<>();
//        this.classesPerSubjectPerTerm = new HashMap<>();
//        Map<Registration, Integer> attemptsSummary = new HashMap<>();
//
//        this.enrollmentsMap.forEach((enrollmentKey, enrollmentData) -> {
//            NationalIdRegistrationKey studentId = registrationMap.get(enrollmentKey.getRegistration());
//            StudentData studentData = this.studentsMap.get(studentId);
//            String course = studentData.getCourseCode();
//            String curriculum = studentData.getCurriculumCode();
//            AcademicUnitKey academicUnitKey = new AcademicUnitKey(enrollmentKey.getSubjectCode().substring(0, 4));
//            SubjectKey subjectKey = new SubjectKey(course, curriculum, enrollmentKey.getSubjectCode());
//
//            Collection<String> terms = this.termsPerAcademicUnit.get(academicUnitKey);
//            if (terms == null) terms = new TreeSet<>();
//            terms.add(enrollmentKey.getTerm());
//            this.termsPerAcademicUnit.put(academicUnitKey, terms);
//
//            Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerSubjectPerTermPerClass =
//                    this.enrollmentsPerAcademicUnitPerSubjectPerTermPerClass.get(academicUnitKey);
//            if (enrollmentsPerSubjectPerTermPerClass == null) {
//                enrollmentsPerSubjectPerTermPerClass = new HashMap<>();
//            }
//            Map<String, Map<String, ClassEnrollments>> enrollmentsPerTermPerClass =
//                    enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
//            if (enrollmentsPerTermPerClass == null) {
//                enrollmentsPerTermPerClass = new HashMap<>();
//            }
//            Map<String, ClassEnrollments> enrollementsPerClass = enrollmentsPerTermPerClass.get(enrollmentKey.getTerm());
//            if (enrollementsPerClass == null) {
//                enrollementsPerClass = new HashMap<>();
//            }
//            ClassEnrollments classEnrollments = enrollementsPerClass.get(enrollmentData.getClassId());
//            if (classEnrollments == null) {
//                classEnrollments = new ClassEnrollments();
//            }
//            addEnrolment(classEnrollments, studentId, subjectKey, enrollmentData.getStatus());
//            enrollementsPerClass.put(enrollmentData.getClassId(), classEnrollments);
//            enrollmentsPerTermPerClass.put(enrollmentKey.getTerm(), enrollementsPerClass);
//            enrollmentsPerSubjectPerTermPerClass.put(subjectKey, enrollmentsPerTermPerClass);
//            this.enrollmentsPerAcademicUnitPerSubjectPerTermPerClass.put(academicUnitKey, enrollmentsPerSubjectPerTermPerClass);
//
//            Map<String, Collection<String>> classesPerTerm = this.classesPerSubjectPerTerm.get(subjectKey);
//            if (classesPerTerm == null) {
//                classesPerTerm = new HashMap<>();
//            }
//            Collection<String> classes = classesPerTerm.get(enrollmentKey.getTerm());
//            if (classes == null) {
//                classes = new TreeSet<>();
//            }
//            classes.add(enrollmentData.getClassId());
//            classesPerTerm.put(enrollmentKey.getTerm(), classes);
//            this.classesPerSubjectPerTerm.put(subjectKey, classesPerTerm);
//            LOGGER.debug(String.format(Messages.INSERTING_ENROLLMENT_S_S_S_D, enrollmentKey.getSubjectCode(), enrollmentKey.getTerm(), enrollmentData.getClassId(), classes.size()));
//
//            if (!enrollmentData.getStatus().equals(SystemConstants.STATUS_ONGOING) &&
//                    !enrollmentData.getStatus().equals(SystemConstants.STATUS_CANCELLED)) {
//                Integer currentCount = attemptsSummary.get(new Registration(enrollmentKey.getRegistration()));
//                if (currentCount == null) {
//                    currentCount = enrollmentData.getCredits();
//                } else {
//                    currentCount += enrollmentData.getCredits();
//                }
//                attemptsSummary.put(new Registration(enrollmentKey.getRegistration()), currentCount);
//            }
//        });
//        attemptsSummary.forEach((registration, credits) -> {
//            NationalIdRegistrationKey key = registrationMap.get(registration.getRegistration());
//            StudentData student = getStudent(key);
//            if (student != null) {
//                student.setAttemptedCredits(credits);
//                updateStudent(key, student);
//            }
//        });
//    }

    private void buildSubjectIndexes() {
        this.subjectsMap.forEach((subjectKey, subjectData) -> {
            LOGGER.debug(String.format(Messages.PROCESSING_S_FOR_D_STUDENTS, subjectKey.getSubjectCode(), this.activesPerCourseMap.get(subjectKey.getCourseCode()).size()));
            this.activesPerCourseMap.get(subjectKey.getCourseCode()).forEach(studentKey -> {
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
            Collection<String> curriculumCodes = this.courseCurriculaMap.get(k.getCourse());
            if (curriculumCodes == null) {
                curriculumCodes = new TreeSet<>();
            }
            curriculumCodes.add(k.getCode());
            this.courseCurriculaMap.put(k.getCourse(), curriculumCodes);
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

    public Collection<Student> getAllActives(String courseCode) {
        Collection<NationalIdRegistrationKey> actives = this.activesPerCourseMap.get(courseCode);
        return getAllStudents(actives);
    }

    public Collection<Student> getAllAlumni(String courseCode) {
        Collection<NationalIdRegistrationKey> alumni = this.alumniPerCourseMap.get(courseCode);
        return getAllStudents(alumni);
    }

    public Collection<Student> getAllDropouts(String courseCode) {
        Collection<NationalIdRegistrationKey> dropouts = this.dropoutsPerCourseMap.get(courseCode);
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

//    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerClassPerTermPerSubjectPerAcademicUnit(String from, String to, String academicUnit) {
//        Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerClassPerTermPerSubject = this.getEnrollmentsPerSubjectPerTermPerClass(academicUnit);
//        Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> filteredEnrollments = new HashMap<>();
//
//        for (Map.Entry<SubjectKey, Map<String, Map<String, ClassEnrollments>>> entry : enrollmentsPerClassPerTermPerSubject.entrySet()) {
//            SubjectKey subjectKey = entry.getKey();
//            Map<String, Map<String, ClassEnrollments>> enrollmentsPerClassPerTerm = entry.getValue();
//            for (Map.Entry<String, Map<String, ClassEnrollments>> entry1 : enrollmentsPerClassPerTerm.entrySet()) {
//                String term = entry1.getKey();
//                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0)
//                    filteredEnrollments.put(subjectKey, enrollmentsPerClassPerTerm);
//            }
//        }
//
//        return filteredEnrollments;
//    }

    public int getRetentionCount(String courseCode, String curriculumCode, String subjectCode) {
        int retention = 0;
        for (NationalIdRegistrationKey active : this.activesPerCourseMap.get(courseCode)) {
            StudentCurriculumProgress studentCurriculum = this.studentCurriculumProgressMap.get(active);
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            if (studentCurriculum.getEnabled().contains(subjectKey)) {
                StudentData studentData = this.studentsMap.get(active);
                CurriculumKey curriculumKey = new CurriculumKey(studentData.getCourseCode(), studentData.getCurriculumCode());
                CurriculumData curriculumData = this.curriculumMap.get(curriculumKey);
                SubjectData subjectData = this.subjectsMap.get(subjectKey);
                if (isAdequate(studentCurriculum, curriculumData, subjectData.getIdealTerm())) {
                    retention++;
                }
            }
        }
        return retention;
    }

    public Collection<SubjectRetentionCSV> getRetention(String courseCode, String curriculumCode, String subjectCode) {
        Collection<SubjectRetentionCSV> responses = new TreeSet<>();
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        for (NationalIdRegistrationKey active : this.activesPerCourseMap.get(courseCode)) {
            StudentCurriculumProgress studentCurriculum = this.studentCurriculumProgressMap.get(active);
            StudentData student = studentsMap.get(active);
            if (!(student.getCourseCode().equals(courseCode) && student.getCurriculumCode().equals(curriculumCode))) continue;
            if (studentCurriculum.getEnabled().contains(subjectKey)) {
                SubjectData subject = this.subjectsMap.get(subjectKey);
                int idealTerm = subject.getIdealTerm();
                String name = subject.getName();
                SubjectRetentionCSV response = new SubjectRetentionCSV(courseCode, curriculumCode,
                        idealTerm, subjectCode, name, active.getRegistration(), student.getAttemptedCredits(),
                        student.getMandatoryCredits(), 0, student.getOptionalCredits(),
                        student.getComplementaryCredits(), student.getCompletedTerms(), student.getSuspendedTerms(),
                        student.getInstitutionalEnrollments(), student.getMobilityTerms(), student.getGpa());
                responses.add(response);
                LOGGER.debug(response.toString());
            }
        }
        return responses;
    }

//    public int getNumberOfClassesPerSubject(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
//        Map<String, Collection<String>> classesPerTerm = this.classesPerSubjectPerTerm.get(new SubjectKey(courseCode, curriculumCode, subjectCode));
//        int ret = 0;
//        if (classesPerTerm != null) {
//            for (String term : classesPerTerm.keySet()) {
//                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
//                    ret += classesPerTerm.get(term).size();
//                    LOGGER.debug(String.format(Messages.ADDIND_S_S_D, subjectCode, term, ret));
//                }
//            }
//        }
//        return ret;
//    }

    public Map<String, Map<String, ClassEnrollments>> getSubjectMetricsPerTerm(String courseCode, String curriculumCode, String from, String to, String subjectCode) {
        Map<String, Map<String, ClassEnrollments>> response = new HashMap<>();
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        Map<String, Map<String, ClassEnrollments>> classEnrollmentsPerTerm = this.enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
        if (classEnrollmentsPerTerm != null) {
            for (String term : classEnrollmentsPerTerm.keySet()) {
                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    response.put(term, classEnrollmentsPerTerm.get(term));
                }
            }
        }
        return response;
    }
}
