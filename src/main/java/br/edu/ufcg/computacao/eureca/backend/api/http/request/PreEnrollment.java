package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentPreEnrollment;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(PreEnrollment.PRE_ENROLLMENT_ENDPOINT)
public class PreEnrollment {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollment.class);

    public static final String PRE_ENROLLMENT_ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "/pre_enrollment";

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<StudentPreEnrollment> getPreEnrollment(
            @RequestParam String studentRegistration,
            @RequestParam String courseCode,
            @RequestParam String curriculumCode
    ) throws InvalidParameterException {
        try {
            StudentPreEnrollment preEnrollment = ApplicationFacade.getInstance().getConcludedSubjects(courseCode, curriculumCode, studentRegistration);
            return new ResponseEntity<>(preEnrollment, HttpStatus.OK);
        } catch (InvalidParameterException e) {
            LOGGER.info(e);
            throw e;
        }
    }
}
