package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.Collection;
import java.util.TreeSet;

public class PreEnrollmentsCSVResponse {

    private Collection<Enrollment> enrollments;

    public PreEnrollmentsCSVResponse(Collection<StudentPreEnrollment> activesPreEnrollment) {
        this.enrollments = new TreeSet<>();
        activesPreEnrollment.forEach(studentPreEnrollment -> {
            studentPreEnrollment.getSubjects().values().forEach(scheduleResponse -> {
                this.enrollments.add(new Enrollment(studentPreEnrollment.getStudentRegistration(),
                        studentPreEnrollment.getTerm(),
                        studentPreEnrollment.getMaxMandatoryCredits(),
                        studentPreEnrollment.getMandatoryCredits(),
                        studentPreEnrollment.getMaxOptionalCredits(),
                        studentPreEnrollment.getOptionalCredits(),
                        studentPreEnrollment.getMaxComplementaryCredits(),
                        studentPreEnrollment.getComplementaryCredits(),
                        studentPreEnrollment.getMaxElectiveCredits(),
                        studentPreEnrollment.getElectiveCredits(),
                        scheduleResponse.getSubjectName(),
                        scheduleResponse.getSchedule().getClassCode()));
            });
        });
    }

    public Collection<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Collection<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
