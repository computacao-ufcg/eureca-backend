package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniDigest;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.DetailedSubjectDemand;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.PreEnrollmentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.StudentPreEnrollmentResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.profile.ProfileResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsSummary;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.StudentClassification;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.EurecaUtil;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
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
    public Collection<Student> getActives(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        return getFilteredStudents(StudentClassification.ACTIVE, courseCode, curriculumCode, from, to);
    }

    @Override
    public Collection<Student> getAllActives(String courseCode, String curriculumCode) throws InvalidParameterException {
        return this.indexesHolder.getAllActives(courseCode, curriculumCode);
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

    private Collection<String> getConcludedSubjectsCode(StudentCurriculumProgress progress) {
        Collection<SubjectKey> concludedSubjects = progress.getCompleted();
        // If the student is currently registered, we take the optimistic approach and consider that he/she
        // will complete the ongoing subjects successfully
        concludedSubjects.addAll(progress.getOngoing());
        return this.mapSubjectsToCodes(concludedSubjects);
    }

    private Collection<String> mapSubjectsToCodes(Collection<SubjectKey> subjects) {
        return subjects.stream()
                .map(SubjectKey::getSubjectCode)
                .collect(Collectors.toSet());
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
        Collection<String> subjectCodes = getSubjectsCode(courseCode, curriculumCode, subjectType);
        if (subjectCodes == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
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
                mandatory, optional, elective, complementary);
        return ret;
    }

    @Override
    public Subject getSubject(String courseCode, String curriculumCode, String subjectCode) throws InvalidParameterException {
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        return getSubject(subjectKey);
    }

    @Override
    public Collection<EnrollmentsPerSubjectData> getEnrollmentsPerSubjectPerTerm(String courseCode, String curriculumCode,
                                                                                 String from, String to, SubjectType subjectType) throws InvalidParameterException {

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
            e.printStackTrace();
            throw e;
        }
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
        Collection<String> subjectCodes = getSubjectsCode(courseCode, curriculumCode, subjectType);
        if (subjectCodes == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
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
    public Collection<SubjectRetentionPerAdmissionTermSummary> getSubjectsRetentionSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Collection<SubjectRetentionPerAdmissionTermSummary> response = new TreeSet<>();
        Collection<String> subjectCodes = getSubjectsList(courseCode, curriculumCode);

        for(String subjectCode : subjectCodes) {
            Collection<SubjectRetentionPerAdmissionTerm> retentionPerTerm = this.indexesHolder.getRetentionCount(courseCode, curriculumCode, from, to, subjectCode);
            if (retentionPerTerm == null) continue;
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

    @Override
    public StudentCurriculumProgress getStudentCurriculumProgress(String studentRegistration) throws InvalidParameterException {
        return this.indexesHolder.getStudentCurriculumProgress(studentRegistration);
    }

    private Collection<Subject> getPriorityList(String courseCode, String curriculumCode, String priorityListString) {
        Collection<Subject> priorityList = new ArrayList<>();
        if (priorityListString != null) {
            Collection<String> priorityListCodes = Arrays.asList(priorityListString.split(","));
            priorityList = this.getSubjectsByCode(courseCode, curriculumCode, priorityListCodes);
            priorityList = priorityList.stream().sorted(Comparator.comparingInt(Subject::getIdealTerm)).collect(Collectors.toList());
        }
        return priorityList;
    }

    @Override
    public StudentPreEnrollmentResponse getStudentPreEnrollment(PreEnrollmentData preEnrollmentData) throws InvalidParameterException {
        String studentRegistration = preEnrollmentData.getStudentRegistration();
        int nextTerm = preEnrollmentData.getNextTerm();
        int idealMandatoryCredits = preEnrollmentData.getIdealMandatoryCredits();
        int idealOptionalCredits = preEnrollmentData.getIdealOptionalCredits();
        int idealComplementaryCredits = preEnrollmentData.getIdealComplementaryCredits();
        int idealElectiveCredits = preEnrollmentData.getIdealElectiveCredits();

        Collection<Subject> availableMandatorySubjects = preEnrollmentData.getAvailableMandatorySubjects();
        Collection<Subject> availableOptionalSubjects = preEnrollmentData.getAvailableOptionalSubjects();
        Collection<Subject> availableComplementarySubjects = preEnrollmentData.getAvailableComplementarySubjects();
        Collection<Subject> availableElectiveSubjects = preEnrollmentData.getAvailableElectiveSubjects();

        Collection<Subject> prioritizedOptionalSubjects = preEnrollmentData.getPrioritizedOptionalSubjects();
        Collection<Subject> prioritizedElectiveSubjects = preEnrollmentData.getPrioritizedElectiveSubjects();
        Collection<Subject> prioritizedMandatorySubjects = preEnrollmentData.getPrioritizedMandatorySubjects();

        StudentPreEnrollmentResponse studentPreEnrollment = new StudentPreEnrollmentResponse(studentRegistration, nextTerm, idealMandatoryCredits, idealOptionalCredits, idealComplementaryCredits, idealElectiveCredits);

        prioritizedOptionalSubjects = this.excludeUnavailableSubjects(prioritizedOptionalSubjects, availableOptionalSubjects);
        prioritizedElectiveSubjects = this.excludeUnavailableSubjects(prioritizedElectiveSubjects, availableElectiveSubjects);
        prioritizedMandatorySubjects = this.excludeUnavailableSubjects(prioritizedMandatorySubjects, availableMandatorySubjects);

        this.enrollMandatorySubjectsUntilConflict(studentPreEnrollment, availableMandatorySubjects);

        if (!studentPreEnrollment.isMandatoryFull()) {
            this.addSubjectsToPreEnrollment(studentPreEnrollment, prioritizedMandatorySubjects);
        }

        if (!studentPreEnrollment.isMandatoryFull()) {
            Collection<Subject> mandatoryLeftovers = EurecaUtil.difference(availableMandatorySubjects, prioritizedMandatorySubjects);
            this.addSubjectsToPreEnrollment(studentPreEnrollment, mandatoryLeftovers);
        }

        if (!studentPreEnrollment.isComplementaryFull()) {
            this.addSubjectsToPreEnrollment(studentPreEnrollment, availableComplementarySubjects);
        }

        if (!studentPreEnrollment.isOptionalFull()) {
            this.addSubjectsToPreEnrollment(studentPreEnrollment, prioritizedOptionalSubjects);
            this.addSubjectsToPreEnrollment(studentPreEnrollment, availableOptionalSubjects);
        }

        if (!studentPreEnrollment.isElectiveFull()) {
            this.addSubjectsToPreEnrollment(studentPreEnrollment, prioritizedElectiveSubjects);
            this.addSubjectsToPreEnrollment(studentPreEnrollment, availableElectiveSubjects);
        }

        return studentPreEnrollment;
    }

    private Collection<Subject> excludeUnavailableSubjects(Collection<Subject> prioritizedSubjects, Collection<Subject> availableSubjects) {
        return EurecaUtil.intersection(prioritizedSubjects, availableSubjects);
    }

    private void enrollMandatorySubjectsUntilConflict(StudentPreEnrollmentResponse studentPreEnrollment, Collection<Subject> availableMandatorySubjects) {
        Map<Integer, Collection<Subject>> mandatorySubjectsGroupedByTerm = this.getMandatorySubjectsGroupedByTermAndType(availableMandatorySubjects);
        for (Integer term : mandatorySubjectsGroupedByTerm.keySet()) {
            Collection<Subject> termSubjects = mandatorySubjectsGroupedByTerm.get(term);
            int totalTermCredits = this.getSubjectCreditsSum(termSubjects);

            if (totalTermCredits > studentPreEnrollment.getMaxMandatoryCredits() - studentPreEnrollment.getMandatoryCredits()) break;

            this.addSubjectsToPreEnrollment(studentPreEnrollment, termSubjects);
        }
    }

    private int getSubjectCreditsSum(Collection<Subject> subjects) {
        return subjects.stream().map(Subject::getCredits).reduce(0, Integer::sum);
    }

    private void addSubjectsToPreEnrollment(StudentPreEnrollmentResponse studentPreEnrollment, Collection<Subject> availableSubjects) {
        for (Subject s : availableSubjects) {
            if (s.isComposed()) {
                Collection<Subject> coRequirements = this.getSubjectsByCode(s.getCourseCode(), s.getCurriculumCode(), s.getCoRequirementsList());
                studentPreEnrollment.addSubject(s, coRequirements);
            } else {
                studentPreEnrollment.addSubject(s);
            }
        }
    }

    private Collection<Subject> getSubjectsByCode(String courseCode, String curriculumCode, Collection<String> subjectCodes) {
        Collection<Subject> subjects = new ArrayList<>();
        for (String subjectCode : subjectCodes) {
            try {
                Subject subject = this.getSubject(courseCode, curriculumCode, subjectCode);
                subjects.add(subject);
            } catch (InvalidParameterException e) {
                LOGGER.error(Messages.INVALID_SUBJECT);
            }
        }
        return subjects;
    }

    private Map<Integer, Map<SubjectType, Collection<Subject>>> getSubjectsGroupedByTerm(Collection<Subject> subjects) {
        Map<Integer, Map<SubjectType, Collection<Subject>>> groupedSubjects = new TreeMap<>();

        for (Subject subject : subjects) {
            int term = subject.getIdealTerm();
            if (!groupedSubjects.containsKey(term)) {
                groupedSubjects.put(term, new HashMap<>());
            }

            Map<SubjectType, Collection<Subject>> subjectsByTerm = groupedSubjects.get(term);
            SubjectType subjectType = EurecaUtil.getSubjectType(subject);
            if (!subjectsByTerm.containsKey(subjectType)) {
                subjectsByTerm.put(subjectType, new ArrayList<>());
            }

            subjectsByTerm.get(subjectType).add(subject);
            groupedSubjects.put(term, subjectsByTerm);
        }

        return groupedSubjects;
    }

    private Map<Integer, Collection<Subject>> getMandatorySubjectsGroupedByTermAndType(Collection<Subject> subjects) {
        return this.getSubjectsGroupedByTermAndType(subjects, SubjectType.MANDATORY);
    }

    private Map<Integer, Collection<Subject>> getSubjectsGroupedByTermAndType(Collection<Subject> subjects, SubjectType type) {
        Map<Integer, Map<SubjectType, Collection<Subject>>> groupedSubjects = this.getSubjectsGroupedByTerm(subjects);
        Map<Integer, Collection<Subject>> groupedSubjectsByType = new TreeMap<>();
        for (Integer term : groupedSubjects.keySet()) {
            Map<SubjectType, Collection<Subject>> termSubjects = groupedSubjects.get(term);
            Collection<Subject> typeSubjects = termSubjects.get(type);
            if (typeSubjects != null) {
                groupedSubjectsByType.put(term, typeSubjects);
            }
        }
        return groupedSubjectsByType;
    }

    @Override
    public PreEnrollmentsResponse getActivesPreEnrollment(Collection<PreEnrollmentData> activesPreEnrollmentData) throws InvalidParameterException {
        Collection<StudentPreEnrollmentResponse> preEnrollments = new HashSet<>();

        for (PreEnrollmentData preEnrollmentData : activesPreEnrollmentData) {
            StudentPreEnrollmentResponse preEnrollment = this.getStudentPreEnrollment(preEnrollmentData);
            preEnrollments.add(preEnrollment);
        }
        SubjectDemandSummary subjectDemandSummary = this.getSubjectDemandSummary(preEnrollments);

        return new PreEnrollmentsResponse(preEnrollments, subjectDemandSummary);
    }

    private SubjectDemandSummary getSubjectDemandSummary(Collection<StudentPreEnrollmentResponse> preEnrollments) {
        Collection<DetailedSubjectDemand> mandatoryDemand = getSubjectDemand("M", preEnrollments);
        Collection<DetailedSubjectDemand> optionalDemand = getSubjectDemand("O", preEnrollments);
        Collection<DetailedSubjectDemand> complementaryDemand = getSubjectDemand("C", preEnrollments);
        Collection<DetailedSubjectDemand> electiveDemand = getSubjectDemand("E", preEnrollments);

        return new SubjectDemandSummary(mandatoryDemand, optionalDemand, complementaryDemand, electiveDemand);
    }

    private Collection<DetailedSubjectDemand> getSubjectDemand(String subjectType, Collection<StudentPreEnrollmentResponse> preEnrollments) {
        Collection<DetailedSubjectDemand> response = new ArrayList<>();
        Map<Subject, Map<Integer, Integer>> subjectDemandByTerm = new HashMap<>();

        for (StudentPreEnrollmentResponse preEnrollment : preEnrollments) {
            Set<Subject> proposedSubjects = preEnrollment.getSubjects();
            int studentCurrentTerm = preEnrollment.getTerm();

            for (Subject subject : proposedSubjects) {
                if (subject.getType().equals(subjectType)) {
                    if (!subjectDemandByTerm.containsKey(subject)) {
                        subjectDemandByTerm.put(subject, new HashMap<>());
                    }
                    Map<Integer, Integer> demandByTerm = subjectDemandByTerm.get(subject);

                    if (!demandByTerm.containsKey(studentCurrentTerm)) {
                        demandByTerm.put(studentCurrentTerm, 0);
                    }

                    int currentDemand = demandByTerm.get(studentCurrentTerm);
                    subjectDemandByTerm.get(subject).put(studentCurrentTerm, currentDemand + 1);
                }
            }
        }

        for (Map.Entry<Subject, Map<Integer, Integer>> entry : subjectDemandByTerm.entrySet()) {
            Subject subject = entry.getKey();
            Map<Integer, Integer> demandByTerm = entry.getValue();

            response.add(new DetailedSubjectDemand(subject, demandByTerm));
        }

        return response;
    }

    private Subject getSubject(SubjectKey subjectKey) throws InvalidParameterException {
        Map<SubjectKey, SubjectData> subjectMap = this.mapsHolder.getMap("subjects");
        SubjectData subjectData = subjectMap.get(subjectKey);
        if (subjectData == null) throw new InvalidParameterException(Messages.INVALID_SUBJECT);
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

    private Collection<String> getSubjectsCode(String courseCode, String curriculumCode, SubjectType subjectType) throws InvalidParameterException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, curriculumCode, courseCode));
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
                    if (subjectMetricsPerRange.getMetrics() != null) metricsPerSubject.add(subjectMetricsPerRange.getMetrics());
                    if (subjectMetricsPerRange.getFrom().compareTo(first) < 0) first = subjectMetricsPerRange.getFrom();
                    if (subjectMetricsPerRange.getTo().compareTo(last) > 0) last = subjectMetricsPerRange.getFrom();
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

    private Collection<Student> getFilteredStudents(StudentClassification status, String courseCode, String curriculumCode) throws InvalidParameterException {
        return this.getFilteredStudents(status, courseCode, curriculumCode, "1949.2", "2050.1");
    }

    private Collection<String> getSubjectsList(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<String> subjectList;
        Curriculum curriculum = this.getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) throw new InvalidParameterException(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
        subjectList = curriculum.getMandatorySubjectsList();
        subjectList.addAll(curriculum.getComplementarySubjectsList());
        return subjectList;
    }

    @Override
    public Collection<Subject> getAllSubjects(String courseCode, String curriculumCode) throws InvalidParameterException {
        Curriculum curriculum = this.getCurriculum(courseCode, curriculumCode);
        Collection<String> subjectCodes = curriculum.getComplementarySubjectsList();
        subjectCodes.addAll(curriculum.getOptionalSubjectsList());
        subjectCodes.addAll(curriculum.getMandatorySubjectsList());
        subjectCodes.addAll(curriculum.getElectiveSubjectsList());

        return this.getSubjectsByCode(courseCode, curriculumCode, subjectCodes);
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
