package com.project2.hct.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
