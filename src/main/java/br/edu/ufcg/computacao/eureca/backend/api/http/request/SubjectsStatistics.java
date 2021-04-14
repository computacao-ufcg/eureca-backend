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
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = SubjectsStatistics.ENDPOINT)
@Api(description = ApiDocumentation.Statistics.API)
public class SubjectsStatistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/subjects";

    private static final Logger LOGGER = Logger.getLogger(SubjectsStatistics.class);

    @RequestMapping(value = "success", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Statistics.GET_SUBJECTS)
    public ResponseEntity<Map<String, Collection<SubjectSummaryResponse>>> getSubjectSummary(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam String to,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = false, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {

        try {
            Map<String, Collection<SubjectSummaryResponse>> ret = ApplicationFacade.getInstance().getSubjectsStatistics(token, from, to);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }
}
