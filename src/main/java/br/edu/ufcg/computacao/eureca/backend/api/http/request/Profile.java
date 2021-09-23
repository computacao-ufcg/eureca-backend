package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.profile.ProfileResponse;
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
@RequestMapping(value = Profile.ENDPOINT)
@Api(description = ApiDocumentation.Profile.API)
public class Profile {
    private Logger LOGGER = Logger.getLogger(Profile.class);

    public static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "profile";

    @ApiOperation(value = ApiDocumentation.Profile.GET_PROFILE)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ProfileResponse> getProfile(
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(required = true, value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token)
    throws EurecaException {
        try {
            ProfileResponse profile = ApplicationFacade.getInstance().getProfile(token);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }
}
