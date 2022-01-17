package br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.pre_enrollment;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.common.exceptions.NotFoundException;

public class StudentNotFoundException extends NotFoundException {

    public StudentNotFoundException(String registration) {
        super(String.format(Messages.STUDENT_S_NOT_FOUND, registration));
    }
}
