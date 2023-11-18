package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.*;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
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

@CrossOrigin
@RestController
@RequestMapping(PreEnrollment.PRE_ENROLLMENT_ENDPOINT)
@Api(description = ApiDocumentation.PreEnrollment.API)
public class PreEnrollment {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollment.class);

    public static final String PRE_ENROLLMENT_ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "/pre_enrollment";

    @ApiOperation(value = ApiDocumentation.PreEnrollment.GET_PRE_ENROLLMENT)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<StudentPreEnrollmentResponse> getPreEnrollment(
            @ApiParam(ApiDocumentation.Common.STUDENT_REGISTRATION)
            @RequestParam String studentRegistration,
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam String courseCode,
            @ApiParam(value = ApiDocumentation.PreEnrollment.TERM)
            @RequestParam String term,
            @ApiParam(value = ApiDocumentation.PreEnrollment.MAX_CREDITS)
            @RequestParam(required = false) Integer numCredits,
            @ApiParam(value = ApiDocumentation.PreEnrollment.OPTIONAL_PRIORITY_LIST)
            @RequestParam(required = false) String optionalPriorityList,
            @ApiParam(value = ApiDocumentation.PreEnrollment.ELECTIVE_PRIORITY_LIST)
            @RequestParam(required = false) String electivePriorityList,
            @ApiParam(value = ApiDocumentation.PreEnrollment.COMPLEMENTARY_PRIORITY_LIST)
            @RequestParam(required = false) String complementaryPriorityList,
            @ApiParam(value = ApiDocumentation.PreEnrollment.MANDATORY_PRIORITY_LIST)
            @RequestParam(required = false) String mandatoryPriorityList,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            StudentPreEnrollmentResponse preEnrollment = ApplicationFacade.getInstance().getPreEnrollment(token,
                    courseCode, studentRegistration, term, numCredits, optionalPriorityList,
                    electivePriorityList, complementaryPriorityList, mandatoryPriorityList);
            return new ResponseEntity<>(preEnrollment, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(e);
            throw e;
        }
    }

    @ApiOperation(value = ApiDocumentation.PreEnrollment.GET_PRE_ENROLLMENTS)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<PreEnrollmentsResponse> getActivesPreEnrollments(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam String courseCode,
            @ApiParam(value = ApiDocumentation.PreEnrollment.TERM)
            @RequestParam String term,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            PreEnrollmentsResponse preEnrollments = ApplicationFacade.getInstance().getPreEnrollments(token, courseCode, term);
            return new ResponseEntity<>(preEnrollments, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(e);
            throw e;
        }
    }

    @ApiOperation(value = ApiDocumentation.PreEnrollment.GET_PRE_ENROLLMENTS_CSV)
    @RequestMapping(value = "/allCSV", method = RequestMethod.GET)
    public ResponseEntity<PreEnrollmentsCSVResponse> getActivesPreEnrollmentsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam String courseCode,
            @ApiParam(value = ApiDocumentation.PreEnrollment.TERM)
            @RequestParam String term,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            PreEnrollmentsCSVResponse preEnrollments = ApplicationFacade.getInstance().getPreEnrollmentsCSV(token, courseCode, term);
            return new ResponseEntity<>(preEnrollments, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(e);
            throw e;
        }
    }

    @ApiOperation(value = ApiDocumentation.PreEnrollment.GET_DEMAND)
    @RequestMapping(value = "/demand", method = RequestMethod.GET)
    public ResponseEntity<DemandResponse> getDemand(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam String courseCode,
            @ApiParam(value = ApiDocumentation.PreEnrollment.TERM)
            @RequestParam String term,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            DemandResponse demand = ApplicationFacade.getInstance().getDemand(token, courseCode, term);
            return new ResponseEntity<>(demand, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(e);
            throw e;
        }
    }

    @ApiOperation(value = ApiDocumentation.PreEnrollment.GET_DEMAND_CSV)
    @RequestMapping(value = "/demandCSV", method = RequestMethod.GET)
    public ResponseEntity<DemandCSVResponse> getDemandCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam String courseCode,
            @ApiParam(value = ApiDocumentation.PreEnrollment.TERM)
            @RequestParam String term,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            DemandCSVResponse demand = ApplicationFacade.getInstance().getDemandCSV(token, courseCode, term);
            return new ResponseEntity<>(demand, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(e);
            throw e;
        }
    }

    @ApiOperation(value = ApiDocumentation.PreEnrollment.GET_POSSIBLE_GRADUATE)
    @RequestMapping(value = "/possibleGraduate", method = RequestMethod.GET)
    public ResponseEntity<PossibleGraduateResponse> getPossibleGraduate(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam String courseCode,
            @ApiParam(value = ApiDocumentation.PreEnrollment.TERM)
            @RequestParam String term,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            PossibleGraduateResponse demand = ApplicationFacade.getInstance().getPossibleGraduate(token, courseCode, term);
            return new ResponseEntity<>(demand, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(e);
            throw e;
        }
    }
}
