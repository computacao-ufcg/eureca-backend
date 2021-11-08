package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsPerSubjectData;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.EmailSearchResponse;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = Communication.ENDPOINT)
@Api(description = ApiDocumentation.Communication.API)
public class Communication {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "communication";

    private static final Logger LOGGER = Logger.getLogger(Communication.class);

    @RequestMapping(value = "studentsEmailSearch", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentEmailSearch.GET_EMAILS)
    public ResponseEntity<Map<String, EmailSearchResponse>> getStudentsEmailsSearch(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.STATUS)
            @RequestParam(required = false, defaultValue = "^$") String status,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.NAME)
            @RequestParam(required = false, defaultValue = "^$") String studentName,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.GENDER)
            @RequestParam(required = false, defaultValue = "^$") String gender,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.CRA_OPERATION)
            @RequestParam(required = false, defaultValue = "^$") String craOperation,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.CRA)
            @RequestParam(required = false, defaultValue = "^$") String cra,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.ENROLLED_CREDITS_OPERATION)
            @RequestParam(required = false, defaultValue = "^$") String enrolledCreditsOperation,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.ENROLLED_CREDITS)
            @RequestParam(required = false, defaultValue = "^$") String enrolledCredits,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.ADMISSION_TERM)
            @RequestParam(required = false, defaultValue = "^$") String admissionTerm,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token

    ) throws EurecaException {
        try {
            Map<String, EmailSearchResponse> emails = ApplicationFacade.getInstance().getStudentsEmailsSearch(token, courseCode,
                    curriculumCode, admissionTerm, studentName, gender,
                    status, craOperation, cra, enrolledCreditsOperation, enrolledCredits);
            return new ResponseEntity<>(emails, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "subjectEmailSearch", method = RequestMethod.GET)
    @ApiOperation(value =  ApiDocumentation.StudentEmailSearch.GET_EMAILS)
    public ResponseEntity<Map<String, EmailSearchResponse>> getSubjectEmailsSearch(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.SUBJECT_NAME)
            @RequestParam(required = false, defaultValue = "^$") String subjectName,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.SUBJECT_TYPE)
            @RequestParam(required = false, defaultValue = "^$") String subjectType,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.ACADEMIC_UNIT)
            @RequestParam(required = false, defaultValue = "^$") String academicUnit,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.TERM)
            @RequestParam(required = false, defaultValue = "^$") String term,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token

    ) throws EurecaException {
        try {
            Map<String, EmailSearchResponse> emails = ApplicationFacade.getInstance().getSubjectEmailsSearch(token, courseCode,
                    curriculumCode, subjectName, subjectType, academicUnit, term);
            return new ResponseEntity<>(emails, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }
}
