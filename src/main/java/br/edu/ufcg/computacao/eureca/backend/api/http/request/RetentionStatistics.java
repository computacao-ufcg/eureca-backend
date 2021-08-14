package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
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

@CrossOrigin
@RestController
@RequestMapping(value = RetentionStatistics.ENDPOINT)
@Api(description = ApiDocumentation.StudentStatistics.API)
public class RetentionStatistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/retention";

    private static final Logger LOGGER = Logger.getLogger(RetentionStatistics.class);

    @RequestMapping(value = "students", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.RetentionStatistics.GET_STUDENT)
    public ResponseEntity<StudentsRetentionStatisticsResponse> getStudentsRetentionStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            StudentsRetentionStatisticsResponse ret = ApplicationFacade.getInstance().getStudentsRetentionStatistics(token, courseCode, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "students/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.RetentionStatistics.GET_STUDENT_CSV)
    public ResponseEntity<StudentResponse> getStudentsRetentionCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            StudentResponse studentsRetentionCSV = ApplicationFacade.getInstance().getStudentsRetentionCSV(token, courseCode, from, to);
            return new ResponseEntity<>(studentsRetentionCSV, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
            throw e;
        }
    }

    @RequestMapping(value = "subjects", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.RetentionStatistics.GET_SUBJECT)
    public ResponseEntity<SubjectRetentionStatisticsResponse> getSubjectsRetentionStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectRetentionStatisticsResponse ret = ApplicationFacade.getInstance().getSubjectsRetentionStatistics(token, courseCode, curriculumCode);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "subjects/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.RetentionStatistics.GET_SUBJECT_CSV)
    public ResponseEntity<SubjectRetentionResponse> getSubjectsRetentionCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = true, value = "curriculumCode") String curriculumCode,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            SubjectRetentionResponse subjectsRetention = ApplicationFacade.getInstance().getSubjectsRetentionCSV(token, courseCode, curriculumCode);
            return new ResponseEntity<>(subjectsRetention, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
            throw e;
        }
    }


    @RequestMapping(value = "summary", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.RetentionStatistics.GET_SUMMARY)
    public ResponseEntity<RetentionStatisticsSummaryResponse> getRetentionStatisticsSummary(
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
            RetentionStatisticsSummaryResponse summary = ApplicationFacade.getInstance().getRetentionStatisticsSummary(token, courseCode, curriculumCode, from, to, lang);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
            throw e;
        }
    }
}
