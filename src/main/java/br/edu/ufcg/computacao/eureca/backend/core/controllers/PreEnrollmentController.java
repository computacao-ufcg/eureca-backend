package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.EurecaUtil;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class PreEnrollmentController {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollmentController.class);

    private DataAccessFacade dataAccessFacade;
    private Map<SubjectKey, Subject> subjectsCache;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentPreEnrollmentResponse getStudentPreEnrollment(String courseCode, String curriculumCode, String studentRegistration, Integer numCredits,
                                                                String optionalPriorityList, String electivePriorityList, String mandatoryPriorityList) throws InvalidParameterException {
        PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, curriculumCode, studentRegistration, numCredits, optionalPriorityList, electivePriorityList, mandatoryPriorityList);
        return this.getStudentPreEnrollment(preEnrollmentData);
    }

    public PreEnrollmentsResponse getActivesPreEnrollments(String courseCode, String curriculumCode) throws InvalidParameterException  {
        Collection<Student> actives = this.dataAccessFacade.getAllActives(courseCode, curriculumCode);
        Collection<String> activesRegistrations = actives.stream().map(student -> student.getRegistration().getRegistration()).collect(Collectors.toList());
        Collection<PreEnrollmentData> activesPreEnrollmentData = new HashSet<>();

        for (String studentRegistration : activesRegistrations) {
            activesPreEnrollmentData.add(this.getPreEnrollmentData(courseCode, curriculumCode, studentRegistration, null, null, null, null));
        }

        return this.getActivesPreEnrollment(activesPreEnrollmentData);
    }

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

    private PreEnrollmentData getPreEnrollmentData(String courseCode, String curriculumCode, String studentRegistration, Integer numCredits, String optionalPriorityList, String electivePriorityList, String mandatoryPriorityList) throws InvalidParameterException {
        Curriculum curriculum = this.dataAccessFacade.getCurriculum(courseCode, curriculumCode);
        StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
        if (this.subjectsCache == null || this.subjectsCache.isEmpty()) {
            this.cacheSubjects(courseCode, curriculumCode);
        }

        int actualTerm = this.getActualTerm(curriculum, studentProgress);
        int nextTerm = this.getNextTerm(actualTerm, studentProgress.getEnrolledCredits(), curriculum.getMinNumberOfTerms());

        Map<SubjectType, Integer> idealCreditsMap = this.getIdealCredits(curriculum, numCredits, nextTerm);
        int idealMandatoryCredits = idealCreditsMap.get(SubjectType.MANDATORY);
        int idealOptionalCredits = idealCreditsMap.get(SubjectType.OPTIONAL);
        int idealComplementaryCredits = idealCreditsMap.get(SubjectType.COMPLEMENTARY);
        int idealElectiveCredits = idealCreditsMap.get(SubjectType.ELECTIVE);

        Map<SubjectType, Collection<Subject>> availableSubjectsGroupedByType = this.getSubjectsAvailableForEnrollmentGroupedByType(courseCode, curriculumCode, studentProgress);
        Collection<Subject> availableMandatorySubjects = availableSubjectsGroupedByType.get(SubjectType.MANDATORY);
        Collection<Subject> availableOptionalSubjects = availableSubjectsGroupedByType.get(SubjectType.OPTIONAL);
        Collection<Subject> availableComplementarySubjects = availableSubjectsGroupedByType.get(SubjectType.COMPLEMENTARY);
        Collection<Subject> availableElectiveSubjects = availableSubjectsGroupedByType.get(SubjectType.ELECTIVE);

        Collection<Subject> prioritizedOptionalSubjects = this.getPriorityList(courseCode, curriculumCode, optionalPriorityList);
        Collection<Subject> prioritizedElectiveSubjects = this.getPriorityList(courseCode, curriculumCode, electivePriorityList);
        Collection<Subject> prioritizedMandatorySubjects = this.getPriorityList(courseCode, curriculumCode, mandatoryPriorityList);

        return new PreEnrollmentData(studentRegistration, nextTerm, idealMandatoryCredits, idealOptionalCredits, idealComplementaryCredits, idealElectiveCredits,
                availableMandatorySubjects, availableComplementarySubjects, availableOptionalSubjects, availableElectiveSubjects,
                prioritizedOptionalSubjects, prioritizedElectiveSubjects, prioritizedMandatorySubjects);

    }

    private StudentPreEnrollmentResponse getStudentPreEnrollment(PreEnrollmentData preEnrollmentData) throws InvalidParameterException {
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
        Map<Integer, Collection<Subject>> mandatorySubjectsGroupedByTerm = this.getSubjectsGroupedByTermAndType(availableMandatorySubjects, SubjectType.MANDATORY);
        for (Integer term : mandatorySubjectsGroupedByTerm.keySet()) {
            Collection<Subject> termSubjects = mandatorySubjectsGroupedByTerm.get(term);
            int totalTermCredits = this.getSubjectCreditsSum(termSubjects);

            if (totalTermCredits > studentPreEnrollment.getMaxMandatoryCredits() - studentPreEnrollment.getMandatoryCredits()) break;

            this.addSubjectsToPreEnrollment(studentPreEnrollment, termSubjects);
        }
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

    public SubjectsDemandResponse getDemand(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectDemand> demand = new TreeSet<>();
        Collection<DetailedSubjectDemand> detailedDemand = this.getDetailedSubjectDemand(courseCode, curriculumCode);

        detailedDemand.forEach(subject -> {
            String subjectCode = subject.getDemand().getSubjectCode();
            String subjectName = subject.getDemand().getSubjectName();
            int totalDemand = subject.getDemand().getTotalDemand();
            demand.add(new SubjectDemand(subjectCode, subjectName, totalDemand));
        });
        return new SubjectsDemandResponse(demand);
    }

    private Collection<DetailedSubjectDemand> getDetailedSubjectDemand(String courseCode, String curriculumCode) throws InvalidParameterException {
        PreEnrollmentsResponse preEnrollments = this.getActivesPreEnrollments(courseCode, curriculumCode);
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

    private int getActualTerm(Curriculum curriculum, StudentCurriculumProgress progress) {
        int accumulatedCredits = progress.getCompletedCredits();
        for (int i = 1; i < curriculum.getMinNumberOfTerms() + 1; i++) {
            if (accumulatedCredits <= curriculum.getExpectedMinAccumulatedCredits(i)) return i;
        }
        return curriculum.getMinNumberOfTerms();
    }

    private int getNextTerm(int actualTerm, int enrolledCredits, int minTerms) {
        int nextTerm = actualTerm + (enrolledCredits > 0 ? 1 : 0);
        return (Math.min(nextTerm, minTerms));
    }

    private Map<SubjectType, Integer> getIdealCredits(Curriculum curriculum, Integer maxCredits, int nextTerm) {
        Map<SubjectType, Integer> idealCredits = new HashMap<>();
        int idealMandatoryCredits = curriculum.getIdealMandatoryCredits(nextTerm);
        int idealOptionalCredits = 0;
        int idealComplementaryCredits = 0;
        int idealElectiveCredits = 0;

        if (maxCredits == null) {
            idealMandatoryCredits = curriculum.getIdealMandatoryCredits(nextTerm);
            idealOptionalCredits = curriculum.getIdealOptionalCredits(nextTerm);
            idealComplementaryCredits = curriculum.getIdealComplementaryCredits(nextTerm);
            idealElectiveCredits = curriculum.getIdealElectiveCredits(nextTerm);
        } else {
            idealMandatoryCredits = Math.min(idealMandatoryCredits, maxCredits);
            idealComplementaryCredits = Math.max(0, Math.min(idealComplementaryCredits, maxCredits - idealMandatoryCredits));
            idealOptionalCredits = Math.max(0, Math.min(idealOptionalCredits, maxCredits - (idealMandatoryCredits + idealComplementaryCredits)));
            idealElectiveCredits = Math.max(0, Math.min(idealElectiveCredits, maxCredits - (idealMandatoryCredits + idealComplementaryCredits + idealOptionalCredits)));
        }

        idealCredits.put(SubjectType.MANDATORY, idealMandatoryCredits);
        idealCredits.put(SubjectType.OPTIONAL, idealOptionalCredits);
        idealCredits.put(SubjectType.COMPLEMENTARY, idealComplementaryCredits);
        idealCredits.put(SubjectType.ELECTIVE, idealElectiveCredits);

        return idealCredits;
    }

    public Map<SubjectType, Collection<Subject>> getSubjectsAvailableForEnrollmentGroupedByType(String courseCode, String curriculumCode, StudentCurriculumProgress studentCurriculumProgress) throws InvalidParameterException {
        Map<SubjectType, Collection<Subject>> groupedSubjects = new TreeMap<>();
        Collection<Subject> availableSubjects = this.getSubjectsAvailableForEnrollment(courseCode, curriculumCode, studentCurriculumProgress);

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

    private Collection<Subject> getSubjectsAvailableForEnrollment(String courseCode, String curriculumCode, StudentCurriculumProgress studentCurriculumProgress) throws InvalidParameterException {
        Collection<String> ongoingSubjectsCode = studentCurriculumProgress.getOngoing().stream().map(SubjectKey::getSubjectCode).collect(Collectors.toList());
        Collection<String> concludedSubjectsCode = studentCurriculumProgress.getCompleted().stream().map(SubjectKey::getSubjectCode).collect(Collectors.toList());
        concludedSubjectsCode.addAll(ongoingSubjectsCode);

        Set<Subject> availableSubjects = new HashSet<>();
        Collection<String> subjectCodes = this.subjectsCache.values().stream().map(Subject::getSubjectCode).collect(Collectors.toList());

        for (String subjectCode : subjectCodes) {
            Subject subject = this.getSubject(courseCode, curriculumCode, subjectCode);
            if (!concludedSubjectsCode.contains(subjectCode) && concludedSubjectsCode.containsAll(subject.getPreRequirementsList())) {
                this.filterCompletedCoRequirements(curriculumCode, courseCode, subject, studentCurriculumProgress);
                availableSubjects.add(subject);
            }
        }

        Comparator<Subject> orderByIdealTerm = Comparator.comparingInt(Subject::getIdealTerm);
        List<Subject> availableSubjectsList = new ArrayList<>(availableSubjects);
        availableSubjectsList.sort(orderByIdealTerm);

        return availableSubjectsList;
    }

    private void filterCompletedCoRequirements(String curriculumCode, String courseCode, Subject subject, StudentCurriculumProgress progress) {
        Collection<SubjectKey> completedSubjects = progress.getCompleted();
        Collection<SubjectKey> coRequirements = subject.getCoRequirementsList().stream().map(subjectCode -> new SubjectKey(courseCode, curriculumCode, subjectCode)).collect(Collectors.toList());
        Collection<SubjectKey> availableCoRequirements = EurecaUtil.difference(coRequirements, completedSubjects);
        Collection<String> availableCoRequirementsCode = availableCoRequirements.stream().map(SubjectKey::getSubjectCode).collect(Collectors.toSet());
        subject.setCoRequirementsList(availableCoRequirementsCode);
    }

    private Subject getSubject(String courseCode, String curriculumCode, String subjectCode) throws InvalidParameterException {
        Subject subject = this.subjectsCache.get(new SubjectKey(courseCode, curriculumCode, subjectCode));
        if (subject == null) throw new InvalidParameterException(Messages.INVALID_SUBJECT);
        return subject;
    }
}
