package com.test3;


public class TestCredit {

	public static void main(String[] args) {
		
		int money = getDecimalCredit(3500.0D*0.001,0.5D);
		System.out.println(money);
	}

	
	public static int getDecimalCredit(double credit, double type) {
		double decimalManager = 0.5;
		String num = String.valueOf(credit);
		String[] array = num.split("\\.");
		double decimal = Double.parseDouble("0." + array[1]);
		int intNum = Integer.parseInt(array[0]);
		if (decimal >= decimalManager) {
			return intNum + 1;
		}
		return intNum;
	}
}
