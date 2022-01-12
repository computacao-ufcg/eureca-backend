package br.edu.ufcg.computacao.eureca.backend.api.http.exceptions;

import br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.pre_enrollment.InvalidTermException;
import br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.pre_enrollment.StudentNotFoundException;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.exceptions.ExceptionResponse;
import br.edu.ufcg.computacao.eureca.common.exceptions.CommonExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class EurecaExceptionHandler extends CommonExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleStudentNotFound(EurecaException exception, WebRequest req) {
        return this.buildEurecaErrorResponseEntity(exception, req, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTermException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidTerm(EurecaException exception, WebRequest req) {
        return this.buildEurecaErrorResponseEntity(exception, req, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ExceptionResponse> buildEurecaErrorResponseEntity(EurecaException exception, WebRequest req, HttpStatus statusCode) {
        ExceptionResponse error = this.buildEurecaError(exception, req);
        return new ResponseEntity<>(error, statusCode);
    }

    private ExceptionResponse buildEurecaError(EurecaException exception, WebRequest req) {
        return new ExceptionResponse(exception.getMessage(), req.getDescription(false));
    }
}
