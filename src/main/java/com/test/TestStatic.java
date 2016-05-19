package com.test;

public class TestStatic extends BaseStatic{
	
	public static String TEST = "this is a test message";
	 
    static{
        System.out.println("test static");
    }
     
    public TestStatic(){
        System.out.println("test constructor");
    }
     
    public static void main(String[] args) {
        System.out.println("ttttttttttt");
    }
}
 
class BaseStatic{
     
    static{
        System.out.println("base static");
    }
     
    public BaseStatic(){
        System.out.println("base constructor");
    }
}
