package com.common;

public class CommonUtils {
	static{
		System.out.println("this is in CommonUtils static");
	}
	
	public static String CONST = "this is const";
	
	private String str = "";
	
	public CommonUtils() {
		System.out.println("this is in CommonUtils 构造函数");
	}
	
	public static void main(String[] args) {
		System.out.println("this is test");
	}

}
