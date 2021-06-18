package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import io.swagger.annotations.Api;
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

    @RequestMapping(value = "summary", method = RequestMethod.GET)
    public ResponseEntity<SubjectSummaryResponse> getSubjectsSummary(
            @ApiParam(value = ApiDocumentation.Statistics.FROM)
            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
            @ApiParam(value = ApiDocumentation.Statistics.TO)
            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
            @ApiParam(value = ApiDocumentation.StudentStatistics.LANGUAGE)
            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            LOGGER.info(Messages.RECEIVING_GET_SUBJECTS_STATISTICS);
            SubjectSummaryResponse summary = ApplicationFacade.getInstance().getSubjectsStatistics(token, from, to, lang);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
            throw e;
        }
    }
//
//    @RequestMapping(value = "summary/csv", method = RequestMethod.GET)
//    public ResponseEntity<Collection<SubjectsSummaryItemResponse>> getSubjectsStatisticsCSV(
//            @ApiParam(value = ApiDocumentation.Statistics.FROM)
//            @RequestParam(required = false, value = "from", defaultValue = SystemConstants.FIRST_POSSIBLE_TERM) String from,
//            @ApiParam(value = ApiDocumentation.Statistics.TO)
//            @RequestParam(required = false, value = "to", defaultValue = SystemConstants.LAST_POSSIBLE_TERM) String to,
//            @ApiParam(value = ApiDocumentation.Statistics.LANGUAGE)
//            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
//            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
//    ) throws EurecaException {
//        try {
//            Collection<SubjectsSummaryItemResponse> subjects = ApplicationFacade.getInstance().getSubjectsStatisticsCSV(token, from, to, lang);
//            return new ResponseEntity<>(subjects, HttpStatus.OK);
//        } catch (EurecaException e) {
//            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
//            throw e;
//        }
//    }
//
//    @RequestMapping(value = "retention", method = RequestMethod.GET)
//    public ResponseEntity<Collection<SubjectsRetentionSummaryResponse>> getRetention(
//            @ApiParam(value = ApiDocumentation.Statistics.LANGUAGE)
//            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
//            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
//    ) throws EurecaException {
//        try {
//            Collection<SubjectsRetentionSummaryResponse> subjects = ApplicationFacade.getInstance().getSubjectsRetention(token, lang);
//            return new ResponseEntity<>(subjects, HttpStatus.OK);
//        } catch (EurecaException e) {
//            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
//            throw e;
//        }
//    }
//
//    @RequestMapping(value = "retention/csv", method = RequestMethod.GET)
//    public ResponseEntity<Collection<SubjectsRetentionResponse>> getRetentionCSV(
//            @ApiParam(value = ApiDocumentation.Statistics.LANGUAGE)
//            @RequestParam(required = false, value = "language", defaultValue = SystemConstants.PORTUGUESE) String lang,
//            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
//    ) throws EurecaException {
//        try {
//            Collection<SubjectsRetentionResponse> subjects = ApplicationFacade.getInstance().getSubjectsRetentionCSV(token, lang);
//            return new ResponseEntity<>(subjects, HttpStatus.OK);
//        } catch (EurecaException e) {
//            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage(), e));
//            throw e;
//        }
//    }
}
