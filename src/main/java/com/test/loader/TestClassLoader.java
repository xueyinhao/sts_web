package com.test.loader;

public class TestClassLoader {
	public static void main(String[] args) {
		
		// 加载器：sun.misc.Launcher$AppClassLoader
		System.out.println(TestClassLoader.class.getClassLoader().getClass().getName());
		// 加载器：BootStrap(loader为null的情况)
		System.out.println(System.class.getClassLoader());//
		System.out.println("----------------查看类加载器的层次结构关系-------------------");
		ClassLoader loader = TestClassLoader.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.getClass().getName());
			loader = loader.getParent();
		}
		System.out.println(loader);
	}
	/**
	 * 运行结果： sun.misc.Launcher$AppClassLoader 
	 * null Bootstrap类加载器,不是java类使用c++编写
	 * ----------------查看类加载器的层次结构关系-------------------
	 * sun.misc.Launcher$AppClassLoader 
	 * sun.misc.Launcher$ExtClassLoader 
	 * null
	 */
}
