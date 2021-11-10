package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.EurecaUtil;
import br.edu.ufcg.computacao.eureca.backend.core.util.PreEnrollmentUtil;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class PreEnrollmentController {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollmentController.class);

    private DataAccessFacade dataAccessFacade;
    private Map<SubjectKey, Subject> subjectsCache;
    private Map<SubjectScheduleKey, SubjectSchedule> scheduleCache;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentPreEnrollmentResponse getStudentPreEnrollment(String courseCode, String curriculumCode, String studentRegistration, String term, Integer numCredits,
                                                                String optionalPriorityList, String electivePriorityList, String mandatoryPriorityList) throws InvalidParameterException {
        PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, curriculumCode, studentRegistration, term, numCredits, optionalPriorityList, electivePriorityList, mandatoryPriorityList);
        return this.getStudentPreEnrollment(preEnrollmentData);
    }

    public PreEnrollmentsResponse getActivesPreEnrollments(String courseCode, String curriculumCode, String term) throws InvalidParameterException  {
        Collection<Student> actives = this.dataAccessFacade.getAllActives(courseCode, curriculumCode);
        Collection<String> activesRegistrations = actives.stream().map(student -> student.getRegistration().getRegistration()).collect(Collectors.toList());
        Collection<StudentPreEnrollmentResponse> activesPreEnrollments = new HashSet<>();

        for (String studentRegistration : activesRegistrations) {
            PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, curriculumCode, studentRegistration, term, null, null, null, null);
            StudentPreEnrollmentResponse preEnrollmentResponse = this.getStudentPreEnrollment(preEnrollmentData);
            activesPreEnrollments.add(preEnrollmentResponse);
        }

        SubjectDemandSummary subjectDemandSummary = this.getSubjectDemandSummary(activesPreEnrollments);
        return new PreEnrollmentsResponse(activesPreEnrollments, subjectDemandSummary);
    }

    private SubjectDemandSummary getSubjectDemandSummary(Collection<StudentPreEnrollmentResponse> preEnrollments) {
        Collection<DetailedSubjectDemand> mandatoryDemand = this.getSubjectDemand("M", preEnrollments);
        Collection<DetailedSubjectDemand> optionalDemand = this.getSubjectDemand("O", preEnrollments);
        Collection<DetailedSubjectDemand> complementaryDemand = this.getSubjectDemand("C", preEnrollments);
        Collection<DetailedSubjectDemand> electiveDemand = this.getSubjectDemand("E", preEnrollments);

        return new SubjectDemandSummary(mandatoryDemand, optionalDemand, complementaryDemand, electiveDemand);
    }

    private Collection<DetailedSubjectDemand> getSubjectDemand(String subjectType, Collection<StudentPreEnrollmentResponse> preEnrollments) {
        Collection<DetailedSubjectDemand> response = new ArrayList<>();
        Map<Subject, Map<Integer, Integer>> subjectDemandByTerm = new HashMap<>();

        for (StudentPreEnrollmentResponse preEnrollment : preEnrollments) {
            Set<Subject> proposedSubjects = preEnrollment.getSubjects().keySet();
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

    private PreEnrollmentData getPreEnrollmentData(String courseCode, String curriculumCode, String studentRegistration, String term, Integer numCredits, String optionalPriorityList, String electivePriorityList, String mandatoryPriorityList) throws InvalidParameterException {
        Curriculum curriculum = this.dataAccessFacade.getCurriculum(courseCode, curriculumCode);
        StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
        if (this.subjectsCache == null || this.subjectsCache.isEmpty()) {
            this.cacheSubjects(courseCode, curriculumCode);
            this.cacheSchedule(courseCode, curriculumCode, term);
        }

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
        Collection<Subject> prioritizedMandatorySubjects = this.getPriorityList(courseCode, curriculumCode, mandatoryPriorityList);

        return new PreEnrollmentData(studentRegistration, term, nextTerm, idealMandatoryCredits, idealOptionalCredits, idealComplementaryCredits, idealElectiveCredits,
                availableMandatorySubjects, availableComplementarySubjects, availableOptionalSubjects, availableElectiveSubjects,
                prioritizedOptionalSubjects, prioritizedElectiveSubjects, prioritizedMandatorySubjects);
    }

    private StudentPreEnrollmentResponse getStudentPreEnrollment(PreEnrollmentData preEnrollmentData) {
        String studentRegistration = preEnrollmentData.getStudentRegistration();
        String term = preEnrollmentData.getTerm();
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

        prioritizedOptionalSubjects = PreEnrollmentUtil.excludeUnavailableSubjects(prioritizedOptionalSubjects, availableOptionalSubjects);
        prioritizedElectiveSubjects = PreEnrollmentUtil.excludeUnavailableSubjects(prioritizedElectiveSubjects, availableElectiveSubjects);
        prioritizedMandatorySubjects = PreEnrollmentUtil.excludeUnavailableSubjects(prioritizedMandatorySubjects, availableMandatorySubjects);

        this.enrollMandatorySubjectsUntilConflict(studentPreEnrollment, availableMandatorySubjects, term);

        if (!studentPreEnrollment.isMandatoryFull()) {
            this.enrollSubjects(studentPreEnrollment, prioritizedMandatorySubjects, term);
        }

        if (!studentPreEnrollment.isMandatoryFull()) {
            Collection<Subject> mandatoryLeftovers = EurecaUtil.difference(availableMandatorySubjects, prioritizedMandatorySubjects);
            this.enrollSubjects(studentPreEnrollment, mandatoryLeftovers, term);
        }

        if (!studentPreEnrollment.isComplementaryFull()) {
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

    private void enrollMandatorySubjectsUntilConflict(StudentPreEnrollmentResponse studentPreEnrollment, Collection<Subject> availableMandatorySubjects, String term) {
        Map<Integer, Collection<Subject>> mandatorySubjectsGroupedByTerm = PreEnrollmentUtil.getSubjectsGroupedByTermAndType(availableMandatorySubjects, SubjectType.MANDATORY);
        for (Integer termNumber : mandatorySubjectsGroupedByTerm.keySet()) {
            Collection<Subject> termSubjects = mandatorySubjectsGroupedByTerm.get(termNumber);
            int totalTermCredits = PreEnrollmentUtil.getSubjectCreditsSum(termSubjects);

            if (totalTermCredits > studentPreEnrollment.getMaxMandatoryCredits() - studentPreEnrollment.getMandatoryCredits()) break;

            enrollSubjects(studentPreEnrollment, termSubjects, term);
        }
    }

    private void enrollSubjects(StudentPreEnrollmentResponse studentPreEnrollment, Collection<Subject> availableSubjects, String term) {
        for (Subject s : availableSubjects) {
            SubjectSchedule subjectAndSchedule = this.getSubjectSchedule(s, term);
            if (s.isComposed()) {
                Collection<Subject> coRequirements = this.getSubjectsByCode(s.getCourseCode(), s.getCurriculumCode(), s.getCoRequirementsList());
                Collection<SubjectSchedule> coRequirementsSchedule = coRequirements.stream().map(subj -> this.getSubjectSchedule(subj, term)).collect(Collectors.toList());
                studentPreEnrollment.enrollSubject(subjectAndSchedule, coRequirementsSchedule);
            } else {
                studentPreEnrollment.enrollSubject(subjectAndSchedule);
            }
        }
    }

    private SubjectSchedule getSubjectSchedule(Subject subject, String term) {
        String courseCode = subject.getCourseCode();
        String curriculumCode = subject.getCurriculumCode();
        String subjectCode = subject.getSubjectCode();
        SubjectSchedule subjectSchedule = this.scheduleCache.get(new SubjectScheduleKey(courseCode, curriculumCode, subjectCode, term));
        if (subjectSchedule == null) return this.scheduleCache.get(new SubjectScheduleKey(courseCode, curriculumCode, "1411311", term));
        return subjectSchedule;
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

    public SubjectsDemandResponse getDemand(String courseCode, String curriculumCode, String term) throws InvalidParameterException {
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

    private Collection<DetailedSubjectDemand> getDetailedSubjectDemand(String courseCode, String curriculumCode, String term) throws InvalidParameterException {
        PreEnrollmentsResponse preEnrollments = this.getActivesPreEnrollments(courseCode, curriculumCode, term);
        Collection<DetailedSubjectDemand> detailedDemand = new TreeSet<>();

        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getMandatoryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getComplementaryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getOptionalDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getElectiveDemand());

        return detailedDemand;
    }

    private void cacheSubjects(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<Subject> allSubjects = this.dataAccessFacade.getAllSubjects(courseCode, curriculumCode);
        this.subjectsCache = new HashMap<>();
        for (Subject subject : allSubjects) {
            SubjectKey key = new SubjectKey(courseCode, curriculumCode, subject.getSubjectCode());
            this.subjectsCache.put(key, subject);
        }
    }

    private void cacheSchedule(String courseCode, String curriculumCode, String term) {
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
                    LOGGER.error(Messages.INVALID_SUBJECT);
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
                SubjectSchedule subjectAndSchedule = this.getSubjectSchedule(subject, term);
                PreEnrollmentUtil.filterAvailableClasses(subjectAndSchedule);
                PreEnrollmentUtil.filterCompletedCoRequirements(curriculumCode, courseCode, subject, studentCurriculumProgress);
                availableSubjects.add(subject);
            }
        }

        Comparator<Subject> orderByIdealTerm = Comparator.comparingInt(Subject::getIdealTerm);
        List<Subject> availableSubjectsList = new ArrayList<>(availableSubjects);
        availableSubjectsList.sort(orderByIdealTerm);

        return availableSubjectsList;
    }

    private Subject getSubject(String courseCode, String curriculumCode, String subjectCode) throws InvalidParameterException {
        Subject subject = this.subjectsCache.get(new SubjectKey(courseCode, curriculumCode, subjectCode));
        if (subject == null) throw new InvalidParameterException(Messages.INVALID_SUBJECT);
        return subject;
    }
}
