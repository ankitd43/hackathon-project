package com.hackathon.consumer.integration.db_config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends
   WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  HeadersConfigurer<HttpSecurity> headers = http.headers();
	  headers.defaultsDisabled();
	  
	  headers.frameOptions().deny();
	  headers.featurePolicy("geolocation 'self'");
	  headers.referrerPolicy();
	  http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable();
	  
	  headers
      .cacheControl().and().frameOptions().sameOrigin();
	  headers.contentTypeOptions();
	  headers.httpStrictTransportSecurity();
	  headers.httpStrictTransportSecurity().maxAgeInSeconds(31536000);
	    headers.httpStrictTransportSecurity().includeSubDomains(true);
	  
	  headers
	    .contentSecurityPolicy("script-src 'self' https://localhost.com; object-src https://localhost.com; report-uri /csp-report-endpoint/");
	    
	    headers.xssProtection().block(true);
	    headers
	    .addHeaderWriter(new StaticHeadersWriter ("X-Content-Security-Policy","default-src 'self'"))
	    .addHeaderWriter(new StaticHeadersWriter("X-Custom-Security-Header","header-value"))
	    .addHeaderWriter(new StaticHeadersWriter("X-WebKit-CSP","default-src 'self'"));
		    
	    headers
	    .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
	    
	    http.requiresChannel()
	    .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
	    .requiresSecure();
	    headers.addHeaderWriter(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.CACHE,
	              ClearSiteDataHeaderWriter.Directive.COOKIES,
	              ClearSiteDataHeaderWriter.Directive.STORAGE));
	    RequestMatcher matcher = new AntPathRequestMatcher("/error");
	    DelegatingRequestMatcherHeaderWriter headerWriter =
	    		new DelegatingRequestMatcherHeaderWriter(matcher,new XFrameOptionsHeaderWriter());
	    headers
	    .addHeaderWriter(headerWriter);
	    
    
  } // ...

  
//  @Bean
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//      // ALTHOUGH THIS SEEMS LIKE USELESS CODE,
//      // IT'S REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
//      return super.authenticationManagerBean();
//  }
    
}