package com.test;

import java.util.ArrayList;
import java.util.List;

public class TestCollection {

	public static void main(String[] args) {
		// List<String> list = new LinkedList<String>();
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
//		list.add("d");
//		list.add("e");
		for (int i = 0; i < list.size(); i++) { // 循环删除集合中的元素
			list.remove(i);
			System.out.println("还剩余的元素个数：" + list.size());
		}
		System.out.println("还剩余的元素个数：" + list.size());

		System.out.println("---------------");

		// for (String s : list) { // 循环删除集合中的元素
		// if(s.equals("b"))
		// list.remove(s);
		// System.out.println("还剩余的元素个数：" + list.size());
		// }

	}

}
