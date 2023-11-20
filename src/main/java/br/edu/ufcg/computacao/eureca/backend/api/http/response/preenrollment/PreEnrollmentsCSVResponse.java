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
                        studentPreEnrollment.getMaxCredits(),
                        studentPreEnrollment.getMandatoryCredits(),
                        studentPreEnrollment.getOptionalCredits(),
                        studentPreEnrollment.getComplementaryCredits(),
                        studentPreEnrollment.getElectiveCredits(),
                        scheduleResponse.getSubjectName(),
                        scheduleResponse.getSchedule().getClassId()));
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
