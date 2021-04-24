package com.hackathon.consumer.integration.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan(basePackages = { "com.hackathon.consumer.integration" })
@EnableAsync
@EnableScheduling
public class ConsumerIntegrationApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ConsumerIntegrationApplication.class, args);
		 
		 DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
		 dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	        
		 System.out.println("Enter in Cumsumer");
	}
}
