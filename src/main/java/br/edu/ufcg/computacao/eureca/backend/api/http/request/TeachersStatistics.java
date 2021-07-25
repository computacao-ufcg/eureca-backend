package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.TeachersSummaryItemResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.TeachersSummaryResponse;
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
@RequestMapping(value = TeachersStatistics.ENDPOINT)
@Api(description = ApiDocumentation.TeachersStatistics.API)
public class TeachersStatistics {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/teachers";

    private static final Logger LOGGER = Logger.getLogger(TeachersStatistics.class);

    @RequestMapping(value = "summary", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.TeachersStatistics.GET_TEACHERS)
    public ResponseEntity<TeachersSummaryResponse> getTeachersSummary(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.Statistics.LANGUAGE)
            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            LOGGER.info(Messages.RECEIVING_GET_TEACHERS_STATISTICS);
            TeachersSummaryResponse summary = ApplicationFacade.getInstance().getTeachersStatistics(token, from, to, lang);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
            throw e;
        }
    }

    @RequestMapping(value = "summary/csv", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.TeachersStatistics.GET_TEACHERS_CSV)
    public ResponseEntity<Collection<TeachersSummaryItemResponse>> getTeachersSummaryCSV(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            Collection<TeachersSummaryItemResponse> teachers = ApplicationFacade.getInstance().getTeachersCSV(token, from, to);
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
            throw e;
        }
    }
}
