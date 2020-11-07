package br.edu.ufcg.computacao.eureca.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class EurecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurecaApplication.class, args);
	}
}
