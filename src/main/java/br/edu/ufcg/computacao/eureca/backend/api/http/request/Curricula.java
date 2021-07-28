package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.CurriculumCodesResponse;
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
@RequestMapping(value = Curricula.ENDPOINT)
@Api(description = ApiDocumentation.Curricula.API)
public class Curricula {
    private Logger LOGGER = Logger.getLogger(Curricula.class);

    public static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "curricula";

    @ApiOperation(value = ApiDocumentation.Curricula.GET_CURRICULA)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<CurriculumCodesResponse> getCurriculumCodes(
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
    throws EurecaException {
        try {
            CurriculumCodesResponse curriculumCodes = ApplicationFacade.getInstance().getCurriculumCodes(token);
            return new ResponseEntity<>(curriculumCodes, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.error(Messages.SOMETHING_WENT_WRONG);
            throw e;
        }
    }
}
