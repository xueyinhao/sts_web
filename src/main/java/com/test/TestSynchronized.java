package com.test;

/** 
 * 测试java多线程的同步，使用5个线程调用载体方法。 
 *  
 * 测试用例1：给载体方法加上synchronized修饰词（如程序中）。 
 * 结果：载体方法被同步，每秒打印一条记录； 
 *  
 * 测试用例2：去掉载体方法的synchronized修饰词（请自己试下）。 
 * 结果：载体方法没有被同步，5条记录很快打印完。 
 *  
 * 结论：可想而知!给static方法添上synchronized修饰词，方法就被同步。 
 * @author ayis 
 * 
 * Jan 13, 2009 
 */  
public class TestSynchronized {  
      
    public static void main(String args[]){  
          
        // 开启5个线程调用载体方法  
        TestSynchronized.mutilThreadInvoke(5);  
    }  
      
    /** 
     * 同步的载体方法 
     */  
    public synchronized static void method(){  
          
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println("method print. time:"+System.currentTimeMillis());  
    }  
      
    /** 
     * 多线程调用载体方法 
     * @param n：调用线程的数目 
     */  
    public static void mutilThreadInvoke(int n){  
          
        for(int i=0 ; i < n ; i++){  
            new Thread(  
                    new Runnable(){  
  
                        @Override  
                        public void run() {  
                            // 调用载体方法  
                            TestSynchronized.method();  
                        }  
                          
                    }  
            ).start();  
        }  
    }  
} 
