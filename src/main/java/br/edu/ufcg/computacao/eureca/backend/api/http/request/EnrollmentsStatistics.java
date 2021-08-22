package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.EnrollmentsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
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
@RequestMapping(value = EnrollmentsStatistics.ENDPOINT)
@Api(description = ApiDocumentation.EnrollmentsStatistics.API)
public class EnrollmentsStatistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/enrollments";

    private static final Logger LOGGER = Logger.getLogger(EnrollmentsStatistics.class);

    @RequestMapping(value = "mandatory", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_MANDATOTY_SUBJECTS_ENROLLMENTS)
    public ResponseEntity<EnrollmentsStatisticsResponse> getMandatorySubjectsEnrollmentsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            EnrollmentsStatisticsResponse response = ApplicationFacade.getInstance().getSubjectEnrollmentsStatistics(token, courseCode, curriculumCode, from, to, SubjectType.MANDATORY);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "mandatory/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_MANDATORY_SUBJECTS_ENROLLMENTS_CSV)
    public ResponseEntity<EnrollmentsResponse> getMandatorySubjectsEnrollmentsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            EnrollmentsResponse response = ApplicationFacade.getInstance().getSubjectEnrollmentsCSV(token, courseCode, curriculumCode, from, to, SubjectType.MANDATORY);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "optional", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_OPTIONAL_SUBJECTS_ENROLLMENTS)
    public ResponseEntity<EnrollmentsStatisticsResponse> getOptionalSubjectsEnrollmentsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            EnrollmentsStatisticsResponse response = ApplicationFacade.getInstance().getSubjectEnrollmentsStatistics(token, courseCode, curriculumCode, from, to, SubjectType.OPTIONAL);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "optional/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_OPTIONAL_SUBJECTS_ENROLLMENTS_CSV)
    public ResponseEntity<EnrollmentsResponse> getOptionalSubjectsEnrollmentsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            EnrollmentsResponse response = ApplicationFacade.getInstance().getSubjectEnrollmentsCSV(token, courseCode, curriculumCode, from, to, SubjectType.OPTIONAL);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "elective", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_ELECTIVE_SUBJECTS_ENROLLMENTS)
    public ResponseEntity<EnrollmentsStatisticsResponse> getElectiveSubjectsEnrollmentsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            EnrollmentsStatisticsResponse response = ApplicationFacade.getInstance().getSubjectEnrollmentsStatistics(token, courseCode, curriculumCode, from, to, SubjectType.ELECTIVE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "elective/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_ELECTIVE_SUBJECTS_ENROLLMENTS_CSV)
    public ResponseEntity<EnrollmentsResponse> getElectiveSubjectsEnrollmentsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            EnrollmentsResponse response = ApplicationFacade.getInstance().getSubjectEnrollmentsCSV(token, courseCode, curriculumCode, from, to, SubjectType.ELECTIVE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "complementary", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_COMPLEMENTARY_SUBJECTS_ENROLLMENTS)
    public ResponseEntity<EnrollmentsStatisticsResponse> getComplementarySubjectsEnrollmentsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            EnrollmentsStatisticsResponse response = ApplicationFacade.getInstance().getSubjectEnrollmentsStatistics(token, courseCode, curriculumCode, from, to, SubjectType.COMPLEMENTARY);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "complementary/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_COMPLEMENTARY_SUBJECTS_ENROLLMENTS_CSV)
    public ResponseEntity<EnrollmentsResponse> getComplementarySubjectsEnrollmentsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            EnrollmentsResponse response = ApplicationFacade.getInstance().getSubjectEnrollmentsCSV(token, courseCode, curriculumCode, from, to, SubjectType.COMPLEMENTARY);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "summary", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.EnrollmentsStatistics.GET_ENROLLMENTS)
    public ResponseEntity<EnrollmentsStatisticsSummaryResponse> getEnrollmentsSummary(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Common.LANGUAGE)
            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            LOGGER.info(Messages.RECEIVING_GET_ENROLLMENTS_STATISTICS);
            EnrollmentsStatisticsSummaryResponse summary = ApplicationFacade.getInstance().getSubjectEnrollmentsStatisticsSummary(token, courseCode, curriculumCode, from, to, lang);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }
}
