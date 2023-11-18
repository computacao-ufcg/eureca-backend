package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CurriculumKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.EurecaUtil;
import br.edu.ufcg.computacao.eureca.backend.core.util.PreEnrollmentUtil;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class PreEnrollmentController {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollmentController.class);

    private DataAccessFacade dataAccessFacade;
    private Map<CurriculumKey, Curriculum> curriculumCache;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
        this.curriculumCache = new HashMap<>();
    }


    public StudentPreEnrollment getStudentPreEnrollment(String courseCode, String studentRegistration, String term,
                                                        Integer numCredits, String optionalPriorityList,
                                                        String electivePriorityList,
                                                        String complementaryPriorityList,
                                                        String mandatoryPriorityList) throws EurecaException {
        Map<SubjectScheduleKey, SubjectSchedule> schedule = this.dataAccessFacade.getAllSchedules(courseCode, term);
        StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
        // Creates list of available and prioritized subjects, based on request input
        PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, term, studentProgress,
                optionalPriorityList, electivePriorityList, complementaryPriorityList, mandatoryPriorityList, schedule);
        // Computes the actual preEnrollment
        StudentPreEnrollment studentPreEnrollment = this.getStudentPreEnrollment(courseCode, term, studentRegistration,
                studentProgress, numCredits, preEnrollmentData, schedule);
        // Empties cache, so that new request will compute preEnrollment from fresh data
        return studentPreEnrollment;
    }

    private StudentPreEnrollment getStudentPreEnrollment(String courseCode, String studentRegistration, String term,
                                                        Integer numCredits, String optionalPriorityList,
                                                        String electivePriorityList,
                                                        String complementaryPriorityList,
                                                        String mandatoryPriorityList,
                                                        Map<SubjectScheduleKey, SubjectSchedule> schedule) throws EurecaException {

        StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
        // Creates list of available and prioritized subjects, based on request input
        PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, term, studentProgress,
                optionalPriorityList, electivePriorityList, complementaryPriorityList, mandatoryPriorityList, schedule);
        // Computes the actual preEnrollment
        StudentPreEnrollment studentPreEnrollment = this.getStudentPreEnrollment(courseCode, term, studentRegistration,
                studentProgress, numCredits, preEnrollmentData, schedule);
        // Empties cache, so that new request will compute preEnrollment from fresh data
        return studentPreEnrollment;
    }

    public PreEnrollments getActivesPreEnrollments(String courseCode, String term) throws EurecaException {
        Collection<Student> actives = this.dataAccessFacade.getAllActives(courseCode);
        Collection<StudentPreEnrollment> activesPreEnrollments = new HashSet<>();
        Map<SubjectScheduleKey, SubjectSchedule> schedule = this.dataAccessFacade.getAllSchedules(courseCode, term);

        for (Student student : actives) {
            String studentRegistration = student.getRegistration().getRegistration();
            LOGGER.debug(String.format(Messages.PRE_ENROLLING_S, studentRegistration));
            StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
            // Creates list of available subjects
            PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, term, studentProgress, schedule);
            // Computes the actual preEnrollment for a student
            StudentPreEnrollment preEnrollmentResponse = this.getStudentPreEnrollment(courseCode,
                    term, studentRegistration, studentProgress, null, preEnrollmentData, schedule);
            activesPreEnrollments.add(preEnrollmentResponse);
        }
        SubjectDemandSummary subjectDemandSummary = this.getSubjectDemandSummary(courseCode, activesPreEnrollments);
        return new PreEnrollments(activesPreEnrollments, subjectDemandSummary);
    }

    private SubjectDemandSummary getSubjectDemandSummary(String courseCode, Collection<StudentPreEnrollment> preEnrollments) throws EurecaException {
        Collection<DetailedSubjectDemand> mandatoryDemand = this.getSubjectDemand(courseCode, "M", preEnrollments);
        Collection<DetailedSubjectDemand> optionalDemand = this.getSubjectDemand(courseCode, "O", preEnrollments);
        Collection<DetailedSubjectDemand> complementaryDemand = this.getSubjectDemand(courseCode, "C", preEnrollments);
        Collection<DetailedSubjectDemand> electiveDemand = this.getSubjectDemand(courseCode, "E", preEnrollments);

        return new SubjectDemandSummary(mandatoryDemand, optionalDemand, complementaryDemand, electiveDemand);
    }

    private Collection<DetailedSubjectDemand> getSubjectDemand(String courseCode, String subjectType,
                                                               Collection<StudentPreEnrollment> preEnrollments) throws EurecaException {
        Collection<DetailedSubjectDemand> response = new ArrayList<>();
        Map<Subject, Map<Integer, Integer>> subjectDemandByTerm = new HashMap<>();

        for (StudentPreEnrollment preEnrollment : preEnrollments) {
            Collection<String> proposedSubjectsCodes = preEnrollment.getSubjects().keySet();
            Collection<Subject> proposedSubjects = this.getSubjectsByCode(courseCode, preEnrollment.getCurriculumCode(),
                    proposedSubjectsCodes);
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

    private PreEnrollmentData getPreEnrollmentData(String courseCode, String term,
                                                   StudentCurriculumProgress studentProgress,
                                                   Map<SubjectScheduleKey, SubjectSchedule> schedule) throws EurecaException {
        return this.getPreEnrollmentData(courseCode, term, studentProgress, null, null, null, null, schedule);
    }

    private PreEnrollmentData getPreEnrollmentData(String courseCode, String term,
                                                   StudentCurriculumProgress studentProgress, String optionalPriorityList,
                                                   String electivePriorityList, String complementaryPriorityList,
                                                   String mandatoryPriorityList,
                                                   Map<SubjectScheduleKey, SubjectSchedule> schedule) throws EurecaException {

        Map<SubjectType, Collection<Subject>> availableSubjectsGroupedByType =
                this.getSubjectsAndSchedulesAvailableForEnrollmentGroupedByType(courseCode, studentProgress, term, schedule);
        Collection<Subject> availableMandatorySubjects = availableSubjectsGroupedByType.get(SubjectType.MANDATORY);
        Collection<Subject> availableOptionalSubjects = availableSubjectsGroupedByType.get(SubjectType.OPTIONAL);
        Collection<Subject> availableComplementarySubjects = availableSubjectsGroupedByType.get(SubjectType.COMPLEMENTARY);
        Collection<Subject> availableElectiveSubjects = availableSubjectsGroupedByType.get(SubjectType.ELECTIVE);
        Collection<SubjectSchedule> availableMandatorySubjectsWithSchedule =
                this.getSubjectsSchedules(availableMandatorySubjects, term, schedule);
        Collection<SubjectSchedule> availableOptionalSubjectsWithSchedule =
                this.getSubjectsSchedules(availableOptionalSubjects, term, schedule);
        Collection<SubjectSchedule> availableComplementarySubjectsWithSchedule =
                this.getSubjectsSchedules(availableComplementarySubjects, term, schedule);
        Collection<SubjectSchedule> availableElectiveSubjectsWithSchedule =
                this.getSubjectsSchedules(availableElectiveSubjects, term, schedule);

        Collection<Subject> prioritizedOptionalSubjects = this.getPriorityList(courseCode,
                studentProgress.getCurriculumCode(), optionalPriorityList);
        Collection<Subject> prioritizedElectiveSubjects = this.getPriorityList(courseCode,
                studentProgress.getCurriculumCode(), electivePriorityList);
        Collection<Subject> prioritizedComplementarySubjects = this.getPriorityList(courseCode,
                studentProgress.getCurriculumCode(), complementaryPriorityList);
        Collection<Subject> prioritizedMandatorySubjects = this.getPriorityList(courseCode,
                studentProgress.getCurriculumCode(), mandatoryPriorityList);
        Collection<SubjectSchedule> prioritizedMandatorySubjectsWithSchedule =
                this.getSubjectsSchedules(prioritizedMandatorySubjects, term, schedule);
        Collection<SubjectSchedule> prioritizedComplementarySubjectsWithSchedule =
                this.getSubjectsSchedules(prioritizedComplementarySubjects, term, schedule);
        Collection<SubjectSchedule> prioritizedOptionalSubjectsWithSchedule =
                this.getSubjectsSchedules(prioritizedOptionalSubjects, term, schedule);
        Collection<SubjectSchedule> prioritizedElectiveSubjectsWithSchedule =
                this.getSubjectsSchedules(prioritizedElectiveSubjects, term, schedule);

        prioritizedOptionalSubjectsWithSchedule = PreEnrollmentUtil.
                excludeUnavailableSubjects(prioritizedOptionalSubjectsWithSchedule,
                availableOptionalSubjectsWithSchedule);
        prioritizedElectiveSubjectsWithSchedule = PreEnrollmentUtil.
                excludeUnavailableSubjects(prioritizedElectiveSubjectsWithSchedule,
                availableElectiveSubjectsWithSchedule);
        prioritizedComplementarySubjectsWithSchedule = PreEnrollmentUtil.
                excludeUnavailableSubjects(prioritizedComplementarySubjectsWithSchedule,
                availableComplementarySubjectsWithSchedule);
        prioritizedMandatorySubjectsWithSchedule = PreEnrollmentUtil.
                excludeUnavailableSubjects(prioritizedMandatorySubjectsWithSchedule,
                availableMandatorySubjectsWithSchedule);

        return new PreEnrollmentData(availableMandatorySubjectsWithSchedule, availableComplementarySubjectsWithSchedule,
                availableOptionalSubjectsWithSchedule, availableElectiveSubjectsWithSchedule,
                prioritizedOptionalSubjectsWithSchedule, prioritizedElectiveSubjectsWithSchedule,
                prioritizedComplementarySubjectsWithSchedule, prioritizedMandatorySubjectsWithSchedule);
    }

    private Curriculum getCachedCurriculum(String courseCode, String curriculumCode) throws EurecaException {
        CurriculumKey key = new CurriculumKey(courseCode, curriculumCode);
        Curriculum curriculum = this.curriculumCache.get(key);
        if (curriculum == null) {
            curriculum = this.dataAccessFacade.getCurriculum(courseCode, curriculumCode);
            this.curriculumCache.put(key, curriculum);
        }
        return curriculum;
    }

    private StudentPreEnrollment getStudentPreEnrollment(String courseCode, String term,
                 String studentRegistration, StudentCurriculumProgress studentProgress, Integer maxCredits,
                 PreEnrollmentData preEnrollmentData, Map<SubjectScheduleKey, SubjectSchedule> schedule) throws EurecaException {

        Curriculum curriculum = getCachedCurriculum(courseCode, studentProgress.getCurriculumCode());
        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, studentProgress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        if (maxCredits == null) maxCredits = new Integer(curriculum.getIdealMaxCredits(nextTerm));

        // Check whether student can extrapolate the maximum amount of credits per term
        int deficit = computeDeficit(curriculum, null, studentProgress);
        if (deficit <= (curriculum.getMaxNumberOfEnrolledCredits() +
                curriculum.getExceptionalAdditionalEnrolledCredits())) {
            maxCredits = deficit;
        }

        // Distribute maxCredits among the different types of subjects
        Map<SubjectType, Integer> idealCreditsMap = PreEnrollmentUtil.getIdealCreditsPerSubjectType(curriculum,
                studentProgress, maxCredits);
        int idealMandatoryCredits = idealCreditsMap.get(SubjectType.MANDATORY);
        int idealOptionalCredits = idealCreditsMap.get(SubjectType.OPTIONAL);
        int idealComplementaryCredits = idealCreditsMap.get(SubjectType.COMPLEMENTARY);
        int idealElectiveCredits = idealCreditsMap.get(SubjectType.ELECTIVE);

        Collection<SubjectSchedule> availableMandatorySubjects = preEnrollmentData.getAvailableMandatorySubjects();
        Collection<SubjectSchedule> availableOptionalSubjects = preEnrollmentData.getAvailableOptionalSubjects();
        Collection<SubjectSchedule> availableComplementarySubjects = preEnrollmentData.getAvailableComplementarySubjects();
        Collection<SubjectSchedule> availableElectiveSubjects = preEnrollmentData.getAvailableElectiveSubjects();

        Collection<SubjectSchedule> prioritizedOptionalSubjects = preEnrollmentData.getPrioritizedOptionalSubjects();
        Collection<SubjectSchedule> prioritizedElectiveSubjects = preEnrollmentData.getPrioritizedElectiveSubjects();
        Collection<SubjectSchedule> prioritizedComplementarySubjects = preEnrollmentData.getPrioritizedComplementarySubjects();
        Collection<SubjectSchedule> prioritizedMandatorySubjects = preEnrollmentData.getPrioritizedMandatorySubjects();

        StudentPreEnrollment studentPreEnrollment = new StudentPreEnrollment(studentRegistration, studentProgress.getCurriculumCode(),
                nextTerm, maxCredits, idealMandatoryCredits, idealOptionalCredits, idealComplementaryCredits, idealElectiveCredits);

        // Mandatory subjects are first enrolled from the earliest terms to the more recent, until in a
        // given term, the number of choices is larger than the number of subjects left to enroll
        this.enrollMandatorySubjectsUntilConflict(studentPreEnrollment, availableMandatorySubjects, term, schedule);

        // If there is still space for mandatory subjects, this means that there are more than one option
        // for a given term; now we use the prioritization sent as parameter in the request (if any)
        if (studentPreEnrollment.isMandatoryNotFull()) {
            this.enrollSubjects(studentPreEnrollment, prioritizedMandatorySubjects, term, schedule);
        }

        // If there is still space, select from whatever mandatory subject is still available
        if (studentPreEnrollment.isMandatoryNotFull()) {
            this.enrollSubjects(studentPreEnrollment, availableMandatorySubjects, term, schedule);
        }

        // From now on, for each type, we try first to use the prioritization sent in the request (if any), and then
        // we enroll any other available subject of the type in question.
        if (studentPreEnrollment.isComplementaryNotFull()) {
            this.enrollSubjects(studentPreEnrollment, prioritizedComplementarySubjects, term, schedule);
            this.enrollSubjects(studentPreEnrollment, availableComplementarySubjects, term, schedule);
        }

        // We use the rate on the missing credits to define the order of prioritization of
        // optional and elective subjects; the higher the rate, the higher the priority

        int missingOptionalCredits = Math.max(0, (curriculum.getTargetOptionalCredits(nextTerm) -
                (studentProgress.getCompletedOptionalCredits() + studentProgress.getEnrolledOptionalCredits())));
        int missingElectiveCredits = Math.max(0, (curriculum.getTargetElectiveCredits(nextTerm) -
                (studentProgress.getCompletedElectiveCredits() + studentProgress.getEnrolledElectiveCredits())));
        double optionalMissingRate = (double) missingOptionalCredits / (double) curriculum.getTargetOptionalCredits(nextTerm);
        double electiveMissingRate = (double) missingElectiveCredits / (double) curriculum.getTargetElectiveCredits(nextTerm);

        if (optionalMissingRate >= electiveMissingRate) {
            if (studentPreEnrollment.isOptionalNotFull()) {
                this.enrollSubjects(studentPreEnrollment, prioritizedOptionalSubjects, term, schedule);
                this.enrollSubjects(studentPreEnrollment, availableOptionalSubjects, term, schedule);
            }
            if (studentPreEnrollment.isElectiveNotFull()) {
                this.enrollSubjects(studentPreEnrollment, prioritizedElectiveSubjects, term, schedule);
                this.enrollSubjects(studentPreEnrollment, availableElectiveSubjects, term, schedule);
            }
        } else {
            if (studentPreEnrollment.isElectiveNotFull()) {
                this.enrollSubjects(studentPreEnrollment, prioritizedElectiveSubjects, term, schedule);
                this.enrollSubjects(studentPreEnrollment, availableElectiveSubjects, term, schedule);
            }
            if (studentPreEnrollment.isOptionalNotFull()) {
                this.enrollSubjects(studentPreEnrollment, prioritizedOptionalSubjects, term, schedule);
                this.enrollSubjects(studentPreEnrollment, availableOptionalSubjects, term, schedule);
            }
        }

        studentPreEnrollment.setPossibleGraduate(isPossibleGraduate(studentPreEnrollment, studentProgress, curriculum));
        return studentPreEnrollment;
    }

    private void enrollMandatorySubjectsUntilConflict(StudentPreEnrollment studentPreEnrollment,
                                                      Collection<SubjectSchedule> availableMandatorySubjects,
                                                      String term, Map<SubjectScheduleKey, SubjectSchedule> schedule)
                                                      throws EurecaException {
        Map<Integer, Collection<SubjectSchedule>> mandatorySubjectsGroupedByTerm = PreEnrollmentUtil.getSubjectsPerTerm(availableMandatorySubjects, SubjectType.MANDATORY);
        for (Integer termNumber : mandatorySubjectsGroupedByTerm.keySet()) {
            Collection<SubjectSchedule> termSubjects = mandatorySubjectsGroupedByTerm.get(termNumber);
            int totalTermCredits = PreEnrollmentUtil.getSubjectCreditsSum(termSubjects);

            if (totalTermCredits > studentPreEnrollment.getMaxMandatoryCredits() -
                    studentPreEnrollment.getMandatoryCredits()) break;

            this.enrollSubjects(studentPreEnrollment, termSubjects, term, schedule);
        }
    }

    private void enrollSubjects(StudentPreEnrollment studentPreEnrollment, Collection<SubjectSchedule> availableSubjects,
                                String term, Map<SubjectScheduleKey, SubjectSchedule> schedule) throws EurecaException {
        for (SubjectSchedule subjectAndSchedule : availableSubjects) {
            Subject subject = subjectAndSchedule.getSubject();
            Set keySet = studentPreEnrollment.getSubjects().keySet();
            if (keySet.contains(subject.getSubjectCode())) {
                continue;
            }
            if (subject.isComposed()) {
                Collection<Subject> coRequirements = this.getSubjectsByCode(subject.getCourseCode(),
                        subject.getCurriculumCode(), subject.getCoRequirementsList());
                // get available schedules, considering the co-requirements
                Collection<SubjectSchedule> coRequirementsSchedule = this.getSubjectsSchedules(coRequirements,
                        term, schedule);
                studentPreEnrollment.enrollSubject(subjectAndSchedule, coRequirementsSchedule);
            } else {
                studentPreEnrollment.enrollSubject(subjectAndSchedule);
            }
        }
    }

    // given a collection with subjects, returns a collection with subject and schedules
    private Collection<SubjectSchedule> getSubjectsSchedules(Collection<Subject> subjects, String term,
                                                             Map<SubjectScheduleKey, SubjectSchedule> schedule) {
        Collection<SubjectSchedule> subjectsSchedules = new ArrayList<>();
        for (Subject subject : subjects) {
            try {
                String courseCode = subject.getCourseCode();
                String curriculumCode = subject.getCurriculumCode();
                String subjectCode = subject.getSubjectCode();
                SubjectSchedule subjectSchedule = this.getSubjectSchedule(courseCode, curriculumCode, subjectCode,
                        term, schedule);
                subjectsSchedules.add(subjectSchedule);
            } catch (InvalidParameterException e) {
                LOGGER.error(Messages.INVALID_SUBJECT_IGNORING);
            }
        }
        return subjectsSchedules;
    }

    private SubjectSchedule getSubjectSchedule(String courseCode, String curriculumCode, String subjectCode,
                                               String term, Map<SubjectScheduleKey, SubjectSchedule> schedule)
                                               throws InvalidParameterException {
        SubjectScheduleKey key = new SubjectScheduleKey(courseCode, curriculumCode, subjectCode, term);
        SubjectSchedule cachedSubjectSchedule = schedule.get(key);
        if (cachedSubjectSchedule == null) throw new InvalidParameterException(String.format(
                Messages.INVALID_SCHEDULE_S_S_S_S, courseCode, curriculumCode, subjectCode, term));
        return cachedSubjectSchedule;
    }

    private Collection<Subject> getSubjectsByCode(String courseCode, String curriculumCode, Collection<String>
            subjectCodes) throws EurecaException {
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

    public SubjectsDemandResponse getDemand(String courseCode, String term) throws EurecaException {
        Collection<SubjectDemand> demand = new TreeSet<>();
        Collection<DetailedSubjectDemand> detailedDemand = this.getDetailedSubjectDemand(courseCode, term);

        detailedDemand.forEach(subject -> {
            String subjectCode = subject.getDemand().getSubjectCode();
            String subjectName = subject.getDemand().getSubjectName();
            int totalDemand = subject.getDemand().getTotalDemand();
            demand.add(new SubjectDemand(subjectCode, subjectName, totalDemand));
        });
        return new SubjectsDemandResponse(demand);
    }

    private Collection<DetailedSubjectDemand> getDetailedSubjectDemand(String courseCode, String term)
            throws EurecaException {
        PreEnrollments preEnrollments = this.getActivesPreEnrollments(courseCode, term);
        Collection<DetailedSubjectDemand> detailedDemand = new TreeSet<>();

        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getMandatoryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getComplementaryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getOptionalDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getElectiveDemand());

        return detailedDemand;
    }

    private Collection<Subject> getPriorityList(String courseCode, String curriculumCode, String priorityListString)
            throws EurecaException {
        Collection<Subject> priorityList = new ArrayList<>();
        if (priorityListString != null) {
            String[] priorityListCodes = priorityListString.split(",");
            for (String subjectCode : priorityListCodes) {
                try {
                    Subject subject = this.getSubject(courseCode, curriculumCode, subjectCode);
                    priorityList.add(subject);
                } catch (InvalidParameterException e) {
                    LOGGER.error(Messages.INVALID_SUBJECT_IGNORING);
                }
            }
            priorityList = priorityList.stream().sorted(Comparator.comparingInt(Subject::getIdealTerm)).
                    collect(Collectors.toList());
        }
        return priorityList;
    }

    public Map<SubjectType, Collection<Subject>> getSubjectsAndSchedulesAvailableForEnrollmentGroupedByType(
            String courseCode, StudentCurriculumProgress studentCurriculumProgress, String term,
            Map<SubjectScheduleKey, SubjectSchedule> schedule) throws EurecaException {
        Map<SubjectType, Collection<Subject>> groupedSubjects = new TreeMap<>();
        Collection<Subject> availableSubjects = this.getSubjectsAvailableForEnrollment(courseCode,
                studentCurriculumProgress, term, schedule);

        groupedSubjects.put(SubjectType.MANDATORY, new ArrayList<>());
        groupedSubjects.put(SubjectType.OPTIONAL, new ArrayList<>());
        groupedSubjects.put(SubjectType.COMPLEMENTARY, new ArrayList<>());
        groupedSubjects.put(SubjectType.ELECTIVE, new ArrayList<>());

        for (Subject subject : availableSubjects) {
            SubjectType subjectType = EurecaUtil.getSubjectType(subject);
            groupedSubjects.get(subjectType).add(subject);
        }

        return groupedSubjects;
    }

    private Collection<String> getConcludedSubjects(StudentCurriculumProgress studentCurriculumProgress) {
        // Optimistically considers that ongoing subjects will be successfully concluded
        Collection<String> ongoingSubjectsCode = studentCurriculumProgress.getOngoing().stream().
                map(SubjectKey::getSubjectCode).collect(Collectors.toList());
        Collection<String> concludedSubjectsCode = studentCurriculumProgress.getCompleted().stream().
                map(SubjectKey::getSubjectCode).collect(Collectors.toList());
        concludedSubjectsCode.addAll(ongoingSubjectsCode);
        return concludedSubjectsCode;
    }

    private Collection<Subject> getSubjectsAvailableForEnrollment(String courseCode,
                                StudentCurriculumProgress studentCurriculumProgress, String term,
                                Map<SubjectScheduleKey, SubjectSchedule> schedule) throws EurecaException {
        Collection<String> concludedSubjectsCode = this.getConcludedSubjects(studentCurriculumProgress);
        Set<Subject> availableSubjects = new HashSet<>();

        for (SubjectSchedule subjectSchedule : schedule.values()) {
            if (!subjectSchedule.getSubject().getCurriculumCode().equals(studentCurriculumProgress.getCurriculumCode())) {
                continue;
            }
            String subjectCode = subjectSchedule.getSubject().getSubjectCode();
            Subject subject = this.getSubject(courseCode, studentCurriculumProgress.getCurriculumCode(), subjectCode);
            if (!concludedSubjectsCode.contains(subjectCode) && concludedSubjectsCode.
                    containsAll(subject.getPreRequirementsList())) {
                try {
                    SubjectSchedule subjectAndSchedule = this.getSubjectSchedule(courseCode,
                            studentCurriculumProgress.getCurriculumCode(), subjectCode, term, schedule);
                    PreEnrollmentUtil.sanitizeSubject(courseCode, studentCurriculumProgress.getCurriculumCode(),
                            subjectAndSchedule, studentCurriculumProgress);
                    availableSubjects.add(subjectAndSchedule.getSubject());
                } catch (InvalidParameterException e) {
                    LOGGER.debug(String.format(Messages.INVALID_SCHEDULE_S_S_S_S, courseCode,
                            studentCurriculumProgress.getCurriculumCode(), subjectCode, term));
                }
            }
        }

        Comparator<Subject> orderByIdealTermThenCredits = Comparator.comparingInt(Subject::getIdealTerm);
        List<Subject> availableSubjectsList = new ArrayList<>(availableSubjects);
        availableSubjectsList.sort(orderByIdealTermThenCredits);

        return availableSubjectsList;
    }

    private Subject getSubject(String courseCode, String curriculumCode, String subjectCode) throws EurecaException {
        Subject subject = this.dataAccessFacade.getSubject(courseCode, curriculumCode, subjectCode);
        if (subject == null) throw new InvalidParameterException(String.format(Messages.INVALID_SUBJECT,
                new SubjectKey(courseCode, curriculumCode, subjectCode)));
        return subject;
    }

    private boolean isPossibleGraduate(StudentPreEnrollment studentPreEnrollment, StudentCurriculumProgress
            studentProgress, Curriculum curriculum) {
        return (computeDeficit(curriculum, studentPreEnrollment, studentProgress) == 0);
    }

    private int computeDeficit(Curriculum curriculum, StudentPreEnrollment studentPreEnrollment, StudentCurriculumProgress studentProgress) {
        int mandatoryNeeded = curriculum.getMinMandatoryCreditsNeeded();
        int complementaryNeeded = curriculum.getMinComplementaryCreditsNeeded();
        int optionalNeeded = curriculum.getMinOptionalCreditsNeeded();
        int electiveNeeded = curriculum.getMinElectiveCreditsNeeded();

        int mandatoryCompleted = studentProgress.getCompletedMandatoryCredits() +
                studentProgress.getEnrolledMandatoryCredits() +
                (studentPreEnrollment != null ? studentPreEnrollment.getMandatoryCredits() : 0);
        int complementaryCompleted = studentProgress.getCompletedComplementaryCredits() +
                studentProgress.getEnrolledComplementaryCredits() +
                (studentPreEnrollment != null ? studentPreEnrollment.getComplementaryCredits() : 0);
        int optionalCompleted = studentProgress.getCompletedOptionalCredits() +
                studentProgress.getEnrolledOptionalCredits() +
                (studentPreEnrollment != null ? studentPreEnrollment.getOptionalCredits() : 0);
        int electiveCompleted = studentProgress.getCompletedElectiveCredits() +
                studentProgress.getEnrolledElectiveCredits() +
                (studentPreEnrollment != null ? studentPreEnrollment.getElectiveCredits() : 0);

        int deficit = Math.max(0, mandatoryNeeded - mandatoryCompleted);
        deficit += Math.max(0, complementaryNeeded - complementaryCompleted);
        deficit += Math.max(0, optionalNeeded - optionalCompleted);
        deficit += Math.max(0, electiveNeeded - electiveCompleted);

        return deficit;
    }
}
