package br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.pre_enrollment;

import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;

public class StudentNotFoundException extends EurecaException {

    private static final String STUDENT_NOT_FOUND = "O estudante com matrícula %s não está cadastrado no sistema.";

    public StudentNotFoundException(String registration) {
        super(String.format(STUDENT_NOT_FOUND, registration));
    }
}
