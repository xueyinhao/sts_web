package com.hao.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XssFilter implements javax.servlet.Filter {
	
	private static Logger logger = LoggerFactory
			.getLogger(XssFilter.class);
	
	private String[] excludePageArray; 

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		boolean isExcludedPage = false;
		String servletPath = ((HttpServletRequest) request).getServletPath();
		//判断是否在过滤url之外    
		for (String page : excludePageArray) { 
			if(servletPath.endsWith(page)){ 
				logger.info("XssFilter excludePage={}",page);
				isExcludedPage = true;     
				break;     
			}     
		}  
		
		if (isExcludedPage) {
			//在过滤url之外     
			filterChain.doFilter(request, response);     
		} else {
			XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
					(HttpServletRequest) request);
			filterChain.doFilter(xssRequest, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		String excludePages = fConfig.getInitParameter("excludePages");     
		if (StringUtils.isNotEmpty(excludePages)) {     
			excludePageArray = excludePages.split(",");     
		} else {
			excludePageArray = new String[0];
		}
	}

}
