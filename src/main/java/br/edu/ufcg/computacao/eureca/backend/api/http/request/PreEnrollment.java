package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentPreEnrollment;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(PreEnrollment.PRE_ENROLLMENT_ENDPOINT)
@Api(ApiDocumentation.PreEnrollment.API)
public class PreEnrollment {
    private static final Logger LOGGER = Logger.getLogger(PreEnrollment.class);

    public static final String PRE_ENROLLMENT_ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "/pre_enrollment";

    @ApiOperation(value = ApiDocumentation.PreEnrollment.GET_PRE_ENROLLMENT)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<StudentPreEnrollment> getPreEnrollment(
            @ApiParam(ApiDocumentation.Common.STUDENT_REGISTRATION)
            @RequestParam String studentRegistration,
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam String curriculumCode,
            @ApiParam(value = ApiDocumentation.Token.AUTHENTICATION_TOKEN)
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token
    ) throws EurecaException {
        try {
            StudentPreEnrollment preEnrollment = ApplicationFacade.getInstance().createPreEnrollment(token, courseCode, curriculumCode, studentRegistration);
            return new ResponseEntity<>(preEnrollment, HttpStatus.OK);
        } catch (EurecaException e) {
            LOGGER.info(e);
            throw e;
        }
    }

    // ToDo: criar um endpoint que não recebe a matrícula como parâmetro e calcula a pre-matrícula para todos os alunos ativos
}
