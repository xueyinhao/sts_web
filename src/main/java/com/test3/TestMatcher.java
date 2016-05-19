package com.test3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMatcher {

	public static void main(String[] args) {
		  String ss="温馨提示：每笔限额50000元；每日限额50000元；每月限额1500000元。大额充值请选择网银充值。";
		  Pattern p=Pattern.compile("(\\d+)");  
		  Matcher m=p.matcher(ss);      
		  while(m.find()){
		      System.out.println(m.group());
		  } 
	}
}
