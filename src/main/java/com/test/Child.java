package com.test;

public class Child extends Father {

	static {
		System.out.println("child-->static");
	}

	private int n = 20;

	{
		n = 30;
	}

	public int x = 200;

	public Child() {
		this("The other constructor");
		System.out.println("child constructor body: " + n);
	}

	public Child(String s) {
		System.out.println(s);
	}

	public void age() {
		System.out.println("age=" + n);
	}

	public void printX() {
		System.out.println("x=" + x);
	}

	public static void main(String[] args) {
		new Child().printX();
	}
}

class Father {

	static {
		System.out.println("super-->static");
	}

	public static int n = 10;
	public int x = 100;

	public Father() {
		System.out.println("super's x=" + x);
		age();
	}

	public void age() {
		System.out.println("nothing");
	}
}
