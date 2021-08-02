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

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping(value = StudentsStatistics.ENDPOINT)
@Api(description = ApiDocumentation.StudentStatistics.API)
public class StudentsStatistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/students";

    private static final Logger LOGGER = Logger.getLogger(StudentsStatistics.class);

    @RequestMapping(value = "actives", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_ACTIVES)
    public ResponseEntity<ActivesStatisticsResponse> getActives(
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            LOGGER.info(Messages.RECEIVING_GET_STUDENTS_STATISTICS);
            ActivesStatisticsResponse ret = ApplicationFacade.getInstance().getActivesStatistics(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "actives/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_ACTIVES_CSV)
    public ResponseEntity<StudentResponse> getActivesCSV(
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            StudentResponse ret = ApplicationFacade.getInstance().getActivesCSV(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "alumni", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_ALUMNI)
    public ResponseEntity<AlumniStatisticsResponse> getAlumni(
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Common.LANGUAGE)
            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            AlumniStatisticsResponse ret = ApplicationFacade.getInstance().getAlumniStatistics(token, from, to, lang);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "alumni/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_ALUMNI_CSV)
    public ResponseEntity<StudentResponse> getAlumniCSV(
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            StudentResponse ret = ApplicationFacade.getInstance().getAlumniCSV(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "dropouts", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_DROPOUT)
    public ResponseEntity<DropoutsStatisticsResponse> getDropouts(
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            DropoutsStatisticsResponse ret = ApplicationFacade.getInstance().getDropoutsStatistics(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "dropouts/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_DROPOUT_CSV)
    public ResponseEntity<StudentResponse> getDropoutsCSV(
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            StudentResponse ret = ApplicationFacade.getInstance().getDropoutsCSV(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "summary", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_SUMMARY)
    public ResponseEntity<StudentsStatisticsSummaryResponse> getStudentsSummary(
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Common.LANGUAGE)
            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            StudentsStatisticsSummaryResponse summary = ApplicationFacade.getInstance().getStudentsStatisticsSummary(token, from, to, lang);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
            throw e;
        }
    }
}
