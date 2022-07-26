package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.curriculum.CurriculumNotFoundException;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniDigest;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.profile.ProfileResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsSummary;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.StudentClassification;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
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
    public Collection<Student> getAllStudentsPerStatus (StudentClassification status, String courseCode, String curriculumCode) throws EurecaException {
        return getAllStudentsByStatusPerCourse(status, courseCode, curriculumCode);
    }

    @Override
    public Collection<Student> getActives(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        return getFilteredStudents(StudentClassification.ACTIVE, courseCode, curriculumCode, from, to);
    }

    @Override
    public Collection<Student> getAllActives(String courseCode, String curriculumCode) throws EurecaException {
        return this.indexesHolder.getAllActives(courseCode, curriculumCode);
    }

    @Override
    public Collection<Student> getAlumni(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        return getFilteredStudents(StudentClassification.ALUMNUS, courseCode, curriculumCode, from, to);
    }

    @Override
    public Collection<Student> getDropouts(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        return getFilteredStudents(StudentClassification.DROPOUT, courseCode, curriculumCode, from, to);
    }

    @Override
    public Map<String, Collection<Student>> getActivesPerAdmissionTerm(String courseCode, String curriculumCode,
                                                        String from, String to) throws EurecaException {
        Map<String, Collection<NationalIdRegistrationKey>> index =
                indexesHolder.getActivesPerCoursePerAdmissionTerm(courseCode, curriculumCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Map<String, Collection<Student>> getAlumniPerGraduationTerm(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getAlumniPerGraduationTerm(courseCode, curriculumCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Map<String, Collection<Student>> getDropoutsPerDropoutTerm(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getDropoutsPerDropoutTerm(courseCode, curriculumCode);
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Collection<AlumniDigest> getAlumniPerStudentSummary(String courseCode, String from, String to) throws EurecaException {
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
    public Curriculum getCurriculum(String courseCode, String curriculumCode) throws EurecaException {
        return this.indexesHolder.getCurriculum(courseCode, curriculumCode);
    }

    @Override
    public Collection<String> getCurriculumCodes(String courseCode) throws EurecaException {
        return this.indexesHolder.getCurriculumCodes(courseCode);
    }

    @Override
    public ProfileResponse getProfile(String userId) throws EurecaException {
        Map<UserKey, ProfileData> profileMap = this.mapsHolder.getMap("profile");
        ProfileData profileData = profileMap.get(new UserKey(userId));
        if (profileData == null) {
            throw new InvalidParameterException(String.format(Messages.INVALID_USER_S, userId));
        }
        return new ProfileResponse(profileData.getCourseCode(), profileData.getCourseName());
    }

    @Override
    public Collection<SubjectMetricsPerTermSummary> getSubjectMetricsPerTermSummary(String courseCode,
                                                                                    String curriculumCode, String from, String to, SubjectType subjectType) throws EurecaException {
        Collection<String> subjectCodes = getSubjectsCode(courseCode, curriculumCode, subjectType);
        if (subjectCodes == null) {
            throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        Collection<SubjectMetricsPerTermSummary> subjectMetricsPerTerms = new TreeSet<>();
        for (String subjectCode : subjectCodes) {
            ArrayList<String> termsList = new ArrayList<>();
            Collection<SubjectMetricsPerTerm> response = new TreeSet<>();
            Map<String, Map<String, ClassEnrollments>> terms = this.indexesHolder.getSubjectMetricsPerTerm(courseCode, curriculumCode, subjectCode);
            for (String term : terms.keySet()) {
                if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                    Map<String, ClassEnrollments> classes = terms.get(term);
                    SubjectMetrics metrics = computeSubjectMetrics(classes.values());
                    SubjectMetricsPerTerm metricsPerTerm = new SubjectMetricsPerTerm(term, metrics);
                    response.add(metricsPerTerm);
                    termsList.add(term);
                }
            }
            int termsListSize = termsList.size();
            if (termsListSize > 0) {
                Collections.sort(termsList);
                String first = termsList.get(0);
                String last = termsList.get(termsListSize - 1);
                Subject subject = getSubject(courseCode, curriculumCode, subjectCode);
                subjectMetricsPerTerms.add(new SubjectMetricsPerTermSummary(first, last, subjectCode, subject.getName(), response));
            }
        }
        return subjectMetricsPerTerms;
    }

    @Override
    public SubjectsStatisticsSummaryResponse getSubjectStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Curriculum curriculum = null;
        curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new CurriculumNotFoundException(courseCode, curriculumCode);
        }
        SubjectsStatisticsSummary mandatory = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getMandatorySubjectsList());
        SubjectsStatisticsSummary optional = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getOptionalSubjectsList());
        SubjectsStatisticsSummary elective = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getElectiveSubjectsList());
        SubjectsStatisticsSummary complementary = buildSubjectSummary(courseCode, curriculumCode, from, to,
                curriculum.getComplementarySubjectsList());
        return new SubjectsStatisticsSummaryResponse(courseCode, curriculumCode,
                mandatory, optional, elective, complementary);
    }

    @Override
    public Subject getSubject(String courseCode, String curriculumCode, String subjectCode) throws InvalidParameterException {
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        return getSubject(subjectKey);
    }

    @Override
    public Collection<EnrollmentsPerSubjectData> getEnrollmentsPerSubjectPerTerm(String courseCode, String curriculumCode,
                                                                                 String from, String to, SubjectType subjectType) throws EurecaException {

        try {
            Collection<String> subjectCodes = getSubjectsCode(courseCode, curriculumCode, subjectType);
            Collection<EnrollmentsPerSubjectData> enrollmentsPerTerm = new TreeSet<>();
            for (String subjectCode : subjectCodes) {
                SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
                Subject subject = getSubject(subjectKey);
                Map<String, Map<String, ClassEnrollments>> enrollments =
                        this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass().get(subjectKey);
                Map<String, Map<String, ClassEnrollments>> filteredEnrollments = new HashMap();
                if (enrollments != null) {
                    enrollments.keySet().forEach(term -> {
                        if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                            Map<String, ClassEnrollments> classes = enrollments.get(term);
                            filteredEnrollments.put(term, classes);
                        }
                    });
                    enrollmentsPerTerm.add(new EnrollmentsPerSubjectData(subjectCode, subject.getName(), filteredEnrollments));
                }
            }
            return enrollmentsPerTerm;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public EnrollmentsStatisticsSummaryResponse getEnrollmentsStatisticsSummary(String courseCode, String curriculumCode,
                                                                                String from, String to) throws EurecaException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        EnrollmentsSummary mandatory = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getMandatorySubjectsList());
        EnrollmentsSummary optional = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getOptionalSubjectsList());
        EnrollmentsSummary elective = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getElectiveSubjectsList());
        EnrollmentsSummary complementary = buildEnrollmentSummary(courseCode, curriculumCode, from, to,
                curriculum.getComplementarySubjectsList());
        return new EnrollmentsStatisticsSummaryResponse(courseCode, curriculumCode,
                mandatory, optional, elective, complementary);
    }

    @Override
    public Collection<EnrollmentsMetricsPerTermSummary> getEnrollmentsPerTermSummary(String courseCode,
                                                                                     String curriculumCode, String from, String to, SubjectType subjectType) throws EurecaException {
        Collection<String> subjectCodes = getSubjectsCode(courseCode, curriculumCode, subjectType);
        if (subjectCodes == null) {
            throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        Collection<EnrollmentsMetricsPerTermSummary> enrollmentsMetricsPerTerms = new TreeSet<>();
        for (String subjectCode : subjectCodes) {
            Collection<EnrollmentsMetricsPerTerm> metricsPerTerms = new TreeSet<>();
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            Map<String, Map<String, ClassEnrollments>> terms = this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass().get(subjectKey);
            ArrayList<String> termsList = new ArrayList<>();
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
                        termsList.add(term);
                    }
                }
                int termsListSize = termsList.size();
                if (termsListSize > 0) {
                    Collections.sort(termsList);
                    String first = termsList.get(0);
                    String last = termsList.get(termsListSize - 1);
                    Subject subject = getSubject(courseCode, curriculumCode, subjectCode);
                    enrollmentsMetricsPerTerms.add(new EnrollmentsMetricsPerTermSummary(first, last, subjectCode,
                            subject.getName(), metricsPerTerms));
                }
            }
        }
        return enrollmentsMetricsPerTerms;
    }

    @Override
    public Collection<SubjectRetentionPerAdmissionTermSummary> getSubjectsRetentionSummary(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<SubjectRetentionPerAdmissionTermSummary> response = new TreeSet<>();
        Collection<String> subjectCodes = getSubjectsList(courseCode, curriculumCode);

        for(String subjectCode : subjectCodes) {
            Collection<SubjectRetentionPerAdmissionTerm> retentionPerTerm = this.indexesHolder.getRetentionCount(courseCode, curriculumCode, from, to, subjectCode);
            if (retentionPerTerm == null) {
                continue;
            }
            Subject subject = getSubject(courseCode, curriculumCode, subjectCode);
            ArrayList<String> termsList = getTermsList(retentionPerTerm);
            int termsListSize = termsList.size();
            if (termsListSize > 0) {
                String first = termsList.get(0);
                String last = termsList.get(termsListSize - 1);
                response.add(new SubjectRetentionPerAdmissionTermSummary(first, last, subjectCode, subject.getName(),
                        subject.getIdealTerm(), retentionPerTerm));
            }
        }
        return response;
    }

    @Override
    public Collection<SubjectRetentionCSV> getSubjectsRetention(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        Collection<SubjectRetentionCSV> response = new TreeSet<>();
        Collection<String> subjectCodes = getSubjectsList(courseCode, curriculumCode);

        for(String subjectCode : subjectCodes) {
            Collection<SubjectRetentionCSV> partialList = this.indexesHolder.getRetention(courseCode, curriculumCode, from, to, subjectCode);
            if (partialList == null) {
                continue;
            }
            response.addAll(partialList);
        }
        return response;
    }

    @Override
    public TeachersStatisticsResponse getTeachersPerTermSummary(String courseCode, String curriculumCode, String from, String to, String academicUnitId) throws EurecaException {
        AcademicUnitData auData = this.indexesHolder.getAuData(new AcademicUnitKey(academicUnitId));
        if (auData == null) {
            throw new InvalidParameterException(String.format(Messages.INVALID_ACADEMIC_UNIT_S, academicUnitId));
        }
        Collection<TeacherStatistics> teachers = this.indexesHolder.getTeachersPerTerm(academicUnitId, courseCode, curriculumCode, from, to);
        return new TeachersStatisticsResponse(academicUnitId, auData.getAcronym(), auData.getName(), courseCode,
                curriculumCode, from, to, teachers);
    }

    @Override
    public Map<String, TeachersStatisticsSummary> getTeachersPerAcademicUnit(String courseCode, String curriculumCode, String from, String to) throws EurecaException {
        return this.indexesHolder.getTeachersPerAcademicUnit(courseCode, curriculumCode, from, to);
    }

    @Override
    public StudentCurriculumProgress getStudentCurriculumProgress(String studentRegistration) throws EurecaException {
        return this.indexesHolder.getStudentCurriculumProgress(studentRegistration);
    }

    @Override
    public Map<SubjectScheduleKey, SubjectSchedule> getAllSchedules(String courseCode, String curriculumCode, String term) {
        Map<SubjectScheduleKey, Map<String, Schedule>> schedulesSeparatedByClassCode = this.indexesHolder.getAllSchedules(courseCode, curriculumCode, term);
        Map<SubjectScheduleKey, SubjectSchedule> allSchedules = new HashMap<>();

        for (Map.Entry<SubjectScheduleKey, Map<String, Schedule>> entry : schedulesSeparatedByClassCode.entrySet()) {
            SubjectScheduleKey key = entry.getKey();
            Map<String, Schedule> value = entry.getValue();

            try {
                Subject subject = this.getSubject(courseCode, curriculumCode, key.getSubjectCode());
                SubjectSchedule subjectSchedule = new SubjectSchedule(subject, value);
                allSchedules.put(key, subjectSchedule);
            } catch (InvalidParameterException e) {
                LOGGER.info(Messages.INVALID_SUBJECT_IGNORING);
            }
        }

        return allSchedules;
    }

    private Subject getSubject(SubjectKey subjectKey) throws InvalidParameterException {
        Map<SubjectKey, SubjectData> subjectMap = this.mapsHolder.getMap("subjects");
        SubjectData subjectData = subjectMap.get(subjectKey);
        if (subjectData == null) {
            throw new InvalidParameterException(Messages.INVALID_SUBJECT_IGNORING);
        }
        return subjectData.createSubject(subjectKey);
    }

    private Map<String, Collection<Student>> getStudentMapFromIndex(String from, String to,
                         Map<String, Collection<NationalIdRegistrationKey>> index) throws EurecaException {
        Map<NationalIdRegistrationKey, StudentData> studentsMap = mapsHolder.getMap("students");
        Map<String, Collection<Student>> termsMap = new HashMap<>();
        for (Map.Entry<String, Collection<NationalIdRegistrationKey>> entry : index.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Collection<Student> actives = new TreeSet<>();
                for (NationalIdRegistrationKey cpfRegistration : entry.getValue()) {
                    StudentData studentData = studentsMap.get(cpfRegistration);
                    Curriculum curriculum = null;
                    curriculum = getCurriculum(studentData.getCourseCode(), studentData.getCurriculumCode());
                    actives.add(studentData.createStudent(cpfRegistration, curriculum));
                }
                termsMap.put(term, actives);
            }
        }
        return termsMap;
    }

    private Collection<String> getSubjectsCode(String courseCode, String curriculumCode, SubjectType subjectType) throws EurecaException {
        Curriculum curriculum = null;
        curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, curriculumCode, courseCode));
        }
        Collection<String> subjectCodes = new HashSet<>();
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
            case ALL:
                subjectCodes.addAll(curriculum.getElectiveSubjectsList());
                subjectCodes.addAll(curriculum.getMandatorySubjectsList());
                subjectCodes.addAll(curriculum.getComplementarySubjectsList());
                subjectCodes.addAll(curriculum.getOptionalSubjectsList());
                break;
            default:
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
        String first = to;
        String last = from;
        for(String subjectCode : subjectCodes) {
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            Map<String, Map<String, ClassEnrollments>> enrollments = this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass().get(subjectKey);
            if (enrollments != null) {
                SubjectMetricsPerRange subjectMetricsPerRange = getSubjectMetricsStatistics(from, to, enrollments);
                if (subjectMetricsPerRange != null) {
                    if (subjectMetricsPerRange.getMetrics() != null) {
                        metricsPerSubject.add(subjectMetricsPerRange.getMetrics());
                    }
                    if (subjectMetricsPerRange.getFrom().compareTo(first) < 0) {
                        first = subjectMetricsPerRange.getFrom();
                    }
                    if (subjectMetricsPerRange.getTo().compareTo(last) > 0) {
                        last = subjectMetricsPerRange.getFrom();
                    }
                }
            }
        }
        SubjectMetricsStatistics metrics = computeSubjectMetricsStatistics(metricsPerSubject);
        return new SubjectsStatisticsSummary(first, last, subjectCodes.size(), metrics);
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
        int termsListSize = termsList.size();
        if (termsListSize > 0) {
            EnrollmentsStatisticsSummary summary = new EnrollmentsStatisticsSummary(
                    (double) subjectsCount / termsCount,
                    (double) classesCount / subjectsCount,
                    (double) classesCount / termsCount,
                    (double) enrollmentsCount / subjectsCount,
                    (double) enrollmentsCount / termsCount);
            Collections.sort(termsList);
            String first = termsList.get(0);
            String last = termsList.get(termsListSize - 1);
            return new EnrollmentsSummary(first, last, new TermCount(max, maxTerm), new TermCount(min, minTerm), summary);
        }
        return null;
    }

    private SubjectMetricsPerRange getSubjectMetricsStatistics(String from, String to, @NotNull Map<String, Map<String, ClassEnrollments>> enrollments) {
        SubjectMetrics subjectMetrics = new SubjectMetrics();
        ArrayList<String> termsList = new ArrayList<>();
        for (String term : enrollments.keySet()) {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Map<String, ClassEnrollments> classes = enrollments.get(term);
                SubjectMetrics otherSubjectMetrics = computeSubjectMetrics(classes.values());
                subjectMetrics.add(otherSubjectMetrics);
                termsList.add(term);
            }
        }
        int termsListSize = termsList.size();
        if (termsListSize > 0) {
            Collections.sort(termsList);
            String first = termsList.get(0);
            String last = termsList.get(termsListSize - 1);
            return new SubjectMetricsPerRange(first, last, subjectMetrics);
        } else {
            return null;
        }
    }

    private ArrayList<String> getTermsList(Collection<SubjectRetentionPerAdmissionTerm> retentionPerTerm) {
        ArrayList<String> termsList = new ArrayList<>();
        retentionPerTerm.forEach(term -> { termsList.add(term.getAdmissionTerm()); });
        return termsList;
    }

    private Collection<Student> getFilteredStudents(StudentClassification status, String courseCode,
                                   String curriculumCode, String from, String to) throws EurecaException {
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

    private Collection<String> getSubjectsList(String courseCode, String curriculumCode) throws EurecaException {
        Collection<String> subjectList;
        Curriculum curriculum = null;
        curriculum = this.getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        subjectList = curriculum.getMandatorySubjectsList();
        subjectList.addAll(curriculum.getComplementarySubjectsList());
        return subjectList;
    }

    @Override
    public Collection<Subject> getAllSubjects(String courseCode, String curriculumCode) throws EurecaException {
        Curriculum curriculum = null;
        curriculum = this.getCurriculum(courseCode, curriculumCode);
        Collection<String> subjectCodes = curriculum.getComplementarySubjectsList();
        subjectCodes.addAll(curriculum.getOptionalSubjectsList());
        subjectCodes.addAll(curriculum.getMandatorySubjectsList());
        subjectCodes.addAll(curriculum.getElectiveSubjectsList());

        return this.getSubjectsByCode(courseCode, curriculumCode, subjectCodes);
    }

    private Collection<Subject> getSubjectsByCode(String courseCode, String curriculumCode, Collection<String> subjectCodes) {
        Collection<Subject> subjects = new ArrayList<>();
        for (String subjectCode : subjectCodes) {
            try {
                Subject subject = this.getSubject(courseCode, curriculumCode, subjectCode);
                subjects.add(subject);
            } catch (InvalidParameterException e) {
                LOGGER.error(Messages.INVALID_SUBJECT_IGNORING);
            }
        }
        return subjects;
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

    private Collection<Student> getAllStudentsByStatusPerCourse(StudentClassification status, String courseCode, String curriculumCode) throws EurecaException {
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

    private class SubjectMetricsPerRange {
        private String from;
        private String to;
        private SubjectMetrics metrics;

        public SubjectMetricsPerRange(String from, String to, SubjectMetrics metrics) {
            this.from = from;
            this.to = to;
            this.metrics = metrics;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public SubjectMetrics getMetrics() {
            return metrics;
        }
    }
}
