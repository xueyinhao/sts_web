package com.test;

public class TestSplit {
	public static void main(String[] args) {
//		String s = "测试,我的|文字,";
//		
//		String[] str = s.split("\\|");
//		for(String a:str){
//			System.out.println(a);
//		}
//		String text = "138660";
//		String str = "";
//		  
//		  try {
//			  String start=text.substring(0, 3);
//			  String end=text.substring(text.length()-4, text.length());
//			  String hide=text.substring(3, text.length()-4).replaceAll("\\w", "*");
//			  str = start+hide+end;
//		  }catch(Exception e){
//			  e.printStackTrace();
//			  str = text.replaceAll("\\w", "*");;
//		  }
//		  
//		  System.out.println(str);
		
		
		//String ip = "192.168.0.111,1921.68.0.222";
		String ip = "192.168.0.111";
		
		String[] s = ip.split(",");
		System.out.println(s.length);
		for(String i : s){
			System.out.println(i);
		}
		
	}
}
