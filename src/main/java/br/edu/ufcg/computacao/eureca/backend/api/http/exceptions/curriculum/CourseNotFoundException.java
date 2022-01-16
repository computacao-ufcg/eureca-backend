package br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.curriculum;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.common.exceptions.NotFoundException;

public class CourseNotFoundException extends NotFoundException {

    public CourseNotFoundException(String courseCode) {
        super(String.format(Messages.INVALID_COURSE_S, courseCode));
    }
}
