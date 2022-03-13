package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.core.controllers.PreEnrollmentController;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class PreEnrollmentUtil {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollmentUtil.class);

    // retorna as disciplinas agrupadas por período e tipo
    public static Map<Integer, Collection<SubjectSchedule>> getSubjectsPerTerm(Collection<SubjectSchedule> subjects, SubjectType type) {
        Map<Integer, Map<SubjectType, Collection<SubjectSchedule>>> groupedSubjects = getSubjectsPerTermPerType(subjects);
        Map<Integer, Collection<SubjectSchedule>> groupedSubjectsByType = new TreeMap<>();

        for (Integer term : groupedSubjects.keySet()) {
            Map<SubjectType, Collection<SubjectSchedule>> termSubjects = groupedSubjects.get(term);
            Collection<SubjectSchedule> typeSubjects = termSubjects.get(type);
            if (typeSubjects != null) {
                groupedSubjectsByType.put(term, typeSubjects);
            }
        }
        return groupedSubjectsByType;
    }

    private static Map<Integer, Map<SubjectType, Collection<SubjectSchedule>>> getSubjectsPerTermPerType(Collection<SubjectSchedule> subjects) {
        Map<Integer, Map<SubjectType, Collection<SubjectSchedule>>> groupedSubjects = new TreeMap<>();

        for (SubjectSchedule subjectAndSchedule : subjects) {
            Subject subject = subjectAndSchedule.getSubject();
            int term = subject.getIdealTerm();
            if (!groupedSubjects.containsKey(term)) {
                groupedSubjects.put(term, new HashMap<>());
            }

            Map<SubjectType, Collection<SubjectSchedule>> subjectsByTerm = groupedSubjects.get(term);
            SubjectType subjectType = EurecaUtil.getSubjectType(subject);
            if (!subjectsByTerm.containsKey(subjectType)) {
                subjectsByTerm.put(subjectType, new ArrayList<>());
            }

            subjectsByTerm.get(subjectType).add(subjectAndSchedule);
            groupedSubjects.put(term, subjectsByTerm);
        }

        return groupedSubjects;
    }

    public static int getSubjectCreditsSum(Collection<SubjectSchedule> subjectsSchedules) {
        int sum = 0;
        for (SubjectSchedule subjectAndSchedule : subjectsSchedules) {
            sum += subjectAndSchedule.getSubject().getCredits();
        }
        return sum;
    }

    public static Collection<SubjectSchedule> excludeUnavailableSubjects(Collection<SubjectSchedule> prioritizedSubjects, Collection<SubjectSchedule> availableSubjects) {
        return EurecaUtil.intersection(prioritizedSubjects, availableSubjects);
    }

    public static void sanitizeSubject(String courseCode, String curriculumCode, SubjectSchedule subjectAndSchedule, StudentCurriculumProgress progress) {
        filterAvailableClasses(subjectAndSchedule);
        filterCompletedCoRequirements(curriculumCode, courseCode, subjectAndSchedule.getSubject(), progress);
    }

    private static void filterAvailableClasses(SubjectSchedule subjectAndSchedule) {
        Map<String, Schedule> updatedSchedules = new HashMap<>(subjectAndSchedule.getSchedules());
        Set<Map.Entry<String, Schedule>> entrySet = subjectAndSchedule.getSchedules().entrySet();

        for (Map.Entry<String, Schedule> entry : entrySet) {
            String classCode = entry.getKey();
            Schedule schedule = entry.getValue();
            if (schedule.getAvailability() <= 0) {
                updatedSchedules.remove(classCode);
            }
        }
        subjectAndSchedule.setSchedules(updatedSchedules);
    }

    private static void filterCompletedCoRequirements(String curriculumCode, String courseCode, Subject subject, StudentCurriculumProgress progress) {
        Collection<SubjectKey> completedSubjects = progress.getCompleted();
        Collection<SubjectKey> coRequirements = subject.getCoRequirementsList().stream().map(subjectCode -> new SubjectKey(courseCode, curriculumCode, subjectCode)).collect(Collectors.toList());
        Collection<SubjectKey> availableCoRequirements = EurecaUtil.difference(coRequirements, completedSubjects);
        Collection<String> availableCoRequirementsCode = availableCoRequirements.stream().map(SubjectKey::getSubjectCode).collect(Collectors.toSet());
        subject.setCoRequirementsList(availableCoRequirementsCode);
    }

    public static Map<SubjectType, Integer> getIdealCreditsPerSubjectType(Curriculum curriculum,
                                                       StudentCurriculumProgress studentProgress, Integer maxCredits) {
        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, studentProgress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Map<SubjectType, Integer> idealCredits = new HashMap<>();
        // Computes the number of credits that are short from the ideal, per type of subject
        int missingMandatoryCredits = Math.max(0, (curriculum.getTargetMandatoryCredits(actualTerm) -
                studentProgress.getCompletedMandatoryCredits()));
        int missingComplementaryCredits = Math.max(0, (curriculum.getTargetComplementaryCredits(actualTerm) -
                studentProgress.getCompletedComplementaryCredits()));
        int missingOptionalCredits = Math.max(0, (curriculum.getTargetOptionalCredits(actualTerm) -
                studentProgress.getCompletedOptionalCredits()));
        int missingElectiveCredits = Math.max(0, (curriculum.getTargetElectiveCredits(actualTerm) -
                studentProgress.getCompletedElectiveCredits()));
        // Computes how many credits are still needed, per type of subject
        int leftMandatoryCredits = Math.max((curriculum.getMinMandatoryCreditsNeeded() -
                studentProgress.getCompletedMandatoryCredits()), 0);
        int leftOptionalCredits = Math.max((curriculum.getMinOptionalCreditsNeeded() -
                studentProgress.getCompletedOptionalCredits()), 0);
        int leftComplementaryCredits = Math.max((curriculum.getMinComplementaryCreditsNeeded() -
                studentProgress.getCompletedComplementaryCredits()), 0);
        int leftElectiveCredits = Math.max((curriculum.getMinElectiveCreditsNeeded() -
                studentProgress.getCompletedElectiveCredits()), 0);
        // The number of ideal credits is such that does not over enroll (considering credits left), and tries to
        // enroll not less than what is ideally recommended, but allows overruling the ideal number of credits
        // suggested form the current term, when the student is lacking behind; finally the number of ideal credits
        // is always limited by maxCredits
        int idealMandatoryCredits = Math.min(leftMandatoryCredits, Math.min(maxCredits,
                (missingMandatoryCredits + curriculum.getIdealMandatoryCredits(nextTerm))));
        int idealOptionalCredits = Math.min(leftOptionalCredits, Math.min(maxCredits,
                (missingOptionalCredits + curriculum.getIdealOptionalCredits(nextTerm))));
        int idealComplementaryCredits = Math.min(leftComplementaryCredits, Math.min(maxCredits,
                (missingComplementaryCredits + curriculum.getIdealComplementaryCredits(nextTerm))));
        int idealElectiveCredits = Math.min(leftElectiveCredits, Math.min(maxCredits,
                (missingElectiveCredits + curriculum.getIdealElectiveCredits(nextTerm))));

        idealCredits.put(SubjectType.MANDATORY, idealMandatoryCredits);
        idealCredits.put(SubjectType.OPTIONAL, idealOptionalCredits);
        idealCredits.put(SubjectType.COMPLEMENTARY, idealComplementaryCredits);
        idealCredits.put(SubjectType.ELECTIVE, idealElectiveCredits);

        return idealCredits;
    }

    public static int getActualTerm(Curriculum curriculum, StudentCurriculumProgress progress) {
        int accumulatedCredits = progress.getCompletedCredits();
        for (int i = 0; i < curriculum.getMinNumberOfTerms(); i++) {
            if (accumulatedCredits <= curriculum.getExpectedMinAccumulatedCredits(i)) return i;
        }
        return curriculum.getMinNumberOfTerms();
    }
}
