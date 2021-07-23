package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectRetentionResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EnviromentVariablesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class IndexesHolder {
    private final Logger LOGGER = Logger.getLogger(IndexesHolder.class);

    private final MapsHolder mapsHolder;
    // Map instances
    private final Map<NationalIdRegistrationKey, StudentData> studentsMap;
    private final Map<RegistrationCodeTermKey, EnrollmentData> enrollmentsMap;
    private final Map<SubjectKey, SubjectData> subjectsMap;
    private final Map<CurriculumKey, CurriculumData> curriculumMap;
    // Student indexes
    private Map<String, NationalIdRegistrationKey> registrationMap;
    private List<NationalIdRegistrationKey> actives;
    private Map<String, Collection<NationalIdRegistrationKey>> activeByAdmissionTerm;
    private List<NationalIdRegistrationKey> alumni;
    private Map<String, Collection<NationalIdRegistrationKey>> alumniByAdmissionTerm;
    private Map<String, Collection<NationalIdRegistrationKey>> alumniByGraduationTerm;
    private List<NationalIdRegistrationKey> dropouts;
    private Map<String, Collection<NationalIdRegistrationKey>> dropoutByAdmissionTerm;
    private Map<String, Collection<NationalIdRegistrationKey>> dropoutByLeaveTerm;
    private Map<String, Collection<NationalIdRegistrationKey>> dropoutByReasonAndAdmissionTerm;
    private Map<String, Collection<NationalIdRegistrationKey>> dropoutByReasonAndLeaveTerm;
    // Enrollment indexes
    private Map<CurriculumKey, Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>>> enrollmentsPerCurriculumPerSubjectPerTermPerClass;
    private Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerSubjectPerTermPerClass;
    private Map<CurriculumKey, TreeSet<String>> termsPerCurriculum;
    private Map<SubjectKey, Map<String, Collection<String>>> classesPerSubjectPerTerm;
    // Subject indexes
    private Map<NationalIdRegistrationKey, StudentCurriculum> studentCurriculumMap;
    // Curricula indexes
    private Map<String, Collection<String>> courseCurriculaMap;

    public IndexesHolder(MapsHolder mapsHolder) {
        this.mapsHolder = mapsHolder;
        this.studentsMap = this.mapsHolder.getMap("students");
        this.enrollmentsMap = this.mapsHolder.getMap("enrollments");
        this.subjectsMap = this.mapsHolder.getMap("subjects");
        this.curriculumMap = this.mapsHolder.getMap("curriculum");
        buildIndexes();
    }

    public Map<String, NationalIdRegistrationKey> getRegistrationMap() {
        return registrationMap;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getActivesPerAdmissionTerm() {
        return activeByAdmissionTerm;
    }

    public List<NationalIdRegistrationKey> getAlumni() {
        return alumni;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getAlumniByAdmissionTerm() {
        return alumniByAdmissionTerm;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getAlumniPerGraduationTerm() {
        return alumniByGraduationTerm;
    }

    public List<NationalIdRegistrationKey> getDropouts() {
        return dropouts;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutByAdmissionTerm() {
        return dropoutByAdmissionTerm;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutsPerDropoutTerm() {
        return dropoutByLeaveTerm;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutByReasonAndAdmissionTerm() {
        return dropoutByReasonAndAdmissionTerm;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutByReasonAndLeaveTerm() {
        return dropoutByReasonAndLeaveTerm;
    }

    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerSubjectPerTermPerClass() {
        return enrollmentsPerSubjectPerTermPerClass;
    }

    private void buildIndexes() {
        buildStudentIndexes();
        buildEnrollmentIndexes();
        buildSubjectIndexes();
        buildCurriculaIndexes();
    }

    private void buildStudentIndexes() {
        this.registrationMap = new HashMap<>();
        this.actives = new ArrayList<>();
        this.activeByAdmissionTerm = new HashMap<>();
        this.alumni = new ArrayList<>();
        this.alumniByAdmissionTerm = new HashMap<>();
        this.alumniByGraduationTerm = new HashMap<>();
        this.dropouts = new ArrayList<>();
        this.dropoutByAdmissionTerm = new HashMap<>();
        this.dropoutByLeaveTerm = new HashMap<>();
        this.dropoutByReasonAndAdmissionTerm = new HashMap<>();
        this.dropoutByReasonAndLeaveTerm = new HashMap<>();
        this.studentCurriculumMap = new HashMap<>();

        this.studentsMap.forEach((k, v) -> {
            this.registrationMap.put(k.getRegistration(), k);
            if (v.isActive()) {
                LOGGER.debug(String.format(Messages.INDEX_ACTIVE_S, v.getName()));
                this.actives.add(k);
                String admissionTerm = v.getAdmissionTerm();
                Collection<NationalIdRegistrationKey> list = this.activeByAdmissionTerm.get(admissionTerm);
                if (list == null) list = new ArrayList<>();
                list.add(k);
                this.activeByAdmissionTerm.put(admissionTerm, list);
                this.studentCurriculumMap.put(k, new StudentCurriculum((v.getCompletedTerms() + 1),
                        (v.getMandatoryCredits() + v.getComplementaryCredits())));
            }
            if (v.isAlumnus()) { // graduated
                LOGGER.debug(String.format(Messages.INDEX_ALUMNUS_S, v.getName()));
                this.alumni.add(k);
                String admissionTerm = v.getAdmissionTerm();
                String graduationTerm = v.getStatusTerm();
                Collection<NationalIdRegistrationKey> listAdmissionTerm = this.alumniByAdmissionTerm.get(admissionTerm);
                if (listAdmissionTerm == null) listAdmissionTerm = new ArrayList<>();
                listAdmissionTerm.add(k);
                this.alumniByAdmissionTerm.put(admissionTerm, listAdmissionTerm);
                Collection<NationalIdRegistrationKey> listGraduationTerm = this.alumniByGraduationTerm.get(graduationTerm);
                if (listGraduationTerm == null) listGraduationTerm = new ArrayList<>();
                listGraduationTerm.add(k);
                this.alumniByGraduationTerm.put(graduationTerm, listGraduationTerm);
            }
            if (v.isDropout()) { // dropout
                LOGGER.debug(String.format(Messages.INDEX_DROPOUT_S, v.getName()));
                this.dropouts.add(k);
                String admissionTerm = v.getAdmissionTerm();
                String leaveTerm = v.getStatusTerm();
                String reason = v.getStatusStr();
                Collection<NationalIdRegistrationKey> listAdmissionTerm = this.dropoutByAdmissionTerm.get(admissionTerm);
                if (listAdmissionTerm == null) listAdmissionTerm = new ArrayList<>();
                listAdmissionTerm.add(k);
                this.dropoutByAdmissionTerm.put(admissionTerm, listAdmissionTerm);
                Collection<NationalIdRegistrationKey> listLeaveTerm = this.dropoutByLeaveTerm.get(leaveTerm);
                if (listLeaveTerm == null) listLeaveTerm = new ArrayList<>();
                listLeaveTerm.add(k);
                this.dropoutByLeaveTerm.put(leaveTerm, listLeaveTerm);
                Collection<NationalIdRegistrationKey> listReasonAndAdmissionTerm = this.dropoutByReasonAndAdmissionTerm.get((reason + admissionTerm));
                if (listReasonAndAdmissionTerm == null) listReasonAndAdmissionTerm = new ArrayList<>();
                listReasonAndAdmissionTerm.add(k);
                this.dropoutByReasonAndAdmissionTerm.put((reason + admissionTerm), listReasonAndAdmissionTerm);
                Collection<NationalIdRegistrationKey> listReasonAndLeaveTerm = this.dropoutByReasonAndLeaveTerm.get((reason + leaveTerm));
                if (listReasonAndLeaveTerm == null) listReasonAndLeaveTerm = new ArrayList<>();
                listReasonAndLeaveTerm.add(k);
                this.dropoutByReasonAndLeaveTerm.put((reason + leaveTerm), listReasonAndLeaveTerm);
            }
        });
    }

    private void buildEnrollmentIndexes() {
        this.enrollmentsPerCurriculumPerSubjectPerTermPerClass = new HashMap<>();
        this.enrollmentsPerSubjectPerTermPerClass = new HashMap<>();
        this.termsPerCurriculum = new HashMap<>();
        this.classesPerSubjectPerTerm = new HashMap<>();
        Map<Registration, Integer> attemptsSummary = new HashMap<>();
        String courseCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCourse();
        String curriculumCode = EnviromentVariablesHolder.getInstance().getEnvironmentVariables().getCurrentCurriculum();

        this.enrollmentsMap.forEach((enrollmentKey, enrollmentData) -> {
            NationalIdRegistrationKey studentId = registrationMap.get(enrollmentKey.getRegistration());
            StudentData studentData = this.studentsMap.get(studentId);
            String course = studentData.getCourse();
            String curriculum = studentData.getCurriculum();
            CurriculumKey curriculumKey = new CurriculumKey(course, curriculum);
            SubjectKey subjectKey = new SubjectKey(course, curriculum, enrollmentKey.getCode());

            TreeSet<String> terms = this.termsPerCurriculum.get(curriculumKey);
            if (terms == null) terms = new TreeSet<>();
            terms.add(enrollmentKey.getTerm());
            this.termsPerCurriculum.put(curriculumKey, terms);

            Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerSubjectPerTermPerClass =
                    this.enrollmentsPerCurriculumPerSubjectPerTermPerClass.get(curriculumKey);
            if (enrollmentsPerSubjectPerTermPerClass == null) {
                enrollmentsPerSubjectPerTermPerClass = new HashMap<>();
            }
            if (courseCode.equals(course) && curriculumCode.equals(curriculum)) {
                this.enrollmentsPerSubjectPerTermPerClass.putAll(enrollmentsPerSubjectPerTermPerClass);
            }
            Map<String, Map<String, ClassEnrollments>> enrollmentsPerTermPerClass =
                    enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
            if (enrollmentsPerTermPerClass == null) {
                enrollmentsPerTermPerClass = new HashMap<>();
            }
            Map<String, ClassEnrollments> enrollmentsPerClass = enrollmentsPerTermPerClass.get(enrollmentKey.getTerm());
            if (enrollmentsPerClass == null) {
                enrollmentsPerClass = new HashMap<>();
            }
            ClassEnrollments classEnrollments = enrollmentsPerClass.get(enrollmentData.getClassId());
            if (classEnrollments == null) {
                classEnrollments = new ClassEnrollments();
            }
            addEnrolment(classEnrollments, studentId, subjectKey, enrollmentData.getStatus());
            enrollmentsPerClass.put(enrollmentData.getClassId(), classEnrollments);
            enrollmentsPerTermPerClass.put(enrollmentKey.getTerm(), enrollmentsPerClass);
            enrollmentsPerSubjectPerTermPerClass.put(subjectKey, enrollmentsPerTermPerClass);
            this.enrollmentsPerCurriculumPerSubjectPerTermPerClass.put(curriculumKey, enrollmentsPerSubjectPerTermPerClass);

            Map<String, Collection<String>> classesPerTerm = this.classesPerSubjectPerTerm.get(subjectKey);
            if (classesPerTerm == null) {
                classesPerTerm = new HashMap<>();
            }
            Collection<String> classes = classesPerTerm.get(enrollmentKey.getTerm());
            if (classes == null) {
                classes = new TreeSet<>();
            }
            classes.add(enrollmentData.getClassId());
            classesPerTerm.put(enrollmentKey.getTerm(), classes);
            this.classesPerSubjectPerTerm.put(subjectKey, classesPerTerm);
            LOGGER.debug(String.format("inserting: (%s, %s, %s) %d", enrollmentKey.getCode(), enrollmentKey.getTerm(), enrollmentData.getClassId(), classes.size()));

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
            this.actives.forEach(studentKey -> {
                StudentCurriculum studentCurriculum = this.studentCurriculumMap.get(studentKey);
                boolean completed = hasCompleted(studentCurriculum, subjectKey, subjectData);
                boolean disabled = studentCurriculum.getDisabled().contains(subjectKey);
                if (!completed && !disabled) {
                    if (isEnabled(studentCurriculum, subjectKey, subjectData)) {
                        studentCurriculum.getEnabled().add(subjectKey);
                        CurriculumData curriculumData = this.curriculumMap.get(new
                                CurriculumKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode()));
                        if (isAdequate(studentCurriculum, curriculumData, subjectData.getIdealTerm())) {
                            studentCurriculum.getAdequate().add(subjectKey);
                        }
                    } else {
                        studentCurriculum.getDisabled().add(subjectKey);
                        LOGGER.debug(String.format(Messages.STUDENT_S_DISABLED_FOR_SUBJECT_S,
                                studentKey.getRegistration(), subjectKey.getSubjectCode()));
                    }
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

    private boolean hasCompleted(StudentCurriculum curriculum, SubjectKey subjectKey, SubjectData subjectData) {
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

    private boolean isEnabled(StudentCurriculum curriculum, SubjectKey subjectKey, SubjectData subjectData) {
        for (String subjectCode : subjectData.getPreRequirementsList()) {
            SubjectKey preRequirementKey = new SubjectKey(subjectKey.getCourseCode(), subjectKey.getCurriculumCode(),
                    subjectCode);
            SubjectData preRequirementData = this.subjectsMap.get(preRequirementKey);
            if (!hasCompleted(curriculum, preRequirementKey, preRequirementData)) return false;
        }
        return true;
    }

    private boolean isAdequate(StudentCurriculum studentCurriculum, CurriculumData curriculumData, int subjectIdealTerm) {
        int accumulatedCredits = studentCurriculum.getAccumulatedCredits();
        int expectedAccumulatedCredits = curriculumData.getExpectedMinAccumulatedCreditsList(subjectIdealTerm);
        return accumulatedCredits >= expectedAccumulatedCredits;
    }

    private void addEnrolment(ClassEnrollments classEnrollments, NationalIdRegistrationKey studentId,
                              SubjectKey subjectKey, String status) {
        StudentCurriculum curriculum;
        switch(status) {
            case SystemConstants.STATUS_SUCCEEDED:
                classEnrollments.getSuccess().add(studentId.getRegistration());
                if ((curriculum = retrieveCurriculum(studentId)) != null) curriculum.getCompleted().add(subjectKey);
                break;
            case SystemConstants.STATUS_EXEMPTED:
                classEnrollments.getExempted().add(studentId.getRegistration());
                if ((curriculum = retrieveCurriculum(studentId)) != null) curriculum.getCompleted().add(subjectKey);
                break;
            case SystemConstants.STATUS_ONGOING:
                classEnrollments.getOngoing().add(studentId.getRegistration());
                if ((curriculum = retrieveCurriculum(studentId)) != null) curriculum.getDisabled().add(subjectKey);
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

    private StudentCurriculum retrieveCurriculum(NationalIdRegistrationKey studentId) {
        StudentCurriculum curriculum = null;
        StudentData student = this.studentsMap.get(studentId);
        if (student.isActive()) {
            curriculum = this.studentCurriculumMap.get(studentId);
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

    public Collection<Student> getAllActives() {
        Collection<Student> allActives = new ArrayList<>();
        Map<NationalIdRegistrationKey, StudentData> mapStudents = this.mapsHolder.getMap("students");
        this.actives.forEach(k -> {
            allActives.add(mapStudents.get(k).createStudent(k));
        });
        return allActives;
    }

    public Collection<Student> getAllAlumni() {
        Collection<Student> allAlumni = new ArrayList<>();
        Map<NationalIdRegistrationKey, StudentData> mapStudents = this.mapsHolder.getMap("students");
        this.alumni.forEach(k -> {
            allAlumni.add(mapStudents.get(k).createStudent(k));
        });
        return allAlumni;
    }

    public Collection<Student> getAllDropouts() {
        Collection<Student> allDropouts = new ArrayList<>();
        Map<NationalIdRegistrationKey, StudentData> mapStudents = this.mapsHolder.getMap("students");
        this.dropouts.forEach(k -> {
            allDropouts.add(mapStudents.get(k).createStudent(k));
        });
        return allDropouts;
    }

    public Collection<Enrollment> getAllEnrollments() {
        Collection<Enrollment> enrollments = new ArrayList<>();
        for (Map.Entry<RegistrationCodeTermKey, EnrollmentData> entry : this.enrollmentsMap.entrySet()) {
            RegistrationCodeTermKey key = entry.getKey();
            EnrollmentData data = entry.getValue();
            Enrollment enrollment = data.createEnrollment(key);
            enrollments.add(enrollment);
        }
        return enrollments;
    }

    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerSubjectPerTermPerClass(String from, String to) {
        Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> filteredEnrollments = new HashMap<>();
        for (Map.Entry<SubjectKey, Map<String, Map<String, ClassEnrollments>>> entry : this.enrollmentsPerSubjectPerTermPerClass.entrySet()) {
            SubjectKey subjectKey = entry.getKey();
            Map<String, Map<String, ClassEnrollments>> enrollmentsPerTerm = entry.getValue();

            for (Map.Entry<String, Map<String, ClassEnrollments>> entry2 : enrollmentsPerTerm.entrySet()) {
                String term = entry2.getKey();

                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    filteredEnrollments.put(subjectKey, enrollmentsPerTerm);
                }
            }
        }
        return filteredEnrollments;
    }

    public int getRetentionCount(String courseCode, String curriculumCode, String subjectCode) {
        int retention = 0;
        for (NationalIdRegistrationKey active : this.actives) {
            StudentCurriculum studentCurriculum = this.studentCurriculumMap.get(active);
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            if (studentCurriculum.getEnabled().contains(subjectKey)) {
                StudentData studentData = this.studentsMap.get(active);
                CurriculumKey curriculumKey = new CurriculumKey(studentData.getCourse(), studentData.getCurriculum());
                CurriculumData curriculumData = this.curriculumMap.get(curriculumKey);
                SubjectData subjectData = this.subjectsMap.get(subjectKey);
                if (isAdequate(studentCurriculum, curriculumData, subjectData.getIdealTerm())) {
                    retention++;
                }
            }
        }
        return retention;
    }

    public Collection<SubjectRetentionResponse> getRetention(String courseCode, String curriculumCode, String subjectCode) {
        Collection<SubjectRetentionResponse> responses = new TreeSet<>();
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        for (NationalIdRegistrationKey active : this.actives) {
            StudentCurriculum studentCurriculum = this.studentCurriculumMap.get(active);
            StudentData student = studentsMap.get(active);
            if (!(student.getCourse().equals(courseCode) && student.getCurriculum().equals(curriculumCode))) continue;
            if (studentCurriculum.getEnabled().contains(subjectKey)) {
                SubjectData subject = this.subjectsMap.get(subjectKey);
                int idealTerm = subject.getIdealTerm();
                String name = subject.getName();
                SubjectRetentionResponse response = new SubjectRetentionResponse(courseCode, curriculumCode,
                        idealTerm, subjectCode, name, active.getRegistration(), student.getAttemptedCredits(),
                        student.getMandatoryCredits(), 0, student.getElectiveCredits(),
                        student.getComplementaryCredits(), student.getCompletedTerms(), student.getSuspendedTerms(),
                        student.getInstitutionalTerms(), student.getMobilityTerms(), student.getGpa());
                responses.add(response);
                LOGGER.info(response.toString());
            }
        }
        return responses;
    }

    public TreeSet<String> getTermsPerCurriculum(String courseCode, String curriculumCode) {
        return termsPerCurriculum.get(new CurriculumKey(courseCode, curriculumCode));
    }

    public int getNumberOfClassesPerSubject(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        Map<String, Collection<String>> classesPerTerm = this.classesPerSubjectPerTerm.get(new SubjectKey(courseCode, curriculumCode, subjectCode));
        int ret = 0;
        if (classesPerTerm != null) {
            for (String term : classesPerTerm.keySet()) {
                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    ret += classesPerTerm.get(term).size();
                    LOGGER.debug(String.format("adding: (%s, %s) %d", subjectCode, term, ret));
                }
            }
        }
        return ret;
    }

    public Map<String, Map<String, ClassEnrollments>> getSubjectMetricsPerTerm(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        Map<String, Map<String, ClassEnrollments>> response = new HashMap<>();
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        Map<String, Map<String, ClassEnrollments>> classEnrollmentsPerTerm = this.enrollmentsPerSubjectPerTermPerClass.get(subjectKey);
        for(String term: classEnrollmentsPerTerm.keySet()) {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                response.put(term, classEnrollmentsPerTerm.get(term));
            }
        }
        return response;
    }
}
