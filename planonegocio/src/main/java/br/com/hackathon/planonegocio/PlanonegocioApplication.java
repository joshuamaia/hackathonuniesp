package br.com.hackathon.planonegocio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Fast Report GPT API", version = "1.0", description = "Fast Report GPT Information"))
public class PlanonegocioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanonegocioApplication.class, args);
	}

}
