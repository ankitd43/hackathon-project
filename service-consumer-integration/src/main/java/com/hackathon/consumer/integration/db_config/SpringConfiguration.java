package com.hackathon.consumer.integration.db_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class SpringConfiguration extends WebMvcConfigurerAdapter {
	
	
	@Bean
	AuthenticationInterceptor authenticationInterceptor(){
		return new AuthenticationInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/*");
		
		
	}
	
}
