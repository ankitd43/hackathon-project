package com.hackathon.consumer.integration.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
@ComponentScan(basePackages = {"com.hackathon.consumer.integration","com.hackathon.consumer.integration.dao","com.hackathon.consumer.integration.service"})
@ComponentScan()
public class ConsumerIntegrationApplication {
	

	public static void main(String[] args) {
		
		ConfigurableApplicationContext ctx = SpringApplication.run(ConsumerIntegrationApplication.class, args);
		 
		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
		 dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	        
	}
}
