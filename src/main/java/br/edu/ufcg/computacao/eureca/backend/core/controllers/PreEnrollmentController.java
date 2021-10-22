package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.TreeSet;

public class PreEnrollmentController {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollmentController.class);

    private DataAccessFacade dataAccessFacade;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentPreEnrollmentResponse createStudentPreEnrollment(String courseCode, String curriculumCode, String studentRegistration, Integer numCredits, String optionalPriorityList, String electivePriorityList) throws InvalidParameterException {
        return this.dataAccessFacade.getStudentPreEnrollment(courseCode, curriculumCode, studentRegistration, numCredits, optionalPriorityList, electivePriorityList);
    }

    public PreEnrollmentsResponse getPreEnrollments(String courseCode, String curriculumCode) throws InvalidParameterException  {
        return this.dataAccessFacade.getActivesPreEnrollment(courseCode, curriculumCode);
    }

    public SubjectsDemandResponse getDemand(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectDemand> demand = new TreeSet<>();
        Collection<DetailedSubjectDemand> detailedDemand = this.getDetailedSubjectDemand(courseCode, curriculumCode);

        detailedDemand.forEach(subject -> {
            String subjectCode = subject.getDemand().getSubjectCode();
            String subjectName = subject.getDemand().getSubjectName();
            int totalDemand = subject.getDemand().getTotalDemand();
            demand.add(new SubjectDemand(subjectCode, subjectName, totalDemand));
        });
        return new SubjectsDemandResponse(demand);
    }

    private Collection<DetailedSubjectDemand> getDetailedSubjectDemand(String courseCode, String curriculumCode) throws InvalidParameterException {
        PreEnrollmentsResponse preEnrollments = this.dataAccessFacade.getActivesPreEnrollment(courseCode, curriculumCode);
        Collection<DetailedSubjectDemand> detailedDemand = new TreeSet<>();

        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getMandatoryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getComplementaryDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getOptionalDemand());
        detailedDemand.addAll(preEnrollments.getSubjectDemandSummary().getElectiveDemand());

        return detailedDemand;
    }
}
