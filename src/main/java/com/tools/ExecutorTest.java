package com.tools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//例子中生成线程池采用了工具类Executors的静态方法。
		//newFixedThreadPool可以生成固定大小的线程池
		//newCachedThreadPool可以生成一个无界、可以自动回收的线程池，
		//newSingleThreadScheduledExecutor可以生成一个单个线程的线程池。
		//newScheduledThreadPool还可以生成支持周期任务的线程池。
		//一般用户场景下各种不同设置要求的线程池都可以这样生成，不用自己new一个线程池出来。
		
		
		// 一个有7个作业线程的线程池，老大的老大找到一个管7个人的小团队的老大
	       ExecutorService laoda = Executors.newFixedThreadPool(7);
			 //提交作业给老大，作业内容封装在Callable中，约定好了输出的类型是String。
				String outputs = laoda.submit(
				         new Callable<String>() {
				             public String call() throws Exception
				             {
				                 return "I am a task, which submited by the so called laoda, and run by those anonymous workers";
				             }
				             //提交后就等着结果吧，到底是手下7个作业中谁领到任务了，老大是不关心的。
				         }).get();

				System.out.println(outputs);

	}

}
