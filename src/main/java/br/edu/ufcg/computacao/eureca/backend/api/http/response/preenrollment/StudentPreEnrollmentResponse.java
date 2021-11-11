package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.core.models.Schedule;
import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectSchedule;

import java.util.*;

public class StudentPreEnrollmentResponse {

    private String studentRegistration;
    private Map<Subject, Schedule> subjects;
    private int term;
    private int maxCredits;
    private int maxMandatoryCredits;
    private int mandatoryCredits;
    private int maxOptionalCredits;
    private int optionalCredits;
    private int maxComplementaryCredits;
    private int complementaryCredits;
    private int maxElectiveCredits;
    private int electiveCredits;

    public StudentPreEnrollmentResponse(String studentRegistration, int term, int maxMandatoryCredits, int maxOptionalCredits, int maxComplementaryCredits, int maxElectiveCredits) {
        this.studentRegistration = studentRegistration;
        this.subjects = new HashMap<>();
        this.term = term;
        this.maxMandatoryCredits = maxMandatoryCredits;
        this.maxOptionalCredits = maxOptionalCredits;
        this.maxComplementaryCredits = maxComplementaryCredits;
        this.maxElectiveCredits = maxElectiveCredits;
        this.maxCredits = this.maxMandatoryCredits + this.maxOptionalCredits + this.maxComplementaryCredits + this.maxElectiveCredits;
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

    public Map<Subject, Schedule> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<Subject, Schedule> subjects) {
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
                isPossibleToEnrollCredits = this.isPossibleToEnrollMandatoryCredits(subjectAndSchedule, coRequirements);
                if (isPossibleToEnrollCredits) {
                    this.enrollMandatorySubject(subjectAndSchedule, coRequirements);
                }
                break;
            case "O":
                isPossibleToEnrollCredits = this.isPossibleToEnrollOptionalCredits(subjectAndSchedule, coRequirements);
                if (isPossibleToEnrollCredits) {
                    this.enrollOptionalSubject(subjectAndSchedule, coRequirements);
                }
                break;
            case "C":
                isPossibleToEnrollCredits = this.isPossibleToEnrollComplementaryCredits(subjectAndSchedule, coRequirements);
                if (isPossibleToEnrollCredits) {
                    this.enrollComplementarySubject(subjectAndSchedule, coRequirements);
                }
                break;
            case "E":
                isPossibleToEnrollCredits = this.isPossibleToEnrollElectiveCredits(subjectAndSchedule, coRequirements);
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
        boolean haveCoRequirementConflict = false;
        Map<Subject, Schedule> availableCoRequirementsSchedules = new HashMap<>();

        for (SubjectSchedule coRequirementSchedule : coRequirements) {
            Subject coRequirement = coRequirementSchedule.getSubject();
            List<Schedule> availableCoRequirementSchedule = this.getAvailableSchedules(coRequirementSchedule);
            haveCoRequirementConflict = availableCoRequirementSchedule.isEmpty();
            if (haveCoRequirementConflict) break;

            availableCoRequirementsSchedules.put(coRequirement, availableCoRequirementSchedule.get(0));
        }

        Subject subject = subjectAndSchedule.getSubject();
        List<Schedule> availableSchedules = this.getAvailableSchedules(subjectAndSchedule);

        boolean haveConflict = availableSchedules.isEmpty() || haveCoRequirementConflict;
        if (!haveConflict) {
            Schedule firstAvailableSchedule = availableSchedules.get(0);
            this.subjects.put(subject, firstAvailableSchedule);
            this.subjects.putAll(availableCoRequirementsSchedules);
            firstAvailableSchedule.decrementAvailability();
        }

        return !haveConflict;
    }

    private void enrollMandatorySubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean enrolled = this.enrollSubjectAndSchedule(subjectAndSchedule, coRequirements);
        if (enrolled) {
            int newCredits = this.getNewCredits(subjectAndSchedule, coRequirements);
            this.mandatoryCredits += newCredits;
        }
    }

    private void enrollOptionalSubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean enrolled = this.enrollSubjectAndSchedule(subjectAndSchedule, coRequirements);
        if (enrolled) {
            int newCredits = this.getNewCredits(subjectAndSchedule);
            this.optionalCredits += newCredits;
        }
    }

    private void enrollComplementarySubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean enrolled = this.enrollSubjectAndSchedule(subjectAndSchedule, coRequirements);
        if (enrolled) {
            int newCredits = this.getNewCredits(subjectAndSchedule, coRequirements);
            this.complementaryCredits += newCredits;
        }
    }

    private void enrollElectiveSubject(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        boolean enrolled = this.enrollSubjectAndSchedule(subjectAndSchedule, coRequirements);
        if (enrolled) {
            int newCredits = this.getNewCredits(subjectAndSchedule, coRequirements);
            this.electiveCredits += newCredits;
        }
    }

    private List<Schedule> getAvailableSchedules(SubjectSchedule subjectAndSchedule) {
        List<Schedule> proposedSchedules = new ArrayList<>(subjectAndSchedule.getAllSchedules());
        for (Schedule enrolledSchedule : this.subjects.values()) {
            proposedSchedules.removeIf(enrolledSchedule::haveConflict);
        }
        return proposedSchedules;
    }

    private boolean isPossibleToEnrollMandatoryCredits(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        int credits = this.getNewCredits(subjectAndSchedule, coRequirements);
        return credits + this.mandatoryCredits <= this.maxMandatoryCredits;
    }

    private boolean isPossibleToEnrollOptionalCredits(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        int credits = this.getNewCredits(subjectAndSchedule, coRequirements);
        return credits + this.optionalCredits <= this.maxOptionalCredits;
    }

    private boolean isPossibleToEnrollComplementaryCredits(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        int credits = this.getNewCredits(subjectAndSchedule, coRequirements);
        return credits + this.complementaryCredits <= this.maxComplementaryCredits;
    }

    private boolean isPossibleToEnrollElectiveCredits(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        int credits = this.getNewCredits(subjectAndSchedule, coRequirements);
        return credits + this.electiveCredits <= this.maxElectiveCredits;
    }

    private int getNewCredits(SubjectSchedule subjectAndSchedule, Collection<SubjectSchedule> coRequirements) {
        return this.getNewCredits(subjectAndSchedule) + this.getCoRequirementsCredits(coRequirements);
    }

    private int getNewCredits(SubjectSchedule subjectAndSchedule) {
        return subjectAndSchedule.getSubject().getCredits();
    }
}
