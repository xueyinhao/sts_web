package com.test;

public class TestArray {

	public static void main(String[] args) {
//		String[] ss = new String[10];
//		int[] ii = new int[10];
//		for(String s:ss){
//			s = "" + Math.random();
//		}
//		for(Integer i:ii){
//			System.out.println(i);
//		}
		
		String s = "excludePages";
		
		String[] ss = s.split(",");
		
		System.out.println(ss.length);
		
		for(String a : ss){
			System.out.println(a);
		}
		
		
		ss = new String[0];
		
		System.out.println(ss.length);
		
		for(String a : ss){
			System.out.println(a);
		}
		
	}

}
