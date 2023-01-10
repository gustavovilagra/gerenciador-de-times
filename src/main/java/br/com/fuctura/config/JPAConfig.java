package br.com.fuctura.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPAConfig {
	
	@Bean
	public DataSource crearDataSource() {
		return DataSourceBuilder
				.create()
				.url("jdbc:h2:mem:testdb")
				.username("sa")
				.password("")
				.driverClassName("org.h2.Driver")
				.build();
	}

}
