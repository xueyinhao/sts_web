package com.test2;
import java.math.BigDecimal;

import com.common.ApiStringUtils;

public class TestDouble {
 public static void main(String[] args) {
	
	// double d = 77581698.03;
	 double d = 19999999.03;
	 BigDecimal bd = new BigDecimal(d);
	 bd = bd.setScale(2, 6);
	 
	 System.out.println(d);
	 System.out.println(String.valueOf(d));
	 System.out.println(bd.toString());
	 
	 System.out.println(ApiStringUtils.formatFloordDouble2(d));
	 System.out.println(ApiStringUtils.formatDouble2(d));
}
}
