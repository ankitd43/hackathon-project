package com.hackathon.consumer.integration.db_config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		
		
		String method = request.getMethod();
		if (!HttpMethod.GET.matches(method)  && !HttpMethod.POST.matches(method)  ) {
			
		} else {
			
			chain.doFilter(req, res);
		}
   		
	}

	public void init(FilterConfig filterConfig) {

	}

	public void destroy() {
	}
}
