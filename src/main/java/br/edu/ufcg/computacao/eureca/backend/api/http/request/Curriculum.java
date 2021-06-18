package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
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
public class Curriculum {
    private Logger LOGGER = Logger.getLogger(Curriculum.class);

    public static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "curriculums";

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
}
