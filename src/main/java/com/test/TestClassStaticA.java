package com.test;

public class TestClassStaticA {
	private static String name ="test";
	static {
		System.out.println("in TestClassStaticA static");
	}
	
	public void test() {
		System.out.println("this in test method");
	}
}
