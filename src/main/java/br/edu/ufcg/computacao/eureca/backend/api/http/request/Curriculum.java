package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping(value = Curriculum.ENDPOINT)
@Api(description = ApiDocumentation.Curriculum.API)
public class Curriculum {
    private Logger LOGGER = Logger.getLogger(Curriculum.class);

    @ApiOperation(value = ApiDocumentation.Curriculum.GET_AVAILABLE_CURRICULUMS)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<String>> getAvailableCurriculums() throws EurecaException {
        try {
            Collection<String> curriculums = ApplicationFacade.getInstance().getAvailableCurriculums();
            return new ResponseEntity<>(curriculums, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.error(Messages.SOMETHING_WENT_WRONG);
            throw e;
        }
    }

    public static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "curriculums";
}
