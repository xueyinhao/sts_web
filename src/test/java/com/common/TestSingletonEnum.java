package com.common;
import org.junit.Test;


public class TestSingletonEnum {
	
	@Test 
	public void test() {  
		SingletonEnum.INSTANCE.setName("aaaa");
		System.out.println("---"+SingletonEnum.INSTANCE.getName());
		System.out.println("---"+SingletonEnum.INSTANCE2.getName());
		SingletonEnum.INSTANCE2.setName("bbbb");
		SingletonEnum.INSTANCE2.execute();
		SingletonEnum.INSTANCE.execute();
	}
}
