package com.test;

class Count {  
    private int num;  
    public void count() {  
    	//int num = 0;  
        for(int i = 1; i <= 10; i++) {  
            num += i;  
        }  
        System.out.println(Thread.currentThread().getName() + "-" + num);  
    }  
} 

public class TestThread {
	public static void main(String[] args) {  
        Runnable runnable = new Runnable() {  
            Count count = new Count();  
            public void run() {  
                count.count();  
            }  
        };  
        for(int i = 0; i < 10; i++) {  
            new Thread(runnable).start();  
        }  
    }  
}


