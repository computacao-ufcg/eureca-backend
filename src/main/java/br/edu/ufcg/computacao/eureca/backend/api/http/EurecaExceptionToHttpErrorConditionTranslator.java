package br.edu.ufcg.computacao.eureca.backend.api.http;

import br.edu.ufcg.computacao.eureca.common.exceptions.ExceptionToHttpErrorConditionTranslator;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class EurecaExceptionToHttpErrorConditionTranslator extends ExceptionToHttpErrorConditionTranslator {
}
