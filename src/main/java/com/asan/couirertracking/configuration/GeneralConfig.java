package com.asan.couirertracking.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
public class GeneralConfig {

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

}
