package com.test.equals;

public class TestEquals {
	public static void main(String[] args) {
		
		String s1 = "abc";
		String s2 = "abc";
		String s3 = new String("abc");
		
		System.out.println(s1==s2);
		System.out.println(s2==s3);
		
		Integer i = 127;
		Integer j = new Integer(127);
		Integer k = Integer.valueOf(127);
		
		System.out.println(i==j);
		System.out.println(i==k);
		
		
		Integer i2 = 128;
		Integer j2 = new Integer(128);
		Integer k2 = Integer.valueOf(128);
		
		System.out.println(i2==j2);
		System.out.println(i2==k2);

	}
}
