package com.hao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hao.entity.User;
import com.hao.service.AaaService;
import com.hao.service.BbbService;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	
	private static Logger logger = LoggerFactory
			.getLogger(TestController.class);
	
	@Autowired
	AaaService aaaService;
	
	@Autowired
	BbbService bbbService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/show")
	public String show(String id, String msg, HttpServletRequest request) {
		request.setAttribute("message", msg);
		request.setAttribute("id", id);
		
		logger.info("--------in test show controller----------");
		
		return "test/show";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/doTest")
	public String test(User u,HttpServletRequest request) {
		System.out.println(u);
		//System.out.println(u2);
		
		return "test/show";
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/doJupm")
	public String jump(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/jupm.jsp").forward(request, response);
		response.sendRedirect("www.baidu.com");
		
		
		return "test/show";
	}
	
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/doServiceA")
	public String doServiceA(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		aaaService.TestAService();
		//bbbService.TestBService();
		
		return "test/aaa";
	}
}
