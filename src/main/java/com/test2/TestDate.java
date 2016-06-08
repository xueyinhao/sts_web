package com.test2;

import java.util.Calendar;

public class TestDate {
 public static void main(String[] args) {
	 Calendar cal = Calendar.getInstance();
	    int day = cal.get(Calendar.DATE);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int year = cal.get(Calendar.YEAR);
	    int dow = cal.get(Calendar.DAY_OF_WEEK);
	    int dom = cal.get(Calendar.DAY_OF_MONTH);
	    int doy = cal.get(Calendar.DAY_OF_YEAR);

	    System.out.println("Current Date: " + cal.getTime());
	    System.out.println("Day: " + day);
	    System.out.println("Month: " + month);
	    System.out.println("Year: " + year);
	    System.out.println("Day of Week: " + dow);
	    System.out.println("Day of Month: " + dom);
	    System.out.println("Day of Year: " + doy);
	    
	    
	    System.out.println(System.currentTimeMillis());
	    System.out.println(System.currentTimeMillis()/1000L);
	    System.out.println(System.currentTimeMillis()/1000L - 1461659791);
	    //1461659791
	    
	    cal.set(2016, 5-1, 31);
	    System.out.println(cal.getTime());
	    
	    Calendar cal2 = Calendar.getInstance();
        
        for(int i=1;i<=12;i++){
        	cal2.setTime(cal.getTime());
        	cal2.add(Calendar.MONTH, i);
        	System.out.println(cal2.getTime());
        }
	    
	    
}
}
