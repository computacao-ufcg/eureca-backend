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
@Api(description = ApiDocumentation.Statistics.API)
public class StudentsStatistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/students";

    private static final Logger LOGGER = Logger.getLogger(StudentsStatistics.class);

    @RequestMapping(value = "actives", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_ATIVOS)
    public ResponseEntity<ActiveDataResponse> getActives(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            ActiveDataResponse ret = ApplicationFacade.getInstance().getActiveStudentsSummary(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "actives/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_ATIVOS_CSV)
    public ResponseEntity<Collection<StudentDataResponse>> getActivesCSV(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<StudentDataResponse> ret = ApplicationFacade.getInstance().getActiveStudentsCSV(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "alumni", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_EGRESSOS)
    public ResponseEntity<AlumniDataResponse> getAlumni(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            AlumniDataResponse ret = ApplicationFacade.getInstance().getAlumniSummary(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "alumni/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_EGRESSOS_CSV)
    public ResponseEntity<Collection<AlumniData>> getAlumniCSV(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<AlumniData> ret = ApplicationFacade.getInstance().getAlumniCSV(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "dropouts", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_EVADIDOS)
    public ResponseEntity<Collection<DropoutSummaryResponse>> getDropouts(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<DropoutSummaryResponse> ret = ApplicationFacade.getInstance().getDropoutsSummary(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "dropouts/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_EVADIDOS_CSV)
    public ResponseEntity<Collection<DropoutData>> getDropoutsCSV(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<DropoutData> ret = ApplicationFacade.getInstance().getDropoutsCSV(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }
}
