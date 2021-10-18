package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.active.ActivesStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
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
@RequestMapping(value = StudentsStatistics.ENDPOINT)
@Api(description = ApiDocumentation.StudentStatistics.API)
public class StudentsStatistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/students";

    private static final Logger LOGGER = Logger.getLogger(StudentsStatistics.class);

    @RequestMapping(value = "actives", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_ACTIVES)
    public ResponseEntity<ActivesStatisticsResponse> getActivesStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            LOGGER.info(Messages.RECEIVING_GET_STUDENTS_STATISTICS);
            ActivesStatisticsResponse ret = ApplicationFacade.getInstance().getActivesStatistics(token, courseCode, curriculumCode, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "actives/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_ACTIVES_CSV)
    public ResponseEntity<StudentsResponse> getActivesCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            StudentsResponse ret = ApplicationFacade.getInstance().getActivesCSV(token, courseCode, curriculumCode, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "alumni", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_ALUMNI)
    public ResponseEntity<AlumniStatisticsResponse> getAlumniStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
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
            AlumniStatisticsResponse ret = ApplicationFacade.getInstance().getAlumniStatistics(token, courseCode, curriculumCode, from, to, lang);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "alumni/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_ALUMNI_CSV)
    public ResponseEntity<StudentsResponse> getAlumniCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            StudentsResponse ret = ApplicationFacade.getInstance().getAlumniCSV(token, courseCode, curriculumCode, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "dropouts", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_DROPOUT)
    public ResponseEntity<DropoutsStatisticsResponse> getDropoutsStatistics(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            DropoutsStatisticsResponse ret = ApplicationFacade.getInstance().getDropoutsStatistics(token, courseCode, curriculumCode, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "dropouts/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_DROPOUT_CSV)
    public ResponseEntity<StudentsResponse> getDropoutsCSV(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            StudentsResponse ret = ApplicationFacade.getInstance().getDropoutsCSV(token, courseCode, curriculumCode, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "summary", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_SUMMARY)
    public ResponseEntity<StudentsStatisticsSummaryResponse> getStudentsStatisticsSummary(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.Common.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Common.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Common.LANGUAGE)
            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            StudentsStatisticsSummaryResponse summary = ApplicationFacade.getInstance().getStudentsStatisticsSummary(token, courseCode, curriculumCode, from, to, lang);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "emailsSearch", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.StudentStatistics.GET_SUMMARY)
    public ResponseEntity<Map<String, String>> getStudentsEmailsSearch(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.STATUS)
            @RequestParam(required = false, value = "status") String status,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.NAME)
            @RequestParam(required = false, value = "studentName") String studentName,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.GENDER)
            @RequestParam(required = false, value = "gender") String gender,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.REGISTRATION)
            @RequestParam(required = false, value = "registration") String registration,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.CRA)
            @RequestParam(required = false, value = "cra") String cra,
            @ApiParam(value = ApiDocumentation.StudentEmailSearch.ENROLLED_CREDITS)
            @RequestParam(required = false, value = "enrolledCredits") String enrolledCredits,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token

    ) throws EurecaException {
        try {
            Map<String, String> summary = ApplicationFacade.getInstance().getStudentsEmailsSearch(token, courseCode, curriculumCode);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

}
