package br.com.fuctura.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
@Getter
@Configuration
public class ApplicationConfig {
	@Value("${url_imc_calculadora}")//http://localhost:8081/calculadora/imc
	private String urlImcCalculadora;

}
