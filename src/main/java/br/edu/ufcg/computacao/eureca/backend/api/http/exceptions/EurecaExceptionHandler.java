package br.edu.ufcg.computacao.eureca.backend.api.http.exceptions;

import br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.curriculum.CourseNotFoundException;
import br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.curriculum.CurriculumNotFoundException;
import br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.pre_enrollment.StudentNotFoundException;
import br.edu.ufcg.computacao.eureca.common.exceptions.CommonExceptionHandler;
import br.edu.ufcg.computacao.eureca.common.exceptions.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class EurecaExceptionHandler extends CommonExceptionHandler {

    @ExceptionHandler({
            StudentNotFoundException.class,
            CurriculumNotFoundException.class,
            CourseNotFoundException.class
    })
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(Exception exception, WebRequest req) {
        return this.handleNotFoundException(exception, req);
    }
}
