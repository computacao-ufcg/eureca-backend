package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EnrollmentData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.NationalIdRegistrationKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.RegistrationCodeTermKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.ClassEnrollments;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import org.apache.log4j.Logger;

import java.util.*;

public class IndexesHolder {
    private Logger LOGGER = Logger.getLogger(IndexesHolder.class);

    private MapsHolder mapsHolder;
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
    private Map<String, Map<String, Map<String, Map<String, ClassEnrollments>>>> enrollmentsPerCurriculumPerSubjectPerTermPerClass;
    private Map<String, TreeSet<String>> termsPerCurriculum;
    private Map<String, Integer> numberOfClasses;

    public IndexesHolder(MapsHolder mapsHolder) {
        this.mapsHolder = mapsHolder;
        buildIndexes();
    }

    public Map<String, NationalIdRegistrationKey> getRegistrationMap() {
        return registrationMap;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getActiveByAdmissionTerm() {
        return activeByAdmissionTerm;
    }

    public List<NationalIdRegistrationKey> getAlumni() {
        return alumni;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getAlumniByAdmissionTerm() {
        return alumniByAdmissionTerm;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getAlumniByGraduationTerm() {
        return alumniByGraduationTerm;
    }

    public List<NationalIdRegistrationKey> getDropouts() {
        return dropouts;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutByAdmissionTerm() {
        return dropoutByAdmissionTerm;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutByDropoutTerm() {
        return dropoutByLeaveTerm;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutByReasonAndAdmissionTerm() {
        return dropoutByReasonAndAdmissionTerm;
    }

    public Map<String, Collection<NationalIdRegistrationKey>> getDropoutByReasonAndLeaveTerm() {
        return dropoutByReasonAndLeaveTerm;
    }

    private void buildIndexes() {
        buildStudentIndexes();
        buildEnrollmentIndexes();
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
        Map<NationalIdRegistrationKey, StudentData> mapStudents = this.mapsHolder.getMap("students");
        mapStudents.forEach((k, v) -> {
            this.registrationMap.put(k.getRegistration(), k);
            if (v.isActive()) {
                LOGGER.debug(String.format(Messages.INDEX_ACTIVE_S, v.getName()));
                this.actives.add(k);
                String admissionTerm = v.getAdmissionTerm();
                Collection<NationalIdRegistrationKey> list = this.activeByAdmissionTerm.get(admissionTerm);
                if (list == null) list = new ArrayList<>();
                list.add(k);
                this.activeByAdmissionTerm.put(admissionTerm, list);
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
                Collection <NationalIdRegistrationKey> listReasonAndAdmissionTerm = this.dropoutByReasonAndAdmissionTerm.get((reason+admissionTerm));
                if (listReasonAndAdmissionTerm == null) listReasonAndAdmissionTerm = new ArrayList<>();
                listReasonAndAdmissionTerm.add(k);
                this.dropoutByReasonAndAdmissionTerm.put((reason+admissionTerm), listReasonAndAdmissionTerm);
                Collection<NationalIdRegistrationKey> listReasonAndLeaveTerm = this.dropoutByReasonAndLeaveTerm.get((reason+leaveTerm));
                if (listReasonAndLeaveTerm == null) listReasonAndLeaveTerm = new ArrayList<>();
                listReasonAndLeaveTerm.add(k);
                this.dropoutByReasonAndLeaveTerm.put((reason+leaveTerm), listReasonAndLeaveTerm);
            }
        });
    }

    private void buildEnrollmentIndexes() {
        this.enrollmentsPerCurriculumPerSubjectPerTermPerClass = new HashMap<>();
        this.termsPerCurriculum = new HashMap<>();
        this.numberOfClasses = new HashMap<>();

        Map<NationalIdRegistrationKey, StudentData> studentsMap = this.mapsHolder.getMap("students");
        Map<RegistrationCodeTermKey, EnrollmentData> mapEnrollments = this.mapsHolder.getMap("enrollments");
        mapEnrollments.forEach((k, v) -> {
            NationalIdRegistrationKey key = registrationMap.get(k.getRegistration());
            String curriculum = studentsMap.get(key).getCurriculum();

            TreeSet<String> terms = this.termsPerCurriculum.get(curriculum);
            if (terms == null) terms = new TreeSet<>();
            terms.add(k.getTerm());
            this.termsPerCurriculum.put(curriculum, terms);

            Map<String, Map<String, Map<String, ClassEnrollments>>> enrollmentsPerSubjectPerTermPerClass =
                    this.enrollmentsPerCurriculumPerSubjectPerTermPerClass.get(curriculum);
            if (enrollmentsPerSubjectPerTermPerClass == null) {
                enrollmentsPerSubjectPerTermPerClass = new HashMap<>();
            }
            Map<String, Map<String, ClassEnrollments>> enrollmentsPerTermPerClass =
                    enrollmentsPerSubjectPerTermPerClass.get(k.getCode());
            if (enrollmentsPerTermPerClass == null) {
                enrollmentsPerTermPerClass = new HashMap<>();
            }
            Map<String, ClassEnrollments> enrollementsPerClass = enrollmentsPerTermPerClass.get(k.getTerm());
            if (enrollementsPerClass == null) {
                enrollementsPerClass = new HashMap<>();
            }
            ClassEnrollments classEnrollments = enrollementsPerClass.get(v.getClassId());
            if (classEnrollments == null) {
                classEnrollments = new ClassEnrollments();
                Integer classes = this.numberOfClasses.get(curriculum + ":" + k.getCode());
                if (classes == null) {
                    classes = new Integer(0);
                }
                classes++;
                this.numberOfClasses.put(curriculum + ":" + k.getCode(), classes);
            }
            addEnrolment(classEnrollments, k.getRegistration(), v.getStatus());
            enrollementsPerClass.put(v.getClassId(), classEnrollments);
            enrollmentsPerTermPerClass.put(k.getTerm(), enrollementsPerClass);
            enrollmentsPerSubjectPerTermPerClass.put(k.getCode(), enrollmentsPerTermPerClass);
            this.enrollmentsPerCurriculumPerSubjectPerTermPerClass.put(curriculum, enrollmentsPerSubjectPerTermPerClass);
        });
    }

    private void addEnrolment(ClassEnrollments classEnrollments, String registration, String status) {
        switch(status) {
            case SystemConstants.STATUS_SUCCEEDED:
                classEnrollments.getSuccess().add(registration);
                break;
            case SystemConstants.STATUS_EXEMPTED:
                classEnrollments.getExempted().add(registration);
                break;
            case SystemConstants.STATUS_ONGOING:
                classEnrollments.getOngoing().add(registration);
                break;
            case SystemConstants.STATUS_FAILED_DUE_GRADE:
                classEnrollments.getFailedDueToGrade().add(registration);
                break;
            case SystemConstants.STATUS_FAILED_DUE_ABSENCE:
                classEnrollments.getFailedDueToAbsence().add(registration);
                break;
            case SystemConstants.STATUS_SUSPENDED:
                classEnrollments.getSuspended().add(registration);
                break;
            default:
                classEnrollments.getCancelled().add(registration);
                break;
        }
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

    public Map<String, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerSubjectPerTermPerClass(String curriculum) {
        return enrollmentsPerCurriculumPerSubjectPerTermPerClass.get(curriculum);
    }

    public TreeSet<String> getTermsPerCurriculum(String curriculum) {
        return termsPerCurriculum.get(curriculum);
    }

    public int getNumberOfClassesPerSubject(String curriculum, String subject) {
        Integer ret = this.numberOfClasses.get(curriculum + ":" + subject);
        return (ret == null ? 0 : ret.intValue());
    }
}
