package com.hao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AaaService {
	
	@Autowired
	BbbService bbbService;
	
	
	public String TestAService(){
		String str = "this is in testa service";
		
		System.out.println(str);
		bbbService.TestBService();
		
		return str;
	}
}
