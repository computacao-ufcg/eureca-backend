package br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.pre_enrollment;

import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;

public class InvalidTermException extends EurecaException {

    private static final String INVALID_TERM = "Período inválido: %s";

    public InvalidTermException(String term) {
        super(String.format(INVALID_TERM, term));
    }
}
