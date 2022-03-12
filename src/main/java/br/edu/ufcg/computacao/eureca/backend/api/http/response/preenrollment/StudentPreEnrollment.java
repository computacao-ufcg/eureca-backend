package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.core.models.Schedule;
import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectSchedule;

import java.util.*;

public class StudentPreEnrollment {

    private String studentRegistration;
    private Map<String, ScheduleResponse> subjects;
    private int term;
    private int currentEnrolledCredits;
    private int maxCredits;
    private int maxMandatoryCredits;
    private int mandatoryCredits;
    private int maxOptionalCredits;
    private int optionalCredits;
    private int maxComplementaryCredits;
    private int complementaryCredits;
    private int maxElectiveCredits;
    private int electiveCredits;

    public StudentPreEnrollment(String studentRegistration, int term, Integer maxCredits, int maxMandatoryCredits,
                                int maxOptionalCredits, int maxComplementaryCredits, int maxElectiveCredits) {
        this.studentRegistration = studentRegistration;
        this.term = term;
        this.maxCredits = maxCredits;
        this.currentEnrolledCredits = 0;
        this.maxMandatoryCredits = maxMandatoryCredits;
        this.maxOptionalCredits = maxOptionalCredits;
        this.maxComplementaryCredits = maxComplementaryCredits;
        this.maxElectiveCredits = maxElectiveCredits;
        this.subjects = new HashMap<>();
    }

    public boolean isMandatoryFull() {
        return this.mandatoryCredits >= this.maxMandatoryCredits;
    }

    public boolean isOptionalFull() {
        return this.optionalCredits >= this.maxOptionalCredits;
    }

    public boolean isComplementaryFull() {
        return this.complementaryCredits >= this.maxComplementaryCredits;
    }

    public boolean isElectiveFull() {
        return this.electiveCredits >= this.maxElectiveCredits;
    }

    public String getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(String studentRegistration) {
        this.studentRegistration = studentRegistration;
    }

    public Map<String, ScheduleResponse> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<String, ScheduleResponse> subjects) {
        this.subjects = subjects;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(int maxCredits) {
        this.maxCredits = maxCredits;
    }

    public int getTotalCredits() {
        return mandatoryCredits + electiveCredits + optionalCredits + complementaryCredits;
    }

    public int getMaxMandatoryCredits() {
        return maxMandatoryCredits;
    }

    public void setMaxMandatoryCredits(int maxMandatoryCredits) {
        this.maxMandatoryCredits = maxMandatoryCredits;
    }

    public int getMaxOptionalCredits() {
        return maxOptionalCredits;
    }

    public void setMaxOptionalCredits(int maxOptionalCredits) {
        this.maxOptionalCredits = maxOptionalCredits;
    }

    public int getMaxComplementaryCredits() {
        return maxComplementaryCredits;
    }

    public void setMaxComplementaryCredits(int maxComplementaryCredits) {
        this.maxComplementaryCredits = maxComplementaryCredits;
    }

    public int getMaxElectiveCredits() {
        return maxElectiveCredits;
    }

    public void setMaxElectiveCredits(int maxElectiveCredits) {
        this.maxElectiveCredits = maxElectiveCredits;
    }

    public int getMandatoryCredits() {
        return mandatoryCredits;
    }

    public void setMandatoryCredits(int mandatoryCredits) {
        this.mandatoryCredits = mandatoryCredits;
    }

    public int getOptionalCredits() {
        return optionalCredits;
    }

    public void setOptionalCredits(int optionalCredits) {
        this.optionalCredits = optionalCredits;
    }

    public int getComplementaryCredits() {
        return complementaryCredits;
    }

    public void setComplementaryCredits(int complementaryCredits) {
        this.complementaryCredits = complementaryCredits;
    }

    public int getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(int electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public void enrollSubject(SubjectSchedule subjectSchedule) {
        this.enrollSubject(subjectSchedule, new ArrayList<>());
    }

    public void enrollSubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean isPossibleToEnrollCredits;

        switch (subjectAndSchedule.getSubject().getType()) {
            case "M":
                isPossibleToEnrollCredits = this.isPossibleToEnrollCredits(subjectAndSchedule, coRequirements,
                        this.mandatoryCredits, this.maxMandatoryCredits);
                if (isPossibleToEnrollCredits) {
                    this.enrollMandatorySubject(subjectAndSchedule, coRequirements);
                }
                break;
            case "O":
                isPossibleToEnrollCredits = this.isPossibleToEnrollCredits(subjectAndSchedule, coRequirements,
                        this.optionalCredits, this.maxOptionalCredits);
                if (isPossibleToEnrollCredits) {
                    this.enrollOptionalSubject(subjectAndSchedule, coRequirements);
                }
                break;
            case "C":
                isPossibleToEnrollCredits = this.isPossibleToEnrollCredits(subjectAndSchedule, coRequirements,
                        this.complementaryCredits, this.maxComplementaryCredits);
                if (isPossibleToEnrollCredits) {
                    this.enrollComplementarySubject(subjectAndSchedule, coRequirements);
                }
                break;
            case "E":
                isPossibleToEnrollCredits = this.isPossibleToEnrollCredits(subjectAndSchedule, coRequirements,
                        this.electiveCredits, this.maxElectiveCredits);
                if (isPossibleToEnrollCredits) {
                    this.enrollElectiveSubject(subjectAndSchedule, coRequirements);
                }
                break;
        }
    }

    private int getCoRequirementsCredits(Collection<SubjectSchedule> coRequirements) {
        int sum = 0;
        for (SubjectSchedule subjectSchedule : coRequirements)
            sum += subjectSchedule.getSubject().getCredits();
        return sum;
    }

    private boolean enrollSubjectAndSchedule(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean haveCoRequirementScheduleConflict = false;
        Map<String, ScheduleResponse> availableCoRequirementsSchedules = new HashMap<>();

        // verifies whether there is a schedule conflict with pre-requirements
        for (SubjectSchedule coRequirementSubjectAndSchedule : coRequirements) {
            Subject coRequirement = coRequirementSubjectAndSchedule.getSubject();
            List<Schedule> availableCoRequirementSchedule = this.getAvailableSchedules(coRequirementSubjectAndSchedule);
            haveCoRequirementScheduleConflict = availableCoRequirementSchedule.isEmpty();
            if (haveCoRequirementScheduleConflict) break;

            Schedule firstAvailableCoRequirementSchedule = availableCoRequirementSchedule.get(0);
            ScheduleResponse coRequirementSchedule = new ScheduleResponse(coRequirement.getName(), firstAvailableCoRequirementSchedule);
            availableCoRequirementsSchedules.put(coRequirement.getSubjectCode(), coRequirementSchedule);
        }

        Subject subject = subjectAndSchedule.getSubject();
        List<Schedule> availableSchedules = this.getAvailableSchedules(subjectAndSchedule);

        // only enrolls if it is possible to enroll both the subject and its co-requirements
        boolean haveScheduleConflict = availableSchedules.isEmpty() || haveCoRequirementScheduleConflict;
        if (!haveScheduleConflict) {
            Schedule firstAvailableSchedule = availableSchedules.get(0);
            ScheduleResponse schedule = new ScheduleResponse(subject.getName(), firstAvailableSchedule);
            this.subjects.put(subject.getSubjectCode(), schedule);
            this.subjects.putAll(availableCoRequirementsSchedules);
            firstAvailableSchedule.decrementAvailability();
            availableCoRequirementsSchedules.values().forEach(coRequirementSchedule -> {
                coRequirementSchedule.getSchedule().decrementAvailability();
            });
        }

        return !haveScheduleConflict;
    }

    private void enrollMandatorySubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean enrolled = this.enrollSubjectAndSchedule(subjectAndSchedule, coRequirements);
        if (enrolled) {
            int newCredits = this.getNewCredits(subjectAndSchedule, coRequirements);
            this.mandatoryCredits += newCredits;
            this.currentEnrolledCredits += newCredits;
        }
    }

    private void enrollOptionalSubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean enrolled = this.enrollSubjectAndSchedule(subjectAndSchedule, coRequirements);
        if (enrolled) {
            int newCredits = this.getNewCredits(subjectAndSchedule);
            this.optionalCredits += newCredits;
            this.currentEnrolledCredits += newCredits;
        }
    }

    private void enrollComplementarySubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean enrolled = this.enrollSubjectAndSchedule(subjectAndSchedule, coRequirements);
        if (enrolled) {
            int newCredits = this.getNewCredits(subjectAndSchedule, coRequirements);
            this.complementaryCredits += newCredits;
            this.currentEnrolledCredits += newCredits;
        }
    }

    private void enrollElectiveSubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean enrolled = this.enrollSubjectAndSchedule(subjectAndSchedule, coRequirements);
        if (enrolled) {
            int newCredits = this.getNewCredits(subjectAndSchedule, coRequirements);
            this.electiveCredits += newCredits;
            this.currentEnrolledCredits += newCredits;
        }
    }

    private List<Schedule> getAvailableSchedules(SubjectSchedule subjectAndSchedule) {
        List<Schedule> proposedSchedules = new ArrayList<>(subjectAndSchedule.getAllSchedules());
        // remove schedules/classes that conflict with already enrolled subjects
        for (ScheduleResponse enrolledSchedule : this.subjects.values()) {
            proposedSchedules.removeIf(schedule -> schedule.haveConflict(enrolledSchedule.getSchedule()));
        }
        return proposedSchedules;
    }

    private boolean isPossibleToEnrollCredits(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule>
            coRequirements, int creditsForType, int maxCreditsForType) {
        int newCredits = this.getNewCredits(subjectAndSchedule, coRequirements);
        int newCurrentEnrolledCredits = newCredits + this.currentEnrolledCredits;
        return (((creditsForType + newCredits) <= maxCreditsForType) && (newCurrentEnrolledCredits <= this.maxCredits));
    }

    private int getNewCredits(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        return subjectAndSchedule.getSubject().getCredits() + this.getCoRequirementsCredits(coRequirements);
    }

    private int getNewCredits(SubjectSchedule subjectAndSchedule) {
        return subjectAndSchedule.getSubject().getCredits();
    }
}
