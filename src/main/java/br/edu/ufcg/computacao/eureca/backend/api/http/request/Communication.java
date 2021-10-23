package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = Communication.ENDPOINT)
@Api(description = ApiDocumentation.Communication.API)
public class Communication {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "communication";

    private static final Logger LOGGER = Logger.getLogger(Communication.class);

    @RequestMapping(value = "studentsEmailSearch", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_SUMMARY)
    public ResponseEntity<Map<String, String>> getStudentsEmailsSearch(
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
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.REGISTRATION)
            @RequestParam(required = false, defaultValue = "^$") String registration,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.CRA_OPERATION)
            @RequestParam(required = false, defaultValue = "^$") String craOperation,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.CRA)
            @RequestParam(required = false, defaultValue = "^$") double cra,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.ENROLLED_CREDITS)
            @RequestParam(required = false, defaultValue = "^$") String enrolledCredits,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.ADMISSION_TERM)
            @RequestParam(required = false, defaultValue = "^$") String admissionTerm,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token

    ) throws EurecaException {
        try {
            Map<String, String> summary = ApplicationFacade.getInstance().getStudentsEmailsSearch(token, courseCode,
                    curriculumCode, admissionTerm, studentName, gender, registration, status, craOperation, cra);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }
}
