package br.com.fuctura.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	public static final String API_JOGADOR="Jogadores";
	public static final String API_TECNICO="Tecnicos";
	public static final String API_TIME="Times";
	public static final String API_ESTADIO="Estadio";
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.fuctura"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
	}
	private ApiInfo metaInfo() {
		ApiInfo apiInfo=new ApiInfo("GERENCIADOR DE DATOS",
				"API REST de Gerenciamento de Times de Futebol",
				"1.0",
				"Terms of Service",
				null,
				"Apache Lincense Version 2.0",
				"https://www.apache.org/licesen.html", new ArrayList<>()
				);
		
		return apiInfo;
		
	}

}