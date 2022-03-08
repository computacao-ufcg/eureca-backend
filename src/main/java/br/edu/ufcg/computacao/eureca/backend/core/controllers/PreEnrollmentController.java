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
    private Map<SubjectKey, Subject> subjectsCache;
    private Map<SubjectScheduleKey, SubjectSchedule> scheduleCache;
    private Map<CurriculumKey, Curriculum> curriculumCache;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
        this.curriculumCache = new HashMap<>();
    }

    public StudentPreEnrollment getStudentPreEnrollment(String courseCode, String curriculumCode,
                                                        String studentRegistration, String term,
                                                        Integer numCredits, String optionalPriorityList,
                                                        String electivePriorityList,
                                                        String complementaryPriorityList,
                                                        String mandatoryPriorityList) throws EurecaException {
        this.loadSubjectAndScheduleCaches(courseCode, curriculumCode, term);
        PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, curriculumCode, studentRegistration,
                term, numCredits, optionalPriorityList, electivePriorityList, complementaryPriorityList,
                mandatoryPriorityList);
        StudentPreEnrollment studentPreEnrollment = this.getStudentPreEnrollment(preEnrollmentData);
        this.releaseSubjectAndScheduleCaches();
        return studentPreEnrollment;
    }

    public PreEnrollments getActivesPreEnrollments(String courseCode, String curriculumCode, String term) throws EurecaException {
        Collection<Student> actives = this.dataAccessFacade.getAllActives(courseCode, curriculumCode);
        this.loadSubjectAndScheduleCaches(courseCode, curriculumCode, term);
        Collection<String> activesRegistrations = actives.stream().map(student -> student.getRegistration().getRegistration()).collect(Collectors.toList());
        Collection<StudentPreEnrollment> activesPreEnrollments = new HashSet<>();

        for (String studentRegistration : activesRegistrations) {
            PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, curriculumCode, studentRegistration, term, null, null, null, null, null);
            StudentPreEnrollment preEnrollmentResponse = this.getStudentPreEnrollment(preEnrollmentData);
            activesPreEnrollments.add(preEnrollmentResponse);
        }

        SubjectDemandSummary subjectDemandSummary = this.getSubjectDemandSummary(courseCode, curriculumCode, term, activesPreEnrollments);
        this.releaseSubjectAndScheduleCaches();

        return new PreEnrollments(activesPreEnrollments, subjectDemandSummary);
    }

    private SubjectDemandSummary getSubjectDemandSummary(String courseCode, String curriculumCode, String term, Collection<StudentPreEnrollment> preEnrollments) {
        Collection<DetailedSubjectDemand> mandatoryDemand = this.getSubjectDemand(courseCode, curriculumCode, "M", preEnrollments);
        Collection<DetailedSubjectDemand> optionalDemand = this.getSubjectDemand(courseCode, curriculumCode, "O", preEnrollments);
        Collection<DetailedSubjectDemand> complementaryDemand = this.getSubjectDemand(courseCode, curriculumCode, "C", preEnrollments);
        Collection<DetailedSubjectDemand> electiveDemand = this.getSubjectDemand(courseCode, curriculumCode, "E", preEnrollments);

        return new SubjectDemandSummary(mandatoryDemand, optionalDemand, complementaryDemand, electiveDemand);
    }

    private Collection<DetailedSubjectDemand> getSubjectDemand(String courseCode, String curriculumCode,
                                         String subjectType, Collection<StudentPreEnrollment> preEnrollments) {
        Collection<DetailedSubjectDemand> response = new ArrayList<>();
        Map<Subject, Map<Integer, Integer>> subjectDemandByTerm = new HashMap<>();

        for (StudentPreEnrollment preEnrollment : preEnrollments) {
            Collection<String> proposedSubjectsCodes = preEnrollment.getSubjects().keySet();
            Collection<Subject> proposedSubjects = this.getSubjectsByCode(courseCode, curriculumCode, proposedSubjectsCodes);
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

    private PreEnrollmentData getPreEnrollmentData(String courseCode, String curriculumCode, String studentRegistration,
                                                   String term, Integer numCredits, String optionalPriorityList,
                                                   String electivePriorityList, String complementaryPriorityList,
                                                   String mandatoryPriorityList) throws EurecaException {
        Curriculum curriculum = getCachedCurriculum(courseCode, curriculumCode);
        StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, studentProgress);
        int nextTerm = PreEnrollmentUtil.getNextTerm(actualTerm, studentProgress.getEnrolledCredits(), curriculum.getMinNumberOfTerms());

        Map<SubjectType, Integer> idealCreditsMap = PreEnrollmentUtil.getIdealCreditsPerSubjectType(curriculum, numCredits, nextTerm);
        int idealMandatoryCredits = idealCreditsMap.get(SubjectType.MANDATORY);
        int idealOptionalCredits = idealCreditsMap.get(SubjectType.OPTIONAL);
        int idealComplementaryCredits = idealCreditsMap.get(SubjectType.COMPLEMENTARY);
        int idealElectiveCredits = idealCreditsMap.get(SubjectType.ELECTIVE);

        Map<SubjectType, Collection<Subject>> availableSubjectsGroupedByType = this.getSubjectsAndSchedulesAvailableForEnrollmentGroupedByType(courseCode, curriculumCode, term, studentProgress);
        Collection<Subject> availableMandatorySubjects = availableSubjectsGroupedByType.get(SubjectType.MANDATORY);
        Collection<Subject> availableOptionalSubjects = availableSubjectsGroupedByType.get(SubjectType.OPTIONAL);
        Collection<Subject> availableComplementarySubjects = availableSubjectsGroupedByType.get(SubjectType.COMPLEMENTARY);
        Collection<Subject> availableElectiveSubjects = availableSubjectsGroupedByType.get(SubjectType.ELECTIVE);

        Collection<Subject> prioritizedOptionalSubjects = this.getPriorityList(courseCode, curriculumCode, optionalPriorityList);
        Collection<Subject> prioritizedElectiveSubjects = this.getPriorityList(courseCode, curriculumCode, electivePriorityList);
        Collection<Subject> prioritizedComplementarySubjects = this.getPriorityList(courseCode, curriculumCode, complementaryPriorityList);
        Collection<Subject> prioritizedMandatorySubjects = this.getPriorityList(courseCode, curriculumCode, mandatoryPriorityList);

        Collection<SubjectSchedule> availableMandatorySubjectsWithSchedule = this.getSubjectsSchedules(availableMandatorySubjects, term);
        Collection<SubjectSchedule> availableOptionalSubjectsWithSchedule = this.getSubjectsSchedules(availableOptionalSubjects, term);
        Collection<SubjectSchedule> availableComplementarySubjectsWithSchedule = this.getSubjectsSchedules(availableComplementarySubjects, term);
        Collection<SubjectSchedule> availableElectiveSubjectsWithSchedule = this.getSubjectsSchedules(availableElectiveSubjects, term);

        Collection<SubjectSchedule> prioritizedMandatorySubjectsWithSchedule = this.getSubjectsSchedules(prioritizedMandatorySubjects, term);
        Collection<SubjectSchedule> prioritizedComplementarySubjectsWithSchedule = this.getSubjectsSchedules(prioritizedComplementarySubjects, term);
        Collection<SubjectSchedule> prioritizedOptionalSubjectsWithSchedule = this.getSubjectsSchedules(prioritizedOptionalSubjects, term);
        Collection<SubjectSchedule> prioritizedElectiveSubjectsWithSchedule = this.getSubjectsSchedules(prioritizedElectiveSubjects, term);

        return new PreEnrollmentData(studentRegistration, term, nextTerm, idealMandatoryCredits, idealOptionalCredits, idealComplementaryCredits, idealElectiveCredits,
                availableMandatorySubjectsWithSchedule, availableComplementarySubjectsWithSchedule, availableOptionalSubjectsWithSchedule, availableElectiveSubjectsWithSchedule,
                prioritizedOptionalSubjectsWithSchedule, prioritizedElectiveSubjectsWithSchedule, prioritizedComplementarySubjectsWithSchedule, prioritizedMandatorySubjectsWithSchedule);
    }

    private void loadSubjectAndScheduleCaches(String courseCode, String curriculumCode, String term) throws EurecaException {
        if (this.subjectsCache == null) {
            this.cacheSubjects(courseCode, curriculumCode);
            this.cacheSchedules(courseCode, curriculumCode, term);
        }
    }

    private void releaseSubjectAndScheduleCaches() {
        this.scheduleCache = null;
        this.subjectsCache = null;
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

    private StudentPreEnrollment getStudentPreEnrollment(PreEnrollmentData preEnrollmentData) {
        String studentRegistration = preEnrollmentData.getStudentRegistration();
        String term = preEnrollmentData.getTerm();
        int nextTerm = preEnrollmentData.getNextTerm();
        int idealMandatoryCredits = preEnrollmentData.getIdealMandatoryCredits();
        int idealOptionalCredits = preEnrollmentData.getIdealOptionalCredits();
        int idealComplementaryCredits = preEnrollmentData.getIdealComplementaryCredits();
        int idealElectiveCredits = preEnrollmentData.getIdealElectiveCredits();

        Collection<SubjectSchedule> availableMandatorySubjects = preEnrollmentData.getAvailableMandatorySubjects();
        Collection<SubjectSchedule> availableOptionalSubjects = preEnrollmentData.getAvailableOptionalSubjects();
        Collection<SubjectSchedule> availableComplementarySubjects = preEnrollmentData.getAvailableComplementarySubjects();
        Collection<SubjectSchedule> availableElectiveSubjects = preEnrollmentData.getAvailableElectiveSubjects();

        Collection<SubjectSchedule> prioritizedOptionalSubjects = preEnrollmentData.getPrioritizedOptionalSubjects();
        Collection<SubjectSchedule> prioritizedElectiveSubjects = preEnrollmentData.getPrioritizedElectiveSubjects();
        Collection<SubjectSchedule> prioritizedComplementarySubjects = preEnrollmentData.getPrioritizedComplementarySubjects();
        Collection<SubjectSchedule> prioritizedMandatorySubjects = preEnrollmentData.getPrioritizedMandatorySubjects();

        StudentPreEnrollment studentPreEnrollment = new StudentPreEnrollment(studentRegistration,
                nextTerm, idealMandatoryCredits, idealOptionalCredits, idealComplementaryCredits, idealElectiveCredits);

        prioritizedOptionalSubjects = PreEnrollmentUtil.excludeUnavailableSubjects(prioritizedOptionalSubjects, availableOptionalSubjects);
        prioritizedElectiveSubjects = PreEnrollmentUtil.excludeUnavailableSubjects(prioritizedElectiveSubjects, availableElectiveSubjects);
        prioritizedComplementarySubjects = PreEnrollmentUtil.excludeUnavailableSubjects(prioritizedComplementarySubjects, availableComplementarySubjects);
        prioritizedMandatorySubjects = PreEnrollmentUtil.excludeUnavailableSubjects(prioritizedMandatorySubjects, availableMandatorySubjects);

        this.enrollMandatorySubjectsUntilConflict(studentPreEnrollment, availableMandatorySubjects, term);

        if (!studentPreEnrollment.isMandatoryFull()) {
            this.enrollSubjects(studentPreEnrollment, prioritizedMandatorySubjects, term);
        }

        if (!studentPreEnrollment.isMandatoryFull()) {
            Collection<SubjectSchedule> mandatoryLeftovers = EurecaUtil.difference(availableMandatorySubjects, prioritizedMandatorySubjects);
            this.enrollSubjects(studentPreEnrollment, mandatoryLeftovers, term);
        }

        if (!studentPreEnrollment.isComplementaryFull()) {
            this.enrollSubjects(studentPreEnrollment, prioritizedComplementarySubjects, term);
            this.enrollSubjects(studentPreEnrollment, availableComplementarySubjects, term);
        }

        if (!studentPreEnrollment.isOptionalFull()) {
            this.enrollSubjects(studentPreEnrollment, prioritizedOptionalSubjects, term);
            this.enrollSubjects(studentPreEnrollment, availableOptionalSubjects, term);
        }

        if (!studentPreEnrollment.isElectiveFull()) {
            this.enrollSubjects(studentPreEnrollment, prioritizedElectiveSubjects, term);
            this.enrollSubjects(studentPreEnrollment, availableElectiveSubjects, term);
        }

        return studentPreEnrollment;
    }

    private void enrollMandatorySubjectsUntilConflict(StudentPreEnrollment studentPreEnrollment, Collection<SubjectSchedule> availableMandatorySubjects, String term) {
        Map<Integer, Collection<SubjectSchedule>> mandatorySubjectsGroupedByTerm = PreEnrollmentUtil.getSubjectsPerTerm(availableMandatorySubjects, SubjectType.MANDATORY);
        for (Integer termNumber : mandatorySubjectsGroupedByTerm.keySet()) {
            Collection<SubjectSchedule> termSubjects = mandatorySubjectsGroupedByTerm.get(termNumber);
            int totalTermCredits = PreEnrollmentUtil.getSubjectCreditsSum(termSubjects);

            if (totalTermCredits > studentPreEnrollment.getMaxMandatoryCredits() - studentPreEnrollment.getMandatoryCredits()) break;

            this.enrollSubjects(studentPreEnrollment, termSubjects, term);
        }
    }

    private void enrollSubjects(StudentPreEnrollment studentPreEnrollment, Collection<SubjectSchedule> availableSubjects, String term) {
        for (SubjectSchedule subjectAndSchedule : availableSubjects) {
            Subject subject = subjectAndSchedule.getSubject();
            if (subject.isComposed()) {
                Collection<Subject> coRequirements = this.getSubjectsByCode(subject.getCourseCode(), subject.getCurriculumCode(), subject.getCoRequirementsList());
                Collection<SubjectSchedule> coRequirementsSchedule = this.getSubjectsSchedules(coRequirements, term); // recupera os horários a partir da(s) disciplina(s) co-requisito(s)
                studentPreEnrollment.enrollSubject(subjectAndSchedule, coRequirementsSchedule);
            } else {
                studentPreEnrollment.enrollSubject(subjectAndSchedule);
            }
        }
    }

    // retorna uma coleção de disciplinas com horários a partir de uma coleção de (apenas) disciplinas
    private Collection<SubjectSchedule> getSubjectsSchedules(Collection<Subject> subjects, String term) {
        Collection<SubjectSchedule> subjectsSchedules = new ArrayList<>();
        for (Subject subject : subjects) {
            try {
                String courseCode = subject.getCourseCode();
                String curriculumCode = subject.getCurriculumCode();
                String subjectCode = subject.getSubjectCode();
                SubjectSchedule subjectSchedule = this.getSubjectSchedule(courseCode, curriculumCode, subjectCode, term);
                subjectsSchedules.add(subjectSchedule);
            } catch (InvalidParameterException e) {
                LOGGER.info(Messages.INVALID_SUBJECT_IGNORING);
            }
        }
        return subjectsSchedules;
    }

    private SubjectSchedule getSubjectSchedule(String courseCode, String curriculumCode, String subjectCode, String term) throws InvalidParameterException {
        SubjectScheduleKey key = new SubjectScheduleKey(courseCode, curriculumCode, subjectCode, term);
        SubjectSchedule subjectSchedule = this.scheduleCache.get(key);
        if (subjectSchedule == null) throw new InvalidParameterException(String.format(Messages.INVALID_SCHEDULE_S_S_S_S, courseCode, curriculumCode, subjectCode, term));
        return subjectSchedule;
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

    public SubjectsDemandResponse getDemand(String courseCode, String curriculumCode, String term) throws EurecaException {
        Collection<SubjectDemand> demand = new TreeSet<>();
        Collection<DetailedSubjectDemand> detailedDemand = this.getDetailedSubjectDemand(courseCode, curriculumCode, term);

        detailedDemand.forEach(subject -> {
            String subjectCode = subject.getDemand().getSubjectCode();
            String subjectName = subject.getDemand().getSubjectName();
            int totalDemand = subject.getDemand().getTotalDemand();
            demand.add(new SubjectDemand(subjectCode, subjectName, totalDemand));
        });
        return new SubjectsDemandResponse(demand);
    }

    private Collection<DetailedSubjectDemand> getDetailedSubjectDemand(String courseCode, String curriculumCode, String term) throws EurecaException {
        PreEnrollments preEnrollments = this.getActivesPreEnrollments(courseCode, curriculumCode, term);
        Collection<DetailedSubjectDemand> detailedDemand = new TreeSet<>();

        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getMandatoryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getComplementaryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getOptionalDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getElectiveDemand());

        return detailedDemand;
    }

    private void cacheSubjects(String courseCode, String curriculumCode) throws EurecaException {
        Collection<Subject> allSubjects = this.dataAccessFacade.getAllSubjects(courseCode, curriculumCode);
        this.subjectsCache = new HashMap<>();
        for (Subject subject : allSubjects) {
            SubjectKey key = new SubjectKey(courseCode, curriculumCode, subject.getSubjectCode());
            this.subjectsCache.put(key, subject);
        }
    }

    private void cacheSchedules(String courseCode, String curriculumCode, String term) {
        this.scheduleCache = this.dataAccessFacade.getAllSchedules(courseCode, curriculumCode, term);
    }

    private Collection<Subject> getPriorityList(String courseCode, String curriculumCode, String priorityListString) {
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
            priorityList = priorityList.stream().sorted(Comparator.comparingInt(Subject::getIdealTerm)).collect(Collectors.toList());
        }
        return priorityList;
    }

    public Map<SubjectType, Collection<Subject>> getSubjectsAndSchedulesAvailableForEnrollmentGroupedByType(String courseCode, String curriculumCode, String term, StudentCurriculumProgress studentCurriculumProgress) throws InvalidParameterException {
        Map<SubjectType, Collection<Subject>> groupedSubjects = new TreeMap<>();
        Collection<Subject> availableSubjects = this.getSubjectsAvailableForEnrollment(courseCode, curriculumCode, term, studentCurriculumProgress);

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
        Collection<String> ongoingSubjectsCode = studentCurriculumProgress.getOngoing().stream().map(SubjectKey::getSubjectCode).collect(Collectors.toList());
        Collection<String> concludedSubjectsCode = studentCurriculumProgress.getCompleted().stream().map(SubjectKey::getSubjectCode).collect(Collectors.toList());
        concludedSubjectsCode.addAll(ongoingSubjectsCode);
        return concludedSubjectsCode;
    }

    private Collection<Subject> getSubjectsAvailableForEnrollment(String courseCode, String curriculumCode, String term, StudentCurriculumProgress studentCurriculumProgress) throws InvalidParameterException {
        Collection<String> concludedSubjectsCode = this.getConcludedSubjects(studentCurriculumProgress);
        Set<Subject> availableSubjects = new HashSet<>();
        Collection<String> subjectCodes = this.subjectsCache.values().stream().map(Subject::getSubjectCode).collect(Collectors.toList());

        for (String subjectCode : subjectCodes) {
            Subject subject = this.getSubject(courseCode, curriculumCode, subjectCode);
            if (!concludedSubjectsCode.contains(subjectCode) && concludedSubjectsCode.containsAll(subject.getPreRequirementsList())) {
                try {
                    SubjectSchedule subjectAndSchedule = this.getSubjectSchedule(courseCode, curriculumCode, subjectCode, term);
                    PreEnrollmentUtil.sanitizeSubject(courseCode, curriculumCode, subjectAndSchedule, studentCurriculumProgress);
                    availableSubjects.add(subjectAndSchedule.getSubject());
                } catch (InvalidParameterException e) {
                    LOGGER.info(String.format(Messages.INVALID_SCHEDULE_S_S_S_S, courseCode, curriculumCode, subjectCode, term));
                }
            }
        }

        Comparator<Subject> orderByIdealTerm = Comparator.comparingInt(Subject::getIdealTerm);
        List<Subject> availableSubjectsList = new ArrayList<>(availableSubjects);
        availableSubjectsList.sort(orderByIdealTerm);

        return availableSubjectsList;
    }

    private Subject getSubject(String courseCode, String curriculumCode, String subjectCode) throws InvalidParameterException {
        Subject subject = this.subjectsCache.get(new SubjectKey(courseCode, curriculumCode, subjectCode));
        if (subject == null) throw new InvalidParameterException(Messages.INVALID_SUBJECT_IGNORING);
        return subject;
    }
}
