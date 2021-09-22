package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;

import java.util.*;

public class PreEnrollmentController {

    private DataAccessFacade dataAccessFacade;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentPreEnrollment createStudentPreEnrollment(String courseCode, String curriculumCode, String studentRegistration) throws InvalidParameterException {
        Curriculum curriculum = this.dataAccessFacade.getCurriculum(courseCode, curriculumCode);
        StudentCurriculumProgress progress = this.dataAccessFacade.getStudentCurriculumProgress(studentRegistration);
        // ToDo: mudar essa lógica para considerar o número ideal de créditos por tipo
        int idealNumberOfCredits = getIdealNumberOfCredits(curriculum, progress);
        int actualTerm = progress.getCompletedTerms() + (progress.getEnrolledCredits() > 0 ? 2 : 1);
        StudentPreEnrollment studentPreEnrollment = new StudentPreEnrollment(studentRegistration, actualTerm, idealNumberOfCredits);

        List<Subject> availableMandatorySubjects = this.dataAccessFacade.getMandatorySubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        List<Subject> availableComplementarySubjects = this.dataAccessFacade.getComplementarySubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        List<Subject> availableElectiveSubjects = this.dataAccessFacade.getElectiveSubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);
        List<Subject> availableOptionalSubjects = this.dataAccessFacade.getOptionalSubjectsAvailableForEnrollment(courseCode, curriculumCode, studentRegistration);

        // ToDo: mudar essa lógica para matricular no número ideal de créditos por tipo (sem considerar co-requisitos, por enquanto)
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

    private int getIdealNumberOfCredits(Curriculum curriculum, StudentCurriculumProgress progress) {
        // ToDo: calcular nextTerm levando em consideração o número de créditos integralizados e
        // o campo expectedMinAccumulatedCreditsList de currículo
        int nextTerm = progress.getCompletedTerms() + (progress.getEnrolledCredits() > 0 ? 2 : 1);
        int idealMandatoryCredits = curriculum.getIdealMandatoryCredits(nextTerm);
        int idealOptionalCredits = curriculum.getIdealOptionalCredits(nextTerm);
        int idealComplementaryCredits = curriculum.getIdealComplementaryCredits(nextTerm);
        return idealMandatoryCredits + idealOptionalCredits + idealComplementaryCredits;
    }
}
