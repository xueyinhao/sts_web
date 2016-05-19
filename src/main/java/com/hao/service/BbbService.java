package com.hao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BbbService {
	
	@Autowired
	AaaService aaaService;
	
	public String TestBService(){
		String str = "this is in testb service";
		
		System.out.println(str);
		return str;
	}
}
