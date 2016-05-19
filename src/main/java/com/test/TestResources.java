package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestResources {
	public static void main(String[] args) throws IOException {
		
		InputStream in = TestResources.class.getClassLoader().getResourceAsStream("application.properties");
		Properties props = new Properties();
		props.load(in);
		
		System.out.println(props.getProperty("jdbc.driver"));
	}
}
