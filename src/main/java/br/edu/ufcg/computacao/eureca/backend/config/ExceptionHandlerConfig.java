package br.edu.ufcg.computacao.eureca.backend.config;

import br.edu.ufcg.computacao.eureca.common.exceptions.CommonExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@ConditionalOnMissingBean(annotation = ControllerAdvice.class)
public class ExceptionHandlerConfig {

    /*
        vai passar primeiro pelo EurecaExceptionHandler e só depois pelo CommonExceptionHandler
     */
    @Bean
    public CommonExceptionHandler exceptionHandler() {
        return new CommonExceptionHandler();
    }
}
