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
@RequestMapping(value = Statistics.ENDPOINT)
@Api(description = ApiDocumentation.Statistics.API)
public class Statistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "estatisticas";

    private static final Logger LOGGER = Logger.getLogger(Statistics.class);

    @RequestMapping(value = "ativos", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_ATIVOS)
    public ResponseEntity<Collection<ActiveSummaryResponse>> getActives(
            @ApiParam(value = ApiDocumentation.Statistics.DE)
            @RequestParam String de,
            @ApiParam(value = ApiDocumentation.Statistics.ATE)
            @RequestParam String ate,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = false, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<ActiveSummaryResponse> ret = ApplicationFacade.getInstance().getActives(token);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "ativos/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_ATIVOS_CSV)
    public ResponseEntity<Collection<ActiveDataResponse>> getActivesCSV(
            @ApiParam(value = ApiDocumentation.Statistics.DE)
            @RequestParam String de,
            @ApiParam(value = ApiDocumentation.Statistics.ATE)
            @RequestParam String ate,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = false, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<ActiveDataResponse> ret = ApplicationFacade.getInstance().getActivesCSV(token);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "egressos", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_EGRESSOS)
    public ResponseEntity<AlumnusSummaryResponse> getAlumni(
            @ApiParam(value = ApiDocumentation.Statistics.DE)
            @RequestParam String de,
            @ApiParam(value = ApiDocumentation.Statistics.ATE)
            @RequestParam String ate,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = false, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            AlumnusSummaryResponse ret = ApplicationFacade.getInstance().getAlumni(token);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "egressos/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_EGRESSOS_CSV)
    public ResponseEntity<Collection<AlumnusDataResponse>> getAlumniCSV(
            @ApiParam(value = ApiDocumentation.Statistics.DE)
            @RequestParam String de,
            @ApiParam(value = ApiDocumentation.Statistics.ATE)
            @RequestParam String ate,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = false, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<AlumnusDataResponse> ret = ApplicationFacade.getInstance().getAlumniCSV(token);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "evadidos", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_EVADIDOS)
    public ResponseEntity<Collection<DropoutSummaryResponse>> getDropouts(
            @ApiParam(value = ApiDocumentation.Statistics.DE)
            @RequestParam String de,
            @ApiParam(value = ApiDocumentation.Statistics.ATE)
            @RequestParam String ate,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = false, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<DropoutSummaryResponse> ret = ApplicationFacade.getInstance().getDropouts(token);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }

    @RequestMapping(value = "evadidos/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_EVADIDOS_CSV)
    public ResponseEntity<Collection<DropoutDataResponse>> getDropoutsCSV(
            @ApiParam(value = ApiDocumentation.Statistics.DE)
            @RequestParam String de,
            @ApiParam(value = ApiDocumentation.Statistics.ATE)
            @RequestParam String ate,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = false, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Collection<DropoutDataResponse> ret = ApplicationFacade.getInstance().getDropoutsCSV(token);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }
}
