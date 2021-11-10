package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class PreEnrollmentUtil {

    public static Map<Integer, Collection<Subject>> getSubjectsGroupedByTermAndType(Collection<Subject> subjects, SubjectType type) {
        Map<Integer, Map<SubjectType, Collection<Subject>>> groupedSubjects = getSubjectsGroupedByTerm(subjects);
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

    private static Map<Integer, Map<SubjectType, Collection<Subject>>> getSubjectsGroupedByTerm(Collection<Subject> subjects) {
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

    public static int getSubjectCreditsSum(Collection<Subject> subjectsSchedules) {
        int sum = 0;
        for (Subject subject : subjectsSchedules) {
            sum += subject.getCredits();
        }
        return sum;
    }

    public static Collection<Subject> excludeUnavailableSubjects(Collection<Subject> prioritizedSubjects, Collection<Subject> availableSubjects) {
        return EurecaUtil.intersection(prioritizedSubjects, availableSubjects);
    }

    public static void filterAvailableClasses(SubjectSchedule subjectAndSchedule) {
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

    public static void filterCompletedCoRequirements(String curriculumCode, String courseCode, Subject subject, StudentCurriculumProgress progress) {
        Collection<SubjectKey> completedSubjects = progress.getCompleted();
        Collection<SubjectKey> coRequirements = subject.getCoRequirementsList().stream().map(subjectCode -> new SubjectKey(courseCode, curriculumCode, subjectCode)).collect(Collectors.toList());
        Collection<SubjectKey> availableCoRequirements = EurecaUtil.difference(coRequirements, completedSubjects);
        Collection<String> availableCoRequirementsCode = availableCoRequirements.stream().map(SubjectKey::getSubjectCode).collect(Collectors.toSet());
        subject.setCoRequirementsList(availableCoRequirementsCode);
    }

    public static Map<SubjectType, Integer> getIdealCreditsPerSubjectType(Curriculum curriculum, Integer maxCredits, int nextTerm) {
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

    public static int getActualTerm(Curriculum curriculum, StudentCurriculumProgress progress) {
        int accumulatedCredits = progress.getCompletedCredits();
        for (int i = 1; i < curriculum.getMinNumberOfTerms() + 1; i++) {
            if (accumulatedCredits <= curriculum.getExpectedMinAccumulatedCredits(i)) return i;
        }
        return curriculum.getMinNumberOfTerms();
    }

    public static int getNextTerm(int actualTerm, int enrolledCredits, int minTerms) {
        int nextTerm = actualTerm + (enrolledCredits > 0 ? 1 : 0);
        return (Math.min(nextTerm, minTerms));
    }
}
