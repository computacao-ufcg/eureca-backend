package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class PreEnrollmentUtil {

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

    public static Map<SubjectType, Integer> getIdealCreditsPerSubjectType(Curriculum curriculum, Integer maxCredits, int nextTerm) {
        Map<SubjectType, Integer> idealCredits = new HashMap<>();
        int idealMandatoryCredits = curriculum.getIdealMandatoryCredits(nextTerm);
        int idealOptionalCredits = curriculum.getIdealOptionalCredits(nextTerm);
        int idealComplementaryCredits = curriculum.getIdealComplementaryCredits(nextTerm);
        int idealElectiveCredits = curriculum.getIdealElectiveCredits(nextTerm);

        if (maxCredits != null) {
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
            if (accumulatedCredits <= curriculum.getExpectedMinAccumulatedCredits(i)) {
                return i;
            }
        }
        return curriculum.getMinNumberOfTerms();
    }

    public static int getNextTerm(int actualTerm, int enrolledCredits, int minTerms) {
        int nextTerm = actualTerm + (enrolledCredits > 0 ? 1 : 0);
        return (Math.min(nextTerm, minTerms));
    }
}
