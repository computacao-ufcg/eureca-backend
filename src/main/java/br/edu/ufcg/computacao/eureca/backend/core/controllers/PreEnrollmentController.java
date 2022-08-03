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

        StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
        // Creates list of available and prioritized subjects, based on request input
        PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, curriculumCode,
                term, studentProgress, optionalPriorityList, electivePriorityList, complementaryPriorityList,
                mandatoryPriorityList);
        // Computes the actual preEnrollment
        StudentPreEnrollment studentPreEnrollment = this.getStudentPreEnrollment(courseCode, curriculumCode,
                term, studentRegistration, studentProgress, numCredits, preEnrollmentData);
        // Empties cache, so that new request will compute preEnrollment from fresh data
        this.releaseSubjectAndScheduleCaches();
        return studentPreEnrollment;
    }

    public PreEnrollments getActivesPreEnrollments(String courseCode, String curriculumCode, String term)
            throws EurecaException {
        NavigableSet<Student> actives = new TreeSet(this.dataAccessFacade.getAllActives(courseCode, curriculumCode)).descendingSet();

        Collection<String> activesRegistrations = actives.stream().map(student ->
                student.getRegistration().getRegistration()).collect(Collectors.toList());
        Collection<StudentPreEnrollment> activesPreEnrollments = new HashSet<>();

        for (String studentRegistration : activesRegistrations) {
            LOGGER.debug(String.format(Messages.PRE_ENROLLING_S, studentRegistration));

            StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
            // Creates list of available subjects
            PreEnrollmentData preEnrollmentData = this.getPreEnrollmentData(courseCode, curriculumCode, term, studentProgress);
            // Computes the actual preEnrollment for a student (cached information is updated to decrease availability
            // of places on preEnrolled classes)
            StudentPreEnrollment preEnrollmentResponse = this.getStudentPreEnrollment(courseCode, curriculumCode,
                    term, studentRegistration, studentProgress, null, preEnrollmentData);
            activesPreEnrollments.add(preEnrollmentResponse);
            // Cache might have been changed to remove already completed co-requirements
            // We backtrack the cache before enrolling a new student
            this.backtrackScheduleCache(courseCode, curriculumCode);
        }

        SubjectDemandSummary subjectDemandSummary = this.getSubjectDemandSummary(courseCode, curriculumCode, term,
                activesPreEnrollments);
        // Empties cache, so that new requests will compute preEnrollment from fresh data
        this.releaseSubjectAndScheduleCaches();

        return new PreEnrollments(activesPreEnrollments, subjectDemandSummary);
    }

    private SubjectDemandSummary getSubjectDemandSummary(String courseCode, String curriculumCode, String term,
                                                         Collection<StudentPreEnrollment> preEnrollments) {
        Collection<DetailedSubjectDemand> mandatoryDemand = this.getSubjectDemand(courseCode, curriculumCode,
                "M", preEnrollments);
        Collection<DetailedSubjectDemand> optionalDemand = this.getSubjectDemand(courseCode, curriculumCode,
                "O", preEnrollments);
        Collection<DetailedSubjectDemand> complementaryDemand = this.getSubjectDemand(courseCode, curriculumCode,
                "C", preEnrollments);
        Collection<DetailedSubjectDemand> electiveDemand = this.getSubjectDemand(courseCode, curriculumCode,
                "E", preEnrollments);

        return new SubjectDemandSummary(mandatoryDemand, optionalDemand, complementaryDemand, electiveDemand);
    }

    private Collection<DetailedSubjectDemand> getSubjectDemand(String courseCode, String curriculumCode,
                                         String subjectType, Collection<StudentPreEnrollment> preEnrollments) {
        Collection<DetailedSubjectDemand> response = new ArrayList<>();
        Map<Subject, Map<Integer, Integer>> subjectDemandByTerm = new HashMap<>();

        for (StudentPreEnrollment preEnrollment : preEnrollments) {
            Collection<String> proposedSubjectsCodes = preEnrollment.getSubjects().keySet();
            Collection<Subject> proposedSubjects = this.getSubjectsByCode(courseCode, curriculumCode,
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

    private PreEnrollmentData getPreEnrollmentData(String courseCode, String curriculumCode, String term,
                                                   StudentCurriculumProgress studentProgress) throws EurecaException {
        return this.getPreEnrollmentData(courseCode, curriculumCode, term, studentProgress, null,
                null, null, null);
    }

private PreEnrollmentData getPreEnrollmentData(String courseCode, String curriculumCode, String term,
                                                   StudentCurriculumProgress studentProgress, String optionalPriorityList,
                                                   String electivePriorityList, String complementaryPriorityList,
                                                   String mandatoryPriorityList) throws EurecaException {

                                                    Curriculum curriculum = this.dataAccessFacade.getCurriculum(courseCode, curriculumCode);
        StudentCurriculumProgress studentProgress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
        if (this.subjectsCache == null || this.subjectsCache.isEmpty()) {
            this.cacheSubjects(courseCode, curriculumCode);
            this.cacheSchedules(courseCode, curriculumCode, term);
        }

        List<Integer> idealsCredtis = this.getIdealCredits(curriculum, studentProgress, numCredits);
        List<Collection<Subject>> collectionsOfSubjects = this.getCollectionsOfSubjects(courseCode, curriculumCode, term, studentProgress, optionalPriorityList, electivePriorityList, mandatoryPriorityList);
        List<Collection<SubjectSchedule>> collectionsOfSubjectSchedule = this.getCollectionsOfSubjectsSchedule(collectionsOfSubjects, term);

        

    return new PreEnrollmentData(studentRegistration, term, idealsCredtis.get(0), idealsCredtis.get(1), idealsCredtis.get(2), idealsCredtis.get(3), idealsCredtis.get(4),
    collectionsOfSubjectSchedule.get(0), collectionsOfSubjectSchedule.get(1), collectionsOfSubjectSchedule.get(2), collectionsOfSubjectSchedule.get(3),
    collectionsOfSubjectSchedule.get(4), collectionsOfSubjectSchedule.get(5), collectionsOfSubjectSchedule.get(6));
    

                                                   }

    private List<Integer> getIdealCredits( Curriculum curriculum, StudentCurriculumProgress studentProgress, Integer numCredits) {
        List<Integer> idealCredits = new ArrayList<Integer>();
        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, studentProgress);
        int nextTerm = PreEnrollmentUtil.getNextTerm(actualTerm, studentProgress.getEnrolledCredits(), curriculum.getMinNumberOfTerms());

        Map<SubjectType, Integer> idealCreditsMap = PreEnrollmentUtil.getIdealCreditsPerSubjectType(curriculum, numCredits, nextTerm);

        idealCredits.add(nextTerm);
        idealCredits.add( idealCreditsMap.get(SubjectType.MANDATORY));
        idealCredits.add( idealCreditsMap.get(SubjectType.OPTIONAL));
        idealCredits.add( idealCreditsMap.get(SubjectType.COMPLEMENTARY));
        idealCredits.add( idealCreditsMap.get(SubjectType.ELECTIVE));

        return null;
    }
    
    private  List<Collection<Subject>> getCollectionsOfSubjects(String courseCode, String curriculumCode, String term,StudentCurriculumProgress studentProgress, String optionalPriorityList, String electivePriorityList, String mandatoryPriorityList) throws InvalidParameterException {
        

        List<Collection<Subject>> collection = new ArrayList<>();

        Map<SubjectType, Collection<Subject>> availableSubjectsGroupedByType = this.getSubjectsAndSchedulesAvailableForEnrollmentGroupedByType(courseCode, curriculumCode, term, studentProgress);
        collection.add(availableSubjectsGroupedByType.get(SubjectType.MANDATORY));
        collection.add(availableSubjectsGroupedByType.get(SubjectType.OPTIONAL));
        collection.add(availableSubjectsGroupedByType.get(SubjectType.COMPLEMENTARY));
        collection.add(availableSubjectsGroupedByType.get(SubjectType.ELECTIVE));
        
        collection.add(this.getPriorityList(courseCode, curriculumCode, optionalPriorityList));
        collection.add(this.getPriorityList(courseCode, curriculumCode, electivePriorityList));
        collection.add(this.getPriorityList(courseCode, curriculumCode, mandatoryPriorityList));

        return collection;
    }

    
    private List<Collection<SubjectSchedule>> getCollectionsOfSubjectsSchedule(List<Collection<Subject>> collection, String term){
        List<Collection<SubjectSchedule>> collectionSchedule = new ArrayList<>(); 

        collectionSchedule.add(this.getSubjectsSchedules(collection.get(0), term));
        collectionSchedule.add(this.getSubjectsSchedules(collection.get(1), term));
        collectionSchedule.add(this.getSubjectsSchedules(collection.get(2), term));
        collectionSchedule.add(this.getSubjectsSchedules(collection.get(3), term));

        collectionSchedule.add(this.getSubjectsSchedules(collection.get(4), term));
        collectionSchedule.add(this.getSubjectsSchedules(collection.get(5), term));
        collectionSchedule.add(this.getSubjectsSchedules(collection.get(6), term));

        return collectionSchedule;

    }

    private StudentPreEnrollment getStudentPreEnrollment(String courseCode, String curriculumCode, String term,
    String studentRegistration, StudentCurriculumProgress studentProgress, Integer maxCredits,
                                            PreEnrollmentData preEnrollmentData) throws EurecaException {

String studentRegistration = preEnrollmentData.getStudentRegistration();
String term = preEnrollmentData.getTerm();

List<Integer> idealsCredtis = this.getIdealCreditsByPreEnrollmentData(preEnrollmentData);
List<Collection<SubjectSchedule>> collectionsOfSubjectSchedule = this.getCollectionsOfSubjectsScheduleByPreEnrollmentData(preEnrollmentData);

StudentPreEnrollmentResponse studentPreEnrollment = new StudentPreEnrollmentResponse(studentRegistration, idealsCredtis.get(0), idealsCredtis.get(1), idealsCredtis.get(2), idealsCredtis.get(3),idealsCredtis.get(4) );

List<Collection<SubjectSchedule>> collectionsOfPriorizedSubjectSchedule = this.getCollectionsOfPriorizedSubjectsScheduleByPreEnrollmentData(collectionsOfSubjectSchedule);

this.enrollMandatorySubjectsUntilConflict(studentPreEnrollment, collectionsOfSubjectSchedule.get(0), term);

this.AnalysisOfstudentPreEnrollment(studentPreEnrollment, term, collectionsOfSubjectSchedule, collectionsOfPriorizedSubjectSchedule);

return studentPreEnrollment;
}

private void AnalysisOfstudentPreEnrollment(StudentPreEnrollmentResponse studentPreEnrollment, String term, List<Collection<SubjectSchedule>> collectionsOfSubjectSchedule, List<Collection<SubjectSchedule>> collectionsOfPriorizedSubjectSchedule){
if (!studentPreEnrollment.isMandatoryFull()) {
this.enrollSubjects(studentPreEnrollment, collectionsOfPriorizedSubjectSchedule.get(2), term);
}

if (!studentPreEnrollment.isMandatoryFull()) {
Collection<SubjectSchedule> mandatoryLeftovers = EurecaUtil.difference(collectionsOfSubjectSchedule.get(0), collectionsOfPriorizedSubjectSchedule.get(2));
this.enrollSubjects(studentPreEnrollment, mandatoryLeftovers, term);
}

if (!studentPreEnrollment.isComplementaryFull()) {
this.enrollSubjects(studentPreEnrollment, collectionsOfSubjectSchedule.get(2), term);
}

if (!studentPreEnrollment.isOptionalFull()) {
this.enrollSubjects(studentPreEnrollment, collectionsOfPriorizedSubjectSchedule.get(0), term);
this.enrollSubjects(studentPreEnrollment, collectionsOfSubjectSchedule.get(1), term);
}

if (!studentPreEnrollment.isElectiveFull()) {
this.enrollSubjects(studentPreEnrollment, collectionsOfPriorizedSubjectSchedule.get(1), term);
this.enrollSubjects(studentPreEnrollment, collectionsOfSubjectSchedule.get(3), term);
}

}

private List<Integer> getIdealCreditsByPreEnrollmentData(PreEnrollmentData preEnrollmentData){
List<Integer> idealCredits = new ArrayList<Integer>();

idealCredits.add(preEnrollmentData.getNextTerm());
idealCredits.add(preEnrollmentData.getIdealMandatoryCredits());
idealCredits.add(preEnrollmentData.getIdealOptionalCredits());
idealCredits.add(preEnrollmentData.getIdealComplementaryCredits());
idealCredits.add(preEnrollmentData.getIdealElectiveCredits());

return idealCredits;
}

private List<Collection<SubjectSchedule>> getCollectionsOfPriorizedSubjectsScheduleByPreEnrollmentData(List<Collection<SubjectSchedule>> collectionsOfSubjectSchedule){
List<Collection<SubjectSchedule>> collectionPriorizedSchedule = new ArrayList<>(); 

collectionPriorizedSchedule.add(PreEnrollmentUtil.excludeUnavailableSubjects(collectionsOfSubjectSchedule.get(4), collectionsOfSubjectSchedule.get(1)) );
collectionPriorizedSchedule.add(PreEnrollmentUtil.excludeUnavailableSubjects(collectionsOfSubjectSchedule.get(5), collectionsOfSubjectSchedule.get(3)));
collectionPriorizedSchedule.add(PreEnrollmentUtil.excludeUnavailableSubjects(collectionsOfSubjectSchedule.get(6), collectionsOfSubjectSchedule.get(0)));

return collectionPriorizedSchedule;

}

private List<Collection<SubjectSchedule>> getCollectionsOfSubjectsScheduleByPreEnrollmentData(PreEnrollmentData preEnrollmentData){
List<Collection<SubjectSchedule>> collectionSchedule = new ArrayList<>(); 

collectionSchedule.add(preEnrollmentData.getAvailableMandatorySubjects());
collectionSchedule.add(preEnrollmentData.getAvailableOptionalSubjects());
collectionSchedule.add(preEnrollmentData.getAvailableComplementarySubjects());
collectionSchedule.add(preEnrollmentData.getAvailableElectiveSubjects());

collectionSchedule.add(preEnrollmentData.getPrioritizedOptionalSubjects());
collectionSchedule.add(preEnrollmentData.getPrioritizedElectiveSubjects());
collectionSchedule.add(preEnrollmentData.getPrioritizedMandatorySubjects());

return collectionSchedule;

}


    private void loadSubjectAndScheduleCaches(String courseCode, String curriculumCode, String term) throws EurecaException {
        if (this.subjectsCache == null) {
            this.cacheSubjects(courseCode, curriculumCode);
            this.cacheSchedules(courseCode, curriculumCode, term);
        }
    }

    private void backtrackScheduleCache(String courseCode, String curriculumCode) throws EurecaException {
        this.cacheSubjects(courseCode, curriculumCode);
        this.scheduleCache.values().forEach(subjectSchedule -> {
            SubjectKey key = new SubjectKey(subjectSchedule.getSubject().getCourseCode(),
                    subjectSchedule.getSubject().getCurriculumCode(),
                    subjectSchedule.getSubject().getSubjectCode());
            Subject originalSubject = this.subjectsCache.get(key);
            subjectSchedule.setSubject(originalSubject);
        });
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
        Map<Integer, Collection<SubjectSchedule>> mandatorySubjectsGroupedByTerm = PreEnrollmentUtil.getSubjectsPerTerm(availableMandatorySubjects, SubjectType.MANDATORY);
        for (Integer termNumber : mandatorySubjectsGroupedByTerm.keySet()) {
            Collection<SubjectSchedule> termSubjects = mandatorySubjectsGroupedByTerm.get(termNumber);
            int totalTermCredits = PreEnrollmentUtil.getSubjectCreditsSum(termSubjects);

            if (totalTermCredits > studentPreEnrollment.getMaxMandatoryCredits() - studentPreEnrollment.getMandatoryCredits()) {
                break;
            }

            this.enrollSubjects(studentPreEnrollment, termSubjects, term);
        }
    }

    private void enrollSubjects(StudentPreEnrollment studentPreEnrollment, Collection<SubjectSchedule> availableSubjects,
                                String term) {
        for (SubjectSchedule subjectAndSchedule : availableSubjects) {
            Subject subject = subjectAndSchedule.getSubject();
            if (subject.isComposed()) {
                Collection<Subject> coRequirements = this.getSubjectsByCode(subject.getCourseCode(),
                        subject.getCurriculumCode(), subject.getCoRequirementsList());
                // get available schedules, considering the co-requirements
                Collection<SubjectSchedule> coRequirementsSchedule = this.getSubjectsSchedules(coRequirements, term);
                studentPreEnrollment.enrollSubject(subjectAndSchedule, coRequirementsSchedule);
            } else {
                studentPreEnrollment.enrollSubject(subjectAndSchedule);
            }
        }
    }

    // given a collection with subjects, returns a collection with subject and schedules
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
                LOGGER.error(Messages.INVALID_SUBJECT_IGNORING);
            }
        }
        return subjectsSchedules;
    }

    private SubjectSchedule getSubjectSchedule(String courseCode, String curriculumCode, String subjectCode,
                                               String term) throws InvalidParameterException {
        SubjectScheduleKey key = new SubjectScheduleKey(courseCode, curriculumCode, subjectCode, term);

        SubjectSchedule subjectSchedule = this.scheduleCache.get(key);
        if (subjectSchedule == null) {
            throw new InvalidParameterException(String.format(Messages.INVALID_SCHEDULE_S_S_S_S, courseCode, curriculumCode, subjectCode, term));
        }
        return subjectSchedule;
    }

    private Collection<Subject> getSubjectsByCode(String courseCode, String curriculumCode, Collection<String>
            subjectCodes) {
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

    private Collection<DetailedSubjectDemand> getDetailedSubjectDemand(String courseCode, String curriculumCode,
                                                                       String term) throws EurecaException {
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
            priorityList = priorityList.stream().sorted(Comparator.comparingInt(Subject::getIdealTerm)).
                    collect(Collectors.toList());
        }
        return priorityList;
    }

    public Map<SubjectType, Collection<Subject>> getSubjectsAndSchedulesAvailableForEnrollmentGroupedByType(
            String courseCode, String curriculumCode, String term, StudentCurriculumProgress studentCurriculumProgress)
            throws InvalidParameterException {
        Map<SubjectType, Collection<Subject>> groupedSubjects = new TreeMap<>();
        Collection<Subject> availableSubjects = this.getSubjectsAvailableForEnrollment(courseCode, curriculumCode, term,
                studentCurriculumProgress);

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

    private Collection<Subject> getSubjectsAvailableForEnrollment(String courseCode, String curriculumCode, String term,
                                StudentCurriculumProgress studentCurriculumProgress) throws InvalidParameterException {
        Collection<String> concludedSubjectsCode = this.getConcludedSubjects(studentCurriculumProgress);
        Set<Subject> availableSubjects = new HashSet<>();
        Collection<String> subjectCodes = this.subjectsCache.values().stream().map(Subject::getSubjectCode).
                collect(Collectors.toList());

        for (String subjectCode : subjectCodes) {
            Subject subject = this.getSubject(courseCode, curriculumCode, subjectCode);
            if (!concludedSubjectsCode.contains(subjectCode) && concludedSubjectsCode.
                    containsAll(subject.getPreRequirementsList())) {
                try {
                    SubjectSchedule subjectAndSchedule = this.getSubjectSchedule(courseCode, curriculumCode,
                            subjectCode, term);
                    PreEnrollmentUtil.sanitizeSubject(courseCode, curriculumCode, subjectAndSchedule,
                            studentCurriculumProgress);
                    availableSubjects.add(subjectAndSchedule.getSubject());
                } catch (InvalidParameterException e) {
                    LOGGER.debug(String.format(Messages.INVALID_SCHEDULE_S_S_S_S, courseCode, curriculumCode,
                            subjectCode, term));
                }
            }
        }

        Comparator<Subject> orderByIdealTermThenCredits = Comparator.comparingInt(Subject::getIdealTerm);
        List<Subject> availableSubjectsList = new ArrayList<>(availableSubjects);
        availableSubjectsList.sort(orderByIdealTermThenCredits);

        return availableSubjectsList;
    }

    private Subject getSubject(String courseCode, String curriculumCode, String subjectCode)
            throws InvalidParameterException {
        Subject subject = this.subjectsCache.get(new SubjectKey(courseCode, curriculumCode, subjectCode));
        if (subject == null) {
            throw new InvalidParameterException(Messages.INVALID_SUBJECT_IGNORING);
        }
        return subject;
    }

    private boolean isPossibleGraduate(StudentPreEnrollment studentPreEnrollment, StudentCurriculumProgress
            studentProgress, Curriculum curriculum) {
        int additional = curriculum.getExceptionalAdditionalEnrolledCredits();
        int mandatoryNeeded = curriculum.getMinMandatoryCreditsNeeded();
        int complementaryNeeded = curriculum.getMinComplementaryCreditsNeeded();
        int optionalNeeded = curriculum.getMinOptionalCreditsNeeded();
        int electiveNeeded = curriculum.getMinElectiveCreditsNeeded();
        int mandatoryCompleted = studentProgress.getCompletedMandatoryCredits() +
                studentPreEnrollment.getMandatoryCredits();
        int complementaryCompleted = studentProgress.getCompletedComplementaryCredits() +
                studentPreEnrollment.getComplementaryCredits();
        int optionalCompleted = studentProgress.getCompletedOptionalCredits() +
                studentPreEnrollment.getOptionalCredits();
        int electiveCompleted = studentProgress.getCompletedElectiveCredits() +
                studentPreEnrollment.getElectiveCredits();
        int mandatoryDeficit = (mandatoryCompleted >= mandatoryNeeded ? 0 : 2);
        int complementaryDeficit = (complementaryCompleted >= complementaryNeeded ? 0 : 2);
        int optionalDeficit = ((optionalCompleted + additional) < optionalNeeded ? 2 :
                (optionalCompleted >= optionalNeeded ? 0 : 1));
        int electiveDeficit = ((electiveCompleted + additional) < electiveNeeded ? 2 :
                (electiveCompleted >= electiveNeeded ? 0 : 1));
        return ((mandatoryDeficit + complementaryDeficit + optionalDeficit + electiveDeficit) <= 1);
    }
}
