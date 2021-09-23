package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.ActivesPreEnrollmentResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class PreEnrollmentController {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollmentController.class);

    private DataAccessFacade dataAccessFacade;

    public PreEnrollmentController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public StudentPreEnrollment createStudentPreEnrollment(String courseCode, String curriculumCode, String studentRegistration) throws InvalidParameterException {
        return this.dataAccessFacade.getStudentPreEnrollment(courseCode, curriculumCode, studentRegistration);
    }

    public ActivesPreEnrollmentResponse getActivesPreEnrollmentResponse(String courseCode, String curriculumCode) throws InvalidParameterException  {
        return this.dataAccessFacade.getActivesPreEnrollment(courseCode, curriculumCode);
    }
}
