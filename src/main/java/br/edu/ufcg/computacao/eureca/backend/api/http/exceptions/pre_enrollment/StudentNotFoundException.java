package br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.pre_enrollment;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;

public class StudentNotFoundException extends EurecaException {

    public StudentNotFoundException(String registration) {
        super(String.format(Messages.STUDENT_S_NOT_FOUND, registration));
    }
}
