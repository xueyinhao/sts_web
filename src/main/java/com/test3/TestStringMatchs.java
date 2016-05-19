package com.test3;

public class TestStringMatchs {
	public static void main(String[] args) {
		match("0eh354");
		match("111111");
		match("11111a");
		match("3333");
		match("777777");
	}

	public static void match(String str) {
		System.out.println(str.matches("[0-9]{6}"));
	}
}
