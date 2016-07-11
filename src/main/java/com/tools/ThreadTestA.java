package com.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTestA {
	public static void main(String[] args) {
		ExecutorService e = Executors.newFixedThreadPool(10);
		e.execute(new MyRunnableB());
		e.execute(new MyRunnableA());
		e.shutdown();
	}

}

class MyRunnableA implements Runnable {

	public void run() {
		System.out.println("Runnable:run()....");
		int i = 0;
		while (i < 20) {
			i++;
			for (int j = 0; j < 1000000; j++)
				;
			System.out.println("i=" + i);
		}
	}
}

class MyRunnableB implements Runnable {
	public void run() {
		char c = 'A' - 1;
		while (c < 'Z') {
			c++;
			for (int j = 0; j < 1000000; j++)
				;
			System.out.println("c=" + c);
		}
	}
}
