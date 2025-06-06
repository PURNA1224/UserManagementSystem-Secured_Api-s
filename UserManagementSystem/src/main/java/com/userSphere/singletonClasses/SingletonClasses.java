package com.userSphere.singletonClasses;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.userSphere.SpringApplicationContext;

@Configuration
public class SingletonClasses {
	
	// Bean definition for ModelMapper with STRICT matching strategy
	// Used for mapping between DTOs and Entities with exact property matching
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}
	
	// Bean definition for BCryptPasswordEncoder with strength 12
	// Used to hash passwords securely before saving to the database
	@Bean
	public BCryptPasswordEncoder getBcrypt() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	public SpringApplicationContext getApplicationContext() {
		return new SpringApplicationContext();
	}
}
