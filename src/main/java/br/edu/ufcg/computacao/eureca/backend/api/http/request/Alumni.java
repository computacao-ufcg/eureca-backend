package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.AlumnusBasicData;
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
@RequestMapping(value = Alumni.PUBLIC_KEY_ENDPOINT)
@Api(description = ApiDocumentation.Alumni.API)

public class Alumni {
    public static final String PUBLIC_KEY_ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "alumni";

    private final Logger LOGGER = Logger.getLogger(Alumni.class);

    @ApiOperation(value = ApiDocumentation.Alumni.GET)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<AlumnusBasicData>> getAlumni(
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = false, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
            throws EurecaException {
        try {
            Collection<AlumnusBasicData> alumniBasicData = ApplicationFacade.getInstance().getAlumniBasicData(token);
            return new ResponseEntity<>(alumniBasicData, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.SOMETHING_WENT_WRONG, e.getMessage()), e);
            throw e;
        }
    }
}
