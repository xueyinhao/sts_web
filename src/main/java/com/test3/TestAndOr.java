package com.test3;

public class TestAndOr {
	public static void main(String[] args) {
		boolean isOpen = true;
		boolean isClose = false;
		
		if(isOpen){
			System.out.println("is Opne");
		}
		
		if(isClose){
			System.out.println("is Close");
		}
		
		if(isOpen && isClose){
			System.out.println("test and");
		}
		
		if(isClose && isOpen){
			System.out.println("test and2");
		}
		
		if(isOpen || isClose){
			System.out.println("test or");
		}
		
		if(isClose || isOpen){
			System.out.println("test or2");
		}
	}
}
