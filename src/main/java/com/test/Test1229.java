package com.test;

import java.math.BigDecimal;

public class Test1229 {
	
	public static void main(String[] args) {
		double validAccount = 0.0D;
		double account = 150000.0D;
		double accountyYes = 149999.93D;
		validAccount = account - accountyYes;
		System.out.println("错误的double=" + validAccount);
		
		System.out.println("----------fix---------------");
		
		BigDecimal bd_account = new BigDecimal(Double.toString(account));
		BigDecimal bd_accountyYes = new BigDecimal(Double.toString(accountyYes));
		BigDecimal bd_validAccount = bd_account.subtract(bd_accountyYes);
		System.out.println("修正后的double=" + bd_validAccount.doubleValue());
		
		
		if(45000L/1L + 2*2499 >= 50000L/1L){
			System.out.println("----------11111---------------");
		}else{
			System.out.println("----------22222---------------");
		}
	}
}
