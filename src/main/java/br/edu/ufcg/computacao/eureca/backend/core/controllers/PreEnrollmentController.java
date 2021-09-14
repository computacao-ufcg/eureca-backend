package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentPreEnrollment;
import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;

import java.util.Collection;

public class PreEnrollmentController {

    private DataAccessFacade dataAccessFacade;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentPreEnrollment createStudentPreEnrollment(String studentRegistration, String courseCode, String curriculumCode) throws InvalidParameterException {
        StudentPreEnrollment studentPreEnrollment = new StudentPreEnrollment(studentRegistration, 24);
        Collection<Subject> availableMandatorySubjects = this.dataAccessFacade.getMandatorySubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        Collection<Subject> availableComplementarySubjects = this.dataAccessFacade.getComplementarySubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        Collection<Subject> availableElectiveSubjects = this.dataAccessFacade.getElectiveSubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        Collection<Subject> availableOptionalSubjects = this.dataAccessFacade.getOptionalSubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);

        while(!studentPreEnrollment.isFull()) {
            for (Subject s : availableMandatorySubjects)
                studentPreEnrollment.addSubject(s);

            for (Subject s : availableOptionalSubjects)
                studentPreEnrollment.addSubject(s);

            for (Subject s : availableComplementarySubjects)
                studentPreEnrollment.addSubject(s);

            for (Subject s : availableElectiveSubjects)
                studentPreEnrollment.addSubject(s);

            if (studentPreEnrollment.getSubjects().isEmpty()) break;
        }

        return studentPreEnrollment;
    }
}
