package com.test;

import java.net.*;

public class TestB {
	public static void main(String[] args) throws Exception {
		String ip = InetAddress.getLocalHost().getHostAddress();
		System.out.println(ip);
	}
}
