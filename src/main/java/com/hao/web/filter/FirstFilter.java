package com.hao.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstFilter implements Filter {
	
	private static Logger logger = LoggerFactory
			.getLogger(FirstFilter.class);
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("first filter destroy-------");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		logger.info("first filter dofilter before-------");
		filterChain.doFilter(request, response);
		logger.info("first filter dofilter after-------");
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("first filter init-------");
	}

}
