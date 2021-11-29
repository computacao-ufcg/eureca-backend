package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.constants.ApiDocumentation;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.EmailSearchResponse;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = Communication.ENDPOINT)
@Api(description = ApiDocumentation.Communication.API)
public class Communication {

    protected static final String ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "communication";

    private static final Logger LOGGER = Logger.getLogger(Communication.class);

    @RequestMapping(value = "studentsEmailSearch", method = RequestMethod.GET)
    @ApiOperation(value = ApiDocumentation.Communication.STUDENT_EMAILS_SEARCH)
    public ResponseEntity<Map<String, EmailSearchResponse>> getStudentsEmailsSearch(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.EmailSearch.STATUS)
            @RequestParam(required = false, defaultValue = "^$") String status,
            @ApiParam(value = ApiDocumentation.EmailSearch.NAME)
            @RequestParam(required = false, defaultValue = "^$") String studentName,
            @ApiParam(value = ApiDocumentation.EmailSearch.GENDER)
            @RequestParam(required = false, defaultValue = "^$") String gender,
            @ApiParam(value = ApiDocumentation.EmailSearch.CRA_OPERATION)
            @RequestParam(required = false, defaultValue = "^$") String craOperation,
            @ApiParam(value = ApiDocumentation.EmailSearch.CRA)
            @RequestParam(required = false, defaultValue = "^$") String cra,
            @ApiParam(value = ApiDocumentation.EmailSearch.ENROLLED_CREDITS_OPERATION)
            @RequestParam(required = false, defaultValue = "^$") String enrolledCreditsOperation,
            @ApiParam(value = ApiDocumentation.EmailSearch.ENROLLED_CREDITS)
            @RequestParam(required = false, defaultValue = "^$") String enrolledCredits,
            @ApiParam(value = ApiDocumentation.EmailSearch.ADMISSION_TERM)
            @RequestParam(required = false, defaultValue = "^$") String admissionTerm,
            @ApiParam(value = ApiDocumentation.EmailSearch.AFFIRMATIVE_POLICY)
            @RequestParam(required = false, defaultValue = "^$") String affirmativePolicy,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token

    ) throws EurecaException {
        try {
            Map<String, EmailSearchResponse> emails = ApplicationFacade.getInstance().getStudentsEmailsSearch(token, courseCode,
                    curriculumCode, admissionTerm, studentName, gender,
                    status, craOperation, cra, enrolledCreditsOperation, enrolledCredits, affirmativePolicy);
            return new ResponseEntity<>(emails, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "subjectEmailSearch", method = RequestMethod.GET)
    @ApiOperation(value =  ApiDocumentation.Communication.SUBJECTS_EMAILS_SEARCH)
    public ResponseEntity<Map<String, EmailSearchResponse>> getSubjectEmailsSearch(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.EmailSearch.SUBJECT_NAME)
            @RequestParam(required = false, defaultValue = "^$") String subjectName,
            @ApiParam(value = ApiDocumentation.EmailSearch.SUBJECT_TYPE)
            @RequestParam(required = false, defaultValue = "^$") String subjectType,
            @ApiParam(value = ApiDocumentation.EmailSearch.ACADEMIC_UNIT)
            @RequestParam(required = false, defaultValue = "^$") String academicUnit,
            @ApiParam(value = ApiDocumentation.EmailSearch.TERM)
            @RequestParam(required = false, defaultValue = "^$") String term,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token

    ) throws EurecaException {
        try {
            Map<String, EmailSearchResponse> emails = ApplicationFacade.getInstance().getSubjectEmailsSearch(token, courseCode,
                    curriculumCode, subjectName, subjectType, academicUnit, term);
            return new ResponseEntity<>(emails, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }

    @RequestMapping(value = "teacherEmailSearch", method = RequestMethod.GET)
    @ApiOperation(value =  ApiDocumentation.Communication.TEACHERS_EMAILS_SEARCH)
    public ResponseEntity<Map<String, EmailSearchResponse>> getTeachersEmailsSearch(
            @ApiParam(value = ApiDocumentation.Common.COURSE)
            @RequestParam(required = true, value = "courseCode") String courseCode,
            @ApiParam(value = ApiDocumentation.Common.CURRICULUM)
            @RequestParam(required = false, value = "curriculumCode", defaultValue = SystemConstants.ALL) String curriculumCode,
            @ApiParam(value = ApiDocumentation.EmailSearch.TEACHER_NAME)
            @RequestParam(required = false, defaultValue = "^$") String teacherName,
            @ApiParam(value = ApiDocumentation.EmailSearch.TEACHER_ID)
            @RequestParam(required = false, defaultValue = "^$") String teacherId,
            @ApiParam(value = ApiDocumentation.EmailSearch.TEACHER_ACADEMIC_UNIT)
            @RequestParam(required = false, defaultValue = "^$") String academicUnit,
            @ApiParam(value = ApiDocumentation.EmailSearch.TERM)
            @RequestParam(required = false, defaultValue = "^$") String term,
            @RequestHeader(value = CommonKeys.AUTHENTICATION_TOKEN_KEY) String token

    ) throws EurecaException {
        try {
            Map<String, EmailSearchResponse> emails = ApplicationFacade.getInstance().getTeacherEmailsSearch(token, courseCode,
                    curriculumCode, teacherName, teacherId, academicUnit, term);
            return new ResponseEntity<>(emails, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(String.format(Messages.EURECA_EXCEPTION_S, e.getMessage()));
            throw e;
        }
    }
}
