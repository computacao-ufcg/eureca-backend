package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
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
@RequestMapping(value = SubjectsStatistics.ENDPOINT)
@Api(description = ApiDocumentation.SubjectStatistics.API)
public class SubjectsStatistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/subjects";

    private static final Logger LOGGER = Logger.getLogger(SubjectsStatistics.class);

    @RequestMapping(value = "mandatory", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_MANDATORY)
    public ResponseEntity<SubjectsStatisticsResponse> getMandatorySubjectsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectsStatisticsResponse ret = ApplicationFacade.getInstance().getSubjectsStatistics(token, courseCode, curriculumCode, from, to, SubjectType.MANDATORY);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "mandatory/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_SUBJECTS_MANDATORY_CSV)
    public ResponseEntity<SubjectsResponse> getMandatorySubjectsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectsResponse ret = ApplicationFacade.getInstance().getSubjectsCSV(token, courseCode, curriculumCode, from, to, SubjectType.MANDATORY);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "optional", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_OPTIONAL)
    public ResponseEntity<SubjectsStatisticsResponse> getOptionalSubjectsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectsStatisticsResponse ret = ApplicationFacade.getInstance().getSubjectsStatistics(token, courseCode, curriculumCode, from, to, SubjectType.OPTIONAL);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "optional/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_SUBJECTS_OPTIONAL_CSV)
    public ResponseEntity<SubjectsResponse> getOptionalSubjectsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectsResponse ret = ApplicationFacade.getInstance().getSubjectsCSV(token, courseCode, curriculumCode, from, to, SubjectType.OPTIONAL);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "elective", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_ELECTIVE)
    public ResponseEntity<SubjectsStatisticsResponse> getElectiveSubjectsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectsStatisticsResponse ret = ApplicationFacade.getInstance().getSubjectsStatistics(token, courseCode, curriculumCode, from, to, SubjectType.ELECTIVE);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "elective/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_SUBJECTS_ELECTIVE_CSV)
    public ResponseEntity<SubjectsResponse> getElectiveSubjectsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectsResponse ret = ApplicationFacade.getInstance().getSubjectsCSV(token, courseCode, curriculumCode, from, to, SubjectType.ELECTIVE);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "complementary", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_COMPLEMENTARY)
    public ResponseEntity<SubjectsStatisticsResponse> getComplementarySubjectsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectsStatisticsResponse ret = ApplicationFacade.getInstance().getSubjectsStatistics(token, courseCode, curriculumCode, from, to, SubjectType.COMPLEMENTARY);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "complementary/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_SUBJECTS_COMPLEMENTARY_CSV)
    public ResponseEntity<SubjectsResponse> getComplementarySubjectsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectsResponse ret = ApplicationFacade.getInstance().getSubjectsCSV(token, courseCode, curriculumCode, from, to, SubjectType.COMPLEMENTARY);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "summary", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.SubjectStatistics.GET_SUBJECTS_SUMMARY)
    public ResponseEntity<SubjectsStatisticsSummaryResponse> getSubjectsSummary(
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
            LOGGER.info(Messages.RECEIVING_GET_SUBJECTS_STATISTICS);
            SubjectsStatisticsSummaryResponse summary = ApplicationFacade.getInstance().getSubjectsStatisticsSummary(token, courseCode, curriculumCode, from, to, lang);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
            throw e;
        }
    }
}
