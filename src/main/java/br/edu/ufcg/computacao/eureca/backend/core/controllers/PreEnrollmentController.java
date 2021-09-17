package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.StudentCurriculumProgress;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentPreEnrollment;
import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;

import java.util.Collection;
import java.util.List;

public class PreEnrollmentController {

    private DataAccessFacade dataAccessFacade;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentPreEnrollment createStudentPreEnrollment(String courseCode, String curriculumCode, String studentRegistration) throws InvalidParameterException {
        int idealCredits = this.dataAccessFacade.getStudentIdealCredits(courseCode, curriculumCode, studentRegistration);
        StudentPreEnrollment studentPreEnrollment = new StudentPreEnrollment(studentRegistration, idealCredits);

        List<Subject> availableMandatorySubjects = this.dataAccessFacade.getMandatorySubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        List<Subject> availableComplementarySubjects = this.dataAccessFacade.getComplementarySubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        List<Subject> availableElectiveSubjects = this.dataAccessFacade.getElectiveSubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        List<Subject> availableOptionalSubjects = this.dataAccessFacade.getOptionalSubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);

        while(!studentPreEnrollment.isFull()) {
            for (Subject s : availableMandatorySubjects)
                studentPreEnrollment.addSubject(s);

            if (!studentPreEnrollment.isFull()) {
                for (Subject s : availableOptionalSubjects)
                    studentPreEnrollment.addSubject(s);
            }

            if (!studentPreEnrollment.isFull()) {
                for (Subject s : availableComplementarySubjects)
                    studentPreEnrollment.addSubject(s);
            }

            if (!studentPreEnrollment.isFull()) {
                for (Subject s : availableElectiveSubjects)
                    studentPreEnrollment.addSubject(s);
            }

            if (studentPreEnrollment.getSubjects().isEmpty()) break;
        }

        return studentPreEnrollment;
    }
}
