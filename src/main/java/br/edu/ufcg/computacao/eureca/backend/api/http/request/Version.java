package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.VersionResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = Version.ENDPOINT)
@Api(description = ApiDocumentation.Version.API)
public class Version {

    public static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "version";

    private static final Logger LOGGER = Logger.getLogger(Version.class);

    @ApiOperation(value = ApiDocumentation.Version.GET_VERSION)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<VersionResponse> getVersion() {
        String versionNumber = null;
        versionNumber = ApplicationFacade.getInstance().getVersionNumber();
        return new ResponseEntity<VersionResponse>(new VersionResponse(versionNumber), HttpStatus.OK);
    }
}
